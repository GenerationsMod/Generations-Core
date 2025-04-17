package generations.gg.generations.core.generationscore.common.world.item.components

import com.cobblemon.mod.common.pokemon.Pokemon
import com.cobblemon.mod.common.pokemon.RenderablePokemon
import com.cobblemon.mod.common.pokemon.Species
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import dev.architectury.registry.registries.DeferredRegister
import dev.architectury.registry.registries.RegistrySupplier
import earth.terrarium.common_storage_lib.data.DataManager
import earth.terrarium.common_storage_lib.data.DataManagerRegistry
import earth.terrarium.common_storage_lib.item.util.ItemStorageData
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.util.DataKeys
import generations.gg.generations.core.generationscore.common.world.item.CalyrexSteedItem
import generations.gg.generations.core.generationscore.common.world.item.WalkmonItem
import generations.gg.generations.core.generationscore.common.world.item.curry.CurryData
import net.minecraft.core.component.DataComponentType
import net.minecraft.core.component.DataComponents
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import java.util.function.Supplier

object GenerationsItemComponents {

    var REGISTER = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.DATA_COMPONENT_TYPE)

    var DISTANCE = register<Double>(
        DataKeys.DISTANCE,
        Codec.DOUBLE,
        StreamCodec.of(ByteBufCodecs.DOUBLE::encode, ByteBufCodecs.DOUBLE::decode)
    )
    var USES =
        register<Int>("uses", Codec.INT, StreamCodec.of(ByteBufCodecs.INT::encode, ByteBufCodecs.INT::decode))
    var USED = register<Boolean>(
        "used",
        Codec.BOOL,
        StreamCodec.of(ByteBufCodecs.BOOL::encode, ByteBufCodecs.BOOL::decode)
    )
    var ENCHANTED = register<Boolean>(
        "enchanted",
        Codec.BOOL,
        StreamCodec.of(ByteBufCodecs.BOOL::encode, ByteBufCodecs.BOOL::decode)
    )
    var CARROT_HOLDER =
        register("carrot_holder", ItemStorageData.CODEC.xmap(CalyrexSteedItem.CarrotHolder))
    var DISC_HOLDER = register(DataKeys.DISC_HOLDER, WalkmonItem.DiscHolder.CODEC)
    val CURRY_DATA = register("curry_data", CurryData.CODEC, CurryData.STREAM_CODEC)
    val TM_DETAILS = register("tm_details", TmDetails.CODEC, TmDetails.STREAM_CODEC)
    val EMBEDDED_POKEMON = register("pokemon", Pokemon.CODEC)
    val CLIENT_POKEMON_DATA: RegistrySupplier<DataComponentType<RenderablePokemon>> = register(DataKeys.CLIENT_POKEMON_DATA, RecordCodecBuilder.create {

        it.group(
            Species.BY_IDENTIFIER_CODEC.fieldOf("species").forGetter(RenderablePokemon::species),
            Codec.STRING.listOf().xmap({ it.toSet() }, { it.toList()}).fieldOf("aspects").forGetter(RenderablePokemon::aspects)
        ).apply(it, ::RenderablePokemon)
    }, object : StreamCodec<RegistryFriendlyByteBuf, RenderablePokemon> {
        override fun decode(`object`: RegistryFriendlyByteBuf): RenderablePokemon {
            return RenderablePokemon.loadFromBuffer(`object`)
        }

        override fun encode(`object`: RegistryFriendlyByteBuf, object2: RenderablePokemon) {
            object2.saveToBuffer(`object`)
        }

    })

    @JvmStatic
    fun init() = REGISTER.register()

    private fun <T> register(
        name: String,
        codec: Codec<T>,
        streamCodec: StreamCodec<RegistryFriendlyByteBuf, T>? = null,
    ): RegistrySupplier<DataComponentType<T>> {
        return REGISTER.register(name) {
            DataComponentType.builder<T>().persistent(codec).cacheEncoding()
                .networkSynchronized(streamCodec).build()
        }
    }
}