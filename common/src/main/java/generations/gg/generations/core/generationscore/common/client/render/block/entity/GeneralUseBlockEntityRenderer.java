package generations.gg.generations.core.generationscore.common.client.render.block.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders;
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.BlockAnimatedObjectInstance;
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.BlockLightValueProvider;
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.BlockObjectInstance;
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.ModelRegistry;
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.animation.FixedFrameAnimationInstance;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.ModelProvidingBlockEntity;
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericModelBlock;
import gg.generations.rarecandy.renderer.animation.AnimationInstance;
import gg.generations.rarecandy.renderer.rendering.ObjectInstance;
import gg.generations.rarecandy.renderer.storage.AnimatedObjectInstance;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class GeneralUseBlockEntityRenderer<T extends ModelProvidingBlockEntity> implements BlockEntityRenderer<T> {

    public GeneralUseBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
    }

    @Override
    public void render(@NotNull T blockEntity, float partialTick, PoseStack stack, @NotNull MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        if (!(blockEntity.getBlockState().getBlock() instanceof GenericModelBlock<?> block && block.canRender(blockEntity.getLevel(), blockEntity.getBlockPos(), blockEntity.getBlockState()))) return;
        stack.pushPose();
        ModelRegistry.prepForBER(stack, blockEntity);
        renderModels(stack, bufferSource, blockEntity, packedLight);
        stack.popPose();
    }

    protected void renderModels(PoseStack stack, MultiBufferSource buffersource, T blockEntity, int packedLight) {
        if(blockEntity.isAnimated()) renderModelFrameProvider(stack, buffersource, blockEntity, packedLight);
        else renderModelProvider(stack, buffersource, blockEntity, packedLight);
    }

    protected void renderModelProvider(PoseStack stack, MultiBufferSource buffersource, ModelProvidingBlockEntity blockEntity, int packedLight) {
        var model = ModelRegistry.get(blockEntity);

        if(model == null || model.renderObject == null) return;

        stack.scale(model.renderObject.scale, model.renderObject.scale, model.renderObject.scale);

        if (blockEntity.objectInstance == null) {
            int amount = instanceAmount();
            blockEntity.objectInstance = new ObjectInstance[amount];

            for (int i = 0; i < amount; i++)
                blockEntity.objectInstance[i] = blockEntity.generateInstance();
        }

        String variant = blockEntity.getVariant();

        for (var instance : blockEntity.objectInstance) {
            if (!Objects.equals(instance.materialId(), variant)) {
                instance.setVariant(variant);
            }

            instance.viewMatrix().set(stack.last().pose());
            ((BlockObjectInstance) instance).setLight(packedLight);
            if(blockEntity instanceof ModelContextProviders.TintProvider provider) ((BlockObjectInstance) instance).setTint(provider.getTint());
            model.render(instance, buffersource);
        }
    }

    protected int instanceAmount() {
        return 1;
    }

    protected void renderModelFrameProvider(PoseStack stack, MultiBufferSource buffersource, ModelProvidingBlockEntity blockEntity, int packedLight) {
        //TODO: Get this operational
        var model = ModelRegistry.get(blockEntity);

        if(model == null || model.renderObject == null) return;

        stack.scale(model.renderObject.scale, model.renderObject.scale, model.renderObject.scale);

        var amount = instanceAmount();
        blockEntity.objectInstance = new ObjectInstance[amount];

        for (int i = 0; i < amount; i++) {
            blockEntity.objectInstance[i] = blockEntity.generateInstance();
        }

        var primeInstance = blockEntity.objectInstance[0];

        if (model.renderObject.isReady()) {
            primeInstance.link(model.renderObject);

            var animationInstance = ((AnimatedObjectInstance) primeInstance);
            var animation = animationInstance.getAnimationsIfAvailable().get(blockEntity.getAnimation());

            if (animation != null) {
                if (blockEntity instanceof ModelContextProviders.FrameProvider frameProvider) {
                    animationInstance.changeAnimation(new FixedFrameAnimationInstance(animation, frameProvider.getFrame()));
                } else {
                    animationInstance.changeAnimation(new AnimationInstance(animation));
                }
            }
        }

        primeInstance.viewMatrix().set(stack.last().pose());
        ((BlockLightValueProvider) primeInstance).setLight(packedLight);
        if(blockEntity instanceof ModelContextProviders.TintProvider provider) ((BlockAnimatedObjectInstance) primeInstance).setTint(provider.getTint());

        var instance = (AnimatedObjectInstance) primeInstance;

        if(blockEntity instanceof ModelContextProviders.FrameProvider frameProvider && instance.currentAnimation instanceof FixedFrameAnimationInstance fixedAnimation && fixedAnimation.getCurrentTime() != frameProvider.getFrame()) {
            fixedAnimation.setCurrentTime(frameProvider.getFrame());
        }

        model.render(instance, buffersource);
    }

    protected void renderResourceLocation(MultiBufferSource source, ResourceLocation location, PoseStack stack, ObjectInstance objectInstance) {
        objectInstance.transformationMatrix().set(stack.last().pose());

        var model = ModelRegistry.get(location);
        if(model != null) model.render(objectInstance, source);
    }

    @Override
    public boolean shouldRenderOffScreen(T blockEntity) {
        return true;
    }
}