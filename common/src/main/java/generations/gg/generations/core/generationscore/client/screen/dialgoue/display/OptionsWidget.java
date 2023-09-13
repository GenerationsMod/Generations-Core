package generations.gg.generations.core.generationscore.client.screen.dialgoue.display;

import com.mojang.blaze3d.systems.RenderSystem;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.client.screen.ScreenUtils;
import generations.gg.generations.core.generationscore.world.sound.GenerationsSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

public class OptionsWidget extends AbstractWidget {
    private static final ResourceLocation CHOICE_ARROW = GenerationsCore.id("textures/gui/dialogue/choice_arrow.png");
    private static final ResourceLocation MESSAGE_BOX = GenerationsCore.id("textures/gui/dialogue/message_box.png");
    private final List<String> options;
    private final Consumer<String> onPicked;
    private String hovered;

    public OptionsWidget(int x, int y, List<String> options, Consumer<String> onPicked) {
        super(x, y, calcWidth(options), calcHeight(options), Component.literal("Choose"));
        this.options = options;
        this.onPicked = onPicked;
    }

    private static int calcWidth(List<String> options) {
        return options.stream()
                .map(s -> Minecraft.getInstance().font.width(s))
                .sorted()
                .findFirst()
                .orElseThrow() + 20;
    }

    private static int calcHeight(List<String> options) {
        return options.size() * 10 + 10;
    }

    @Override
    public void renderWidget(@NotNull GuiGraphics stack, int mouseX, int mouseY, float partialTick) {
        isMouseOver(mouseX, mouseY);

        stack.pose().pushPose();
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1, 1, 1, 1F);

        ScreenUtils.drawTexture(stack, MESSAGE_BOX, getX() - getWidth(), getY() - getHeight(), 0, 0, getWidth(), 2, getWidth(), 20); // Top 3 Slice
        ScreenUtils.drawTexture(stack, MESSAGE_BOX, getX() - getWidth(), getY() - getHeight() + 2, 0, 50, getWidth(), getHeight() - 4, getWidth(), getHeight() * 4); // Top 3 Slice
        ScreenUtils.drawTexture(stack, MESSAGE_BOX, getX() - getWidth(), getY() - 2, 0, -2, getWidth(), 2, getWidth(), 20); // Top 3 Slice

        int y = getY() - getHeight() - 4;
        for (var option : options) {
            stack.drawString(Minecraft.getInstance().font, option, getX() - getWidth() + 15, y += 10, 0xFF000000);
        }

        if(hovered != null) {
            ScreenUtils.drawTexture(stack, CHOICE_ARROW, (int) (getX() - getWidth() + 4 + Mth.sin(partialTick * 0.2f) / 0.95f), getY() - getHeight() + 5 + options.indexOf(hovered) * 10, 0, 0, 8, 8, 8, 8);
        }

        stack.pose().popPose();
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        var top = getY() - getHeight();
        var left = getX() - getWidth();
        var listHovered = (mouseY >= top && mouseY <= getY()) && (mouseX >= left && mouseX <= getX());

        if (listHovered) {
            int textStartY = getY() - getHeight() - 2;
            var posInsideY = (float) mouseY - textStartY;
            var idx = Mth.floor(posInsideY / 10) - 1;
            hovered = options.get(Math.max(0, Math.min(idx, options.size() - 1)));
        } else {
            hovered = null;
        }

        return listHovered;
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        if (hovered != null) {
            onPicked.accept(hovered);
        }
    }

    @Override
    protected boolean clicked(double mouseX, double mouseY) {
        return this.active && this.visible && isMouseOver(mouseX, mouseY);
    }

    @Override
    public void playDownSound(@NotNull SoundManager handler) {
        handler.play(SimpleSoundInstance.forUI(GenerationsSounds.UI_CLICK.get(), 1.0F));
    }

    @Override
    protected void updateWidgetNarration(@NotNull NarrationElementOutput narrationElementOutput) {
    }
}