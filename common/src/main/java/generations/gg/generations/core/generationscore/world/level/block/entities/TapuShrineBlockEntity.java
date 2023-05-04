package generations.gg.generations.core.generationscore.world.level.block.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TapuShrineBlockEntity extends ShrineBlockEntity {
    public TapuShrineBlockEntity(BlockPos pos, BlockState state) {
        super(PokeModBlockEntities.TAPU_SHRINE.get(), pos, state);
    }
}
