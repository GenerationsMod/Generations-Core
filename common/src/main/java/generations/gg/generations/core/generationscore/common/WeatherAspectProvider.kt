package generations.gg.generations.core.generationscore.common

import com.cobblemon.mod.common.api.pokemon.PokemonProperties
import com.cobblemon.mod.common.api.pokemon.aspect.AspectProvider
import com.cobblemon.mod.common.pokemon.Pokemon
import net.minecraft.core.BlockPos
import net.minecraft.world.level.Level
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.Biome.Precipitation

val WEATHER_ASPECT = object : AspectProvider {
    override fun provide(properties: PokemonProperties): Set<String> {
        return emptySet()
    }

    override fun provide(pokemon: Pokemon): Set<String> {
        return (pokemon.entity ?: pokemon.getOwnerPlayer())?.let {
            when (it.level().getPreceptiation(it.blockPosition())) {
                Precipitation.NONE -> {
                    if(it.level().isDay) "sunny" else null
                }
                Precipitation.RAIN -> "rainy"
                Precipitation.SNOW -> "snowy"
            }

        }?.let { setOf(it) }?: return emptySet()
    }

    private fun Level.getPreceptiation(pos: BlockPos): Precipitation {
        return if (!this.isRaining) {
            Precipitation.NONE
        } else {
            val biome = getBiome(pos).value() as Biome
            biome.getPrecipitationAt(pos)
        }
    }
}
