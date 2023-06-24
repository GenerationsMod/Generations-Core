package generations.gg.generations.core.generationscore.util

import com.cobblemon.mod.common.api.pokemon.evolution.Evolution
import com.cobblemon.mod.common.pokemon.evolution.adapters.CobblemonEvolutionAdapter
import com.google.gson.*
import generations.gg.generations.core.generationscore.world.dialogue.nodes.AbstractNode
import java.lang.reflect.Type
import kotlin.reflect.KClass

abstract class Adapter<T: Any>(private val variant: String): JsonDeserializer<T>, JsonSerializer<T> {

    private val types = mutableMapOf<String, KClass<out T>>()

    val size = types.size

    val ids = types.keys;

    fun <K:T> registerType(id: String, type: KClass<K>) {
        this.types[id.lowercase()] = type
    }

    override fun deserialize(jsonIn: JsonElement, typeOfT: Type, context: JsonDeserializationContext): T? {
        val json = jsonIn.asJsonObject
        val variant = json.get(variant).asString.lowercase()
        val type = this.types[variant] ?: throw IllegalArgumentException("Cannot resolve type for variant $variant")
        return context.deserialize(json, type.java)
    }

    override fun serialize(src: T, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        val json = context.serialize(src, src::class.java).asJsonObject
        val variant = getId(src) ?: throw IllegalArgumentException("Cannot resolve variant for type ${src::class.qualifiedName}")
        json.addProperty(variant, variant)
        return json
    }

    fun getId(src: T): String? = this.types.entries.find { it.value == src::class }?.key
}