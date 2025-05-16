package generations.gg.generations.core.generationscore.common.world.level.block

import net.minecraft.core.BlockPos
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState

/**
 * @author JT122406
 * @see net.minecraftforge.common.extensions.IForgeBlock.isPortalFrame
 */
class EnchantedObsidianBlock(arg: Properties) : Block(arg),
    IPortalBlock {
    override fun isPortalFrame(state: BlockState, level: BlockGetter, pos: BlockPos): Boolean {
        return state.`is`(GenerationsBlocks.ENCHANTED_OBSIDIAN_SET.getBaseBlock())
    }
}

