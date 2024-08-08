package generations.gg.generations.core.generationscore.common.world.level.block.entities;

import com.cobblemon.mod.common.block.entity.PCBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class DefaultPcBlockEntity extends PcBlockEntity<DefaultPcBlockEntity> {
    public DefaultPcBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        super(GenerationsBlockEntities.PC.get(), blockPos, blockState);
    }

    public static final BlockEntityTicker<DefaultPcBlockEntity> TICKER = (world, blockPos, blockState, blockEntity) -> {
        if (!world.isClientSide) {
            blockEntity.togglePCOn(blockEntity.getInRangeViewerCount(world, blockEntity.getBlockPos(), 0.5) > 0);
        }
    };
}
