package generations.gg.generations.core.generationscore.common.world.recipe

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import dev.architectury.registry.registries.Registrar
import dev.architectury.registry.registries.RegistrarManager
import dev.architectury.registry.registries.RegistrySupplier
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.util.Codecs.codec
import generations.gg.generations.core.generationscore.common.util.StreamCodecs.asRegistryFriendly
import generations.gg.generations.core.generationscore.common.util.StreamCodecs.streamCodec
import generations.gg.generations.core.generationscore.common.world.recipe.RksResultType.Companion.RKS_RESULT
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.resources.ResourceLocation
import java.util.function.BiConsumer
import java.util.function.Function

data class RksResultType<T : RksResult<T>>(val codec: MapCodec<T>, val streamCodec: StreamCodec<RegistryFriendlyByteBuf, T>,
) {
    companion object {
        val RKS_RESULT: Registrar<RksResultType<*>> = RegistrarManager.get(GenerationsCore.MOD_ID).builder<RksResultType<*>?>(GenerationsCore.id("rks_result")).syncToClients().build()
        val CODEC = RKS_RESULT.codec().dispatch(RksResult<*>::type, RksResultType<*>::codec)
        val STREAM_CODEC = RKS_RESULT.streamCodec().dispatch(RksResult<*>::type, RksResultType<*>::streamCodec)

        val POKEMON = register<PokemonResult>("pokemon", PokemonResult.CODEC, PokemonResult.STREAM_CODEC)
        val ITEM: RegistrySupplier<RksResultType<ItemResult>> = register<ItemResult>("item", ItemResult.CODEC, ItemResult.STREAM_CODEC)

        fun <T : RksResult<T>> register(
            name: String,
            codec: MapCodec<T>,
            streamCodec: StreamCodec<RegistryFriendlyByteBuf, T>
        ): RegistrySupplier<RksResultType<T>> {
            return RKS_RESULT.register(GenerationsCore.id(name)) { RksResultType<T>(codec, streamCodec) }
        }

        @JvmStatic
        fun init() {}
    }
}
