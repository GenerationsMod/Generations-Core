package generations.gg.generations.core.generationscore.common.compat.jei

import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.client.screen.container.RksMachineScreen
import generations.gg.generations.core.generationscore.common.world.container.GenerationsContainers
import generations.gg.generations.core.generationscore.common.world.container.RksMachineContainer
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsUtilityBlocks
import generations.gg.generations.core.generationscore.common.world.recipe.GenerationsCoreRecipeTypes
import generations.gg.generations.core.generationscore.common.world.recipe.RksRecipe
import mezz.jei.api.IModPlugin
import mezz.jei.api.JeiPlugin
import mezz.jei.api.recipe.category.IRecipeCategory
import mezz.jei.api.registration.*
import mezz.jei.api.runtime.IIngredientManager
import mezz.jei.library.plugins.vanilla.crafting.CategoryRecipeValidator
import net.minecraft.client.Minecraft
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.crafting.RecipeHolder

@JeiPlugin
class GenerationsCoreJeiCompat : IModPlugin {
    companion object {
        private val id: ResourceLocation = GenerationsCore.id("rks")
    }
    lateinit var rksCategory: IRecipeCategory<RecipeHolder<RksRecipe>>


    override fun getPluginUid(): ResourceLocation {
        return id
    }

    override fun registerCategories(registration: IRecipeCategoryRegistration) {
        rksCategory = RksRecipeCategory(registration.jeiHelpers.guiHelper)
        registration.addRecipeCategories(rksCategory)
    }

    override fun registerRecipes(registration: IRecipeRegistration) {
        val ingredientManager = registration.ingredientManager

        val craftingRecipes: Pair<List<RecipeHolder<RksRecipe>>, List<RecipeHolder<RksRecipe>>> = ingredientManager.rksRecipes()
        registration.addRecipes(RksRecipeCategory.RKS_MACHINE, craftingRecipes.first)
        registration.addRecipes(RksRecipeCategory.RKS_MACHINE, craftingRecipes.second)
    }

    override fun registerRecipeCatalysts(registration: IRecipeCatalystRegistration) {
        registration.addRecipeCatalyst(GenerationsUtilityBlocks.RKS_MACHINE, RksRecipeCategory.RKS_MACHINE)
    }

    override fun registerGuiHandlers(registration: IGuiHandlerRegistration) {
        registration.addRecipeClickArea(RksMachineScreen::class.java, 90, 35, 22, 15, RksRecipeCategory.RKS_MACHINE)
    }

    override fun registerRecipeTransferHandlers(registration: IRecipeTransferRegistration) {
        registration.addRecipeTransferHandler(
            RksMachineContainer::class.java, GenerationsContainers.RKS_MACHINE, RksRecipeCategory.RKS_MACHINE, 1, 9, 10, 36
        )
    }


    private fun IIngredientManager.rksRecipes(): Pair<List<RecipeHolder<RksRecipe>>, List<RecipeHolder<RksRecipe>>> {
        val validator: CategoryRecipeValidator<RksRecipe> = CategoryRecipeValidator(rksCategory, this, 9)

        val handled: MutableList<RecipeHolder<RksRecipe>> = ArrayList()
        val unhandled: MutableList<RecipeHolder<RksRecipe>> = ArrayList()

        val recipeManager = Minecraft.getInstance().level?.recipeManager ?: return Pair(emptyList(), emptyList())

        recipeManager.getAllRecipesFor(GenerationsCoreRecipeTypes.RKS).forEach { recipe ->
                if (validator.isRecipeHandled(recipe)) {
                    handled.add(recipe)
                } else {
                    unhandled.add(recipe)
                }
        }

        return handled.toList() to unhandled.toList()
    }
}
