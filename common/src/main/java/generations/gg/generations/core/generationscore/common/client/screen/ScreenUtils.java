package generations.gg.generations.core.generationscore.common.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class ScreenUtils {
    /**
     * Matches vanilla color codes, hex values and '*'.
     * strings like '*', '&4', '&r', '&f', '&#ff0012', '&#D7D7D7'...
     */
    private static final Pattern COLOR_PATTERN = Pattern.compile("((?i)&[0-9A-FK-OR])|((?i)&#[0-9A-F]{6})|(\\*)");

    /**
     * Utility method because Forge sucks with parameter names. Also, drawTexture makes more sense than blit.
     */
    public static void drawTexture(GuiGraphics matrices, ResourceLocation location, int x, int y, int u, int v, int width, int height, int textureWidth, int textureHeight) {
        matrices.blit(location,x, y, u, v, width, height, textureWidth, textureHeight);
    }

    /**
     * Utility method because Forge sucks with parameter names. Also, drawTexture makes more sense than blit.
     */
    public static void drawTexture(GuiGraphics graphics, ResourceLocation location, int x, int y, int width, int height, float uOffset, float vOffset, int uWidth, int vHeight, int textureWidth, int textureHeight) {
        graphics.blit(location, x, y, width, height, uOffset, vOffset, uWidth, vHeight, textureWidth, textureHeight);
    }

    public static void drawAnchoredTexture(GuiGraphics graphics, ResourceLocation location, int x, int y, int width, int height, int u, int v, int uWidth, int vHeight, int textureWidth, int textureHeight, Anchor anchor) {
        graphics.pose().pushPose();
        anchor.process(graphics.pose(), x, y, width, height);
        ScreenUtils.drawTexture(graphics, location, 0, 0, width, height, u, v, uWidth, vHeight, textureWidth, textureHeight);
        graphics.pose().popPose();
    }

    public static void drawAnchoredStretchedTexture(GuiGraphics stack, ResourceLocation location, int x, int y, int width, int height, int u, int v, int uWidth, int vHeight, int textureWidth, int textureHeight, float widthMultiplier, float heightMultiplier, Anchor anchor) {
        stack.pose().pushPose();
        anchor.process(stack.pose(), x, y, width, height, widthMultiplier, heightMultiplier);
        ScreenUtils.drawTexture(stack, location, 0, 0, width, height, u, v, uWidth, vHeight, textureWidth, textureHeight);
        stack.pose().popPose();
    }

    /**
     * {@link PoseStack} black magic to render text at a different scale
     */
    public static void drawScaledText(GuiGraphics matrices, Object text, float x, float y, float scale, int color, boolean shadow) {
        matrices.pose().pushPose();
        matrices.pose().scale(scale, scale, 0);
        matrices.pose().translate(x / scale, y / scale, 0);
        drawText(matrices, text, color, shadow);
        matrices.pose().popPose();
    }

    /**
     * {@link PoseStack} black magic to render text at a different scale but also with centering
     */
    public static void drawScaledCenteredText(GuiGraphics matrices, Object text, int x, int y, float scale, int color, boolean shadow) {
        drawScaledText(matrices, text, x - (textWidth(text) / 2f) * scale, (float) y, scale, color, shadow);
    }

    private static int textWidth(Object text) {
        var font = Minecraft.getInstance().font;

        if (text instanceof String string) return font.width(string);
        else if (text instanceof Component component) return font.width(component);
        else if (text instanceof FormattedCharSequence sequence) return font.width(sequence);
        throw new RuntimeException("Invalid Text Object " + text);
    }

    private static void drawText(GuiGraphics matrices, Object text, int color, boolean shadow) {
        var font = Minecraft.getInstance().font;

        if (text instanceof String string) matrices.drawString(font, string, 0, 0, color, shadow);
        else if (text instanceof Component component) matrices.drawString(font, component, 0, 0, color, shadow);
        else if (text instanceof FormattedCharSequence sequence) matrices.drawString(font, sequence, 0, 0, color, shadow);
    }

    private static void drawText(GuiGraphics matrices, Object text, float x, float y, int color, boolean shadow) {
        var font = Minecraft.getInstance().font;
        matrices.pose().pushPose();
        matrices.pose().translate(x, y, 0);
        if (text instanceof String string) matrices.drawString(font, string, 0, 0, color, shadow);
        else if (text instanceof Component component) matrices.drawString(font, component, 0, 0, color, shadow);
        else if (text instanceof FormattedCharSequence sequence) matrices.drawString(font, sequence, 0, 0, color, shadow);
        matrices.pose().popPose();
    }


    public static void drawTextWithHeight(GuiGraphics matrices, Object text, float x, float y, float height, int color) {
        drawTextWithHeight(matrices, text, x, y, height, color, Position.LEFT);
    }

    public static void drawText(GuiGraphics matrices, Object text, float x, float y, int color, ScreenUtils.Position pos) {
        switch (pos) {
            case LEFT -> drawText(matrices, text, x, y, color, false);

            case MIDDLE -> {
                int length = getTextLength(text) / 2;
                drawText(matrices, text, x - length, y, color, false);
            }
            case RIGHT -> {
                int length = getTextLength(text);
                drawText(matrices, text, x - length, y, color, false);
            }
        }
    }

    public static void drawTextWithHeight(GuiGraphics matrices, Object text, float x, float y, float height, int color, ScreenUtils.Position pos) {
        switch (pos) {
            case LEFT -> drawScaledText(matrices, text, x, y, height / 9f, color, false);

            case MIDDLE -> {
                int length = getScaledTextLength(text, (int) height) / 2;
                drawScaledText(matrices, text, x - length, y, height / 9f, color, false);
            }
            case RIGHT -> {
                int length = getScaledTextLength(text, (int) height);
                drawScaledText(matrices, text, x - length, y, height / 9f, color, false);
            }
        }
    }

    public static int getScaledTextLength(Object text, int height) {
        if (text instanceof String string) return (int) (Minecraft.getInstance().font.width(string) * (height / 9f));
        else if (text instanceof FormattedText string)
            return (int) (Minecraft.getInstance().font.width(string) * (height / 9f));
        else if (text instanceof FormattedCharSequence string)
            return (int) (Minecraft.getInstance().font.width(string) * (height / 9f));
        else return 0;
    }

    public static int getTextLength(Object text) {
        if (text instanceof String string) return Minecraft.getInstance().font.width(string);
        else if (text instanceof FormattedText string)
            return Minecraft.getInstance().font.width(string);
        else if (text instanceof FormattedCharSequence string)
            return Minecraft.getInstance().font.width(string);
        else return 0;
    }


    public static void drawTextWithHeightWithLengthScaling(GuiGraphics matrices, Object text, float x, float y, float optimalHeight, float length, int color, ScreenUtils.Position pos) {
        float height = optimalHeight;
        int actualLength = getScaledTextLength(text, (int) optimalHeight);

        if (actualLength > length) {
            height *= (length / actualLength);

            x += (optimalHeight / 2 - height / 2);
        }

        drawTextWithHeight(matrices, text, x, y, height, color, pos);
    }

    public static void drawCenteredString(GuiGraphics poseStack, Font font, Component text, int x, int y, int color, boolean shadow) {
        poseStack.drawString(font, text, (x - font.width(text) / 2), y, color, shadow);
    }

    public static void drawCenteredString(GuiGraphics poseStack, Font font, String text, int x, int y, int color, boolean shadow) {
        poseStack.drawString(font, text, (x - font.width(text) / 2), y, color, shadow);

    }

    public static void drawRect(GuiGraphics poseStack, float x, float y, float width, float height, int color) {
        if (width == 0 || height == 0) {
            return;
        }

        drawRect(poseStack.pose().last().pose(), 0, x, y, x + width, y + height, color);
    }

    public static void drawRect(Matrix4f mat, int zLevel, float left, float top, float right, float bottom, int startColor) {
        float startAlpha = (float) (startColor >> 24 & 0xFF) / 255.0f;
        float startRed = (float) (startColor >> 16 & 0xFF) / 255.0f;
        float startGreen = (float) (startColor >> 8 & 0xFF) / 255.0f;
        float startBlue = (float) (startColor & 0xFF) / 255.0f;

        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        var tessellator = Tesselator.getInstance();
        var buffer = tessellator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        buffer.addVertex(mat, right, top, zLevel).setColor(startRed, startGreen, startBlue, startAlpha);
        buffer.addVertex(mat, left, top, zLevel).setColor(startRed, startGreen, startBlue, startAlpha);
        buffer.addVertex(mat, left, bottom, zLevel).setColor(startRed, startGreen, startBlue, startAlpha);
        buffer.addVertex(mat, right, bottom, zLevel).setColor(startRed, startGreen, startBlue, startAlpha);
        BufferUploader.drawWithShader(buffer.buildOrThrow());
    }

    // FIXME: this code breaks if you raise the line above around half way on the Y axis
    public static void drawLine(Matrix4f mat, int zLevel, Vector2f start, Vector2f end, int startColor) {
        float startAlpha = (float) (startColor >> 24 & 0xFF) / 255.0f;
        float startRed = (float) (startColor >> 16 & 0xFF) / 255.0f;
        float startGreen = (float) (startColor >> 8 & 0xFF) / 255.0f;
        float startBlue = (float) (startColor & 0xFF) / 255.0f;

        RenderSystem.setShader(GameRenderer::getRendertypeLinesShader);
        RenderSystem.lineWidth(2.0F);
        var tessellator = Tesselator.getInstance();
        var buffer = tessellator.begin(VertexFormat.Mode.LINES, DefaultVertexFormat.POSITION_COLOR_NORMAL);
        var direction = new Vector2f();
        end.sub(start, direction).normalize(direction);
        buffer.addVertex(mat, start.x, start.y, zLevel).setColor(startRed, startGreen, startBlue, startAlpha).setNormal(direction.x, direction.y, 0);
        buffer.addVertex(mat, end.x, end.y, zLevel).setColor(startRed, startGreen, startBlue, startAlpha).setNormal(direction.x, direction.y, 0);
        BufferUploader.drawWithShader(buffer.buildOrThrow());
    }

    /**
     * Determines if the given coordinates are within the specified rectangle.
     *
     * @param x      The x-coordinate to check.
     * @param y      The y-coordinate to check.
     * @param xPos   The x-coordinate of the top-left corner of the rectangle.
     * @param yPos   The y-coordinate of the top-left corner of the rectangle.
     * @param width  The width of the rectangle.
     * @param height The height of the rectangle.
     * @return {@code true} if the coordinates are within the rectangle, {@code false} otherwise.
     */
    public static boolean contains(double x, double y, double xPos, double yPos, double width, double height) {
        return x >= xPos && x <= xPos + width && y >= yPos && y <= yPos + height;
    }

    /**
     * Determines if the given coordinates are within the specified rectangle.
     *
     * @param x      The x-coordinate to check.
     * @param y      The y-coordinate to check.
     * @param xPos   The x-coordinate of the top-left corner of the rectangle.
     * @param yPos   The y-coordinate of the top-left corner of the rectangle.
     * @param width  The width of the rectangle.
     * @param height The height of the rectangle.
     * @return {@code true} if the coordinates are within the rectangle, {@code false} otherwise.
     */
    public static boolean contains(int x, int y, int xPos, int yPos, int width, int height) {
        return x >= xPos && x <= xPos + width && y >= yPos && y <= yPos + height;
    }

    public static void transform(PoseStack matrixStack, float x, float y, float width, float height, float widthScale, float heightScale, Anchor anchor, Consumer<PoseStack> consumer) {
        float pivotX = anchor.getX(width);
        float pivotY = anchor.getY(height);
        matrixStack.pushPose();
        matrixStack.last().pose()
                .translate(pivotX, pivotY, 0)
                .scale(widthScale, heightScale, 1)
                .translate(-pivotX, -pivotY, 0)
                .translate(x, y, 0);
        consumer.accept(matrixStack);
        matrixStack.popPose();
    }

    public static EditBox createTextField(int x, int y, int width, int height, int maxTextLength,
                                          @NotNull String initialText,
                                          @NotNull Consumer<String> responder,
                                          @Nullable Tooltip tooltip) {
        return createTextField(x, y, width, height, maxTextLength, true, initialText, null, responder, tooltip);
    }

    public static EditBox createTextField(int x, int y, int width, int height, int maxTextLength,
                                          @NotNull String initialText,
                                          @NotNull Consumer<String> responder) {
        return createTextField(x, y, width, height, maxTextLength, true, initialText, null, responder, null);
    }

    public static EditBox createTextField(int x, int y, int width, int height, int maxTextLength,
                                          @NotNull String initialText,
                                          @Nullable Predicate<String> filter, @NotNull Consumer<String> responder) {
        return createTextField(x, y, width, height, maxTextLength, true, initialText, filter, responder, null);
    }

    public static EditBox createTextField(int x, int y, int width, int height, int maxTextLength,
                                          @NotNull String initialText,
                                          @Nullable Predicate<String> filter, @NotNull Consumer<String> responder,
                                          @Nullable Tooltip tooltip) {
        return createTextField(x, y, width, height, maxTextLength, true, initialText, filter, responder, tooltip);
    }

    public static EditBox createTextField(int x, int y, int width, int height, int maxTextLength, boolean bordered,
                                          @NotNull String initialText,
                                          @Nullable Predicate<String> filter, @NotNull Consumer<String> responder,
                                          @Nullable Tooltip tooltip) {
        EditBox textField = new EditBox(Minecraft.getInstance().font, x, y, width, height, Component.empty());

        textField.setValue(initialText);
        textField.setBordered(bordered);
        textField.setFilter(filter == null ? Objects::nonNull : filter);
        textField.setResponder(responder);
        textField.setMaxLength(maxTextLength);
        textField.setTooltip(tooltip);

        return textField;
    }

    /**
     * Formats the input string into a MutableComponent applying styles
     * based on the {@link #COLOR_PATTERN patterns} it finds in the text.
     *
     * @param input example: "This &l&ais a *test* &#a373f0input"
     */
    public static MutableComponent formatStringWithColorsToComponent(String input) {
        var matcher = COLOR_PATTERN.matcher(input);
        var component = Component.empty();
        var currentStyle = Style.EMPTY.applyFormat(ChatFormatting.BLACK);
        var prevIndex = 0;
        var hasMatchedAsterisk = false;
        while (matcher.find()) {
            var match = matcher.group(0);
            int start = matcher.start();
            int end = matcher.end();
            if (prevIndex != start) {
                component.append(Component.literal(input.substring(prevIndex, start)).withStyle(currentStyle));
            }
            prevIndex = end;

            if (match.equals("*")) {
                if (hasMatchedAsterisk) {
                    currentStyle = currentStyle
                            .applyFormat(ChatFormatting.RESET)
                            .applyFormat(ChatFormatting.BLACK);
                    hasMatchedAsterisk = false;
                } else {
                    currentStyle = currentStyle
                            .applyFormat(ChatFormatting.RESET)
                            .applyFormat(ChatFormatting.RED);
                    hasMatchedAsterisk = true;
                }
            } else if (match.startsWith("&#")) {
                currentStyle = getStyleFromHex(currentStyle, match);
            } else {
                currentStyle = getStyleFromColorCode(currentStyle, match);
            }
        }
        component.append(Component.literal(input.substring(prevIndex)).withStyle(currentStyle));

        return component;
    }

    public static MutableComponent formatEditableStringWithColorsToComponent(String input, int cursor) {
        var firstHalf = cursor == 0 ? Component.empty() : formatStringWithColorsToComponent(input.substring(0, cursor - 1));
        var lastHalf = formatStringWithColorsToComponent(input.substring(cursor + 1));
        var cursorChar = formatStringWithColorsToComponent(Character.toString(input.charAt(cursor)))
                .withStyle(ChatFormatting.UNDERLINE);

        return firstHalf.append(cursorChar).append(lastHalf);
    }

    /**
     * @param hexString examples: '&#FF0000', '&#D7D7D7'
     */
    public static Style getStyleFromHex(Style currentStyle, String hexString) {
        return currentStyle.withColor(Integer.parseInt(hexString.substring(2), 16));
    }

    /**
     * @param colorCode examples: '&4', '&l', '&k', '&7'
     */
    public static Style getStyleFromColorCode(Style currentStyle, String colorCode) {
        try {
            return currentStyle.applyFormat(Objects.requireNonNull(ChatFormatting.getByCode(colorCode.charAt(1))));
        } catch (Exception e) {
            return currentStyle;
        }
    }

    public static void renderCutoff(GuiGraphics stack, int x, int y, int width, int height, OnRender onRender) {
        stack.pose().pushPose();
        stack.pose().translate(0.0F, 0.0F, 950.0F);
        RenderSystem.enableDepthTest();
        RenderSystem.colorMask(false, false, false, false);
        stack.fill(4680, 2260, -4680, -2260, 0xFF000000);
        stack.pose().translate(0.0F, 0.0F, -950.0F);
        RenderSystem.depthFunc(GL11.GL_GEQUAL);
        stack.fill(x + width, y + height, x, y, 0xFF000000);
        RenderSystem.depthFunc(GL11.GL_LEQUAL);
        RenderSystem.colorMask(true, true, true, true);
        onRender.render(stack);
        RenderSystem.depthFunc(GL11.GL_GEQUAL);
        stack.pose().translate(0.0F, 0.0F, -950.0F);
        RenderSystem.colorMask(false, false, false, false);
        stack.fill(4680, 2260, -4680, -2260, 0xFF000000);
        RenderSystem.colorMask(true, true, true, true);
        RenderSystem.depthFunc(GL11.GL_LEQUAL);
        stack.pose().popPose();
    }

    public static void enableScissor(double x, double y, double endX, double endY) {
        double width = endX - x;
        double height = endY - y;
        width = Math.max(0, width);
        height = Math.max(0, height);
        float d = (float) Minecraft.getInstance().getWindow().getGuiScale();
        int ay = (int) ((Minecraft.getInstance().getWindow().getGuiScaledHeight() - (y + height)) * d);
        RenderSystem.enableScissor((int) (x * d), ay, (int) (width * d), (int) (height * d));
    }

    public static void endScissor() {
        RenderSystem.disableScissor();
    }


    public static void enableScissor(GuiGraphics stack, float x1, float y1, float x2, float y2) {
        Matrix4f matrix = stack.pose().last().pose();
        Vector3f coord = new Vector3f(x1, y1, 0);
        Vector3f end = new Vector3f(x2, y2, 0);
        matrix.transformPosition(coord);
        matrix.transformPosition(end);
        double x = coord.x();
        double y = coord.y();
        double endX = end.x();
        double endY = end.y();

        enableScissor(x, y, endX, endY);
    }


    public interface OnRender {
        void render(GuiGraphics stack);
    }

    public enum Position {
        LEFT, MIDDLE, RIGHT
    }
}
