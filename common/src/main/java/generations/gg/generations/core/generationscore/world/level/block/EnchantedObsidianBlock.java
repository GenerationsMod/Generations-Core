package generations.gg.generations.core.generationscore.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

/**
 * @author JT122406
 * @see net.minecraftforge.common.extensions.IForgeBlock#isPortalFrame(BlockState, BlockGetter, BlockPos)
 * Allows EnchantedObsidian to be used as a nether portal frame
 */
public class EnchantedObsidianBlock extends Block implements IPortalBlock {

    public EnchantedObsidianBlock(Properties arg) {
        super(arg);
    }

    public boolean isPortalFrame(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(GenerationsBlocks.ENCHANTED_OBSIDIAN_SET.getBaseBlock());
    }
}

