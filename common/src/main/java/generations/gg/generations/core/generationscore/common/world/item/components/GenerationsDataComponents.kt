package generations.gg.generations.core.generationscore.common.world.item.components

import com.cobblemon.mod.common.pokemon.Pokemon
import com.mojang.serialization.Codec
import earth.terrarium.common_storage_lib.item.util.ItemStorageData
import generations.gg.generations.core.generationscore.common.GenerationsStorage.registry
import generations.gg.generations.core.generationscore.common.util.DataKeys
import generations.gg.generations.core.generationscore.common.util.PlatformRegistry
import generations.gg.generations.core.generationscore.common.util.StreamCodecs.asRegistryFriendly
import generations.gg.generations.core.generationscore.common.world.item.curry.CurryData
import generations.gg.generations.core.generationscore.common.world.item.legends.RubyRodItem
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.InventorySize
import net.minecraft.core.Holder
import net.minecraft.core.component.DataComponentType
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.JukeboxPlayable
import net.minecraft.world.item.JukeboxSong

object GenerationsDataComponents: PlatformRegistry<DataComponentType<*>>(Registries.DATA_COMPONENT_TYPE, BuiltInRegistries.DATA_COMPONENT_TYPE) {
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

    //Walkmon Data
    val CURRENT_SLOT = register("current_slot", Codec.INT, ByteBufCodecs.VAR_INT.asRegistryFriendly())
    val ROWS = register("rows", Codec.INT, ByteBufCodecs.VAR_INT.asRegistryFriendly())
    val TIME_UNTIL_NEXT_SONG = register("time_until_next_song", Codec.INT, ByteBufCodecs.VAR_INT.asRegistryFriendly())
    val PLAYING = register("playing", Codec.BOOL, ByteBufCodecs.BOOL.asRegistryFriendly())
    val CURRENT_SONG = register("current_song", JukeboxSong.DIRECT_CODEC, JukeboxSong.DIRECT_STREAM_CODEC)

//    val WALKMON_DATA = register("walkmon_data", WalkmonData.CODEC, WalkmonData.STREAM_CODEC)
//    val DISCS = register("discs", DiscContainer.CODEC, DiscContainer.STREAM_CODEC)
    val CALYREX_CARROTS = register("calyrex_carrots", CarrotHolder.CODEC, CarrotHolder.STREAM_CODEC)
    val IMBUED = register("imbued", ItemStack.OPTIONAL_CODEC, ItemStack.OPTIONAL_STREAM_CODEC)
    val REGI_ORBS = registry.builder(ItemStorageData.DEFAULT).serialize(ItemStorageData.CODEC).networkSerializer(
        ItemStorageData.NETWORK_CODEC).buildAndRegister("regi_orbs")


    val FISHED_SHARDS = register("fished_shards", RubyRodItem.FishedShards.CODEC)
    val INVENTORY_SIZE = register("inventory_size", InventorySize.CODEC, InventorySize.STREAM_CODEC)

    private fun <T> register(
        name: String,
        codec: Codec<T>,
        streamCodec: StreamCodec<RegistryFriendlyByteBuf, T>? = null,
    ): Holder<DataComponentType<T>> = create(name, { DataComponentType.builder<T>().persistent(codec).cacheEncoding().networkSynchronized(streamCodec).build() })
}

