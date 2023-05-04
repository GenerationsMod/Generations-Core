package generations.gg.generations.core.generationscore.world.level.block.entities.generic;

import generations.gg.generations.core.generationscore.world.level.block.entities.DyedVariantBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.PokeModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class GenericDyedVariantBlockEntity extends DyedVariantBlockEntity {
    public GenericDyedVariantBlockEntity(BlockPos arg2, BlockState arg3) {
        super(PokeModBlockEntities.GENERIC_DYED_VARIANT.get(), arg2, arg3);
    }
}
