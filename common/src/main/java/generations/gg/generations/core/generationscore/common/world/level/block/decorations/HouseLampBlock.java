package generations.gg.generations.core.generationscore.common.world.level.block.decorations;

import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.GenericModelProvidingBlockEntity;
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class HouseLampBlock extends GenericRotatableModelBlock<GenericModelProvidingBlockEntity> {
    public HouseLampBlock(BlockBehaviour.Properties props) {
        super(props, GenerationsBlockEntities.GENERIC_MODEL_PROVIDING, GenerationsBlockEntityModels.HOUSE_LAMP);
    }
}