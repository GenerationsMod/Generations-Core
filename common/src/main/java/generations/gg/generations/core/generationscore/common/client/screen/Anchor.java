package generations.gg.generations.core.generationscore.common.client.screen;

import com.mojang.blaze3d.vertex.PoseStack;

public enum Anchor {
    TOP_LEFT(0, 0),
    TOP_CENTER(0, 0.5f),
    TOP_RIGHT(0, 1.0f),
    CENTER_LEFT(0.5f, 0),
    CENTER_CENTER(0.5f, 0.5f),
    CENTER_RIGHT(0.5f, 1.0f),
    BOTTOM_LEFT(1.0f, 0),
    BOTTOM_CENTER(1.0f, 0.5f),
    BOTTOM_RIGHT(1.0f, 1.0f);

    private final float vertical;
    private final float horizontal;

    Anchor(float vertical, float horizontal) {
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    public void process(PoseStack poseStack, float x, float y, float width, float height, float widthMultiplier, float heightMultiplier) {
        float anchorX = horizontal * width;
        float anchorY = vertical * height;

        poseStack.last().pose()
                .translate(x, y, 0)
                .scale(widthMultiplier, heightMultiplier, 1)
                .translate(-anchorX, -anchorY, 0);
    }

    public void process(PoseStack poseStack, int x, int y, int width, int height) {
        float anchorX = horizontal * width;
        float anchorY = vertical * height;

        poseStack.translate(x - anchorX, y - anchorY, 0);
    }

    public float getX(float x) {
        return horizontal * x;
    }

    public float getY(float y) {
        return vertical * y;
    }
}


