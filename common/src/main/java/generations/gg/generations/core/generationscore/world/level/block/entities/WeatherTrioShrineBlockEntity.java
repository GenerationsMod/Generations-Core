package generations.gg.generations.core.generationscore.world.level.block.entities;

import generations.gg.generations.core.generationscore.world.entity.block.PokemonUtil;
import generations.gg.generations.core.generationscore.world.level.block.shrines.WeatherTrioShrineBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class WeatherTrioShrineBlockEntity extends InteractShrineBlockEntity {
    public WeatherTrioShrineBlockEntity(BlockPos arg2, BlockState arg3) {
        super(GenerationsBlockEntities.WEATHER_TRIO.get(), arg2, arg3);
    }

    public boolean activate(ServerPlayer player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        return checkSpawning(player, stack);
    }

    public boolean checkSpawning(ServerPlayer player, ItemStack stack) {
        toggleActive();
        PokemonUtil.spawn(((WeatherTrioShrineBlock) getBlockState().getBlock()).getSpecies(), level, player.getOnPos());

//        level.addFreshEntity(new PixelmonEntity(level, PixelmonData.of(((WeatherTrioShrineBlock) getBlockState().getBlock()).getSpecies()), player.getOnPos())); TODO: Enable
        sync();
        toggleActive();
        stack.shrink(1);
        return true;
    }
}
