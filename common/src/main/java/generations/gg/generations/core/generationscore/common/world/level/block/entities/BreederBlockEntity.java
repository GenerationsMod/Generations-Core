package generations.gg.generations.core.generationscore.common.world.level.block.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BreederBlockEntity extends ModelProvidingBlockEntity {
    public BreederBlockEntity(BlockPos arg2, BlockState arg3) {
        super(GenerationsBlockEntities.BREEDER.get(), arg2, arg3);
    }
}
