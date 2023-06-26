package generations.gg.generations.core.generationscore.world.level.block.decorations;

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
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class LitwickCandleBlock extends GenericRotatableModelBlock<GenericModelProvidingBlockEntity> {
    public static final GenerationsVoxelShapes.DirectionalShapes SHAPES = GenerationsVoxelShapes.generateDirectionVoxelShape(Shapes.join(Shapes.box(0.3125, 0, 0.3125, 0.6875, 0.3125, 0.6875), Shapes.box(0.4375, 0.34375, 0.4375, 0.5625, 0.5, 0.5625), BooleanOp.OR), Direction.NORTH);

    public LitwickCandleBlock(BlockBehaviour.Properties properties) {
        super(properties, GenerationsBlockEntities.GENERIC_MODEL_PROVIDING, GenerationsBlockEntityModels.LITWICK_CANDLE);
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES.getShape(state);
    }
}
