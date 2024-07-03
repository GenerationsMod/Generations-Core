package generations.gg.generations.core.generationscore.world.level.block.utilityblocks;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.world.level.block.entities.ModelProvidingBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.MutableBlockEntityType;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericRotatableModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
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
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

@SuppressWarnings("deprecation")
public abstract class DyeableBlock<T extends ModelProvidingBlockEntity, V extends DyeableBlock<T, V>> extends GenericRotatableModelBlock<T> {
    private final DyeColor color;
    private final Map<DyeColor, RegistrySupplier<V>> function;

    public DyeableBlock(DyeColor color, Map<DyeColor, RegistrySupplier<V>> function, RegistrySupplier<MutableBlockEntityType<T>> biFunction, BiFunction<BlockPos, BlockState, BlockPos> baseBlockPosFunction, Properties arg, ResourceLocation model, int width, int height, int length) {
        super(arg, biFunction, baseBlockPosFunction, model, width, height, length);
        this.color = color;
        this.function = function;
    }

    public DyeableBlock(DyeColor color, Map<DyeColor, RegistrySupplier<V>> function, RegistrySupplier<MutableBlockEntityType<T>> biFunction, BiFunction<BlockPos, BlockState, BlockPos> baseBlockPosFunction, Properties arg, ResourceLocation model) {
        super(arg, biFunction, baseBlockPosFunction, model);
        this.color = color;
        this.function = function;
    }

    public DyeableBlock(DyeColor color, Map<DyeColor, RegistrySupplier<V>> function, RegistrySupplier<MutableBlockEntityType<T>> biFunction, Properties arg, ResourceLocation model, int width, int height, int length) {
        super(arg, biFunction, model, width, height, length);
        this.color = color;
        this.function = function;
    }

    public DyeableBlock(DyeColor color, Map<DyeColor, RegistrySupplier<V>> function, RegistrySupplier<MutableBlockEntityType<T>> biFunction, Properties arg, ResourceLocation model) {
        super(arg, biFunction, model);
        this.color = color;
        this.function = function;
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level world, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand handIn, @NotNull BlockHitResult hit) {
        if (!world.isClientSide() && handIn == InteractionHand.MAIN_HAND) {
            if (!tryDyeColor(state, world, pos, player, handIn, hit)) {

                return serverUse(state, (ServerLevel) world, pos, (ServerPlayer) player, handIn, hit);
            }
            else return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    public Item getItemFromDyeColor(DyeColor color) {
        return getBlockFromDyeColor(color).asItem();
    }

    public V getBlockFromDyeColor(DyeColor color) {
        return function.get(color).get();
    }

    public boolean tryDyeColor(@NotNull BlockState state, Level world, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand handIn, @NotNull BlockHitResult hit) {
        ItemStack heldItem = player.getMainHandItem();

        boolean isEmpty = !heldItem.isEmpty();
        boolean isDye = heldItem.getItem() instanceof DyeItem;

        if (isEmpty && isDye) {

            DyeColor dyeColor = ((DyeItem) heldItem.getItem()).getDyeColor();

            var baseState = world.getBlockState(pos);

            if(this.getClass().isInstance(baseState.getBlock())) {
                var baseBlock = this.getClass().cast(baseState.getBlock());


                if (!baseBlock.color.equals(dyeColor)) {

                    var base = getBaseBlockPos(pos, state);

                    if (!player.isCreative()) heldItem.shrink(1);

                    var newBlock = getBlockFromDyeColor(dyeColor);

                    var defaultState = newBlock.defaultBlockState().setValue(FACING, baseState.getValue(FACING));

                        var dir = state.getValue(FACING);

                        for (int x = 0; x < width + 1; x++) {
                            for (int y = 0; y < height + 1; y++) {
                                for (int z = 0; z < length + 1; z++) {
                                    var adjustX = adjustX(x);
                                    var adjustZ = adjustX(x);

                                    var blockPos = base.relative(dir.getCounterClockWise(), adjustX).relative(Direction.UP, y).relative(dir, adjustZ);

                                    var currentState = world.getBlockState(blockPos);

                                    var stateX = baseBlock.getWidthValue(currentState);
                                    var stateY = baseBlock.getHeightValue(currentState);
                                    var stateZ = baseBlock.getLengthValue(currentState);

                                    world.setBlock(blockPos, newBlock.setSize(defaultState.setValue(WATERLOGGED, currentState.getValue(WATERLOGGED)), stateX, stateY, stateZ), 2, 0);
                                }
                            }
                        }
                        return true;
                }
            }
        }

        return false;
    }

    @Override
    public @NotNull ItemStack getCloneItemStack(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState) {
        return new ItemStack(getItemFromDyeColor(getColor()));
    }

    protected InteractionResult serverUse(BlockState state, ServerLevel world, BlockPos pos, ServerPlayer player, InteractionHand handIn, BlockHitResult hit) {
        return InteractionResult.PASS;
    }

    public DyeColor getColor() {
        return color;
    }

    @Override
    public @NotNull List<ItemStack> getDrops(@NotNull BlockState state, @NotNull LootParams.Builder params) {
        return getWidthValue(state) == 0 || getHeightValue(state) == 0 || getLengthValue(state) == 0 ? super.getDrops(state, params) : Collections.emptyList();
    }
}
