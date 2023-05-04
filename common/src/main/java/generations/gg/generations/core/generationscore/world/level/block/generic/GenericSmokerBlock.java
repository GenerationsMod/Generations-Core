package generations.gg.generations.core.generationscore.world.level.block.generic;

import com.pokemod.pokemod.world.level.block.entities.generic.GenericSmokerBlockEntity;
import com.pokemod.pokemod.world.level.block.entities.PokeModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SmokerBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class GenericSmokerBlock extends SmokerBlock {
    public GenericSmokerBlock(Properties arg) {
        super(arg);
    }

    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new GenericSmokerBlockEntity(pos, state);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(@NotNull Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> blockEntityType) {
        return SmokerBlock.createFurnaceTicker(level, blockEntityType, PokeModBlockEntities.GENERIC_SMOKER.get());
    }
}
