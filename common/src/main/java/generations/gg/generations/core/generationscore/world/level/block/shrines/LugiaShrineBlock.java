package generations.gg.generations.core.generationscore.world.level.block.shrines;

import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsVoxelShapes;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import static net.minecraft.world.phys.shapes.BooleanOp.OR;

public class LugiaShrineBlock extends BirdShrineBlock {
    private static final GenerationsVoxelShapes.GenericRotatableShapes SHAPE = GenerationsVoxelShapes.generateRotationalVoxelShape(Shapes.or(
            Shapes.box(0, 0, 0, 1, 0.178125, 1),
                Shapes.box(0, 0, 0.125, 1, 0.6875, 0.875),
                        Shapes.box(0.3125, 0.6875, 0.3125, 0.6875, 1.375, 0.6875),
                                Shapes.box(0.375, 0.6875, -0.0625, 0.625, 1, 0.3125),
                                        Shapes.box(0.375, 1.375, 0.4375, 0.625, 1.8125, 0.8125),
                                                Shapes.box(0.6875, 1.0625, 0.3125, 1.0625, 1.75, 0.5),
                                                        Shapes.box(-0.0625, 1.0625, 0.3125, 0.3125, 1.75, 0.5),
                                                                Shapes.box(-0.5, 1.3125, 0.1875, 0.0625, 1.9375, 0.375),
                                                                        Shapes.box(0.9375, 1.3125, 0.1875, 1.5, 1.9375, 0.375)),

    Direction.SOUTH, 3, 2, 1, 1, 0);

    public LugiaShrineBlock(Properties materialIn) {
        super(materialIn, GenerationsBlockEntityModels.LUGIA_SHRINE, 2, 1, 0, GenerationsItems.SILVER_WING);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE.getShape(state);
    }

    @Override
    public int getBaseX() {
        return 1;
    }

    @Override
    protected boolean validPosition(int x, int y, int z) {
        return x == getBaseX() || y != 0;
    }
}
