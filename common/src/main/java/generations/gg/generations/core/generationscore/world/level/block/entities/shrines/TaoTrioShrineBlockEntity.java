package generations.gg.generations.core.generationscore.world.level.block.entities.shrines;

import generations.gg.generations.core.generationscore.world.entity.block.PokemonUtil;
import generations.gg.generations.core.generationscore.world.item.legends.TaoTrioStoneItem;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class TaoTrioShrineBlockEntity extends InteractShrineBlockEntity {
    public TaoTrioShrineBlockEntity(BlockPos pos, BlockState state) {
        super(GenerationsBlockEntities.TAO_TRIO_SHRINE.get(), pos, state);
    }

    public boolean activate(ServerPlayer player, InteractionHand hand) {
        return trySpawn(player, player.getItemInHand(hand));
    }

    public boolean trySpawn(ServerPlayer player, ItemStack stack) {
        toggleActive();
        PokemonUtil.spawn(((TaoTrioStoneItem) stack.getItem()).getSpecies(), level, player.getOnPos());
        sync();
        toggleActive();
        stack.shrink(1);
        return true;
    }

}
