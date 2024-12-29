package generations.gg.generations.core.generationscore.common.world.loot

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.config.SpeciesKey
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.level.storage.loot.LootContext
import net.minecraft.world.level.storage.loot.Serializer
import net.minecraft.world.level.storage.loot.parameters.LootContextParams
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType

@JvmRecord
data class SpeciesKeyCondition(val key: SpeciesKey) : LootItemCondition {
    override fun getType(): LootItemConditionType {
        return LootItemConditionTypes.SPECIES_KEY.get()
    }

    override fun test(lootContext: LootContext): Boolean {
        val entity = lootContext.getParamOrNull(LootContextParams.THIS_ENTITY)

        if (entity is ServerPlayer) {
            return GenerationsCore.CONFIG.caught.capped(entity, key)
        } else {
            return false
        }
    }

    class Serializer<T> : net.minecraft.world.level.storage.loot.Serializer<SpeciesKeyCondition> {
        override fun serialize(
            json: JsonObject,
            value: SpeciesKeyCondition,
            serializationContext: JsonSerializationContext,
        ) {
            json.addProperty("key", value.key.toString())
        }

        override fun deserialize(
            json: JsonObject,
            serializationContext: JsonDeserializationContext,
        ): SpeciesKeyCondition = json.getAsString("key").let { SpeciesKey.fromString(it) }.let { SpeciesKeyCondition(it) }
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
