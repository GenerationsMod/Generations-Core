package generations.gg.generations.core.generationscore.client;

import com.cobblemon.mod.common.client.entity.PokemonClientDelegate;
import com.cobblemon.mod.common.client.render.models.blockbench.PoseableEntityState;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import generations.gg.generations.core.generationscore.client.render.PixelmonInstanceProvider;
import generations.gg.generations.core.generationscore.client.render.rarecandy.LightingSettings;
import generations.gg.generations.core.generationscore.client.render.rarecandy.PixelmonInstance;
import generations.gg.generations.core.generationscore.world.entity.StatueEntity;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;

import java.util.Set;

public class StatueEntityClient extends PoseableEntityState<PokemonEntity> implements PixelmonInstanceProvider {
    private PixelmonInstance pixelmonInstance;

    @Override
    public void updatePartialTicks(float v) {
        setCurrentPartialTicks(getPartialTicks() + v);
    }

    public void setCurrentTicks(float v) {
        setCurrentPartialTicks(v);
    }

    public PixelmonInstance getInstance() {
        if (pixelmonInstance == null)
            pixelmonInstance = new PixelmonInstance(new Matrix4f(), new Matrix4f(), null, () -> LightingSettings.NORMAL_SHADING);

        return pixelmonInstance;
    }

    @Override
    public void setInstance(PixelmonInstance instance) {
        pixelmonInstance = instance;
    }

    @Override
    public ResourceLocation species() {
        return null;
    }

    @Override
    public Set<String> aspects() {
        return null;
    }

    @Nullable
    @Override
    public PokemonEntity getEntity() {
        return null;
    }
}
