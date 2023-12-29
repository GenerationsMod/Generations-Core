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
    private static final GenerationsVoxelShapes.GenericRotatableShapes SHAPE = GenerationsVoxelShapes.generateRotationalVoxelShape(
            Shapes.join(Shapes.box(0, 0, 0.109375, 1, 0.60625, 0.875),
                    Shapes.join(Shapes.box(0.8125, 0.6, 0.109375, 1, 0.69375, 0.875),
                            Shapes.join(Shapes.box(0, 0.6, 0.109375, 0.1875, 0.69375, 0.875),
                                    Shapes.join(Shapes.box(0, 0, 0.109375, 1, 0.6, 0.875),
                                            Shapes.join(Shapes.box(0.1875, 0.5875, 0.359375, 0.8125, 0.75, 0.625),
                                                    Shapes.join(Shapes.box(0.3125, 0.75, 0.421875, 0.6875, 1.2875, 0.5625),
                                                            Shapes.join(Shapes.box(0.21875, 1.1875, 0.4609375, 0.78125, 1.4125, 0.5296875),
                                                                    Shapes.box(0.15625, 0.75, 0.4609375, 0.84375, 1.1, 0.5296875), OR), OR), OR), OR), OR), OR), OR),
            Direction.NORTH, 1, 2, 1);

    public LugiaShrineBlock(Properties materialIn) {
        super(materialIn, GenerationsBlockEntityModels.LUGIA_SHRINE, 0, 1, 0, GenerationsItems.SILVER_WING);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE.getShape(state);
    }
}
