package generations.gg.generations.core.generationscore.common.client.screen

import net.minecraft.client.gui.Gui
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.resources.ResourceLocation

/*
   Nine Slice code inspired by implmentation seen in the Moasic Module of LibrarianLib
*/
class NineSlice(
    u: Int,
    v: Int,
    width: Int,
    height: Int,
    sheet: ResourceLocation,
    scale: Int = 1024,
    private val left: Int,
    private val right: Int,
    private val top: Int,
    private val bottom: Int): SubTexture(u, v, width, height, sheet, scale) {
//    private var left: Int
//    private var right: Int
//    private var top: Int
//    private var bottom: Int

//    constructor(
//        u: Int,
//        v: Int,
//        width: Int,
//        height: Int,
//        sheet: ResourceLocation,
//        scale: Int = 1024,
//        left: Int,
//        right: Int,
//        top: Int,
//        bottom: Int
//    ) : super(u, v, width, height, sheet, scale) {
//        this.left = left
//        this.right = right
//        this.top = top
//        this.bottom = bottom
//    }

    override fun renderStretched(
        graphics: GuiGraphics,
        x: Int,
        y: Int,
        widthMultiplier: Float,
        heightMultiplier: Float
    ) {
        renderComplex(graphics, x, y, (this.width * widthMultiplier).toInt(), (this.height * heightMultiplier).toInt())
    }

    override fun renderAnchoredStretched(
        graphics: GuiGraphics,
        x: Int,
        y: Int,
        widthMultiplier: Float,
        heightMultiplier: Float,
        anchor: Anchor
    ) {
        graphics.pose().pushPose()
        anchor.process(
            graphics.pose(),
            x.toFloat(),
            y.toFloat(),
            width.toFloat(),
            height.toFloat(),
            widthMultiplier,
            heightMultiplier
        )
        renderComplex(graphics, 0, 0, width, height)
        graphics.pose().popPose()
    }

    override fun render(graphics: GuiGraphics, x: Int, y: Int, width: Int, height: Int) {
        renderComplex(graphics, x, y, width, height)
    }

    override fun renderAnchored(graphics: GuiGraphics, x: Int, y: Int, anchor: Anchor) {
        graphics.pose().pushPose()
        anchor.process(graphics.pose(), x, y, width, height)
        ScreenUtils.drawTexture(
            graphics, sheet, 0, 0, width, height,
            u.toFloat(),
            v.toFloat(),
            this.width,
            this.height,
            this.sheetScale,
            this.sheetScale
        )
        graphics.pose().popPose()
    }

    override fun renderInner(graphics: GuiGraphics, x: Int, y: Int, texWidth: Int, texHeight: Int) {
        renderComplex(graphics, x, y, texWidth, texHeight)
    }

    private fun renderComplex(graphics: GuiGraphics, x: Int, y: Int, width: Int, height: Int) {
        //TODO: Replace with vanilla nineslice
        section(
            graphics, NineSliceEquations.FIRST, NineSliceEquations.FIRST, x, y, width, height, u, v,
            this.width,
            this.height, left, right, top, bottom, sheetScale
        )
        section(
            graphics, NineSliceEquations.FIRST, NineSliceEquations.SECOND, x, y, width, height, u, v,
            this.width,
            this.height, left, right, top, bottom, sheetScale
        )
        section(
            graphics, NineSliceEquations.FIRST, NineSliceEquations.THIRD, x, y, width, height, u, v,
            this.width,
            this.height, left, right, top, bottom, sheetScale
        )
        section(
            graphics, NineSliceEquations.SECOND, NineSliceEquations.FIRST, x, y, width, height, u, v,
            this.width,
            this.height, left, right, top, bottom, sheetScale
        )
        section(
            graphics, NineSliceEquations.SECOND, NineSliceEquations.SECOND, x, y, width, height, u, v,
            this.width,
            this.height, left, right, top, bottom, sheetScale
        )
        section(
            graphics, NineSliceEquations.SECOND, NineSliceEquations.THIRD, x, y, width, height, u, v,
            this.width,
            this.height, left, right, top, bottom, sheetScale
        )
        section(
            graphics, NineSliceEquations.THIRD, NineSliceEquations.FIRST, x, y, width, height, u, v,
            this.width,
            this.height, left, right, top, bottom, sheetScale
        )
        section(
            graphics, NineSliceEquations.THIRD, NineSliceEquations.SECOND, x, y, width, height, u, v,
            this.width,
            this.height, left, right, top, bottom, sheetScale
        )
        section(
            graphics, NineSliceEquations.THIRD, NineSliceEquations.THIRD, x, y, width, height, u, v,
            this.width,
            this.height, left, right, top, bottom, sheetScale
        )
    }

    protected fun section(
        graphics: GuiGraphics,
        horizontal: NineSliceEquation,
        vertical: NineSliceEquation,
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        u: Int,
        v: Int,
        uWidth: Int,
        vHeight: Int,
        left: Int,
        right: Int,
        top: Int,
        bottom: Int,
        sheetSize: Int
    ) {
        ScreenUtils.drawTexture(
            graphics, sheet,
            horizontal.getPos(x, width, left, right),
            vertical.getPos(y, height, top, bottom),
            horizontal.getSize(width, left, right),
            vertical.getSize(height, top, bottom),
            horizontal.getPos(u, uWidth, left, right).toFloat(),
            vertical.getPos(v, vHeight, top, bottom).toFloat(),
            horizontal.getSize(uWidth, left, right),
            vertical.getSize(vHeight, top, bottom),
            sheetSize, sheetSize
        )
    }


    private enum class NineSliceEquations : NineSliceEquation {
        FIRST {
            override fun getPos(start: Int, length: Int, firstSlice: Int, secondSlice: Int): Int {
                return start
            }

            override fun getSize(length: Int, firstSlice: Int, secondSlice: Int): Int {
                return firstSlice
            }
        },
        SECOND {
            override fun getPos(start: Int, length: Int, firstSlice: Int, secondSlice: Int): Int {
                return start + firstSlice
            }

            override fun getSize(length: Int, firstSlice: Int, secondSlice: Int): Int {
                return length - (firstSlice + secondSlice)
            }

            override val isRepeated: Boolean
                get() = true
        },
        THIRD {
            override fun getPos(start: Int, length: Int, firstSlice: Int, secondSlice: Int): Int {
                return start + length - secondSlice
            }

            override fun getSize(length: Int, firstSlice: Int, secondSlice: Int): Int {
                return secondSlice
            }
        }
    }

    interface NineSliceEquation {
        fun getPos(start: Int, length: Int, firstSlice: Int, secondSlice: Int): Int
        fun getSize(length: Int, firstSlice: Int, secondSlice: Int): Int
        val isRepeated: Boolean
            get() = false
    }
}