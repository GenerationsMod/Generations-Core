package generations.gg.generations.core.generationscore.common.util.adapters

import generations.gg.generations.core.generationscore.common.util.Codecs
import net.minecraft.world.item.ItemStack

val ITEM_STACK_ADAPTER  = Codecs.fromCodec(ItemStack.OPTIONAL_CODEC)
