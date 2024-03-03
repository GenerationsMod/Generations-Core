package generations.gg.generations.core.generationscore.client.render.rarecandy;

import gg.generations.rarecandy.arceus.model.Model;
import gg.generations.rarecandy.arceus.model.pk.MultiRenderObject;
import gg.generations.rarecandy.arceus.model.pk.MultiRenderObjectInstance;
import gg.generations.rarecandy.arceus.model.pk.MultiRenderingInstance;
import gg.generations.rarecandy.arceus.model.pk.PkMaterial;
import org.joml.Matrix4f;

import java.util.function.Supplier;

public class GenerationsObjectInstance extends MultiRenderObjectInstance<MultiRenderObject<?>> implements BlockLightValueProvider {
    private final Matrix4f transformationMatrix;
    public Matrix4f[] matrixTransforms;
    private int light;

    public GenerationsObjectInstance(MultiRenderObject<?> object, Matrix4f transformationMatrix, String materialId) {
        super(object, transformationMatrix, materialId);
        this.transformationMatrix = transformationMatrix;
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

    public Matrix4f transformationMatrix() {
        return transformationMatrix;
    }
}