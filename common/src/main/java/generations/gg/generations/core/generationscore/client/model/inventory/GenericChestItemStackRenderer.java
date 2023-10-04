package generations.gg.generations.core.generationscore.client.model.inventory;

import com.cobblemon.mod.common.client.render.item.CobblemonBuiltinItemRenderer;
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
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public class GenericChestItemStackRenderer implements CobblemonBuiltinItemRenderer {

  private final Supplier<GenericChestBlockEntity> te;
  private final BlockEntityRenderDispatcher blockEntityRenderDispatcher;

  public GenericChestItemStackRenderer(BlockEntityRenderDispatcher renderDispatcher, EntityModelSet modelSet, Supplier<GenericChestBlockEntity> te) {
    this.te = te;
    this.blockEntityRenderDispatcher = renderDispatcher;
  }

  @Override
  public void render(@NotNull ItemStack itemStack, @NotNull ItemDisplayContext itemDisplayContext, @NotNull PoseStack poseStack, @NotNull MultiBufferSource multiBufferSource, int combinedLightIn, int combinedOverlayIn) {
    this.blockEntityRenderDispatcher.renderItem(this.te.get(), poseStack, multiBufferSource, combinedLightIn, combinedOverlayIn);
  }
}