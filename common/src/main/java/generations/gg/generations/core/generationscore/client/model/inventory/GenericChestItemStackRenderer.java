package generations.gg.generations.core.generationscore.client.model.inventory;

import com.mojang.blaze3d.vertex.PoseStack;
import generations.gg.generations.core.generationscore.world.level.block.entities.generic.GenericChestBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public class GenericChestItemStackRenderer extends BlockEntityWithoutLevelRenderer {

  private final Supplier<GenericChestBlockEntity> te;
  private final BlockEntityRenderDispatcher blockEntityRenderDispatcher;

  public GenericChestItemStackRenderer(BlockEntityRenderDispatcher renderDispatcher, EntityModelSet modelSet, Supplier<GenericChestBlockEntity> te) {
    super(renderDispatcher, modelSet);

    this.te = te;
    this.blockEntityRenderDispatcher = renderDispatcher;
  }

  @Override
  public void renderByItem(ItemStack itemStackIn, ItemDisplayContext transformType, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
    this.blockEntityRenderDispatcher.renderItem(this.te.get(), matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
  }
}