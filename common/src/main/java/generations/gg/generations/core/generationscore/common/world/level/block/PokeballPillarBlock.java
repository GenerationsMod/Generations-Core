package generations.gg.generations.core.generationscore.common.world.level.block;

import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.GenericModelProvidingBlockEntity;
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class PokeballPillarBlock extends GenericRotatableModelBlock<GenericModelProvidingBlockEntity> {
    private static VoxelShape UPPER = Shapes.box(0.125, 0, 0.125, 0.875, 0.8125, 0.875);
    private static VoxelShape LOWER = Shapes.join(Shapes.box(0, 0, 0, 1, 0.1875, 1),
            Shapes.box(0.125, 0.1875, 0.125, 0.875, 1, 0.875), BooleanOp.OR);
    public PokeballPillarBlock(BlockBehaviour.Properties properties) {
        super(properties, GenerationsBlockEntities.GENERIC_MODEL_PROVIDING, GenerationsBlockEntityModels.POKEBALL_PILLAR, 0, 1, 0);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return getHeightValue(state) == 0 ? LOWER : UPPER;
    }
}
