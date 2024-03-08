package generations.gg.generations.core.generationscore.world.level.block.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class SpawnerBlockEntity extends SimpleBlockEntity {

    public SpawnerBlockEntity(BlockPos pos, BlockState state) {
        super(GenerationsBlockEntities.SPAWNER_BLOCK.get(), pos, state);
    }

    @Override
    protected void readNbt(CompoundTag nbt) {

    }

    @Override
    protected void writeNbt(CompoundTag nbt) {

    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, SpawnerBlockEntity entity) {

    }
}
