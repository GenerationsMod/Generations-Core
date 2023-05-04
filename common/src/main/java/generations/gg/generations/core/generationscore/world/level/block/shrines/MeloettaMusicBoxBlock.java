package generations.gg.generations.core.generationscore.world.level.block.shrines;

import com.pokemod.pokemod.world.level.block.entities.MeloettaMusicBoxBlockEntity;
import com.pokemod.pokemod.world.level.block.entities.PokeModBlockEntities;
import com.pokemod.pokemod.world.level.block.entities.PokeModBlockEntityModels;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class MeloettaMusicBoxBlock extends ShrineBlock<MeloettaMusicBoxBlockEntity> {

    public MeloettaMusicBoxBlock(BlockBehaviour.Properties properties) {
        super(properties, PokeModBlockEntities.MELOETTA_MUSIC_BOX, PokeModBlockEntityModels.MELOETTA_MUSIC_BOX);
    }
}
