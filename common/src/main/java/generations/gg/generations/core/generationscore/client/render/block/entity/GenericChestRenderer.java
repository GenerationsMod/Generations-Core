package generations.gg.generations.core.generationscore.client.render.block.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsBlocks;
import generations.gg.generations.core.generationscore.world.level.block.entities.generic.GenericChestBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericChestBlock;
import net.minecraft.Util;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.function.Consumer;

public class GenericChestRenderer implements BlockEntityRenderer<GenericChestBlockEntity> {
    private static final String BOTTOM = "bottom";
    private static final String LID = "lid";
    private static final String LOCK = "lock";
    private final ModelPart lid;
    private final ModelPart bottom;
    private final ModelPart lock;

    public GenericChestRenderer(BlockEntityRendererProvider.Context arg) {
        ModelPart modelpart = arg.bakeLayer(ModelLayers.CHEST);
        this.bottom = modelpart.getChild("bottom");
        this.lid = modelpart.getChild("lid");
        this.lock = modelpart.getChild("lock");
    }

    public void render(GenericChestBlockEntity blockEntity, float partialTick, PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        Level level = blockEntity.getLevel();
        boolean flag = level != null;
        BlockState blockstate = flag ? blockEntity.getBlockState() : GenerationsBlocks.POKEBALL_CHEST.get().defaultBlockState().setValue(ChestBlock.FACING, Direction.SOUTH);

        poseStack.pushPose();
        float f = blockstate.getValue(ChestBlock.FACING).toYRot();
        poseStack.translate(0.5, 0.5, 0.5);
        poseStack.mulPose(Axis.YP.rotationDegrees(-f));
        poseStack.translate(-0.5, -0.5, -0.5);

        float f1 = blockEntity.getOpenNess(partialTick);
        f1 = 1.0F - f1;
        f1 = 1.0F - f1 * f1 * f1;
        Material material = getMaterial(blockEntity);
        VertexConsumer vertexconsumer = material.buffer(bufferSource, RenderType::entityCutout);
        this.render(poseStack, vertexconsumer, this.lid, this.lock, this.bottom, f1, packedLight, packedOverlay);
        poseStack.popPose();

    }

    private static Material getMaterial(GenericChestBlockEntity blockEntity) {
        return map.get(blockEntity.getBlockState().getBlock() instanceof GenericChestBlock chest ? chest.getMaterialType() : "pokeball_chest");
    }

    private void render(
            PoseStack poseStack,
            VertexConsumer consumer,
            ModelPart lidPart,
            ModelPart lockPart,
            ModelPart bottomPart,
            float lidAngle,
            int packedLight,
            int packedOverlay
    ) {
        lidPart.xRot = -(lidAngle * ((float) (Math.PI / 2)));
        lockPart.xRot = lidPart.xRot;
        lidPart.render(poseStack, consumer, packedLight, packedOverlay);
        lockPart.render(poseStack, consumer, packedLight, packedOverlay);
        bottomPart.render(poseStack, consumer, packedLight, packedOverlay);
    }

    private static final HashMap<String, Material> map;

    public static Collection<Material> getGenericChestTextures() {
        return map.values();
    }

    static {
        map = Util.make(new HashMap<>(), map -> {
            Consumer<String> consumer = (id) -> map.put(id, new Material(Sheets.CHEST_SHEET, GenerationsCore.id("key/chest/" + id)));
            consumer.accept("pokeball_chest");
            consumer.accept("greatball_chest");
            consumer.accept("ultraball_chest");
            consumer.accept("masterball_chest");
        });
    }
}
