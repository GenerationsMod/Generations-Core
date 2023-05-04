package generations.gg.generations.core.generationscore.world.level.block.fabric;

import com.google.common.collect.Streams;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.LevelReader;

public class SoftSoilBlockImpl {
    public static boolean isNearWater(LevelReader level, BlockPos pos) {
        return Streams.stream(BlockPos.betweenClosed(pos.offset(-4, 0, -4), pos.offset(4, 1, 4)).iterator()).anyMatch(blockPos -> level.getFluidState(blockPos).is(FluidTags.WATER));
    }
}
