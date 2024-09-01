package generations.gg.generations.core.generationscore.common

import com.cobblemon.mod.common.api.pokemon.PokemonProperties
import com.cobblemon.mod.common.api.pokemon.aspect.AspectProvider
import com.cobblemon.mod.common.pokemon.Pokemon
import net.minecraft.world.entity.LivingEntity

class PlayerAspectProvider : AspectProvider{
    override fun provide(properties: PokemonProperties): Set<String> {
        TODO("Not yet implemented")
    }

    override fun provide(pokemon: Pokemon): Set<String> {
        var entity: LivingEntity? = pokemon.entity ?: pokemon.getOwnerPlayer() ?: return emptySet()

        return setOf<String>().also {
            if(entity.level().isRaining) {

            }
        }


    }

}
