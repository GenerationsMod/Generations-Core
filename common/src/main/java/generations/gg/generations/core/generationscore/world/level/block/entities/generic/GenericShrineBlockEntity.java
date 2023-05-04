package generations.gg.generations.core.generationscore.world.level.block.entities.generic;

import com.pokemod.pokemod.world.level.block.entities.PokeModBlockEntities;
import com.pokemod.pokemod.world.level.block.entities.ShrineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class GenericShrineBlockEntity extends ShrineBlockEntity {
    public GenericShrineBlockEntity(BlockPos arg2, BlockState arg3) {
        super(PokeModBlockEntities.GENERIC_SHRINE.get(), arg2, arg3);
    }
}
