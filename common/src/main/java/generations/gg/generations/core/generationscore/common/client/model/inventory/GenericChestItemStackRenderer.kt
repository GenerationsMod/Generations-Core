package generations.gg.generations.core.generationscore.common.client.model.inventory

import com.cobblemon.mod.common.client.render.item.CobblemonBuiltinItemRenderer
import com.mojang.blaze3d.vertex.PoseStack
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.GenericChestBlockEntity
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack
import java.util.function.Supplier

@Environment(EnvType.CLIENT)
class GenericChestItemStackRenderer(private val te: Supplier<GenericChestBlockEntity>) : CobblemonBuiltinItemRenderer {
    override fun render(
        stack: ItemStack,
        mode: ItemDisplayContext,
        matrices: PoseStack,
        vertexConsumers: MultiBufferSource,
        light: Int,
        overlay: Int
    ) {
        Minecraft.getInstance().blockEntityRenderDispatcher.renderItem(
            te.get(),
            matrices,
            vertexConsumers,
            light,
            overlay
        )
    }
}