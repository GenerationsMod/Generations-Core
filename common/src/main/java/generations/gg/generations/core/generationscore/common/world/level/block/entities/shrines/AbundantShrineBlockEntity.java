package generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines;

import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class AbundantShrineBlockEntity extends ShrineBlockEntity {
    public AbundantShrineBlockEntity(BlockPos pos, BlockState state) {
        super(GenerationsBlockEntities.ABUNDANT_SHRINE.get(), pos, state);
    }
}
