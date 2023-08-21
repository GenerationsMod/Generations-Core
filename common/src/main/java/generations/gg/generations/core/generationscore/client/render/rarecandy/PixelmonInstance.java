package generations.gg.generations.core.generationscore.client.render.rarecandy;

import gg.generations.rarecandy.storage.AnimatedObjectInstance;
import org.joml.Matrix4f;

import java.util.function.Supplier;

public class PixelmonInstance extends AnimatedObjectInstance implements BlockLightValueProvider {

    public final Supplier<LightingSettings> settingsSupplier;

    public Matrix4f[] matrixTransforms;
    private int light;

    public PixelmonInstance(Matrix4f transformationMatrix, Matrix4f viewMatrix, String materialId, Supplier<LightingSettings> settingsSupplier) {
        super(transformationMatrix, viewMatrix, materialId);
        this.settingsSupplier = settingsSupplier;
    }

    public int lightColor() {
        return settingsSupplier.get().lightColor();
    }

    public float reflectivity() {
        return settingsSupplier.get().reflectivity();
    }

    public float shineDamper() {
        return settingsSupplier.get().shineDamper();
    }

    public float diffuseColorMix() {
        return settingsSupplier.get().diffuseColorMix();
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