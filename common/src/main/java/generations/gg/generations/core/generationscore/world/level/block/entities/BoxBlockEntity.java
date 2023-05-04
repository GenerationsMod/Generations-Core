package generations.gg.generations.core.generationscore.world.level.block.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BoxBlockEntity extends ModelProvidingBlockEntity {
    public BoxBlockEntity(BlockPos pos, BlockState state) {
        super(PokeModBlockEntities.BOX.get(), pos, state);
    }
}
