package generations.gg.generations.core.generationscore.client.render.rarecandy;

import gg.generations.rarecandy.renderer.storage.AnimatedObjectInstance;
import org.joml.Matrix4f;

public class PixelmonInstance extends AnimatedObjectInstance implements BlockLightValueProvider {
    public Matrix4f[] matrixTransforms;
    private int light;

    public PixelmonInstance(Matrix4f transformationMatrix, Matrix4f viewMatrix, String materialId) {
        super(transformationMatrix, viewMatrix, materialId);
    }

    @Override
    public Matrix4f[] getTransforms() {
        return matrixTransforms;
    }

    @Override
    public int getLight() {
        return light;
    }

    @Override
    public void setLight(int light) {
        this.light = light;
    }
}