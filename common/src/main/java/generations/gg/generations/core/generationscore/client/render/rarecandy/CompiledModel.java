package generations.gg.generations.core.generationscore.client.render.rarecandy;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferUploader;
import gg.generations.rarecandy.pokeutils.PixelAsset;
import gg.generations.rarecandy.renderer.components.MeshObject;
import gg.generations.rarecandy.renderer.components.MultiRenderObject;
import gg.generations.rarecandy.renderer.loading.ModelLoader;
import gg.generations.rarecandy.renderer.rendering.ObjectInstance;
import gg.generations.rarecandy.renderer.storage.ObjectManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.function.Supplier;

/**
 * Represents a compiled model which can be rendered
 */
public class CompiledModel {
    public final MultiRenderObject<MeshObject> renderObject;
    private PixelmonInstance guiInstance = new PixelmonInstance(new Matrix4f(), new Matrix4f(), null);

    public CompiledModel(ResourceLocation a, InputStream stream, Supplier<MeshObject> supplier) {
        this(a, stream, supplier, true);
    }

    public CompiledModel(ResourceLocation a, InputStream stream, Supplier<MeshObject> supplier, boolean requiresVariantTexture) {
        var loader = ModelRegistry.getWorldRareCandy().getLoader();
        this.renderObject = loader.createObject(
                () -> new PixelAsset(stream, a.toString()),
                 (gltfModel, smdFileMap, pkxFileMap, gfFileMap, textures, config, object) -> {
                    var glCalls = new ArrayList<Runnable>();
                    try {
                        ModelLoader.create2(object, gltfModel, smdFileMap, pkxFileMap, gfFileMap, textures, config, glCalls, supplier);
                    } catch (NullPointerException e) {
//                        RareCandyTest.LOGGER.error("Catching exception reading model %s.".formatted(a));
                        e.printStackTrace();
                    }
                    return glCalls;
                },
                object -> {
                    var manager = Minecraft.getInstance().getTextureManager();

                    if(requiresVariantTexture) {
                        object.availableVariants().forEach(s -> manager.register(new ResourceLocation("pk:" + s), new AbstractTexture() {
                            @Override
                            public void load(@NotNull ResourceManager resourceManager) {

                            }
                        }));
                    }

                    guiInstance.link(object);
                    if(object.scale == 0f) object.scale = 1.0f;
                }
        );
    }

    public void renderGui(ObjectInstance instance, Matrix4f projectionMatrix) {
        RenderSystem.enableDepthTest();
        BufferUploader.reset();
        RenderSystem.applyModelViewMatrix();
        instance.viewMatrix().set(RenderSystem.getModelViewMatrix());
        render(instance, projectionMatrix, ModelRegistry.getWorldRareCandy().objectManager);
        ModelRegistry.getWorldRareCandy().render(true, MinecraftClientGameProvider.getTimePassed());
    }

    public void render(ObjectInstance instance, Matrix4f projectionMatrix) {
        render(instance, projectionMatrix, ModelRegistry.getWorldRareCandy().objectManager);
    }

    public void render(ObjectInstance instance, Matrix4f projectionMatrix, ObjectManager objectManager) {
        if (!renderObject.isReady()) return;

        Minecraft.getInstance().getProfiler().push("create_model_instance");
        MinecraftClientGameProvider.projMatrix = projectionMatrix;
        objectManager.add(this.renderObject, instance);
        Minecraft.getInstance().getProfiler().pop();
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
}