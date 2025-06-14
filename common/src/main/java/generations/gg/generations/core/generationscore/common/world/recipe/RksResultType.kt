package generations.gg.generations.core.generationscore.common.world.recipe

import com.mojang.serialization.MapCodec
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec

data class RksResultType<T : RksResult<T>>(val codec: MapCodec<T>, val streamCodec: StreamCodec<RegistryFriendlyByteBuf, T>)
