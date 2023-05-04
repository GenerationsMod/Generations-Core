package generations.gg.generations.core.generationscore.world.level.block.shrines;

import com.pokemod.pokemod.world.level.block.entities.LunarShrineBlockEntity;
import com.pokemod.pokemod.world.level.block.entities.PokeModBlockEntities;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class LunarShrineBlock extends ShrineBlock<LunarShrineBlockEntity> {

    public LunarShrineBlock(BlockBehaviour.Properties properties) {
        super(properties, PokeModBlockEntities.LUNAR_SHRINE, null);
    }
}
