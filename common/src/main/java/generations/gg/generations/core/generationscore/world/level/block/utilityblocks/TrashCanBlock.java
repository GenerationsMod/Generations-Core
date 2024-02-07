package generations.gg.generations.core.generationscore.world.level.block.utilityblocks;

import dev.architectury.registry.menu.MenuRegistry;
import generations.gg.generations.core.generationscore.world.container.TrashCanContainer;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsVoxelShapes;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.world.level.block.entities.generic.GenericModelProvidingBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericRotatableModelBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class TrashCanBlock extends GenericRotatableModelBlock<GenericModelProvidingBlockEntity> {
    public static final GenerationsVoxelShapes.DirectionalShapes SHAPE = GenerationsVoxelShapes.generateDirectionVoxelShape(Shapes.or(
                Shapes.box(0.14, 0, 0.14, 0.86, 0.725, 0.86),
                Shapes.box(0.09312500000000001, 0.625, 0.09312500000000001, 0.906875, 0.6625, 0.906875),
                Shapes.box(0.328125, 0, 0.328125, 0.671875, 0.75, 0.671875),
                Shapes.box(0.398125, 0, 0.475, 0.6012500000000001, 0.80625, 0.525)),
            Direction.SOUTH);

    public TrashCanBlock(BlockBehaviour.Properties props) {
        super(props, GenerationsBlockEntities.GENERIC_MODEL_PROVIDING, GenerationsBlockEntityModels.TRASH_CAN);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE.getShape(state);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level worldIn, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand handIn, @NotNull BlockHitResult hit) {
        if (player.isShiftKeyDown())
            return InteractionResult.FAIL;

        if (worldIn.isClientSide)
            return InteractionResult.SUCCESS;

        MenuRegistry.openMenu((ServerPlayer) player, new SimpleMenuProvider((i, arg, arg2) -> new TrashCanContainer(i, arg), Component.translatable("container.trashcan")));

        return InteractionResult.SUCCESS;
    }
}
