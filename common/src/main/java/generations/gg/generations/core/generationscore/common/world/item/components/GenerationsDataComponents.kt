package generations.gg.generations.core.generationscore.common.world.item.components

import com.cobblemon.mod.common.pokemon.Pokemon
import com.mojang.serialization.Codec
import generations.gg.generations.core.generationscore.common.util.DataKeys
import generations.gg.generations.core.generationscore.common.util.PlatformRegistry
import generations.gg.generations.core.generationscore.common.world.item.WalkmonItem
import generations.gg.generations.core.generationscore.common.world.item.curry.CurryData
import generations.gg.generations.core.generationscore.common.world.item.legends.RubyRodItem
import net.minecraft.core.Holder
import net.minecraft.core.Registry
import net.minecraft.core.component.DataComponentType
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.resources.ResourceKey

object GenerationsDataComponents: PlatformRegistry<DataComponentType<*>>() {
    override val registry: Registry<DataComponentType<*>> = BuiltInRegistries.DATA_COMPONENT_TYPE
    override val resourceKey: ResourceKey<Registry<DataComponentType<*>>> = Registries.DATA_COMPONENT_TYPE

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

    val CURRY_DATA = register("curry_data", CurryData.CODEC, CurryData.STREAM_CODEC)
    val TM_DETAILS = register("tm_details", TmDetails.CODEC, TmDetails.STREAM_CODEC)
    val EMBEDDED_POKEMON = register("embedded_pokemon", Pokemon.CODEC, Pokemon.S2C_CODEC)

    val MAIL_DATA = register("mail_data", MailContent.CODEC, MailContent.STREAM_CODEC)
    val WALKMON_DATA = register("walkmon_data", WalkmonItem.WalkmonData.CODEC, WalkmonItem.WalkmonData.STREAM_CODEC)
    val FISHED_SHARDS = register("fished_shards", RubyRodItem.FishedShards.CODEC)

    private fun <T> register(
        name: String,
        codec: Codec<T>,
        streamCodec: StreamCodec<RegistryFriendlyByteBuf, T>? = null,
    ): Holder<DataComponentType<*>> = create(name, { DataComponentType.builder<T>().persistent(codec).cacheEncoding().networkSynchronized(streamCodec).build() })
}

