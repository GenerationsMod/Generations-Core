package generations.gg.generations.core.generationscore.world.level.block.utilityblocks;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.world.level.block.entities.DyedVariantBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.MutableBlockEntityType;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericRotatableModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;

@SuppressWarnings("deprecation")
public abstract class DyeableBlock<T extends DyedVariantBlockEntity<?>, V extends DyeableBlock<T, V>> extends GenericRotatableModelBlock<T> {
    private final DyeColor color;
    private final Map<DyeColor, RegistrySupplier<DyeableBlock<T, V>>> function;

    public DyeableBlock(DyeColor color, Map<DyeColor, RegistrySupplier<DyeableBlock<T, V>>> function, RegistrySupplier<MutableBlockEntityType<T>> biFunction, BiFunction<BlockPos, BlockState, BlockPos> baseBlockPosFunction, Properties arg, ResourceLocation model, int width, int height, int length) {
        super(arg, biFunction, baseBlockPosFunction, model, width, height, length);
        this.color = color;
        this.function = function;
    }

    public DyeableBlock(DyeColor color, Map<DyeColor, RegistrySupplier<DyeableBlock<T, V>>> function, RegistrySupplier<MutableBlockEntityType<T>> biFunction, BiFunction<BlockPos, BlockState, BlockPos> baseBlockPosFunction, Properties arg, ResourceLocation model) {
        super(arg, biFunction, baseBlockPosFunction, model);
        this.color = color;
        this.function = function;
    }

    public DyeableBlock(DyeColor color, Map<DyeColor, RegistrySupplier<DyeableBlock<T, V>>> function, RegistrySupplier<MutableBlockEntityType<T>> biFunction, Properties arg, ResourceLocation model, int width, int height, int length) {
        super(arg, biFunction, model, width, height, length);
        this.color = color;
        this.function = function;
    }

    public DyeableBlock(DyeColor color, Map<DyeColor, RegistrySupplier<DyeableBlock<T, V>>> function, RegistrySupplier<MutableBlockEntityType<T>> biFunction, Properties arg, ResourceLocation model) {
        super(arg, biFunction, model);
        this.color = color;
        this.function = function;
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level world, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand handIn, @NotNull BlockHitResult hit) {
        if (!world.isClientSide()) {
            if (!tryDyeColor(state, world, pos, player, handIn, hit))
                return serverUse(state, world, pos, player, handIn, hit);
            else return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    public Item getItemFromDyeColor(DyeColor color) {
        return getBlockFromDyeColor(color).asItem();
    }

    public DyeableBlock<T, V> getBlockFromDyeColor(DyeColor color) {
        return function.get(color).get();
    }

    public boolean tryDyeColor(@NotNull BlockState state, Level world, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand handIn, @NotNull BlockHitResult hit) {
        ItemStack heldItem = player.getMainHandItem();

        boolean isEmpty = !heldItem.isEmpty();
        boolean isDye = heldItem.getItem() instanceof DyeItem;

        if (isEmpty && isDye) {
            var base = getBaseBlockPos(pos, state);

            DyeColor dyeColor = ((DyeItem) heldItem.getItem()).getDyeColor();

            var baseState = world.getBlockState(base);

            if(this.getClass().isInstance(baseState.getBlock())) {
                var baseBlock = this.getClass().cast(baseState.getBlock());


                if (!baseBlock.color.equals(dyeColor)) {
                    if (!player.isCreative()) heldItem.shrink(1);

                    var newBlock = getBlockFromDyeColor(dyeColor);

                    var defaultState = newBlock.defaultBlockState().setValue(FACING, baseState.getValue(FACING));

//                    if (newBlock.getClass().equals(state.getBlock().getClass())) {
                        var dir = state.getValue(FACING);

                        for (int x = 0; x < width; x++) {
                            for (int y = 0; y < height; y++) {
                                for (int z = 0; z < length; z++) {
                                    var adjustX = adjustX(x);
                                    var adjustZ = adjustX(x);

                                    var blockPos = base.relative(dir.getCounterClockWise(), adjustZ).relative(Direction.UP, y).relative(dir, adjustZ);

                                    var currentState = world.getBlockState(blockPos);

                                    var stateX = baseBlock.getWidthValue(currentState);
                                    var stateY = baseBlock.getHeightValue(currentState);
                                    var stateZ = baseBlock.getLengthValue(currentState);

                                    world.setBlock(blockPos, newBlock.setSize(defaultState.setValue(WATERLOGGED, currentState.getValue(WATERLOGGED)), x, y, z), 2, 0);
                                }
                            }
                        }

                        return true;
//                    }
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

    public DyeColor getColor() {
        return color;
    }

    @Override
    public @NotNull List<ItemStack> getDrops(@NotNull BlockState state, @NotNull LootParams.Builder params) {
        return getWidthValue(state) == 0 || getHeightValue(state) == 0 || getLengthValue(state) == 0 ? super.getDrops(state, params) : Collections.emptyList();
    }
}
