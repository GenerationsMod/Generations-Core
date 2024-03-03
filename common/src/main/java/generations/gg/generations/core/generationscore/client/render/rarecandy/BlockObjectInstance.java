package generations.gg.generations.core.generationscore.client.render.rarecandy;

import generations.gg.generations.core.generationscore.client.model.ModelContextProviders;
import gg.generations.rarecandy.arceus.model.pk.MultiRenderObject;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class BlockObjectInstance extends GenerationsObjectInstance implements ModelContextProviders.TintProvider {
    private int light = 0x000000;
    private Vector3f tint = null;

    public BlockObjectInstance(MultiRenderObject<?> object, Matrix4f transformationMatrix, String materialId) {
        super(object, transformationMatrix, materialId);
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
