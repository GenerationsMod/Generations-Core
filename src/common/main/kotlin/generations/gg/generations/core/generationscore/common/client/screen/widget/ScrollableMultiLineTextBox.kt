package generations.gg.generations.core.generationscore.common.client.screen.widget

import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.gui.Font
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.components.AbstractWidget
import net.minecraft.client.gui.narration.NarrationElementOutput
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent
import net.minecraft.util.FormattedCharSequence
import net.minecraft.util.Mth
import java.util.*
import java.util.stream.Collectors
import kotlin.math.min

class ScrollableMultiLineTextBox(
    x: Int,
    y: Int,
    width: Int,
    height: Int,
    private val centered: Boolean,
    private val shadow: Boolean,
    private val font: Font,
    private val textColor: Int,
    private var paragraphs: List<Component>
) :
    AbstractWidget(x, y, width, height, Component.empty()) {
    private var scroll = 0
    private var maxScroll = 0

    constructor(
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        centered: Boolean,
        shadow: Boolean,
        font: Font,
        textColor: Int,
        vararg paragraphs: String
    ) : this(
        x,
        y,
        width,
        height,
        centered,
        shadow,
        font,
        textColor,
        Arrays.stream<String>(paragraphs).map<MutableComponent> { text: String? -> Component.literal(text) }.collect(
            Collectors.toList<Component>()
        )
    )

    constructor(
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        centered: Boolean,
        shadow: Boolean,
        font: Font,
        textColor: Int,
        vararg paragraphs: Component?
    ) : this(x, y, width, height, centered, shadow, font, textColor, java.util.List.of<Component>(*paragraphs))

    init {
        updateMaxScroll()
    }

    private fun updateMaxScroll() {
        val paragraphsHeight = paragraphs.stream()
            .mapToInt { p: Component? -> font.split(p, width - 2).size }
            .reduce(0) { a: Int, b: Int -> Integer.sum(a, b) } * lineHeight()
        this.maxScroll = if (paragraphs.isEmpty() || (paragraphs.size == 1 && paragraphs[0].string.isBlank()))
            0
        else
            (if (paragraphsHeight <= height) 0 else paragraphsHeight - height)
        this.scroll = min(scroll.toDouble(), maxScroll.toDouble()).toInt()
    }

    override fun renderWidget(guiGraphics: GuiGraphics, mouseX: Int, mouseY: Int, partialTick: Float) {
        RenderSystem.enableBlend()
        RenderSystem.defaultBlendFunc()
        RenderSystem.enableDepthTest()

        if (this.isHovered && this.maxScroll > 0) {
            renderScrollBar(guiGraphics)
        }
        this.renderParagraphs(guiGraphics)

        //        ScreenUtils.renderCutoff(guiGraphics, getX(), getY(), width, height, this::renderParagraphs);
    }

    fun clearParagraphs() {
        this.paragraphs = emptyList()
        this.maxScroll = 0
    }

    fun setParagraphs(vararg paragraphs: String) {
        this.paragraphs = paragraphs.map(Component::literal)
        updateMaxScroll()
    }

    fun setParagraphs(vararg paragraphs: Component?) {
        this.paragraphs = paragraphs.filterNotNull()
        updateMaxScroll()
    }

    fun setParagraphs(paragraphs: List<Component>) {
        this.paragraphs = paragraphs
        updateMaxScroll()
    }

    private fun renderScrollBar(graphics: GuiGraphics) {
        graphics.pose().pushPose()
        graphics.pose().translate(
            (x + width - 1).toDouble(),
            (y + (scroll.toFloat() / maxScroll) * (height - scrollBarHeight())).toDouble(),
            0.0
        )
        graphics.vLine(0, 0, scrollBarHeight(), textColor)
        graphics.pose().popPose()
    }

    private fun scrollBarHeight(): Int {
        return if (maxScroll == 0) 0 else height / (maxScroll + 1)
    }

    private fun renderParagraphs(poseStack: GuiGraphics) {
        var y = this.y - scroll
        for (component in paragraphs) {
            val paragraph = trimParagraph(component)
            y = renderParagraph(paragraph, poseStack, y)
        }
    }

    private fun trimParagraph(paragraph: Component): List<FormattedCharSequence> {
        return font.split(paragraph, width - 2)
    }

    private fun renderParagraph(paragraph: List<FormattedCharSequence>, stack: GuiGraphics, y: Int): Int {
        var y = y
        for (formattedCharSequence in paragraph) {
            drawLine(stack, formattedCharSequence, y)
            y += lineHeight()
        }
        return y
    }

    private fun drawLine(stack: GuiGraphics, line: FormattedCharSequence, y: Int) {
        val x = if (centered) x - font.width(line) / 2.0f else x.toFloat()

        stack.drawString(font, line, x.toInt(), y, textColor, shadow)
    }

    private fun lineHeight(): Int {
        return font.lineHeight + 1
    }

    override fun mouseScrolled(mouseX: Double, mouseY: Double, scrollX: Double, scrollY: Double): Boolean {
        this.scroll = Mth.clamp(scroll - scrollY, 0.0, maxScroll.toDouble()).toInt()
        return super.mouseScrolled(mouseX, mouseY, scrollX, scrollY)
    }

    override fun updateWidgetNarration(narrationElementOutput: NarrationElementOutput) {
    }
}