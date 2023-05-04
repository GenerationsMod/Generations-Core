package generations.gg.generations.core.generationscore.world.level.block.entities.generic;

import com.pokemod.pokemod.world.level.block.entities.ModelProvidingBlockEntity;
import com.pokemod.pokemod.world.level.block.entities.PokeModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class GenericModelProvidingBlockEntity extends ModelProvidingBlockEntity {
    public GenericModelProvidingBlockEntity(BlockPos arg2, BlockState arg3) {
        super(PokeModBlockEntities.GENERIC_MODEL_PROVIDING.get(), arg2, arg3);
    }
}
