package generations.gg.generations.core.generationscore.client.screen.dialgoue.configure;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import generations.gg.generations.core.generationscore.client.screen.AbstractHierarchicalWidget;
import generations.gg.generations.core.generationscore.client.screen.ScreenUtils;
import generations.gg.generations.core.generationscore.world.dialogue.GenerationsDialogueNodeTypes;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class NodeListWidget extends AbstractHierarchicalWidget {

    private final ArrayList<ResourceLocation> possibleNodes;
    private ResourceLocation hoveredNode = null;

    public NodeListWidget(int relativeX, int relativeY, int width, int height) {
        super(relativeX, relativeY, width, height, null);
        setX(getX() - getWidth());
        setY(getY() - getHeight());
        this.possibleNodes = new ArrayList<>();
        possibleNodes.addAll(GenerationsDialogueNodeTypes.DIALOGUE_NODE_TYPES.getIds());
    }

    @Override
    public void render(@NotNull PoseStack stack, int mouseX, int mouseY, float partialTick) {
        super.render(stack, mouseX, mouseY, partialTick);

        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        ScreenUtils.drawRect(stack, getX(), getY(), getWidth(), getHeight(), 0xFF222222);
        ScreenUtils.drawCenteredString(stack, client.font, "Create a new Node", getX() + width / 2, getY() + 2, 0xFFFFFFFF, false);

        for (int i = 0; i < possibleNodes.size(); i++) {
            client.font.draw(stack, possibleNodes.get(i).getPath(), getX() + 2, getY() + 16 + i * 10, 0xFFFFFFFF);
        }

        finishRendering(stack, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        var listHovered = super.isMouseOver(mouseX, mouseY);

        if (listHovered) {
            int textStartY = getY() + 16;
            var posInsideY = (float) mouseY - textStartY;
            if(posInsideY > 0) {
                var idx = (int) Math.floor(posInsideY / 10);
                hoveredNode = possibleNodes.get(Math.max(0, Math.min(idx, possibleNodes.size() - 1)));
                setTooltip(Tooltip.create(Component.literal(hoveredNode.toString())));
                return true;
            }
        }

        setTooltip(null);
        hoveredNode = null;
        return listHovered;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if(!isHovered) {
            var screen = (ConfigureDialogueScreen) client.screen;
            screen.selectingNewNode = false;
            screen.init(client, screen.width, screen.height);
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        System.out.println("test");
        super.onClick(mouseX, mouseY);
    }
}