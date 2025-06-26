package generations.gg.generations.core.generationscore.common.compat.rei

import com.google.common.collect.Lists
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsUtilityBlocks
import me.shedaniel.math.Point
import me.shedaniel.math.Rectangle
import me.shedaniel.rei.api.client.gui.Renderer
import me.shedaniel.rei.api.client.gui.widgets.Slot
import me.shedaniel.rei.api.client.gui.widgets.Widget
import me.shedaniel.rei.api.client.gui.widgets.Widgets
import me.shedaniel.rei.api.client.registry.display.DisplayCategory
import me.shedaniel.rei.api.common.category.CategoryIdentifier
import me.shedaniel.rei.api.common.display.DisplayMerger
import me.shedaniel.rei.api.common.entry.EntryIngredient
import me.shedaniel.rei.api.common.util.EntryStacks
import net.minecraft.network.chat.Component
import net.minecraft.world.inventory.AbstractContainerMenu

class DefaultRksCategory : DisplayCategory<DefaultRksMachineRecipeDisplay<*>> {
    override fun getCategoryIdentifier(): CategoryIdentifier<out DefaultRksMachineRecipeDisplay<*>> =
        ReiCompatClient.RKS_MACHINE

    override fun getTitle(): Component = Component.translatable("gui.recipe_viewer.category.rks_machine")

    override fun getIcon(): Renderer = EntryStacks.of(GenerationsUtilityBlocks.RKS_MACHINE.value())

    override fun setupDisplay(display: DefaultRksMachineRecipeDisplay<*>, bounds: Rectangle): List<Widget> {
        val startPoint = Point(bounds.centerX - 58, bounds.centerY - 27)
        val widgets: MutableList<Widget> = Lists.newArrayList()
        widgets.add(Widgets.createRecipeBase(bounds))
        widgets.add(
            Widgets.createArrow(Point(startPoint.x + 60, startPoint.y + 18))
                .animationDurationTicks(display.weavingTime.toDouble())
        )
        widgets.add(Widgets.createResultSlotBackground(Point(startPoint.x + 95, startPoint.y + 19)))
        val input = display.getInputIngredients(3, 3)
        val slots: MutableList<Slot> = Lists.newArrayList()
        for (y in 0..2) for (x in 0..2) slots.add(
            Widgets.createSlot(
                Point(
                    startPoint.x + 1 + x * 18,
                    startPoint.y + 1 + y * 18
                )
            ).markInput()
        )
        for (ingredient in input) {
            slots[ingredient.index].entries(ingredient.get())
        }
        widgets.addAll(slots)
        widgets.add(
            Widgets.createSlot(Point(startPoint.x + 95, startPoint.y + 19)).entries(
                display.outputEntries[0]
            ).disableBackground().markOutput()
        )
        if (display.isShapeless) {
            widgets.add(Widgets.createShapelessIcon(bounds))
        }
        return widgets
    }

    override fun getDisplayMerger(): DisplayMerger<DefaultRksMachineRecipeDisplay<*>> {
        return object : DisplayMerger<DefaultRksMachineRecipeDisplay<*>> {
            override fun canMerge(
                first: DefaultRksMachineRecipeDisplay<*>,
                second: DefaultRksMachineRecipeDisplay<*>
            ): Boolean {
                if (first.categoryIdentifier != second.categoryIdentifier) return false
                if (!equals(
                        first.getOrganisedInputEntries<AbstractContainerMenu>(3, 3),
                        second.getOrganisedInputEntries<AbstractContainerMenu>(3, 3)
                    )
                ) return false
                if (!equals(first.outputEntries, second.outputEntries)) return false
                if (first.isShapeless != second.isShapeless) return false
                if (first.width != second.width) return false
                if (first.height != second.height) return false
                return true
            }

            override fun hashOf(display: DefaultRksMachineRecipeDisplay<*>): Int {
                return display.categoryIdentifier.hashCode() * 31 * 31 * 31 + display.getOrganisedInputEntries<AbstractContainerMenu>(
                    3,
                    3
                ).hashCode() * 31 * 31 + display.outputEntries.hashCode()
            }

            fun equals(l1: List<EntryIngredient>, l2: List<EntryIngredient>): Boolean {
                if (l1.size != l2.size) return false
                val it1 = l1.iterator()
                val it2 = l2.iterator()
                while (it1.hasNext() && it2.hasNext()) {
                    if (it1.next() != it2.next()) return false
                }
                return true
            }
        }
    }
}
