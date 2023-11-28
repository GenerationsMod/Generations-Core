package generations.gg.generations.core.generationscore.world.level.block;

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

public class HdTvBlock extends GenericRotatableModelBlock<GenericModelProvidingBlockEntity> {
    public static final GenerationsVoxelShapes.GenericRotatableShapes SHAPE = GenerationsVoxelShapes.generateRotationalVoxelShape(
            Shapes.join(Shapes.box(0.1875, 0, 0.375, 0.8125, 0.125, 0.75),
                    Shapes.join(Shapes.box(0.1875, 0.125, 0.375, 0.8125, 0.25, 0.6875),
                        Shapes.join(Shapes.box(-0.625, 0, 0.4375, 1.625, 0.375, 0.5625),
                            Shapes.join(Shapes.box(0.25, 0.25, 0.375, 0.75, 0.375, 0.6875),
                                Shapes.box(-1, 0.1875, 0.375, 2, 1.9375, 0.625), BooleanOp.OR), BooleanOp.OR), BooleanOp.OR), BooleanOp.OR).move(1.5, 0, 0),
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
