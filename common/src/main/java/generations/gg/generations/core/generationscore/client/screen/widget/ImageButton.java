package generations.gg.generations.core.generationscore.client.screen.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import generations.gg.generations.core.generationscore.client.screen.ScreenUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class ImageButton extends Button {
    private final Font font = Minecraft.getInstance().font;
    private final int u, v, uWidth, vHeight, textureWidth, textureHeight;
    public final ResourceLocation texture;
    public ImageButton(int x, int y, int width, int height, ResourceLocation texture,
            int u, int v, int uWidth, int vHeight, int textureWidth, int textureHeight, OnPress onPress) {
        super(x,y, width, height, Component.empty(), onPress, Button.DEFAULT_NARRATION);
        this.texture = texture;
        this.u = u;
        this.v = v;
        this.uWidth = uWidth;
        this.vHeight = vHeight;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        int i = getYImage(this.isHoveredOrFocused());
        RenderSystem.enableDepthTest();
        guiGraphics.blit(texture, getX(), getY(), width, height, u, v + vHeight * i - vHeight, uWidth, vHeight, textureWidth, textureHeight);
        ScreenUtils.drawCenteredString(guiGraphics, font, getMessage(), getX() + width/2, getY() + height / 2 - font.lineHeight / 2, 0xFFFFFF, false);
    }

    protected int getYImage(boolean isHovered) {
        int i = 1;
        if (!this.active) i = 0;
        else if (isHovered) i = 2;

        return i;
    }
}
