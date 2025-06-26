package generations.gg.generations.core.generationscore.common.client.screen.container;

import com.cobblemon.mod.common.Cobblemon;
import com.mojang.blaze3d.systems.RenderSystem;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.world.container.CookingPotContainer;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.CookingPotBlockEntity;
import generations.gg.generations.core.generationscore.common.network.packets.C2STogglePacket;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class CookingPotScreen extends AbstractContainerScreen<CookingPotContainer> {
    public static final ResourceLocation cookingPotGuiTextures = GenerationsCore.id("textures/gui/container/cooking_pot.png");

    public CookingPotScreen(CookingPotContainer container, Inventory playerInventory, Component title) {
        super(container, playerInventory, title);
        this.inventoryLabelY += 21;
        imageHeight = 186;
        imageWidth = 176;
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
        poseStack.blit(cookingPotGuiTextures, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);

        int l = this.getCookProgressScaled();
        poseStack.blit(cookingPotGuiTextures, this.leftPos + 105, this.topPos + 24, 176, 18, l + 1, 16);

        if(getMenu().isCooking()) {
            poseStack.blit(cookingPotGuiTextures, this.leftPos + 106, this.topPos + 45, 176, 0, 20, 18);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if(findPoint(leftPos + 106, topPos + 45, leftPos + 126, topPos + 63, mouseX, mouseY)) {
            Cobblemon.INSTANCE.getImplementation().getNetworkManager().sendToServer(new C2STogglePacket());
            return true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    static boolean findPoint(double x1, double y1, double x2, double y2, double x, double y) {
        return x > x1 && x < x2 && y > y1 && y < y2;
    }

    private int getCookProgressScaled() {
        int i = this.getMenu().getCookTime();
        return i != 0 ? i * 24 / 200 : 0;
    }
}
