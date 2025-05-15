package generations.gg.generations.core.generationscore.common.world.item

import net.minecraft.world.item.BlockItem
import net.minecraft.world.level.block.Block

open class BlockItemWithLang(block: Block, properties: Properties) : BlockItem(block, properties), LangTooltip
