package generations.gg.generations.core.generationscore.world.level.block.utilityblocks;

import com.pokemod.pokemod.world.level.block.entities.PokeModBlockEntityModels;
import com.pokemod.pokemod.world.level.block.generic.GenericRotatableModelBlock;
import com.pokemod.pokemod.world.level.block.entities.CookingPotBlockEntity;
import com.pokemod.pokemod.world.level.block.entities.PokeModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public class CookingPotBlock extends GenericRotatableModelBlock<CookingPotBlockEntity> {

    public CookingPotBlock(Properties materialIn) {
        super(materialIn, PokeModBlockEntities.COOKING_POT, PokeModBlockEntityModels.COOKING_POT);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level worldIn, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand handIn, @NotNull BlockHitResult hit) {
        BlockEntity tileEntity = worldIn.getBlockEntity(pos);

        if (!(tileEntity instanceof CookingPotBlockEntity be) || player.isShiftKeyDown())
            return InteractionResult.FAIL;

        if (worldIn.isClientSide)
            return InteractionResult.SUCCESS;

        NetworkHooks.openScreen((ServerPlayer) player, be, pos);

        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> blockEntityType) {
        return level.isClientSide ? null : BaseEntityBlock.createTickerHelper(blockEntityType, PokeModBlockEntities.COOKING_POT.get(), CookingPotBlockEntity::serverTick);
    }
}
