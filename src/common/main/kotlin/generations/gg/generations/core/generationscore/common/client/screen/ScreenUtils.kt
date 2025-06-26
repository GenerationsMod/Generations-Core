package generations.gg.generations.core.generationscore.common.client.screen

import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.*
import net.minecraft.ChatFormatting
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Font
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.components.EditBox
import net.minecraft.client.gui.components.Tooltip
import net.minecraft.client.renderer.GameRenderer
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.FormattedText
import net.minecraft.network.chat.MutableComponent
import net.minecraft.network.chat.Style
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.FormattedCharSequence
import org.joml.Matrix4f
import org.joml.Vector2f
import org.joml.Vector3f
import org.lwjgl.opengl.GL11
import java.util.*
import java.util.function.Consumer
import java.util.function.Predicate
import java.util.regex.Pattern
import kotlin.math.max

object ScreenUtils {
    /**
     * Matches vanilla color codes, hex values and '*'.
     * strings like '*', '&4', '&r', '&f', '&#ff0012', '&#D7D7D7'...
     */
    private val COLOR_PATTERN: Pattern = Pattern.compile("((?i)&[0-9A-FK-OR])|((?i)&#[0-9A-F]{6})|(\\*)")

    /**
     * Utility method because Forge sucks with parameter names. Also, drawTexture makes more sense than blit.
     */
    fun drawTexture(
        matrices: GuiGraphics,
        location: ResourceLocation,
        x: Int,
        y: Int,
        u: Int,
        v: Int,
        width: Int,
        height: Int,
        textureWidth: Int,
        textureHeight: Int
    ) {
        matrices.blit(location, x, y, u.toFloat(), v.toFloat(), width, height, textureWidth, textureHeight)
    }

    /**
     * Utility method because Forge sucks with parameter names. Also, drawTexture makes more sense than blit.
     */
    fun drawTexture(
        graphics: GuiGraphics,
        location: ResourceLocation,
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        uOffset: Float,
        vOffset: Float,
        uWidth: Int,
        vHeight: Int,
        textureWidth: Int,
        textureHeight: Int
    ) {
        graphics.blit(location, x, y, width, height, uOffset, vOffset, uWidth, vHeight, textureWidth, textureHeight)
    }

    fun drawAnchoredTexture(
        graphics: GuiGraphics,
        location: ResourceLocation,
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        u: Int,
        v: Int,
        uWidth: Int,
        vHeight: Int,
        textureWidth: Int,
        textureHeight: Int,
        anchor: Anchor
    ) {
        graphics.pose().pushPose()
        anchor.process(graphics.pose(), x, y, width, height)
        drawTexture(
            graphics,
            location,
            0,
            0,
            width,
            height,
            u.toFloat(),
            v.toFloat(),
            uWidth,
            vHeight,
            textureWidth,
            textureHeight
        )
        graphics.pose().popPose()
    }

    fun drawAnchoredStretchedTexture(
        stack: GuiGraphics,
        location: ResourceLocation,
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        u: Int,
        v: Int,
        uWidth: Int,
        vHeight: Int,
        textureWidth: Int,
        textureHeight: Int,
        widthMultiplier: Float,
        heightMultiplier: Float,
        anchor: Anchor
    ) {
        stack.pose().pushPose()
        anchor.process(
            stack.pose(),
            x.toFloat(),
            y.toFloat(),
            width.toFloat(),
            height.toFloat(),
            widthMultiplier,
            heightMultiplier
        )
        drawTexture(
            stack,
            location,
            0,
            0,
            width,
            height,
            u.toFloat(),
            v.toFloat(),
            uWidth,
            vHeight,
            textureWidth,
            textureHeight
        )
        stack.pose().popPose()
    }

    /**
     * [PoseStack] black magic to render text at a different scale
     */
    fun drawScaledText(
        matrices: GuiGraphics,
        text: Any,
        x: Float,
        y: Float,
        scale: Float,
        color: Int,
        shadow: Boolean
    ) {
        matrices.pose().pushPose()
        matrices.pose().scale(scale, scale, 0f)
        matrices.pose().translate(x / scale, y / scale, 0f)
        drawText(matrices, text, color, shadow)
        matrices.pose().popPose()
    }

    /**
     * [PoseStack] black magic to render text at a different scale but also with centering
     */
    fun drawScaledCenteredText(
        matrices: GuiGraphics,
        text: Any,
        x: Int,
        y: Int,
        scale: Float,
        color: Int,
        shadow: Boolean
    ) {
        drawScaledText(matrices, text, x - (textWidth(text) / 2f) * scale, y.toFloat(), scale, color, shadow)
    }

    private fun textWidth(text: Any): Int {
        val font = Minecraft.getInstance().font

        return when (text) {
            is String -> font.width(text)
            is Component -> font.width(text)
            is FormattedCharSequence -> font.width(text)
            else -> throw RuntimeException("Invalid Text Object $text")
        }
    }

    private fun drawText(matrices: GuiGraphics, text: Any, color: Int, shadow: Boolean) {
        val font = Minecraft.getInstance().font

        when (text) {
            is String -> matrices.drawString(font, text, 0, 0, color, shadow)
            is Component -> matrices.drawString(font, text, 0, 0, color, shadow)
            is FormattedCharSequence -> matrices.drawString(font, text, 0, 0, color, shadow)
        }
    }

    private fun drawText(matrices: GuiGraphics, text: Any, x: Float, y: Float, color: Int, shadow: Boolean) {
        val font = Minecraft.getInstance().font
        matrices.pose().pushPose()
        matrices.pose().translate(x, y, 0f)
        when (text) {
            is String -> matrices.drawString(font, text, 0, 0, color, shadow)
            is Component -> matrices.drawString(font, text, 0, 0, color, shadow)
            is FormattedCharSequence -> matrices.drawString(font, text, 0, 0, color, shadow)
        }
        matrices.pose().popPose()
    }


    fun drawText(matrices: GuiGraphics, text: Any, x: Float, y: Float, color: Int, pos: Position) {
        when (pos) {
            Position.LEFT -> drawText(matrices, text, x, y, color, false)
            Position.MIDDLE -> {
                val length = getTextLength(text) / 2
                drawText(matrices, text, x - length, y, color, false)
            }

            Position.RIGHT -> {
                val length = getTextLength(text)
                drawText(matrices, text, x - length, y, color, false)
            }
        }
    }

    @JvmOverloads
    fun drawTextWithHeight(
        matrices: GuiGraphics,
        text: Any,
        x: Float,
        y: Float,
        height: Float,
        color: Int,
        pos: Position = Position.LEFT
    ) {
        when (pos) {
            Position.LEFT -> drawScaledText(matrices, text, x, y, height / 9f, color, false)
            Position.MIDDLE -> {
                val length = getScaledTextLength(text, height.toInt()) / 2
                drawScaledText(matrices, text, x - length, y, height / 9f, color, false)
            }

            Position.RIGHT -> {
                val length = getScaledTextLength(text, height.toInt())
                drawScaledText(matrices, text, x - length, y, height / 9f, color, false)
            }
        }
    }

    fun getScaledTextLength(text: Any?, height: Int): Int {
        return if (text is String) (Minecraft.getInstance().font.width(text) * (height / 9f)).toInt()
        else if (text is FormattedText) (Minecraft.getInstance().font.width(
            text
        ) * (height / 9f)).toInt()
        else if (text is FormattedCharSequence) (Minecraft.getInstance().font.width(
            text
        ) * (height / 9f)).toInt()
        else 0
    }

    fun getTextLength(text: Any?): Int {
        return when (text) {
            is String -> Minecraft.getInstance().font.width(text)
            is FormattedText -> Minecraft.getInstance().font.width(text)
            is FormattedCharSequence -> Minecraft.getInstance().font.width(text)
            else -> 0
        }
    }


    fun drawTextWithHeightWithLengthScaling(
        matrices: GuiGraphics,
        text: Any,
        x: Float,
        y: Float,
        optimalHeight: Float,
        length: Float,
        color: Int,
        pos: Position
    ) {
        var xModified = x
        var height = optimalHeight
        val actualLength = getScaledTextLength(text, optimalHeight.toInt())

        if (actualLength > length) {
            height *= (length / actualLength)

            xModified += (optimalHeight / 2 - height / 2)
        }

        drawTextWithHeight(matrices, text, xModified, y, height, color, pos)
    }

    @JvmStatic
    fun drawCenteredString(
        poseStack: GuiGraphics,
        font: Font,
        text: Component,
        x: Int,
        y: Int,
        color: Int,
        shadow: Boolean
    ) {
        poseStack.drawString(font, text, (x - font.width(text) / 2), y, color, shadow)
    }

    @JvmStatic
    fun drawCenteredString(
        poseStack: GuiGraphics,
        font: Font,
        text: String,
        x: Int,
        y: Int,
        color: Int,
        shadow: Boolean
    ) {
        poseStack.drawString(font, text, (x - font.width(text) / 2), y, color, shadow)
    }

    fun drawRect(poseStack: GuiGraphics, x: Float, y: Float, width: Float, height: Float, color: Int) {
        if (width == 0f || height == 0f) {
            return
        }

        drawRect(poseStack.pose().last().pose(), 0, x, y, x + width, y + height, color)
    }

    fun drawRect(mat: Matrix4f, zLevel: Int, left: Float, top: Float, right: Float, bottom: Float, startColor: Int) {
        val startAlpha = (startColor shr 24 and 0xFF).toFloat() / 255.0f
        val startRed = (startColor shr 16 and 0xFF).toFloat() / 255.0f
        val startGreen = (startColor shr 8 and 0xFF).toFloat() / 255.0f
        val startBlue = (startColor and 0xFF).toFloat() / 255.0f

        RenderSystem.setShader { GameRenderer.getPositionColorShader() }
        val tessellator = Tesselator.getInstance()
        val buffer = tessellator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR)
        buffer.addVertex(mat, right, top, zLevel.toFloat()).setColor(startRed, startGreen, startBlue, startAlpha)
        buffer.addVertex(mat, left, top, zLevel.toFloat()).setColor(startRed, startGreen, startBlue, startAlpha)
        buffer.addVertex(mat, left, bottom, zLevel.toFloat()).setColor(startRed, startGreen, startBlue, startAlpha)
        buffer.addVertex(mat, right, bottom, zLevel.toFloat()).setColor(startRed, startGreen, startBlue, startAlpha)
        BufferUploader.drawWithShader(buffer.buildOrThrow())
    }

    // FIXME: this code breaks if you raise the line above around half way on the Y axis
    fun drawLine(mat: Matrix4f, zLevel: Int, start: Vector2f, end: Vector2f, startColor: Int) {
        val startAlpha = (startColor shr 24 and 0xFF).toFloat() / 255.0f
        val startRed = (startColor shr 16 and 0xFF).toFloat() / 255.0f
        val startGreen = (startColor shr 8 and 0xFF).toFloat() / 255.0f
        val startBlue = (startColor and 0xFF).toFloat() / 255.0f

        RenderSystem.setShader { GameRenderer.getRendertypeLinesShader() }
        RenderSystem.lineWidth(2.0f)
        val tessellator = Tesselator.getInstance()
        val buffer = tessellator.begin(VertexFormat.Mode.LINES, DefaultVertexFormat.POSITION_COLOR_NORMAL)
        val direction = Vector2f()
        end.sub(start, direction).normalize(direction)
        buffer.addVertex(mat, start.x, start.y, zLevel.toFloat()).setColor(startRed, startGreen, startBlue, startAlpha)
            .setNormal(direction.x, direction.y, 0f)
        buffer.addVertex(mat, end.x, end.y, zLevel.toFloat()).setColor(startRed, startGreen, startBlue, startAlpha)
            .setNormal(direction.x, direction.y, 0f)
        BufferUploader.drawWithShader(buffer.buildOrThrow())
    }

    /**
     * Determines if the given coordinates are within the specified rectangle.
     *
     * @param x      The x-coordinate to check.
     * @param y      The y-coordinate to check.
     * @param xPos   The x-coordinate of the top-left corner of the rectangle.
     * @param yPos   The y-coordinate of the top-left corner of the rectangle.
     * @param width  The width of the rectangle.
     * @param height The height of the rectangle.
     * @return `true` if the coordinates are within the rectangle, `false` otherwise.
     */
    fun contains(x: Double, y: Double, xPos: Double, yPos: Double, width: Double, height: Double): Boolean {
        return x >= xPos && x <= xPos + width && y >= yPos && y <= yPos + height
    }

    /**
     * Determines if the given coordinates are within the specified rectangle.
     *
     * @param x      The x-coordinate to check.
     * @param y      The y-coordinate to check.
     * @param xPos   The x-coordinate of the top-left corner of the rectangle.
     * @param yPos   The y-coordinate of the top-left corner of the rectangle.
     * @param width  The width of the rectangle.
     * @param height The height of the rectangle.
     * @return `true` if the coordinates are within the rectangle, `false` otherwise.
     */
    fun contains(x: Int, y: Int, xPos: Int, yPos: Int, width: Int, height: Int): Boolean {
        return x >= xPos && x <= xPos + width && y >= yPos && y <= yPos + height
    }

    fun transform(
        matrixStack: PoseStack,
        x: Float,
        y: Float,
        width: Float,
        height: Float,
        widthScale: Float,
        heightScale: Float,
        anchor: Anchor,
        consumer: Consumer<PoseStack?>
    ) {
        val pivotX = anchor.getX(width)
        val pivotY = anchor.getY(height)
        matrixStack.pushPose()
        matrixStack.last().pose()
            .translate(pivotX, pivotY, 0f)
            .scale(widthScale, heightScale, 1f)
            .translate(-pivotX, -pivotY, 0f)
            .translate(x, y, 0f)
        consumer.accept(matrixStack)
        matrixStack.popPose()
    }

    fun createTextField(
        x: Int, y: Int, width: Int, height: Int, maxTextLength: Int,
        initialText: String,
        responder: Consumer<String>,
        tooltip: Tooltip?
    ): EditBox {
        return createTextField(x, y, width, height, maxTextLength, true, initialText, {true }, responder, tooltip)
    }

    fun createTextField(
        x: Int, y: Int, width: Int, height: Int, maxTextLength: Int,
        initialText: String,
        responder: Consumer<String>
    ): EditBox {
        return createTextField(x, y, width, height, maxTextLength, true, initialText, { true }, responder, null)
    }

    fun createTextField(
        x: Int, y: Int, width: Int, height: Int, maxTextLength: Int,
        initialText: String,
        filter: Predicate<String>, responder: Consumer<String>
    ): EditBox {
        return createTextField(x, y, width, height, maxTextLength, true, initialText, filter, responder, null)
    }

    fun createTextField(
        x: Int, y: Int, width: Int, height: Int, maxTextLength: Int,
        initialText: String,
        filter: Predicate<String>, responder: Consumer<String>,
        tooltip: Tooltip?
    ): EditBox {
        return createTextField(x, y, width, height, maxTextLength, true, initialText, filter, responder, tooltip)
    }

    fun createTextField(
        x: Int, y: Int, width: Int, height: Int, maxTextLength: Int, bordered: Boolean,
        initialText: String,
        filter: Predicate<String> = Predicate { true }, responder: Consumer<String>,
        tooltip: Tooltip?
    ): EditBox {
        val textField = EditBox(Minecraft.getInstance().font, x, y, width, height, Component.empty())

        textField.value = initialText
        textField.isBordered = bordered
        textField.setFilter(filter
            ?: Predicate { obj: String? -> Objects.nonNull(obj) })
        textField.setResponder(responder)
        textField.setMaxLength(maxTextLength)
        textField.tooltip = tooltip

        return textField
    }

    /**
     * Formats the input string into a MutableComponent applying styles
     * based on the [patterns][.COLOR_PATTERN] it finds in the text.
     *
     * @param input example: "This &l&ais a *test* &#a373f0input"
     */
    fun formatStringWithColorsToComponent(input: String): MutableComponent {
        val matcher = COLOR_PATTERN.matcher(input)
        val component = Component.empty()
        var currentStyle = Style.EMPTY.applyFormat(ChatFormatting.BLACK)
        var prevIndex = 0
        var hasMatchedAsterisk = false
        while (matcher.find()) {
            val match = matcher.group(0)
            val start = matcher.start()
            val end = matcher.end()
            if (prevIndex != start) {
                component.append(Component.literal(input.substring(prevIndex, start)).withStyle(currentStyle))
            }
            prevIndex = end

            if (match == "*") {
                if (hasMatchedAsterisk) {
                    currentStyle = currentStyle
                        .applyFormat(ChatFormatting.RESET)
                        .applyFormat(ChatFormatting.BLACK)
                    hasMatchedAsterisk = false
                } else {
                    currentStyle = currentStyle
                        .applyFormat(ChatFormatting.RESET)
                        .applyFormat(ChatFormatting.RED)
                    hasMatchedAsterisk = true
                }
            } else if (match.startsWith("&#")) {
                currentStyle = getStyleFromHex(
                    currentStyle, match
                )
            } else {
                currentStyle = getStyleFromColorCode(
                    currentStyle, match
                )
            }
        }
        component.append(Component.literal(input.substring(prevIndex)).withStyle(currentStyle))

        return component
    }

    fun formatEditableStringWithColorsToComponent(input: String, cursor: Int): MutableComponent {
        val firstHalf =
            if (cursor == 0) Component.empty() else formatStringWithColorsToComponent(input.substring(0, cursor - 1))
        val lastHalf = formatStringWithColorsToComponent(input.substring(cursor + 1))
        val cursorChar = formatStringWithColorsToComponent(
            input[cursor].toString()
        )
            .withStyle(ChatFormatting.UNDERLINE)

        return firstHalf.append(cursorChar).append(lastHalf)
    }

    /**
     * @param hexString examples: '&#FF0000', '&#D7D7D7'
     */
    fun getStyleFromHex(currentStyle: Style, hexString: String): Style {
        return currentStyle.withColor(hexString.substring(2).toInt(16))
    }

    /**
     * @param colorCode examples: '&4', '&l', '&k', '&7'
     */
    fun getStyleFromColorCode(currentStyle: Style, colorCode: String): Style {
        return try {
            currentStyle.applyFormat(ChatFormatting.getByCode(
                        colorCode[1]
                    )!!
                )
        } catch (e: Exception) {
            currentStyle
        }
    }

    fun renderCutoff(stack: GuiGraphics, x: Int, y: Int, width: Int, height: Int, onRender: OnRender) {
        stack.pose().pushPose()
        stack.pose().translate(0.0f, 0.0f, 950.0f)
        RenderSystem.enableDepthTest()
        RenderSystem.colorMask(false, false, false, false)
        stack.fill(4680, 2260, -4680, -2260, -0x1000000)
        stack.pose().translate(0.0f, 0.0f, -950.0f)
        RenderSystem.depthFunc(GL11.GL_GEQUAL)
        stack.fill(x + width, y + height, x, y, -0x1000000)
        RenderSystem.depthFunc(GL11.GL_LEQUAL)
        RenderSystem.colorMask(true, true, true, true)
        onRender.render(stack)
        RenderSystem.depthFunc(GL11.GL_GEQUAL)
        stack.pose().translate(0.0f, 0.0f, -950.0f)
        RenderSystem.colorMask(false, false, false, false)
        stack.fill(4680, 2260, -4680, -2260, -0x1000000)
        RenderSystem.colorMask(true, true, true, true)
        RenderSystem.depthFunc(GL11.GL_LEQUAL)
        stack.pose().popPose()
    }

    fun enableScissor(x: Double, y: Double, endX: Double, endY: Double) {
        var width = endX - x
        var height = endY - y
        width = max(0.0, width)
        height = max(0.0, height)
        val d = Minecraft.getInstance().window.guiScale.toFloat()
        val ay = ((Minecraft.getInstance().window.guiScaledHeight - (y + height)) * d).toInt()
        RenderSystem.enableScissor((x * d).toInt(), ay, (width * d).toInt(), (height * d).toInt())
    }

    fun endScissor() {
        RenderSystem.disableScissor()
    }


    fun enableScissor(stack: GuiGraphics, x1: Float, y1: Float, x2: Float, y2: Float) {
        val matrix = stack.pose().last().pose()
        val coord = Vector3f(x1, y1, 0f)
        val end = Vector3f(x2, y2, 0f)
        matrix.transformPosition(coord)
        matrix.transformPosition(end)
        val x = coord.x().toDouble()
        val y = coord.y().toDouble()
        val endX = end.x().toDouble()
        val endY = end.y().toDouble()

        enableScissor(x, y, endX, endY)
    }


    interface OnRender {
        fun render(stack: GuiGraphics?)
    }

    enum class Position {
        LEFT, MIDDLE, RIGHT
    }
}
