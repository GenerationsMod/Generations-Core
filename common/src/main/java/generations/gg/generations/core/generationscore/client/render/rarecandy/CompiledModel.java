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
import org.joml.Matrix4f;

import java.io.IOException;
import java.io.InputStream;

/**
 * Represents a compiled model which can be rendered
 */
public class CompiledModel {
    public MultiRenderObject<?> renderObject;

    private GenerationsObjectInstance guiInstance;
    private boolean isReady = false;


    public CompiledModel(ResourceLocation a, InputStream stream) {
        this(a, stream, false);
    }

    public CompiledModel(ResourceLocation a, InputStream stream, boolean requiresVariantTexture) {

        var locator = new MinecraftPkFileLocator(a, stream);

        ModelRegistry.addRunnable(() -> {
            var rawModel = AssimpModelLoader.load(locator.getModelName(), locator, 0);
            var model = new MultiRenderObject<>(rawModel);

            var manager = Minecraft.getInstance().getTextureManager();

            System.out.println("Rawr");

            renderObject = model;
            guiInstance = new GenerationsObjectInstance(renderObject, new Matrix4f(), null);
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
        });
    }

    public void renderGui(GenerationsObjectInstance guiInstance) {
        RenderSystem.enableDepthTest();
        BufferUploader.reset();
        RenderSystem.applyModelViewMatrix();
        render(guiInstance, ModelRegistry.getGuiRareCandy().objectManager);
        ModelRegistry.getGuiRareCandy().render();
        ModelRegistry.getGuiRareCandy().clear();
    }

    public void render(MultiRenderObjectInstance<?> instance) {
        render(instance, ModelRegistry.getWorldRareCandy().objectManager);
    }

    public void render(MultiRenderObjectInstance<?> instance, RareCandyScene<RenderingInstance> objectManager) {
        if (!isReady) {
            return;
        }

        Minecraft.getInstance().getProfiler().push("create_model_instance");
        MinecraftClientGameProvider.projMatrix =  RenderSystem.getProjectionMatrix();



        instance.addToScene(objectManager);
        Minecraft.getInstance().getProfiler().pop();
    }

    public void delete() {
        try {
            if(isReady()) renderObject.close();
        } catch (IOException e) {

        }
    }

    public GenerationsObjectInstance getGuiInstance() {
        return guiInstance;
    }

    public boolean isReady() {
        return isReady;
    }
}