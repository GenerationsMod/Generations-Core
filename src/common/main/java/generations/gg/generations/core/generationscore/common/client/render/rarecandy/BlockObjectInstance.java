package generations.gg.generations.core.generationscore.common.client.render.rarecandy;

import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders;
import gg.generations.rarecandy.renderer.rendering.ObjectInstance;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class BlockObjectInstance extends ObjectInstance implements BlockLightValueProvider, ModelContextProviders.TintProvider {
    private int light = 0x000000;
    private Vector3f tint = null;

    public BlockObjectInstance(Matrix4f transformationMatrix, String variant) {
        super(transformationMatrix, variant);
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
