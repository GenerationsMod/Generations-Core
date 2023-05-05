package generations.gg.generations.core.generationscore.world.level.block.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class TaoTrioShrineBlockEntity extends InteractShrineBlockEntity {
    public TaoTrioShrineBlockEntity(BlockPos pos, BlockState state) {
        super(PokeModBlockEntities.TAO_TRIO_SHRINE.get(), pos, state);
    }

    public boolean activate(ServerPlayer player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        return trySpawn(player, stack);
    }

    public boolean trySpawn(ServerPlayer player, ItemStack stack) {
        toggleActive();
//        level.addFreshEntity(new PixelmonEntity(level, PixelmonData.of(((TaoTrioStoneItem) stack.getItem()).getSpecies()), player.getOnPos())); TODO: enable
        sync();
        toggleActive();
        stack.shrink(1);
        return true;
    }

}
