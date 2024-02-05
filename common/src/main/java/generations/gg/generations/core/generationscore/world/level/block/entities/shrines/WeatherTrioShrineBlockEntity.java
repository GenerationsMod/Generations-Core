package generations.gg.generations.core.generationscore.world.level.block.entities.shrines;

import generations.gg.generations.core.generationscore.world.entity.block.PokemonUtil;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.shrines.ShrineBlock;
import generations.gg.generations.core.generationscore.world.level.schedule.ScheduledTask;
import generations.gg.generations.core.generationscore.world.level.block.shrines.WeatherTrioShrineBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class WeatherTrioShrineBlockEntity extends ShrineBlockEntity {
    public WeatherTrioShrineBlockEntity(BlockPos arg2, BlockState arg3) {
        super(GenerationsBlockEntities.WEATHER_TRIO.get(), arg2, arg3);
    }
}
