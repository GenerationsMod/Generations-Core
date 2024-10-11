package generations.gg.generations.core.generationscore.common.world.level.block.entities.generic;

import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.ShrineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class GenericShrineBlockEntity extends ShrineBlockEntity {
    public GenericShrineBlockEntity(BlockPos arg2, BlockState arg3) {
        super(GenerationsBlockEntities.GENERIC_SHRINE.get(), arg2, arg3);
    }
}
