package generations.gg.generations.core.generationscore.world.level.block.decorations;

import generations.gg.generations.core.generationscore.world.level.block.GenerationsVoxelShapes;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.world.level.block.entities.generic.GenericModelProvidingBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericRotatableModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class BookShelfBlock extends GenericRotatableModelBlock<GenericModelProvidingBlockEntity> {
    private final static GenerationsVoxelShapes.GenericRotatableShapes SHAPES = GenerationsVoxelShapes.generateRotationalVoxelShape(Shapes.box(-0.9312499999999999, 0, 0.05625000000000002, 1.93125, 2.8375, 0.9625).move(1, 0, 0), Direction.SOUTH, 3, 3, 1);

    public BookShelfBlock(BlockBehaviour.Properties props) {
        super(props, GenerationsBlockEntities.GENERIC_MODEL_PROVIDING, GenerationsBlockEntityModels.BOOKSHELF, 2, 2, 0);
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES.getShape(state);
    }
}