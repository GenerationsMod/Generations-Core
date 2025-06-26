package generations.gg.generations.core.generationscore.common.client.render.entity

import com.cobblemon.mod.common.client.render.layer.PokemonOnShoulderRenderer
import net.minecraft.nbt.CompoundTag
import java.util.*

interface PokemonOnShoulderRenderAccess {
    fun invokeExtractUuid(tag: CompoundTag?): UUID?
    fun invokeExtractData(shoulderNbt: CompoundTag?, pokemonUUID: UUID?): PokemonOnShoulderRenderer.ShoulderData?
}
