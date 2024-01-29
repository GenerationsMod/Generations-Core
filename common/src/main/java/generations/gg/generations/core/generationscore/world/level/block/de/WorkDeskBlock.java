package generations.gg.generations.core.generationscore.world.level.block.de;

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
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class WorkDeskBlock extends GenericRotatableModelBlock<GenericModelProvidingBlockEntity> {
    private final static GenerationsVoxelShapes.GenericRotatableShapes SHAPES = GenerationsVoxelShapes.generateRotationalVoxelShape(Shapes.or(
                    Shapes.box(1.78125, 0, -0.21250000000000002, 1.89375, 1.125, -0.09999999999999998),
                    Shapes.box(-0.89375, 0, 1.0875, -0.78125, 1.125, 1.2000000000000002),
                    Shapes.box(1.78125, 0, 1.0875, 1.89375, 1.125, 1.2000000000000002),
                    Shapes.box(-0.89375, 0, -0.21250000000000002, -0.78125, 1.125, -0.10000000000000009),
                    Shapes.box(-0.95, 1.11875, -0.26249999999999996, 1.95, 1.4124999999999999, 1.2625),
                    Shapes.box(-0.8500000000000001, 0.14125, -0.17500000000000004, 1.8500000000000003, 1.1412499999999999, 1.1624999999999999)
    ).move(0.725, 0, 0.38125), Direction.SOUTH,3, 2, 2);

    public WorkDeskBlock(BlockBehaviour.Properties props) {
        super(props, GenerationsBlockEntities.GENERIC_MODEL_PROVIDING, GenerationsBlockEntityModels.WORK_DESK,2, 1, 1);
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES.getShape(state);
    }
}