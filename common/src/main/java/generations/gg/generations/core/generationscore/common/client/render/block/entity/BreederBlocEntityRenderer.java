package generations.gg.generations.core.generationscore.common.client.render.block.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.BlockObjectInstance;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.BlockObjectInstance;
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.BlockObjectInstance;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.BreederBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class BreederBlocEntityRenderer extends GeneralUseBlockEntityRenderer<BreederBlockEntity> {
    public static final ResourceLocation AUTO_FEEDER = GenerationsCore.id("models/block/utility_blocks/breeder/auto_feeder.pk");
    public static final ResourceLocation AUTO_FEEDER_FILL = GenerationsCore.id("models/block/utility_blocks/breeder/auto_feeder_fill.pk");
    public static final ResourceLocation EGG = GenerationsCore.id("models/block/utility_blocks/breeder/egg.pk");

    public BreederBlocEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    protected void renderModels(PoseStack stack, BreederBlockEntity blockEntity, int packedLight) {
        super.renderModels(stack, blockEntity, packedLight);
        ((BlockObjectInstance) blockEntity.objectInstance[1]).setLight(packedLight);
        renderResourceLocation(AUTO_FEEDER_FILL, stack, blockEntity.objectInstance[1]);
        ((BlockObjectInstance) blockEntity.objectInstance[2]).setLight(packedLight);
        renderResourceLocation(AUTO_FEEDER, stack, blockEntity.objectInstance[2]);
        ((BlockObjectInstance) blockEntity.objectInstance[3]).setLight(packedLight);
        renderResourceLocation(EGG, stack, blockEntity.objectInstance[3]);
    }

    @Override
    protected int instanceAmount() {
        return 4;
    }
}
