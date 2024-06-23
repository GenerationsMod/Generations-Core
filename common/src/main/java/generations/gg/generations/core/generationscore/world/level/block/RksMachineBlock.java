package generations.gg.generations.core.generationscore.world.level.block;

import dev.architectury.registry.menu.MenuRegistry;
import generations.gg.generations.core.generationscore.world.container.RksMachineContainer;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.world.level.block.entities.RksMachineBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericRotatableModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RksMachineBlock extends GenericRotatableModelBlock<RksMachineBlockEntity> {
    private static final GenerationsVoxelShapes.GenericRotatableShapes SHAPE = GenerationsVoxelShapes.generateRotationalVoxelShape(
            Shapes.or(Shapes.box(0.5, 0, -0.25625, 1.371875, 0.04375, 1.25625),
                Shapes.box(-0.37187499999999996, 0, -0.25625, 0.5, 0.04375, 1.25625),
                Shapes.box(-0.26875000000000004, 0, -0.16874999999999996, 0.5812499999999999, 0.65625, 1.16875),
                Shapes.box(0.41875000000000007, 0, -0.16874999999999996, 1.26875, 0.65625, 1.16875),
                Shapes.box(-0.49375, 0, 0.8125, -0.24375000000000002, 0.4125, 1.125),
                Shapes.box(1.24375, 0, -0.125, 1.49375, 0.4125, 0.1875),
                Shapes.box(-0.49375, 0, -0.125, -0.24375000000000002, 0.4125, 0.1875),
                Shapes.box(1.24375, 0, 0.8125, 1.49375, 0.4125, 1.125),
                Shapes.box(-0.17500000000000004, 0.625, -0.07499999999999996, 0.5125, 0.771875, 1.075),
                Shapes.box(0.48750000000000004, 0.625, -0.07499999999999996, 1.175, 0.771875, 1.075),
                Shapes.box(0.4375, 0.6875, 0.025000000000000022, 1.0375, 1.4375, 0.975),
                Shapes.box(-0.03749999999999998, 0.6875, 0.025000000000000022, 0.5625, 1.4375, 0.975),
                Shapes.box(-0.11250000000000004, 1.415625, -0.03125, 0.5, 1.60625, 1.03125),
                Shapes.box(0.5, 1.415625, -0.03125, 1.1125, 1.60625, 1.03125),
                Shapes.box(0.5, 1.5625, 0.16562500000000002, 0.88125, 1.6875, 0.834375),
                Shapes.box(0.11875000000000002, 1.5625, 0.16562500000000002, 0.5, 1.6875, 0.834375),
                Shapes.box(0.2, 1.6875, 0.2375, 0.5125, 1.765625, 0.7625),
                Shapes.box(0.4875, 1.6875, 0.2375, 0.8, 1.765625, 0.7625)), Direction.SOUTH, 2, 2, 2, 0.5, -0.5);

    public RksMachineBlock(BlockBehaviour.Properties copy) {
        super(copy, GenerationsBlockEntities.RKS_MACHINE, GenerationsBlockEntityModels.RKS_MACHINE, 1, 1, 1);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE.getShape(state);
    }

    @Override
    public void onRemove(BlockState oldState, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!oldState.is(newState.getBlock())) {
            BlockEntity tileEntity = worldIn.getBlockEntity(pos);
            if (tileEntity instanceof RksMachineBlockEntity machine) {
                final NonNullList<ItemStack> inventory = machine.inventory;
                Containers.dropContents(worldIn, pos, inventory);

                worldIn.updateNeighbourForOutputSignal(pos, this);
            }
        }
        super.onRemove(oldState, worldIn, pos, newState, isMoving);
    }

    @Nullable
    public BlockEntityTicker<RksMachineBlockEntity> getTicker(@NotNull Level level, @NotNull BlockState blockState, @NotNull BlockEntityType entityType) {
        return createRksMachineTicker(level, entityType, GenerationsBlockEntities.RKS_MACHINE.get());
    }

    protected void openContainer(Level level, BlockPos bpos, Player player) {
        var rksMachine = getAssoicatedBlockEntity(level, bpos).orElseThrow(() -> new IllegalStateException("Our named container provider is missing!"));
        if (rksMachine != null) {
            MenuRegistry.openExtendedMenu((ServerPlayer) player, rksMachine);
            player.awardStat(Stats.INTERACT_WITH_FURNACE);
        }
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level level, @NotNull BlockPos blockPos, @NotNull Player player, @NotNull InteractionHand interactionHand, @NotNull BlockHitResult blockHitResult) {
        if (!level.isClientSide()) {
            this.openContainer(level, blockPos, player);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean hasAnalogOutputSignal(@NotNull BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(@NotNull BlockState blockState, Level level, @NotNull BlockPos blockPos) {
        return RksMachineContainer.getRedstoneSignalFromBlockEntity(level.getBlockEntity(blockPos));
    }

    @Nullable
    protected static BlockEntityTicker<RksMachineBlockEntity> createRksMachineTicker(Level level, BlockEntityType<?> entityType, BlockEntityType<RksMachineBlockEntity> entityTypeE) {
        return level.isClientSide() ? null : (BlockEntityTicker<RksMachineBlockEntity>) createTickerHelper(entityType, entityTypeE, RksMachineBlockEntity::serverTick);
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState blockState) {
        if(getWidthValue(blockState) == 0 && getHeightValue(blockState) == 0 && getLengthValue(blockState) == 0) return RenderShape.ENTITYBLOCK_ANIMATED;
        else return RenderShape.INVISIBLE;
    }
}
