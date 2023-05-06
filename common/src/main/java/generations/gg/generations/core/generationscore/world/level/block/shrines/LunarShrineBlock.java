package generations.gg.generations.core.generationscore.world.level.block.shrines;

import generations.gg.generations.core.generationscore.world.level.block.entities.LunarShrineBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class LunarShrineBlock extends ShrineBlock<LunarShrineBlockEntity> {

    public LunarShrineBlock(BlockBehaviour.Properties properties) {
        super(properties, GenerationsBlockEntities.LUNAR_SHRINE, null);
    }
}
