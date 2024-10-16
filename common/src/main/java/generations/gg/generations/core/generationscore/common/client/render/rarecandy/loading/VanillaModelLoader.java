package generations.gg.generations.core.generationscore.common.client.render.rarecandy.loading;

import gg.generations.rarecandy.assimp.*;
import gg.generations.rarecandy.pokeutils.*;
import gg.generations.rarecandy.renderer.animation.Animation;
import gg.generations.rarecandy.renderer.animation.Skeleton;
import gg.generations.rarecandy.renderer.components.AnimatedMeshObject;
import gg.generations.rarecandy.renderer.components.MeshObject;
import gg.generations.rarecandy.renderer.components.MultiRenderObject;
import gg.generations.rarecandy.renderer.loading.*;
import gg.generations.rarecandy.renderer.model.*;
import gg.generations.rarecandy.renderer.model.material.Material;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static java.util.Objects.requireNonNull;

public class VanillaModelLoader {

    private static Vector3f temp = new Vector3f();


    public static <T extends MeshObject, V extends MultiRenderObject<T>> void processModel(V objects, PixelAsset asset, Map<String, AnimResource> animResources, Map<String, String> images, ModelConfig config, List<Runnable> glCalls, Supplier<T> supplier) {
        if (config == null) throw new RuntimeException("config.json can't be null.");

        var scene = ModelLoader.read(asset);

        var rootNode = ModelNode.create(scene.mRootNode());

        var meshes = IntStream.range(0, scene.mNumMeshes()).mapToObj(i -> AIMesh.create(scene.mMeshes().get(i))).toArray(AIMesh[]::new);

        Skeleton skeleton = new Skeleton(rootNode, meshes, config.excludeMeshNamesFromSkeleton);

        var animations = processAnimations(scene, skeleton, animResources, config);

        var variants = processVariants(config, images);

        for (var mesh : meshes) {
            processPrimitiveModels(objects, supplier, mesh, variants, glCalls, skeleton, animations, config.hideDuringAnimation, config.modelOptions != null ? config.modelOptions : Collections.<String, MeshOptions>emptyMap());
        }

        var transform = new Matrix4f();

        traverseTree(transform, rootNode, objects);

        Assimp.aiReleaseImport(scene);

    }

    private static Map<String, Animation> processAnimations(AIScene scene, Skeleton skeleton, Map<String, AnimResource> animResources, ModelConfig config) {
        extractAssimpAnimations(scene, skeleton, animResources);

        Map<String, Animation> animations = new HashMap<>();

        var offSetsToInsert = new HashMap<String, Animation.Offset>();

        animResources.forEach((name, animResource) -> {
            var fps = animResource.fps();
            fps = config.animationFpsOverride != null && config.animationFpsOverride.containsKey(name) ? config.animationFpsOverride.get(name) : fps;

            var offsets = animResource.getOffsets();
            offsets.forEach((trackName, offset) -> config.getMaterialsForAnimation(trackName).forEach(a -> offSetsToInsert.put(a, offset)));
            offsets.putAll(offSetsToInsert);
            offSetsToInsert.clear();

            var nodes = animResource.getNodes(skeleton);
            var ignoreScaling = config.ignoreScaleInAnimation != null && (config.ignoreScaleInAnimation.contains(name) || config.ignoreScaleInAnimation.contains("all"));

            animations.put(name, new Animation(name, (int) fps, skeleton, nodes, offsets, ignoreScaling, config.offsets.getOrDefault(name, new SkeletalTransform()).scale(config.scale)));
        });

        return animations;
    }

    private static void extractAssimpAnimations(AIScene scene, Skeleton skeleton, Map<String, AnimResource> animResources) {
        for (int i = 0; i < scene.mNumAnimations(); i++) {
            AIAnimation aiAnimation = AIAnimation.create(scene.mAnimations().get(i));
            var animName = aiAnimation.mName().dataString();

            var fps = aiAnimation.mTicksPerSecond();

            var animationNodes = new Animation.AnimationNode[skeleton.jointMap.size()];

            for (int channelIndex = 0; channelIndex < aiAnimation.mNumChannels(); channelIndex++) {
                var channel = AINodeAnim.create(aiAnimation.mChannels().get(channelIndex));

                var boneName = channel.mNodeName().dataString();

                if(!skeleton.boneIdMap.containsKey(boneName)) continue;

                var node = animationNodes[skeleton.boneIdMap.get(boneName)] = new Animation.AnimationNode();


                for (int posIndex = 0; posIndex < channel.mNumPositionKeys(); posIndex++) {
                    var posKey = channel.mPositionKeys().get(posIndex);

                    var time = posKey.mTime();
                    var pos = new Vector3f(posKey.mValue().x(), posKey.mValue().y(), posKey.mValue().z());

                    node.positionKeys.add(time, pos);
                }

                for (int rotIndex = 0; rotIndex < channel.mNumRotationKeys(); rotIndex++) {
                    var rotKey = channel.mRotationKeys().get(rotIndex);

                    var time = rotKey.mTime();
                    var rot = new Quaternionf(rotKey.mValue().x(), rotKey.mValue().y(), rotKey.mValue().z(), rotKey.mValue().w());

                    node.rotationKeys.add(time, rot);
                }

                for (int scaleIndex = 0; scaleIndex < channel.mNumScalingKeys(); scaleIndex++) {
                    var scaleKey = channel.mScalingKeys().get(scaleIndex);

                    var time = scaleKey.mTime();
                    var scale = new Vector3f(scaleKey.mValue().x(), scaleKey.mValue().y(), scaleKey.mValue().z());

                    node.scaleKeys.add(time, scale);
                }
            }

            for (int nodeIndex = 0; nodeIndex < animationNodes.length; nodeIndex++) {

                if(animationNodes[nodeIndex] == null) {
                    var node = new Animation.AnimationNode();
                    var joint = skeleton.jointMap.get(skeleton.bones[nodeIndex].name);

                    node.rotationKeys.add(0, joint.poseRotation);
                    node.rotationKeys.add(0, joint.poseRotation);
                    node.scaleKeys.add(0, joint.poseScale);
                }
            }

            animResources.putIfAbsent(animName, new GenericAnimResource((long) fps, animationNodes));
        }
    }

    private static Map<String, Map<String, Variant>> processVariants(ModelConfig config, Map<String, String> images) {
        var materials = config.prepMaterials(images);

        var defaultVariant = new HashMap<String, Variant>();

        Map<String, List<String>> aliases = config.aliases != null ? config.aliases : Collections.emptyMap();


        config.defaultVariant.forEach((k, v) -> {
            var variant = new Variant(materials.get(v.material()), v.hide(), v.offset());

            if(!aliases.isEmpty() && aliases.containsKey(k)) {
                for (String s : aliases.get(k)) {
                    defaultVariant.put(s, variant);
                }
            }
            else defaultVariant.put(k, variant);

        });

        var variants = new HashMap<String, Map<String, Variant>>();

        if(config.variants != null) {
            config.variants.forEach((variantKey, variantParent) -> {

                VariantParent child = config.variants.get(variantParent.inherits());

                var map = variantParent.details();

                while (child != null) {
                    var details = child.details();

                    applyVariantDetails(details, map);

                    child = config.variants.get(child.inherits());
                }

                applyVariantDetails(config.defaultVariant, map);

                applyVariant(variantKey, variants, materials, map, aliases);
            });
        } else {
            defaultVariant.forEach((s1, variant) -> {
                defaultVariant.forEach((s, variant1) -> variants.computeIfAbsent(s, a -> new HashMap<>()).put("regular", variant1));
            });
        }

        return variants;
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

    private static void applyVariant(
            String variantKey,
            Map<String, Map<String, Variant>> variants,
            Map<String, Material> materials,
            Map<String, VariantDetails> variantMap,
            Map<String, List<String>> aliases) {
        variantMap.forEach((k, v) -> {
            Material mat = materials.get(v.material());
            boolean hide = v.hide() != null && v.hide();
            var offset = v.offset() != null ? v.offset() : null;

            var variant = new Variant(mat, hide, offset);

            if(!aliases.isEmpty() && aliases.containsKey(k)) aliases.get(k).forEach(s -> variants.computeIfAbsent(s, s1 -> new HashMap<>()).put(variantKey, variant));
            else {
                variants.computeIfAbsent(k, s1 -> new HashMap<>()).put(variantKey, variant);
            }
        });
    }

    private static void applyTransforms(Matrix4f transform, ModelNode node) {
        transform.set(node.transform);
    }

    private static <T extends MeshObject> void processPrimitiveModels(MultiRenderObject<T> objects, Supplier<T> objSupplier, AIMesh mesh, Map<String, Map<String, Variant>> variants, List<Runnable> glCalls, @Nullable Skeleton skeleton, @Nullable Map<String, Animation> animations, Map<String, ModelConfig.HideDuringAnimation> hideDuringAnimations, Map<String, MeshOptions> meshOptions) {
        var name = mesh.mName().dataString();

        var renderObject = objSupplier.get();
        var glModel = processPrimitiveModel(skeleton, mesh, meshOptions, glCalls, objects.dimensions);

        var variant = variants.get(name);

        if (animations != null && renderObject instanceof AnimatedMeshObject animatedMeshObject) {
            animatedMeshObject.setup(variant, glModel, name, animations, hideDuringAnimations.getOrDefault(name, ModelConfig.HideDuringAnimation.NONE));
        } else {
            renderObject.setup(variant, glModel, name);
        }

        objects.add(renderObject);
    }

    private static RenderModel processPrimitiveModel(Skeleton skeleton, AIMesh mesh, Map<String, MeshOptions> options, List<Runnable> glCalls, Vector3f dimensions) {
        var name = mesh.mName().dataString();

        var invertFace = options.containsKey(name) && options.get(name).invert();

        var amount = mesh.mNumVertices();

        var positions = new float[amount * 3];
        var uvs = new float[amount * 2];
        var normals = new float[amount * 3];
        var boneIds =  new int[amount * 4];
        var boneWeights = new float[amount * 4];

        var aiFaces = mesh.mFaces();
        var indexBuffer = new int[mesh.mNumFaces() * 3];

        for (int j = 0; j < mesh.mNumFaces(); j++) {
            var aiFace = aiFaces.get(j).mIndices();
            var index = j * 3;

            indexBuffer[index + 0] = aiFace.get(invertFace ? 2 : 0);
            indexBuffer[index + 1] = aiFace.get(1);
            indexBuffer[index + 2] = aiFace.get(invertFace ? 0 : 2);
        }


        var aiVert = mesh.mVertices();
        var aiUV = mesh.mTextureCoords(0);

        if (aiUV == null) {
            throw new RuntimeException("Error UV coordinates not found!");
        }

        var aiNormals = mesh.mNormals();

        if (aiNormals == null) {
            throw new RuntimeException("Error Normals not found!");
        }

        int[] ids = new int[amount * 4];
        float[] weights = new float[amount * 4];

        if (mesh.mBones() != null) {
            var aiBones = requireNonNull(mesh.mBones());

            for (int boneIndex = 0; boneIndex < aiBones.capacity(); boneIndex++) {
                var aiBone = AIBone.create(aiBones.get(boneIndex));

                var weight = aiBone.mWeights();

                var index = skeleton.getId(aiBone.mName().dataString());

                for (int weightId = 0; weightId < weight.capacity(); weightId++) {
                    var aiWeight = weight.get(weightId);
                    var vertexId = aiWeight.mVertexId();

                    if(aiWeight.mWeight() > 0f) {
                        addBoneData(ids, weights, vertexId, index, aiWeight.mWeight());
                    }
                }
            }
        }

        var isEmpty = IntStream.range(0, ids.length).allMatch(a -> ids[a] == 0);

        for (int i = 0; i < amount; i++) {

            var position = aiVert.get(i);
            var uv = aiUV.get(i);
            var normal = aiNormals.get(i);

            var v2 = i * 2;
            var v3 = i * 3;
            var v4 = i * 4;

            positions[v3 + 0] = position.x();
            positions[v3 + 1] = position.y();
            positions[v3 + 2] = position.z();
            uvs[v2 + 0] = uv.x();
            uvs[v2 + 1] = 1 - uv.y();
            normals[v3 + 0] = normal.x();
            normals[v3 + 1] = normal.y();
            normals[v3 + 2] = normal.z();

            if(isEmpty) {
                boneIds[v4 + 0] = 1;
                boneIds[v4 + 1] = 0;
                boneIds[v4 + 2] = 0;
                boneIds[v4 + 3] = 0;

                boneWeights[v4 + 0] = 1.0f;
                boneWeights[v4 + 1] = 0.0f;
                boneWeights[v4 + 2] = 0.0f;
                boneWeights[v4 + 3] = 0.0f;
            } else {
                boneIds[v4 + 0] = ids[v4 + 0];
                boneIds[v4 + 1] = ids[v4 + 1];
                boneIds[v4 + 2] = ids[v4 + 2];
                boneIds[v4 + 3] = ids[v4 + 3];

                boneWeights[v4 + 0] = weights[v4 + 0];
                boneWeights[v4 + 1] = weights[v4 + 1];
                boneWeights[v4 + 2] = weights[v4 + 2];
                boneWeights[v4 + 3] = weights[v4 + 3];
            }

            dimensions.max(temp.set(position.x(), position.y(), position.z()));
        }

        var indexSize = mesh.mNumFaces() * 3;

        return new VanilaRenderModel(indexBuffer, positions, uvs, normals, boneIds, boneWeights, indexSize);
    }

    public static void addBoneData(int[] ids, float[] weights, int vertexId, int boneId, float weight) {
        var length = vertexId * 4;
        for (var i = 0 ; i < 4; i++) {
            var blep = length + i;

            if (weights[blep] == 0.0) {
                ids[blep] = boneId;
                weights[blep] = weight;

                return;
            }
        }
    }


    public static Matrix4f from(Matrix4f transform, AIMatrix4x4 aiMat4) {
        return transform.set(aiMat4.a1(), aiMat4.a2(), aiMat4.a3(), aiMat4.a4(),
                aiMat4.b1(), aiMat4.b2(), aiMat4.b3(), aiMat4.b4(),
                aiMat4.c1(), aiMat4.c2(), aiMat4.c3(), aiMat4.c4(),
                aiMat4.d1(), aiMat4.d2(), aiMat4.d3(), aiMat4.d4());
    }
}