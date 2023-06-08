package generations.gg.generations.core.generationscore.client.render.rarecandy;

import gg.generations.rarecandy.rendering.ObjectInstance;
import org.joml.Matrix4f;

public class BlockObjectInstance extends ObjectInstance implements BlockLightValueProvider {
    private int light = 0x000000;

    public BlockObjectInstance(Matrix4f transformationMatrix, Matrix4f viewMatrix, String variant) {
        super(transformationMatrix, viewMatrix, variant);
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
