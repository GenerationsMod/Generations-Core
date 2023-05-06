package generations.gg.generations.core.generationscore.world.level.block.utilityblocks;

import generations.gg.generations.core.generationscore.world.level.block.entities.BoxBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.PokeModBlockEntityModels;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericRotatableModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("deprecation")
public class BoxBlock extends GenericRotatableModelBlock<BoxBlockEntity> {

    public BoxBlock(BlockBehaviour.Properties props) {
        super(props, GenerationsBlockEntities.BOX, PokeModBlockEntityModels.BOX);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return Shapes.box(0.2, 0.0, 0.2, 0.8, 0.6, 0.8);
    }
}
