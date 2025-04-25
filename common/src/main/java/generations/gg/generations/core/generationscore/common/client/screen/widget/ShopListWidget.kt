package generations.gg.generations.core.generationscore.common.client.screen.widget

import com.mojang.blaze3d.systems.RenderSystem
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.client.screen.ShopScreen
import generations.gg.generations.core.generationscore.common.world.shop.SimpleShopEntry
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.components.AbstractWidget
import net.minecraft.client.gui.narration.NarrationElementOutput
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import kotlin.math.min

class ShopListWidget(
    private val screen: ShopScreen, x: Int, y: Int, width: Int, height: Int, private val entryHeight: Int,
    private var isBuyPage: Boolean, private var entries: Array<SimpleShopEntry>, private val onPress: OnPress
) : AbstractWidget(x, y, width, height, Component.empty()) {
    var selectedIndex: Int = -1
        private set

    private var maxScroll: Int
    private var scroll = 0

    init {
        val entriesHeight = entryHeight * entries.size
        this.maxScroll = if (entriesHeight <= height) 0 else -height
    }

    fun setEntries(entries: Array<SimpleShopEntry>, isBuyPage: Boolean) {
        this.entries = entries
        this.selectedIndex = min(selectedIndex.toDouble(), (entries.size - 1).toDouble()).toInt()
        this.isBuyPage = isBuyPage
        val entriesHeight = entryHeight * entries.size
        val newMaxScroll = if (entriesHeight <= height) 0 else entriesHeight - height
        if (newMaxScroll < scroll) this.scroll = newMaxScroll
        maxScroll = newMaxScroll
    }

    override fun renderWidget(guiGraphics: GuiGraphics, mouseX: Int, mouseY: Int, partialTick: Float) {
        RenderSystem.enableBlend()
        RenderSystem.defaultBlendFunc()
        if (scroll > 0) {
            guiGraphics.blit(TEXTURE, x + width / 2 - 5, y - 13, 10, 10, 716f, 235f, 21, 19, 921, 805)
        }
        if (maxScroll > 0 && scroll < maxScroll) {
            guiGraphics.blit(TEXTURE, x + width / 2 - 5, y + height + 3, 10, 10, 737f, 235f, 21, 19, 921, 805)
        }
        //        ScreenUtils.renderCutoff(guiGraphics, getX(), getY(), width, height, poseStack -> {
        for (i in entries.indices) {
            renderEntry(i, guiGraphics, mouseX, mouseY)
        }
        //        });
    }

    private fun renderEntry(index: Int, stack: GuiGraphics, mouseX: Int, mouseY: Int) {
        val entry = entries[index]
        val y = y + index * entryHeight

        if (index == selectedIndex || isEntryHovered(index, mouseX.toDouble(), mouseY.toDouble())) {
            stack.blit(TEXTURE, x, y, width + 23, entryHeight, 0f, 743f, 716, 62, 921, 805)
        }

        val itemX = x + paddingX
        val itemY = y + entryHeight / 2 - 8
        stack.renderFakeItem(entry.item, itemX, itemY)
        stack.renderItemDecorations(minecraft.font, entry.item, itemX, itemY)
        if (isItemHovered(index, mouseX, mouseY)) {
            stack.renderTooltip(minecraft.font, entry.item, mouseX, mouseY)
        }

        stack.drawString(
            minecraft.font,
            entry.item.hoverName.string,
            itemX + 20,
            (itemY + 8 - minecraft.font.lineHeight / 2f).toInt(),
            0x000000,
            false
        )

        val priceText = (if (isBuyPage) entry.buyPrice else entry.sellPrice).toString()
        val priceTextX = x + width - paddingX - minecraft.font.width(priceText)
        val priceTextY = y + entryHeight / 2 - minecraft.font.lineHeight / 2
        stack.blit(POKEDOLLAR_ICON, priceTextX - 7, priceTextY, 0f, 0f, 7, 9, 7, 9)
        stack.drawString(minecraft.font, priceText, priceTextX, priceTextY, 0x000000, false)
    }

    override fun onClick(mouseX: Double, mouseY: Double) {
        this.selectedIndex = hoveredEntryIndex(mouseX, mouseY)
        onPress.apply(selectedIndex)
    }

    private fun isEntryHovered(index: Int, mouseX: Double, mouseY: Double): Boolean {
        val y = y + index * entryHeight
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + entryHeight
    }

    private fun isItemHovered(index: Int, mouseX: Int, mouseY: Int): Boolean {
        val y = y + index * entryHeight + entryHeight / 2 - 8
        return mouseX >= x + paddingX && mouseX <= x + paddingX + 16 && mouseY >= y && mouseY <= y + 16
    }

    private fun hoveredEntryIndex(mouseX: Double, mouseY: Double): Int {
        for (i in entries.indices) {
            if (isEntryHovered(i, mouseX, mouseY)) return i
        }

        return -1
    }

    override fun mouseScrolled(mouseX: Double, mouseY: Double, scrollX: Double, scrollY: Double): Boolean {
        this.scroll = Mth.clamp(scroll - scrollY, 0.0, maxScroll.toDouble()).toInt()
        return super.mouseScrolled(mouseX, mouseY, scrollX, scrollY)
    }

    fun select(index: Int) {
        if (index < -1 || index >= entries.size) {
            return
        }
        this.selectedIndex = index
        onPress.apply(index)
    }

    override fun updateWidgetNarration(narrationElementOutput: NarrationElementOutput) {
    }

    interface OnPress {
        fun apply(index: Int)
    }

    companion object {
        private val minecraft: Minecraft = Minecraft.getInstance()
        private val POKEDOLLAR_ICON: ResourceLocation = GenerationsCore.id("textures/gui/pokedollar.png")
        private val TEXTURE: ResourceLocation = GenerationsCore.id("textures/gui/shop/shop.png")
        private const val paddingX = 2
    }
}