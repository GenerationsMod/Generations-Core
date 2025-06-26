package generations.gg.generations.core.generationscore.common.client.screen.widget;

import com.mojang.math.Axis;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

import java.util.function.BiConsumer;

public class AngleSelectionWidget extends AbstractWidget {
    private final int radius, color;
    private final int centerX, centerY;
    private final BiConsumer<Float, Float> onAngleChanged;

    private float lowerLimit, upperLimit;
    private float angle;

    public AngleSelectionWidget(int x, int y, int radius, float initialAngle, int padding, int color, BiConsumer<Float, Float> onAngleChanged) {
        this(x, y, radius, initialAngle, 0, 360, padding, color, onAngleChanged);
    }

    public AngleSelectionWidget(int x, int y, int radius, float initialAngle, float lowerLimit, float upperLimit, int padding, int color, BiConsumer<Float, Float> onAngleChanged) {
        super(x - padding, y - padding, radius * 2 + 1 + padding * 2, radius * 2 + 1 + padding * 2, Component.empty());
        this.angle = initialAngle % 360;
        this.radius = radius;
        this.centerX = x + radius;
        this.centerY = y + radius;
        this.lowerLimit = lowerLimit == 360 ? 360 : lowerLimit % 360;
        this.upperLimit = upperLimit == 360 ? 360 : upperLimit % 360;
        this.color = 0xFF000000 | color;
        this.onAngleChanged = onAngleChanged;
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        setAngle(getAngle(centerX, centerY, mouseX, mouseY));
    }

    @Override
    protected void onDrag(double mouseX, double mouseY, double dragX, double dragY) {
        setAngle(getAngle(centerX, centerY, mouseX, mouseY));
    }


    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        graphics.pose().pushPose();

        graphics.pose().translate(centerX, centerY, 0.0);
        drawCircle(graphics, 0, 0, radius, color);
        graphics.pose().translate(0.5, 0.5, 0.0);
        graphics.pose().mulPose(Axis.ZP.rotationDegrees(angle));
        graphics.pose().translate(-0.5, -0.5, 0.0);
        graphics.vLine(0, 0, -radius+1, color);
        drawCircle(graphics, 0, -radius, 2, color);
        graphics.pose().popPose();
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {

    }

    public float getAngle() {
        return angle % 360;
    }

    public void setAngle(float angle) {
        float prevAngle = getAngle();
        this.angle = clampAngle(angle == 360 ? 360 : angle % 360);
        this.onAngleChanged.accept(prevAngle, getAngle());
    }

    public float getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(float lowerLimit) {
        this.lowerLimit = lowerLimit == 360 ? 360 : (lowerLimit + 360) % 360;
    }

    public float getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(float upperLimit) {
        this.upperLimit = upperLimit == 360 ? 360 : (upperLimit + 360) % 360;
    }

    private float clampAngle(float angle) {
        if (lowerLimit < upperLimit) {
            if (angle >= lowerLimit && angle <= upperLimit) {
                return angle;
            }
            float centerOpposite = (180 + ((lowerLimit + upperLimit) / 2)) % 360;
            if ((angle < lowerLimit && lowerLimit < 180) || angle > centerOpposite) {
                return lowerLimit;
            }
            return upperLimit;
        } else {
            if (angle < lowerLimit && angle > upperLimit) {
                float centerOpposite = ((lowerLimit + upperLimit) / 2) % 360;
                if (angle >= centerOpposite) {
                    return lowerLimit;
                }
                return upperLimit;
            } else {
                return angle;
            }
        }
    }

    private static float getAngle(double centerX, double centerY, double targetX, double targetY) {
        double angle = Math.toDegrees(Math.atan2(targetY - centerY, targetX - centerX));
        angle += Math.ceil(-angle / 360) * 360 + 90;
        return (float) angle;
    }

    private static void drawCircle(GuiGraphics poseStack, int centerX, int centerY, int radius, int color) {
        int x = 0;
        int y = radius;
        float num = 1f - radius;
        drawCirclePixels(poseStack, centerX, centerY, x, y, color);
        while (y > x) {
            if (num < 0) {
                num += 2 * x + 3;
            }
            else {
                num += 2 * (x - y) + 5;
                --y;
            }
            ++x;
            drawCirclePixels(poseStack, centerX, centerY, x, y, color);
        }
    }

    private static void drawCirclePixels(GuiGraphics poseStack, int cx, int cy, int x, int y, int color) {
        drawPixel(poseStack, cx + x, cy + y, color);
        drawPixel(poseStack, cx + x, cy - y, color);
        drawPixel(poseStack, cx - x, cy - y, color);
        drawPixel(poseStack, cx - x, cy + y, color);
        drawPixel(poseStack, cx + y, cy + x, color);
        drawPixel(poseStack, cx + y, cy - x, color);
        drawPixel(poseStack, cx - y, cy - x, color);
        drawPixel(poseStack, cx - y, cy + x, color);
    }

    private static void drawPixel(GuiGraphics poseStack, int x, int y, int color) {
        poseStack.fill(x, y, x + 1, y + 1, color);
    }
}