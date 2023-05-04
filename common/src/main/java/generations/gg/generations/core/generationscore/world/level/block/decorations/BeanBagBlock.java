package generations.gg.generations.core.generationscore.world.level.block.decorations;

import generations.gg.generations.core.generationscore.world.level.block.entities.PokeModBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.PokeModBlockEntityModels;
import generations.gg.generations.core.generationscore.world.level.block.entities.generic.GenericModelProvidingBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericRotatableModelBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class BeanBagBlock extends GenericRotatableModelBlock<GenericModelProvidingBlockEntity> implements SittableBlock {

    public BeanBagBlock(BlockBehaviour.Properties props) {
        super(props, PokeModBlockEntities.GENERIC_MODEL_PROVIDING, PokeModBlockEntityModels.SNORLAX_BEAN_BAG);
    }

    @Override
    public double getOffset() {
        return 0.8f;
    }
}