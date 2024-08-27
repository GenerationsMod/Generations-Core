package generations.gg.generations.core.generationscore.common.world.level.block.shrines;

import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.GenericShrineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import static net.minecraft.world.phys.shapes.BooleanOp.OR;

@SuppressWarnings("deprecation")
public class TapuShrineBlock extends ShrineBlock<GenericShrineBlockEntity> {
    private static final GenerationsVoxelShapes.DirectionalShapes SHAPE = GenerationsVoxelShapes.generateDirectionVoxelShape(
            Shapes.join(Shapes.box(0.1875, 0, 0.1875, 0.8125, 0.109375, 0.8125),
                Shapes.box(0.21875, 0, 0.21875, 0.78125, 1, 0.78125), OR));

    public TapuShrineBlock(BlockBehaviour.Properties properties) {
        super(properties, GenerationsBlockEntities.GENERIC_SHRINE, GenerationsBlockEntityModels.TAPU_SHRINE);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE.getShape(state);
    }
}
