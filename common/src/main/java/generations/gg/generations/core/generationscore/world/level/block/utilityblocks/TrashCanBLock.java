package generations.gg.generations.core.generationscore.world.level.block.utilityblocks;

import com.pokemod.pokemod.world.level.block.entities.PokeModBlockEntities;
import com.pokemod.pokemod.world.level.block.entities.PokeModBlockEntityModels;
import com.pokemod.pokemod.world.level.block.entities.generic.GenericModelProvidingBlockEntity;
import com.pokemod.pokemod.world.level.block.generic.GenericRotatableModelBlock;
import com.pokemod.pokemod.world.container.TrashCanContainer;
import net.minecraft.core.BlockPos;
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
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("deprecation")
public class TrashCanBLock extends GenericRotatableModelBlock<GenericModelProvidingBlockEntity> {

    public TrashCanBLock(BlockBehaviour.Properties props) {
        super(props, PokeModBlockEntities.GENERIC_MODEL_PROVIDING, PokeModBlockEntityModels.TRASH_CAN);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return Shapes.box(0.3, 0.0, 0.3, 0.7, 0.5, 0.7);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level worldIn, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand handIn, @NotNull BlockHitResult hit) {
        if (player.isShiftKeyDown())
            return InteractionResult.FAIL;

        if (worldIn.isClientSide)
            return InteractionResult.SUCCESS;

        NetworkHooks.openScreen((ServerPlayer) player, new SimpleMenuProvider((i, arg, arg2) -> new TrashCanContainer(i, arg), Component.translatable("container.trashcan")));

        return InteractionResult.SUCCESS;
    }
}
