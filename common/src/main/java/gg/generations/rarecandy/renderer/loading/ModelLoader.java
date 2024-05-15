package gg.generations.rarecandy.renderer.loading;

import dev.thecodewarrior.binarysmd.formats.SMDTextReader;
import dev.thecodewarrior.binarysmd.studiomdl.SMDFile;
import generations.gg.generations.core.generationscore.client.GenerationsTextureLoader;
import generations.gg.generations.core.generationscore.client.render.rarecandy.CompiledModel;
import gg.generations.rarecandy.pokeutils.*;
import gg.generations.rarecandy.pokeutils.gfbanm.AnimationT;
import gg.generations.rarecandy.pokeutils.reader.ITextureLoader;
import gg.generations.rarecandy.pokeutils.reader.TextureReference;
import gg.generations.rarecandy.pokeutils.tracm.TRACM;
import gg.generations.rarecandy.pokeutils.tranm.TRANMT;
import gg.generations.rarecandy.renderer.ThreadSafety;
import gg.generations.rarecandy.renderer.animation.*;
import gg.generations.rarecandy.renderer.components.AnimatedMeshObject;
import gg.generations.rarecandy.renderer.components.MeshObject;
import gg.generations.rarecandy.renderer.components.MultiRenderObject;
import gg.generations.rarecandy.renderer.components.RenderObject;
import gg.generations.rarecandy.renderer.model.GLModel;
import gg.generations.rarecandy.renderer.model.GlCallSupplier;
import gg.generations.rarecandy.renderer.model.MeshDrawCommand;
import gg.generations.rarecandy.renderer.model.Variant;
import gg.generations.rarecandy.renderer.model.material.Material;
import gg.generations.rarecandy.renderer.rendering.RareCandy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.assimp.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.MemoryUtil;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.lwjgl.opengl.GL30C.*;

public class ModelLoader {
    private final ExecutorService modelLoadingPool;

    public ModelLoader() {
        this.modelLoadingPool = Executors.newFixedThreadPool(2);
    }

    public interface NodeProvider {
        Animation.AnimationNode[] getNode(Animation animation, Skeleton skeleton);
    }

    public static <T extends MeshObject> void create2(MultiRenderObject<T> objects, PixelAsset gltfModel, Map<String, SMDFile> smdFileMap, Map<String, byte[]> gfbFileMap, Map<String, Pair<byte[], byte[]>> trFilesMap, Map<String, String> images, ModelConfig config, List<Runnable> glCalls, Supplier<T> supplier) {
        create2(objects, gltfModel, smdFileMap, gfbFileMap, trFilesMap, images, config, glCalls, supplier, Animation.GLB_SPEED);
    }

    private static List<Attribute> ATTRIBUTES = List.of(
            Attribute.POSITION,
            Attribute.TEXCOORD,
            Attribute.NORMAL,
            Attribute.BONE_IDS,
            Attribute.BONE_WEIGHTS
    );

    public static <T extends MeshObject> void create2(MultiRenderObject<T> objects, PixelAsset asset, Map<String, SMDFile> smdFileMap, Map<String, byte[]> gfbFileMap, Map<String, Pair<byte[], byte[]>> trFilesMap, Map<String, String> images, ModelConfig config, List<Runnable> glCalls, Supplier<T> supplier, int animationSpeed) {
        if (config == null) throw new RuntimeException("config.json can't be null.");
//        checkForRootTransformation(objects, gltfModel);

        Map<String, NodeProvider> animationNodeMap = new HashMap<>();
        Map<String, Integer> fpsMap = new HashMap<>();
        Map<String, Map<String, Animation.Offset>> offsetsMap = new HashMap<>();

        Set<String> animationNames = new HashSet<>();

        var gltfModel = ModelLoader.read(asset);

        var rootNode = ModelNode.create(gltfModel.mRootNode());

        Skeleton skeleton = new Skeleton(rootNode);

        var meshes = Mesh.readMeshData(skeleton, gltfModel);

        for (var entry : trFilesMap.entrySet()) {
            var name = entry.getKey();
            var tranm = entry.getValue().a() != null ? TRANMT.deserializeFromBinary(entry.getValue().a()) : null;
            var tracm = entry.getValue().b() != null ? TRACM.getRootAsTRACM(ByteBuffer.wrap(entry.getValue().b())) : null;

            if(tranm != null || tracm != null) {
                animationNames.add(name);
                if (tranm != null) {
                    animationNodeMap.putIfAbsent(name, (animation, skeleton1) -> TranmUtils.getNodes(animation, skeleton1, tranm));
                    fpsMap.putIfAbsent(name, (int) tranm.getInfo().getAnimationRate());
                }

                if (tracm != null) {
                    offsetsMap.putIfAbsent(name, TracmUtils.getOffsets(tracm));
                    fpsMap.putIfAbsent(name, (int) tracm.config().framerate());
                }
            }
        }

        for (var entry : gfbFileMap.entrySet()) {
            var name = entry.getKey();

            try {
                var gfbAnim = AnimationT.deserializeFromBinary(entry.getValue());

                if(gfbAnim.getMaterial() != null || gfbAnim.getSkeleton() != null) {
                    animationNames.add(name);
                    fpsMap.put(name, (int) gfbAnim.getInfo().getFrameRate());

                    if(gfbAnim.getSkeleton() != null) {
                        animationNodeMap.put(name, (animation, skeleton13) -> GfbanmUtils.getNodes(animation, skeleton13, gfbAnim));
                    }

                    if(gfbAnim.getMaterial() != null) {
                        offsetsMap.put(name, GfbanmUtils.getOffsets(gfbAnim));
                    }
                }
            } catch (Exception e) {
                System.out.println("Failed to load animation %s due to the following exception: %s".formatted(name, e.getMessage()));
                e.printStackTrace();
            }
        }

        for (var entry : smdFileMap.entrySet()) {
            var key = entry.getKey();
            var value = entry.getValue();

            if(value != null) {
                animationNames.add(key);
                animationNodeMap.putIfAbsent(key, (NodeProvider) (animation, skeleton12) -> SmdUtils.getNode(animation, skeleton12, value));
                fpsMap.putIfAbsent(key, 30);
            }
        }

        Map<String, Animation> animations = new HashMap<>();

        for(var name : animationNames) {
            var fps = fpsMap.get(name);
            fps = config.animationFpsOverride != null && config.animationFpsOverride.containsKey(name) ? config.animationFpsOverride.get(name) : fps;

            var offsets = offsetsMap.getOrDefault(name, new HashMap<>());
            offsets.forEach((trackName, offset) -> config.getMaterialsForAnimation(trackName).forEach(a -> offsets.put(a, offset)));

            var nodes = animationNodeMap.getOrDefault(name, (animation, skeleton14) -> null);
            var ignoreScaling = config.ignoreScaleInAnimation != null && (config.ignoreScaleInAnimation.contains(name) || config.ignoreScaleInAnimation.contains("all"));

            animations.put(name, new Animation(name, fps, skeleton, nodes, offsets, ignoreScaling));
        }

        Map<String, ModelConfig.HideDuringAnimation> hideDuringAnimation = new HashMap<>();

        var materials = new HashMap<String, Material>();

        config.materials.forEach((k, v) -> {
            var material = MaterialReference.process(k, config.materials, images);

            materials.put(k, material);
        });

        var defaultVariant = new HashMap<String, Variant>();
        config.defaultVariant.forEach((k, v) -> {
            var variant = new Variant(materials.get(v.material()), v.hide(), v.offset());
            defaultVariant.put(k, variant);
        });

        var variantMaterialMap = new HashMap<String, Map<String, Material>>();
        var variantHideMap = new HashMap<String, List<String>>();
        var variantOffsetMap = new HashMap<String, Map<String, Vector2f>>();


        if(config.hideDuringAnimation != null) {
            hideDuringAnimation = config.hideDuringAnimation;
        }

        if(config.variants != null) {
            for (Map.Entry<String, VariantParent> entry : config.variants.entrySet()) {
                String variantKey = entry.getKey();
                VariantParent variantParent = entry.getValue();

                VariantParent child = config.variants.get(variantParent.inherits());

                var map = variantParent.details();

                while (child != null) {
                    var details = child.details();

                    applyVariantDetails(details, map);

                    child = config.variants.get(child.inherits());
                }

                applyVariantDetails(config.defaultVariant, map);

                var matMap = variantMaterialMap.computeIfAbsent(variantKey, s3 -> new HashMap<>());
                var hideMap = variantHideMap.computeIfAbsent(variantKey, s3 -> new ArrayList<>());
                var offsetMap = variantOffsetMap.computeIfAbsent(variantKey, s3 -> new HashMap<>());


                applyVariant(materials, matMap, hideMap, offsetMap, map);
            }
        } else {
            var matMap = variantMaterialMap.computeIfAbsent("regular", s3 -> new HashMap<>());
            var hideMap = variantHideMap.computeIfAbsent("regular", s3 -> new ArrayList<>());
            var offsetMap = variantOffsetMap.computeIfAbsent("regular", s3 -> new HashMap<>());


            defaultVariant.forEach((s1, variant) -> {
                matMap.put(s1, variant.material());
                if (variant.hide()) hideMap.add(s1);
                if (variant.offset() != null) offsetMap.put(s1, variant.offset());

            });
        }

        var matMap = reverseMap(variantMaterialMap);
        var hidMap = reverseListMap(variantHideMap);
        var offsetMap = reverseMap(variantOffsetMap);


//        objects.dimensions.set(calculateDimensions(meshes));

        Stream.of(meshes).flatMap(a -> a.positions().stream()).forEach(objects.dimensions::max);
        for (var meshModel : meshes) {
            processPrimitiveModels(objects, supplier, meshModel, matMap, hidMap, offsetMap, glCalls, skeleton, animations, hideDuringAnimation);
        }


        var transform = new Matrix4f();

        traverseTree(transform, rootNode, objects);

        Assimp.aiReleaseImport(gltfModel);

    }

    private static <T extends MeshObject> void traverseTree(Matrix4f transform, ModelNode node, MultiRenderObject<T> objects) {
        applyTransforms(transform, node);

        objects.setRootTransformation(objects.getRootTransformation().add(transform, new Matrix4f()));

        for (var child : node.children) {
            traverseTree(transform, child, objects);
        }
    }

    private static void applyVariantDetails(Map<String, VariantDetails> applied, Map<String, VariantDetails> appliee) {
        for (Map.Entry<String, VariantDetails> entry : applied.entrySet()) {
            String k = entry.getKey();
            VariantDetails v = entry.getValue();
            appliee.compute(k, (s, variantDetails) -> {
                if(variantDetails == null) return v;
                return variantDetails.fillIn(v);
            });
        }
    }

    private static void applyVariant(Map<String, Material> materials, Map<String, Material> matMap, List<String> hideMap, Map<String, Vector2f> offsetMap, Map<String, VariantDetails> variantMap) {
        variantMap.forEach((k, v) -> {
            matMap.put(k, materials.get(v.material()));
            if (v.hide() != null && v.hide()) hideMap.add(k);
            if (v.offset() != null) offsetMap.put(k, v.offset());
        });
    }

    public static Vector3f calculateDimensions(Mesh[] meshes) {
        var vec = new Vector3f();
        var pos = new Vector3f();

        for(var mesh : meshes) {
            var buf = mesh.positions();
            var smallestVertexX = 0f;
            var smallestVertexY = 0f;
            var smallestVertexZ = 0f;
            var largestVertexX = 0f;
            var largestVertexY = 0f;
            var largestVertexZ = 0f;
            for (int i = 0; i < buf.size(); i += 3) { // Start at the y entry of every vertex and increment by 12 because there are 12 bytes per vertex
                var xPoint = buf.get(i).x();
                var yPoint = buf.get(i).y();
                var zPoint = buf.get(i).z();
                smallestVertexX = Math.min(smallestVertexX, xPoint);
                smallestVertexY = Math.min(smallestVertexY, yPoint);
                smallestVertexZ = Math.min(smallestVertexZ, zPoint);
                largestVertexX = Math.max(largestVertexX, xPoint);
                largestVertexY = Math.max(largestVertexY, yPoint);
                largestVertexZ = Math.max(largestVertexZ, zPoint);
            }

            vec.set(largestVertexX - smallestVertexX, largestVertexY - smallestVertexY, largestVertexZ - smallestVertexZ);

            pos.max(vec);

        }

        return pos;
    }

    private static void applyTransforms(Matrix4f transform, ModelNode node) {
        transform.set(node.transform);
    }

    private static <T extends MeshObject> void processPrimitiveModels(MultiRenderObject<T> objects, Supplier<T> objSupplier, Mesh mesh, Map<String, Map<String, Material>> materialMap, Map<String, List<String>> hiddenMap, Map<String, Map<String, Vector2f>> offsetMap, List<Runnable> glCalls, @Nullable Skeleton skeleton, @Nullable Map<String, Animation> animations, Map<String, ModelConfig.HideDuringAnimation> hideDuringAnimations) {
        var name = mesh.name();

        var map = materialMap.get(name);
        var list = hiddenMap.get(name);
        var offset = offsetMap.get(name);

        var glModel = processPrimitiveModel(mesh, skeleton);
        var renderObject = objSupplier.get();

        if (animations != null && renderObject instanceof AnimatedMeshObject animatedMeshObject) {
            animatedMeshObject.setup(map, list, offset, glModel, name, skeleton, animations, hideDuringAnimations.getOrDefault(name, ModelConfig.HideDuringAnimation.NONE));
        } else {
            renderObject.setup(map, list, offset, glModel, name);
        }

        objects.add(renderObject);
    }

    private static GLModel processPrimitiveModel(Mesh mesh, Skeleton skeleton) {
        var model = new GLModel();

        var length = calculateVertexSize(ATTRIBUTES);
        var amount = mesh.positions().size();

        var vertexBuffer = MemoryUtil.memAlloc(length * amount);

        var bones = IntStream.range(0, amount).mapToObj(a -> new VertexBoneData()).toList();


        mesh.bones().forEach(bone -> {
            var boneId = skeleton.getId(bone);

            for(var weight : bone.weights) {
                if(weight.weight == 0.0) return;
                else {
                    bones.get(weight.vertexId).addBoneData(boneId, weight.weight);
                }
            }
        });

        var isEmpty = bones.stream().allMatch(VertexBoneData::isEmpty);

        for (int i = 0; i < amount; i++) {

            var position = mesh.positions().get(i);
            var uv = mesh.uvs().get(i);
            var normal = mesh.normals().get(i);
            var bone = bones.get(i);

            vertexBuffer.putFloat(position.x);
            vertexBuffer.putFloat(position.y);
            vertexBuffer.putFloat(position.z);
            vertexBuffer.putFloat(uv.x);
            vertexBuffer.putFloat(uv.y);
            vertexBuffer.putFloat(normal.x);
            vertexBuffer.putFloat(normal.y);
            vertexBuffer.putFloat(normal.z);

            if(isEmpty) {
                vertexBuffer.put((byte) 1);
                vertexBuffer.put((byte) 0);
                vertexBuffer.put((byte) 0);
                vertexBuffer.put((byte) 0);

                vertexBuffer.putFloat(1);
                vertexBuffer.putFloat(0);
                vertexBuffer.putFloat(0);
                vertexBuffer.putFloat(0);
            } else {
                vertexBuffer.put((byte) bone.ids()[0]);
                vertexBuffer.put((byte) bone.ids()[1]);
                vertexBuffer.put((byte) bone.ids()[2]);
                vertexBuffer.put((byte) bone.ids()[3]);

                vertexBuffer.putFloat(bone.weights()[0]);
                vertexBuffer.putFloat(bone.weights()[1]);
                vertexBuffer.putFloat(bone.weights()[2]);
                vertexBuffer.putFloat(bone.weights()[3]);
            }
        }

        vertexBuffer.flip();
        model.vertexBuffer = vertexBuffer;

        var indexBuffer = MemoryUtil.memAlloc(mesh.indices().size() * 4);
        indexBuffer.asIntBuffer().put(mesh.indices().stream().mapToInt(a -> a).toArray()).flip();

        model.indexBuffer = indexBuffer;
        model.indexSize = mesh.indices().size();

        return model;
    }

    public static int generateVao(ByteBuffer vertexBuffer, List<Attribute> layout) {
        var vao = glGenVertexArrays();

        glBindVertexArray(vao);
        var stride = calculateVertexSize(layout);
        var attribPtr = 0;

        // I hate openGL. why cant I keep the vertex data and vertex layout separate :(
        var vbo = glGenBuffers();

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);

        for (int i = 0; i < layout.size(); i++) {
            var attrib = layout.get(i);
            glEnableVertexAttribArray(i);
            glVertexAttribPointer(
                    i,
                    attrib.amount(),
                    attrib.glType(),
                    false,
                    stride,
                    attribPtr
            );
            attribPtr += calculateAttributeSize(attrib);
        }

        glBindVertexArray(0);

        return vao;
    }

    private static void vertexAttribPointer(Attribute data, int binding) {
        GL20.glEnableVertexAttribArray(binding);
        GL20.glVertexAttribPointer(
                binding,
                data.amount(),
                data.glType(),
                false,
                0,
                0);
    }

    public <T extends RenderObject> MultiRenderObject<T> createObject(CompiledModel model, @NotNull Supplier<PixelAsset> is, GlCallSupplier<T> objectCreator, Consumer<MultiRenderObject<T>> onFinish) {
        var obj = new MultiRenderObject<T>();
        var task = threadedCreateObject(model, obj, is, objectCreator, onFinish);
        if (RareCandy.DEBUG_THREADS) task.run();
        else modelLoadingPool.submit(task);
        return obj;
    }

    public MultiRenderObject<MeshObject> generatePlane(float width, float length, Consumer<MultiRenderObject<MeshObject>> onFinish) {
        var pair = PlaneGenerator.generatePlane(width, length);

        var task = ThreadSafety.wrapException(() -> {
            ThreadSafety.runOnContextThread(() -> {
                pair.a().forEach(Runnable::run);
                pair.b().updateDimensions();
                if (onFinish != null) onFinish.accept(pair.b());
            });
        });
        if (RareCandy.DEBUG_THREADS) task.run();
        else modelLoadingPool.submit(task);

        return pair.b();
    }

    private <T extends RenderObject> Runnable threadedCreateObject(CompiledModel model, MultiRenderObject<T> obj, @NotNull Supplier<PixelAsset> is, GlCallSupplier<T> objectCreator, Consumer<MultiRenderObject<T>> onFinish) {
        return ThreadSafety.wrapException(() -> {
            try {
                var asset = is.get();
                var config = asset.getConfig();

                var images = readImages(asset, model);

                if (asset.getModelFile() == null) return;

                if (config != null) obj.scale = config.scale;
//            var model = read(asset);
                var smdAnims = readSmdAnimations(asset);
                var gfbAnims = readGfbAnimations(asset);
                var trAnims = readtrAnimations(asset);
                var glCalls = objectCreator.getCalls(asset, smdAnims, gfbAnims, trAnims, images, config, obj);
                ThreadSafety.runOnContextThread(() -> {
                    glCalls.forEach(Runnable::run);
                    obj.updateDimensions();
                    if (onFinish != null) onFinish.accept(obj);
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private Map<String, String> readImages(PixelAsset asset, CompiledModel model) {
        var images = asset.getImageFiles();
        var map = new HashMap<String, String>();
        for (var entry : images) {
            var key = entry.getKey();

//            try {
                var id = asset.name + "-" + key;
                ((GenerationsTextureLoader) GenerationsTextureLoader.instance()).register(model, id, entry.getValue());

                map.put(key, id);
//            } catch (IOException e) {
//                System.out.print("Error couldn't load: " + key); //TODO: Logger solution.
//            }
        }

        return map;
    }

    private Map<String, byte[]> readGfbAnimations(PixelAsset asset) {
        return asset.files.entrySet().stream()
                .filter(entry -> entry.getKey().endsWith(".pkx") || entry.getKey().endsWith(".gfbanm"))
                .collect(Collectors.toMap(this::cleanAnimName, Map.Entry::getValue));
    }

    private HashMap<String, Pair<byte[], byte[]>> readtrAnimations(PixelAsset asset) {
        var map = new HashMap<String, Pair<byte[], byte[]>>();

        var list = asset.files.keySet().stream().filter(a -> a.endsWith("tranm") || a.endsWith("tracm")).collect(Collectors.toCollection(ArrayList::new));

        while (!list.isEmpty()) {
            var a = list.remove(0);

            if (a.endsWith(".tranm")) {
                var index = list.indexOf(a.replace(".tranm", ".tracm"));

                if (index != -1) {
                    var b = list.remove(index);

                    map.put(a.replace(".tranm", ""), new Pair<>(asset.files.get(a), asset.files.get(b)));
                } else {
                    map.put(a.replace(".tranm", ""), new Pair<>(asset.files.get(a), null));
                }
            } else {
                if (a.endsWith(".tracm")) {
                    var index = list.indexOf(a.replace(".tracm", ".tranm"));

                    if (index != -1) {
                        var b = list.remove(index);

                        map.put(a.replace(".tracm", ""), new Pair<>(asset.files.get(b), asset.files.get(a)));
                    } else {
                        map.put(a.replace(".tracm", ""), new Pair<>(null, asset.files.get(a)));
                    }
                }
            }

        }

        return map;
    }


    public String cleanAnimName(Map.Entry<String, byte[]> entry) {
        var str = entry.getKey();
        return cleanAnimName(str);
    }

    public String cleanAnimName(String str) {
        var substringEnd = str.lastIndexOf(".") == -1 ? str.length() : str.lastIndexOf(".");
        var substringStart = str.lastIndexOf("/") == -1 ? 0 : str.lastIndexOf("/");
        return str.substring(substringStart, substringEnd);
    }

    private Map<String, SMDFile> readSmdAnimations(PixelAsset pixelAsset) {
        var files = pixelAsset.getAnimationFiles();
        var map = new HashMap<String, SMDFile>();
        var reader = new SMDTextReader();

        for (var entry : files) {
            var smdFile = reader.read(new String(entry.getValue()));
            map.put(cleanAnimName(entry.getKey().replace(".smd", "")), smdFile);
        }

        return map;
    }

    public void close() {
        modelLoadingPool.shutdown();
    }

    public static AIScene read(PixelAsset asset) {
        var name = asset.modelName;

        var fileIo = AIFileIO.create()
                .OpenProc((pFileIO, pFileName, openMode) -> {
                    var fileName = MemoryUtil.memUTF8(pFileName);
                    var bytes = asset.get(fileName);
                    var data = BufferUtils.createByteBuffer(bytes.length);
                    data.put(bytes);
                    data.flip();

                    return AIFile.create()
                            .ReadProc((pFile, pBuffer, size, count) -> {
                                var max = Math.min(data.remaining() / size, count);
                                MemoryUtil.memCopy(MemoryUtil.memAddress(data), pBuffer, max * size);
                                data.position((int) (data.position() + max * size));
                                return max;
                            })
                            .SeekProc((pFile, offset, origin) -> {
                                switch (origin) {
                                    case Assimp.aiOrigin_CUR -> data.position(data.position() + (int) offset);
                                    case Assimp.aiOrigin_SET -> data.position((int) offset);
                                    case Assimp.aiOrigin_END -> data.position(data.limit() + (int) offset);
                                }

                                return 0;
                            })
                            .FileSizeProc(pFile -> data.limit())
                            .address();
                })
                .CloseProc((pFileIO, pFile) -> {
                    var aiFile = AIFile.create(pFile);
                    aiFile.ReadProc().free();
                    aiFile.SeekProc().free();
                    aiFile.FileSizeProc().free();
                });

        var scene = Assimp.aiImportFileEx(name, Assimp.aiProcess_Triangulate | Assimp.aiProcess_ImproveCacheLocality, fileIo);

        if (scene == null) throw new RuntimeException(Assimp.aiGetErrorString());

        return scene;
//            return reader.readWithoutReferences(new ByteArrayInputStream(asset.getModelFile()));
    }

    public static <T> Map<String, Map<String, T>> reverseMap(Map<String, Map<String, T>> inputMap) {
        Map<String, Map<String, T>> reversedMap = new HashMap<>();

        for (Map.Entry<String, Map<String, T>> outerEntry : inputMap.entrySet()) {
            String outerKey = outerEntry.getKey();
            Map<String, T> innerMap = outerEntry.getValue();

            for (Map.Entry<String, T> innerEntry : innerMap.entrySet()) {
                String innerKey = innerEntry.getKey();
                T value = innerEntry.getValue();

                reversedMap.computeIfAbsent(innerKey, k -> new HashMap<>()).put(outerKey, value);
            }
        }

        return reversedMap;
    }

    public static Map<String, List<String>> reverseListMap(Map<String, List<String>> inputMap) {
        Map<String, List<String>> reversedMap = new HashMap<>();

        for (Map.Entry<String, List<String>> entry : inputMap.entrySet()) {
            String outerKey = entry.getKey();
            List<String> innerList = entry.getValue();

            for (String innerKey : innerList) {
                reversedMap.computeIfAbsent(innerKey, k -> new ArrayList<>()).add(outerKey);
            }
        }

        return reversedMap;
    }

    public static Matrix4f from(Matrix4f transform, AIMatrix4x4 aiMat4) {
        return transform.set(aiMat4.a1(), aiMat4.a2(), aiMat4.a3(), aiMat4.a4(),
                aiMat4.b1(), aiMat4.b2(), aiMat4.b3(), aiMat4.b4(),
                aiMat4.c1(), aiMat4.c2(), aiMat4.c3(), aiMat4.c4(),
                aiMat4.d1(), aiMat4.d2(), aiMat4.d3(), aiMat4.d4());
    }

    public static int calculateVertexSize(List<Attribute> layout) {
        var size = 0;
        for (var attrib : layout) size += calculateAttributeSize(attrib);
        return size;
    }

    public static int calculateAttributeSize(Attribute attrib) {
        return switch (attrib.glType()) {
            case GL_FLOAT, GL_UNSIGNED_INT, GL_INT -> 4;
            case GL_BYTE, GL_UNSIGNED_BYTE -> 1;
            case GL_SHORT, GL_UNSIGNED_SHORT, GL_HALF_FLOAT -> 2;
            default -> throw new IllegalStateException("Unexpected OpenGL Attribute type: " + attrib.glType() + ". If this is wrong, please contact hydos");
        } * attrib.amount();
    }
}