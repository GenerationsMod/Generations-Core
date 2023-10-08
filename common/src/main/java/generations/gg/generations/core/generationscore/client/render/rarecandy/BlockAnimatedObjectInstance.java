package generations.gg.generations.core.generationscore.client.render.rarecandy;

import gg.generations.rarecandy.renderer.storage.AnimatedObjectInstance;
import org.joml.Matrix4f;

public class BlockAnimatedObjectInstance extends AnimatedObjectInstance implements BlockLightValueProvider {
    private int light = 0x000000;

    public BlockAnimatedObjectInstance(Matrix4f transformationMatrix, Matrix4f viewMatrix, String materialId) {
        super(transformationMatrix, viewMatrix, materialId);
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
