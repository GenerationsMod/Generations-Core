package generations.gg.generations.core.generationscore.world.level.block.decorations;

import com.pokemod.pokemod.world.level.block.entities.PokeModBlockEntities;
import com.pokemod.pokemod.world.level.block.entities.generic.GenericModelProvidingBlockEntity;
import com.pokemod.pokemod.world.level.block.entities.PokeModBlockEntityModels;
import com.pokemod.pokemod.world.level.block.generic.GenericRotatableModelBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class HouseLampBlock extends GenericRotatableModelBlock<GenericModelProvidingBlockEntity> {
    public HouseLampBlock(BlockBehaviour.Properties props) {
        super(props, PokeModBlockEntities.GENERIC_MODEL_PROVIDING, PokeModBlockEntityModels.HOUSE_LAMP);
    }
}