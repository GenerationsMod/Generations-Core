package generations.gg.generations.core.generationscore.common.world.level.block

import generations.gg.generations.core.generationscore.common.world.item.BlockItemWithLang
import generations.gg.generations.core.generationscore.common.world.item.FormChanging
import generations.gg.generations.core.generationscore.common.world.item.FormChangingToggle
import net.minecraft.world.item.BlockItem
import net.minecraft.world.level.block.Block

class FormChangingBlockItem(block: Block, properties: Properties, override val provider: String): BlockItemWithLang(block, properties), FormChangingToggle
