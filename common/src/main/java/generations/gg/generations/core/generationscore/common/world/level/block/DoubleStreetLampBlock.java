package generations.gg.generations.core.generationscore.common.world.level.block;

import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.GenericModelProvidingBlockEntity;
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.GenericModelProvidingBlockEntity;
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.GenericModelProvidingBlockEntity;
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DoubleStreetLampBlock extends GenericRotatableModelBlock<GenericModelProvidingBlockEntity> {
    private static final GenerationsVoxelShapes.GenericRotatableShapes SHAPE = GenerationsVoxelShapes.generateRotationalVoxelShape(Shapes.or(
                    Shapes.box(0, 0, 0, 1, 0.175, 1),
                    Shapes.box(0.1875, 0, 0.1875, 0.8125, 1.14375, 0.8125),
                    Shapes.box(0.1875, 0, 0.1875, 0.8125, 1.25, 0.8125),
                    Shapes.box(0.421875, 0, 0.421875, 0.578125, 3.875, 0.578125),
                    Shapes.box(0.234375, 3.625, 0.234375, 0.765625, 4, 0.765625),
                    Shapes.box(-0.515625, 3.8125, 0.421875, 1.515625, 4.3125, 0.578125),
                    Shapes.box(1.046875, 3.0625, 0.234375, 1.578125, 3.875, 0.765625),
                    Shapes.box(0.859375, 3.1875, 0.046875, 1.765625, 3.8125, 0.953125),
                    Shapes.box(-0.765625, 3.1875, 0.046875, 0.140625, 3.8125, 0.953125),
                    Shapes.box(-0.578125, 3.0625, 0.234375, -0.046875, 3.875, 0.765625)),
            Direction.SOUTH, 3, 5, 1, 1, 0);

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
