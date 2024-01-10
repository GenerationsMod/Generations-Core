package generations.gg.generations.core.generationscore.client.render.rarecandy;

import generations.gg.generations.core.generationscore.client.model.ModelContextProviders;
import gg.generations.rarecandy.renderer.storage.AnimatedObjectInstance;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class BlockAnimatedObjectInstance extends AnimatedObjectInstance implements BlockLightValueProvider, ModelContextProviders.TintProvider {
    private int light = 0x000000;
    private Vector3f tint;

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

    public void setTint(Vector3f tint) {
        this.tint = tint;
    }

    public Vector3f getTint() {
        return tint;
    }
}
