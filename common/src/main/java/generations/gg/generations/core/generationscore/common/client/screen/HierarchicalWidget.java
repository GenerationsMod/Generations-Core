package generations.gg.generations.core.generationscore.common.client.screen;

import net.minecraft.client.gui.screens.Screen;

import java.util.List;

public interface HierarchicalWidget {

    List<HierarchicalWidget> getChildren();

    HierarchicalWidget getParent();

    void onParentMove(int parentX, int parentY);

    int getX();

    int getY();

    void onScreenClose(Screen screen);

    default boolean onScreenMouseRelease(double mouseX, double mouseY, int button) {
        for (HierarchicalWidget child : getChildren()) {
            if (!child.onScreenMouseRelease(mouseX, mouseY, button)) {
                return false;
            }
        }

        return true;
    }

    default HierarchicalWidget getRootParent() {
        var currentParent = getParent();
        while (currentParent != null && currentParent.getParent() != null) currentParent = currentParent.getParent();
        return currentParent == null ? this : currentParent;
    }

    void setX(int x);

    void setY(int y);

    int getWidth();

    int getHeight();

    default int getMaxX() {
        int currentMaxX = getX() + getWidth();
        for (var child : getChildren()) currentMaxX = Math.max(currentMaxX, child.getMaxX());
        return currentMaxX;
    }

    default int getMaxY() {
        int currentMaxY = getY() + getHeight();
        for (var child : getChildren()) currentMaxY = Math.max(currentMaxY, child.getMaxY());
        return currentMaxY;
    }
}