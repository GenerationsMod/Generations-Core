package generations.gg.generations.core.generationscore.client.screen.statue;


import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.client.screen.ScreenUtils;
import generations.gg.generations.core.generationscore.client.screen.widget.AngleSelectionWidget;
import generations.gg.generations.core.generationscore.client.screen.widget.ImageCheckbox;
import generations.gg.generations.core.generationscore.client.screen.widget.PixelmonSelectionButton;
import generations.gg.generations.core.generationscore.client.screen.widget.PixelmonSelectionWidget;
import generations.gg.generations.core.generationscore.network.GenerationsNetwork;
import generations.gg.generations.core.generationscore.network.packets.statue.C2SUpdateStatueInfoPacket;
import generations.gg.generations.core.generationscore.world.entity.StatueEntity;
import gg.generations.rarecandy.animation.Animation;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class StatueEditorScreen extends Screen {
    private static final ResourceLocation TEXTURE = GenerationsCore.id("textures/gui/npc/customization.png");
    private final StatueEntity statue;
    private int x, y;

    private AbstractWidget orientationWidget, statickCheckbox, pixelmonFormTextField, pixelmonSkinTextField, pixelmonSelectionButton,
            pixelmonSelectionWidget, pixelmonSelectionSearchBar, scaleTextField, animationTextField, timestampTextField, parserTextField;

    public StatueEditorScreen(StatueEntity entity) {
        super(Component.empty());
        this.statue = entity;
    }

    @Override
    protected void init() {
        this.x = width / 2 - 128;
        this.y = height / 2 - 83;

        var info = statue.getStatueData();

        this.parserTextField = this.addRenderableWidget(ScreenUtils.createTextField(x + 6, y + 7, 170, 14, 50,
                "Derp", s -> {
//                    npcEntity.getDisplayData().setDisplayName(s);
//                    npcEntity.updateDisplayData();
                }));

        this.scaleTextField = this.addRenderableWidget(ScreenUtils.createTextField(x + 43, y + 66,
                78, 14, 5, String.valueOf(info.getScale()),
                s -> {
                    if (s.isEmpty()) {
                        return true;
                    }
                    try {
                        if (parseFloat(s) >= 0) {
                            return true;
                        }
                    } catch (Exception ignored) {}
                    return false;
                },
                s -> {
                    var scale = parseFloat(s);

                    if (scale <= 0) {
                        statue.getStatueData().setScale(1.0F);
                        statue.updateStatueData();
                    } else {
                        statue.getStatueData().setScale(scale);
                        statue.updateStatueData();
                    }
                }));

        this.animationTextField = this.addRenderableWidget(ScreenUtils.createTextField(x + 129, y + 107, 120, 14, 50, info.getAnimation(), a -> true, s -> {
            statue.getStatueData().setAnimation(s);
            statue.updateStatueData();
            checkTimestampState(true);
        }));

        this.timestampTextField = this.addRenderableWidget(ScreenUtils.createTextField(x + 129, y + 125, 60, 14, 25, String.valueOf(info.getFrame()), s -> {
            if (s.isEmpty()) {
                return true;
            }
            try {
                if (parseFloat(s) >= 0) {
                    return true;
                }
            } catch (Exception ignored) {}
            return false;
        }, s -> {
            var value = s.isEmpty() ? 0 : parseFloat(s);

            //TODO: Work on
//            var anim = getAnimation();
//            if (anim != null) {
//                statue.getStatueData().setProgress((float) Mth.clamp(value, 0, anim.animationDuration));
//                statue.updateStatueData();
//            }
        }));

        checkTimestampState(true);

        statickCheckbox = this.addRenderableWidget(new ImageCheckbox(x + 160, y + 65, 16, 16, TEXTURE, 0, 166,
                () -> {
                    var data = statue.getStatueData();
                    data.setIsStatic(true);
                    statue.updateStatueData();
                    checkTimestampState(false);
                },
                () -> {
                    var data = statue.getStatueData();
                    data.setIsStatic(false);
                    statue.updateStatueData();
                    checkTimestampState(true);
                },
                info.isStatic()
        ));

        orientationWidget = addRenderableWidget(new AngleSelectionWidget(x + 28, y + 113, 15, (statue.getStatueData().getOrientation() + 180) % 360, 5, 0x000000,
                (prevAngle, angle) -> {
                    statue.getStatueData().setOrientation((angle + 180) % 360);
                    statue.updateStatueData();
                }));

        initializePixelmonModelCustomizationWidgets();
        setPixelmonSelectionWidgetVisibility(false);
    }

//    private Animation getAnimation() {
//        if (statue.clientInfo != null)
//            if (statue.clientInfo.pixelmonInstance != null) {
//                return statue.clientInfo.pixelmonInstance.getAnimationsIfAvailable().get(statue.getStatueData().getAnimation());
//            }
//        return null;
//    }

    @Override
    public void render(GuiGraphics poseStack, int mouseX, int mouseY, float partialTick) {
        poseStack.pose().pushPose();
        poseStack.pose().translate(x, y, 0.0);
        poseStack.blit(TEXTURE, 0, 0, 0, 0, 256, 166, 256, 256);
        poseStack.pose().popPose();

        super.render(poseStack, mouseX, mouseY, partialTick);

        if (scaleTextField != null && scaleTextField.visible) poseStack.drawString(font, "Scale", x + 6, y + 70, 0x5F5F60);

        if (statickCheckbox != null && statickCheckbox.visible) poseStack.drawString(font, "Static:", x + 129, y + 69, 0x5F5F60);
        if (pixelmonFormTextField != null && pixelmonFormTextField.visible) poseStack.drawString(font, "Form:", x + 44, y + 31, 0x5F5F60);
        if (pixelmonSkinTextField != null && pixelmonSkinTextField.visible) poseStack.drawString(font, "Skin:", x + 44, y + 49, 0x5F5F60);
        if (animationTextField != null && animationTextField.visible) {
            poseStack.drawString(font, "Animation:", x + 77, y + 110, 0x5F5F60);
        }
        if (timestampTextField != null && timestampTextField.visible) {
            poseStack.drawString(font, "Timestamp:", x + 77, y + 128, 0x5F5F60);
//TODO: WOrk on
//            var animation = getAnimation();
//            String s = animation != null ? String.valueOf((int) (animation.animationDuration)) : "-1";
//            poseStack.drawString(font, " / " + s, x + 190, y + 128, 0x5F5F60);
        }

        if (orientationWidget != null && orientationWidget.visible) {
            poseStack.drawString(font, "N", x + 41, y + 102, 0x000000);
            poseStack.drawString(font, "E", x + 63, y + 125, 0x000000);
            poseStack.drawString(font, "W", x + 19, y + 125, 0x000000);
            poseStack.drawString(font, "S", x + 41, y + 148, 0x000000);
            poseStack.drawString(font, "Orientation: " + String.format("%.2f", statue.getStatueData().getOrientation()), x + 12, y + 90, 0x5F5F60);
        }
    }

    private void initializePixelmonModelCustomizationWidgets() {
        var pixelmonData = statue.getStatueData();
        pixelmonSelectionButton = this.addRenderableWidget(new PixelmonSelectionButton(
                x + 6, y + 26,
                new PixelmonSelectionWidget.PixelmonWidgetData(pixelmonData.getProperties().asRenderablePokemon()),
                btn -> {
                    boolean visibility = pixelmonSelectionWidget.visible;
                    setPixelmonSelectionWidgetVisibility(!visibility);
                    scaleTextField.visible = visibility;
                    scaleTextField.active = visibility;

                    orientationWidget.visible = visibility;
                    orientationWidget.active = visibility;
                    statickCheckbox.visible = visibility;
                    statickCheckbox.active = visibility;
                    pixelmonFormTextField.visible = visibility;
                    pixelmonFormTextField.active = visibility;
                    pixelmonSkinTextField.visible = visibility;
                    pixelmonSkinTextField.active = visibility;
                    animationTextField.active = visibility;
                    animationTextField.visible = visibility;
                    checkTimestampState(visibility);
                    parserTextField.active = visibility;
                    parserTextField.visible = visibility;
                })
        );

        pixelmonSelectionWidget = this.addRenderableWidget(new PixelmonSelectionWidget(pixelmonSelectionButton.getX() + 34, pixelmonSelectionButton.getY(), data -> {
            var info = statue.getStatueData();
//            info.setPokemon(data.key());
//            var form = info.getPokedexEntry().getFormMap().containsKey(info.getFormId()) ? info.getSkinId() : info.getPokedexEntry().getDefaultFormId();
//            info.setForm(form);
//            var skin = info.getPokemonForm().skins().containsKey(info.getSkinId()) ? info.getSkinId() : info.getPokemonForm().defaultSkin();
//            info.setSkin(skin);

            setPixelmonSelectionWidgetVisibility(false);

            scaleTextField.visible = true;
            scaleTextField.active = true;
            orientationWidget.visible = true;
            orientationWidget.active = true;
            statickCheckbox.visible = true;
            statickCheckbox.active = true;
            pixelmonFormTextField.visible = true;
            pixelmonFormTextField.active = true;
            pixelmonSkinTextField.visible = true;
            pixelmonSkinTextField.active = true;
            animationTextField.active = true;
            animationTextField.visible = true;
            checkTimestampState(true);
            parserTextField.active = true;
            parserTextField.visible = true;
            ((PixelmonSelectionButton)pixelmonSelectionButton).setSelectedPixelmon(new PixelmonSelectionWidget.PixelmonWidgetData(statue.getStatueData().getProperties().asRenderablePokemon()));
        }));

        var pixelmonSelectionSearchBar = new EditBox(font, pixelmonSelectionButton.getX() + 34, pixelmonSelectionButton.getY() - 10, pixelmonSelectionWidget.getWidth(), 10, Component.empty());
        pixelmonSelectionSearchBar.setResponder(((PixelmonSelectionWidget)pixelmonSelectionWidget)::updateShownData);
        pixelmonSelectionSearchBar.setHint(Component.literal("Filter..."));
        pixelmonSelectionSearchBar.setBordered(false);
        this.pixelmonSelectionSearchBar = this.addRenderableWidget(pixelmonSelectionSearchBar);

        this.pixelmonFormTextField = this.addRenderableWidget(ScreenUtils.createTextField(x + 75, y + 27,
                120, 14, 32, "Form", //pixelmonData.getFormId(),
                s -> {
                    var data = statue.getStatueData();
//                    var form = data.getPokedexEntry().getFormMap().containsKey(s) ? s : data.getPokedexEntry().getDefaultFormId();
//                    data.setForm(form);
//                    var skin = data.getPokemonForm().skins().containsKey(pixelmonSkinTextField.getMessage().getString()) ? pixelmonSkinTextField.getMessage().getString() : data.getPokemonForm().defaultSkin();
//                    data.setSkin(skin);

                    ((PixelmonSelectionButton)pixelmonSelectionButton).setSelectedPixelmon(new PixelmonSelectionWidget.PixelmonWidgetData(statue.getStatueData().getProperties().asRenderablePokemon()));
                    statue.setStatueInfo(data);
                }));

        this.pixelmonSkinTextField = this.addRenderableWidget(ScreenUtils.createTextField(x + 75, y + 45,
                120, 14, 32, "Skin", //pixelmonData.getSkinId(),
                s -> {
                    var data = statue.getStatueData();
//                    var form = data.getPokedexEntry().getFormMap().containsKey(pixelmonFormTextField.getMessage().getString()) ? pixelmonFormTextField.getMessage().getString() : data.getPokedexEntry().getDefaultFormId();
//                    data.setForm(form);
//                    var skin = data.getPokemonForm().skins().containsKey(s) ? s : data.getPokemonForm().defaultSkin();
//                    data.setSkin(skin);
//
//                    data.setSkin(s);
                    ((PixelmonSelectionButton)pixelmonSelectionButton).setSelectedPixelmon(new PixelmonSelectionWidget.PixelmonWidgetData(statue.getStatueData().getProperties().asRenderablePokemon()));
                    statue.setStatueInfo(data);
                }));
    }

    private void setPixelmonSelectionWidgetVisibility(boolean visible) {
        pixelmonSelectionSearchBar.visible = visible;
        pixelmonSelectionSearchBar.active = visible;
        pixelmonSelectionWidget.visible = visible;
        pixelmonSelectionWidget.active = visible;
    }

    private void checkTimestampState(boolean visibility) {
        //TODO: work on
//        var animation = getAnimation();
//        if(visibility && animation != null) {
//            timestampTextField.visible = true;
//            timestampTextField.active = true;
//            timestampTextField.setMessage(Component.literal(String.valueOf((int) (statue.getStatueData().getFrame()))));
//        } else {
//            timestampTextField.visible = false;
//            timestampTextField.active = false;
//        }
    }

    @Override
    public void onClose() {
        GenerationsNetwork.INSTANCE.sendToServer(new C2SUpdateStatueInfoPacket(statue.getId(), statue.getStatueData()));
        super.onClose();
    }

    public static float parseFloat(String s) throws NumberFormatException {
        if (s.endsWith(".")) {
            s = s + "0";
        }

        if(s.isEmpty()) return 1.0f;

        return Float.parseFloat(s);
    }

    public static int parseInt(String s) throws NumberFormatException {
        if (s.contains(".")) {
            s = s.split("\\.")[0];
        }

        if(s.isEmpty()) return 0;

        return Integer.parseInt(s);
    }
}