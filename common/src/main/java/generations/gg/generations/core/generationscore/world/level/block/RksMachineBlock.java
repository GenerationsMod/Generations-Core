package generations.gg.generations.core.generationscore.world.level.block;

import generations.gg.generations.core.generationscore.world.container.RksMachineContainer;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.world.level.block.entities.RksMachineBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.generic.GenericModelProvidingBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericRotatableModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.stats.Stats;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class RksMachineBlock extends GenericRotatableModelBlock<RksMachineBlockEntity> {
    public RksMachineBlock(BlockBehaviour.Properties copy) {
        super(copy, GenerationsBlockEntities.RKS_MACHINE, GenerationsBlockEntityModels.RKS_MACHINE, 1, 1, 1);

    }

//    @Override
//    protected void onRemove(Level level, BlockPos pos) {
//        getAssoicatedBlockEntity(level, pos).ifPresent(machine -> {
//            final NonNullList<ItemStack> inventory = machine.inventory;
//            Containers.dropContents(level, pos, inventory);
//
//            level.updateNeighbourForOutputSignal(pos, this);
//        });
//    }

    //    @Override
//    public void onRemove(BlockState oldState, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
//        if (!oldState.is(newState.getBlock())) {
//            BlockEntity tileEntity = worldIn.getBlockEntity(pos);
//            if (tileEntity instanceof RksMachineBlockEntity machine) {
//                final NonNullList<ItemStack> inventory = machine.inventory;
//                Containers.dropContents(worldIn, pos, inventory);
//
//                worldIn.updateNeighbourForOutputSignal(pos, this);
//            }
//        }
//        super.onRemove(oldState, worldIn, pos, newState, isMoving);
//    }

    @Nullable
    public BlockEntityTicker<RksMachineBlockEntity> getTicker(Level level, BlockState blockState, BlockEntityType entityType) {
        return createRksMachineTicker(level, entityType, GenerationsBlockEntities.RKS_MACHINE.get());
    }

    protected void openContainer(Level level, BlockPos bpos, Player player) {
        var rksMachine = getAssoicatedBlockEntity(level, bpos).orElseThrow(() -> new IllegalStateException("Our named container provider is missing!"));
        if (rksMachine != null) {
            player.openMenu(rksMachine);
            player.awardStat(Stats.INTERACT_WITH_FURNACE);
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (!level.isClientSide()) {
            this.openContainer(level, blockPos, player);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos blockPos) {
        return RksMachineContainer.getRedstoneSignalFromBlockEntity(level.getBlockEntity(blockPos));
    }

    @Nullable
    protected static BlockEntityTicker<RksMachineBlockEntity> createRksMachineTicker(Level level, BlockEntityType<?> entityType, BlockEntityType<RksMachineBlockEntity> entityTypeE) {
        return level.isClientSide() ? null : (BlockEntityTicker<RksMachineBlockEntity>) createTickerHelper(entityType, entityTypeE, RksMachineBlockEntity::serverTick);
    }

    @Override
    public RenderShape getRenderShape(BlockState blockState) {
        if(getWidthValue(blockState) == 0 && getHeightValue(blockState) == 0 && getLengthValue(blockState) == 0) return RenderShape.ENTITYBLOCK_ANIMATED;
        else return RenderShape.INVISIBLE;
    }
}
