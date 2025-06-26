package generations.gg.generations.core.generationscore.common.util.extensions

import com.cobblemon.mod.common.api.battles.model.PokemonBattle
import com.cobblemon.mod.common.api.types.tera.TeraType
import com.cobblemon.mod.common.battles.BattleRegistry
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.pokemon.Pokemon

import java.util.UUID

val PokemonEntity.battleTeraType: TeraType?
    get() = this.battleId?.let(BattleRegistry::getBattle)?.findActiveEffectPokemon(this.uuid)?.teraType

fun PokemonBattle.findActiveEffectPokemon(uuid: UUID): Pokemon? = this.activePokemon.map { it.battlePokemon }.filterNotNull().filter { it.entity?.uuid == uuid }.map { it.effectedPokemon }.firstOrNull()
