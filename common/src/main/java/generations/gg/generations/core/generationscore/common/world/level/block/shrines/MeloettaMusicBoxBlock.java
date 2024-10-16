package generations.gg.generations.core.generationscore.common.world.level.block.shrines;

import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.MeloettaMusicBoxBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MeloettaMusicBoxBlock extends GenericRotatableModelBlock<MeloettaMusicBoxBlockEntity> {
    private static final VoxelShape SHAPE = Shapes.box(0.25f, 0f, 0.25f, 0.75f, 0.375f, 0.75f);
    public static final BooleanProperty HAS_RECORD = BlockStateProperties.HAS_RECORD;

    public MeloettaMusicBoxBlock(BlockBehaviour.Properties properties) {
        super(properties, GenerationsBlockEntities.MELOETTA_MUSIC_BOX, GenerationsBlockEntityModels.MELOETTA_MUSIC_BOX);
    }

    @Override
    protected BlockState createDefaultState() {
        return super.createDefaultState().setValue(HAS_RECORD, false);
    }



    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE;
    }

    public void setPlacedBy(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state, @Nullable LivingEntity placer, @NotNull ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        CompoundTag compoundTag = BlockItem.getBlockEntityData(stack);
        if (compoundTag != null && compoundTag.contains("RecordItem")) {
            level.setBlock(pos, state.setValue(HAS_RECORD, true), 2);
        }

    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (state.getValue(HAS_RECORD)) {
            BlockEntity var8 = level.getBlockEntity(pos);
            if (var8 instanceof MeloettaMusicBoxBlockEntity meloettaMusicBoxBlockEntity) {
                meloettaMusicBoxBlockEntity.popOutRecord();
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        }

        return InteractionResult.PASS;
    }

    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (!state.is(newState.getBlock())) {
            BlockEntity var7 = level.getBlockEntity(pos);
            if (var7 instanceof MeloettaMusicBoxBlockEntity meloettaMusicBoxBlockEntity) {
                meloettaMusicBoxBlockEntity.popOutRecord();
            }

            super.onRemove(state, level, pos, newState, movedByPiston);
        }
    }

    public boolean isSignalSource(BlockState state) {
        return true;
    }

    public int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        BlockEntity var6 = level.getBlockEntity(pos);
        if (var6 instanceof MeloettaMusicBoxBlockEntity jukeboxBlockEntity) {
            if (jukeboxBlockEntity.isRecordPlaying()) {
                return 15;
            }
        }

        return 0;
    }

    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        BlockEntity entity = level.getBlockEntity(pos);
        if (entity instanceof MeloettaMusicBoxBlockEntity jukeboxBlockEntity) {
            Item item = jukeboxBlockEntity.getFirstItem().getItem();
            if (item instanceof RecordItem recordItem) {
                return recordItem.getAnalogOutput();
            }
        }

        return 0;
    }

    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(HAS_RECORD);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, @NotNull BlockEntityType<T> blockEntityType) {
        return state.getValue(HAS_RECORD) ? createTickerHelper(blockEntityType, GenerationsBlockEntities.MELOETTA_MUSIC_BOX.get(), MeloettaMusicBoxBlockEntity::playRecordTick) : null;
    }
}
