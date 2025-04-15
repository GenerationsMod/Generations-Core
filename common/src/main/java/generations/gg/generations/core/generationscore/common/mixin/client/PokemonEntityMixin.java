package generations.gg.generations.core.generationscore.common.mixin.client;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import generations.gg.generations.core.generationscore.common.client.render.CobblemonInstanceProvider;
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.CobblemonInstance;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PokemonEntity.class)
public abstract class PokemonEntityMixin implements CobblemonInstanceProvider {
    private CobblemonInstance instance;

    public CobblemonInstance getInstance() {
        if (instance == null) {
            instance = new CobblemonInstance(new Matrix4f(), new Matrix4f(), null);
        }

        return instance;
    }

    private PokemonEntity self() {
        return (PokemonEntity) (Object) this;
    }

}