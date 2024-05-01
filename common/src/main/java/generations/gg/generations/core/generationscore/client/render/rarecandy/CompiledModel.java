package generations.gg.generations.core.generationscore.client.render.rarecandy;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferUploader;
import generations.gg.generations.core.generationscore.GenerationsCore;
import gg.generations.rarecandy.pokeutils.PixelAsset;
import gg.generations.rarecandy.renderer.components.AnimatedMeshObject;
import gg.generations.rarecandy.renderer.components.MeshObject;
import gg.generations.rarecandy.renderer.components.MultiRenderObject;
import gg.generations.rarecandy.renderer.loading.ModelLoader;
import gg.generations.rarecandy.renderer.rendering.ObjectInstance;
import gg.generations.rarecandy.renderer.rendering.RenderStage;
import gg.generations.rarecandy.renderer.storage.ObjectManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Represents a compiled model which can be rendered
 */
public class CompiledModel {
    public final MultiRenderObject<MeshObject> renderObject;
    public final ResourceLocation name;

    private boolean isUploaded = false;
    private PixelmonInstance guiInstance = new PixelmonInstance(new Matrix4f(), new Matrix4f(), null);

    public CompiledModel(ResourceLocation name, InputStream stream, Supplier<MeshObject> supplier) {
        this(name, stream, supplier, true);
    }

    public CompiledModel(ResourceLocation name, InputStream stream, Supplier<MeshObject> supplier, boolean requiresVariantTexture) {
        this.name = name;
        var loader = ModelRegistry.getWorldRareCandy().getLoader();
        this.renderObject = loader.createObject(
                () -> new PixelAsset(stream, name.toString()),
                 (gltfModel, smdFileMap, pkxFileMap, gfFileMap, textures, config, object) -> {
                    var glCalls = new ArrayList<Runnable>();
                    try {
                        ModelLoader.create2(object, gltfModel, smdFileMap, pkxFileMap, gfFileMap, textures, config, glCalls, supplier);
                    } catch (Exception e) {
//                        RareCandyTest.LOGGER.error("Catching exception reading model %s.".formatted(a));
                    }
                    return glCalls;
                },
                object -> {
                    var manager = Minecraft.getInstance().getTextureManager();

                    if(requiresVariantTexture) {
                        if(!object.objects.isEmpty()) {

                            object.availableVariants().forEach(s -> manager.register(new ResourceLocation("pk:" + s), new AbstractTexture() {
                                @Override
                                public void load(@NotNull ResourceManager resourceManager) {

                                }
                            }));
                        } else {
                            System.out.println("Warning %s lacks variants: " + name.toString());
                        }
                    }

                    guiInstance.link(object);
                    if(object.scale == 0f) object.scale = 1.0f;
                }
        );
    }

    public CompiledModel() {
        this.renderObject = null;
        this.name = GenerationsCore.id("dummy");
    }

    public static CompiledModel of(ResourceLocation pair, Resource resource) {
        try {
            var is = resource.open();
            return new CompiledModel(pair, is, AnimatedMeshObject::new);
        } catch (Exception e) {
            var path = pair.toString();
            if (path.endsWith(".smdx") || path.endsWith(".pqc")) throw new RuntimeException("Tried reading a 1.12 .smdx or .pqc");
            throw new RuntimeException("Failed to load " + path, e);
        }
    }

    public void renderGui(ObjectInstance instance, Matrix4f projectionMatrix) {
        if(renderObject == null) return;

        RenderSystem.enableDepthTest();
        BufferUploader.reset();
        RenderSystem.applyModelViewMatrix();
        instance.viewMatrix().set(RenderSystem.getModelViewMatrix());
        render(instance, projectionMatrix, ModelRegistry.getGuiRareCandy().objectManager);


        ModelRegistry.getGuiRareCandy().render(false, MinecraftClientGameProvider.getTimePassed(), RenderStage.SOLID);
        ModelRegistry.getGuiRareCandy().render(true, MinecraftClientGameProvider.getTimePassed(), RenderStage.TRANSPARENT);
    }

    public void render(ObjectInstance instance, Matrix4f projectionMatrix) {
        if(renderObject == null) return;
        render(instance, projectionMatrix, ModelRegistry.getWorldRareCandy().objectManager);
    }

    private void render(ObjectInstance instance, Matrix4f projectionMatrix, ObjectManager objectManager) {
        if (!renderObject.isReady()) return;

        if(!isUploaded) uploadIfNeeded();

        Minecraft.getInstance().getProfiler().push("create_model_instance");
        MinecraftClientGameProvider.projMatrix = projectionMatrix;
        objectManager.add(this.renderObject, instance);
        Minecraft.getInstance().getProfiler().pop();
    }

    private void uploadIfNeeded() {
        List<MeshObject> objects = renderObject.objects;
        for (int i = 0, objectsSize = objects.size(); i < objectsSize; i++) {
            MeshObject meshObject = objects.get(i);

                meshObject.model.upload();

//            }
        }

        isUploaded = true;
    }

    public void delete() {
        try {
            renderObject.close();
        } catch (IOException e) {

        }
    }

    public PixelmonInstance getGuiInstance() {
        return guiInstance;
    }

    public boolean isUploaded() {
        return isUploaded;
    }

    public void removeFromGpu() {
        renderObject.objects.forEach(a -> a.model.removeFromGpu());
        isUploaded = false;
    }

    public static final CompiledModel DUMMY = new CompiledModel();
}