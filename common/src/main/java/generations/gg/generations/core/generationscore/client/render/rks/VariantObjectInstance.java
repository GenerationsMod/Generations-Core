package generations.gg.generations.core.generationscore.client.render.rks;

import com.thepokecraftmod.rks.storage.AnimatedObjectInstance;
import com.thepokecraftmod.rks.texture.RenderMaterial;
import org.joml.Matrix4f;

public class VariantObjectInstance extends AnimatedObjectInstance {
    public final String variant;

    public VariantObjectInstance(int boneCount, Matrix4f transformationMatrix, RenderMaterial materialId, String variant) {
        super(boneCount, transformationMatrix, materialId);
        this.variant = variant;
    }
}
