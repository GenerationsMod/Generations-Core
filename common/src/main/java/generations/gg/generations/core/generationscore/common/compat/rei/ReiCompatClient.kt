package generations.gg.generations.core.generationscore.common.compat.rei

import generations.gg.generations.core.generationscore.common.client.screen.container.RksMachineScreen
import generations.gg.generations.core.generationscore.common.world.container.RksMachineContainer
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsUtilityBlocks
import generations.gg.generations.core.generationscore.common.world.recipe.GenerationsCoreRecipeTypes
import generations.gg.generations.core.generationscore.common.world.recipe.RksRecipe
import me.shedaniel.math.Rectangle
import me.shedaniel.rei.api.client.plugins.REIClientPlugin
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry
import me.shedaniel.rei.api.client.registry.transfer.TransferHandlerRegistry
import me.shedaniel.rei.api.client.registry.transfer.simple.SimpleTransferHandler
import me.shedaniel.rei.api.common.category.CategoryIdentifier
import me.shedaniel.rei.api.common.util.EntryStacks
import net.minecraft.world.item.crafting.RecipeHolder

open class ReiCompatClient : REIClientPlugin {
    override fun registerCategories(registry: CategoryRegistry) {
        registry.add(
            DefaultRksCategory()
        ) { configuration: CategoryRegistry.CategoryConfiguration<DefaultRksMachineRecipeDisplay<*>?> ->
            configuration.addWorkstations(
                EntryStacks.of(GenerationsUtilityBlocks.RKS_MACHINE)
            )
        }
    }

    override fun registerDisplays(registry: DisplayRegistry) {
        registry.registerRecipeFiller(
            RksRecipe::class.java, GenerationsCoreRecipeTypes.RKS
        ) { recipe: RecipeHolder<RksRecipe?>? ->
            DefaultRksMachineRecipeDisplay.of(
                recipe
            )
        }
    }


    override fun registerScreens(registry: ScreenRegistry) {
        registry.registerContainerClickArea(
            { rksMachineScreen: RksMachineScreen? -> Rectangle(90, 35, 22, 15) },
            RksMachineScreen::class.java, RKS_MACHINE
        )
    }

    override fun registerTransferHandlers(registry: TransferHandlerRegistry) {
        registry.register(
            SimpleTransferHandler.create(
                RksMachineContainer::class.java, RKS_MACHINE, SimpleTransferHandler.IntRange(1, 10)
            )
        )
    }

    companion object {
        @JvmField
        val RKS_MACHINE: CategoryIdentifier<out DefaultRksMachineRecipeDisplay<*>> =
            CategoryIdentifier.of("generationscore", "rks_machine")
    }
}
