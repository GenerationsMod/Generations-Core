package generations.gg.generations.core.generationscore.client.model;

import net.minecraft.resources.ResourceLocation;

public class ModelContextProviders {
    public interface AngleProvider {
        float getAngle();
    }

    public interface VariantProvider extends ModelProvider {
        String getVariant();
    }

    public interface ModelProvider {
        ResourceLocation getModel();

        default boolean isAnimated() {
            return false;
        }

        default String getAnimation() {
            return "";
        }
    }

    public interface FrameProvider extends ModelProvider {
        float getFrame();
    }
}
