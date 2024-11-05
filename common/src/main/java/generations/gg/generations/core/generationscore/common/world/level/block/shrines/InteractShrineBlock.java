package generations.gg.generations.core.generationscore.common.world.level.block.shrines;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.MutableBlockEntityType;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.InteractShrineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class InteractShrineBlock<T extends InteractShrineBlockEntity> extends ShrineBlock<T> {

    private static final BooleanProperty ACTIVE = BooleanProperty.create("active");


    protected InteractShrineBlock(Properties materialIn, RegistrySupplier<MutableBlockEntityType<T>> blockEntityFunction, ResourceLocation model) {
        super(materialIn, blockEntityFunction, model);
    }

    protected InteractShrineBlock(Properties materialIn, RegistrySupplier<MutableBlockEntityType<T>> blockEntityFunction, ResourceLocation model, int width, int height, int length) {
        super(materialIn, blockEntityFunction, model, width, height, length);
    }

    public static boolean isActive(BlockState state) {
        return state.getBlock() instanceof InteractShrineBlock<?> && state.getValue(ACTIVE);
    }

    public static void toggleActive(Level level, BlockPos pos) {
        var state = level.getBlockState(pos);

        if(state.getBlock() instanceof InteractShrineBlock<?> shrine && shrine.isActivatable()) {
            var isActive = isActive(state);

            isActive = !isActive;


            pos = shrine.getBaseBlockPos(pos, state);
            var facing = state.getValue(FACING);

            for (int x = 0; x <= shrine.width; x++) {
                for (int z = 0; z <= shrine.length; z++) {
                    for (int y = 0; y <= shrine.height; y++) {
                        if (!shrine.validPosition(x, y, z)) continue;


                        var blockPos = shrine.adjustBlockPos(pos, facing, x, y, z, true);

                        var stateCurrent = level.getBlockState(blockPos);

                        level.setBlockAndUpdate(blockPos, stateCurrent.setValue(ACTIVE, isActive));
                    }
                }
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(@NotNull StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(ACTIVE);
    }

    @Override
    protected BlockState createDefaultState() {
        return super.createDefaultState().setValue(ACTIVE, false);
    }

    @Override
    public BlockState getStateForPlacement(@NotNull BlockPlaceContext context) {
        return super.getStateForPlacement(context).setValue(ACTIVE, false);
    }

    public String getVariant(BlockState blockState) {
        return isActive(blockState) ? "activated" : "deactivated";
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        if (level.isClientSide() || hand == InteractionHand.OFF_HAND) return InteractionResult.PASS;

        var activeState = isActive(state);

        if ((!activeState) && isStackValid(player.getItemInHand(hand)) && interact(level, pos, state, (ServerPlayer) player, hand, activeState)) {
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return level.isClientSide || waitToDeactivateTime() == 0 ? null : InteractShrineBlock.createTickerHelper(blockEntityType, getBlockEntityType(), InteractShrineBlockEntity::serverTick);
    }

    protected abstract boolean interact(Level level, BlockPos pos, BlockState state, ServerPlayer player, InteractionHand hand, boolean activationState);

    protected void activate(Level level, BlockPos pos) {
        if(waitToDeactivateTime() == 0) getAssoicatedBlockEntity(level, pos).ifPresent(InteractShrineBlockEntity::triggerCountDown);
    }

    public void deactivate(Level level, BlockPos pos, BlockState state) {
        if(revertsAfterActivation()) toggleActive(level, pos);
        postDeactivation(level, pos, state);
    }

    public abstract boolean isStackValid(ItemStack stack);

    public int waitToDeactivateTime() {
        return 0;
    }

    public boolean revertsAfterActivation() {
        return true;
    }

    public void postDeactivation(Level level, BlockPos pos, BlockState state) {

    }
}
