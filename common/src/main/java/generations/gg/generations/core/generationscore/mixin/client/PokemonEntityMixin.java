package generations.gg.generations.core.generationscore.mixin.client;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import generations.gg.generations.core.generationscore.client.render.PixelmonInstanceProvider;
import generations.gg.generations.core.generationscore.client.render.rarecandy.LightingSettings;
import generations.gg.generations.core.generationscore.client.render.rarecandy.PixelmonInstance;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Set;

@Mixin(PokemonEntity.class)
public abstract class PokemonEntityMixin implements PixelmonInstanceProvider {
    private PixelmonInstance instance;

    public PixelmonInstance getInstance() {
        if (instance == null)
            instance = new PixelmonInstance(new Matrix4f(), new Matrix4f(), null, () -> LightingSettings.NORMAL_SHADING);

        return instance;
    }

    private PokemonEntity self() {
        return (PokemonEntity) (Object) this;
    }

    @Override
    public void setInstance(PixelmonInstance instance) {
        this.instance = instance;
    }

    @Override
    public Set<String> aspects() {
        return self().getPokemon().getAspects();
    }

    @Override
    public ResourceLocation species() {
        return self().getPokemon().getSpecies().getResourceIdentifier();
    }
}