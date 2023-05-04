package generations.gg.generations.core.generationscore.world.level.block.decorations;

import com.pokemod.pokemod.world.level.block.entities.PokeModBlockEntities;
import com.pokemod.pokemod.world.level.block.entities.generic.GenericModelProvidingBlockEntity;
import com.pokemod.pokemod.world.level.block.entities.PokeModBlockEntityModels;
import com.pokemod.pokemod.world.level.block.generic.GenericRotatableModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.*;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("deprecation")
public class PokecenterScarletSignBlock extends GenericRotatableModelBlock<GenericModelProvidingBlockEntity> {
    public PokecenterScarletSignBlock(Properties materialIn) {
        super(materialIn, PokeModBlockEntities.GENERIC_MODEL_PROVIDING, PokeModBlockEntityModels.POKECENTER_SCARLET_SIGN);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return Shapes.box(-1,-1, 0, 2, 2, 0.25);
    }
}
