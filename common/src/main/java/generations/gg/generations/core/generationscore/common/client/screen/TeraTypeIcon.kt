package generations.gg.generations.core.generationscore.common.client.screen;

import com.cobblemon.mod.common.api.gui.blitk
import com.cobblemon.mod.common.api.types.tera.TeraType
import com.cobblemon.mod.common.api.types.tera.elemental.ElementalTypeTeraType
import com.cobblemon.mod.common.api.types.tera.gimmick.StellarTeraType
import generations.gg.generations.core.generationscore.common.GenerationsCore
import net.minecraft.client.gui.GuiGraphics

private val TeraType?.textureXMultiplier: Int
    get() = when(this) {
        is ElementalTypeTeraType -> this.type.textureXMultiplier
        is StellarTeraType -> 18
        else -> 19
    }

class TeraTypeIcon(
    val x: Number,
    val y: Number,
    val type: TeraType?,
    val centeredX: Boolean = false,
    val small: Boolean = false,
    val opacity: Float = 1F
) {
    companion object {
        private const val TYPE_ICON_DIAMETER = 36
        private const val SCALE = 0.5F

        private val typesResource = GenerationsCore.id("textures/gui/tera_types.png")
        private val smallTypesResource = GenerationsCore.id("textures/gui/tera_types_small.png")
    }

    fun render(context: GuiGraphics) {
        val diameter = if (small) (TYPE_ICON_DIAMETER / 2) else TYPE_ICON_DIAMETER
        val offsetX = if (centeredX) (((diameter / 2) * SCALE)) else 0F;

        blitk(
            matrixStack = context.pose(),
            texture = if (small) smallTypesResource else typesResource,
            x = (x.toFloat() - offsetX) / SCALE,
            y = y.toFloat() / SCALE,
            height = diameter,
            width = diameter,
            uOffset = diameter * type.textureXMultiplier.toFloat() + 0.1,
            textureWidth = diameter * 20,
            alpha = opacity,
            scale = SCALE
        )
    }
}