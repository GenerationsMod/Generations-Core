package generations.gg.generations.core.generationscore.world.level.block.forge;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.FarmlandWaterManager;

public class SoftSoilBlockImpl {
    public static boolean isNearWater(LevelReader level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        for (BlockPos blockpos : BlockPos.betweenClosed(pos.offset(-4, 0, -4), pos.offset(4, 1, 4))) {
            if (!state.canBeHydrated(level, pos, level.getFluidState(blockpos), blockpos)) continue;
            return true;
        }
        return FarmlandWaterManager.hasBlockWaterTicket(level, pos);
    }
}
