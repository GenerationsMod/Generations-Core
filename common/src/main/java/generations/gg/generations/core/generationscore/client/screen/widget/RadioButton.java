package generations.gg.generations.core.generationscore.client.screen.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import generations.gg.generations.core.generationscore.client.screen.ScreenUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;

public class RadioButton extends AbstractButton {

    private static final Minecraft minecraft = Minecraft.getInstance();

    private final int optionWidth, optionHeight;
    private final int u, v, uWidth, vHeight, textureWidth, textureHeight;
    private final int padding;
    private final Orientation orientation;
    private final ResourceLocation texture;
    private final RadioOption[] options;

    private int hoveredOptionId, selectedOptionId;

    public RadioButton(int x, int y, int width, int height, ResourceLocation texture,
                       int u, int v,
                       Orientation orientation, int padding, int defaultSelection, RadioOption... options) {
        this(x, y, width, height, texture, u, v, width, height, 256, 256, orientation, padding, defaultSelection, options);
    }

    public RadioButton(int x, int y, int width, int height, ResourceLocation texture,
                       int u, int v, int uWidth, int vHeight, int textureWidth, int textureHeight,
                       Orientation orientation, int padding, int defaultSelection, RadioOption... options) {
        super(x, y,
                orientation == Orientation.HORIZONTAL ? width * options.length + padding * (options.length-1) : width,
                orientation == Orientation.VERTICAL ? height * options.length + padding * (options.length-1) : height,
                Component.empty());
        this.optionWidth = width;
        this.optionHeight = height;
        this.texture = texture;
        this.u = u;
        this.v = v;
        this.uWidth = uWidth;
        this.vHeight = vHeight;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.orientation = orientation;
        this.padding = padding;
        this.options = options;
        this.hoveredOptionId = -1;
        this.selectedOptionId = defaultSelection;
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        Font font = minecraft.font;
        guiGraphics.setColor(1.0F, 1.0F, 1.0F, this.alpha);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        int x = this.getX();
        int y = this.getY();
        int xadd = this.orientation == Orientation.HORIZONTAL ? optionWidth + padding : 0;
        int yadd = this.orientation == Orientation.VERTICAL ? optionHeight + padding : 0;
        int k = this.getFGColor();
        boolean isAnyOptionHovered = false;
        for (int i = 0; i < options.length; i++) {
            int j = this.getYImage(selectedOptionId == i);
            if (isMouseOverOption(mouseX, mouseY, x, y)) {
                this.hoveredOptionId = i;
                j = getYImage(true);
                this.setTooltip(this.options[i].tooltip);
                isAnyOptionHovered = true;
            }

            guiGraphics.blit(texture, x, y, optionWidth, optionHeight, u, v + vHeight * j - vHeight, uWidth, vHeight, textureWidth, textureHeight);
            if (options[i].text != null) {
                ScreenUtils.drawCenteredString(guiGraphics, font, options[i].text, x + this.optionWidth / 2, y + height / 2 - font.lineHeight / 2, k | Mth.ceil(this.alpha * 255.0F) << 24, false);
            }
            x += xadd;
            y += yadd;
        }
        if (!isAnyOptionHovered) {
            this.setTooltip(null);
            this.hoveredOptionId = -1;
        }
    }

    private boolean isMouseOverOption(int mouseX, int mouseY, int optionX, int optionY) {
        return mouseX >= optionX && mouseX <= optionX + optionWidth && mouseY >= optionY && mouseY <= optionY + optionHeight;
    }

    @Override
    public void onPress() {
        if (hoveredOptionId != -1) {
            this.selectedOptionId = hoveredOptionId;
            options[hoveredOptionId].onPress.onPress();
        }
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {

    }

    public record RadioOption(@Nullable String text, @Nullable Tooltip tooltip, OnPress onPress) {}

    public enum Orientation {
        HORIZONTAL,
        VERTICAL
    }


    //Genericify
    public int getFGColor() {
        return this.active ? 0xFFFFFF : 0xA0A0A0;
    }

    protected int getYImage(boolean isHovered) {
        int i = 1;
        if (!this.active) {
            i = 0;
        } else if (isHovered) {
            i = 2;
        }

        return i;
    }
}