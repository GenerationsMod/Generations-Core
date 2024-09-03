package generations.gg.generations.core.generationscore.common

import com.cobblemon.mod.common.api.pokemon.PokemonProperties
import com.cobblemon.mod.common.api.pokemon.aspect.AspectProvider
import com.cobblemon.mod.common.api.pokemon.feature.FlagSpeciesFeature
import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature
import com.cobblemon.mod.common.api.pokemon.feature.SynchronizedSpeciesFeatureProvider
import com.cobblemon.mod.common.api.pokemon.helditem.HeldItemProvider
import com.cobblemon.mod.common.api.properties.CustomPokemonPropertyType
import com.cobblemon.mod.common.client.gui.summary.featurerenderers.SummarySpeciesFeatureRenderer
import com.cobblemon.mod.common.pokemon.Pokemon
import com.google.gson.JsonObject
import generations.gg.generations.core.generationscore.common.world.item.getProviderOrNull
import generations.gg.generations.core.generationscore.common.world.item.getProviders
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.resources.ResourceLocation
import kotlin.random.Random

class HeldItemSpeciesFeatureProvider(
    override var keys: List<String> = listOf(),
    var default: String? = null,
    var isAspect: Boolean = true,
    var items: Map<ResourceLocation, String> = mapOf()
    var aspectFormat: String = "{{choice}}"
) : SynchronizedSpeciesFeatureProvider<StringSpeciesFeature>, CustomPokemonPropertyType<StringSpeciesFeature>, AspectProvider {
    override val needsKey get() = true
    override var visible: Boolean = false

    val choices: Set<String>
        get() {
            val set = mutableSetOf<String>().also { it.addAll(items.values) }
            default?.run { set.add(this) }
            return set;
        }

    override fun encode(buffer: FriendlyByteBuf) {
        buffer.writeCollection(keys) { _, value -> buffer.writeUtf(value) }
        buffer.writeNullable(default) { _, value -> buffer.writeUtf(value) }
        buffer.writeBoolean(isAspect)
        buffer.writeMap(items, { _, value -> buffer.writeResourceLocation(value) }, { _, value -> buffer.writeUtf(value)})
    }

    override fun decode(buffer: FriendlyByteBuf) {
        keys = buffer.readList { it.readUtf() }
        default = buffer.readNullable { it.readUtf() }
        isAspect = buffer.readBoolean()
        items = buffer.readMap(::HashMap, { it.readResourceLocation() }, FriendlyByteBuf::readUtf)
    }

    override fun getRenderer(pokemon: Pokemon): SummarySpeciesFeatureRenderer<StringSpeciesFeature>? {
        return null
    }


    override fun invoke(buffer: FriendlyByteBuf, name: String): FlagSpeciesFeature? {
        return if (name in keys) {
            FlagSpeciesFeature(name).also { it.decode(buffer) }
        } else {
            null
        }
    }

    override fun examples() = mutableSetOf<String>().also { it.addAll(items.values) }.also { default?.run { it.add(this) } }

    internal constructor() {
        this.keys = emptyList()
    }

    constructor(keys: List<String>) {
        this.keys = keys
    }

    constructor(keys: List<String>, default: Boolean) {
        this.keys = keys
        this.default = default.toString()
    }

    constructor(vararg keys: String) : this(keys.toList())

    override fun get(pokemon: Pokemon) = pokemon.getFeature<StringSpeciesFeature>(keys.first())

    override fun invoke(pokemon: Pokemon): StringSpeciesFeature? {
        val existing = get(pokemon)
        return if (existing != null && existing.value in choices) {
            existing
        } else {
            fromString(default)
        }
    }

    override fun invoke(nbt: CompoundTag): StringSpeciesFeature? {
        return if (nbt.contains(keys.first())) {
            StringSpeciesFeature(keys.first(), "").also { it.loadFromNBT(nbt) }
        } else null
    }

    override fun invoke(json: JsonObject): StringSpeciesFeature? {
        return if (json.has(keys.first())) {
            StringSpeciesFeature(keys.first(), "").also { it.loadFromJSON(json) }
        } else null
    }

    override fun fromString(value: String?): StringSpeciesFeature? {
        val lower = value?.lowercase()
        if (lower == null || lower !in choices) {
            return null
        }

        return StringSpeciesFeature(keys.first(), lower)
    }

    override fun provide(pokemon: Pokemon): Set<String> {
        return when {
            isAspect -> {
                if (pokemon.getProviders<HeldItemSpeciesFeatureProvider>().any { it == this }) {
                    var feature = get(pokemon)

                    items[pokemon.heldItem().item.builtInRegistryHolder().key().location()]?.let { setOf(it) }
                        ?: default?.let { setOf(it) } ?: emptySet()
                } else {
                    emptySet()
                }
            }

            else {
                emptySet()
            }
        }
    }

    override fun provide(properties: PokemonProperties): Set<String> { //TODO: Finish and enable when PokemonProperties have held item support.
//        return if (isAspect && properties.customProperties.filterIsInstance<StringSpeciesFeature>().find { it.name == keys.first() }?.enabled == true) {
//            default?.let { setOf(it) } ?: emptySet()
//        } else {
            return emptySet()
//        }
    }

    fun create(): StringSpeciesFeature {
        return StringSpeciesFeature(keys.first(), "")
    }
}