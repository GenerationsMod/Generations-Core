package generations.gg.generations.core.generationscore.world.level.block;

import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.world.level.block.entities.generic.GenericModelProvidingBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericRotatableModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DoubleStreetLampBlock extends GenericRotatableModelBlock<GenericModelProvidingBlockEntity> {
    private static GenerationsVoxelShapes.GenericRotatableShapes SHAPE = GenerationsVoxelShapes.generateRotationalVoxelShape(Shapes.or(Shapes.box(0, 0, 0, 1, 0.16875, 1),
            Shapes.box(0.1875, 0, 0.1875, 0.8125, 1.15, 0.8125),
                    Shapes.box(0.375, 1.125, 0.375, 0.625, 1.25625, 0.625),
                            Shapes.box(0.4375, 1.25, 0.4375, 0.5625, 3.94375, 0.5625),
                                    Shapes.box(0.25, 3.64375, 0.25, 0.75, 4.043749999999999, 0.75),
                                            Shapes.box(0.875, 3.01875, 0.0625, 1.75, 3.8562499999999993, 0.9375),
                                                    Shapes.box(-0.5625, 3.83125, 0.4375, 1.5625, 4.231249999999999, 0.5625),
                                                            Shapes.box(-0.75, 3.01875, 0.0625, 0.125, 3.8562499999999993, 0.9375)), Direction.NORTH, 3, 5, 1, 1, 0, 0);

    protected DoubleStreetLampBlock(Properties materialIn) {
        super(materialIn, GenerationsBlockEntities.GENERIC_MODEL_PROVIDING, GenerationsBlockEntityModels.DOUBLE_STREET_LAMP, 2, 4, 0);
    }

    @Override
    public int getBaseX() {
        return 1;
    }

    @Override
    protected boolean validPosition(int x, int y, int z) {
        return switch (y) {
            case 0, 1, 2 -> x != 0 && x != 2;
            default -> true;
        };
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE.getShape(state);
    }
}
