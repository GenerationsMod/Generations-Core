package generations.gg.generations.core.generationscore.common.client.screen.mails

import com.mojang.blaze3d.systems.RenderSystem
import generations.gg.generations.core.generationscore.common.tags.GenerationsItemTags
import generations.gg.generations.core.generationscore.common.world.item.ClosedMailItem
import generations.gg.generations.core.generationscore.common.world.item.MailType
import generations.gg.generations.core.generationscore.common.world.item.MailTypes
import generations.gg.generations.core.generationscore.common.world.item.components.GenerationsDataComponents.MAIL_DATA
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.ChatFormatting
import net.minecraft.client.GameNarrator
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.components.Button
import net.minecraft.client.gui.screens.Screen
import net.minecraft.locale.Language
import net.minecraft.network.chat.*
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.FormattedCharSequence
import net.minecraft.util.Mth
import net.minecraft.world.item.ItemStack
import kotlin.math.min

@Environment(value = EnvType.CLIENT)
class MailViewScreen private constructor(arg: MailAccess, bl: Boolean) : Screen(GameNarrator.NO_TITLE) {
    private var mailAccess: MailAccess? = null

    /**
     * Holds a copy of the page text, split into page width lines
     */
    private var cachedPageComponents = emptyList<FormattedCharSequence>()

    private var authorComponent: FormattedCharSequence? = null

    /**
     * Determines if a sound is played when the page is turned
     */
    constructor(arg: MailAccess) : this(arg, true)

    constructor() : this(EMPTY_ACCESS, false)

    init {
        setBookAccess(arg)
    }

    override fun renderBlurredBackground(partialTick: Float) {
    }

    fun setBookAccess(mailAccess: MailAccess?) {
        this.mailAccess = mailAccess
        val formattedText = this.mailAccess!!.formattedContents
        this.cachedPageComponents = Minecraft.getInstance().font.split(formattedText, TEXT_WIDTH)
        this.authorComponent = Language.getInstance().getVisualOrder(
            this.mailAccess!!.formattedAuthor
        )
    }

    override fun init() {
        this.createMenuControls()
    }

    protected fun createMenuControls() {
        this.addRenderableWidget(
            Button.builder(
                CommonComponents.GUI_DONE
            ) { arg: Button? -> minecraft!!.setScreen(null) }
                .bounds(this.width / 2 - 100, (this.height + IMAGE_HEIGHT) / 2 + 4, 200, 20).build()
        )
    }

    override fun render(poseStack: GuiGraphics, mouseX: Int, mouseY: Int, partialTick: Float) {
        this.renderBackground(poseStack, mouseX, mouseY, partialTick)
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f)
        val x = (this.width - IMAGE_WIDTH) / 2
        val y = (this.height - IMAGE_HEIGHT) / 2
        poseStack.blit(mailAccess!!.texture(), x, y, 0f, 0f, IMAGE_WIDTH, IMAGE_HEIGHT, IMAGE_WIDTH, IMAGE_HEIGHT)

        poseStack.drawString(this.font, authorComponent, x + 164, y + 165, 0, false)

        val l = min(
            (TEXT_HEIGHT / font.lineHeight).toDouble(),
            cachedPageComponents.size.toDouble()
        ).toInt()
        for (m in 0 until l) {
            val formattedCharSequence = cachedPageComponents[m]
            poseStack.drawString(
                font,
                formattedCharSequence,
                (x + PAGE_TEXT_X_OFFSET),
                (y + PAGE_TEXT_Y_OFFSET + m * font.lineHeight),
                0,
                false
            )
        }
        val style = this.getClickedComponentStyleAt(mouseX.toDouble(), mouseY.toDouble())
        if (style != null) {
            poseStack.renderComponentHoverEffect(font, style, mouseX, mouseY)
        }
        super.render(poseStack, mouseX, mouseY, partialTick)
    }

    override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        var style: Style? = null
        if (button == 0 && (getClickedComponentStyleAt(mouseX, mouseY).also {
                style = it
            }) != null && this.handleComponentClicked(style)) {
            return true
        }
        return super.mouseClicked(mouseX, mouseY, button)
    }

    override fun handleComponentClicked(style: Style?): Boolean {
        val clickEvent = style?.clickEvent ?: return false

        val bl = super.handleComponentClicked(style)
        if (bl && clickEvent.action == ClickEvent.Action.RUN_COMMAND) {
            this.closeScreen()
        }
        return bl
    }

    protected fun closeScreen() {
        minecraft!!.setScreen(null)
    }

    fun getClickedComponentStyleAt(mouseX: Double, mouseY: Double): Style? {
        if (cachedPageComponents.isEmpty()) {
            return null
        }
        val i = Mth.floor(mouseX - ((this.width - IMAGE_WIDTH) / 2).toDouble() - PAGE_TEXT_X_OFFSET)
        val j = Mth.floor(mouseY - 2.0 - PAGE_TEXT_Y_OFFSET)
        if (i < 0 || j < 0) {
            return null
        }
        val k = min(
            (TEXT_HEIGHT / font.lineHeight).toDouble(),
            cachedPageComponents.size.toDouble()
        ).toInt()
        if (i <= TEXT_WIDTH && j < minecraft!!.font.lineHeight * k + k) {
            val l = j / minecraft!!.font.lineHeight
            if (l >= 0 && l < cachedPageComponents.size) {
                val formattedCharSequence = cachedPageComponents[l]
                return minecraft!!.font.splitter.componentStyleAtWidth(formattedCharSequence, i)
            }
            return null
        }
        return null
    }

    @Environment(value = EnvType.CLIENT)
    interface MailAccess {
        val formattedContents: FormattedText
        val formattedAuthor: FormattedText

        fun texture(): ResourceLocation

        companion object {
            fun fromItem(stack: ItemStack): MailAccess {
                if (stack.`is`(GenerationsItemTags.CLOSED_POKEMAIL)) {
                    return WrittenMailAccess(stack)
                }
                if (stack.`is`(GenerationsItemTags.POKEMAIL)) {
                    return WritableMailAccess(stack)
                }
                return EMPTY_ACCESS
            }
        }
    }

    @Environment(value = EnvType.CLIENT)
    class WritableMailAccess(arg: ItemStack) : MailAccess {
        val contents: String
        private val type: MailType

        init {
            val mail = arg.get(MAIL_DATA.value())

            this.contents = mail?.content ?: ""
            this.type = (arg.item as ClosedMailItem).type
        }

        override val formattedContents: FormattedText
            get() = FormattedText.of(contents)

        override val formattedAuthor: FormattedText
            get() = FormattedText.EMPTY

        override fun texture(): ResourceLocation {
            return type.location
        }
    }

    @Environment(value = EnvType.CLIENT)
    class WrittenMailAccess(arg: ItemStack) : MailAccess {
        private val contents: String
        val author: String
        private val type: MailType

        init {
            val mail = arg.get(MAIL_DATA.value())

            this.contents = mail?.content ?: Component.translatable("book.invalid.tag").withStyle(ChatFormatting.DARK_RED).string
            this.author = mail?.author ?: ""
            this.type = (arg.item as ClosedMailItem).type
        }

        override val formattedAuthor: FormattedText
            get() = FormattedText.of(author)

        override val formattedContents: FormattedText
            get() = FormattedText.of(contents)

        override fun texture(): ResourceLocation {
            return type.location
        }
    }

    companion object {
        const val PAGE_INDICATOR_TEXT_Y_OFFSET: Int = 16
        const val PAGE_TEXT_X_OFFSET: Int = 27
        const val PAGE_TEXT_Y_OFFSET: Int = 13
        val EMPTY_ACCESS: MailAccess = object : MailAccess {
            override val formattedContents: FormattedText
                get() = FormattedText.EMPTY

            override val formattedAuthor: FormattedText
                get() = FormattedText.EMPTY

            override fun texture(): ResourceLocation {
                return MailTypes.AIR.location
            }
        }

        public const val TEXT_WIDTH: Int = 198
        public const val TEXT_HEIGHT: Int = 126
        public const val IMAGE_WIDTH: Int = 252
        public  const val IMAGE_HEIGHT: Int = 188
    }
}