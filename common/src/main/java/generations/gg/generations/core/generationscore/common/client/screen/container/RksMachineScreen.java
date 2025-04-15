package generations.gg.generations.core.generationscore.common.client.screen.container;

import com.cobblemon.mod.common.Cobblemon;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.world.container.RksMachineContainer;
import generations.gg.generations.core.generationscore.common.network.packets.C2STogglePacket;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class RksMachineScreen extends AbstractContainerScreen<RksMachineContainer> {
	private static final ResourceLocation TEXTURE = GenerationsCore.id("textures/gui/container/rks_machine.png");

	private final Button button;

	public RksMachineScreen(RksMachineContainer handler, Inventory inventory, Component title) {
		super(handler, inventory, title);
		button = Button.builder(Component.literal("Start"), button -> Cobblemon.INSTANCE.getImplementation().getNetworkManager().sendToServer(new C2STogglePacket())).bounds(0, 0, 41, 13).build();
	}

	public void init() {
		super.init();
		this.addRenderableWidget(button);
		this.button.setPosition(this.leftPos + 109, this.topPos + 62);
		this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
	}


	public void render(@NotNull GuiGraphics matrices, int mouseX, int mouseY, float delta) {
		this.renderBackground(matrices, mouseX, mouseY, delta);
		this.renderBg(matrices, delta, mouseX, mouseY);
		super.render(matrices, mouseX, mouseY, delta);
		this.renderTooltip(matrices, mouseX, mouseY);}

	protected void renderBg(GuiGraphics matrices, float delta, int mouseX, int mouseY) {
		int i = this.leftPos;
		int j = (this.height - this.imageHeight) / 2;
		matrices.blit(TEXTURE, i, j, 0, 0, this.imageWidth, this.imageWidth);

		if (this.menu.isWeaving()) {
			int k = (this.menu).getBurnProgress(22);
			matrices.blit(TEXTURE, i + 89, j + 34, 176, 0, k + 1, 16);
		}
	}
}
