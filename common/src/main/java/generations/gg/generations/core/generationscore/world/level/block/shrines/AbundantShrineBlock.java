package generations.gg.generations.core.generationscore.world.level.block.shrines;

import generations.gg.generations.core.generationscore.world.level.block.entities.shrines.AbundantShrineBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class AbundantShrineBlock extends ShrineBlock<AbundantShrineBlockEntity> {

    public AbundantShrineBlock(BlockBehaviour.Properties properties) {
        super(properties, GenerationsBlockEntities.ABUNDANT_SHRINE, GenerationsBlockEntityModels.ABUNDANT_SHRINE);
    }
}
