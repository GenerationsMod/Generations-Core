package generations.gg.generations.core.generationscore.common.world.loot

import com.google.gson.JsonObject
import com.mojang.serialization.codecs.RecordCodecBuilder
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.config.SpeciesKey
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.level.storage.loot.LootContext
import net.minecraft.world.level.storage.loot.parameters.LootContextParams
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType

@JvmRecord
data class SpeciesKeyCondition(val key: SpeciesKey) : LootItemCondition {
    override fun getType(): LootItemConditionType {
        return LootItemConditionTypes.SPECIES_KEY.value()
    }

    override fun test(lootContext: LootContext): Boolean {
        val entity = lootContext.getParamOrNull(LootContextParams.THIS_ENTITY)

        if (entity is ServerPlayer) {
            return GenerationsCore.CONFIG!!.caught.capped(entity, key)
        } else {
            return false
        }
    }

    companion object {
        @JvmField
        val CODEC = RecordCodecBuilder.mapCodec { it.group(
            SpeciesKey.CODEC.fieldOf("key").forGetter(SpeciesKeyCondition::key)
        ).apply(it, ::SpeciesKeyCondition) }
    }

    public class Builder : LootItemCondition.Builder {
        lateinit var key: SpeciesKey
        fun key(key: SpeciesKey): Builder {
            this.key = key
            return this
        }
        override fun build(): LootItemCondition {
            return SpeciesKeyCondition(key)
        }

    }
}

private fun JsonObject.getAsString(name: String) = this.getAsJsonPrimitive(name).asString
