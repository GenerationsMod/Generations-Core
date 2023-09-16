package generations.gg.generations.core.generationscore.client.screen.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class FakeSlot extends AbstractWidget {

    public final int x;
    public final int y;
    private boolean hasTooltip;
    private ItemStack itemStack;
    private final OnWidgetPress onPress;
    private final Minecraft minecraft;
    private final ItemRenderer itemRenderer;
    private final ResourceLocation noItemIcon;

    public FakeSlot(int x, int y, ItemStack stack, OnWidgetPress onPress) {
        this(x, y, stack, null, onPress);
    }

    public FakeSlot(int x, int y, ItemStack stack, @Nullable ResourceLocation noItemIcon, OnWidgetPress onPress) {
        super(x, y, 16, 16, Component.empty());
        this.x = x;
        this.y = y;
        this.onPress = onPress;
        this.itemStack = stack;
        this.minecraft = Minecraft.getInstance();
        this.itemRenderer = minecraft.getItemRenderer();
        this.noItemIcon = noItemIcon;
    }

    @Override
    public void setTooltip(@Nullable Tooltip tooltip) {
        this.hasTooltip = tooltip != null;
        super.setTooltip(tooltip);
    }

    public boolean hasTooltip() {
        return this.hasTooltip;
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        guiGraphics.pose().pushPose();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);

        if (itemStack.isEmpty()) {
            ResourceLocation noItemIcon = getNoItemIcon();
            if (noItemIcon != null) {
                RenderSystem.setShaderTexture(0, noItemIcon);
                guiGraphics.blit(getNoItemIcon(), x, y, 0, 0, 16, 16, 16, 16);
            }
        } else {
            RenderSystem.enableDepthTest();
            guiGraphics.renderFakeItem(itemStack, x, y);
            guiGraphics.renderItemDecorations(minecraft.font, itemStack, x, y);
        }

        if (this.isHovered) {
            AbstractContainerScreen.renderSlotHighlight(guiGraphics, x, y, 0x80ffffff);
        }
        guiGraphics.pose().popPose();
    }

    public ItemStack getItem() {
        return itemStack;
    }

    public void setItem(ItemStack stack) {
        this.itemStack = stack;
    }

//    @OnlyIn(Dist.CLIENT)
    public ResourceLocation getNoItemIcon() {
        return noItemIcon;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (this.active && this.visible) {
            if (this.isValidClickButton(button)) {
                boolean flag = this.clicked(mouseX, mouseY);
                if (flag) {
                    onPress.onPress(this);
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
    }
}