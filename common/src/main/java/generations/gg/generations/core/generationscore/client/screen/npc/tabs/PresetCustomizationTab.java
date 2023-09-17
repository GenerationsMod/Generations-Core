package generations.gg.generations.core.generationscore.client.screen.npc.tabs;

import com.mojang.serialization.JsonOps;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.client.screen.ScreenUtils;
import generations.gg.generations.core.generationscore.client.screen.widget.DropdownWidget;
import generations.gg.generations.core.generationscore.network.GenerationsNetwork;
import generations.gg.generations.core.generationscore.network.packets.dialogue.C2SSaveDatapackEntryPacket;
import generations.gg.generations.core.generationscore.network.packets.npc.C2SSetNpcPresetPacket;
import generations.gg.generations.core.generationscore.world.entity.PlayerNpcEntity;
import generations.gg.generations.core.generationscore.world.npc.NpcPreset;
import generations.gg.generations.core.generationscore.world.npc.NpcPresets;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PresetCustomizationTab extends CustomizationTab {

    private static final ResourceLocation TEXTURE = GenerationsCore.id("textures/gui/npc/customization.png");

    private int errorTick;
    private String newPresetName, errorMessage;
    private AbstractWidget nameTextField, createPresetButton;

    public PresetCustomizationTab(PlayerNpcEntity npcEntity) {
        super(npcEntity);
        this.newPresetName = "";
    }

    @Override
    public void init(int x, int y) {
        super.init(x, y);

        var nameTextField = this.addRenderableWidget(new EditBox(font, x + 46, y + 22, 150, 14, Component.empty()));
        nameTextField.setMaxLength(30);
        nameTextField.setValue(newPresetName);
        nameTextField.setResponder(s -> this.newPresetName = s);
        this.nameTextField = this.addRenderableWidget(ScreenUtils.createTextField(x + 46, y + 22,
                150, 14, 32, newPresetName,
                s -> s.matches("^\\w*$"), // matches [a-zA-Z0-9_] 0 or more times from start to end of string
                s -> this.newPresetName = s));

        this.createPresetButton = this.addRenderableWidget(new Button.Builder(Component.literal("Create Preset"), btn -> this.saveDataPackEntry())
                .bounds(x + 45, y + 43, 152, 20)
                .build());


        var locations = NpcPresets.instance().getIds();
        Map<String, ResourceLocation> presetMap = new HashMap<>();
        List<String> presets = locations.stream().map(resourceLocation -> {
            if (locations.stream().filter(r -> r.getPath().equals(resourceLocation.getPath())).count() == 1) {
                presetMap.put(resourceLocation.getPath(), resourceLocation);
                return resourceLocation.getPath();
            } else {
                presetMap.put(resourceLocation.toString(), resourceLocation);
                return resourceLocation.toString();
            }
        }).sorted((s1, s2) -> {
            boolean s1hasColon = s1.contains(":");
            boolean s2hasColon = s2.contains(":");
            if (s1hasColon && !s2hasColon) {
                return 1;
            }
            if (!s1hasColon && s2hasColon) {
                return -1;
            }
            return s1.compareTo(s2);
        }).collect(Collectors.toList());
        String createPresetString = "Create new preset...";
        presets.add(0, createPresetString);

        this.addRenderableWidget(new DropdownWidget(x + 54, y + 6, 143, presets, presets.get(0),
                (widget, preset) -> {
                    if (!preset.equals(createPresetString)) {
                        new C2SSetNpcPresetPacket(npcEntity.getId(), presetMap.get(preset)).sendToServer();
                    }
                    widget.select(0);
                },
                () -> {
                    this.nameTextField.visible = false;
                    this.createPresetButton.visible = false;
                },
                () -> {
                    this.nameTextField.visible = true;
                    this.createPresetButton.visible = true;
                }));
    }

    @Override
    public void tick() {
        if (this.errorTick > 0) {
            this.errorTick--;
        }
    }

    @Override
    public void preRender(GuiGraphics poseStack, int mouseX, int mouseY, float partialTick) {
        poseStack.pose().pushPose();
        {
            poseStack.pose().translate(x, y, 0.0);
            poseStack.blit(TEXTURE, 0, 0, 0, 0, 256, 166, 256, 256);
        }
        poseStack.pose().popPose();
        float bbHeight = npcEntity.getRenderedEntity() == null ? 1.8f : Math.max(npcEntity.getBbHeight(), 1f);
        int scale = (int) (54 / bbHeight);
        InventoryScreen.renderEntityInInventoryFollowsMouse(poseStack, x + 225, y + 74, scale, 0, 0, npcEntity);
        poseStack.drawString(font, "Preset:", x + 7, y + 8, 0x5F5F60, false);
        if (this.nameTextField.visible) {
            poseStack.drawString(font, "Name:", x + 7, y + 26, 0x5F5F60, false);
        }
        if (this.errorTick > 0) {
            ScreenUtils.drawCenteredString(poseStack, font, this.errorMessage, x + 128, y + 86, 0xFF0000, false);
        }
    }

    @Override
    public void postRender(GuiGraphics poseStack, int mouseX, int mouseY, float partialTick) {

    }

    private void saveDataPackEntry() {
        var encoder = JsonOps.INSTANCE.withEncoder(NpcPreset.CODEC);
        var data = encoder.apply(this.npcEntity.toPreset()).getOrThrow(false, this::warnCouldntSave);
        if (this.newPresetName.isBlank()) {
            this.errorTick = 150;
            this.errorMessage = "Preset name must not be blank!";
            return;
        }
        this.errorTick = 0;
        ((EditBox)this.nameTextField).setValue("");
        GenerationsNetwork.INSTANCE.sendToServer(new C2SSaveDatapackEntryPacket(GenerationsCore.id("npc_presets/"+this.newPresetName+".json"), data));
    }

    private void warnCouldntSave(String s) {
        this.errorTick = 150;
        this.errorMessage = "Failed to save preset! Check your logs for more info!";
        GenerationsCore.LOGGER.error(s);
    }

}