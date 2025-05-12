package generations.gg.generations.core.generationscore.common.recipe

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.world.recipe.GenerationsIngredient
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec

@JvmRecord
data class GenerationsIngredientType<T : GenerationsIngredient>(
    val codec: MapCodec<T>,
    val streamCodec: StreamCodec<RegistryFriendlyByteBuf, T>
)
