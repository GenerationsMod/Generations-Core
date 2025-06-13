package generations.gg.generations.core.generationscore.common.client.screen.mails

import com.cobblemon.mod.common.Cobblemon
import com.google.common.collect.Lists
import com.mojang.blaze3d.systems.RenderSystem
import generations.gg.generations.core.generationscore.common.network.packets.C2SEditMailPacket
import generations.gg.generations.core.generationscore.common.world.item.MailItem
import generations.gg.generations.core.generationscore.common.world.item.components.GenerationsDataComponents.MAIL_DATA
import generations.gg.generations.core.generationscore.common.world.item.components.MailContent
import it.unimi.dsi.fastutil.ints.IntArrayList
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.ChatFormatting
import net.minecraft.Util
import net.minecraft.client.GameNarrator
import net.minecraft.client.StringSplitter
import net.minecraft.client.gui.Font
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.components.Button
import net.minecraft.client.gui.font.TextFieldHelper
import net.minecraft.client.gui.screens.Screen
import net.minecraft.client.renderer.Rect2i
import net.minecraft.client.renderer.RenderType
import net.minecraft.network.chat.CommonComponents
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.Style
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.FormattedCharSequence
import net.minecraft.util.StringUtil
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import org.apache.commons.lang3.StringUtils
import org.apache.commons.lang3.mutable.MutableBoolean
import org.apache.commons.lang3.mutable.MutableInt
import java.util.*
import kotlin.math.max
import kotlin.math.min

@Environment(value = EnvType.CLIENT)
class MailEditScreen(private val owner: Player, private val book: ItemStack, private val hand: InteractionHand) :
    Screen(GameNarrator.NO_TITLE) {
    /**
     * Whether the book's title or contents has been modified since being opened
     */
    private var isModified: Boolean = false

    /**
     * Update ticks since the gui was opened
     */
    private var frameTick: Int = 0
    private var contents: String = ""
    private val location: ResourceLocation
    private val pageEdit: TextFieldHelper = TextFieldHelper(
        { this.currentPageText },
        { text: String -> this.currentPageText = text },
        { this.clipboard },
        { clipboardValue: String ->
            this.clipboard =
                clipboardValue
        },
        { string: String? -> font.wordWrapHeight(string, TEXT_WIDTH) <= TEXT_HEIGHT })

    /**
     * In milliseconds
     */
    private var lastClickTime: Long = 0
    private var lastIndex: Int = -1
    private var displayCache: DisplayCache? = DisplayCache.EMPTY

    init {
        val mailContent: MailContent? = book.get(MAIL_DATA)
        if (mailContent != null) {
            contents = mailContent.content
        }

        location = (book.item as MailItem).type.location
    }


    private var clipboard: String
        get() = if (this.minecraft != null) TextFieldHelper.getClipboardContents(
            minecraft
        ) else ""
        private set(clipboardValue) {
            if (this.minecraft != null) {
                TextFieldHelper.setClipboardContents(
                    this.minecraft,
                    clipboardValue
                )
            }
        }

    override fun renderBlurredBackground(partialTick: Float) {

    }

    override fun tick() {
        super.tick()
        ++this.frameTick
    }

    override fun init() {
        this.clearDisplayCache()

        val correctedHeight: Int = (height + IMAGE_HEIGHT) / 2

        this.addRenderableWidget(
            Button.builder(
                Component.translatable("book.signButton"),
                { arg: Button? ->
                    this.saveChanges(true)
                    minecraft!!.setScreen(null)
                }).bounds(this.width / 2 - 100, correctedHeight + 2, 98, 20).build()
        )
        this.addRenderableWidget(
            Button.builder(
                CommonComponents.GUI_DONE,
                { arg ->
                    this.saveChanges(false)
                    minecraft!!.setScreen(null)
                }).bounds(this.width / 2 + 2, correctedHeight + 2, 98, 20).build()
        )
    }

    private fun saveChanges(publish: Boolean) {
//        if (!this.isModified) {
//            return;
//        }
        this.updateLocalCopy(publish)
        val i: Int = if (this.hand == InteractionHand.MAIN_HAND) owner.inventory.selected else 40
        Cobblemon.implementation.networkManager.sendToServer(
            C2SEditMailPacket(
                i,
                this.contents,
                if (publish) Optional.of<String>("") else Optional.empty<String>()
            )
        )
    }

    private fun updateLocalCopy(sign: Boolean) {
        val mailContent: MailContent = book.getOrDefault(MAIL_DATA, MailContent())

        if (contents.isNotEmpty()) {
            mailContent.content = this.contents
        }
        if (sign) {
            mailContent.author = owner.gameProfile.name
        }
    }

    override fun keyPressed(keyCode: Int, scanCode: Int, modifiers: Int): Boolean {
        if (super.keyPressed(keyCode, scanCode, modifiers)) {
            return true
        }

        val bl: Boolean = this.bookKeyPressed(keyCode, scanCode, modifiers)
        if (bl) {
            this.clearDisplayCache()
            return true
        }
        return false
    }

    override fun charTyped(codePoint: Char, modifiers: Int): Boolean {
        if (super.charTyped(codePoint, modifiers)) {
            return true
        }

        if (StringUtil.isAllowedChatCharacter(codePoint)) {
            pageEdit.insertText(codePoint.toString())
            this.clearDisplayCache()
            return true
        }
        return false
    }

    /**
     * Handles keypresses, clipboard functions, and page turning
     */
    private fun bookKeyPressed(keyCode: Int, scanCode: Int, modifiers: Int): Boolean {
        if (isSelectAll(keyCode)) {
            pageEdit.selectAll()
            return true
        }
        if (isCopy(keyCode)) {
            pageEdit.copy()
            return true
        }
        if (isPaste(keyCode)) {
            pageEdit.paste()
            return true
        }
        if (isCut(keyCode)) {
            pageEdit.cut()
            return true
        }
        val cursorStep: TextFieldHelper.CursorStep =
            if (hasControlDown()) TextFieldHelper.CursorStep.WORD else TextFieldHelper.CursorStep.CHARACTER
        when (keyCode) {
            259 -> {
                pageEdit.removeFromCursor(-1, cursorStep)
                return true
            }

            261 -> {
                pageEdit.removeFromCursor(1, cursorStep)
                return true
            }

            257, 335 -> {
                pageEdit.insertText("\n")
                return true
            }

            263 -> {
                pageEdit.moveBy(-1, hasShiftDown(), cursorStep)
                return true
            }

            262 -> {
                pageEdit.moveBy(1, hasShiftDown(), cursorStep)
                return true
            }

            265 -> {
                this.keyUp()
                return true
            }

            264 -> {
                this.keyDown()
                return true
            }

            268 -> {
                this.keyHome()
                return true
            }

            269 -> {
                this.keyEnd()
                return true
            }
        }
        return false
    }

    private fun keyUp() {
        this.changeLine(-1)
    }

    private fun keyDown() {
        this.changeLine(1)
    }

    private fun changeLine(yChange: Int) {
        val i: Int = pageEdit.cursorPos
        val j: Int = getDisplayCache()!!.changeLine(i, yChange)
        pageEdit.setCursorPos(j, hasShiftDown())
    }

    private fun keyHome() {
        if (hasControlDown()) {
            pageEdit.setCursorToStart(hasShiftDown())
        } else {
            val i: Int = pageEdit.cursorPos
            val j: Int = getDisplayCache()!!.findLineStart(i)
            pageEdit.setCursorPos(j, hasShiftDown())
        }
    }

    private fun keyEnd() {
        if (hasControlDown()) {
            pageEdit.setCursorToEnd(hasShiftDown())
        } else {
            val displayCache: DisplayCache? =
                this.getDisplayCache()
            val i: Int = pageEdit.cursorPos
            val j: Int = displayCache!!.findLineEnd(i)
            pageEdit.setCursorPos(j, hasShiftDown())
        }
    }

    private var currentPageText: String
        /**
         * Returns the contents of the current page as a string (or an empty string if the currPage isn't a valid page index)
         */
        get() {
            return contents
        }
        private set(text) {
            this.contents = text
            this.isModified = true
            this.clearDisplayCache()
        }

    override fun render(graphics: GuiGraphics, mouseX: Int, mouseY: Int, partialTick: Float) {
        this.renderBackground(graphics, mouseX, mouseY, partialTick)
        this.focused = null
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f)
        val x: Int = (this.width - IMAGE_WIDTH) / 2
        val y: Int = (this.height - IMAGE_HEIGHT) / 2
        graphics.blit(location, x, y, 0f, 0f, IMAGE_WIDTH, IMAGE_HEIGHT, IMAGE_WIDTH, IMAGE_HEIGHT)

        val displayCache: DisplayCache? =
            this.getDisplayCache()
        for (lineInfo: LineInfo in displayCache!!.lines) {
            graphics.drawString(this.font, lineInfo.asComponent, lineInfo.x, lineInfo.y, -0x1000000, false)
        }
        this.renderHighlight(graphics, displayCache.selection)
        this.renderCursor(graphics, displayCache.cursor, displayCache.cursorAtEnd)

        super.render(graphics, mouseX, mouseY, partialTick)
    }

    private fun renderCursor(graphics: GuiGraphics, cursorPos: Pos2i, isEndOfText: Boolean) {
        var cursorPos: Pos2i = cursorPos
        if (this.frameTick / 6 % 2 == 0) {
            cursorPos = this.convertLocalToScreen(cursorPos)
            if (!isEndOfText) {
                graphics.fill(cursorPos.x, cursorPos.y - 1, cursorPos.x + 1, cursorPos.y + font.lineHeight, -0x1000000)
            } else {
                graphics.drawString(this.font, "_", cursorPos.x, cursorPos.y, 0, false)
            }
        }
    }

    private fun renderHighlight(graphics: GuiGraphics, selected: Array<Rect2i>) {
        for (rect2i: Rect2i in selected) {
            val i: Int = rect2i.x
            val j: Int = rect2i.y
            val k: Int = i + rect2i.width
            val l: Int = j + rect2i.height
            graphics.fill(RenderType.guiTextHighlight(), i, j, k, l, -16776961)
        }
    }

    private fun convertScreenToLocal(screenPos: Pos2i): Pos2i {
        return Pos2i(
            screenPos.x - (this.width - IMAGE_WIDTH) / 2 - MailViewScreen.PAGE_TEXT_X_OFFSET,
            screenPos.y - (this.height - IMAGE_HEIGHT) / 2 - MailViewScreen.PAGE_TEXT_Y_OFFSET
        )
    }

    private fun convertLocalToScreen(localScreenPos: Pos2i): Pos2i {
        return Pos2i(
            localScreenPos.x + (this.width - IMAGE_WIDTH) / 2 + MailViewScreen.PAGE_TEXT_X_OFFSET,
            localScreenPos.y + (this.height - IMAGE_HEIGHT) / 2 + MailViewScreen.PAGE_TEXT_Y_OFFSET
        )
    }

    override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        if (super.mouseClicked(mouseX, mouseY, button)) {
            return true
        }
        if (button == 0) {
            val l: Long = Util.getMillis()
            val displayCache: DisplayCache? =
                this.getDisplayCache()
            val i: Int = displayCache!!.getIndexAtPosition(
                this.font,
                this.convertScreenToLocal(Pos2i(mouseX.toInt(), mouseY.toInt()))
            )
            if (i >= 0) {
                if (i == this.lastIndex && l - this.lastClickTime < 250L) {
                    if (!pageEdit.isSelecting) {
                        this.selectWord(i)
                    } else {
                        pageEdit.selectAll()
                    }
                } else {
                    pageEdit.setCursorPos(i, hasShiftDown())
                }
                this.clearDisplayCache()
            }
            this.lastIndex = i
            this.lastClickTime = l
        }
        return true
    }

    private fun selectWord(index: Int) {
        val string: String = this.currentPageText
        pageEdit.setSelectionRange(
            StringSplitter.getWordPosition(string, -1, index, false),
            StringSplitter.getWordPosition(string, 1, index, false)
        )
    }

    override fun mouseDragged(mouseX: Double, mouseY: Double, button: Int, dragX: Double, dragY: Double): Boolean {
        if (super.mouseDragged(mouseX, mouseY, button, dragX, dragY)) {
            return true
        }
        if (button == 0) {
            val displayCache: DisplayCache? =
                this.getDisplayCache()
            val i: Int = displayCache!!.getIndexAtPosition(
                this.font,
                this.convertScreenToLocal(Pos2i(mouseX.toInt(), mouseY.toInt()))
            )
            pageEdit.setCursorPos(i, true)
            this.clearDisplayCache()
        }
        return true
    }

    private fun getDisplayCache(): DisplayCache? {
        if (this.displayCache == null) {
            this.displayCache = this.rebuildDisplayCache()
        }
        return this.displayCache
    }

    private fun clearDisplayCache() {
        this.displayCache = null
    }

    private fun clearDisplayCacheAfterPageChange() {
        pageEdit.setCursorToEnd()
        this.clearDisplayCache()
    }

    private fun rebuildDisplayCache(): DisplayCache {
        val bl: Boolean
        val string: String = this.currentPageText
        if (string.isEmpty()) {
            return DisplayCache.EMPTY
        }
        val i2: Int = pageEdit.cursorPos
        val j2: Int = pageEdit.selectionPos
        val intList: IntArrayList = IntArrayList()
        val list: ArrayList<LineInfo> = Lists.newArrayList()
        val mutableInt: MutableInt = MutableInt()
        val mutableBoolean: MutableBoolean = MutableBoolean()
        val stringSplitter: StringSplitter = font.splitter
        stringSplitter.splitLines(string, MailViewScreen.TEXT_WIDTH, Style.EMPTY, true, { arg: Style, i: Int, j: Int ->
            val k: Int = mutableInt.getAndIncrement()
            val string2: String = string.substring(i, j)
            mutableBoolean.setValue(string2.endsWith("\n"))
            val string3: String = StringUtils.stripEnd(string2, " \n")
            val l: Int = k * font.lineHeight
            val pos2i: Pos2i =
                this.convertLocalToScreen(Pos2i(0, l))
            intList.add(i)
            list.add(LineInfo(arg, string3, pos2i.x, pos2i.y))
        })
        val `is`: IntArray = intList.toIntArray()
        bl = i2 == string.length
        val bl2: Boolean = bl

        var l: Int
        val pos2i: Pos2i

        if (bl && mutableBoolean.isTrue) {
            pos2i = Pos2i(0, list.size * font.lineHeight)
        } else {
            val k: Int = findLineFromPos(`is`, i2)
            l = font.width(string.substring(`is`.get(k), i2))
            pos2i = Pos2i(l, k * font.lineHeight)
        }
        val list2: ArrayList<Rect2i> = Lists.newArrayList()
        if (i2 != j2) {
            var o: Int
            l = min(i2.toDouble(), j2.toDouble()).toInt()
            val m: Int = max(i2.toDouble(), j2.toDouble()).toInt()
            val n: Int = findLineFromPos(`is`, l)
            if (n == (findLineFromPos(`is`, m).also { o = it })) {
                val p: Int = n * font.lineHeight
                val q: Int = `is`.get(n)
                list2.add(this.createPartialLineSelection(string, stringSplitter, l, m, p, q))
            } else {
                val p: Int = if (n + 1 > `is`.size) string.length else `is`.get(n + 1)
                list2.add(
                    this.createPartialLineSelection(
                        string,
                        stringSplitter,
                        l,
                        p,
                        n * font.lineHeight,
                        `is`.get(n)
                    )
                )
                for (q in n + 1 until o) {
                    val r: Int = q * font.lineHeight
                    val string2: String = string.substring(`is`.get(q), `is`.get(q + 1))
                    val s: Int = stringSplitter.stringWidth(string2).toInt()
                    list2.add(this.createSelection(Pos2i(0, r), Pos2i(s, r + font.lineHeight)))
                }
                list2.add(
                    this.createPartialLineSelection(
                        string,
                        stringSplitter,
                        `is`.get(o),
                        m,
                        o * font.lineHeight,
                        `is`.get(o)
                    )
                )
            }
        }
        return DisplayCache(string, pos2i, bl, `is`, list.toTypedArray<LineInfo>(), list2.toTypedArray<Rect2i>())
    }

    private fun createPartialLineSelection(
        input: String,
        splitter: StringSplitter,
        i: Int,
        j: Int,
        k: Int,
        l: Int,
    ): Rect2i {
        val string: String = input.substring(l, i)
        val string2: String = input.substring(l, j)
        val pos2i: Pos2i = Pos2i(splitter.stringWidth(string).toInt(), k)
        val pos2i2: Pos2i = Pos2i(splitter.stringWidth(string2).toInt(), k + font.lineHeight)
        return this.createSelection(pos2i, pos2i2)
    }

    private fun createSelection(corner1: Pos2i, corner2: Pos2i): Rect2i {
        val pos2i: Pos2i =
            this.convertLocalToScreen(corner1)
        val pos2i2: Pos2i =
            this.convertLocalToScreen(corner2)
        val i: Int = min(pos2i.x.toDouble(), pos2i2.x.toDouble()).toInt()
        val j: Int = max(pos2i.x.toDouble(), pos2i2.x.toDouble()).toInt()
        val k: Int = min(pos2i.y.toDouble(), pos2i2.y.toDouble()).toInt()
        val l: Int = max(pos2i.y.toDouble(), pos2i2.y.toDouble()).toInt()
        return Rect2i(i, k, j - i, l - k)
    }

    @Environment(value = EnvType.CLIENT)
    internal class DisplayCache(
        private val fullText: String,
        val cursor: Pos2i,
        val cursorAtEnd: Boolean,
        private val lineStarts: IntArray,
        val lines: Array<LineInfo>,
        val selection: Array<Rect2i>,
    ) {
        fun getIndexAtPosition(font: Font, cursorPosition: Pos2i): Int {
            val i: Int = cursorPosition.y / font.lineHeight
            if (i < 0) {
                return 0
            }
            if (i >= lines.size) {
                return fullText.length
            }
            val lineInfo: LineInfo =
                lines.get(i)
            return lineStarts.get(i) + font.splitter.plainIndexAtWidth(
                lineInfo.contents,
                cursorPosition.x,
                lineInfo.style
            )
        }

        fun changeLine(xChange: Int, yChange: Int): Int {
            val m: Int
            val i: Int = findLineFromPos(this.lineStarts, xChange)
            val j: Int = i + yChange
            if (0 <= j && j < lineStarts.size) {
                val k: Int = xChange - lineStarts.get(i)
                val l: Int = lines.get(j).contents.length
                m = (lineStarts.get(j) + min(k.toDouble(), l.toDouble())).toInt()
            } else {
                m = xChange
            }
            return m
        }

        fun findLineStart(line: Int): Int {
            val i: Int = findLineFromPos(this.lineStarts, line)
            return lineStarts.get(i)
        }

        fun findLineEnd(line: Int): Int {
            val i: Int = findLineFromPos(this.lineStarts, line)
            return lineStarts.get(i) + lines.get(i).contents.length
        }

        companion object {
            val EMPTY: DisplayCache = DisplayCache(
                "",
                Pos2i(0, 0),
                true,
                intArrayOf(0),
                arrayOf(LineInfo(Style.EMPTY, "", 0, 0)),
                emptyArray()
            )
        }
    }

    @Environment(value = EnvType.CLIENT)
    internal class LineInfo(val style: Style, val contents: String, val x: Int, val y: Int) {
        val asComponent: Component

        init {
            this.asComponent = Component.literal(contents).setStyle(style)
        }
    }

    @Environment(value = EnvType.CLIENT)
    internal class Pos2i(val x: Int, val y: Int)
    companion object {
        private const val TEXT_WIDTH: Int = 198
        private const val TEXT_HEIGHT: Int = 126
        private const val IMAGE_WIDTH: Int = 252
        private const val IMAGE_HEIGHT: Int = 188
        private val BLACK_CURSOR: FormattedCharSequence = FormattedCharSequence.forward(
            "_", Style.EMPTY.withColor(
                ChatFormatting.BLACK
            )
        )
        private val GRAY_CURSOR: FormattedCharSequence = FormattedCharSequence.forward(
            "_", Style.EMPTY.withColor(
                ChatFormatting.GRAY
            )
        )

        fun findLineFromPos(lineStarts: IntArray, find: Int): Int {
            val i: Int = Arrays.binarySearch(lineStarts, find)
            if (i < 0) {
                return -(i + 2)
            }
            return i
        }
    }
}