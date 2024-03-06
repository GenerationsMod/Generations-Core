package generations.gg.generations.core.generationscore.world.level.block.entities;

import com.cobblemon.mod.common.block.PCBlock;
import generations.gg.generations.core.generationscore.client.model.ModelContextProviders;
import generations.gg.generations.core.generationscore.world.level.block.utilityblocks.DyeableBlock;
import generations.gg.generations.core.generationscore.world.level.block.utilityblocks.DyedPcBlock;
import generations.gg.generations.core.generationscore.world.level.block.utilityblocks.PcBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

import static generations.gg.generations.core.generationscore.world.level.block.entities.DyedVariantBlockEntity.COLOR_MAP;

public class DyedPcBlockEntity extends PcBlockEntity<DyedPcBlockEntity> implements ModelContextProviders.TintProvider {
    public DyedPcBlockEntity(@NotNull BlockPos blockPos, @NotNull BlockState blockState) {
        super(GenerationsBlockEntities.DYED_PC.get(), blockPos, blockState);
    }

    public static final BlockEntityTicker<DyedPcBlockEntity> TICKER = (world, blockPos, blockState, blockEntity) -> {
        if (!world.isClientSide) {
            blockEntity.togglePCOn(blockEntity.getInRangeViewerCount(world, blockEntity.getBlockPos(), 0.5) > 0);
        }
    };

    public DyeColor getColor() {
        return ((DyeableBlock<?, ?>) getBlockState().getBlock()).getColor();
    }

    @Override
    public Vector3f getTint() {
        return COLOR_MAP.getOrDefault(getColor(), null);
    }
}
