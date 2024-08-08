package generations.gg.generations.core.generationscore.common.world.level.block.shrines;

import com.cobblemon.mod.common.api.spawning.TimeRange;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.altar.CelestialAltarBlockEntity;
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.altar.CelestialAltarBlockEntity;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.altar.CelestialAltarBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ConduitBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CelestialAltarBlock extends GenericRotatableModelBlock<CelestialAltarBlockEntity> {
    public static final BooleanProperty IS_SUN = BooleanProperty.create("is_sun");

    public static final GenerationsVoxelShapes.GenericRotatableShapes SHAPE = GenerationsVoxelShapes.generateRotationalVoxelShape(Shapes.or(
                Shapes.box(0.03749999999999998, 0, 0.03749999999999998, 0.9625, 0.2875, 0.9625),
                Shapes.box(0.38125, 0.2875, 0.38125, 0.61875, 0.396875, 0.61875),
                Shapes.box(0.3375, 0.48125, 0.4875, 0.6625, 0.8125, 0.50625),
                Shapes.box(0.44375, 0.2875, 0.19374999999999998, 0.5812499999999999, 0.871875, 0.36875),
                Shapes.box(0.60625, 0.2875, 0.51875, 0.775, 0.684375, 0.6875),
                Shapes.box(0.19374999999999998, 0.2875, 0.525, 0.35624999999999996, 1.0375, 0.68125)
            ), Direction.SOUTH, 1, 1, 1, 0, 0);

    public CelestialAltarBlock(BlockBehaviour.Properties properties) {
        super(properties, GenerationsBlockEntities.CELESTIAL_ALTAR, GenerationsBlockEntityModels.CELESTIAL_ALTAR);
    }

    @Override
    protected void createBlockStateDefinition(@NotNull StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(IS_SUN);
    }

    @Override
    protected BlockState createDefaultState() {
        return super.createDefaultState().setValue(IS_SUN, true);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> blockEntityType) {
        return ConduitBlock.createTickerHelper(blockEntityType, GenerationsBlockEntities.CELESTIAL_ALTAR.get(), level.isClientSide ? (level1, blockPos, blockState, blockEntity) -> {} : (level12, pos, blockState, blockEntity) -> {
            var state1 = level12.getBlockState(pos).setValue(IS_SUN, TimeRange.Companion.getTimeRanges().get("day").contains((int) level12.dayTime()));
            level12.setBlockAndUpdate(pos, state1);
        });
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        if(level.isClientSide) return InteractionResult.PASS;

        player.sendSystemMessage(Component.translatable("generations_core.blocks.celestial_altar.hint"));

        return InteractionResult.SUCCESS;
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE.getShape(state);
    }
}
