package generations.gg.generations.core.generationscore.world.level.block.utilityblocks;

import com.pokemod.pokemod.world.level.block.entities.BreederBlockEntity;
import com.pokemod.pokemod.world.level.block.entities.PokeModBlockEntities;
import com.pokemod.pokemod.world.level.block.generic.GenericRotatableModelBlock;

public class BreederBlock extends GenericRotatableModelBlock<BreederBlockEntity> {

    public BreederBlock(Properties materialIn) {
        super(materialIn, PokeModBlockEntities.BREEDER, null);
    }
}
