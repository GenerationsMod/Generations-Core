package generations.gg.generations.core.generationscore.world.level.block;

import dev.architectury.registry.menu.MenuRegistry;
import generations.gg.generations.core.generationscore.world.container.RksMachineContainer;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.world.level.block.entities.RksMachineBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericRotatableModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RksMachineBlock extends GenericRotatableModelBlock<RksMachineBlockEntity> {
    public RksMachineBlock(BlockBehaviour.Properties copy) {
        super(copy, GenerationsBlockEntities.RKS_MACHINE, GenerationsBlockEntityModels.RKS_MACHINE, 1, 1, 1);

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
