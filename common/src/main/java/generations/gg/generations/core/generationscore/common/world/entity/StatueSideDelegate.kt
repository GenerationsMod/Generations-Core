package generations.gg.generations.core.generationscore.common.world.entity

import com.cobblemon.mod.common.api.entity.EntitySideDelegate
import com.cobblemon.mod.common.api.pokemon.PokemonProperties
import generations.gg.generations.core.generationscore.common.world.entity.statue.StatueEntity

interface StatueSideDelegate : EntitySideDelegate<StatueEntity> {
    fun updatePoke(properties: PokemonProperties) {}
    fun updateMaterial(value: String?) {}
}