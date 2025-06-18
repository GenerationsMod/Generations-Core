package generations.gg.generations.core.generationscore.common.world.recipe

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.util.PlatformRegistry
import net.minecraft.core.Holder
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec

object GenerationsRksTypes: PlatformRegistry<RksResultType<*>>(GenerationsCore.RKS_RESULT_TYPE.key, GenerationsCore.RKS_RESULT_TYPE.registry) {

    val POKEMON = register("pokemon", PokemonResult.CODEC, PokemonResult.STREAM_CODEC)
    val ITEM = register("item", ItemResult.CODEC, ItemResult.STREAM_CODEC)

    private fun <T: RksResult<T>> register(name: String, codec: MapCodec<T>, streamCodec: StreamCodec<RegistryFriendlyByteBuf, T>): Holder<RksResultType<*>> = create(name, { RksResultType(codec, streamCodec) })
}