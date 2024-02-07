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
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class WorkDeskBlock extends GenericRotatableModelBlock<GenericModelProvidingBlockEntity> {
    private final static GenerationsVoxelShapes.GenericRotatableShapes SHAPES = GenerationsVoxelShapes.generateRotationalVoxelShape(Shapes.or(
            Shapes.box(2.78125, 0, -0.7124999999999999, 2.89375, 1.125, -0.6000000000000001),
            Shapes.box(0.10624999999999996, 0, 0.5874999999999999, 0.21875, 1.125, 0.7000000000000002),
            Shapes.box(2.78125, 0, 0.5874999999999999, 2.89375, 1.125, 0.7000000000000002),
            Shapes.box(0.10624999999999996, 0, -0.7124999999999999, 0.21875, 1.125, -0.6000000000000001),
            Shapes.box(0.050000000000000044, 1.11875, -0.7625000000000002, 2.95, 1.4124999999999999, 0.7625),
            Shapes.box(0.1499999999999999, 0.14125, -0.675, 2.8500000000000005, 1.1412499999999999, 0.6624999999999999)
    ), Direction.SOUTH,3, 2, 2);

    public WorkDeskBlock(BlockBehaviour.Properties props) {
        super(props, GenerationsBlockEntities.GENERIC_MODEL_PROVIDING, GenerationsBlockEntityModels.WORK_DESK,2, 1, 1);
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES.getShape(state);
    }
}