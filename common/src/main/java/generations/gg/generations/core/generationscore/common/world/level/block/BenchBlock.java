package generations.gg.generations.core.generationscore.common.world.level.block;

import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.GenericModelProvidingBlockEntity;
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.shapes.Shapes;

public class BenchBlock extends GenericRotatableModelBlock<GenericModelProvidingBlockEntity> {
    private static final GenerationsVoxelShapes.GenericRotatableShapes SHAPE = GenerationsVoxelShapes.generateRotationalVoxelShape(
            Shapes.or(
                    Shapes.box(0.11562499999999998, 0, 0.81875, 0.20937499999999998, 0.1875, 0.94375),
                    Shapes.box(1.790625, 0, 0.06874999999999998, 1.884375, 0.1875, 0.19374999999999998),
                    Shapes.box(0.11562499999999998, 0, 0.06874999999999998, 0.20937499999999998, 0.1875, 0.19374999999999998),
                    Shapes.box(1.790625, 0, 0.81875, 1.884375, 0.1875, 0.94375),
                    Shapes.box(0.06874999999999998, 0.1375, 0.006249999999999978, 1.93125, 0.403125, 0.9874999999999999),
                    Shapes.box(0.03749999999999998, 0.2625, 0.006249999999999978, 0.19999999999999996, 0.6312500000000001, 0.9874999999999999),
                    Shapes.box(0.03749999999999998, 0.29375, -0.006249999999999978, 1.9625, 0.98125, 0.3125),
                    Shapes.box(1.8, 0.2625, 0.006249999999999978, 1.9625, 0.6312500000000001, 0.9874999999999999)), Direction.SOUTH, 2, 1, 1);

    public BenchBlock(BlockBehaviour.Properties properties) {
        super(properties, GenerationsBlockEntities.GENERIC_MODEL_PROVIDING, GenerationsBlockEntityModels.BENCH, 1, 0, 0);
    }
}
