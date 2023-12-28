package generations.gg.generations.core.generationscore.client.screen.widget;

import com.cobblemon.mod.common.api.gui.GuiUtilsKt;
import com.cobblemon.mod.common.api.pokemon.PokemonSpecies;
import com.cobblemon.mod.common.pokemon.RenderablePokemon;
import com.cobblemon.mod.common.pokemon.Species;
import com.mojang.blaze3d.systems.RenderSystem;
import generations.gg.generations.core.generationscore.GenerationsCore;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Consumer;

public class PixelmonSelectionWidget extends AbstractButton {

    private static final Minecraft minecraft = Minecraft.getInstance();
    private static final ResourceLocation TEXTURE = GenerationsCore.id("textures/gui/npc/customization.png");
    private final List<PixelmonWidgetData> registeredPixelmon = PokemonSpecies.INSTANCE.getImplemented().stream()
            .map(Species::getForms)
            .flatMap(Collection::stream)
            .map(a -> new RenderablePokemon(a.species, new HashSet<>(a.getAspects())))
            .map(PixelmonWidgetData::new)
            .sorted(Comparator.comparing(PixelmonWidgetData::getName)).toList();

    private int scrollY;
    private String currentFilter;
    private PixelmonWidgetData hoveredPixelmonData;
    private final Consumer<PixelmonWidgetData> onPixelmonSelected;
    private List<PixelmonWidgetData> registeredPixelmonFiltered;
    private List<PixelmonWidgetData> shownData;

    public PixelmonSelectionWidget(int x, int y, Consumer<PixelmonWidgetData> onPixelmonSelected) {
        super(x, y, 5 * 34, 5 * 34, Component.empty());
        updateShownData();
        this.onPixelmonSelected = onPixelmonSelected;
    }

    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
        boolean isAnyPixelmonHovered = false;
        for (int yOff = 0; yOff < 5; yOff++) {
            for (int xOff = 0; xOff < 5; xOff++) {
                int j = xOff + yOff * 5;
                if (j < shownData.size()) {
                    graphics.pose().pushPose();
                    int x = this.getX() + xOff * 34;
                    int y = this.getY() + yOff * 34;

                    graphics.pose().translate(x,y,0);

                    graphics.blit(TEXTURE, 0, 0, 102, 199, 34, 34, 256, 256);

                    graphics.enableScissor(x + 1, y + 1, x + 33, y + 33);
                    graphics.pose().translate(17,1-8.57,0);

                    var pixelmon = shownData.get(j);

                    GuiUtilsKt.drawPortraitPokemon(pixelmon.data().getSpecies(), pixelmon.data.getAspects(), graphics.pose(), 9.28f, true, null, partialTick);
                    graphics.disableScissor();
                    if (isMouseOverPixelmon(mouseX, mouseY, x, y)) {
                        this.setTooltip(Tooltip.create(Component.literal(pixelmon.getName())));
                        this.setHoveredPixelmonData(pixelmon);
                        isAnyPixelmonHovered = true;
                    }

                    graphics.pose().popPose();
                }
            }
        }
        if (!isAnyPixelmonHovered) {
            setTooltip(null);
            setHoveredPixelmonData(null);
        }
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        if (delta > 0) {
            this.scrollY = clampScroll(scrollY - 1);
        } else {
            this.scrollY = clampScroll(scrollY + 1);
        }
        updateShownData();
        return super.mouseScrolled(mouseX, mouseY, delta);
    }

    public int clampScroll(int newScroll) {
        int max = registeredPixelmonFiltered.size() - 25;
        if (max > 0)
            return Mth.clamp(newScroll, 0, max);
        else
            return scrollY;
    }

    private boolean isMouseOverPixelmon(int mouseX, int mouseY, int pixelmonX, int pixelmonY) {
        return mouseX >= pixelmonX && mouseX <= pixelmonX + 34 && mouseY >= pixelmonY && mouseY <= pixelmonY + 34;
    }

    private void setHoveredPixelmonData(@Nullable PixelmonWidgetData data) {
        this.hoveredPixelmonData = data;
    }

    @Override
    public void onPress() {
        if (hoveredPixelmonData != null)
            this.onPixelmonSelected.accept(hoveredPixelmonData);
    }

    private void updateShownData() {
        updateShownData(currentFilter);
    }

    public void updateShownData(@Nullable String filter) {
        currentFilter = filter;
        setFilteredData();
        int min = Math.min(scrollY, Math.max(0, registeredPixelmonFiltered.size() - 25));
        int max = Math.min(min + 25, registeredPixelmonFiltered.size());
        shownData = registeredPixelmonFiltered.subList(min, max);
    }

    private void setFilteredData() {
        registeredPixelmonFiltered = currentFilter != null && !currentFilter.isBlank() ?
                registeredPixelmon.stream().filter(data -> data.getName().toLowerCase(Locale.ENGLISH).contains(currentFilter.toLowerCase(Locale.ENGLISH))).toList()
                        /*|| data.key.toString().toLowerCase(Locale.ENGLISH).contains(currentFilter.toLowerCase(Locale.ENGLISH))).toList()*/ :
                registeredPixelmon;
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {

    }

    public record PixelmonWidgetData(RenderablePokemon data) {
        public String getName() {
            return data.getForm().getName() + " " + data.getSpecies().getName();
        }
    }
}