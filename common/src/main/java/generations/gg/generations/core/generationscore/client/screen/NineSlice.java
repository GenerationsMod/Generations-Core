package generations.gg.generations.core.generationscore.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.ResourceLocation;

/*
    Nine Slice code inspired by implmentation seen in the Moasic Module of LibrarianLib
 */
public class NineSlice extends SubTexture {
    private int left;
    private int right;
    private int top;
    private int bottom;

    public NineSlice(int u, int v, int width, int height, ResourceLocation sheet, int left, int right, int top, int down) {
        super(u, v, width, height, sheet);
        setUpSlices(left, right, top, down);
    }

    private void setUpSlices(int left, int right, int top, int down) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = down;
    }

    public NineSlice(int u, int v, int width, int height, ResourceLocation sheet, int scale, int left, int right, int up, int down) {
        super(u, v, width, height, sheet, scale);
        setUpSlices(left, right, up, down);
    }

    public void renderStretched(PoseStack stack, int x, int y, float widthMultiplier, float heightMultiplier) {
        RenderSystem.setShaderTexture(0, this.sheet);
        renderComplex(stack, x, y, (int) (this.width * widthMultiplier), (int) (this.height * heightMultiplier));
    }

    public void renderAnchoredStretched(PoseStack stack, int x, int y, float widthMultiplier, float heightMultiplier, Anchor anchor) {
        stack.pushPose();
        anchor.process(stack, x, y, width, height, widthMultiplier, heightMultiplier);
        RenderSystem.setShaderTexture(0, this.sheet);
        renderComplex(stack, 0, 0, width, height);
        stack.popPose();
    }

    public void render(PoseStack stack, int x, int y, int width, int height) {
        RenderSystem.setShaderTexture(0, this.sheet);
        renderComplex(stack, x, y, width, height);
    }

    @Override
    public void renderAnchored(PoseStack stack, int x, int y, Anchor anchor) {
        stack.pushPose();
        anchor.process(stack, x, y, width, height);
        RenderSystem.setShaderTexture(0, this.sheet);
        ScreenUtils.drawTexture(stack, 0, 0, width, height, this.u, this.v, this.uWidth, this.vHeight, this.sheetScale, this.sheetScale);
        stack.popPose();
    }

    @Override
    protected void renderInner(PoseStack stack, int x, int y, int texWidth, int texHeight) {
        RenderSystem.setShaderTexture(0, this.sheet);
        renderComplex(stack, x, y, texWidth, texHeight);
    }

    protected void renderComplex(PoseStack stack, int x, int y, int width, int height) {
        //TODO: Replace with vanilla nineslice
        section(stack, NineSliceEquations.FIRST, NineSliceEquations.FIRST, x, y, width, height, u, v, uWidth, vHeight, left, right, top, bottom, sheetScale);
        section(stack, NineSliceEquations.FIRST, NineSliceEquations.SECOND, x, y, width, height, u, v, uWidth, vHeight, left, right, top, bottom, sheetScale);
        section(stack, NineSliceEquations.FIRST, NineSliceEquations.THIRD, x, y, width, height, u, v, uWidth, vHeight, left, right, top, bottom, sheetScale);
        section(stack, NineSliceEquations.SECOND, NineSliceEquations.FIRST, x, y, width, height, u, v, uWidth, vHeight, left, right, top, bottom, sheetScale);
        section(stack, NineSliceEquations.SECOND, NineSliceEquations.SECOND, x, y, width, height, u, v, uWidth, vHeight, left, right, top, bottom, sheetScale);
        section(stack, NineSliceEquations.SECOND, NineSliceEquations.THIRD, x, y, width, height, u, v, uWidth, vHeight, left, right, top, bottom, sheetScale);
        section(stack, NineSliceEquations.THIRD, NineSliceEquations.FIRST, x, y, width, height, u, v, uWidth, vHeight, left, right, top, bottom, sheetScale);
        section(stack, NineSliceEquations.THIRD, NineSliceEquations.SECOND, x, y, width, height, u, v, uWidth, vHeight, left, right, top, bottom, sheetScale);
        section(stack, NineSliceEquations.THIRD, NineSliceEquations.THIRD, x, y, width, height, u, v, uWidth, vHeight, left, right, top, bottom, sheetScale);
    }

    protected void section(PoseStack stack, NineSliceEquation horizontal, NineSliceEquation vertical, int x, int y, int width, int height, int u, int v, int uWidth, int vHeight, int left, int right, int top, int bottom, int sheetSize) {
        ScreenUtils.drawTexture(stack,
                horizontal.getPos(x, width, left, right),
                vertical.getPos(y, height, top, bottom),
                horizontal.getSize(width, left, right),
                vertical.getSize(height, top, bottom),
                horizontal.getPos(u, uWidth, left, right),
                vertical.getPos(v, vHeight, top, bottom),
                horizontal.getSize(uWidth, left, right),
                vertical.getSize(vHeight, top, bottom),
                sheetSize, sheetSize);
    }


    private enum NineSliceEquations implements NineSliceEquation {
        FIRST {
            @Override
            public int getPos(int start, int length, int firstSlice, int secondSlice) {
                return start;
            }

            @Override
            public int getSize(int length, int firstSlice, int secondSlice) {
                return firstSlice;
            }
        },
        SECOND {
            @Override
            public int getPos(int start, int length, int firstSlice, int secondSlice) {
                return start + firstSlice;
            }

            @Override
            public int getSize(int length, int firstSlice, int secondSlice) {
                return length - (firstSlice + secondSlice);
            }

            @Override
            public boolean isRepeated() {
                return true;
            }
        },
        THIRD {
            @Override
            public int getPos(int start, int length, int firstSlice, int secondSlice) {
                return start + length - secondSlice;
            }

            @Override
            public int getSize(int length, int firstSlice, int secondSlice) {
                return secondSlice;
            }
        }
    }

    public interface NineSliceEquation {
        int getPos(int start, int length, int firstSlice, int secondSlice);
        int getSize(int length, int firstSlice, int secondSlice);
        default boolean isRepeated() { return false; }
    }
}