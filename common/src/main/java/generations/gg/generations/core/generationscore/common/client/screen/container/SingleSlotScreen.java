package generations.gg.generations.core.generationscore.common.client.screen.container;

import com.mojang.blaze3d.systems.RenderSystem;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.world.container.SingleSlotContainer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class SingleSlotScreen<T extends SingleSlotContainer> extends AbstractContainerScreen<T> {
    private final ResourceLocation CONTAINER_BACKGROUND = GenerationsCore.id("textures/gui/container/single_slot.png");

    public SingleSlotScreen(T arg, Inventory arg2, Component arg3) {
        super(arg, arg2, arg3);
    }
    @Override
    public void render(@NotNull GuiGraphics poseStack, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(poseStack, mouseX, mouseY, partialTick);
        super.render(poseStack, mouseX, mouseY, partialTick);
        this.renderTooltip(poseStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics poseStack, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        poseStack.blit(CONTAINER_BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
    }
}