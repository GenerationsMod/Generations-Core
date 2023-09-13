package generations.gg.generations.core.generationscore.client.screen.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import generations.gg.generations.core.generationscore.client.screen.ScreenUtils;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ScrollableMultiLineTextBox extends AbstractWidget {

    private final boolean centered;
    private final boolean shadow;
    private final int textColor;
    private final Font font;
    private List<Component> paragraphs;

    private int scroll;
    private int maxScroll;

    public ScrollableMultiLineTextBox(int x, int y, int width, int height, boolean centered, boolean shadow, Font font, int textColor, String... paragraphs) {
        this(x, y, width, height, centered, shadow, font, textColor, Arrays.stream(paragraphs).map(Component::literal).collect(Collectors.toList()));
    }

    public ScrollableMultiLineTextBox(int x, int y, int width, int height, boolean centered, boolean shadow, Font font, int textColor, Component... paragraphs) {
        this(x, y, width, height, centered, shadow, font, textColor, List.of(paragraphs));
    }

    public ScrollableMultiLineTextBox(int x, int y, int width, int height, boolean centered, boolean shadow, Font font, int textColor, List<Component> paragraphs) {
        super(x, y, width, height, Component.empty());
        this.centered = centered;
        this.shadow = shadow;
        this.paragraphs = paragraphs;
        this.font = font;
        this.textColor = textColor;
        updateMaxScroll();
    }

    private void updateMaxScroll() {
        int paragraphsHeight = paragraphs.stream()
                .mapToInt(p -> font.split(p, width - 2).size())
                .reduce(0, Integer::sum) * lineHeight();
        this.maxScroll = paragraphs.isEmpty() || (paragraphs.size() == 1 && paragraphs.get(0).getString().isBlank())
                ? 0 : (paragraphsHeight <= height ? 0 : paragraphsHeight - height);
        this.scroll = Math.min(scroll, maxScroll);
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();

        if (this.isHovered && this.maxScroll > 0) {
            renderScrollBar(guiGraphics);
        }
        ScreenUtils.renderCutoff(guiGraphics, getX(), getY(), width, height, this::renderParagraphs);
    }

    public void clearParagraphs() {
        this.paragraphs = Collections.emptyList();
        this.maxScroll = 0;
    }

    public void setParagraphs(String... paragraphs) {
        this.paragraphs = Arrays.stream(paragraphs).map(Component::literal).collect(Collectors.toList());
        updateMaxScroll();
    }

    public void setParagraphs(Component... paragraphs) {
        this.paragraphs = List.of(paragraphs);
        updateMaxScroll();
    }

    public void setParagraphs(List<Component> paragraphs) {
        this.paragraphs = paragraphs;
        updateMaxScroll();
    }

    private void renderScrollBar(GuiGraphics graphics) {
        graphics.pose().pushPose();
        graphics.pose().translate(getX() + width - 1, getY() + ((float)scroll / maxScroll) * (height - scrollBarHeight()), 0.0);
        graphics.vLine(0, 0, scrollBarHeight(), textColor);
        graphics.pose().popPose();
    }

    private int scrollBarHeight() {
        return maxScroll == 0 ? 0 : height / (maxScroll + 1);
    }

    private void renderParagraphs(GuiGraphics poseStack) {
        int y = this.getY() - scroll;
        for (Component component : paragraphs) {
            List<FormattedCharSequence> paragraph = trimParagraph(component);
            y = renderParagraph(paragraph, poseStack, y);
        }
    }

    private List<FormattedCharSequence> trimParagraph(Component paragraph) {
        return font.split(paragraph, width - 2);
    }

    private int renderParagraph(List<FormattedCharSequence> paragraph, GuiGraphics stack, int y) {
        for (FormattedCharSequence formattedCharSequence : paragraph) {
            drawLine(stack, formattedCharSequence, y);
            y += lineHeight();
        }
        return y;
    }

    private void drawLine(GuiGraphics stack, FormattedCharSequence line, int y) {
        float x = centered ? getX() - font.width(line) / 2.0F : getX();

        stack.drawString(font, line, (int) x, y, textColor, shadow);
    }

    private int lineHeight() {
        return font.lineHeight + 1;
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        this.scroll = (int) Mth.clamp(scroll - delta, 0, maxScroll);
        return super.mouseScrolled(mouseX, mouseY, delta);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
    }

}