package generations.gg.generations.core.generationscore.world.level.block.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class HealerBlockEntity extends DyedVariantBlockEntity { //TODO: Convert to DyedVariantBlockEntity
    public HealerBlockEntity(BlockPos pos, BlockState state) {
        super(GenerationsBlockEntities.HEALER.get(), pos, state);
    }
}
