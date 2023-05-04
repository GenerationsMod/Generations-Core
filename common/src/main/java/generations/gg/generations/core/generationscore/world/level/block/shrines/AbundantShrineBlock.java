package generations.gg.generations.core.generationscore.world.level.block.shrines;

import generations.gg.generations.core.generationscore.world.level.block.entities.AbundantShrineBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.PokeModBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.PokeModBlockEntityModels;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class AbundantShrineBlock extends ShrineBlock<AbundantShrineBlockEntity> {

    public AbundantShrineBlock(BlockBehaviour.Properties properties) {
        super(properties, PokeModBlockEntities.ABUNDANT_SHRINE, PokeModBlockEntityModels.ABUNDANT_SHRINE);
    }
}
