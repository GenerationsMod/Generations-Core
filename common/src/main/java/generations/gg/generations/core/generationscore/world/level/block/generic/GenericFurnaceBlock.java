package generations.gg.generations.core.generationscore.world.level.block.generic;

import generations.gg.generations.core.generationscore.world.level.block.entities.PokeModBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.generic.GenericFurnaceBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GenericFurnaceBlock extends FurnaceBlock {
    public GenericFurnaceBlock(Properties arg) {
        super(arg);
    }

    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new GenericFurnaceBlockEntity(pos, state);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> blockEntityType) {
        return FurnaceBlock.createFurnaceTicker(level, blockEntityType, PokeModBlockEntities.GENERIC_FURNACE.get());
    }
}
