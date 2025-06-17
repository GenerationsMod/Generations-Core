package generations.gg.generations.core.generationscore.common.compat.jei

import com.mojang.blaze3d.platform.InputConstants
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsUtilityBlocks
import generations.gg.generations.core.generationscore.common.world.recipe.GenerationsCoreRecipeTypes
import generations.gg.generations.core.generationscore.common.world.recipe.RksRecipe
import generations.gg.generations.core.generationscore.common.world.recipe.ShapedRksRecipe
import generations.gg.generations.core.generationscore.common.world.recipe.ShapelessRksRecipe
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder
import mezz.jei.api.gui.builder.ITooltipBuilder
import mezz.jei.api.gui.ingredient.ICraftingGridHelper
import mezz.jei.api.gui.ingredient.IRecipeSlotDrawable
import mezz.jei.api.gui.ingredient.IRecipeSlotsView
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder
import mezz.jei.api.helpers.IGuiHelper
import mezz.jei.api.recipe.IFocusGroup
import mezz.jei.api.recipe.RecipeType
import mezz.jei.api.recipe.category.AbstractRecipeCategory
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.core.Holder
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.crafting.RecipeHolder
import net.minecraft.world.level.block.Block
import java.util.function.Supplier

class RksRecipeCategory(private val helper: IGuiHelper) :
    AbstractRecipeCategory<RecipeHolder<RksRecipe>>(
        RKS_MACHINE,
        Component.translatable("gui.recipe_viewer.category.rks_machine"),
        helper.createDrawableItemLike(GenerationsUtilityBlocks.RKS_MACHINE.value()),
        width,
        height
    ), IExtendableRksRecipeCategory {
    private val craftingGridHelper: ICraftingGridHelper = helper.createCraftingGridHelper()
    private val extendableHelper = RksExtensionHelper()

    init {
        extendableHelper.addRecipeExtension(ShapedRksRecipe::class.java, RksRecipeExtension())
        extendableHelper.addRecipeExtension(ShapelessRksRecipe::class.java, RksRecipeExtension())
    }

    override fun setRecipe(builder: IRecipeLayoutBuilder, holder: RecipeHolder<RksRecipe>, group: IFocusGroup) {
        val recipeExtension = extendableHelper.getRecipeExtension(holder)
        recipeExtension.setRecipe(holder, builder, craftingGridHelper, group)
    }

    override fun onDisplayedIngredientsUpdate(
        holder: RecipeHolder<RksRecipe>,
        slots: List<IRecipeSlotDrawable>,
        group: IFocusGroup
    ) {
        val recipeExtension = extendableHelper.getRecipeExtension(holder)
        recipeExtension.onDisplayedIngredientsUpdate(holder, slots, group)
    }

    override fun createRecipeExtras(
        builder: IRecipeExtrasBuilder,
        holder: RecipeHolder<RksRecipe>,
        group: IFocusGroup
    ) {
        val recipeExtension = extendableHelper.getRecipeExtension(holder)
        recipeExtension.createRecipeExtras(holder, builder, craftingGridHelper, group)
    }


    override fun draw(
        holder: RecipeHolder<RksRecipe>,
        view: IRecipeSlotsView,
        graphics: GuiGraphics,
        mouseX: Double,
        mouseY: Double
    ) {
        val recipeExtension = extendableHelper.getRecipeExtension(holder)
        val recipeWidth = this.width
        val recipeHeight = this.height
        recipeExtension.drawInfo(recipeWidth, recipeHeight, graphics, mouseX, mouseY)

        val recipeArrow = helper.recipeArrow
        recipeArrow.draw(graphics, 61, (Companion.height - recipeArrow.height) / 2)
    }

    override fun getTooltip(
        tooltip: ITooltipBuilder,
        holder: RecipeHolder<RksRecipe>,
        view: IRecipeSlotsView,
        mouseX: Double,
        mouseY: Double
    ) {
        val recipeExtension = extendableHelper.getRecipeExtension(holder)
        recipeExtension.getTooltip(tooltip, holder, mouseX, mouseY)
    }

    override fun getTooltipStrings(
        holder: RecipeHolder<RksRecipe>,
        view: IRecipeSlotsView,
        mouseX: Double,
        mouseY: Double
    ): List<Component> {
        val recipeExtension = extendableHelper.getRecipeExtension(holder)

        return recipeExtension.getTooltipStrings(holder, mouseX, mouseY)
    }

    override fun handleInput(
        holder: RecipeHolder<RksRecipe>,
        mouseX: Double,
        mouseY: Double,
        input: InputConstants.Key
    ): Boolean {
        val recipeExtension = extendableHelper.getRecipeExtension(holder)
        return recipeExtension.handleInput(mouseX, mouseY, input)
    }

    override fun isHandled(holder: RecipeHolder<RksRecipe>): Boolean {
        return extendableHelper.getOptionalRecipeExtension(holder)
            .isPresent
    }

    override fun <R : RksRecipe?> addExtension(clazz: Class<out R>, extension: IRksCategoryExtension<R>) {
        extendableHelper.addRecipeExtension(clazz, extension)
    }

    override fun getRegistryName(holder: RecipeHolder<RksRecipe>): ResourceLocation? {
        return extendableHelper.getOptionalRecipeExtension(holder)
            .flatMap { extension: IRksCategoryExtension<RksRecipe> ->
                extension.getRegistryName(
                    holder
                )
            }
            .orElseGet { holder.id() }
    }

    companion object {
        const val width: Int = 116
        const val height: Int = 54

        val RKS_MACHINE: RecipeType<RecipeHolder<RksRecipe>> = RecipeType.createFromVanilla(GenerationsCoreRecipeTypes.RKS.asValue())
    }
}

fun <T: Recipe<*>> Holder<net.minecraft.world.item.crafting.RecipeType<*>>.asValue(): net.minecraft.world.item.crafting.RecipeType<T> = this.value() as net.minecraft.world.item.crafting.RecipeType<T>
