package generations.gg.generations.core.generationscore.client.render.rarecandy;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferUploader;
import gg.generations.rarecandy.arceus.core.RareCandyScene;
import gg.generations.rarecandy.arceus.model.RenderingInstance;
import gg.generations.rarecandy.arceus.model.pk.MultiRenderObject;
import gg.generations.rarecandy.arceus.model.pk.MultiRenderObjectInstance;
import gg.generationsmod.rarecandy.assimp.AssimpModelLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * Represents a compiled model which can be rendered
 */
public class CompiledModel {
    public MultiRenderObject<?> renderObject;

    private PixelmonInstance guiInstance;
    private boolean isReady = false;


    public CompiledModel(ResourceLocation a, InputStream stream) {
        this(a, stream, false);
    }

    public CompiledModel(ResourceLocation a, InputStream stream, boolean requiresVariantTexture) {

        var locator = new MinecraftPkFileLocator(a, stream);

        CompletableFuture.supplyAsync(() -> locator)
                .thenApply(b -> AssimpModelLoader.load(locator.getModelName(), locator, 0))
                .thenApply(MultiRenderObject::new)
                .thenAccept(new Consumer<MultiRenderObject<RenderingInstance>>() {
                    @Override
                    public void accept(MultiRenderObject<RenderingInstance> model) {
                        CompiledModel.this.renderObject = model;

                        var manager = Minecraft.getInstance().getTextureManager();

                        CompiledModel.this.isReady = true;

                        if(requiresVariantTexture) {
//                            TODO: Implment this
//                            renderObject.availableVariants().forEach(s -> manager.register(new ResourceLocation("pk:" + s), new AbstractTexture() {
//                                @Override
//                                public void load(@NotNull ResourceManager resourceManager) {
//
//                                }
//                            }));
                        }
                    }
                });


    }

    public void renderGui(PixelmonInstance guiInstance) {
        RenderSystem.enableDepthTest();
        BufferUploader.reset();
        RenderSystem.applyModelViewMatrix();
        render(guiInstance, ModelRegistry.getGuiRareCandy().objectManager);
        ModelRegistry.getGuiRareCandy().render();
    }

    public void render(MultiRenderObjectInstance<?> instance) {
        render(instance, ModelRegistry.getWorldRareCandy().objectManager);
    }

    public void render(MultiRenderObjectInstance<?> instance, RareCandyScene objectManager) {
        if (!isReady) return;

        Minecraft.getInstance().getProfiler().push("create_model_instance");
        MinecraftClientGameProvider.projMatrix =  RenderSystem.getProjectionMatrix();



        instance.addToScene(objectManager);
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

    public boolean isReady() {
        return isReady;
    }
}