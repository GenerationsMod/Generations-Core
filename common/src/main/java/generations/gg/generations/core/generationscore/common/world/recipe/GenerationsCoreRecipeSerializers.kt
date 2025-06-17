package generations.gg.generations.core.generationscore.common.world.recipe

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.util.PlatformRegistry
import net.minecraft.core.Holder
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.crafting.RecipeSerializer

object GenerationsCoreRecipeSerializers: PlatformRegistry<RecipeSerializer<*>>() {
    override val registry: Registry<RecipeSerializer<*>> = BuiltInRegistries.RECIPE_SERIALIZER
    override val resourceKey: ResourceKey<Registry<RecipeSerializer<*>>> = Registries.RECIPE_SERIALIZER

    var SHAPED_RKS = register("shaped_rks", ShapedRksRecipe.CODEC, ShapedRksRecipe.STREAM_CODEC)
    var SHAPELESS_RKS = register("shapeless_rks", ShapelessRksRecipe.CODEC, ShapelessRksRecipe.STREAM_CODEC)

    fun <T : Recipe<*>> register(
        name: String,
        codec: MapCodec<T>,
        streamCodec: StreamCodec<RegistryFriendlyByteBuf, T>
    ): Holder<RecipeSerializer<*>> = create(name, {
        object : RecipeSerializer<T> {
            override fun codec(): MapCodec<T> {
                return codec
            }

            override fun streamCodec(): StreamCodec<RegistryFriendlyByteBuf, T> {
                return streamCodec
            }
        }
    })
}
