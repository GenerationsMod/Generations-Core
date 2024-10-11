package generations.gg.generations.core.generationscore.common.world.level.block.deco;

import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.GenericModelProvidingBlockEntity;
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class ShelfBlock extends GenericRotatableModelBlock<GenericModelProvidingBlockEntity> {
        private final static GenerationsVoxelShapes.GenericRotatableShapes SHAPES = GenerationsVoxelShapes.generateRotationalVoxelShape(
                Shapes.or(
                        Shapes.box(1.1875, 0, 0.07500000000000001, 1.31875, 0.8125, 0.8999999999999999),
                        Shapes.box(-0.31875, 0, 0.07500000000000001, 1.31875, 0.103125, 0.8999999999999999),
                        Shapes.box(-0.3187500000000001, 0, 0.07500000000000001, 1.3187499999999999, 0.8125, 0.84375),
                        Shapes.box(-0.31875, 0.41875, 0.07500000000000001, 1.31875, 0.528125, 0.871875),
                        Shapes.box(-0.375, 0.75625, 0.04999999999999999, 1.375, 0.875, 0.9437500000000001),
                        Shapes.box(-0.3187500000000001, 0, 0.07500000000000001, -0.1875, 0.8125, 0.8999999999999999)).move(0.5, 0, 0),
                Direction.SOUTH, 2, 1, 1);

    public ShelfBlock(BlockBehaviour.Properties props) {
        super(props, GenerationsBlockEntities.GENERIC_MODEL_PROVIDING, GenerationsBlockEntityModels.SHELF, 1, 0, 0);
        }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPES.getShape(state);
    }
}