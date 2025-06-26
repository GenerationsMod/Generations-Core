package generations.gg.generations.core.generationscore.common.client.screen.container

import generations.gg.generations.core.generationscore.common.world.container.GenericChestContainer
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.player.Inventory

class GenericChestScreen(menu: GenericChestContainer, inventory: Inventory, title: Component) : AbstractContainerScreen<GenericChestContainer>(menu, inventory, title) {
    init {
        imageWidth = menu.guiWidth
        imageHeight = menu.guiHeight

        this.titleLabelX = 8
        this.titleLabelY = 5
        this.inventoryLabelX = menu.playerInventoryX
        this.inventoryLabelY = this.imageHeight - 93
    }

    override fun render(poseStack: GuiGraphics, mouseX: Int, mouseY: Int, partialTick: Float) {
        this.renderBackground(poseStack, mouseX, mouseY, partialTick)
        super.render(poseStack, mouseX, mouseY, partialTick)
        this.renderTooltip(poseStack, mouseX, mouseY)
    }

    override fun renderBg(poseStack: GuiGraphics, partialTick: Float, mouseX: Int, mouseY: Int) {
        poseStack.pose().pushPose()
        poseStack.pose().translate(leftPos.toFloat(), topPos.toFloat(), 0f)
        ContainerTextureSheet.BACKGROUND.render(poseStack, 0, 0, imageWidth, imageHeight)
        render(poseStack, 7, 15, 0, menu.inventoryHeight, menu.inventoryWidth)
        render(poseStack, menu.playerInventoryX - 1, menu.guiHeight - 83, 1, 3, 9)
        render(poseStack, menu.playerInventoryX - 1, menu.guiHeight - 25, 0, 1, 9)
        poseStack.pose().popPose()
    }

    private fun render(stack: GuiGraphics, x: Int, y: Int, startingRow: Int, rows: Int, columns: Int) {
        for (row in 0..<rows) {
            for (column in 0..<columns) {
                ContainerTextureSheet.SLOT.render(stack, x + column * 18, y + row * 18)
            }
        }
    }
}
