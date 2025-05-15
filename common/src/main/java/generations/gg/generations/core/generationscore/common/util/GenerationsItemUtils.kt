package generations.gg.generations.core.generationscore.common.util

import generations.gg.generations.core.generationscore.common.world.item.GenericRotatableBlockItem
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block

object GenerationsItemUtils {
    fun <T : Block> generateBlockItem(t: T, properties: Item.Properties): BlockItem {
        return if (t is GenericRotatableModelBlock<*>) {
            GenericRotatableBlockItem(t, properties)
        } else {
            BlockItem(t, properties)
        }
    }
}
