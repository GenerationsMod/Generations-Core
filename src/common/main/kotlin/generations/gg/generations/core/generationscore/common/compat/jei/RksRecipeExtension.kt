//package generations.gg.generations.core.generationscore.common.compat.jei
//
//import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
//import generations.gg.generations.core.generationscore.common.world.recipe.RksRecipe
//import generations.gg.generations.core.generationscore.common.world.recipe.ShapedRksRecipe
//import mezz.jei.api.gui.builder.IRecipeLayoutBuilder
//import mezz.jei.api.gui.ingredient.ICraftingGridHelper
//import mezz.jei.api.recipe.IFocusGroup
//import mezz.jei.library.util.RecipeUtil
//import net.minecraft.resources.ResourceLocation
//import net.minecraft.world.item.crafting.RecipeHolder
//import java.util.*
//import java.util.List
//
//class RksRecipeExtension<T : RksRecipe> : IRksCategoryExtension<T> {
//    override fun setRecipe(
//        recipeHolder: RecipeHolder<T>,
//        builder: IRecipeLayoutBuilder,
//        craftingGridHelper: ICraftingGridHelper,
//        focuses: IFocusGroup
//    ) {
//        val recipe: RksRecipe = recipeHolder.value()
//        val resultItem = RecipeUtil.getResultItem(recipe)
//
//        val width = getWidth(recipeHolder)
//        val height = getHeight(recipeHolder)
//        craftingGridHelper.createAndSetOutputs(builder, List.of(resultItem))
////        craftingGridHelper.createAndSetIngredients(builder, recipe.ingredients, width, height)
//    }
//
//    override fun getWidth(recipeHolder: RecipeHolder<T>): Int {
//        return recipeHolder.value().instanceOrNull<ShapedRksRecipe>()?.pattern?.width ?: 0
//    }
//
//    override fun getHeight(recipeHolder: RecipeHolder<T>): Int {
//       return recipeHolder.value().instanceOrNull<ShapedRksRecipe>()?.pattern?.height ?: 0
//    }
//
//    override fun isHandled(recipe: RecipeHolder<T>): Boolean {
//        return !recipe.value().isSpecial
//    }
//}
