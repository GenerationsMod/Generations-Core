package generations.gg.generations.core.generationscore.world.level.block.utilityblocks;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.world.item.DyedBlockItem;
import generations.gg.generations.core.generationscore.world.level.block.entities.DyedVariantBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.MutableBlockEntityType;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericRotatableModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;
import java.util.function.Function;

@SuppressWarnings("deprecation")
public abstract class DyeableBlock<T extends DyedVariantBlockEntity<?>, V extends DyeableBlock<T, V>> extends GenericRotatableModelBlock<T> {
    private final Function<DyeColor, DyedBlockItem<T, V>> function;

    public DyeableBlock(Function<DyeColor, DyedBlockItem<T, V>> function, RegistrySupplier<MutableBlockEntityType<T>> biFunction, BiFunction<BlockPos, BlockState, BlockPos> baseBlockPosFunction, Properties arg, ResourceLocation model, int width, int height, int length) {
        super(arg, biFunction, baseBlockPosFunction, model, width, height, length);
        this.function = function;
    }

    public DyeableBlock(Function<DyeColor, DyedBlockItem<T, V>> function, RegistrySupplier<MutableBlockEntityType<T>> biFunction, BiFunction<BlockPos, BlockState, BlockPos> baseBlockPosFunction, Properties arg, ResourceLocation model) {
        super(arg, biFunction, baseBlockPosFunction, model);
        this.function = function;
    }

    public DyeableBlock(Function<DyeColor, DyedBlockItem<T, V>> function, RegistrySupplier<MutableBlockEntityType<T>> biFunction, Properties arg, ResourceLocation model, int width, int height, int length) {
        super(arg, biFunction, model, width, height, length);
        this.function = function;
    }

    public DyeableBlock(Function<DyeColor, DyedBlockItem<T, V>> function, RegistrySupplier<MutableBlockEntityType<T>> biFunction, Properties arg, ResourceLocation model) {
        super(arg, biFunction, model);
        this.function = function;
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level world, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand handIn, @NotNull BlockHitResult hit) {
        if (!world.isClientSide()) {
            if (!tryDyeColor(state, world, pos, player, handIn, hit))
                return serverUse(state, world, pos, player, handIn, hit);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    public Item getItemFromDyeColor(DyeColor color) {
        return function.apply(color);
    }

    public boolean tryDyeColor(@NotNull BlockState state, Level world, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand handIn, @NotNull BlockHitResult hit) {
        ItemStack heldItem = player.getMainHandItem();

        boolean isEmpty = !heldItem.isEmpty();
        boolean isDye = heldItem.getItem() instanceof DyeItem;

        if (isEmpty && isDye) {
            var blockEntity = getAssoicatedBlockEntity(world, pos);

            DyeColor dyeColor = ((DyeItem) heldItem.getItem()).getDyeColor();

            if (blockEntity.isPresent()) {
                var color = blockEntity.get().getColor();

                if (!color.equals(dyeColor)) {
                    if (!player.isCreative()) heldItem.shrink(1);

                    blockEntity.get().setColor(dyeColor);
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public @NotNull ItemStack getCloneItemStack(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState) {
        return new ItemStack(getItemFromDyeColor(getAssoicatedBlockEntity(blockGetter, blockPos).map(DyedVariantBlockEntity::getColor).orElse(DyeColor.RED)));
    }

    protected InteractionResult serverUse(BlockState state, Level world, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        return InteractionResult.PASS;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        var basePos = getBaseBlockPos(pos, state);
        if(!basePos.equals(pos)) {
            level.destroyBlock(basePos, true);
        }

        super.onRemove(state, level, pos, newState, isMoving);
    }
}
