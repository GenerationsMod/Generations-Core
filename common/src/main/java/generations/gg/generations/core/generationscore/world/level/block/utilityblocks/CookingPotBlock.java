package generations.gg.generations.core.generationscore.world.level.block.utilityblocks;

import dev.architectury.registry.menu.MenuRegistry;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsVoxelShapes;
import generations.gg.generations.core.generationscore.world.level.block.entities.CookingPotBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericRotatableModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

@SuppressWarnings("deprecation")
public class CookingPotBlock extends GenericRotatableModelBlock<CookingPotBlockEntity> {
    private static final GenerationsVoxelShapes.DirectionalShapes WITH_CAMPFIRE = GenerationsVoxelShapes.generateDirectionVoxelShape( Stream.of(
            Shapes.box(0.125, 0, 0.25, 0.875, 0.3125, 0.75),
            Shapes.box(0, 0.5375, 0.375, 1, 0.56875, 0.625),
            Shapes.box(0.125, 0.3125, 0.125, 0.875, 0.625, 0.875),
            Shapes.box(0.15625, 0, 0.15625, 0.84375, 0.125, 0.84375)
    ).reduce(Shapes.empty(), (a, b) -> Shapes.join(a, b, BooleanOp.OR)), Direction.SOUTH);

    private static final GenerationsVoxelShapes.DirectionalShapes WITHOUT_CAMPFIRE = GenerationsVoxelShapes.generateDirectionVoxelShape( Stream.of(
            Shapes.box(0.125, 0, 0.25, 0.875, 0.3125, 0.75),
            Shapes.box(0, 0.5375, 0.375, 1, 0.56875, 0.625),
            Shapes.box(0.125, 0.3125, 0.125, 0.875, 0.625, 0.875)
    ).reduce(Shapes.empty(), (a, b) -> Shapes.join(a, b, BooleanOp.OR)), Direction.SOUTH);

    public CookingPotBlock(Properties materialIn) {
        super(materialIn, GenerationsBlockEntities.COOKING_POT, GenerationsBlockEntityModels.COOKING_POT);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level worldIn, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand handIn, @NotNull BlockHitResult hit) {
        BlockEntity tileEntity = worldIn.getBlockEntity(pos);

        if (!(tileEntity instanceof CookingPotBlockEntity be) || player.isShiftKeyDown())
            return InteractionResult.FAIL;

        if (worldIn.isClientSide)
            return InteractionResult.SUCCESS;

        MenuRegistry.openExtendedMenu((ServerPlayer) player, be);

        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> blockEntityType) {
        return level.isClientSide ? null : BaseEntityBlock.createTickerHelper(blockEntityType, GenerationsBlockEntities.COOKING_POT.get(), CookingPotBlockEntity::serverTick);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return (level.getBlockEntity(pos, GenerationsBlockEntities.COOKING_POT.get()).filter(CookingPotBlockEntity::hasLogs).isPresent() ? WITH_CAMPFIRE : WITHOUT_CAMPFIRE).getShape(state);
    }
}
