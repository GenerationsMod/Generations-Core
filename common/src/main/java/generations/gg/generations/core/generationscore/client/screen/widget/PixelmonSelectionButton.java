package generations.gg.generations.core.generationscore.client.screen.widget;

import com.cobblemon.mod.common.api.gui.GuiUtilsKt;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.client.screen.ScreenUtils;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class PixelmonSelectionButton extends AbstractButton {

    private static final ResourceLocation TEXTURE = GenerationsCore.id("textures/gui/npc/customization.png");
    private final OnWidgetPress onPress;
    private PixelmonSelectionWidget.PixelmonWidgetData selectedPixelmon;

    public PixelmonSelectionButton(int x, int y, PixelmonSelectionWidget.PixelmonWidgetData selectedPixelmon, OnWidgetPress onPress) {
        super(x, y, 34, 34, Component.empty());
        setSelectedPixelmon(selectedPixelmon);
        this.onPress = onPress;
    }

    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
//        RenderSystem.enableBlend();
        graphics.blit(TEXTURE, getX(), getY(), 102, 199, 34, 34);

        graphics.pose().pushPose();

        graphics.enableScissor(getX() + 1, getY() + 1, getX() + 33, getY() + 33);
        graphics.pose().translate(getX() + 17,getY() + 1-8.57,0);

        GuiUtilsKt.drawPortraitPokemon(selectedPixelmon.data().getSpecies(), selectedPixelmon.data().getAspects(), graphics.pose(), 9.28f, true, null, partialTick);
        graphics.disableScissor();

        graphics.pose().popPose();

        ScreenUtils.drawTexture(graphics, TEXTURE, getX()+1, getY()+1, 0, 0, 32, 32, 32, 32, 256, 256);

    }

    public void setSelectedPixelmon(PixelmonSelectionWidget.PixelmonWidgetData data) {
        this.selectedPixelmon = data;
        setTooltip(Tooltip.create(Component.literal(selectedPixelmon.getName())));
    }

    @Override
    public void onPress() {
        this.onPress.onPress(this);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {

    }
}