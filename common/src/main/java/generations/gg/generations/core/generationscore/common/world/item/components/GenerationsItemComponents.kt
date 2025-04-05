package generations.gg.generations.core.generationscore.common.world.item.components

import com.mojang.serialization.Codec
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

    var REGISTER = DataManagerRegistry(GenerationsCore.MOD_ID)

    var DISTANCE = register<Double>(DataKeys.DISTANCE, { 0 }, Codec.DOUBLE, StreamCodec.of(ByteBufCodecs.DOUBLE::encode, ByteBufCodecs.DOUBLE::decode))
    var USES = register<Int>("uses", { 0 }, Codec.INT, StreamCodec.of(ByteBufCodecs.INT::encode, ByteBufCodecs.INT::decode))
    var USED = register<Boolean>("used", { false }, Codec.BOOL, StreamCodec.of(ByteBufCodecs.BOOL::encode, ByteBufCodecs.BOOL::decode))
    var ENCHANTED = register<Boolean>("enchanted", { false }, Codec.BOOL, StreamCodec.of(ByteBufCodecs.BOOL::encode, ByteBufCodecs.BOOL::decode))
    var CARROT_HOLDER = register("carrot_holder", ItemStorageData.DEFAULT, ItemStorageData.CODEC.xmap(CalyrexSteedItem.CarrotHolder))
    var DISC_HOLDER = register(DataKeys.DISC_HOLDER, WalkmonItem.DiscHolder.CODEC)
    val CURRY_DATA = register("curry_data", CurryData.CODEC, CurryData.STREAM_CODEC)

    @JvmStatic
    fun init() = REGISTER.init()

    private fun <T> register(name: String, factory: Supplier<T>, codec: Codec<T>, streamCodec: StreamCodec<RegistryFriendlyByteBuf, T>? = null, itemBased: Boolean = true): DataManager<T> {
        return REGISTER.builder(factory).serialize(codec)
            .also { if (streamCodec != null) it.networkSerializer(streamCodec) }
            .also {
                if(itemBased) it.withDataComponent()
                else it.copyOnDeath()
            }.buildAndRegister(name)
        }
    }
}