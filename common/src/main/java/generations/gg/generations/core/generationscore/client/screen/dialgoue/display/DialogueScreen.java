package generations.gg.generations.core.generationscore.client.screen.dialgoue.display;

import com.mojang.blaze3d.systems.RenderSystem;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.client.screen.ScreenUtils;
import generations.gg.generations.core.generationscore.client.screen.dialgoue.configure.ConfigureStringListEntryScreen;
import generations.gg.generations.core.generationscore.network.packets.dialogue.C2SCloseDialoguePacket;
import generations.gg.generations.core.generationscore.network.packets.dialogue.C2SRequestNodesDialoguePacket;
import generations.gg.generations.core.generationscore.network.packets.dialogue.C2SRespondDialoguePacket;
import generations.gg.generations.core.generationscore.world.sound.GenerationsSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

import java.util.List;

public class DialogueScreen extends Screen {

    private final boolean canCloseOnEsc;
    public ActiveInfo activeInfo;

    public DialogueScreen(boolean canCloseOnEsc) {
        super(Component.literal("Dialogue"));
        this.canCloseOnEsc = canCloseOnEsc;
    }

    @Override
    protected void init() {
        if (activeInfo instanceof ChooseActiveInfo chooseActiveInfo && !chooseActiveInfo.chosen) {
            addRenderableWidget(new OptionsWidget(width / 2 + (340 / 2), height - 90, chooseActiveInfo.options, this::chooseOption));
        }
    }

    public void chooseOption(String chosenOption) {
        if (activeInfo instanceof ChooseActiveInfo chooseActiveInfo && !chooseActiveInfo.chosen) {
            chooseActiveInfo.chosen = true;
            GenerationsCore.getImplementation().getNetworkManager().sendPacketToServer(new C2SRespondDialoguePacket(chosenOption));
            GenerationsCore.getImplementation().getNetworkManager().sendPacketToServer(new C2SRequestNodesDialoguePacket());
            init(minecraft, width, height);
        }
    }

    @Override
    public void render(@NotNull GuiGraphics stack, int mouseX, int mouseY, float partialTick) {
        super.render(stack, mouseX, mouseY, partialTick);

        stack.pose().pushPose();
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1, 1, 1, 1F);
        ScreenUtils.drawTexture(stack, GenerationsCore.id("textures/gui/dialogue/message_box.png"), width / 2 - 170, height - 84, 0, 0, 340, 80, 340, 80);
        stack.enableScissor(width / 2 - 170, height - 84, width / 2 + 170, height - 6);
        var y = height - 69;

        if (activeInfo != null) {
            for (var line : ConfigureStringListEntryScreen.DisplayCache.calculateMultilineFittingString(activeInfo.text(), 63)) {
                var component = ScreenUtils.formatStringWithColorsToComponent(line);
                stack.drawString(Minecraft.getInstance().font, component, width / 2 - 170 + 8, y, 0xFFFFFF);
                y += 10;
            }

            if (activeInfo.renderArrow()) {
                ScreenUtils.drawTexture(stack, GenerationsCore.id("textures/gui/dialogue/next_dialogue_arrow.png"), width / 2 + 340 / 2 - 30, (int) (height - 26 * + (Mth.sin(partialTick * 0.2f) / 0.95f)), 0, 0, 10, 10, 10, 10);
            }
        } else {
            ScreenUtils.drawCenteredString(stack, Minecraft.getInstance().font, "Waiting for Server...", width / 2, y, 0xFF000000, false);
        }

        stack.disableScissor();
        RenderSystem.disableBlend();
        stack.pose().popPose();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
            if (activeInfo != null) {
                activeInfo.onClick();
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return canCloseOnEsc;
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void onClose() {
        super.onClose();
        GenerationsCore.getImplementation().getNetworkManager().sendPacketToServer(new C2SCloseDialoguePacket());
    }

    public interface ActiveInfo {

        boolean renderArrow();

        void onClick();

        String text();
    }

    public static class SayActiveInfo implements ActiveInfo {

        private final List<String> text;
        private int index;
        private String currentLine;
        private final boolean showArrow;

        public SayActiveInfo(List<String> text, boolean showArrow) {
            this.text = text;
            this.index = 0;
            this.showArrow = showArrow;
        }

        @Override
        public void onClick() {
            if (index >= text.size()) {
                GenerationsCore.getImplementation().getNetworkManager().sendPacketToServer(new C2SRequestNodesDialoguePacket());
            } else {
                this.currentLine = text.get(index++);
                Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(GenerationsSounds.UI_CLICK.get(), 1.0F));
            }
        }

        @Override
        public String text() {
            if (currentLine == null) onClick();
            return currentLine;
        }

        @Override
        public boolean renderArrow() {
            return text.size() <= index || showArrow;
        }
    }

    public static class ChooseActiveInfo implements ActiveInfo {
        private final String text;
        private final List<String> options;
        public boolean chosen;

        public ChooseActiveInfo(String text, List<String> options) {
            this.text = text;
            this.options = options;
        }

        @Override
        public boolean renderArrow() {
            return false;
        }

        @Override
        public void onClick() {
        }

        @Override
        public String text() {
            return text;
        }
    }
}