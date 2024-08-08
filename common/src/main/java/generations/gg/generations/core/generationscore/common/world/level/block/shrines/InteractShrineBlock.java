package generations.gg.generations.core.generationscore.common.world.level.block.shrines;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.MutableBlockEntityType;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.ShrineBlockEntity;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.MutableBlockEntityType;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.ShrineBlockEntity;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.MutableBlockEntityType;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.ShrineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("deprecation")
public abstract class InteractShrineBlock<T extends ShrineBlockEntity> extends ShrineBlock<T> {

    protected InteractShrineBlock(Properties materialIn, RegistrySupplier<MutableBlockEntityType<T>> blockEntityFunction, ResourceLocation model) {
        super(materialIn, blockEntityFunction, model);
    }

    protected InteractShrineBlock(Properties materialIn, RegistrySupplier<MutableBlockEntityType<T>> blockEntityFunction, ResourceLocation model, int width, int height, int length) {
        super(materialIn, blockEntityFunction, model, width, height, length);
    }


    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        if (level.isClientSide() || hand == InteractionHand.OFF_HAND) return InteractionResult.PASS;

        var activeState = isActive(state);

        if ((activeState != ActivationState.ON) && isStackValid(player.getItemInHand(hand)) && activate(level, pos, state, (ServerPlayer) player, hand, activeState)) {
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    protected abstract boolean activate(Level level, BlockPos pos, BlockState state, ServerPlayer player, InteractionHand hand, ActivationState activationState);

    public abstract boolean isStackValid(ItemStack stack);
}
