package generations.gg.generations.core.generationscore.common.recipe

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.world.recipe.GenerationsIngredient
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.resources.ResourceLocation

@JvmRecord
data class GenerationsIngredientType<T : GenerationsIngredient>(
    val id: ResourceLocation,
    val codec: MapCodec<T>,
    val streamCodec: StreamCodec<RegistryFriendlyByteBuf, T>
)
