package generations.gg.generations.core.generationscore.world.level.block;

import generations.gg.generations.core.generationscore.world.level.block.entities.MachineBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.PokeModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("deprecation")
public class MachineBlock extends BaseEntityBlock {
    public MachineBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new MachineBlockEntity(pos, state);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        if(!level.isClientSide() && level.getBlockEntity(pos) instanceof MachineBlockEntity te) {
//            NetworkHooks.openScreen((ServerPlayer) player, te, pos);

            return InteractionResult.SUCCESS;
        }

        return super.use(state, level, pos, player, hand, hit);
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> blockEntityType) {
        return level.isClientSide ? null : MachineBlock.createTickerHelper(blockEntityType, PokeModBlockEntities.MACHINE_BLOCK.get(), MachineBlockEntity::serverTick);
    }
}
