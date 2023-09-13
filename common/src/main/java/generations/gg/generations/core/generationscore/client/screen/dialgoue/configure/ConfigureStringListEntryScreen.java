package generations.gg.generations.core.generationscore.client.screen.dialgoue.configure;

import com.mojang.blaze3d.systems.RenderSystem;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.client.screen.ScreenUtils;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.AbstractNode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.font.TextFieldHelper;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector2i;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class ConfigureStringListEntryScreen extends Screen {
    private final TextFieldHelper textEditor;
    private final ConfigureDialogueScreen prevScreen;
    private final String fieldName;
    private final AbstractNode node;
    private final List<String> pages;
    private int currentPage;
    private int frameTick;
    private boolean cursorActive;
    private final DisplayCache displayCache = new DisplayCache(63);

    public ConfigureStringListEntryScreen(Screen screen, List<String> list, String fieldName, AbstractNode node) {
        super(CommonComponents.EMPTY);
        this.prevScreen = (ConfigureDialogueScreen) screen;
        this.pages = new ArrayList<>(list);
        this.textEditor = new TextFieldHelper(this::getCurrentPageText, this::setCurrentPageText, this::getClipboard, this::setClipboard, (string) -> true);
        this.fieldName = fieldName;
        this.node = node;
        displayCache.newText(pages.get(currentPage));
    }

    private void setClipboard(String clipboardValue) {
        if (this.minecraft != null) {
            TextFieldHelper.setClipboardContents(this.minecraft, clipboardValue);
        }
    }

    private String getClipboard() {
        return this.minecraft != null ? TextFieldHelper.getClipboardContents(this.minecraft) : "";
    }

    private void setCurrentPageText(String text) {
        if (this.currentPage >= 0 && this.currentPage < this.pages.size())
            this.pages.set(this.currentPage, text);
    }

    private String getCurrentPageText() {
        return this.currentPage >= 0 && this.currentPage < this.pages.size() ? this.pages.get(this.currentPage) : "";
    }

    private void goBack(Button button) {
        currentPage--;
        currentPage = Math.max(0, currentPage);
        displayCache.newText(pages.get(currentPage));
    }

    private void advance(Button button) {
        currentPage++;
        currentPage = Math.min(pages.size() - 1, currentPage);
        displayCache.newText(pages.get(currentPage));
    }

    private void changeLine(int yChange) {
        displayCache.changeLine(yChange);
        textEditor.setCursorPos(displayCache.getCursorOffset(), Screen.hasShiftDown());
    }

    private boolean checkSpecialKeyPresses(int keyCode, int scanCode, int modifiers) {
        if (Screen.isSelectAll(keyCode)) {
            this.textEditor.selectAll();
            return true;
        } else if (Screen.isCopy(keyCode)) {
            this.textEditor.copy();
            return true;
        } else if (Screen.isPaste(keyCode)) {
            this.textEditor.paste();
            return true;
        } else if (Screen.isCut(keyCode)) {
            this.textEditor.cut();
            return true;
        } else {
            var cursorStep = Screen.hasControlDown() ? TextFieldHelper.CursorStep.WORD : TextFieldHelper.CursorStep.CHARACTER;
            switch (keyCode) {
                case GLFW.GLFW_KEY_ENTER, GLFW.GLFW_KEY_KP_ENTER -> {
                    this.textEditor.insertText("\n");
                    displayCache.newText(pages.get(currentPage));
                    return true;
                }
                case GLFW.GLFW_KEY_BACKSPACE -> {
                    this.textEditor.removeFromCursor(-1, cursorStep);
                    displayCache.newText(pages.get(currentPage));
                    return true;
                }
                case GLFW.GLFW_KEY_DELETE -> {
                    this.textEditor.removeFromCursor(1, cursorStep);
                    displayCache.newText(pages.get(currentPage));
                    return true;
                }
                case GLFW.GLFW_KEY_RIGHT -> {
                    this.textEditor.moveBy(1, Screen.hasShiftDown(), cursorStep);
                    displayCache.updateCursor(textEditor.getCursorPos());
                    return true;
                }
                case GLFW.GLFW_KEY_LEFT -> {
                    this.textEditor.moveBy(-1, Screen.hasShiftDown(), cursorStep);
                    displayCache.updateCursor(textEditor.getCursorPos());
                    return true;
                }
                case GLFW.GLFW_KEY_DOWN -> {
                    this.changeLine(1);
                    return true;
                }
                case GLFW.GLFW_KEY_UP -> {
                    this.changeLine(-1);
                    return true;
                }
                case GLFW.GLFW_KEY_HOME -> {
                    if (Screen.hasControlDown()) {
                        this.textEditor.setCursorToStart(Screen.hasShiftDown());
                    } else {
                        int cursorPos = this.textEditor.getCursorPos();
                        int lineStart = this.displayCache.findLineStart(cursorPos);
                        this.textEditor.setCursorPos(lineStart, Screen.hasShiftDown());
                    }
                    return true;
                }
                case GLFW.GLFW_KEY_END -> {
                    if (Screen.hasControlDown()) {
                        this.textEditor.setCursorToEnd(Screen.hasShiftDown());
                    } else {
                        var displayCache = this.displayCache;
                        int cursorPos = this.textEditor.getCursorPos();
                        int j = displayCache.findLineEnd(cursorPos);
                        this.textEditor.setCursorPos(j, Screen.hasShiftDown());
                    }
                    return true;
                }
                default -> {
                    return false;
                }
            }
        }
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (super.keyPressed(keyCode, scanCode, modifiers)) return true;
        else return this.checkSpecialKeyPresses(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        if (!super.charTyped(codePoint, modifiers)) {
            textEditor.insertText(Character.toString(codePoint));
            displayCache.newText(pages.get(currentPage));
        }

        return true;
    }

    @Override
    protected void init() {
        super.init();
        this.addRenderableWidget(Button.builder(Component.literal("<"), this::goBack)
                .pos(width / 2 - 26 - (340 / 2), height - 84)
                .size(20, 20)
                .build()
        );

        this.addRenderableWidget(Button.builder(Component.literal(">"), this::advance)
                .pos(width / 2 + 6 + (340 / 2), height - 84)
                .size(20, 20)
                .build()
        );
    }

    @Override
    public void render(@NotNull GuiGraphics stack, int mouseX, int mouseY, float partialTick) {
        super.renderBackground(stack);
        super.render(stack, mouseX, mouseY, partialTick);

        if (currentPage >= pages.size()) {
            currentPage = pages.size() - 1;
        }

        stack.pose().pushPose();
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1, 1, 1, 1F);
        ScreenUtils.drawTexture(stack, GenerationsCore.id("textures/gui/dialogue/message_box.png"), width / 2 - 340 / 2, height - 84, 0, 0, 340, 80, 340, 80);
        stack.enableScissor(width / 2 - (340 / 2), height - 84, width / 2 + (340 / 2), height - 6);
        var y = height - 69;

        for (int i = 0; i < displayCache.lines.size(); i++) {
            var rawString = displayCache.lines.get(i);
            var component = ScreenUtils.formatStringWithColorsToComponent(rawString);
            if (displayCache.cursor.y == i) component = ScreenUtils.formatEditableStringWithColorsToComponent(rawString, displayCache.cursor.x);
            stack.drawString(Minecraft.getInstance().font, component, width / 2 - 340 / 2 + 8, y, 0xFFFFFF);
            y += 10;
        }

        stack.disableScissor();
        RenderSystem.disableBlend();
        stack.pose().popPose();
    }

    @Override
    public void tick() {
        frameTick++;
        if (frameTick % 10 == 0) cursorActive = !cursorActive;
    }

    @Override
    public void onClose() {
        prevScreen.save(node, fieldName, pages);

        minecraft.setScreen(prevScreen);
    }

    public static class DisplayCache {

        private final int width;
        private final Vector2i cursor = new Vector2i();
        private List<String> lines = new ArrayList<>();
        private String fullText;

        public DisplayCache(int width) {
            this.width = width;
        }

        public void newText(String currentText) {
            this.fullText = currentText;
            this.lines = calculateMultilineFittingString(currentText, width);
        }

        public int findLineEnd(int cursorPos) {
            int lineIndex = getLineWithinBounds((int) Math.ceil(cursorPos / (double) width));
            return lineIndex * width;
        }

        public int findLineStart(int cursorPos) {
            int lineIndex = getLineWithinBounds((int) Math.floor(cursorPos / (double) width));
            return (lineIndex) * width + 1; // Previous line + 1 char to get to start of current line
        }

        private int getLineWithinBounds(int index) {
            return Math.min(Math.max(0, index), lines.size() - 1);
        }

        public static List<String> calculateMultilineFittingString(String originalString, int charsPerLine) {
            if (originalString.length() < charsPerLine) return List.of(originalString);

            var lines = new ArrayList<String>();
            var toProcess = originalString;

            while (true) {
                if (toProcess.length() < charsPerLine) {
                    lines.add(toProcess.substring(1));
                    return lines;
                } else {
                    var line = toProcess.substring(0, charsPerLine);
                    // find the start of the word we may currently be inside of
                    int index = line.length() - 1;
                    while (((char) line.getBytes()[index]) != ' ') {
                        index--;
                    }

                    line = toProcess.substring(0, index);
                    if (line.getBytes()[0] == ' ') line = line.substring(1);
                    lines.add(line);
                    toProcess = toProcess.substring(index);
                }
            }
        }

        public void changeLine(int yChange) {
            cursor.set(cursor.x, getLineWithinBounds(cursor.y + yChange));
        }

        public int getCursorOffset() {
            return cursor.y * width + cursor.x;
        }

        public void updateCursor(int cursorPos) {
            cursor.set(Math.min(cursorPos - 1 % width, fullText.length() - 1), (int) Math.floor(cursorPos - 1 / (float) width));
        }
    }
}