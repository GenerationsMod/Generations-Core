package generations.gg.generations.core.generationscore.common.client.screen.widget;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class ImageCheckbox extends AbstractButton {

    private final int u, v;
    private final Runnable onSelected, onDeselected;
    private final ResourceLocation texture;
    private boolean selected;

    public ImageCheckbox(int x, int y, int width, int height, ResourceLocation texture, int u, int v, Runnable onSelected, Runnable onDeselected, boolean selected) {
        super(x, y, width, height, Component.empty());
        this.selected = selected;
        this.texture = texture;
        this.u = u;
        this.v = v;
        this.onSelected = onSelected;
        this.onDeselected = onDeselected;
    }

    public void onPress() {
        this.selected = !this.selected;
        if (this.selected)
            onSelected.run();
        else
            onDeselected.run();
    }

    public boolean selected() {
        return this.selected;
    }

    public void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        narrationElementOutput.add(NarratedElementType.TITLE, this.createNarrationMessage());
        if (this.active) {
            if (this.isFocused()) {
                narrationElementOutput.add(NarratedElementType.USAGE, Component.translatable("narration.checkbox.usage.focused"));
            } else {
                narrationElementOutput.add(NarratedElementType.USAGE, Component.translatable("narration.checkbox.usage.hovered"));
            }
        }

    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        Minecraft minecraft = Minecraft.getInstance();
        RenderSystem.enableDepthTest();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        guiGraphics.blit(texture, getX(), getY(), u + (isFocused() ? width : 0), v + (selected ? height : 0), width, height);
//        this.renderBg(poseStack, minecraft, mouseX, mouseY);
    }
}