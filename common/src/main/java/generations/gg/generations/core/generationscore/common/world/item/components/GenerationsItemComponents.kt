package generations.gg.generations.core.generationscore.common.world.item.components

import com.mojang.serialization.Codec
import dev.architectury.registry.registries.DeferredRegister
import dev.architectury.registry.registries.RegistrySupplier
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.util.DataKeys
import generations.gg.generations.core.generationscore.common.world.item.CalyrexSteedItem
import generations.gg.generations.core.generationscore.common.world.item.WalkmonItem
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

    var DISTANCE = register<Double>(DataKeys.DISTANCE, Codec.DOUBLE, StreamCodec.of(ByteBufCodecs.DOUBLE::encode, ByteBufCodecs.DOUBLE::decode))
    var USES = register<Int>("uses", Codec.INT, StreamCodec.of(ByteBufCodecs.INT::encode, ByteBufCodecs.INT::decode))
    var USED = register<Boolean>("used", Codec.BOOL, StreamCodec.of(ByteBufCodecs.BOOL::encode, ByteBufCodecs.BOOL::decode))
    var ENCHANTED = register<Boolean>("enchanted", Codec.BOOL, StreamCodec.of(ByteBufCodecs.BOOL::encode, ByteBufCodecs.BOOL::decode))
    var CARROT_HOLDER = register("inventory", CalyrexSteedItem.CarrotHolder.CODEC)
    var DISC_HOLDER = register(DataKeys.DISC_HOLDER, WalkmonItem.DiscHolder.CODEC)

    @JvmStatic
    fun init() = REGISTER.register()

    private fun <T> register(name: String, codec: Codec<T>, streamCodec: StreamCodec<RegistryFriendlyByteBuf, T>? = null, cache: Boolean = true): RegistrySupplier<DataComponentType<T>> {
        return REGISTER.register(name) {
            DataComponentType.builder<T>()
                .persistent(codec)
                .also { if (streamCodec != null) it.networkSynchronized(streamCodec) }
                .also { if (cache) it.cacheEncoding() }
                .build()
        }
    }
}