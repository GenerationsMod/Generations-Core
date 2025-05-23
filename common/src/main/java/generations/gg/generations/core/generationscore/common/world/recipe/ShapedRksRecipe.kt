package generations.gg.generations.core.generationscore.common.world.recipe

import com.cobblemon.mod.common.util.codec.kotlinOptionalFieldOf
import com.cobblemon.mod.common.util.codec.optionalFieldOfWithDefault
import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import generations.gg.generations.core.generationscore.common.config.SpeciesKey
import generations.gg.generations.core.generationscore.common.recipe.RksInput
import net.minecraft.core.NonNullList
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.world.item.crafting.CraftingInput
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraft.world.level.Level
import java.util.Optional

class ShapedRksRecipe(
    group: String,
    result: RksResult<*>,
    consumesTimeCapsules: Boolean,
    key: Optional<SpeciesKey>,
    experience: Float,
    processingTime: Int,
    showNotification: Boolean = false,
    val pattern: ShapedRecipePattern,
) : RksRecipe(group, result, consumesTimeCapsules, key, experience, processingTime, showNotification) {

    override fun getSerializer(): RecipeSerializer<*> = GenerationsCoreRecipeSerializers.SHAPED_RKS.get()

    override fun getRksIngredients(): NonNullList<GenerationsIngredient> = pattern.ingredients();

    override fun canCraftInDimensions(width: Int, height: Int): Boolean = width >= pattern.width && height >= pattern.height

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    override fun matches(input: RksInput, level: Level): Boolean = pattern.matches(input)

    override fun isIncomplete(): Boolean {
        val nonNullList = pattern.ingredients()
        return nonNullList.isEmpty() || nonNullList.filter { !it.isEmpty }.any(GenerationsIngredient::isEmpty)
    }

    companion object {
        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, ShapedRksRecipe> = StreamCodec.of(ShapedRksRecipe::toNetwork, ShapedRksRecipe::fromNetwork)

        private fun toNetwork(buffer: RegistryFriendlyByteBuf, value: ShapedRksRecipe) {
            ByteBufCodecs.STRING_UTF8.encode(buffer, value.group)
            RksResultType.STREAM_CODEC.encode(buffer, value.result)
            ByteBufCodecs.BOOL.encode(buffer, value.consumesTimeCapsules)
            SpeciesKey.OPTIONAL_STREAM_CODEC.encode(buffer, value.key)
            ByteBufCodecs.FLOAT.encode(buffer, value.experience)
            ByteBufCodecs.INT.encode(buffer, value.processingTime)
            ByteBufCodecs.BOOL.encode(buffer, value.showNotification)
            ShapedRecipePattern.STREAM_CODEC.encode(buffer, value.pattern)
        }

        private fun fromNetwork(buffer: RegistryFriendlyByteBuf): ShapedRksRecipe {
            val group = ByteBufCodecs.STRING_UTF8.decode(buffer)
            val result = RksResultType.STREAM_CODEC.decode(buffer)
            val consumesTimeCapsules = ByteBufCodecs.BOOL.decode(buffer)
            val key = SpeciesKey.OPTIONAL_STREAM_CODEC.decode(buffer)
            val experience = ByteBufCodecs.FLOAT.decode(buffer)
            val processingTime = ByteBufCodecs.INT.decode(buffer)
            val showNotification = ByteBufCodecs.BOOL.decode(buffer)
            val pattern = ShapedRecipePattern.STREAM_CODEC.decode(buffer)

            return ShapedRksRecipe(group, result, consumesTimeCapsules, key, experience, processingTime, showNotification, pattern)
        }

        val CODEC: MapCodec<ShapedRksRecipe> = RecordCodecBuilder.mapCodec { it.group(
            Codec.STRING.fieldOf("group").forGetter { it.group },
            RksResultType.CODEC.fieldOf("result").forGetter { it.result },
            Codec.BOOL.fieldOf("consumesTimeCapsules").forGetter { it.consumesTimeCapsules },
            SpeciesKey.CODEC.optionalFieldOf("key").forGetter { it.key },
            Codec.FLOAT.fieldOf("experience").forGetter { it.experience },
            Codec.INT.fieldOf("processingTime").forGetter { it.processingTime },
            Codec.BOOL.fieldOf("showNotification").forGetter { it.showNotification },
            ShapedRecipePattern.MAP_CODEC.fieldOf("pattern").forGetter { it.pattern }
        ).apply(it, ::ShapedRksRecipe) }
    }
}
