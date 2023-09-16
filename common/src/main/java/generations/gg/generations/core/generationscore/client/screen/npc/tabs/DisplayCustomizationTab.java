package generations.gg.generations.core.generationscore.client.screen.npc.tabs;

import com.cobblemon.mod.common.pokemon.Pokemon;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.client.screen.ScreenUtils;
import generations.gg.generations.core.generationscore.client.screen.widget.*;
import generations.gg.generations.core.generationscore.util.NpcUtils;
import generations.gg.generations.core.generationscore.world.entity.PlayerNpcEntity;
import generations.gg.generations.core.generationscore.world.npc.display.RotationInfo;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Pose;

import java.util.Arrays;
import java.util.List;

public class DisplayCustomizationTab extends CustomizationTab {

    private static final ResourceLocation TEXTURE = GenerationsCore.id("textures/gui/npc/customization.png");

    private final CompoundTag emptyTag = new CompoundTag();

    private AbstractWidget modelDropdownWidget, scaleTextField, rotationTypeDropdownWidget,
            bodyAngleWidget, headXAngleWidget, headYAngleWidget,
            playerPoseDropdownWidget, playerTextureRadioButton, playerTextureTextField,
            mobTagTextField;
//            pixelmonShinyCheckbox, pixelmonFormTextField, pixelmonSkinTextField, pixelmonSelectionButton,
//            pixelmonSelectionWidget, pixelmonSelectionSearchBar;

    public DisplayCustomizationTab(PlayerNpcEntity npcEntity) {
        super(npcEntity);
    }

    @Override
    public void init(int x, int y) {
        super.init(x, y);

        // name text field
        this.addRenderableWidget(ScreenUtils.createTextField(x + 6, y + 7, 170, 14, 50,
                npcEntity.getDisplayData().getDisplayName(), s -> {
                    npcEntity.getDisplayData().setDisplayName(s);
                    npcEntity.updateDisplayData();
                }));
        this.addRenderableWidget(new ImageCheckbox(x + 180, y + 6, 16, 16, TEXTURE, 0, 166,
                () -> {
                    npcEntity.getDisplayData().setNameVisibility(true);
                    npcEntity.updateDisplayData();
                },
                () -> {
                    npcEntity.getDisplayData().setNameVisibility(false);
                    npcEntity.updateDisplayData();
                },
                npcEntity.getDisplayData().isNameVisible()));

        this.modelDropdownWidget = this.addRenderableWidget(new DropdownWidget(
                x + 50, y + 24, 146,
                NpcUtils.getValidModelEntityTypes(), npcEntity.getRenderedEntityTypeKey(), (widget, entityType) -> {
            npcEntity.getDisplayData().getRendererInfo().setEntityType(entityType);
            npcEntity.updateDisplayData();
            updateWidgetVisibility(entityType);
        },
                () -> {
                    if (playerPoseDropdownWidget.visible) {
                        playerPoseDropdownWidget.active = false;
                        playerPoseDropdownWidget.visible = false;
                        playerTextureRadioButton.active = false;
                        playerTextureRadioButton.visible = false;
                        playerTextureTextField.active = false;
                        playerTextureTextField.visible = false;
                        ((DropdownWidget) playerPoseDropdownWidget).setShowOptions(false);
                    } else if (mobTagTextField.visible) {
                        mobTagTextField.active = false;
                        mobTagTextField.visible = false;
                    }/* else if (pixelmonShinyCheckbox.visible) {
                        pixelmonShinyCheckbox.active = false;
                        pixelmonShinyCheckbox.visible = false;
                        setPixelmonSelectionWidgetVisibility(false);
                        pixelmonSelectionButton.active = false;
                        pixelmonSelectionButton.visible = false;
                        pixelmonFormTextField.active = false;
                        pixelmonFormTextField.visible = false;
                        pixelmonSkinTextField.active = false;
                        pixelmonSkinTextField.visible = false;
                    }*/
                    scaleTextField.visible = false;
                    rotationTypeDropdownWidget.active = false;
                    rotationTypeDropdownWidget.visible = false;
                    updateAngleWidgetVisibility(false);
                },
                () -> {
                    updateWidgetVisibility(npcEntity.getRenderedEntityTypeKey());
                    rotationTypeDropdownWidget.active = true;
                    rotationTypeDropdownWidget.visible = true;
                    updateAngleWidgetVisibility();
                }
        ));

        this.scaleTextField = this.addRenderableWidget(ScreenUtils.createTextField(x + 43, y + 92,
                78, 14, 5, String.valueOf(npcEntity.getDisplayData().getRendererInfo().getScale()),
                s -> {
                    if (s.isEmpty()) {
                        return true;
                    }
                    try {
                        if (Float.parseFloat(s) > 0) {
                            return true;
                        }
                    } catch (Exception ignored) {}
                    return false;
                },
                s -> {
                    if (s.isEmpty()) {
                        npcEntity.getDisplayData().getRendererInfo().setScale(1.0F);
                        npcEntity.updateDisplayData();
                    } else {
                        float f = Float.parseFloat(s);
                        npcEntity.getDisplayData().getRendererInfo().setScale(f);
                        npcEntity.updateDisplayData();
                    }
                }));

        this.rotationTypeDropdownWidget = this.addRenderableWidget(new DropdownWidget(x + 180, y + 94, 70,
                Arrays.stream(RotationInfo.RotationType.values()).map(RotationInfo.RotationType::getName).toList(),
                npcEntity.getDisplayData().getRotationInfo().getTypeName(),
                (widget, type) -> {
                    updateAngleWidgetVisibility(type.equals(RotationInfo.RotationType.STATIC.getName()));
                    npcEntity.getDisplayData().getRotationInfo().setType(type);
                    npcEntity.updateDisplayData();
                },
                () -> {}, () -> {}
        ));

        this.bodyAngleWidget = this.addRenderableWidget(new AngleSelectionWidget(x + 14, y + 121, 15, (npcEntity.getDisplayData().getRotationInfo().getBodyY() + 180) % 360, 5, 0x000000,
                (prevAngle, angle) -> {
                    float diff = angle - prevAngle;
                    AngleSelectionWidget headYAngleWidget = (AngleSelectionWidget) this.headYAngleWidget;
                    // FIXME when reaching -45 or +45 degrees on the angle, the head angle jumps to the upper or lower limit
                    headYAngleWidget.setLowerLimit(angle - 45);
                    headYAngleWidget.setUpperLimit(angle + 45);
                    headYAngleWidget.setAngle(headYAngleWidget.getAngle() + diff);
                    npcEntity.getDisplayData().getRotationInfo().setBodyY((angle + 180) % 360);
                    npcEntity.updateDisplayData();
                }));
        this.headYAngleWidget = this.addRenderableWidget(new AngleSelectionWidget(x + 73, y + 121, 15, (npcEntity.getDisplayData().getRotationInfo().getHeadY() + 180) % 360, (npcEntity.getDisplayData().getRotationInfo().getBodyY() - 45 + 180) % 360, (npcEntity.getDisplayData().getRotationInfo().getBodyY() + 45 + 180) % 360, 5, 0x000000,
                (prevAngle, angle) -> {
                    npcEntity.getDisplayData().getRotationInfo().setHeadY((angle + 180) % 360);
                    npcEntity.updateDisplayData();
                }));
        this.headXAngleWidget = this.addRenderableWidget(new AngleSelectionWidget(x + 132, y + 121, 15, -((npcEntity.getDisplayData().getRotationInfo().getHeadX() + 90) % 360), 180, 360, 5, 0x000000,
                (prevAngle, angle) -> {
                    float headAngle = -((angle - 270) % 360);
                    if (headAngle == 270)
                        headAngle = -90;
                    npcEntity.getDisplayData().getRotationInfo().setHeadX(headAngle);
                    npcEntity.updateDisplayData();
                }));

        updateAngleWidgetVisibility();

        initializePlayerModelCustomizationWidgets();
        initializeMobModelCustomizationWidgets();
//        initializePixelmonModelCustomizationWidgets();

        updateWidgetVisibility(npcEntity.getDisplayData().getRendererInfo().getEntityTypeKey());
    }

    private void updateAngleWidgetVisibility() {
        updateAngleWidgetVisibility(npcEntity.getDisplayData().getRotationInfo().getType() == RotationInfo.RotationType.STATIC);
    }

    private void updateAngleWidgetVisibility(boolean visible) {
        this.bodyAngleWidget.visible = visible;
        this.bodyAngleWidget.active = visible;
        this.headXAngleWidget.visible = visible;
        this.headXAngleWidget.active = visible;
        this.headYAngleWidget.visible = visible;
        this.headYAngleWidget.active = visible;
    }

    private void initializePlayerModelCustomizationWidgets() {
        playerPoseDropdownWidget = this.addRenderableWidget(new DropdownWidget(
                x + 50, y + 37, 146,
                List.of(Pose.STANDING.name(), Pose.CROUCHING.name(), Pose.SITTING.name(), Pose.SLEEPING.name(), Pose.SWIMMING.name()),
                npcEntity.getDisplayData().getRendererInfo().getPoseName(), (widget, pose) -> {
            npcEntity.getDisplayData().getRendererInfo().setPose(Enum.valueOf(Pose.class, pose));
            npcEntity.updateDisplayData();
        },
                () -> {
                    playerTextureRadioButton.active = false;
                    playerTextureRadioButton.visible = false;
                    playerTextureTextField.active = false;
                    playerTextureTextField.visible = false;
                    scaleTextField.active = false;
                    scaleTextField.visible = false;
                    rotationTypeDropdownWidget.active = false;
                    rotationTypeDropdownWidget.visible = false;
                    updateAngleWidgetVisibility(false);
                },
                () -> {
                    playerTextureRadioButton.active = true;
                    playerTextureRadioButton.visible = true;
                    playerTextureTextField.active = true;
                    playerTextureTextField.visible = true;
                    scaleTextField.active = true;
                    scaleTextField.visible = true;
                    rotationTypeDropdownWidget.active = true;
                    rotationTypeDropdownWidget.visible = true;
                    updateAngleWidgetVisibility();
                }
        ));
        playerTextureRadioButton = this.addRenderableWidget(new RadioButton(
                x + 50, y + 50, 70, 11, TEXTURE, 32, 210,
                RadioButton.Orientation.HORIZONTAL, 6, npcEntity.getDisplayData().getRendererInfo().isTextureUsername() ? 0 : 1,
                new RadioButton.RadioOption("username",
                        Tooltip.create(Component.literal("Loads the given player's skin")),
                        () -> {
                            npcEntity.getDisplayData().getRendererInfo().setTextureUsername(true);
                            npcEntity.updateDisplayData();
                        }),
                new RadioButton.RadioOption("local",
                        Tooltip.create(Component.literal("Loads a skin from your loaded resourcepacks. Example: \"minecraft:textures/entity/player/wide/kai.png\"")),
                        () -> {
                            npcEntity.getDisplayData().getRendererInfo().setTextureUsername(false);
                            npcEntity.updateDisplayData();
                        })
        ));
        this.playerTextureTextField = this.addRenderableWidget(ScreenUtils.createTextField(x + 7, y + 64,
                188, 14, 50, npcEntity.getDisplayData().getRendererInfo().getTexture(),
                s -> {
                    npcEntity.getDisplayData().getRendererInfo().setTexture(s);
                    npcEntity.updateDisplayData();
                }));
    }

    private void initializeMobModelCustomizationWidgets() {
        this.mobTagTextField = this.addRenderableWidget(ScreenUtils.createTextField(x + 30, y + 38,
                165, 14, 500, npcEntity.getRenderedEntityTagWithoutId().getAsString(),
                s -> {
                    CompoundTag tag = emptyTag;
                    if (!s.isEmpty()) {
                        try {
                            tag = TagParser.parseTag(s);
                        } catch (Exception e) {
                            tag = npcEntity.getDisplayData().getRendererInfo().getTag();
                        }
                    }
                    npcEntity.getDisplayData().getRendererInfo().setTag(tag);
                    npcEntity.updateDisplayData();
                }));
    }

//    private void initializePixelmonModelCustomizationWidgets() {
//        var pixelmonData = getPixelmonData(npcEntity.getDisplayData().getRendererInfo().getTag());
//        pixelmonSelectionButton = this.addRenderableWidget(new PixelmonSelectionButton(
//                x + 6, y + 37,
//                getPixelmonWidgetData(pixelmonData),
//                btn -> {
//                    boolean visibility = pixelmonSelectionWidget.visible;
//                    setPixelmonSelectionWidgetVisibility(!visibility);
//                    modelDropdownWidget.visible = visibility;
//                    modelDropdownWidget.active = visibility;
//                    scaleTextField.visible = visibility;
//                    scaleTextField.active = visibility;
//                    rotationTypeDropdownWidget.visible = visibility;
//                    rotationTypeDropdownWidget.active = visibility;
//                    if (visibility)
//                        updateAngleWidgetVisibility();
//                    else
//                        updateAngleWidgetVisibility(false);
//                    pixelmonShinyCheckbox.visible = visibility;
//                    pixelmonShinyCheckbox.active = visibility;
//                    pixelmonFormTextField.visible = visibility;
//                    pixelmonFormTextField.active = visibility;
//                    pixelmonSkinTextField.visible = visibility;
//                    pixelmonSkinTextField.active = visibility;
//                })
//        );
//
//        pixelmonSelectionWidget = this.addRenderableWidget(new PixelmonSelectionWidget(pixelmonSelectionButton.getX() + 34, pixelmonSelectionButton.getY(), data -> {
//            var tag = npcEntity.getDisplayData().getRendererInfo().getTag();
//            var dataTag = tag.contains(NpcUtils.PIXELMON_DATA_TAG_KEY) ? tag.getCompound(NpcUtils.PIXELMON_DATA_TAG_KEY) : new CompoundTag();
//            dataTag.putString(NpcUtils.PIXELMON_DATA_POKEDEXENTRY_KEY, data.key().toString());
//            tag.put(NpcUtils.PIXELMON_DATA_TAG_KEY, dataTag);
//            var newPixelmonData = getPixelmonData(tag);
//            setNpcPixelmonData(newPixelmonData);
//            setPixelmonSelectionWidgetVisibility(false);
//            modelDropdownWidget.visible = true;
//            modelDropdownWidget.active = true;
//            scaleTextField.visible = true;
//            scaleTextField.active = true;
//            rotationTypeDropdownWidget.visible = true;
//            rotationTypeDropdownWidget.active = true;
//            updateAngleWidgetVisibility();
//            pixelmonShinyCheckbox.visible = true;
//            pixelmonShinyCheckbox.active = true;
//            pixelmonFormTextField.visible = true;
//            pixelmonFormTextField.active = true;
//            pixelmonSkinTextField.visible = true;
//            pixelmonSkinTextField.active = true;
//            ((PixelmonSelectionButton)pixelmonSelectionButton).setSelectedPixelmon(getPixelmonWidgetData(newPixelmonData));
//        }));
//
//        var pixelmonSelectionSearchBar = new EditBox(font, pixelmonSelectionButton.getX() + 34, pixelmonSelectionButton.getY() - 10, pixelmonSelectionWidget.getWidth(), 10, Component.empty());
//        pixelmonSelectionSearchBar.setResponder(((PixelmonSelectionWidget)pixelmonSelectionWidget)::updateShownData);
//        pixelmonSelectionSearchBar.setHint(Component.literal("Filter..."));
//        pixelmonSelectionSearchBar.setBordered(false);
//        this.pixelmonSelectionSearchBar = this.addRenderableWidget(pixelmonSelectionSearchBar);
//
//        pixelmonShinyCheckbox = this.addRenderableWidget(new ImageCheckbox(x + 74, y + 37, 16, 16, TEXTURE, 0, 166,
//                () -> {
//                    var data = getPixelmonData(npcEntity.getDisplayData().getRendererInfo().getTag());
//                    data.setShiny(true);
//                    ((PixelmonSelectionButton)pixelmonSelectionButton).setSelectedPixelmon(getPixelmonWidgetData(data));
//                    setNpcPixelmonData(data);
//                },
//                () -> {
//                    var data = getPixelmonData(npcEntity.getDisplayData().getRendererInfo().getTag());
//                    data.setShiny(false);
//                    ((PixelmonSelectionButton)pixelmonSelectionButton).setSelectedPixelmon(getPixelmonWidgetData(data));
//                    setNpcPixelmonData(data);
//                },
//                pixelmonData.getShiny()
//        ));
//
//        this.pixelmonFormTextField = this.addRenderableWidget(ScreenUtils.createTextField(x + 75, y + 56,
//                120, 14, 32, pixelmonData.form,
//                s -> {
//                    var data = getPixelmonData(npcEntity.getDisplayData().getRendererInfo().getTag());
//                    data.setForm(s);
//                    ((PixelmonSelectionButton)pixelmonSelectionButton).setSelectedPixelmon(getPixelmonWidgetData(data));
//                    setNpcPixelmonData(data);
//                }));
//
//        this.pixelmonSkinTextField = this.addRenderableWidget(ScreenUtils.createTextField(x + 75, y + 74,
//                120, 14, 32, pixelmonData.skin,
//                s -> {
//                    var data = getPixelmonData(npcEntity.getDisplayData().getRendererInfo().getTag());
//                    data.setSkin(s);
//                    ((PixelmonSelectionButton)pixelmonSelectionButton).setSelectedPixelmon(getPixelmonWidgetData(data));
//                    setNpcPixelmonData(data);
//                }));
//    }

    public static PixelmonSelectionWidget.PixelmonWidgetData getPixelmonWidgetData(Pokemon pixelmonData) {
        return new PixelmonSelectionWidget.PixelmonWidgetData(pixelmonData.asRenderablePokemon());
    }

    private Pokemon getPixelmonData(CompoundTag tag) {
        return NpcUtils.pixelmonDataFromTag(tag);
    }

//    private void setNpcPixelmonData(Pokemon data) {
//        CompoundTag tag = new CompoundTag();
//        tag.putString(NpcUtils.PIXELMON_DATA_POKEDEXENTRY_KEY, data.getSpecies().toString());
//        tag.putString(NpcUtils.PIXELMON_DATA_FORM_KEY, data.form);
//        tag.putString(NpcUtils.PIXELMON_DATA_SKIN_KEY, data.skin);
//        CompoundTag dataTag = new CompoundTag();
//        dataTag.put(NpcUtils.PIXELMON_DATA_TAG_KEY, tag);
//        npcEntity.getDisplayData().getRendererInfo().setTag(dataTag);
//        npcEntity.updateDisplayData();
//    }

    private void updateWidgetVisibility(String model) {
        // player model widgets
        var playerWidgetVisibility = model.equals(NpcUtils.MODEL_STEVE) || model.equals(NpcUtils.MODEL_ALEX);
        playerPoseDropdownWidget.visible = playerWidgetVisibility;
        playerPoseDropdownWidget.active = playerWidgetVisibility;
        playerTextureRadioButton.visible = playerWidgetVisibility;
        playerTextureRadioButton.active = playerWidgetVisibility;
        playerTextureTextField.visible = playerWidgetVisibility;
        playerTextureTextField.active = playerWidgetVisibility;

        // pixelmon model widgets
//        var pixelmonWidgetVisibility = model.equals("pokemod:pixelmon");
//        setPixelmonSelectionWidgetVisibility(false);
//        pixelmonSelectionButton.active = pixelmonWidgetVisibility;
//        pixelmonSelectionButton.visible = pixelmonWidgetVisibility;
//        pixelmonShinyCheckbox.active = pixelmonWidgetVisibility;
//        pixelmonShinyCheckbox.visible = pixelmonWidgetVisibility;
//        pixelmonFormTextField.active = pixelmonWidgetVisibility;
//        pixelmonFormTextField.visible = pixelmonWidgetVisibility;
//        pixelmonSkinTextField.active = pixelmonWidgetVisibility;
//        pixelmonSkinTextField.visible = pixelmonWidgetVisibility;

        // mob model widgets
        var mobWidgetVisibility = !playerWidgetVisibility; // && !pixelmonWidgetVisibility;
        mobTagTextField.visible = mobWidgetVisibility;
        mobTagTextField.active = mobWidgetVisibility;

        scaleTextField.visible = true;
    }

//    private void setPixelmonSelectionWidgetVisibility(boolean visible) {
//        pixelmonSelectionSearchBar.visible = visible;
//        pixelmonSelectionSearchBar.active = visible;
//        pixelmonSelectionWidget.visible = visible;
//        pixelmonSelectionWidget.active = visible;
//    }

    @Override
    public void tick() {

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
        if (modelDropdownWidget != null && modelDropdownWidget.visible) {
            poseStack.drawString(font, "Model:", x + 6, y + 26, 0x5F5F60, false);
        }
        if (scaleTextField != null && scaleTextField.visible) {
            poseStack.drawString(font, "Scale:", x + 6, y + 96, 0x5F5F60, false);
        }
        if (rotationTypeDropdownWidget != null && rotationTypeDropdownWidget.visible) {
            poseStack.drawString(font, "Rotation:", x + 126, y + 96, 0x5F5F60, false);
        }
        if (bodyAngleWidget != null && bodyAngleWidget.visible) {
            poseStack.drawString(font, "N", x + 26, y + 111, 0x000000, false);
            poseStack.drawString(font, "E", x + 46, y + 132, 0x000000, false);
            poseStack.drawString(font, "W", x + 6, y + 132, 0x000000, false);
            poseStack.drawString(font, "S", x + 26, y + 153, 0x000000, false);
            poseStack.drawString(font, "N", x + 85, y + 111, 0x000000, false);
            poseStack.drawString(font, "E", x + 105, y + 132, 0x000000, false);
            poseStack.drawString(font, "W", x + 65, y + 132, 0x000000, false);
            poseStack.drawString(font, "S", x + 85, y + 153, 0x000000, false);
            poseStack.drawString(font, "UP", x + 141, y + 111, 0x000000, false);
            poseStack.drawString(font, "DOWN", x + 135, y + 153, 0x000000, false);
            poseStack.drawString(font, "BodyY: " + String.format("%.2f", npcEntity.getDisplayData().getRotationInfo().getBodyY()), x + 181, y + 121, 0x5F5F60, false);
            poseStack.drawString(font, "HeadY: " + String.format("%.2f", npcEntity.getDisplayData().getRotationInfo().getHeadY()), x + 181, y + 130, 0x5F5F60, false);
            poseStack.drawString(font, "HeadX: " + String.format("%.2f", npcEntity.getDisplayData().getRotationInfo().getHeadX()), x + 181, y + 139, 0x5F5F60, false);
        }

        if (playerPoseDropdownWidget != null && playerPoseDropdownWidget.visible) {
            poseStack.drawString(font, "Pose:", x + 6, y + 39, 0x5F5F60, false);
            poseStack.drawString(font, "Texture:", x + 6, y + 52, 0x5F5F60, false);
        } else if (mobTagTextField != null && mobTagTextField.visible) {
            poseStack.drawString(font, "Tag:", x + 6, y + 42, 0x5F5F60, false);
        }/* else if (pixelmonShinyCheckbox != null && pixelmonShinyCheckbox.visible) {
            poseStack.drawString(font, "Shiny:", x + 43, y + 41, 0x5F5F60, false);
            poseStack.drawString(font, "Form:", x + 43, y + 59, 0x5F5F60, false);
            poseStack.drawString(font, "Skin:", x + 43, y + 78, 0x5F5F60, false);
        }*/
    }

    @Override
    public void postRender(GuiGraphics poseStack, int mouseX, int mouseY, float partialTick) {

    }
}