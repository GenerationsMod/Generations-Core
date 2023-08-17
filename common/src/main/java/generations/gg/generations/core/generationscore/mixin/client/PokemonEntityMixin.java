package generations.gg.generations.core.generationscore.mixin.client;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import generations.gg.generations.core.generationscore.client.render.PixelmonInstanceProvider;
import generations.gg.generations.core.generationscore.client.render.rarecandy.LightingSettings;
import generations.gg.generations.core.generationscore.client.render.rarecandy.PixelmonInstance;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PokemonEntity.class)
public class PokemonEntityMixin implements PixelmonInstanceProvider {
    private PixelmonInstance instance;

    public PixelmonInstance getInstance() {
        if (instance == null)
            instance = new PixelmonInstance(new Matrix4f(), new Matrix4f(), null, () -> LightingSettings.NORMAL_SHADING);

        return instance;
    }

    @Override
    public void setInstance(PixelmonInstance instance) {
        this.instance = instance;
    }
}