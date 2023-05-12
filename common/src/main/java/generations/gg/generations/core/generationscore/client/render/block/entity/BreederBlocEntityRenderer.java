package generations.gg.generations.core.generationscore.client.render.block.entity;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.client.render.rarecandy.ModelRegistry;
import generations.gg.generations.core.generationscore.world.level.block.entities.BreederBlockEntity;
import gg.generations.rarecandy.rendering.ObjectInstance;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;

public class BreederBlocEntityRenderer extends GeneralUseBlockEntityRenderer<BreederBlockEntity> {
    public static final ResourceLocation AUTO_FEEDER = GenerationsCore.id("models/block/utility_blocks/breeder/auto_feeder.pk");
    public static final ResourceLocation AUTO_FEEDER_FILL = GenerationsCore.id("models/block/utility_blocks/breeder/auto_feeder_fill.pk");
    public static final ResourceLocation BREEDER = GenerationsCore.id("models/block/utility_blocks/breeder/breeder.pk");
    public static final ResourceLocation EGG = GenerationsCore.id("models/block/utility_blocks/breeder/egg.pk");

    public BreederBlocEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        super(ctx);
    }

    @Override
    protected void renderModels(PoseStack stack, BreederBlockEntity blockEntity) {
        renderResourceLocation(stack, AUTO_FEEDER);
        ModelRegistry.get(AUTO_FEEDER_FILL, "fullbright").render(new ObjectInstance(new Matrix4f(), stack.last().pose(), null), RenderSystem.getProjectionMatrix());
        renderResourceLocation(stack, BREEDER);
        renderResourceLocation(stack, EGG);
    }
}
