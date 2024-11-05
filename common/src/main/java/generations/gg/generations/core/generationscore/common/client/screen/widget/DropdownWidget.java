//package generations.gg.generations.core.generationscore.common.client.screen.widget;
//
//import com.mojang.blaze3d.systems.RenderSystem;
//import generations.gg.generations.core.generationscore.common.GenerationsCore;
//import generations.gg.generations.core.generationscore.common.client.screen.ScreenUtils;
//import net.fabricmc.api.EnvType;
//import net.fabricmc.api.Environment;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.gui.Font;
//import net.minecraft.client.gui.GuiGraphics;
//import net.minecraft.client.gui.components.AbstractWidget;
//import net.minecraft.client.gui.components.Tooltip;
//import net.minecraft.client.gui.narration.NarrationElementOutput;
//import net.minecraft.network.chat.Component;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.util.Mth;
//
//import java.util.List;
//import java.util.function.BiConsumer;
//
//@Environment(EnvType.CLIENT)
//public class DropdownWidget extends AbstractWidget {
//
//    private static final Minecraft minecraft = Minecraft.getInstance();
//    private static final ResourceLocation TEXTURE = GenerationsCore.id("textures/gui/npc/customization.png");
//
//    private int scrollY;
//    private boolean showOptions;
//    private String selected;
//    private final int maxDisplayedOptions;
//    private final List<String> options;
//    private final BiConsumer<DropdownWidget, String> onSelectedChanged;
//    private final OnPress onStartFocus, onStopFocus;
//    private final int maxTextWidth;
//
//
//    public DropdownWidget(int x, int y, int width, List<String> options, String defaultOption, BiConsumer<DropdownWidget, String> onSelectedChanged, OnPress onStartFocus, OnPress onStopFocus) {
//        super(x, y, width, 11, Component.literal(defaultOption));
//        if (!options.contains(defaultOption)) {
//            throw new IllegalArgumentException("Given default option is not in the list of options for this Dropdown Widget");
//        }
//        this.options = options;
//        this.selected = defaultOption;
//        this.maxDisplayedOptions = 10; // (minecraft.getWindow().getGuiScaledHeight() - (y + 11 + options.size()*11)) / 11;
//        this.scrollY = clampScroll(options.indexOf(defaultOption));
//        this.onSelectedChanged = onSelectedChanged;
//        this.onStartFocus = onStartFocus;
//        this.onStopFocus = onStopFocus;
//        this.maxTextWidth = width-8;
//    }
//
//    @Override
//
//    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
//        guiGraphics.setColor(1.0F, 1.0F, 1.0F, this.alpha);
//        RenderSystem.enableBlend();
//        RenderSystem.defaultBlendFunc();
//        RenderSystem.enableDepthTest();
//        // draw main widget
//        {
//            boolean shouldRenderTooltip = mouseX >= getX() && mouseX <= getX() + width && mouseY >= getY() && mouseY <= getY() + 11;
//            if (shouldRenderTooltip)
//                this.setTooltip(Tooltip.create(this.getMessage()));
//            int i = this.getYImage(this.isHoveredOrFocused());
//            drawOption(guiGraphics, getX(), getY(), i, mouseX, mouseY, shortenText(this.getMessage().getString()));
//            guiGraphics.blit(TEXTURE, getX()-10, getY(), 191 + (this.showOptions ? 9 : 0), 177 + i * 11 - 11, 9, 11);
//        }
//        // draw options
//        if (showOptions) {
//            for (int i = 0; i < maxDisplayedOptions(); i++) {
//                int j = Mth.clamp(i+scrollY, 0, options.size());
//                boolean isOptionHovered = isMouseOverOption(i, mouseX, mouseY);
//                String text = shortenText(options.get(j));
//                if (isOptionHovered) {
//                    if (text.endsWith("...")) {
//                        this.setTooltip(Tooltip.create(Component.literal(options.get(j))));
//                    } else {
//                        this.setTooltip(null);
//                    }
//                }
//                drawOption(guiGraphics, getX(), getY()+11+i*11, this.getYImage(isOptionHovered), mouseX, mouseY, text);
//            }
//        }
//    }
//
//    @Override
//    public void onClick(double mouseX, double mouseY) {
//        if (!this.showOptions) {
//            setShowOptions(true);
//            this.onStartFocus.onPress();
//        }
//        else if (!isMouseOverAnyOption(mouseX, mouseY)) {
//            setShowOptions(false);
//            this.onStopFocus.onPress();
//        }
//        else {
//            for (int i = 0; i < maxDisplayedOptions(); i++) {
//                double minY = getY()+11+i*11;
//                if (mouseY >= minY && mouseY <= minY+11) {
//                    selectOption(i);
//                    this.onStopFocus.onPress();
//                    break;
//                }
//            }
//        }
//    }
//
//    @Override
//    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
//        if (delta > 0) {
//            this.scrollY = clampScroll(scrollY - 1);
//        } else {
//            this.scrollY = clampScroll(scrollY + 1);
//        }
//        return super.mouseScrolled(mouseX, mouseY, delta);
//    }
//
//    private void drawOption(GuiGraphics guiGraphics, int x, int y, int yImage, int mouseX, int mouseY, String text) {
//        int halfWidth = this.width / 2;
//        int v = 166 + yImage * 11;
//        guiGraphics.setColor(1.0F, 1.0F, 1.0F, this.alpha);
//        guiGraphics.blit(TEXTURE, x, y, 32, v, halfWidth, 11);
//        guiGraphics.blit(TEXTURE, x + halfWidth, y, 178 - halfWidth, v, halfWidth, 11);
//        int j = getFGColor();
//        ScreenUtils.drawCenteredString(guiGraphics, minecraft.font, text, x + this.width / 2, y + 2, j | Mth.ceil(this.alpha * 255.0F) << 24, false);
//    }
//
//    private String shortenText(String text) {
//        Font fontrenderer = minecraft.font;
//        if (fontrenderer.width(text) > maxTextWidth) {
//            return fontrenderer.plainSubstrByWidth(text, maxTextWidth - fontrenderer.width("...")) + "...";
//        }
//        return text;
//    }
//
//    public int clampScroll(int newScroll) {
//        int max = options.size()-maxDisplayedOptions;
//        if (max > 0)
//            return Mth.clamp(newScroll, 0, max);
//        else
//            return scrollY;
//    }
//
//    public void setShowOptions(boolean b) {
//        this.showOptions = b;
//        if (!showOptions) {
//            this.height = 11;
//            this.setFocused(false);
//        } else {
//            this.height = 11+maxDisplayedOptions()*11;
//            this.setFocused(true);
//        }
//    }
//
//    public boolean isShowingOptions() {
//        return showOptions;
//    }
//
//    private int maxDisplayedOptions() {
//        return Math.min(options.size(), maxDisplayedOptions);
//    }
//
//    private boolean isMouseOverOption(int index, double mouseX, double mouseY) {
//        double minY = this.getY()+11+index*11;
//        return mouseX >= getX() && mouseX <= getX()+width && mouseY >= minY && mouseY <= minY+11;
//    }
//
//    private boolean isMouseOverAnyOption(double mouseX, double mouseY) {
//        return mouseX >= getX() && mouseX <= getX()+width && mouseY >= getY()+11 && mouseY <= getY()+height;
//    }
//
//    public void select(int i) {
//        i = Mth.clamp(i+scrollY, 0, options.size());
//        this.selected = options.get(i);
//        this.setMessage(Component.literal(options.get(i)));
//        this.setShowOptions(false);
//    }
//
//    private void selectOption(int i) {
//        this.select(i);
//        this.onSelectedChanged.accept(this, this.selected);
//    }
//
//    @Override
//    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
//
//    }
//
//    protected int getYImage(boolean isHovered) {
//        int i = 1;
//        if (!this.active) {
//            i = 0;
//        } else if (isHovered) {
//            i = 2;
//        }
//
//        return i;
//    }
//
//    public int getFGColor() {
//        return this.active ? 0xFFFFFF : 0xA0A0A0;
//    }
//}