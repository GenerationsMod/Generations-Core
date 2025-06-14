package generations.gg.generations.core.generationscore.common.world.recipe

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.generationsResource
import generations.gg.generations.core.generationscore.common.util.PlatformRegistry
import net.minecraft.core.Registry
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.resources.ResourceKey

object GenerationsRksTypes: PlatformRegistry<RksResultType<*>>() {
    override val registry: Registry<RksResultType<*>>
        get() = GenerationsCore.RKS_RESULT_TYPE.registry
    override val resourceKey: ResourceKey<Registry<RksResultType<*>>>
        get() = GenerationsCore.RKS_RESULT_TYPE.key

    val POKEMON = register("pokemon", PokemonResult.CODEC, PokemonResult.STREAM_CODEC)
    val ITEM = register("item", ItemResult.CODEC, ItemResult.STREAM_CODEC)

    private fun <T: RksResult<T>> register(name: String, codec: MapCodec<T>, streamCodec: StreamCodec<RegistryFriendlyByteBuf, T>): RksResultType<T> = create(name.generationsResource(), RksResultType(codec, streamCodec))
}