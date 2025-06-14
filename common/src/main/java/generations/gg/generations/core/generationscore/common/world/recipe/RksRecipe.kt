package generations.gg.generations.core.generationscore.common.world.recipe

import generations.gg.generations.core.generationscore.common.config.SpeciesKey
import generations.gg.generations.core.generationscore.common.recipe.RksInput
import generations.gg.generations.core.generationscore.common.util.getPokemon
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.level.block.entities.RksMachineBlockEntity
import net.minecraft.core.HolderLookup
import net.minecraft.core.NonNullList
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.*
import java.util.*
import kotlin.jvm.optionals.getOrNull

abstract class RksRecipe(
    @JvmField val group: String,
    val result: RksResult<*>,
    val consumesTimeCapsules: Boolean,
    val key: Optional<SpeciesKey>,
    val experience: Float,
    val processingTime: Int,
    val showNotification: Boolean
) : Recipe<RksInput> {

    abstract override fun getSerializer(): RecipeSerializer<*>

    override fun getGroup(): String {
        return this.group
    }

    override fun getResultItem(registries: HolderLookup.Provider): ItemStack {
        return result.stack
    }

    override fun getIngredients(): NonNullList<Ingredient> {
        return NonNullList.create()
    }

    abstract fun getRksIngredients(): NonNullList<GenerationsIngredient>

    override fun showNotification(): Boolean {
        return this.showNotification
    }

    fun getRemainingItems(container: RksMachineBlockEntity): NonNullList<ItemStack> {
        val nonNullList = NonNullList.withSize(9, ItemStack.EMPTY)
        for (i in nonNullList.indices) {
            val itemStack = container.inventory[i].copy()
            val item = itemStack.item
            if (itemStack.`is`(GenerationsItems.TIME_CAPSULE)) {
                container.pokemon = Optional.ofNullable(itemStack.getPokemon())
                if (!consumesTimeCapsules) nonNullList[i] = itemStack.copy()
            } else if (item.hasCraftingRemainingItem()) nonNullList[i] =
                ItemStack(item.craftingRemainingItem)
        }
        return nonNullList
    }

    override fun assemble(input: RksInput, registries: HolderLookup.Provider): ItemStack {
        return getResultItem(registries).copy()
    }

    fun experience(): Float {
        return experience
    }

    val isPokemonResult: Boolean
        get() = result.isPokemon

    fun process(player: Player, rksMachineBlockEntity: RksMachineBlockEntity, stack: ItemStack) {
        result.process(player, rksMachineBlockEntity, stack)
    }

    override fun getType(): RecipeType<*> {
        return GenerationsCoreRecipeTypes.RKS
    }
}

