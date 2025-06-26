package generations.gg.generations.core.generationscore.common.world.item

import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

class ChargedMeltanBox(properties: Item.Properties): ItemWithLangTooltipImpl(properties.stacksTo(1)) {
    override fun isFoil(stack: ItemStack): Boolean = true
}