package generations.gg.generations.core.generationscore.forge.datagen.generators.recipe

import com.cobblemon.mod.common.util.asResource
import dev.architectury.registry.registries.RegistrySupplier
import net.minecraft.core.Holder
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.recipes.RecipeBuilder.getDefaultRecipeId
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.RecipeProvider
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.data.recipes.ShapelessRecipeBuilder
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.level.ItemLike
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer
import java.util.function.Function
import java.util.stream.Stream

class GenerationsRecipeProvider @SafeVarargs constructor(
    output: PackOutput, registries: CompletableFuture<HolderLookup.Provider>,
    vararg providers: (PackOutput, CompletableFuture<HolderLookup.Provider>) -> Proxied
) :
    RecipeProvider(output, registries) {
    var providers: List<Proxied> = providers.map { it.invoke(output, registries) }.toList()

    override fun buildRecipes(consumer: RecipeOutput) {
        providers.forEach(Consumer { provider: Proxied -> provider.buildRecipes(consumer) })
    }

    abstract class Proxied(arg: PackOutput, registries: CompletableFuture<HolderLookup.Provider>) : RecipeProvider(arg, registries) {
        public abstract override fun buildRecipes(recipeOutput: RecipeOutput)

        protected fun RecipeOutput.shaped(category: RecipeCategory = RecipeCategory.MISC, output: Holder<out ItemLike>, id: ResourceLocation = getDefaultRecipeId(output.value().asItem()), count: Int = 1, block: ShapedRecipeBuilder.() -> Unit) = this.shaped(category, output.value().asItem(), id, count, block)
        protected fun RecipeOutput.shaped(category: RecipeCategory = RecipeCategory.MISC, output: ItemLike, id: ResourceLocation = getDefaultRecipeId(output), count: Int = 1, block: ShapedRecipeBuilder.() -> Unit) {
            val builder = ShapedRecipeBuilder.shaped(category, output, count)
            block.invoke(builder)
            builder.save(this, id)
        }

        protected fun RecipeOutput.shapeless(category: RecipeCategory = RecipeCategory.MISC, output: ItemLike, id: String, count: Int = 1, block: ShapelessRecipeBuilder.() -> Unit) = this.shapeless(category, output.asItem(), id.asResource(), count, block)
        protected fun RecipeOutput.shapeless(category: RecipeCategory = RecipeCategory.MISC, output: Holder<out ItemLike>, id: String, count: Int = 1, block: ShapelessRecipeBuilder.() -> Unit) = this.shapeless(category, output, id.asResource(), count, block)
        protected fun RecipeOutput.shapeless(category: RecipeCategory = RecipeCategory.MISC, output: Holder<out ItemLike>, id: ResourceLocation = getDefaultRecipeId(output.value().asItem()), count: Int = 1, block: ShapelessRecipeBuilder.() -> Unit) = this.shapeless(category, output.value().asItem(), id, count, block)
        protected fun RecipeOutput.shapeless(category: RecipeCategory = RecipeCategory.MISC, output: ItemLike, id: ResourceLocation = getDefaultRecipeId(output), count: Int = 1, block: ShapelessRecipeBuilder.() -> Unit) {
            val builder = ShapelessRecipeBuilder.shapeless(category, output, count)
            block.invoke(builder)
            builder.save(this, id)
        }

        fun ShapedRecipeBuilder.unlockedByHolder(item: Holder<out ItemLike>) = this.unlockedByItem(item.value())
        fun ShapedRecipeBuilder.unlockedByItem(item: ItemLike) = this.unlockedBy(getHasName(item), has(item))
        fun ShapedRecipeBuilder.define(symbo: Char, holder: Holder<out ItemLike>) = this.define(symbo, holder.value())

        fun ShapelessRecipeBuilder.unlockedByHolder(item: Holder<out ItemLike>) = this.unlockedByItem(item.value())
        fun ShapelessRecipeBuilder.unlockedByItem(item: ItemLike) = this.unlockedBy(getHasName(item), has(item))
        fun ShapelessRecipeBuilder.requires(holder: Holder<out ItemLike>, count: Int = 1) = this.requires(holder.value(), count)

    }
}
