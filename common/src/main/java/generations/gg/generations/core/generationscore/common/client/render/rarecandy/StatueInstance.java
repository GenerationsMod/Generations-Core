package generations.gg.generations.core.generationscore.common.client.render.rarecandy;

import org.joml.Matrix4f;

public class StatueInstance extends CobblemonInstance {
    private String material;

    public StatueInstance(Matrix4f transformationMatrix, Matrix4f viewMatrix, String materialId) {
        super(transformationMatrix, viewMatrix, materialId);
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getMaterial() {
        return material;
    }
}
