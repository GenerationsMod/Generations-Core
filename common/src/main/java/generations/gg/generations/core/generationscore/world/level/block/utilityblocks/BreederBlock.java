package generations.gg.generations.core.generationscore.world.level.block.utilityblocks;

import generations.gg.generations.core.generationscore.world.level.block.entities.BreederBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericRotatableModelBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class BreederBlock extends GenericRotatableModelBlock<BreederBlockEntity> {

    public BreederBlock(BlockBehaviour.Properties materialIn) {
        super(materialIn, GenerationsBlockEntities.BREEDER, GenerationsBlockEntityModels.BREEDER);
    }
}
