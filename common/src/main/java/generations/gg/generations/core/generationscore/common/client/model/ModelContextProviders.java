package generations.gg.generations.core.generationscore.common.client.model;

import gg.generations.rarecandy.renderer.rendering.ObjectInstance;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

public class ModelContextProviders {
    public interface AngleProvider {
        float getAngle();
    }

    public interface VariantProvider extends ModelProvider {
        @Nullable String getVariant();
    }

    public interface ModelProvider {
        @Nullable ResourceLocation getModel();

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

    public interface TintProvider {
        @Nullable Vector3f getTint();
    }

}
