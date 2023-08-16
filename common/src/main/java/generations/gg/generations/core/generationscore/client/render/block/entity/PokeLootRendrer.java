package generations.gg.generations.core.generationscore.client.render.block.entity;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import generations.gg.generations.core.generationscore.client.render.rarecandy.BlockObjectInstance;
import generations.gg.generations.core.generationscore.client.render.rarecandy.ModelRegistry;
import generations.gg.generations.core.generationscore.world.level.block.entities.BallLootBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericModelBlock;
import gg.generations.rarecandy.rendering.ObjectInstance;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import org.joml.Matrix4f;

public class PokeLootRendrer implements BlockEntityRenderer<BallLootBlockEntity> {

    public PokeLootRendrer(BlockEntityRendererProvider.Context ctx) {}

    @Override
    public void render(BallLootBlockEntity blockEntity, float partialTick, PoseStack stack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        if (!(blockEntity.isVisible() && blockEntity.getBlockState().getBlock() instanceof GenericModelBlock<?> block && block.canRender(blockEntity.getLevel(), blockEntity.getBlockPos(), blockEntity.getBlockState()))) return;
        stack.pushPose();
        if (blockEntity.objectInstance == null) {
            blockEntity.objectInstance = new ObjectInstance[] { new BlockObjectInstance(new Matrix4f(), new Matrix4f(), "") };
        }

        var primeInstance = blockEntity.objectInstance[0];

        if (!primeInstance.materialId().equals(blockEntity.getVariant())) {
            primeInstance.setVariant(blockEntity.getVariant());
        }


        ((BlockObjectInstance) primeInstance).setLight(packedLight);


        ModelRegistry.prepForBER(stack, blockEntity);
        stack.translate(0, 0.25f, 0);

        var model = ModelRegistry.get(blockEntity, "block");
        var scale = model.renderObject.scale * 0.5f;
        stack.scale(scale, scale, scale);
        primeInstance.viewMatrix().set(stack.last().pose());

        model.render(primeInstance, RenderSystem.getProjectionMatrix());
        stack.popPose();
    }
}
