package generations.gg.generations.core.generationscore.client.screen.npc.tabs;

import com.mojang.blaze3d.systems.RenderSystem;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.client.screen.ScreenUtils;
import generations.gg.generations.core.generationscore.client.screen.widget.DropdownWidget;
import generations.gg.generations.core.generationscore.client.screen.widget.FakeSlot;
import generations.gg.generations.core.generationscore.world.entity.PlayerNpcEntity;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.item.NpcPathTool;
import generations.gg.generations.core.generationscore.world.npc.display.MovementInfo;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;

import static net.minecraft.client.gui.screens.inventory.InventoryScreen.renderEntityInInventoryFollowsMouse;

public class MovementCustomizationTab extends CustomizationTab {

    private static final ResourceLocation TEXTURE = GenerationsCore.id("textures/gui/npc/movement.png");
    private static final ResourceLocation EMPTY_SLOT_PATH = GenerationsCore.id("textures/gui/npc/empty_slot_path.png");

    private float oldMouseX;
    private float oldMouseY;

    private AbstractWidget centerXTextField, centerYTextField, centerZTextField, radiusTextField,
            speedTextField, delayTextField,
            pathSlot;

    private ItemStack carried;
    protected ItemRenderer itemRenderer;

    public MovementCustomizationTab(PlayerNpcEntity npcEntity) {
        super(npcEntity);
        this.itemRenderer = minecraft.getItemRenderer();
    }

    @Override
    public void init(int x, int y) {
        super.init(x, y);
        this.carried = ItemStack.EMPTY;

        var playerInventory = minecraft.player.getInventory();

        ItemStack stackInPathSlot = npcEntity.getDisplayData().getMovementInfo().getType() == MovementInfo.MovementType.WANDER_PATH
                ? getItemFromPathInfo() : ItemStack.EMPTY;

        this.pathSlot = this.addRenderableWidget(new FakeSlot(x + 120, y + 58, stackInPathSlot, EMPTY_SLOT_PATH, widget -> {
            var slot = (FakeSlot)widget;
            if (carried.isEmpty()) {
                carried = slot.getItem();
                npcEntity.getDisplayData().getMovementInfo().setPath(Collections.emptyList());
                slot.setItem(ItemStack.EMPTY);
            } else if (carried.getItem() instanceof NpcPathTool) {
                slot.setItem(carried);
                npcEntity.getDisplayData().getMovementInfo().setPath(NpcPathTool.getPath(carried));
                carried = ItemStack.EMPTY;
            }
            npcEntity.updateDisplayData();
        }));
        this.pathSlot.setTooltip(Tooltip.create(Component.literal("Use the Npc Path Tool to define a path and put it in here! To get the item back, use /pokenpc path_to_item <npc_uuid>")));

        for(int l = 0; l < 3; ++l) {
            for(int j1 = 0; j1 < 9; ++j1) {
                this.addRenderableWidget(new FakeSlot(x + 48 + j1 * 18, y + 84 + l * 18, playerInventory.getItem(j1 + (l + 1) * 9), widget -> this.carried = ((FakeSlot)widget).getItem()));
            }
        }

        for(int i1 = 0; i1 < 9; ++i1) {
            this.addRenderableWidget(new FakeSlot(x + 48 + i1 * 18, y + 142, playerInventory.getItem(i1), widget -> this.carried = ((FakeSlot)widget).getItem()));
        }

        var origin = npcEntity.getDisplayData().getMovementInfo().getOrigin() == null
                ? npcEntity.blockPosition()
                : npcEntity.getDisplayData().getMovementInfo().getOrigin();
        Predicate<String> centerFilter = s -> {
            if (s.isEmpty() || s.equals("-")) {
                return true;
            }

            try {
                Integer.parseInt(s);
                return true;
            } catch (Exception ignored) {}

            return false;
        };
        this.centerXTextField = this.addRenderableWidget(ScreenUtils.createTextField(x + 48, y + 20,
                41, 13, 32, String.valueOf(origin.getX()),
                centerFilter,
                s -> {
                    if (s.isEmpty() || s.equals("-")) {
                        npcEntity.getDisplayData().getMovementInfo().setOriginX(origin.getX());
                    } else {
                        int i = Integer.parseInt(s);
                        npcEntity.getDisplayData().getMovementInfo().setOriginX(i);
                    }
                    npcEntity.updateDisplayData();
                }));
        this.centerYTextField = this.addRenderableWidget(ScreenUtils.createTextField(x + 99, y + 20,
                41, 13, 32, String.valueOf(origin.getY()),
                centerFilter,
                s -> {
                    if (s.isEmpty() || s.equals("-")) {
                        npcEntity.getDisplayData().getMovementInfo().setOriginY(origin.getY());
                    } else {
                        int i = Integer.parseInt(s);
                        npcEntity.getDisplayData().getMovementInfo().setOriginY(i);
                    }
                    npcEntity.updateDisplayData();
                }));
        this.centerZTextField = this.addRenderableWidget(ScreenUtils.createTextField(x + 150, y + 20,
                41, 13, 32, String.valueOf(origin.getZ()),
                centerFilter,
                s -> {
                    if (s.isEmpty() || s.equals("-")) {
                        npcEntity.getDisplayData().getMovementInfo().setOriginZ(origin.getZ());
                    } else {
                        int i = Integer.parseInt(s);
                        npcEntity.getDisplayData().getMovementInfo().setOriginZ(i);
                    }
                    npcEntity.updateDisplayData();
                }));

        this.delayTextField = this.addRenderableWidget(ScreenUtils.createTextField(x + 48, y + 37,
                41, 13, 32, String.valueOf(npcEntity.getDisplayData().getMovementInfo().getCooldownTicks()),
                s -> {
                    if (s.isEmpty()) {
                        return true;
                    }
                    try {
                        return Integer.parseInt(s) >= 0;
                    } catch (Exception ignored) {}

                    return false;
                },
                s -> {
                    if (s.isEmpty()) {
                        npcEntity.getDisplayData().getMovementInfo().setCooldownTicks(40);
                    } else {
                        int i = Integer.parseInt(s);
                        npcEntity.getDisplayData().getMovementInfo().setCooldownTicks(i);
                    }
                    npcEntity.updateDisplayData();
                }, Tooltip.create(Component.literal("Delay (in ticks) before moving onto the next position"))));

        this.speedTextField = this.addRenderableWidget(ScreenUtils.createTextField(x + 150, y + 37,
                41, 13, 32, String.valueOf(npcEntity.getDisplayData().getMovementInfo().getMoveSpeed()),
                s -> {
                    if (s.isEmpty()) {
                        return true;
                    }
                    try {
                        return Float.parseFloat(s) > 0;
                    } catch (Exception ignored) {}

                    return false;
                },
                s -> {
                    if (s.isEmpty()) {
                        npcEntity.getDisplayData().getMovementInfo().setMoveSpeed(1.0F);
                    } else {
                        float f = Float.parseFloat(s);
                        npcEntity.getDisplayData().getMovementInfo().setMoveSpeed(f);
                    }
                    npcEntity.updateDisplayData();
                }, Tooltip.create(Component.literal("Speed modifier"))));

        this.radiusTextField = this.addRenderableWidget(ScreenUtils.createTextField(x + 48, y + 54,
                41, 13, 32, String.valueOf(npcEntity.getDisplayData().getMovementInfo().getRadius()),
                s -> {
                    if (s.isEmpty()) {
                        return true;
                    }
                    try {
                        return Integer.parseInt(s) > 0;
                    } catch (Exception ignored) {}

                    return false;
                },
                s -> {
                    if (s.isEmpty()) {
                        npcEntity.getDisplayData().getMovementInfo().setRadius(12);
                    } else {
                        int i = Integer.parseInt(s);
                        npcEntity.getDisplayData().getMovementInfo().setRadius(i);
                    }
                    npcEntity.updateDisplayData();
                }, Tooltip.create(Component.literal("Radius (in blocks) from the center point in which the NPC will wander around"))));

        this.addRenderableWidget(new DropdownWidget(x + 47, y + 6, 146,
                Arrays.stream(MovementInfo.MovementType.values()).map(MovementInfo.MovementType::getName).toList(),
                npcEntity.getDisplayData().getMovementInfo().getTypeName(),
                (widget, type) -> {
                    var prevType = npcEntity.getDisplayData().getMovementInfo().getType();
                    npcEntity.getDisplayData().getMovementInfo().setType(type);
                    if (prevType != MovementInfo.MovementType.WANDER_RANDOMLY
                            && type.equals(MovementInfo.MovementType.WANDER_RANDOMLY.getName())) {
                        ((EditBox)this.centerXTextField).setValue(String.valueOf(npcEntity.getBlockX()));
                        ((EditBox)this.centerYTextField).setValue(String.valueOf(npcEntity.getBlockY()));
                        ((EditBox)this.centerZTextField).setValue(String.valueOf(npcEntity.getBlockZ()));
                        if (prevType == MovementInfo.MovementType.STILL) {
                            ((EditBox)this.delayTextField).setValue("40");
                            ((EditBox)this.speedTextField).setValue("1.0");
                            ((EditBox)this.radiusTextField).setValue("12");
                        }
                    }

                    if (prevType != MovementInfo.MovementType.WANDER_PATH
                            && type.equals(MovementInfo.MovementType.WANDER_PATH.getName())) {
                        npcEntity.getDisplayData().getMovementInfo().setPath(Collections.emptyList());
                        if (prevType == MovementInfo.MovementType.STILL) {
                            ((EditBox)this.delayTextField).setValue("40");
                            ((EditBox)this.speedTextField).setValue("1.0");
                        }
                    }

                    updateWidgetVisibility(type);
                    npcEntity.updateDisplayData();
                },
                this::hideAllWidgets,
                this::updateWidgetVisibility
        ));

        updateWidgetVisibility();
    }

    private ItemStack getItemFromPathInfo() {
        if (npcEntity.getDisplayData().getMovementInfo().getPath() == null
                || npcEntity.getDisplayData().getMovementInfo().getPath().size() == 0) {
            return ItemStack.EMPTY;
        }

        ItemStack stack = new ItemStack(GenerationsItems.NPC_PATH_TOOL.get());
        NpcPathTool.setPath(stack, npcEntity.getDisplayData().getMovementInfo().getPath());
        return stack;
    }

    private void updateWidgetVisibility() {
        updateWidgetVisibility(npcEntity.getDisplayData().getMovementInfo().getTypeName());
    }

    private void updateWidgetVisibility(String type) {
        boolean areaWidgetVisibility = type.equals(MovementInfo.MovementType.WANDER_RANDOMLY.getName());
        boolean patrolWidgetVisibility = type.equals(MovementInfo.MovementType.WANDER_PATH.getName());

        centerXTextField.active = areaWidgetVisibility;
        centerXTextField.visible = areaWidgetVisibility;
        centerYTextField.active = areaWidgetVisibility;
        centerYTextField.visible = areaWidgetVisibility;
        centerZTextField.active = areaWidgetVisibility;
        centerZTextField.visible = areaWidgetVisibility;
        radiusTextField.active = areaWidgetVisibility;
        radiusTextField.visible = areaWidgetVisibility;
        speedTextField.active = areaWidgetVisibility || patrolWidgetVisibility;
        speedTextField.visible = areaWidgetVisibility || patrolWidgetVisibility;
        delayTextField.active = areaWidgetVisibility || patrolWidgetVisibility;
        delayTextField.visible = areaWidgetVisibility || patrolWidgetVisibility;
        pathSlot.active = patrolWidgetVisibility;
        pathSlot.visible = patrolWidgetVisibility;
    }

    private void hideAllWidgets() {
        centerXTextField.active = false;
        centerXTextField.visible = false;
        centerYTextField.active = false;
        centerYTextField.visible = false;
        centerZTextField.active = false;
        centerZTextField.visible = false;
        radiusTextField.active = false;
        radiusTextField.visible = false;
        speedTextField.active = false;
        speedTextField.visible = false;
        delayTextField.active = false;
        delayTextField.visible = false;
        pathSlot.active = false;
        pathSlot.visible = false;
    }

    @Override
    public void tick() {

    }

    @Override
    public void preRender(GuiGraphics poseStack, int mouseX, int mouseY, float partialTick) {
        poseStack.blit(TEXTURE, x, y, 0, 0, 256, 166, 256, 256);
        float bbHeight = npcEntity.getRenderedEntity() == null ? 1.8f : Math.max(npcEntity.getBbHeight(), 1f);
        int scale = (int) (54 / bbHeight);
        renderEntityInInventoryFollowsMouse(poseStack, x + 225, y + 74, scale, (float) (x + 225) - this.oldMouseX, (float) (y + 74 - 50) - this.oldMouseY, npcEntity);
        RenderSystem.disableDepthTest();

        poseStack.drawString(font, "Type:", x + 7, y + 8, 0x5F5F60, false);

        if (centerXTextField.visible) {
            poseStack.drawString(font, "Center:", x + 7, y + 23, 0x5F5F60, false);
            poseStack.drawString(font, "x", x + 91, y + 29, 0x5F5F60, false);
            poseStack.drawString(font, "y", x + 142, y + 29, 0x5F5F60, false);
            poseStack.drawString(font, "z", x + 193, y + 29, 0x5F5F60, false);
        }
        if (delayTextField.visible) {
            poseStack.drawString(font, "Delay:", x + 7, y + 40, 0x5F5F60, false);
        }
        if (speedTextField.visible) {
            poseStack.drawString(font, "Speed:", x + 110, y + 40, 0x5F5F60, false);
        }
        if (radiusTextField.visible) {
            poseStack.drawString(font, "Radius:", x + 7, y + 57, 0x5F5F60, false);
        }
        if (pathSlot.visible) {
            poseStack.drawString(font, "Path:", x + 90, y + 62, 0x5F5F60, false);
            poseStack.blit(TEXTURE, pathSlot.getX() - 1, pathSlot.getY() - 1, 0, 166, 18, 18, 256, 256);
        }
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

        guiGraphics.pose().popPose();
    }
}