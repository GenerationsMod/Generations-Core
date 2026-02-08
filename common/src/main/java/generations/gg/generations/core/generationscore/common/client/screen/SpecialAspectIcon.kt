package generations.gg.generations.core.generationscore.common.client.screen

import com.cobblemon.mod.common.api.gui.blitk
import generations.gg.generations.core.generationscore.common.GenerationsCore
import net.minecraft.client.gui.GuiGraphics

class SpecialAspectIcon(
    val x: Number,
    val y: Number,
    val centeredX: Boolean = false,
    val small: Boolean = false,
    val opacity: Float = 1F
) {
    companion object {
        private const val ICON_DIAMETER = 16
        private const val SCALE = 1F
        private val iconResource = GenerationsCore.id("textures/gui/special_aspect_icon.png")
    }

    fun render(context: GuiGraphics) {
        val diameter = if (small) (ICON_DIAMETER / 2) else ICON_DIAMETER
        val offsetX = if (centeredX) (((diameter / 2) * SCALE)) else 0F

        blitk(
            matrixStack = context.pose(),
            texture = iconResource,
            x = (x.toFloat() - offsetX) / SCALE,
            y = y.toFloat() / SCALE,
            height = diameter,
            width = diameter,
            uOffset = 0F,
            textureWidth = diameter,
            textureHeight = diameter,
            alpha = opacity,
            scale = SCALE
        )
    }
}