package generations.gg.generations.core.generationscore.client.screen.container;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.util.Pair;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.client.screen.Anchor;
import generations.gg.generations.core.generationscore.client.screen.SubTexture;
import generations.gg.generations.core.generationscore.world.container.MachineBlockContainer;
import generations.gg.generations.core.generationscore.world.container.slots.TypeSlot;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.DoubleSupplier;

public class MachineBlockScreen extends AbstractContainerScreen<MachineBlockContainer> {
    public static final ResourceLocation TEXTURE = GenerationsCore.id("textures/gui/container/machine_block.png");
    public static final SubTexture ARCEUS_SYMBOL = new SubTexture(0, 0, 94, 74, GenerationsCore.id("textures/gui/container/arceus_symbol.png"), 94);
    private final DoubleSupplier bakeTimeProvider = () -> getMenu().getBakeTime();
    public MachineBlockScreen(MachineBlockContainer arg, Inventory arg2, Component arg3) {
        super(arg, arg2, arg3);

        imageHeight = 255;
        imageWidth = 176;
    }

    @Override
    public void render(@NotNull GuiGraphics poseStack, int mouseX, int mouseY, float partialTick) {
        this.menu.slots.stream().filter(a -> a instanceof TypeSlot).forEach(a -> {
           TypeSlot.interpolateVectors((TypeSlot) a, 0, 0, 80, 80, bakeTimeProvider.getAsDouble());
        });

        this.renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, partialTick);
        this.renderTooltip(poseStack, mouseX, mouseY);
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics poseStack, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

        poseStack.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
        double percent = bakeTimeProvider.getAsDouble();

        ARCEUS_SYMBOL.renderAnchoredStretched(poseStack, this.leftPos + 88, this.topPos + 88, (float) percent, (float) percent, Anchor.CENTER_CENTER);
    }

    @Override
    protected void renderLabels(@NotNull GuiGraphics poseStack, int mouseX, int mouseY) {
    }

//    public void renderSlot(@NotNull PoseStack poseStack, @NotNull Slot slot) {
//        Pair<ResourceLocation, ResourceLocation> pair;
//        boolean isTypeSlot = slot instanceof TypeSlot;
//        if(isTypeSlot) TypeSlot.interpolateVectors((TypeSlot) slot, slot.x, slot.y, 80, 80, bakeTimeProvider.getAsDouble());
//        int x = isTypeSlot ? ((TypeSlot) slot).offsetX : slot.x;
//        int y = isTypeSlot ? ((TypeSlot) slot).offsetY : slot.y;
//
//        var itemstack = slot.getItem();
//        var flag = false;
//        var flag1 = slot == this.clickedSlot && !this.draggingItem.isEmpty() && !this.isSplittingStack;
//        var itemstack1 = this.menu.getCarried();
//        String s = null;
//
//        if (slot == this.clickedSlot && !this.draggingItem.isEmpty() && this.isSplittingStack && !itemstack.isEmpty()) {
//            itemstack = itemstack.copy();
//            itemstack.setCount(itemstack.getCount() / 2);
//        } else if (this.isQuickCrafting && this.quickCraftSlots.contains(slot) && !itemstack1.isEmpty()) {
//            if (this.quickCraftSlots.size() == 1) {
//                return;
//            }
//            if (AbstractContainerMenu.canItemQuickReplace(slot, itemstack1, true) && this.menu.canDragTo(slot)) {
//                itemstack = itemstack1.copy();
//                flag = true;
//                AbstractContainerMenu.getQuickCraftSlotCount(this.quickCraftSlots, this.quickCraftingType, itemstack, slot.getItem().isEmpty() ? 0 : slot.getItem().getCount());
//                int k = Math.min(itemstack.getMaxStackSize(), slot.getMaxStackSize(itemstack));
//                if (itemstack.getCount() > k) {
//                    s = ChatFormatting.YELLOW.toString() + k;
//                    itemstack.setCount(k);
//                }
//            } else {
//                this.quickCraftSlots.remove(slot);
//                this.recalculateQuickCraftRemaining();
//            }
//        }
//        if (itemstack.isEmpty() && slot.isActive() && (pair = slot.getNoItemIcon()) != null) {
//            TextureAtlasSprite textureatlassprite = this.minecraft.getTextureAtlas(pair.getFirst()).apply(pair.getSecond());
//            RenderSystem.setShaderTexture(0, textureatlassprite.atlasLocation());
//            RenderSystem.enableBlend();
//            RenderSystem.defaultBlendFunc();
//            AbstractContainerScreen.blit(poseStack, x, y, /*this.getBlitOffset()*/ 0, 16, 16, textureatlassprite);
//            RenderSystem.disableBlend();
//            flag1 = true;
//        }
//        if (!flag1) {
//            if (flag) {
//                AbstractContainerScreen.fill(poseStack, x, y, x + 16, y + 16, -2130706433);
//            }
//            RenderSystem.enableDepthTest();
//            assert this.minecraft != null;
//            this.itemRenderer.renderAndDecorateItem(poseStack, this.minecraft.player, itemstack, x, y, slot.x + slot.y * this.imageWidth);
//            this.itemRenderer.renderGuiItemDecorations(poseStack, this.font, itemstack, x, y, s);
//        }
//    }
//
//    private void renderSlot(GuiGraphics guiGraphics, Slot slot) {
//        int i = slot.x;
//        int j = slot.y;
//        ItemStack itemStack = slot.getItem();
//        boolean bl = false;
//        boolean bl2 = slot == this.clickedSlot && !this.draggingItem.isEmpty() && !this.isSplittingStack;
//        ItemStack itemStack2 = this.menu.getCarried();
//        String string = null;
//        if (slot == this.clickedSlot && !this.draggingItem.isEmpty() && this.isSplittingStack && !itemStack.isEmpty()) {
//            itemStack = itemStack.copyWithCount(itemStack.getCount() / 2);
//        } else if (this.isQuickCrafting && this.quickCraftSlots.contains(slot) && !itemStack2.isEmpty()) {
//            if (this.quickCraftSlots.size() == 1) {
//                return;
//            }
//
//            if (AbstractContainerMenu.canItemQuickReplace(slot, itemStack2, true) && this.menu.canDragTo(slot)) {
//                bl = true;
//                int k = Math.min(itemStack2.getMaxStackSize(), slot.getMaxStackSize(itemStack2));
//                int l = slot.getItem().isEmpty() ? 0 : slot.getItem().getCount();
//                int m = AbstractContainerMenu.getQuickCraftPlaceCount(this.quickCraftSlots, this.quickCraftingType, itemStack2) + l;
//                if (m > k) {
//                    m = k;
//                    String var10000 = ChatFormatting.YELLOW.toString();
//                    string = var10000 + k;
//                }
//
//                itemStack = itemStack2.copyWithCount(m);
//            } else {
//                this.quickCraftSlots.remove(slot);
//                this.recalculateQuickCraftRemaining();
//            }
//        }
//
//        guiGraphics.pose().pushPose();
//        guiGraphics.pose().translate(0.0F, 0.0F, 100.0F);
//        if (itemStack.isEmpty() && slot.isActive()) {
//            Pair<ResourceLocation, ResourceLocation> pair = slot.getNoItemIcon();
//            if (pair != null) {
//                TextureAtlasSprite textureAtlasSprite = (TextureAtlasSprite)this.minecraft.getTextureAtlas((ResourceLocation)pair.getFirst()).apply((ResourceLocation)pair.getSecond());
//                guiGraphics.blit(i, j, 0, 16, 16, textureAtlasSprite);
//                bl2 = true;
//            }
//        }
//
//        if (!bl2) {
//            if (bl) {
//                guiGraphics.fill(i, j, i + 16, j + 16, -2130706433);
//            }
//
//            guiGraphics.renderItem(itemStack, i, j, slot.x + slot.y * this.imageWidth);
//            guiGraphics.renderItemDecorations(this.font, itemStack, i, j, string);
//        }
//
//        guiGraphics.pose().popPose();
//    }
}
