package generations.gg.generations.core.generationscore.client.screen.npc.tabs;

import com.mojang.blaze3d.systems.RenderSystem;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.client.screen.widget.DropdownWidget;
import generations.gg.generations.core.generationscore.client.screen.widget.FakeSlot;
import generations.gg.generations.core.generationscore.util.GenerationsItemUtils;
import generations.gg.generations.core.generationscore.world.entity.PlayerNpcEntity;
import generations.gg.generations.core.generationscore.world.npc.display.NpcDisplayData;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

import java.util.Arrays;

import static net.minecraft.client.gui.screens.inventory.InventoryScreen.renderEntityInInventoryFollowsMouse;

public class HeldItemCustomizationTab extends CustomizationTab {

    private static final ResourceLocation[] ARMOR_SLOT_TEXTURES = new ResourceLocation[]{GenerationsCore.id("textures/gui/npc/empty_armor_slot_boots.png"), GenerationsCore.id("textures/gui/npc/empty_armor_slot_leggings.png"), GenerationsCore.id("textures/gui/npc/empty_armor_slot_chestplate.png"), GenerationsCore.id("textures/gui/npc/empty_armor_slot_helmet.png")};
    private static final ResourceLocation TEXTURE = GenerationsCore.id("textures/gui/npc/held_items.png");
    private static final ResourceLocation EMPTY_ARMOR_SLOT_SWORD = GenerationsCore.id("textures/gui/npc/empty_armor_slot_sword.png");
    private static final ResourceLocation EMPTY_ARMOR_SLOT_SHIELD = GenerationsCore.id("textures/gui/npc/empty_armor_slot_shield.png");
    private static final EquipmentSlot[] VALID_EQUIPMENT_SLOTS = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};

    private float oldMouseX;
    private float oldMouseY;

    private ItemStack carried;

    protected ItemRenderer itemRenderer;

    public HeldItemCustomizationTab(PlayerNpcEntity npcEntity) {
        super(npcEntity);
        this.itemRenderer = minecraft.getItemRenderer();
    }

    @Override
    public void init(int x, int y) {
        super.init(x, y);
        this.carried = ItemStack.EMPTY;

        assert minecraft.player != null;
        var playerInventory = minecraft.player.getInventory();

        this.addRenderableWidget(new FakeSlot(x + 210, y + 43, npcEntity.getMainHandItem(), EMPTY_ARMOR_SLOT_SWORD, widget -> {
            var slot = (FakeSlot)widget;
            if (this.carried == ItemStack.EMPTY || this.carried == null) {
                this.carried = slot.getItem();
                slot.setItem(ItemStack.EMPTY);
                npcEntity.getDisplayData().getHeldItemsInfo().setHeldItem(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
                npcEntity.updateDisplayData();
            } else {
                slot.setItem(carried);
                npcEntity.getDisplayData().getHeldItemsInfo().setHeldItem(EquipmentSlot.MAINHAND, carried);
                npcEntity.updateDisplayData();
                this.carried = ItemStack.EMPTY;
            }
        }));

        this.addRenderableWidget(new FakeSlot(x + 210, y + 61, npcEntity.getOffhandItem(), EMPTY_ARMOR_SLOT_SHIELD, widget -> {
            var slot = (FakeSlot)widget;
            if (this.carried == ItemStack.EMPTY || this.carried == null) {
                this.carried = slot.getItem();
                slot.setItem(ItemStack.EMPTY);
                npcEntity.getDisplayData().getHeldItemsInfo().setHeldItem(EquipmentSlot.OFFHAND, ItemStack.EMPTY);
                npcEntity.updateDisplayData();
            } else {
                slot.setItem(carried);
                npcEntity.getDisplayData().getHeldItemsInfo().setHeldItem(EquipmentSlot.OFFHAND, carried);
                npcEntity.updateDisplayData();
                this.carried = ItemStack.EMPTY;
            }
        }));

        for (int k = 0; k < 4; ++k) {
            final EquipmentSlot equipmentslottype = VALID_EQUIPMENT_SLOTS[k];
            this.addRenderableWidget(new FakeSlot(x + 141, y + 7 + k * 18, npcEntity.getItemBySlot(equipmentslottype), ARMOR_SLOT_TEXTURES[equipmentslottype.getIndex()], widget -> {
                var slot = (FakeSlot)widget;
                if (this.carried == ItemStack.EMPTY || this.carried == null) {
                    this.carried = slot.getItem();
                    slot.setItem(ItemStack.EMPTY);
                    npcEntity.getDisplayData().getHeldItemsInfo().setHeldItem(equipmentslottype, ItemStack.EMPTY);
                    npcEntity.updateDisplayData();
                } else if (GenerationsItemUtils.canEquip(carried, equipmentslottype, npcEntity)) {
                    slot.setItem(carried);
                    npcEntity.getDisplayData().getHeldItemsInfo().setHeldItem(equipmentslottype, carried);
                    npcEntity.updateDisplayData();
                    this.carried = ItemStack.EMPTY;
                }
            }));
        }

        for(int l = 0; l < 3; ++l) {
            for(int j1 = 0; j1 < 9; ++j1) {
                this.addRenderableWidget(new FakeSlot(x + 48 + j1 * 18, y + 84 + l * 18, playerInventory.getItem(j1 + (l + 1) * 9), widget -> this.carried = ((FakeSlot)widget).getItem()));
            }
        }

        for(int i1 = 0; i1 < 9; ++i1) {
            this.addRenderableWidget(new FakeSlot(x + 48 + i1 * 18, y + 142, playerInventory.getItem(i1), widget -> this.carried = ((FakeSlot)widget).getItem()));
        }

        this.addRenderableWidget(new DropdownWidget(x + 61, y + 6, 70,
                Arrays.stream(NpcDisplayData.Collision.values()).map(NpcDisplayData.Collision::getName).toList(),
                npcEntity.getDisplayData().getCollisionName(),
                (widget, collision) -> {
                    npcEntity.getDisplayData().setCollision(NpcDisplayData.Collision.getOrDefault(collision, npcEntity.getDisplayData().getCollision()));
                    npcEntity.updateDisplayData();
                },
                () -> {},
                () -> {}));
    }

    @Override
    public void tick() {

    }

    @Override
    public void preRender(GuiGraphics poseStack, int mouseX, int mouseY, float partialTick) {
        poseStack.setColor(1f, 1f, 1f, 1f);
        poseStack.blit(TEXTURE, x, y, 0, 0, 256, 166, 256, 256);
        float bbHeight = npcEntity.getRenderedEntity() == null ? 1.8f : Math.max(npcEntity.getBbHeight(), 1f);
        int scale = (int) (54 / bbHeight);
        renderEntityInInventoryFollowsMouse(poseStack, x + 184, y + 74, scale, (float) (x + 184) - this.oldMouseX, (float) (y + 74 - 50) - this.oldMouseY, npcEntity);
        RenderSystem.disableDepthTest();
        poseStack.setColor(1.0F, 1.0F, 1.0F, 1.0F);
        poseStack.drawString(font, "Collision:", x + 7, y + 8, 0x5F5F60, false);
    }

    @Override
    public void postRender(GuiGraphics poseStack, int mouseX, int mouseY, float partialTick) {
        ItemStack itemstack = this.carried;
        if (!itemstack.isEmpty()) {
            this.renderFloatingItem(poseStack, itemstack, mouseX - 8, mouseY - 8, null);
        }

        RenderSystem.enableDepthTest();

        this.oldMouseX = (float)mouseX;
        this.oldMouseY = (float)mouseY;
    }

    private void renderFloatingItem(GuiGraphics guiGraphics, ItemStack stack, int x, int y, String altText) {
//        PoseStack posestack = RenderSystem.getModelViewStack();
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(0.0F, 0.0F, 32.0F);
//        RenderSystem.applyModelViewMatrix();

        Font font = this.font;

        guiGraphics.renderItem(stack, x, y);
        guiGraphics.renderItemDecorations(font, stack, x, y - (this.carried.isEmpty() ? 0 : 8), altText);

        guiGraphics.pose().popPose();;
    }
}