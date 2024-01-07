package generations.gg.generations.core.generationscore.world.level.block.utilityblocks;

import generations.gg.generations.core.generationscore.world.level.block.GenerationsVoxelShapes;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import static net.minecraft.world.phys.shapes.BooleanOp.OR;

public class RotomPc extends PcBlock {
    public static final GenerationsVoxelShapes.GenericRotatableShapes SHAPE = GenerationsVoxelShapes.generateRotationalVoxelShape(
            Shapes.join(Shapes.box(0.015625, 0, 0.28125, 0.978125, 2, 0.5625),
                    Shapes.join(Shapes.box(0.015625, 0.6875, 0, 0.978125, 0.8125, 0.28125),
                            Shapes.join(Shapes.box(0.05937500000000001, 0.875, 0, 0.07500000000000001, 1.625, 0.28125),
                                    Shapes.join(Shapes.box(0.390625, 0.90625, 0.21875, 0.603125, 1.0625, 0.296875),
                                        Shapes.join(Shapes.box(0.015625, 1.9375, 0.21875, 0.978125, 2.5, 0.4375),
                                                Shapes.box(0.1875, 1.25, 0.5625, 0.8125, 1.5, 0.625), OR), OR), OR), OR), OR),
            Direction.NORTH,
            1, 3, 1);

    public RotomPc(@NotNull BlockBehaviour.Properties arg) {
        super(arg, GenerationsBlockEntityModels.ROTOM_PC, 0, 2, 0);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE.getShape(state);
    }
}
