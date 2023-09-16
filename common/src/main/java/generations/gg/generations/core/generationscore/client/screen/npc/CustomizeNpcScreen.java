package generations.gg.generations.core.generationscore.client.screen.npc;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.client.screen.npc.tabs.*;
import generations.gg.generations.core.generationscore.client.screen.widget.FakeSlot;
import generations.gg.generations.core.generationscore.client.screen.widget.RadioButton;
import generations.gg.generations.core.generationscore.network.GenerationsNetwork;
import generations.gg.generations.core.generationscore.network.packets.npc.C2SUpdateNpcDisplayDataPacket;
import generations.gg.generations.core.generationscore.util.NpcUtils;
import generations.gg.generations.core.generationscore.world.entity.PlayerNpcEntity;
import generations.gg.generations.core.generationscore.world.item.GenerationsArmor;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsUtilityBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

//@OnlyIn(Dist.CLIENT)
public class CustomizeNpcScreen extends Screen {

    private static final ResourceLocation TEXTURE = GenerationsCore.id("textures/gui/npc/customization.png");

    private int x, y;
    private int selectedTabId;
    private final Tab[] tabs;

    private final PlayerNpcEntity npcEntity;

    public CustomizeNpcScreen(PlayerNpcEntity npcEntity) {
        super(Component.literal("Customize NPC"));
        NpcUtils.loadValidEntityTypes(Minecraft.getInstance().level);
        this.npcEntity = npcEntity;
        this.tabs = new Tab[] {
                new Tab(new DisplayCustomizationTab(npcEntity), new ItemStack(Items.SPYGLASS), "Display"),
                new Tab(new HeldItemCustomizationTab(npcEntity), new ItemStack(GenerationsArmor.ROCKET.chestplate().get()), "Held Items"),
                new Tab(new MovementCustomizationTab(npcEntity), new ItemStack(GenerationsItems.NEST_BALL.get()/*.RED_BIKE.get()*/), "Movement"),
                new Tab(new PresetCustomizationTab(npcEntity), new ItemStack(GenerationsUtilityBlocks.RED_PC.get()), "Presets")
        };
        selectTab(0);
    }

    private void selectTab(int id) {
        this.selectedTabId = id;
        init();
    }

    @Override
    protected void init() {
        this.x = width / 2 - 128;
        this.y = height / 2 - 83;
        clearWidgets();
        var selectedTab = tabs[selectedTabId].tab;
        selectedTab.init(x, y);
        for (GuiEventListener r : selectedTab.getChildren()) {
            this.addRenderableWidget((AbstractWidget)r);
        }
        this.addRenderableWidget(new RadioButton(x + 2, y - 27, 28, 27,
                TEXTURE, 228, 166, RadioButton.Orientation.HORIZONTAL, 0, selectedTabId, getTabs()
        ));
    }

    private RadioButton.RadioOption[] getTabs() {
        var options = new RadioButton.RadioOption[tabs.length];
        for (int i = 0; i < tabs.length; i++) {
            int finalI = i;
            options[i] = new RadioButton.RadioOption(null, Tooltip.create(Component.literal(tabs[i].tooltip)), () -> selectTab(finalI));
        }
        return options;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void tick() {
        tabs[selectedTabId].tab.tick();
    }

    @Override
    public void render(GuiGraphics poseStack, int mouseX, int mouseY, float partialTick) {
        var selectedTab = tabs[selectedTabId];
        selectedTab.tab.preRender(poseStack, mouseX, mouseY, partialTick);
        super.render(poseStack, mouseX, mouseY, partialTick);
        selectedTab.tab.postRender(poseStack, mouseX, mouseY, partialTick);

        for (int i = 0; i < tabs.length; i++) {
            poseStack.renderFakeItem(tabs[i].icon, x + 8 + i * 28, y - 20);

            var children = tabs[i].tab.getChildren();
            for (var child : children) {
                if (child instanceof FakeSlot slot && slot.isHoveredOrFocused()) {
                    if (!slot.hasTooltip() && !slot.getItem().isEmpty())
                        poseStack.renderTooltip(font, slot.getItem(), mouseX, mouseY);
                }
            }
        }
    }

    @Override
    public void onClose() {
        GenerationsNetwork.INSTANCE.sendToServer(new C2SUpdateNpcDisplayDataPacket(npcEntity.getId(), npcEntity.getDisplayData()));
        super.onClose();
    }

    private record Tab(CustomizationTab tab, ItemStack icon, String tooltip) {
    }
}