package generations.gg.generations.core.generationscore.common.world.recipe

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.config.SpeciesKey
import generations.gg.generations.core.generationscore.common.recipe.GenerationsIngredidents
import generations.gg.generations.core.generationscore.common.recipe.RksInput
import net.minecraft.core.NonNullList
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraft.world.level.Level
import java.util.*

class ShapelessRksRecipe(
    group: String,
    result: RksResult<*>,
    consumesTimeCapsules: Boolean,
    key: Optional<SpeciesKey>,
    experience: Float,
    processingTime: Int,
    showNotification: Boolean = false,
    val recipeItems: NonNullList<GenerationsIngredient>
) : RksRecipe(group, result, consumesTimeCapsules, key, experience, processingTime, showNotification) {

    override fun matches(input: RksInput, level: Level): Boolean {
        if(input.ingredientCount() != this.recipeItems.size) return false;
        else return if(input.size() == 1 && this.recipeItems.size == 1) this.recipeItems.first().matches(input.getItem(0)) else input.stackedContents().tryCraft(this.getRksIngredients());
    }

    override fun getRksIngredients(): NonNullList<GenerationsIngredient> = recipeItems

    override fun canCraftInDimensions(width: Int, height: Int): Boolean {
        return width * height >= this.recipeItems.size
    }

    override fun getSerializer(): RecipeSerializer<*> {
        return GenerationsCoreRecipeSerializers.SHAPELESS_RKS.value()
    }

    companion object {
        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, ShapelessRksRecipe> = StreamCodec.of(ShapelessRksRecipe::toNetwork, ShapelessRksRecipe::fromNetwork)

        private fun toNetwork(buffer: RegistryFriendlyByteBuf, value: ShapelessRksRecipe) {
            ByteBufCodecs.STRING_UTF8.encode(buffer, value.group)
            GenerationsCore.RKS_RESULT_TYPE.streamCodec.encode(buffer, value.result)
            ByteBufCodecs.BOOL.encode(buffer, value.consumesTimeCapsules)
            SpeciesKey.OPTIONAL_STREAM_CODEC.encode(buffer, value.key)
            ByteBufCodecs.FLOAT.encode(buffer, value.experience)
            ByteBufCodecs.INT.encode(buffer, value.processingTime)
            ByteBufCodecs.BOOL.encode(buffer, value.showNotification)
            buffer.writeVarInt(value.recipeItems.size)

                value.recipeItems.forEach({ ingredient ->
                GenerationsIngredidents.STREAM_CODEC.encode(
                    buffer,
                    ingredient
                )
            })        }

        private fun fromNetwork(buffer: RegistryFriendlyByteBuf): ShapelessRksRecipe {
            val group = ByteBufCodecs.STRING_UTF8.decode(buffer)
            val result = GenerationsCore.RKS_RESULT_TYPE.streamCodec.decode(buffer)
            val consumesTimeCapsules = ByteBufCodecs.BOOL.decode(buffer)
            val key = SpeciesKey.OPTIONAL_STREAM_CODEC.decode(buffer)
            val experience = ByteBufCodecs.FLOAT.decode(buffer)
            val processingTime = ByteBufCodecs.INT.decode(buffer)
            val showNotification = ByteBufCodecs.BOOL.decode(buffer)
            val items = NonNullList.withSize<GenerationsIngredient>(buffer.readVarInt(), EmptyIngredient)
            items.replaceAll { ingredient: GenerationsIngredient? ->
                GenerationsIngredidents.STREAM_CODEC.decode(
                    buffer
                )
            }

            return ShapelessRksRecipe(group, result, consumesTimeCapsules, key, experience, processingTime, showNotification, items)
        }

        val CODEC: MapCodec<ShapelessRksRecipe> = RecordCodecBuilder.mapCodec { it.group(
            Codec.STRING.fieldOf("group").forGetter { it.group },
            GenerationsCore.RKS_RESULT_TYPE.codec.fieldOf("result").forGetter { it.result },
            Codec.BOOL.fieldOf("consumesTimeCapsules").forGetter { it.consumesTimeCapsules },
            SpeciesKey.CODEC.optionalFieldOf("key").forGetter { it.key },
            Codec.FLOAT.fieldOf("experience").forGetter { it.experience },
            Codec.INT.fieldOf("processingTime").forGetter { it.processingTime },
            Codec.BOOL.fieldOf("showNotification").forGetter { it.showNotification },
            GenerationsIngredidents.CODEC.listOf().xmap({ list -> NonNullList.createWithCapacity<GenerationsIngredient>(list.size).also({ it.addAll(list)})}, { it }).fieldOf("pattern").forGetter { it.recipeItems }
        ).apply(it, ::ShapelessRksRecipe) }
    }
}
