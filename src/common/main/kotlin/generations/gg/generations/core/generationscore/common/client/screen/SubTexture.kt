package generations.gg.generations.core.generationscore.common.client.screen

import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite
import net.minecraft.resources.ResourceLocation

/**
 * Used for working with Sprite Sheets. Allows for "mapping" coordinates inside the image to something which can be drawn.
 */
open class SubTexture @JvmOverloads constructor(
    protected val u: Int,
    protected val v: Int,
    protected val width: Int,
    protected val height: Int,
    protected val sheet: ResourceLocation,
    protected val sheetScale: Int = 1024
) {
    open fun render(graphics: GuiGraphics, x: Int, y: Int, centered: Boolean) {
        renderInner(graphics, if (centered) x - this.width / 2 else x, y, this.sheetScale, this.sheetScale)
    }

    open fun renderStretched(graphics: GuiGraphics, x: Int, y: Int, widthMultiplier: Float, heightMultiplier: Float) {
        ScreenUtils.drawTexture(
            graphics,
            sheet, x, y, (this.width * widthMultiplier).toInt(), (this.height * heightMultiplier).toInt(),
            u.toFloat(),
            v.toFloat(), this.width, this.height, this.sheetScale, this.sheetScale
        )
    }

    protected open fun renderInner(graphics: GuiGraphics, x: Int, y: Int, texWidth: Int, texHeight: Int) {
        ScreenUtils.drawTexture(
            graphics,
            sheet, x, y, this.width, this.height,
            u.toFloat(),
            v.toFloat(), this.width, this.height, texWidth, texHeight
        )
    }

    open fun render(graphics: GuiGraphics, x: Int, y: Int, width: Int, height: Int) {
        ScreenUtils.drawTexture(
            graphics,
            sheet, x, y, width, height,
            u.toFloat(),
            v.toFloat(), this.width, this.height, this.sheetScale, this.sheetScale
        )
    }

    fun render(graphics: GuiGraphics, x: Int, y: Int, color: Int) {
        render(graphics, x, y, width, height, color)
    }

    fun render(graphics: GuiGraphics, x: Int, y: Int, width: Int, height: Int, color: Int) {
        val startAlpha = (color shr 24 and 0xFF).toFloat() / 255.0f
        val startRed = (color shr 16 and 0xFF).toFloat() / 255.0f
        val startGreen = (color shr 8 and 0xFF).toFloat() / 255.0f
        val startBlue = (color and 0xFF).toFloat() / 255.0f

        RenderSystem.setShaderColor(startRed, startGreen, startBlue, startAlpha)
        render(graphics, x, y, width, height)
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f)
    }

    open fun renderAnchored(graphics: GuiGraphics, x: Int, y: Int, anchor: Anchor) {
        ScreenUtils.drawAnchoredTexture(
            graphics,
            sheet, x, y,
            width,
            height, this.u, this.v, this.width, this.height, this.sheetScale, this.sheetScale, anchor
        )
    }

    open fun renderAnchoredStretched(
        graphics: GuiGraphics,
        x: Int,
        y: Int,
        widthMultiplier: Float,
        heightMultiplier: Float,
        anchor: Anchor
    ) {
        ScreenUtils.drawAnchoredStretchedTexture(
            graphics,
            sheet, x, y,
            width,
            height,
            this.u,
            this.v,
            this.width,
            this.height,
            this.sheetScale,
            this.sheetScale, widthMultiplier, heightMultiplier, anchor
        )
    }

    fun render(graphics: GuiGraphics, x: Int, y: Int) {
        this.render(graphics, x, y, false)
    }

    companion object {
        val BLANK: SubTexture = object : SubTexture(0, 0, 0, 0, MissingTextureAtlasSprite.getLocation()) {
            override fun render(graphics: GuiGraphics, x: Int, y: Int, centered: Boolean) {}
            override fun renderStretched(
                graphics: GuiGraphics,
                x: Int,
                y: Int,
                widthMultiplier: Float,
                heightMultiplier: Float
            ) {
            }

            override fun renderInner(graphics: GuiGraphics, x: Int, y: Int, texWidth: Int, texHeight: Int) {}
            override fun renderAnchored(graphics: GuiGraphics, x: Int, y: Int, anchor: Anchor) {}
            override fun renderAnchoredStretched(
                graphics: GuiGraphics,
                x: Int,
                y: Int,
                widthMultiplier: Float,
                heightMultiplier: Float,
                anchor: Anchor
            ) {
            }
        }
    }
}