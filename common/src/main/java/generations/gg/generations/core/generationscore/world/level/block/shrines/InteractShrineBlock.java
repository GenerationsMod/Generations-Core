package generations.gg.generations.core.generationscore.world.level.block.shrines;

import com.pokemod.pokemod.world.level.block.entities.InteractShrineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("deprecation")
public abstract class InteractShrineBlock<T extends InteractShrineBlockEntity> extends ShrineBlock<T> {
    private final Class<T> tClass;

    protected InteractShrineBlock(Properties materialIn, RegistryObject<BlockEntityType<T>> blockEntityFunction, ResourceLocation model, Class<T> tClass) {
        super(materialIn, blockEntityFunction, model);
        this.tClass = tClass;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide() || hand == InteractionHand.OFF_HAND) return InteractionResult.PASS;
        BlockEntity entity = level.getBlockEntity(pos);
        if (isStackValid(player.getItemInHand(hand)) && activate(entity, player, hand)) {
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    private boolean activate(BlockEntity entity, Player player, InteractionHand hand) {
        if(!tClass.isInstance(entity)) return false;
        T t = tClass.cast(entity);
        return !t.isActive() && t.activate((ServerPlayer) player, hand);
    }

    public abstract boolean isStackValid(ItemStack stack);
}
