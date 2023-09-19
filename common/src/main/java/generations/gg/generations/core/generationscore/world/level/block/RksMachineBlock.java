package generations.gg.generations.core.generationscore.world.level.block;

import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.world.level.block.entities.generic.GenericModelProvidingBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericRotatableModelBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class RksMachineBlock extends GenericRotatableModelBlock<GenericModelProvidingBlockEntity> {
    public RksMachineBlock(BlockBehaviour.Properties copy) {
        super(copy, GenerationsBlockEntities.GENERIC_MODEL_PROVIDING, GenerationsBlockEntityModels.RKS_MACHINE, 1, 1, 1);

    }
}
