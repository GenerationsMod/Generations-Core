package generations.gg.generations.core.generationscore.client.screen.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.client.screen.ScreenUtils;
import generations.gg.generations.core.generationscore.client.screen.ShopScreen;
import generations.gg.generations.core.generationscore.world.shop.SimpleShopEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class ShopListWidget extends AbstractWidget {

    private static final Minecraft minecraft = Minecraft.getInstance();
    private static final ResourceLocation POKEDOLLAR_ICON = GenerationsCore.id("textures/gui/pokedollar.png");
    private static final ResourceLocation TEXTURE = GenerationsCore.id("textures/gui/shop/shop.png");
    private static final int paddingX = 2;

    private final int entryHeight;
    private final ShopScreen screen;
    private final OnPress onPress;

    private boolean isBuyPage;
    private int selectedIndex = -1;
    private SimpleShopEntry[] entries;

    private int maxScroll;
    private int scroll;

    public ShopListWidget(ShopScreen screen, int x, int y, int width, int height, int entryHeight,
                          boolean isBuyPage, SimpleShopEntry[] entries, OnPress onPress) {
        super(x, y, width, height, Component.empty());
        this.isBuyPage = isBuyPage;
        this.screen = screen;
        this.entryHeight = entryHeight;
        this.entries = entries;
        this.onPress = onPress;
        int entriesHeight = entryHeight * entries.length;
        this.maxScroll = entriesHeight <= height ? 0 : - height;
    }

    public void setEntries(SimpleShopEntry[] entries, boolean isBuyPage) {
        this.entries = entries;
        this.selectedIndex = Math.min(selectedIndex, entries.length - 1);
        this.isBuyPage = isBuyPage;
        int entriesHeight = entryHeight * entries.length;
        int newMaxScroll = entriesHeight <= height ? 0 : entriesHeight - height;
        if (newMaxScroll < scroll)
            this.scroll = newMaxScroll;
        maxScroll = newMaxScroll;
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        if (scroll > 0) {
            guiGraphics.blit(TEXTURE, getX() + width / 2 - 5, getY() - 13, 10, 10, 716, 235, 21, 19, 921, 805);
        }
        if (maxScroll > 0 && scroll < maxScroll) {
            guiGraphics.blit(TEXTURE, getX() + width / 2 - 5, getY() + height + 3, 10, 10, 737, 235, 21, 19, 921, 805);
        }
        ScreenUtils.renderCutoff(guiGraphics, getX(), getY(), width, height, poseStack -> {
            for (int i = 0; i < entries.length; i++) {
                renderEntry(i, poseStack, mouseX, mouseY);
            }
        });
    }

    private void renderEntry(int index, GuiGraphics stack, int mouseX, int mouseY) {
        SimpleShopEntry entry = entries[index];
        int y = getY() + index * entryHeight;

        if (index == selectedIndex || isEntryHovered(index, mouseX, mouseY)) {
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, TEXTURE);
            stack.blit(TEXTURE, getX(), y, width + 23, entryHeight, 0, 743, 716, 62, 921, 805);
        }

        int itemX = getX() + paddingX;
        int itemY = y + entryHeight / 2 - 8;
        stack.renderFakeItem(entry.getItem(), itemX, itemY);
        stack.renderItemDecorations(minecraft.font, entry.getItem(), itemX, itemY);
        if (isItemHovered(index, mouseX, mouseY)) {
            stack.renderTooltip(minecraft.font, entry.getItem(), mouseX, mouseY);
        }

        stack.drawString(minecraft.font, entry.getItem().getHoverName().getString(), itemX + 20, (int) (itemY + 8 - minecraft.font.lineHeight / 2F), 0x000000);

        String priceText = String.valueOf(isBuyPage ? entry.getBuyPrice() : entry.getSellPrice());
        int priceTextX = getX() + width - paddingX - minecraft.font.width(priceText);
        int priceTextY = y + entryHeight / 2 - minecraft.font.lineHeight / 2;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, POKEDOLLAR_ICON);
        stack.blit(POKEDOLLAR_ICON, priceTextX - 7, priceTextY, 0, 0, 7, 9, 7, 9);
        stack.drawString(minecraft.font, priceText, priceTextX, priceTextY, 0x000000);
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        this.selectedIndex = hoveredEntryIndex(mouseX, mouseY);
        onPress.apply(selectedIndex);
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    private boolean isEntryHovered(int index, double mouseX, double mouseY) {
        int y = getY() + index * entryHeight;
        return mouseX >= getX() && mouseX <= getX() + width && mouseY >= y && mouseY <= y + entryHeight;
    }

    private boolean isItemHovered(int index, int mouseX, int mouseY) {
        int y = getY() + index * entryHeight + entryHeight / 2 - 8;
        return mouseX >= getX() + paddingX && mouseX <= getX() + paddingX + 16 && mouseY >= y && mouseY <= y + 16;
    }

    private int hoveredEntryIndex(double mouseX, double mouseY) {
        for (int i = 0; i < entries.length; i++) {
            if (isEntryHovered(i, mouseX, mouseY))
                return i;
        }

        return -1;
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        this.scroll = (int)Mth.clamp(scroll - delta, 0, maxScroll);
        return super.mouseScrolled(mouseX, mouseY, delta);
    }

    public void select(int index) {
        if (index < -1 || index >= entries.length) {
            return;
        }
        this.selectedIndex = index;
        this.onPress.apply(index);
    }

    @Override
    protected void updateWidgetNarration(@NotNull NarrationElementOutput narrationElementOutput) {
    }

    public interface OnPress {
        void apply(int index);
    }
}