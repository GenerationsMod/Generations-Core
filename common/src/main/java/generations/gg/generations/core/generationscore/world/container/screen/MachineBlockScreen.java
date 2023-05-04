package generations.gg.generations.core.generationscore.world.container.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.util.Pair;
import com.pokemod.pokemod.PokeMod;
import com.pokemod.pokemod.client.screen.Anchor;
import com.pokemod.pokemod.client.screen.SubTexture;
import generations.gg.generations.core.generationscore.world.container.MachineBlockContainer;
import generations.gg.generations.core.generationscore.world.container.slots.TypeSlot;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import org.jetbrains.annotations.NotNull;

import java.util.function.DoubleSupplier;

public class MachineBlockScreen extends AbstractContainerScreen<MachineBlockContainer> {
    public static final ResourceLocation TEXTURE = new ResourceLocation("pokemod:textures/gui/container/machine_block.png");
    public static final SubTexture ARCEUS_SYMBOL = new SubTexture(0, 0, 94, 74, PokeMod.id("textures/gui/container/arceus_symbol.png"), 94);
    private final DoubleSupplier bakeTimeProvider = () -> getMenu().getBakeTime();
    public MachineBlockScreen(MachineBlockContainer arg, Inventory arg2, Component arg3) {
        super(arg, arg2, arg3);

        imageHeight = 255;
        imageWidth = 176;
    }

    @Override
    public void render(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, partialTick);
        this.renderTooltip(poseStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(@NotNull PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE);
        this.blit(poseStack, this.leftPos, this.topPos, 0, 0, this.getXSize(), this.getYSize());
        double percent = bakeTimeProvider.getAsDouble();

        ARCEUS_SYMBOL.renderAnchoredStretched(poseStack, this.leftPos + 88, this.topPos + 88, (float) percent, (float) percent, Anchor.CENTER_CENTER);
    }

    @Override
    protected void renderLabels(@NotNull PoseStack poseStack, int mouseX, int mouseY) {
    }

    public void renderSlot(@NotNull PoseStack poseStack, @NotNull Slot slot) {
        Pair<ResourceLocation, ResourceLocation> pair;
        boolean isTypeSlot = slot instanceof TypeSlot;
        if(isTypeSlot) TypeSlot.interpolateVectors((TypeSlot) slot, slot.x, slot.y, 80, 80, bakeTimeProvider.getAsDouble());
        int x = isTypeSlot ? ((TypeSlot) slot).offsetX : slot.x;
        int y = isTypeSlot ? ((TypeSlot) slot).offsetY : slot.y;

        var itemstack = slot.getItem();
        var flag = false;
        var flag1 = slot == this.clickedSlot && !this.draggingItem.isEmpty() && !this.isSplittingStack;
        var itemstack1 = this.menu.getCarried();
        String s = null;
        if (slot == this.clickedSlot && !this.draggingItem.isEmpty() && this.isSplittingStack && !itemstack.isEmpty()) {
            itemstack = itemstack.copy();
            itemstack.setCount(itemstack.getCount() / 2);
        } else if (this.isQuickCrafting && this.quickCraftSlots.contains(slot) && !itemstack1.isEmpty()) {
            if (this.quickCraftSlots.size() == 1) {
                return;
            }
            if (AbstractContainerMenu.canItemQuickReplace(slot, itemstack1, true) && this.menu.canDragTo(slot)) {
                itemstack = itemstack1.copy();
                flag = true;
                AbstractContainerMenu.getQuickCraftSlotCount(this.quickCraftSlots, this.quickCraftingType, itemstack, slot.getItem().isEmpty() ? 0 : slot.getItem().getCount());
                int k = Math.min(itemstack.getMaxStackSize(), slot.getMaxStackSize(itemstack));
                if (itemstack.getCount() > k) {
                    s = ChatFormatting.YELLOW.toString() + k;
                    itemstack.setCount(k);
                }
            } else {
                this.quickCraftSlots.remove(slot);
                this.recalculateQuickCraftRemaining();
            }
        }
//        this.setBlitOffset(100);
//        this.itemRenderer.blitOffset = 100.0f;
        if (itemstack.isEmpty() && slot.isActive() && (pair = slot.getNoItemIcon()) != null) {
            TextureAtlasSprite textureatlassprite = this.minecraft.getTextureAtlas(pair.getFirst()).apply(pair.getSecond());
            RenderSystem.setShaderTexture(0, textureatlassprite.atlasLocation());
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            AbstractContainerScreen.blit(poseStack, x, y, /*this.getBlitOffset()*/ 0, 16, 16, textureatlassprite);
            RenderSystem.disableBlend();
            flag1 = true;
        }
        if (!flag1) {
            if (flag) {
                AbstractContainerScreen.fill(poseStack, x, y, x + 16, y + 16, -2130706433);
            }
            RenderSystem.enableDepthTest();
            this.itemRenderer.renderAndDecorateItem(poseStack, this.minecraft.player, itemstack, x, y, slot.x + slot.y * this.imageWidth);
            this.itemRenderer.renderGuiItemDecorations(poseStack, this.font, itemstack, x, y, s);
        }
//        this.itemRenderer.blitOffset = 0.0f;
//        this.setBlitOffset(0);
    }
}
