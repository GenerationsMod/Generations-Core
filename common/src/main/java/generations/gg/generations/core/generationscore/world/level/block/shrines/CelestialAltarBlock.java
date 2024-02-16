package generations.gg.generations.core.generationscore.world.level.block.shrines;

import generations.gg.generations.core.generationscore.world.level.block.entities.shrines.altar.CelestialAltarBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericRotatableModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ConduitBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CelestialAltarBlock extends GenericRotatableModelBlock<CelestialAltarBlockEntity> {
    public static final BooleanProperty IS_SUN = BooleanProperty.create("is_sun");



    public CelestialAltarBlock(BlockBehaviour.Properties properties) {
        super(properties, GenerationsBlockEntities.CELESTIAL_ALTAR, GenerationsBlockEntityModels.CELESTIAL_ALTAR);
    }

    @Override
    protected void createBlockStateDefinition(@NotNull StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(IS_SUN);
    }

    @Override
    protected BlockState createDefaultState() {
        return super.createDefaultState().setValue(IS_SUN, true);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> blockEntityType) {
        return ConduitBlock.createTickerHelper(blockEntityType, GenerationsBlockEntities.CELESTIAL_ALTAR.get(), level.isClientSide ? (level1, blockPos, blockState, blockEntity) -> {} : (level12, pos, blockState, blockEntity) -> {
            var state1 = level12.getBlockState(pos).setValue(IS_SUN, level12.isDay());
            level12.setBlockAndUpdate(pos, state1);
        });
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        if(level.isClientSide) return InteractionResult.PASS;

        player.sendSystemMessage(Component.translatable("generations_core.blocks.celestial_altar.hint"));

        return InteractionResult.SUCCESS;
    }
}
