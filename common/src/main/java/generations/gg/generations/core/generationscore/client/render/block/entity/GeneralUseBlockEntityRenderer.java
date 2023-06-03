package generations.gg.generations.core.generationscore.client.render.block.entity;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import generations.gg.generations.core.generationscore.client.model.ModelContextProviders;
import generations.gg.generations.core.generationscore.client.render.rarecandy.ModelRegistry;
import generations.gg.generations.core.generationscore.client.render.rarecandy.animation.FixedFrameAnimationInstance;
import generations.gg.generations.core.generationscore.world.level.block.entities.ModelProvidingBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericModelBlock;
import gg.generations.rarecandy.animation.AnimationInstance;
import gg.generations.rarecandy.rendering.ObjectInstance;
import gg.generations.rarecandy.storage.AnimatedObjectInstance;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

public class GeneralUseBlockEntityRenderer<T extends ModelProvidingBlockEntity> implements BlockEntityRenderer<T> {

    public GeneralUseBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
    }

    @Override
    public void render(@NotNull T blockEntity, float partialTick, PoseStack stack, @NotNull MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        if (!(blockEntity.getBlockState().getBlock() instanceof GenericModelBlock<?> block && block.canRender(blockEntity.getLevel(), blockEntity.getBlockPos(), blockEntity.getBlockState()))) return;
        stack.pushPose();
        ModelRegistry.prepForBER(stack, blockEntity);
        renderModels(stack, blockEntity);
        RenderSystem.disableDepthTest();
        stack.popPose();
    }

    protected void renderModels(PoseStack stack, T blockEntity) {
        if(blockEntity.isAnimated()) {
            renderModelFrameProvider(stack, blockEntity);
        } else {
            renderModelProvider(stack, blockEntity);
        }
    }

    protected void renderModelProvider(PoseStack stack, ModelProvidingBlockEntity blockEntity) {

        if (blockEntity.objectInstance == null) {
            blockEntity.objectInstance = new ObjectInstance(new Matrix4f(), stack.last().pose(), "");
        }

        if (blockEntity instanceof ModelContextProviders.VariantProvider provider && !blockEntity.objectInstance.materialId().equals(provider.getVariant())) {
            blockEntity.objectInstance.setVariant(provider.getVariant());
        }

        blockEntity.objectInstance.viewMatrix().set(stack.last().pose());

        ModelRegistry.get(blockEntity, "fullbright").render(blockEntity.objectInstance, RenderSystem.getProjectionMatrix());
    }

    protected void renderModelFrameProvider(PoseStack stack, ModelProvidingBlockEntity blockEntity) {
        var model = ModelRegistry.get(blockEntity, "animated");

        if (blockEntity.objectInstance == null) {
            blockEntity.objectInstance = new AnimatedObjectInstance(new Matrix4f(), stack.last().pose(), blockEntity instanceof ModelContextProviders.VariantProvider variantProvider ? variantProvider.getVariant() : null);
        }

        if (model.renderObject.isReady()) {
            blockEntity.objectInstance.link(model.renderObject);

            var animationInstance = ((AnimatedObjectInstance) blockEntity.objectInstance);
            var animation = animationInstance.getAnimationsIfAvailable().get(blockEntity.getAnimation());

            if (animation != null) {
                if (blockEntity instanceof ModelContextProviders.FrameProvider frameProvider) {
                    animationInstance.changeAnimation(new FixedFrameAnimationInstance(animation, frameProvider.getFrame()));
                } else {
                    animationInstance.changeAnimation(new AnimationInstance(animation));
                }
            }
        }

        blockEntity.objectInstance.viewMatrix().set(stack.last().pose());

        var instance = (AnimatedObjectInstance) blockEntity.objectInstance;


        if(blockEntity instanceof ModelContextProviders.FrameProvider frameProvider && instance.currentAnimation instanceof FixedFrameAnimationInstance fixedAnimation && fixedAnimation.getCurrentTime() != frameProvider.getFrame()) {
            fixedAnimation.setCurrentTime(frameProvider.getFrame());
        }

        model.render(instance, RenderSystem.getProjectionMatrix());
    }

    protected void renderResourceLocation(PoseStack stack, ResourceLocation location) {
        ModelRegistry.get(location, "fullbright").render(new ObjectInstance(new Matrix4f(), stack.last().pose(), null), RenderSystem.getProjectionMatrix());
    }

    @Override
    public boolean shouldRenderOffScreen(T blockEntity) {
        return true;
    }
}