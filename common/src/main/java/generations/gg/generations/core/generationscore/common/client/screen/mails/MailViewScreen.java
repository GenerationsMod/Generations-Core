package generations.gg.generations.core.generationscore.common.client.screen.mails;

import com.mojang.blaze3d.systems.RenderSystem;
import generations.gg.generations.core.generationscore.common.tags.GenerationsItemTags;
import generations.gg.generations.core.generationscore.common.world.item.ClosedMailItem;
import generations.gg.generations.core.generationscore.common.world.item.MailType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.ChatFormatting;
import net.minecraft.client.GameNarrator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.locale.Language;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

@Environment(value= EnvType.CLIENT)
public class MailViewScreen extends Screen {
    public static final int PAGE_INDICATOR_TEXT_Y_OFFSET = 16;
    public static final int PAGE_TEXT_X_OFFSET = 27;
    public static final int PAGE_TEXT_Y_OFFSET = 13;
    public static final MailAccess EMPTY_ACCESS = new MailAccess() {
        @Override
        public FormattedText getContents() {
            return FormattedText.EMPTY;
        }

        @Override
        public FormattedText getAuthor() {
            return FormattedText.EMPTY;
        }

        @Override
        public ResourceLocation texture() {
            return MailType.AIR.getLocation();
        }
    };

    protected static final int TEXT_WIDTH = 198;
    protected static final int TEXT_HEIGHT = 126;
    protected static final int IMAGE_WIDTH = 252;
    protected static final int IMAGE_HEIGHT = 188;
    private MailAccess mailAccess;
    /**
     * Holds a copy of the page text, split into page width lines
     */
    private List<FormattedCharSequence> cachedPageComponents = Collections.emptyList();

    private FormattedCharSequence authorComponent;

    /**
     * Determines if a sound is played when the page is turned
     */

    public MailViewScreen(MailAccess arg) {
        this(arg, true);
    }

    public MailViewScreen() {
        this(EMPTY_ACCESS, false);
    }

    private MailViewScreen(MailAccess arg, boolean bl) {
        super(GameNarrator.NO_TITLE);
        setBookAccess(arg);
    }

    public void setBookAccess(MailAccess mailAccess) {
        this.mailAccess = mailAccess;
        FormattedText formattedText = this.mailAccess.getContents();
        this.cachedPageComponents = Minecraft.getInstance().font.split(formattedText, TEXT_WIDTH);
        this.authorComponent = Language.getInstance().getVisualOrder(this.mailAccess.getAuthor());
    }

    @Override
    protected void init() {
        this.createMenuControls();
    }

    protected void createMenuControls() {
        this.addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, arg -> this.minecraft.setScreen(null)).bounds(this.width / 2 - 100, (this.height + IMAGE_HEIGHT) / 2 + 4, 200, 20).build());
    }

    @Override
    public void render(GuiGraphics poseStack, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(poseStack);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        int x = (this.width - IMAGE_WIDTH) / 2;
        int y = (this.height - IMAGE_HEIGHT) / 2;
        poseStack.blit(mailAccess.texture(), x, y, 0, 0, IMAGE_WIDTH, IMAGE_HEIGHT, IMAGE_WIDTH, IMAGE_HEIGHT);

        poseStack.drawString(this.font, authorComponent, x + 164, y + 165, 0, false);

        int l = Math.min(TEXT_HEIGHT / this.font.lineHeight, this.cachedPageComponents.size());
        for (int m = 0; m < l; ++m) {
            FormattedCharSequence formattedCharSequence = this.cachedPageComponents.get(m);
            poseStack.drawString(font, formattedCharSequence, (x + PAGE_TEXT_X_OFFSET), (y + PAGE_TEXT_Y_OFFSET + m * this.font.lineHeight), 0, false);
        }
        Style style = this.getClickedComponentStyleAt(mouseX, mouseY);
        if (style != null) {
            poseStack.renderComponentHoverEffect(font, style, mouseX, mouseY);
        }
        super.render(poseStack, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        Style style;
        if (button == 0 && (style = this.getClickedComponentStyleAt(mouseX, mouseY)) != null && this.handleComponentClicked(style)) {
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean handleComponentClicked(Style style) {
        ClickEvent clickEvent = style.getClickEvent();
        if (clickEvent == null) {
            return false;
        }

        boolean bl = super.handleComponentClicked(style);
        if (bl && clickEvent.getAction() == ClickEvent.Action.RUN_COMMAND) {
            this.closeScreen();
        }
        return bl;
    }

    protected void closeScreen() {
        this.minecraft.setScreen(null);
    }

    @Nullable
    public Style getClickedComponentStyleAt(double mouseX, double mouseY) {
        if (this.cachedPageComponents.isEmpty()) {
            return null;
        }
        int i = Mth.floor(mouseX - (double)((this.width - IMAGE_WIDTH) / 2) - PAGE_TEXT_X_OFFSET);
        int j = Mth.floor(mouseY - 2.0 - PAGE_TEXT_Y_OFFSET);
        if (i < 0 || j < 0) {
            return null;
        }
        int k = Math.min(TEXT_HEIGHT / this.font.lineHeight, this.cachedPageComponents.size());
        if (i <= TEXT_WIDTH && j < this.minecraft.font.lineHeight * k + k) {
            int l = j / this.minecraft.font.lineHeight;
            if (l >= 0 && l < this.cachedPageComponents.size()) {
                FormattedCharSequence formattedCharSequence = this.cachedPageComponents.get(l);
                return this.minecraft.font.getSplitter().componentStyleAtWidth(formattedCharSequence, i);
            }
            return null;
        }
        return null;
    }

    public static String loadPages(CompoundTag tag) {
        return tag.getString("contents");
    }

    @Environment(value= EnvType.CLIENT)
    public interface MailAccess {
        FormattedText getContents();
        FormattedText getAuthor();
        ResourceLocation texture();

        static MailAccess fromItem(ItemStack stack) {
            if (stack.is(GenerationsItemTags.CLOSED_POKEMAIL)) {
                return new WrittenMailAccess(stack);
            }
            if (stack.is(GenerationsItemTags.POKEMAIL)) {
                return new WritableMailAccess(stack);
            }
            return EMPTY_ACCESS;
        }
    }

    @Environment(value= EnvType.CLIENT)
    public static class WritableMailAccess implements MailAccess {
        private final String contents;
        private final MailType type;

        public WritableMailAccess(ItemStack arg) {
            this.contents = WritableMailAccess.readContents(arg);
            this.type = ((ClosedMailItem) arg.getItem()).getType();
        }

        private static String readContents(ItemStack writtenBookStack) {
            CompoundTag compoundTag = writtenBookStack.getTag();
            return compoundTag != null ? MailViewScreen.loadPages(compoundTag) : "";
        }

        @Override
        public FormattedText getContents() {
            return FormattedText.of(contents);
        }

        @Override
        public FormattedText getAuthor() {
            return FormattedText.EMPTY;
        }

        @Override
        public ResourceLocation texture() {
            return type.getLocation();
        }
    }

    @Environment(value= EnvType.CLIENT)
    public static class WrittenMailAccess implements MailAccess {
        private final String pages;
        private final String author;
        private final MailType type;

        public WrittenMailAccess(ItemStack arg) {
            this.pages = WrittenMailAccess.readPages(arg);
            this.author = WrittenMailAccess.readAuthor(arg);
            this.type = ((ClosedMailItem) arg.getItem()).getType();
        }

        private static String readAuthor(ItemStack arg) {
            CompoundTag compoundTag = arg.getTag();
            if (compoundTag != null && ClosedMailItem.makeSureTagIsValid(compoundTag)) {
                return compoundTag.getString("author");
            }
            return "";
        }

        private static String readPages(ItemStack writtenBookStack) {
            CompoundTag compoundTag = writtenBookStack.getTag();
            if (compoundTag != null && ClosedMailItem.makeSureTagIsValid(compoundTag)) {
                return MailViewScreen.loadPages(compoundTag);
            }
            return Component.Serializer.toJson(Component.translatable("book.invalid.tag").withStyle(ChatFormatting.DARK_RED));
        }

        @Override
        public FormattedText getContents() {
            /*try {
                MutableComponent formattedText = Component.Serializer.fromJson(string);
                if (formattedText != null) {
                    return formattedText;
                }
            }
            catch (Exception exception) {
                // empty catch block
            }*/
            return FormattedText.of(this.pages);
        }

        @Override
        public FormattedText getAuthor() {
            return FormattedText.of(author);
        }

        @Override
        public ResourceLocation texture() {
            return type.getLocation();
        }
    }
}
