package generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines;

import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.common.world.level.block.shrines.InteractShrineBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class TaoTrioShrineBlockEntity extends InteractShrineBlockEntity {
    public TaoTrioShrineBlockEntity(BlockPos pos, BlockState state) {
        super(GenerationsBlockEntities.TAO_TRIO_SHRINE.get(), pos, state);
    }
}
