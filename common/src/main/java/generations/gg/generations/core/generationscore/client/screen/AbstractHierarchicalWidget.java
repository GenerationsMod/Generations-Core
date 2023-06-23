package generations.gg.generations.core.generationscore.client.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractHierarchicalWidget extends AbstractWidget implements HierarchicalWidget {
    protected final Minecraft client = Minecraft.getInstance();
    protected final List<HierarchicalWidget> children = new ArrayList<>();
    private final int relativeX;
    private final int relativeY;
    private final HierarchicalWidget parent;

    public AbstractHierarchicalWidget(int relativeX, int relativeY, int width, int height, HierarchicalWidget parent) {
        super(parent == null ? relativeX : relativeX + parent.getX(), parent == null ? relativeY : relativeY + parent.getY(), width, height, CommonComponents.EMPTY);
        this.relativeX = relativeX;
        this.relativeY = relativeY;
        this.parent = parent;
    }

    public void finishRendering(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        for (HierarchicalWidget child : getChildren()) {
            if (child instanceof Renderable rendererChild) {
                rendererChild.render(poseStack, mouseX, mouseY, partialTick);
            }
        }
    }

    @Override
    public HierarchicalWidget getParent() {
        return parent;
    }

    @Override
    public List<HierarchicalWidget> getChildren() {
        return children;
    }

    @Override
    public void render(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        super.render(poseStack, mouseX, mouseY, partialTick);
        if (this.visible) {
            this.isHovered = isMouseOver(mouseX, mouseY);
        }
    }

    @Override
    public void onParentMove(int parentX, int parentY) {
        setX(relativeX + parentX);
        setY(relativeY + parentY);
    }

    @Override
    public void setX(int x) {
        super.setX(x);

        for (var child : getChildren()) {
            child.onParentMove(getX(), getY());
        }
    }

    @Override
    public void setY(int y) {
        super.setY(y);

        for (var child : getChildren()) {
            child.onParentMove(getX(), getY());
        }
    }

    @Override
    public void setPosition(int x, int y) {
        super.setPosition(x, y);

        for (var child : getChildren()) {
            child.onParentMove(getX(), getY());
        }
    }

    @Override
    protected void updateWidgetNarration(@NotNull NarrationElementOutput narrationElementOutput) {}

    @Override
    public void renderWidget(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTick) {}

    @Override
    public void onScreenClose(Screen screen) {
        for (HierarchicalWidget child : getChildren()) {
            child.onScreenClose(screen);
        }
    }
}