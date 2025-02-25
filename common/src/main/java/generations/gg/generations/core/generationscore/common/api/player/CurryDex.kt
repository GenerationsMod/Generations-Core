package generations.gg.generations.core.generationscore.common.api.player

import com.cobblemon.mod.common.Cobblemon.playerData
import com.cobblemon.mod.common.api.berry.Flavor
import com.cobblemon.mod.common.util.party
import com.google.gson.JsonObject
import com.mojang.serialization.Codec
import com.mojang.serialization.JsonOps
import com.mojang.serialization.codecs.RecordCodecBuilder
import generations.gg.generations.core.generationscore.common.api.events.CurryEvents
import generations.gg.generations.core.generationscore.common.api.events.CurryEvents.AddEntry
import generations.gg.generations.core.generationscore.common.util.GenerationsUtils
import generations.gg.generations.core.generationscore.common.world.item.curry.CurryData
import generations.gg.generations.core.generationscore.common.world.item.curry.CurryTasteRating
import generations.gg.generations.core.generationscore.common.world.item.curry.CurryType
import net.minecraft.core.BlockPos
import net.minecraft.core.registries.Registries
import net.minecraft.locale.Language
import net.minecraft.resources.ResourceKey
import net.minecraft.server.level.ServerPlayer
import net.minecraft.util.StringRepresentable
import net.minecraft.world.level.biome.Biome
import java.time.Instant
import java.util.*
import java.util.function.*
import kotlin.Boolean
import kotlin.Comparator
import kotlin.Int
import kotlin.String

class CurryDex @JvmOverloads constructor(var entries: MutableList<CurryDexEntry> = ArrayList()) :
    PlayerDataExtension() {
    init {
        sort()
    }

    fun add(entry: CurryDexEntry) {
        if (entries.stream().noneMatch { a: CurryDexEntry ->
                comparator.compare(
                    entry,
                    a
                ) == 0
            } || entry.type == CurryType.Gigantamax && entries.stream()
                .noneMatch { a: CurryDexEntry -> a.type == CurryType.Gigantamax }) {
            entries.add(entry)
            sort()
        }
    }

    private fun sort() = entries.sortWith(comparator)

    fun clear() = entries.clear()

    fun getEntry(id: Int): CurryDexEntry {
        return entries[id]
    }

    fun setToRead() {
        entries.forEach(Consumer { entry: CurryDexEntry -> entry.newEntry = false })
    }


    val currentTaste: CurryTasteRating
        get() {
            val size = entries.size
            return when {
                size >= 151 -> CurryTasteRating.Charizard
                size >= 100 -> CurryTasteRating.Copperajah
                size >= 50 -> CurryTasteRating.Milcery
                size >= 10 -> CurryTasteRating.Wobbuffet
                else -> CurryTasteRating.Koffing
            }
        }

    data class CurryDexEntry(var instant: Long, var pokemonName: String, var biome: ResourceKey<Biome>, var pos: BlockPos, var newEntry: Boolean = true, var type: CurryType= CurryType.None, var flavor: Flavor?, var rating: CurryTasteRating) {

        companion object {
            @JvmField val CODEC: Codec<CurryDexEntry> = RecordCodecBuilder.create { instance ->
                instance.group(
                    Codec.LONG.fieldOf("instant").forGetter { it.instant },
                    Codec.STRING.fieldOf("pokeName").forGetter { it.pokemonName },
                    ResourceKey.codec(Registries.BIOME).fieldOf("biome").forGetter { it.biome },
                    BlockPos.CODEC.fieldOf("pos").forGetter { it.pos },
                    Codec.BOOL.fieldOf("newEntry").forGetter { it.newEntry },
                    StringRepresentable.fromEnum(CurryType::values).fieldOf("type").forGetter { it.type },
                    Codec.STRING.xmap({it.uppercase()}, {it.lowercase()}).xmap({ Flavor.valueOf(it) }, { it.name }).nullableCodec().fieldOf("flavor").forGetter { it.flavor },
                    StringRepresentable.fromEnum(CurryTasteRating::values).fieldOf("rating").forGetter { it.rating },
                ).apply(instance) { instant, pokemonName, biome, pos, newEntry, type, flavor, rating -> CurryDexEntry(instant, pokemonName, biome, pos, newEntry, type, flavor, rating) }
            }
        }


        override fun toString(): String {
            var name = Language.getInstance().getOrDefault("item.curry.name")
            if (type != CurryType.None) name = type.localizedName + " " + name
            if (flavor != null && type != CurryType.Gigantamax) name =
                GenerationsUtils.getFlavorLocalizedName(flavor) + " " + name
            return name
        }

        val description: String
            get() = Language.getInstance().getOrDefault(("gui.currydex.description.${flavor?.name?.lowercase()?.underscorePrefix()}${if (type != CurryType.None) type.name.lowercase().underscorePrefix() else ""}curry"))
    }

    override fun name(): String {
        return KEY
    }

    override fun serialize(): JsonObject {
        val json = super.serialize()
        CODEC.encode(this, JsonOps.INSTANCE, json)
        return json
    }

    override fun deserialize(jsonObject: JsonObject): PlayerDataExtension {
        return CODEC.decode(JsonOps.INSTANCE, jsonObject).getOrThrow(false, System.out::println).first
    }

    companion object {
        @JvmField
        var KEY: String = "curry_dex"

        @JvmField val CODEC: Codec<CurryDex> = RecordCodecBuilder.create { instance -> instance.group(
                CurryDexEntry.CODEC.listOf().fieldOf("entries").forGetter { it.entries }
            ).apply(instance, ::CurryDex)
        }

        private val comparator: Comparator<CurryDexEntry> =
            Comparator.comparingInt { a: CurryDexEntry -> a.type!!.ordinal }
                .thenComparingInt { a: CurryDexEntry -> a.flavor!!.ordinal }

        fun of(player: ServerPlayer): CurryDex {
            return playerData.get(player).extraData.computeIfAbsent(KEY) { key: String -> CurryDex() }.let { it as CurryDex }
        }

        fun add(player: ServerPlayer, data: CurryData?) {
            val dex = of(player)

            if (data != null) {
                val dexEntry = CurryDexEntry(
                    Instant.now().epochSecond,
                    player.party().firstOrNull()?.let { it.getDisplayName() }?.let { it.string } ?: "n/a",
                    player.level().getBiome(player.onPos).unwrapKey().get(),
                    player.onPos,
                    true,
                    data.curryType,
                    data.flavor,
                    data.rating)

                val event = AddEntry(player, data, dexEntry)

                if (!CurryEvents.ADD_ENTRY.invoker().act(event).isTrue) dex.add(event.entry)
            }
        }
    }
}

private fun String.underscorePrefix(): String = this + "_"

private fun <A: Any> Codec<A>.nullableCodec(): Codec<A?> {
    return this.orElse(null)
}
