package generations.gg.generations.core.generationscore.client.render.block.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import generations.gg.generations.core.generationscore.client.model.ModelContextProviders;
import generations.gg.generations.core.generationscore.client.render.rarecandy.BlockLightValueProvider;
import generations.gg.generations.core.generationscore.client.render.rarecandy.BlockObjectInstance;
import generations.gg.generations.core.generationscore.client.render.rarecandy.ModelRegistry;
import generations.gg.generations.core.generationscore.client.render.rarecandy.GenerationsObjectInstance;
import generations.gg.generations.core.generationscore.client.render.rarecandy.animation.FixedFrameAnimationInstance;
import generations.gg.generations.core.generationscore.world.level.block.entities.ModelProvidingBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericModelBlock;
import gg.generations.rarecandy.legacy.animation.AnimationInstance;
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
        renderModels(stack, blockEntity, packedLight);
        stack.popPose();
    }

    protected void renderModels(PoseStack stack, T blockEntity, int packedLight) {
        if(blockEntity.isAnimated()) renderModelFrameProvider(stack, blockEntity, packedLight);
        else renderModelProvider(stack, blockEntity, packedLight);
    }

    protected void renderModelProvider(PoseStack stack, ModelProvidingBlockEntity blockEntity, int packedLight) {
        var model = ModelRegistry.get(blockEntity);
        if(!model.isReady()) return;
        stack.scale(model.renderObject.getScale(), model.renderObject.getScale(), model.renderObject.getScale());

        if (blockEntity.objectInstance == null) {
            int amount = instanceAmount();
            blockEntity.objectInstance = new BlockObjectInstance[amount];

            for (int i = 0; i < amount; i++)
                blockEntity.objectInstance[i] = blockEntity.generateInstance(model.renderObject);
        }

        String variant = blockEntity.getVariant();

        for (var instance : blockEntity.objectInstance) {
            if (!Objects.equals(instance.getVariant(), variant)) {
                instance.setVariant(variant);
            }

            instance.transformationMatrix().set(stack.last().pose());
            instance.setLight(packedLight);
            if(blockEntity instanceof ModelContextProviders.TintProvider provider) (instance).setTint(provider.getTint());
            model.render(instance);
        }
    }

    protected int instanceAmount() {
        return 1;
    }

    protected void renderModelFrameProvider(PoseStack stack, ModelProvidingBlockEntity blockEntity, int packedLight) {
        //TODO: Get this operational

        var model = ModelRegistry.get(blockEntity);

        if(!model.isReady()) return;

        stack.scale(model.renderObject.getScale(), model.renderObject.getScale(), model.renderObject.getScale());

        var amount = instanceAmount();
        blockEntity.objectInstance = new BlockObjectInstance[amount];

        for (int i = 0; i < amount; i++) {
            blockEntity.objectInstance[i] = blockEntity.generateInstance(model.renderObject);
        }

        var primeInstance = blockEntity.objectInstance[0];

        if (model.isReady()) {

            var animation = (primeInstance).getAnimationsIfAvailable().get(blockEntity.getAnimation());

            if (animation != null) {
                if (blockEntity instanceof ModelContextProviders.FrameProvider frameProvider) {
                    (primeInstance).changeAnimation(new FixedFrameAnimationInstance(animation, frameProvider.getFrame()));
                } else {
                    (primeInstance).changeAnimation(new AnimationInstance(animation));
                }
            }
        }

        primeInstance.transformationMatrix().set(stack.last().pose());
        ((BlockLightValueProvider) primeInstance).setLight(packedLight);
        if(blockEntity instanceof ModelContextProviders.TintProvider provider) primeInstance.setTint(provider.getTint());

        if(blockEntity instanceof ModelContextProviders.FrameProvider frameProvider && primeInstance.currentAnimation instanceof FixedFrameAnimationInstance fixedAnimation && fixedAnimation.getCurrentTime() != frameProvider.getFrame()) {
            fixedAnimation.setCurrentTime(frameProvider.getFrame());
        }

        model.render(primeInstance);
    }

    protected void renderResourceLocation(ResourceLocation location, PoseStack stack, GenerationsObjectInstance objectInstance) {
        objectInstance.transformationMatrix().set(stack.last().pose());
        ModelRegistry.get(location).render(objectInstance);
    }

    @Override
    public boolean shouldRenderOffScreen(T blockEntity) {
        return true;
    }
}