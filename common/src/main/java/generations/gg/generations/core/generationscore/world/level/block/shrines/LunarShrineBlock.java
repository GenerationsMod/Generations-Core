package generations.gg.generations.core.generationscore.world.level.block.shrines;

import generations.gg.generations.core.generationscore.world.level.block.entities.LunarShrineBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.PokeModBlockEntities;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class LunarShrineBlock extends ShrineBlock<LunarShrineBlockEntity> {

    public LunarShrineBlock(BlockBehaviour.Properties properties) {
        super(properties, PokeModBlockEntities.LUNAR_SHRINE, null);
    }
}
