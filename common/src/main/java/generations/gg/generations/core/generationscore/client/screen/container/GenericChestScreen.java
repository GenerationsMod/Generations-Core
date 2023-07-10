package generations.gg.generations.core.generationscore.client.screen.container;

import generations.gg.generations.core.generationscore.world.container.GenericChestContainer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class GenericChestScreen extends AbstractContainerScreen<GenericChestContainer> {
    public GenericChestScreen(GenericChestContainer arg, Inventory arg2, Component arg3) {
        super(arg, arg2, arg3);
        imageWidth = getMenu().getGuiWidth();
        imageHeight = getMenu().getGuiHeight();

        this.titleLabelX = 8;
        this.titleLabelY = 5;
        this.inventoryLabelX = getMenu().getPlayerInventoryX();
        this.inventoryLabelY = this.imageHeight - 93;
    }

    @Override
    public void render(@NotNull GuiGraphics poseStack, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, partialTick);
        this.renderTooltip(poseStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics poseStack, float partialTick, int mouseX, int mouseY) {
        poseStack.pose().pushPose();
        poseStack.pose().translate(leftPos, topPos, 0);
        ContainerTextureSheet.BACKGROUND.render(poseStack, 0, 0, imageWidth, imageHeight);
        render(poseStack, 7, 15, 0, menu.getInventoryHeight(), menu.getInventoryWidth());
        render(poseStack, menu.getPlayerInventoryX() - 1, menu.getGuiHeight() - 83, 1, 3, 9);
        render(poseStack, menu.getPlayerInventoryX() - 1, menu.getGuiHeight() - 25, 0, 1, 9);
        poseStack.pose().popPose();
    }

    private void render(GuiGraphics stack, int x, int y, int startingRow, int rows, int columns) {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                ContainerTextureSheet.SLOT.render(stack, x + column * 18, y + row * 18);
            }
        }
    }

}
