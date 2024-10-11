package generations.gg.generations.core.generationscore.common.world.level.block;

import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.GenericModelProvidingBlockEntity;
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HdTvBlock extends GenericRotatableModelBlock<GenericModelProvidingBlockEntity> {
    public static final GenerationsVoxelShapes.GenericRotatableShapes SHAPE = GenerationsVoxelShapes.generateRotationalVoxelShape(
            Shapes.join(Shapes.box(0.375, 0, 0.4375, 2.65625, 0.125, 0.5625),
                    Shapes.join(Shapes.box(0.4375, 0.125, 0.4375, 2.59375, 0.25, 0.5625),
                            Shapes.join(Shapes.box(1.1875, 0, 0.375, 1.828125, 0.375, 0.6875),
                                    Shapes.join(Shapes.box(0, 0.1875, 0.4375, 3, 1.921875, 0.625),
                                            Shapes.box(0.375, 0.375, 0.375, 2.625, 1.734375, 0.4375), BooleanOp.OR), BooleanOp.OR), BooleanOp.OR), BooleanOp.OR),
            Direction.SOUTH,
            3, 2, 1);


    public HdTvBlock(BlockBehaviour.Properties properties) {
        super(properties, GenerationsBlockEntities.GENERIC_MODEL_PROVIDING, GenerationsBlockEntityModels.HDTV, 2, 1, 0);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE.getShape(state);
    }
}
