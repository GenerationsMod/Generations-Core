package generations.gg.generations.core.generationscore.common.world.recipe

import com.mojang.serialization.MapCodec
import dev.architectury.registry.registries.DeferredRegister
import dev.architectury.registry.registries.RegistrySupplier
import generations.gg.generations.core.generationscore.common.GenerationsCore
import net.minecraft.core.registries.Registries
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.crafting.RecipeSerializer

object GenerationsCoreRecipeSerializers {
    var RECIPE_SERIALIZERS: DeferredRegister<RecipeSerializer<*>> =
        DeferredRegister.create(GenerationsCore.MOD_ID, Registries.RECIPE_SERIALIZER)
    var SHAPED_RKS: RegistrySupplier<RecipeSerializer<ShapedRksRecipe>> = register("shaped_rks", ShapedRksRecipe.CODEC, ShapedRksRecipe.STREAM_CODEC)
    var SHAPELESS_RKS: RegistrySupplier<RecipeSerializer<ShapelessRksRecipe>> = register("shapeless_rks", ShapelessRksRecipe.CODEC, ShapelessRksRecipe.STREAM_CODEC)

    @JvmStatic
    fun init() {
        RECIPE_SERIALIZERS.register()
    }

    fun <T : Recipe<*>> register(
        name: String,
        codec: MapCodec<T>,
        streamCodec: StreamCodec<RegistryFriendlyByteBuf, T>
    ): RegistrySupplier<RecipeSerializer<T>> {
        return RECIPE_SERIALIZERS.register(
            name
        ) {
            object : RecipeSerializer<T> {
                override fun codec(): MapCodec<T> {
                    return codec
                }

                override fun streamCodec(): StreamCodec<RegistryFriendlyByteBuf, T> {
                    return streamCodec
                }
            }
        }
    }
}
