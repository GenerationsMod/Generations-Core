package generations.gg.generations.core.generationscore.world.level;

import generations.gg.generations.core.generationscore.world.level.block.GenerationsBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;

public interface IPortalBlock {
    boolean isPortalFrame(BlockState state, BlockGetter level, BlockPos pos);
}
