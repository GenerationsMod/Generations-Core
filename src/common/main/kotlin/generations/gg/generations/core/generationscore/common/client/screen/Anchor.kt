package generations.gg.generations.core.generationscore.common.client.screen

import com.mojang.blaze3d.vertex.PoseStack

enum class Anchor(private val vertical: Float, private val horizontal: Float) {
    TOP_LEFT(0f, 0f),
    TOP_CENTER(0f, 0.5f),
    TOP_RIGHT(0f, 1.0f),
    CENTER_LEFT(0.5f, 0f),
    CENTER_CENTER(0.5f, 0.5f),
    CENTER_RIGHT(0.5f, 1.0f),
    BOTTOM_LEFT(1.0f, 0f),
    BOTTOM_CENTER(1.0f, 0.5f),
    BOTTOM_RIGHT(1.0f, 1.0f);

    fun process(
        poseStack: PoseStack,
        x: Float,
        y: Float,
        width: Float,
        height: Float,
        widthMultiplier: Float,
        heightMultiplier: Float
    ) {
        val anchorX = horizontal * width
        val anchorY = vertical * height

        poseStack.last().pose()
            .translate(x, y, 0f)
            .scale(widthMultiplier, heightMultiplier, 1f)
            .translate(-anchorX, -anchorY, 0f)
    }

    fun process(poseStack: PoseStack, x: Int, y: Int, width: Int, height: Int) {
        val anchorX = horizontal * width
        val anchorY = vertical * height

        poseStack.translate(x - anchorX, y - anchorY, 0f)
    }

    fun getX(x: Float): Float {
        return horizontal * x
    }

    fun getY(y: Float): Float {
        return vertical * y
    }
}


