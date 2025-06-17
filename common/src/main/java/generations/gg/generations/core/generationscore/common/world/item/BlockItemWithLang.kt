package generations.gg.generations.core.generationscore.common.world.item

import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlockItem
import net.minecraft.core.Holder
import net.minecraft.world.level.block.Block

open class BlockItemWithLang(block: Holder<Block>, properties: Properties) : GenerationsBlockItem(block, properties), LangTooltip
