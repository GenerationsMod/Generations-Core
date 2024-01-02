package generations.gg.generations.core.generationscore.client.screen.statue;


import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import com.cobblemon.mod.common.client.gui.PokemonGuiUtilsKt;
import com.cobblemon.mod.common.client.gui.summary.widgets.ModelWidget;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.client.screen.ScreenUtils;
import generations.gg.generations.core.generationscore.client.screen.widget.AngleSelectionWidget;
import generations.gg.generations.core.generationscore.client.screen.widget.ImageCheckbox;
import generations.gg.generations.core.generationscore.network.GenerationsNetwork;
import generations.gg.generations.core.generationscore.network.packets.statue.C2SUpdateStatueInfoPacket;
import generations.gg.generations.core.generationscore.world.entity.StatueEntity;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.joml.Math;
import org.joml.Quaternionf;

public class StatueEditorScreen extends Screen {
    private static final ResourceLocation TEXTURE = GenerationsCore.id("textures/gui/npc/customization.png");
    private static final ResourceLocation STATUE = GenerationsCore.id("textures/gui/statue/statue_gui.png");
    private final StatueEntity statue;
    private int x, y;

    private AbstractWidget orientationWidget, statickCheckbox, nameTextField, scaleTextField, animationTextField, timestampTextField, parserTextField, interactableCheckbox, modelWidget;

    public StatueEditorScreen(StatueEntity entity) {
        super(Component.empty());
        this.statue = entity;
    }

    @Override
    protected void init() {
        this.x = width / 2 - 96;
        this.y = height / 2 - 84;

        var info = statue.getStatueData();

        this.parserTextField = this.addRenderableWidget(ScreenUtils.createTextField(x + 7, y + 7, 178, 14, 500,
                info.getProperties().asString(" "), s -> {
                    statue.getStatueData().setProperties(PokemonProperties.Companion.parse(s, " ", "="));
                    updateStatueData();
                }));

        this.nameTextField = this.addRenderableWidget(ScreenUtils.createTextField(x + 59, y + 92, 126, 14, 50, info.getLabel(), a -> true, s -> {
                    statue.getStatueData().setLabel(s);
                    updateStatueData();
        }));

        this.animationTextField = this.addRenderableWidget(ScreenUtils.createTextField(x + 59, y + 110, 126, 14, 50, info.getAnimation(), a -> true, s -> {
            statue.getStatueData().setAnimation(s);
            updateStatueData();
        }));

        this.timestampTextField = this.addRenderableWidget(ScreenUtils.createTextField(x + 59, y + 128, 78, 14, 25, String.valueOf(info.getFrame()), s -> {
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
                  statue.getStatueData().setProgress(value);
                updateStatueData();
//            }
        }));

        this.scaleTextField = this.addRenderableWidget(ScreenUtils.createTextField(x + 59, y + 146, 36, 14, 5, String.valueOf(info.getScale()),
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
                        updateStatueData();
                    } else {
                        statue.getStatueData().setScale(scale);
                        updateStatueData();
                    }
                }));

        statickCheckbox = this.addRenderableWidget(new ImageCheckbox(x + 170, y + 127, 16, 16, TEXTURE, 0, 166,
                () -> {
                    statue.getStatueData().setIsStatic(true);
                    updateStatueData();
                },
                () -> {
                    statue.getStatueData().setIsStatic(false);
                    updateStatueData();
                },
                info.isStatic()
        ));


        this.interactableCheckbox = this.addRenderableWidget(new ImageCheckbox(x + 170, y + 145, 16, 16, TEXTURE, 0, 166,
                () -> {
                    statue.getStatueData().setSacredAshInteractable(true);
                    updateStatueData();
                },
                () -> {
                    var data = statue.getStatueData();
                    statue.getStatueData().setSacredAshInteractable(false);
                    updateStatueData();
                },
                info.isSacredAshInteractable()
        ));

        orientationWidget = addRenderableWidget(new AngleSelectionWidget(x + 43, y + 47, 15, (statue.getStatueData().getOrientation()), 5, 0x000000,
                (prevAngle, angle) -> {
                    statue.getStatueData().setOrientation(angle);
                    updateStatueData();
                }));

        modelWidget = this.addRenderableWidget(new ModelWidget(x +  122, y + 25, 63, 63, info.getProperties().asRenderablePokemon(), 1.9090909f, -325f, -9.545454f));
    }

    private void updateStatueData() {
        GenerationsCore.implementation.getNetworkManager().sendPacketToServer(new C2SUpdateStatueInfoPacket(statue.getId(), statue.getStatueData()));
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

        poseStack.pose().pushPose();
        poseStack.fill(122, 25, 122 + 63, 25 + 63, 0xff000000);
        poseStack.enableScissor(x + 122, y + 25, x + 122 + 63, y + 25 + 63);

        poseStack.pose().translate((63 / 2f), 63 - 5.0, 0.0);
        PokemonGuiUtilsKt.drawProfilePokemon(statue.getStatueData().asRenderablePokemon(), poseStack.pose(), new Quaternionf().rotationXYZ(Math.toRadians(13f), Math.toRadians(-35F), Math.toRadians(0F)), statue.delegate, 6f, partialTick);
        poseStack.disableScissor();
        poseStack.pose().popPose();

        poseStack.blit(STATUE, 0, 0, 0, 0, 256, 166, 256, 256);

        poseStack.pose().popPose();
        super.render(poseStack, mouseX, mouseY, partialTick);

        ScreenUtils.drawText(poseStack, "Static:", x + 168, y + 131, 0x5F5F60, ScreenUtils.Position.RIGHT);
        ScreenUtils.drawText(poseStack, "Interactable:", x + 168, y + 149, 0x5F5F60, ScreenUtils.Position.RIGHT);

        ScreenUtils.drawText(poseStack, "Name:", x + 56, y + 95, 0x5F5F60, ScreenUtils.Position.RIGHT);
        ScreenUtils.drawText(poseStack, "Animation", x + 56, y + 113, 0x5F5F60, ScreenUtils.Position.RIGHT);
        ScreenUtils.drawText(poseStack, "Timestamp:", x + 56, y + 131, 0x5F5F60, ScreenUtils.Position.RIGHT);
        ScreenUtils.drawText(poseStack, "Scale:", x + 56, y + 149, 0x5F5F60, ScreenUtils.Position.RIGHT);

        poseStack.drawString(font, "N", x + 56, y + 36, 0x000000, false);
        poseStack.drawString(font, "E", x + 78, y + 59, 0x000000, false);
        poseStack.drawString(font, "W", x + 34, y + 59, 0x000000, false);
        poseStack.drawString(font, "S", x + 56, y + 82, 0x000000, false);
        poseStack.drawString(font, "Orientation: " + String.format("%.2f", statue.getStatueData().getOrientation()), x + 11, y + 24, 0x5F5F60, false);
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

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}