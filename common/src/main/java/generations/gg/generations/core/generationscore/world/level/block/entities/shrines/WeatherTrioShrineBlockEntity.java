package generations.gg.generations.core.generationscore.world.level.block.entities.shrines;

import generations.gg.generations.core.generationscore.world.entity.block.PokemonUtil;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.shrines.WeatherTrioShrineBlock;
import generations.gg.generations.core.generationscore.world.level.schedule.ScheduledTask;
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
        return checkSpawning(player, player.getItemInHand(hand));
    }

    public boolean checkSpawning(ServerPlayer player, ItemStack stack) {
        toggleActive();
        PokemonUtil.spawn(((WeatherTrioShrineBlock) getBlockState().getBlock()).getSpecies().createProperties(70), level, player.getOnPos());
        sync();
        stack.shrink(1);
        ScheduledTask.schedule(this::toggleActive, 150);
        return true;
    }
}
