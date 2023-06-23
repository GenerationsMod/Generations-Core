package generations.gg.generations.core.generationscore.client.screen.mails;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.network.packets.C2SEditMailPacket;
import generations.gg.generations.core.generationscore.world.item.MailItem;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.ChatFormatting;
import net.minecraft.SharedConstants;
import net.minecraft.Util;
import net.minecraft.client.GameNarrator;
import net.minecraft.client.StringSplitter;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.font.TextFieldHelper;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.commons.lang3.mutable.MutableInt;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static generations.gg.generations.core.generationscore.client.screen.mails.MailViewScreen.loadPages;

@Environment(value= EnvType.CLIENT)
public class MailEditScreen extends Screen {
    private static final int TEXT_WIDTH = 198;
    private static final int TEXT_HEIGHT = 126;
    private static final int IMAGE_WIDTH = 252;
    private static final int IMAGE_HEIGHT = 188;
    private static final FormattedCharSequence BLACK_CURSOR = FormattedCharSequence.forward("_", Style.EMPTY.withColor(ChatFormatting.BLACK));
    private static final FormattedCharSequence GRAY_CURSOR = FormattedCharSequence.forward("_", Style.EMPTY.withColor(ChatFormatting.GRAY));
    private final Player owner;
    private final ItemStack book;
    /**
     * Whether the book's title or contents has been modified since being opened
     */
    private boolean isModified;

    /**
     * Update ticks since the gui was opened
     */
    private int frameTick;
    private String contents = "";
    private final ResourceLocation location;
    private final TextFieldHelper pageEdit = new TextFieldHelper(this::getCurrentPageText, this::setCurrentPageText, this::getClipboard, this::setClipboard, string -> this.font.wordWrapHeight(string, TEXT_WIDTH) <= TEXT_HEIGHT);
    /**
     * In milliseconds
     */
    private long lastClickTime;
    private int lastIndex = -1;
    private final InteractionHand hand;
    @Nullable
    private DisplayCache displayCache = DisplayCache.EMPTY;

    public MailEditScreen(Player arg, ItemStack arg2, InteractionHand arg3) {
        super(GameNarrator.NO_TITLE);
        this.owner = arg;
        this.book = arg2;
        this.hand = arg3;
        CompoundTag compoundTag = arg2.getTag();
        if (compoundTag != null) {
            contents = loadPages(compoundTag);
        }

        location = ((MailItem) arg2.getItem()).getType().getLocation();
    }

    private void setClipboard(String clipboardValue) {
        if (this.minecraft != null) {
            TextFieldHelper.setClipboardContents(this.minecraft, clipboardValue);
        }
    }

    private String getClipboard() {
        return this.minecraft != null ? TextFieldHelper.getClipboardContents(this.minecraft) : "";
    }

    @Override
    public void tick() {
        super.tick();
        ++this.frameTick;
    }

    @Override
    protected void init() {
        this.clearDisplayCache();

        var correctedHeight = (height + IMAGE_HEIGHT) / 2;

        this.addRenderableWidget(Button.builder(Component.translatable("book.signButton"), arg -> {
            this.saveChanges(true);
            this.minecraft.setScreen(null);
        }).bounds(this.width / 2 - 100, correctedHeight + 2, 98, 20).build());
        this.addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, arg -> {
            this.saveChanges(false);
            this.minecraft.setScreen(null);
        }).bounds(this.width / 2 + 2, correctedHeight + 2, 98, 20).build());
    }

    private void saveChanges(boolean publish) {
        if (!this.isModified) {
            return;
        }
        this.updateLocalCopy(publish);
        int i = this.hand == InteractionHand.MAIN_HAND ? this.owner.getInventory().selected : 40;
        GenerationsCore.getImplementation().getNetworkManager().sendPacketToServer(new C2SEditMailPacket(i, this.contents, publish ? Optional.of("") : Optional.empty()));
    }

    private void updateLocalCopy(boolean sign) {
        if (!this.contents.isEmpty()) {
            this.book.addTagElement("contents", StringTag.valueOf(this.contents));
        }
        if (sign) {
            this.book.addTagElement("author", StringTag.valueOf(this.owner.getGameProfile().getName()));
        }
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (super.keyPressed(keyCode, scanCode, modifiers)) {
            return true;
        }

        boolean bl = this.bookKeyPressed(keyCode, scanCode, modifiers);
        if (bl) {
            this.clearDisplayCache();
            return true;
        }
        return false;
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        if (super.charTyped(codePoint, modifiers)) {
            return true;
        }

        if (SharedConstants.isAllowedChatCharacter(codePoint)) {
            this.pageEdit.insertText(Character.toString(codePoint));
            this.clearDisplayCache();
            return true;
        }
        return false;
    }

    /**
     * Handles keypresses, clipboard functions, and page turning
     */
    private boolean bookKeyPressed(int keyCode, int scanCode, int modifiers) {
        if (Screen.isSelectAll(keyCode)) {
            this.pageEdit.selectAll();
            return true;
        }
        if (Screen.isCopy(keyCode)) {
            this.pageEdit.copy();
            return true;
        }
        if (Screen.isPaste(keyCode)) {
            this.pageEdit.paste();
            return true;
        }
        if (Screen.isCut(keyCode)) {
            this.pageEdit.cut();
            return true;
        }
        TextFieldHelper.CursorStep cursorStep = Screen.hasControlDown() ? TextFieldHelper.CursorStep.WORD : TextFieldHelper.CursorStep.CHARACTER;
        switch (keyCode) {
            case 259 -> {
                this.pageEdit.removeFromCursor(-1, cursorStep);
                return true;
            }
            case 261 -> {
                this.pageEdit.removeFromCursor(1, cursorStep);
                return true;
            }
            case 257, 335 -> {
                this.pageEdit.insertText("\n");
                return true;
            }
            case 263 -> {
                this.pageEdit.moveBy(-1, Screen.hasShiftDown(), cursorStep);
                return true;
            }
            case 262 -> {
                this.pageEdit.moveBy(1, Screen.hasShiftDown(), cursorStep);
                return true;
            }
            case 265 -> {
                this.keyUp();
                return true;
            }
            case 264 -> {
                this.keyDown();
                return true;
            }
            case 268 -> {
                this.keyHome();
                return true;
            }
            case 269 -> {
                this.keyEnd();
                return true;
            }
        }
        return false;
    }

    private void keyUp() {
        this.changeLine(-1);
    }

    private void keyDown() {
        this.changeLine(1);
    }

    private void changeLine(int yChange) {
        int i = this.pageEdit.getCursorPos();
        int j = this.getDisplayCache().changeLine(i, yChange);
        this.pageEdit.setCursorPos(j, Screen.hasShiftDown());
    }

    private void keyHome() {
        if (Screen.hasControlDown()) {
            this.pageEdit.setCursorToStart(Screen.hasShiftDown());
        } else {
            int i = this.pageEdit.getCursorPos();
            int j = this.getDisplayCache().findLineStart(i);
            this.pageEdit.setCursorPos(j, Screen.hasShiftDown());
        }
    }

    private void keyEnd() {
        if (Screen.hasControlDown()) {
            this.pageEdit.setCursorToEnd(Screen.hasShiftDown());
        } else {
            DisplayCache displayCache = this.getDisplayCache();
            int i = this.pageEdit.getCursorPos();
            int j = displayCache.findLineEnd(i);
            this.pageEdit.setCursorPos(j, Screen.hasShiftDown());
        }
    }

    /**
     * Returns the contents of the current page as a string (or an empty string if the currPage isn't a valid page index)
     */
    private String getCurrentPageText() {
        return contents;
    }

    private void setCurrentPageText(String text) {
        this.contents = text;
        this.isModified = true;
        this.clearDisplayCache();
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(poseStack);
        this.setFocused(null);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, location);
        int x = (this.width - IMAGE_WIDTH) / 2;
        int y = (this.height - IMAGE_HEIGHT) / 2;
        blit(poseStack, x, y, 0, 0, IMAGE_WIDTH, IMAGE_HEIGHT, IMAGE_WIDTH, IMAGE_HEIGHT);

        DisplayCache displayCache = this.getDisplayCache();
        for (LineInfo lineInfo : displayCache.lines) {
            this.font.draw(poseStack, lineInfo.asComponent, (float)lineInfo.x, (float)lineInfo.y, -16777216);
        }
        this.renderHighlight(displayCache.selection);
        this.renderCursor(poseStack, displayCache.cursor, displayCache.cursorAtEnd);

        super.render(poseStack, mouseX, mouseY, partialTick);
    }

    private void renderCursor(PoseStack poseStack, Pos2i cursorPos, boolean isEndOfText) {
        if (this.frameTick / 6 % 2 == 0) {
            cursorPos = this.convertLocalToScreen(cursorPos);
            if (!isEndOfText) {
                GuiComponent.fill(poseStack, cursorPos.x, cursorPos.y - 1, cursorPos.x + 1, cursorPos.y + this.font.lineHeight, -16777216);
            } else {
                this.font.draw(poseStack, "_", (float)cursorPos.x, (float)cursorPos.y, 0);
            }
        }
    }

    private void renderHighlight(Rect2i[] selected) {
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferBuilder = tesselator.getBuilder();
        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderColor(0.0f, 0.0f, 255.0f, 255.0f);
//        RenderSystem.disableTexture();
        RenderSystem.enableColorLogicOp();
        RenderSystem.logicOp(GlStateManager.LogicOp.OR_REVERSE);
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION);
        for (Rect2i rect2i : selected) {
            int i = rect2i.getX();
            int j = rect2i.getY();
            int k = i + rect2i.getWidth();
            int l = j + rect2i.getHeight();
            bufferBuilder.vertex(i, l, 0.0).endVertex();
            bufferBuilder.vertex(k, l, 0.0).endVertex();
            bufferBuilder.vertex(k, j, 0.0).endVertex();
            bufferBuilder.vertex(i, j, 0.0).endVertex();
        }
        tesselator.end();
        RenderSystem.disableColorLogicOp();
//        RenderSystem.enableTexture();
    }

    private Pos2i convertScreenToLocal(Pos2i screenPos) {
        return new Pos2i(screenPos.x - (this.width - IMAGE_WIDTH) / 2 - MailViewScreen.PAGE_TEXT_X_OFFSET, screenPos.y - (this.height - IMAGE_HEIGHT) / 2  - MailViewScreen.PAGE_TEXT_Y_OFFSET);
    }

    private Pos2i convertLocalToScreen(Pos2i localScreenPos) {
        return new Pos2i(localScreenPos.x + (this.width - IMAGE_WIDTH) / 2 + MailViewScreen.PAGE_TEXT_X_OFFSET, localScreenPos.y + (this.height - IMAGE_HEIGHT) / 2 + MailViewScreen.PAGE_TEXT_Y_OFFSET);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (super.mouseClicked(mouseX, mouseY, button)) {
            return true;
        }
        if (button == 0) {
            long l = Util.getMillis();
            DisplayCache displayCache = this.getDisplayCache();
            int i = displayCache.getIndexAtPosition(this.font, this.convertScreenToLocal(new Pos2i((int)mouseX, (int)mouseY)));
            if (i >= 0) {
                if (i == this.lastIndex && l - this.lastClickTime < 250L) {
                    if (!this.pageEdit.isSelecting()) {
                        this.selectWord(i);
                    } else {
                        this.pageEdit.selectAll();
                    }
                } else {
                    this.pageEdit.setCursorPos(i, Screen.hasShiftDown());
                }
                this.clearDisplayCache();
            }
            this.lastIndex = i;
            this.lastClickTime = l;
        }
        return true;
    }

    private void selectWord(int index) {
        String string = this.getCurrentPageText();
        this.pageEdit.setSelectionRange(StringSplitter.getWordPosition(string, -1, index, false), StringSplitter.getWordPosition(string, 1, index, false));
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (super.mouseDragged(mouseX, mouseY, button, dragX, dragY)) {
            return true;
        }
        if (button == 0) {
            DisplayCache displayCache = this.getDisplayCache();
            int i = displayCache.getIndexAtPosition(this.font, this.convertScreenToLocal(new Pos2i((int)mouseX, (int)mouseY)));
            this.pageEdit.setCursorPos(i, true);
            this.clearDisplayCache();
        }
        return true;
    }

    private DisplayCache getDisplayCache() {
        if (this.displayCache == null) {
            this.displayCache = this.rebuildDisplayCache();
        }
        return this.displayCache;
    }

    private void clearDisplayCache() {
        this.displayCache = null;
    }

    private void clearDisplayCacheAfterPageChange() {
        this.pageEdit.setCursorToEnd();
        this.clearDisplayCache();
    }

    private DisplayCache rebuildDisplayCache() {
        boolean bl;
        String string = this.getCurrentPageText();
        if (string.isEmpty()) {
            return DisplayCache.EMPTY;
        }
        int i2 = this.pageEdit.getCursorPos();
        int j2 = this.pageEdit.getSelectionPos();
        IntArrayList intList = new IntArrayList();
        var list = Lists.<LineInfo>newArrayList();
        MutableInt mutableInt = new MutableInt();
        MutableBoolean mutableBoolean = new MutableBoolean();
        StringSplitter stringSplitter = this.font.getSplitter();
        stringSplitter.splitLines(string, MailViewScreen.TEXT_WIDTH, Style.EMPTY, true, (arg, i, j) -> {
            int k = mutableInt.getAndIncrement();
            String string2 = string.substring(i, j);
            mutableBoolean.setValue(string2.endsWith("\n"));
            String string3 = StringUtils.stripEnd(string2, " \n");
            int l = k * this.font.lineHeight;
            Pos2i pos2i = this.convertLocalToScreen(new Pos2i(0, l));
            intList.add(i);
            list.add(new LineInfo(arg, string3, pos2i.x, pos2i.y));
        });
        int[] is = intList.toIntArray();
        boolean bl2 = bl = i2 == string.length();

        int l;
        Pos2i pos2i;

        if (bl && mutableBoolean.isTrue()) {
            pos2i = new Pos2i(0, list.size() * this.font.lineHeight);
        } else {
            int k = MailEditScreen.findLineFromPos(is, i2);
            l = this.font.width(string.substring(is[k], i2));
            pos2i = new Pos2i(l, k * this.font.lineHeight);
        }
        ArrayList<Rect2i> list2 = Lists.newArrayList();
        if (i2 != j2) {
            int o;
            l = Math.min(i2, j2);
            int m = Math.max(i2, j2);
            int n = MailEditScreen.findLineFromPos(is, l);
            if (n == (o = MailEditScreen.findLineFromPos(is, m))) {
                int p = n * this.font.lineHeight;
                int q = is[n];
                list2.add(this.createPartialLineSelection(string, stringSplitter, l, m, p, q));
            } else {
                int p = n + 1 > is.length ? string.length() : is[n + 1];
                list2.add(this.createPartialLineSelection(string, stringSplitter, l, p, n * this.font.lineHeight, is[n]));
                for (int q = n + 1; q < o; ++q) {
                    int r = q * this.font.lineHeight;
                    String string2 = string.substring(is[q], is[q + 1]);
                    int s = (int)stringSplitter.stringWidth(string2);
                    list2.add(this.createSelection(new Pos2i(0, r), new Pos2i(s, r + this.font.lineHeight)));
                }
                list2.add(this.createPartialLineSelection(string, stringSplitter, is[o], m, o * this.font.lineHeight, is[o]));
            }
        }
        return new DisplayCache(string, pos2i, bl, is, list.toArray(new LineInfo[0]), list2.toArray(new Rect2i[0]));
    }

    static int findLineFromPos(int[] lineStarts, int find) {
        int i = Arrays.binarySearch(lineStarts, find);
        if (i < 0) {
            return -(i + 2);
        }
        return i;
    }

    private Rect2i createPartialLineSelection(String input, StringSplitter splitter, int i, int j, int k, int l) {
        String string = input.substring(l, i);
        String string2 = input.substring(l, j);
        Pos2i pos2i = new Pos2i((int)splitter.stringWidth(string), k);
        Pos2i pos2i2 = new Pos2i((int)splitter.stringWidth(string2), k + this.font.lineHeight);
        return this.createSelection(pos2i, pos2i2);
    }

    private Rect2i createSelection(Pos2i corner1, Pos2i corner2) {
        Pos2i pos2i = this.convertLocalToScreen(corner1);
        Pos2i pos2i2 = this.convertLocalToScreen(corner2);
        int i = Math.min(pos2i.x, pos2i2.x);
        int j = Math.max(pos2i.x, pos2i2.x);
        int k = Math.min(pos2i.y, pos2i2.y);
        int l = Math.max(pos2i.y, pos2i2.y);
        return new Rect2i(i, k, j - i, l - k);
    }

    @Environment(value= EnvType.CLIENT)
    static class DisplayCache {
        static final DisplayCache EMPTY = new DisplayCache("", new Pos2i(0, 0), true, new int[]{0}, new LineInfo[]{new LineInfo(Style.EMPTY, "", 0, 0)}, new Rect2i[0]);
        private final String fullText;
        final Pos2i cursor;
        final boolean cursorAtEnd;
        private final int[] lineStarts;
        final LineInfo[] lines;
        final Rect2i[] selection;

        public DisplayCache(String string, Pos2i arg, boolean bl, int[] is, LineInfo[] args, Rect2i[] args2) {
            this.fullText = string;
            this.cursor = arg;
            this.cursorAtEnd = bl;
            this.lineStarts = is;
            this.lines = args;
            this.selection = args2;
        }

        public int getIndexAtPosition(Font font, Pos2i cursorPosition) {
            int i = cursorPosition.y / font.lineHeight;
            if (i < 0) {
                return 0;
            }
            if (i >= this.lines.length) {
                return this.fullText.length();
            }
            LineInfo lineInfo = this.lines[i];
            return this.lineStarts[i] + font.getSplitter().plainIndexAtWidth(lineInfo.contents, cursorPosition.x, lineInfo.style);
        }

        public int changeLine(int xChange, int yChange) {
            int m;
            int i = MailEditScreen.findLineFromPos(this.lineStarts, xChange);
            int j = i + yChange;
            if (0 <= j && j < this.lineStarts.length) {
                int k = xChange - this.lineStarts[i];
                int l = this.lines[j].contents.length();
                m = this.lineStarts[j] + Math.min(k, l);
            } else {
                m = xChange;
            }
            return m;
        }

        public int findLineStart(int line) {
            int i = MailEditScreen.findLineFromPos(this.lineStarts, line);
            return this.lineStarts[i];
        }

        public int findLineEnd(int line) {
            int i = MailEditScreen.findLineFromPos(this.lineStarts, line);
            return this.lineStarts[i] + this.lines[i].contents.length();
        }
    }

    @Environment(value= EnvType.CLIENT)
    static class LineInfo {
        final Style style;
        final String contents;
        final Component asComponent;
        final int x;
        final int y;

        public LineInfo(Style arg, String string, int i, int j) {
            this.style = arg;
            this.contents = string;
            this.x = i;
            this.y = j;
            this.asComponent = Component.literal(string).setStyle(arg);
        }
    }

    @Environment(value= EnvType.CLIENT)
    static class Pos2i {
        public final int x;
        public final int y;

        Pos2i(int i, int j) {
            this.x = i;
            this.y = j;
        }
    }
}