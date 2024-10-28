package generations.gg.generations.core.generationscore.common.api.events.general;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public class StatueEvents {
    public interface CanUseChisel {
        boolean canUse(ServerPlayer player, boolean defaultValue);
    }

    public static Event<CanUseChisel> CAN_USE_CHISEL = EventFactory.of(canUseChisels -> (player, defaultValue) -> {
        return canUseChisels.stream().anyMatch(canUseChisel -> canUseChisel.canUse(player, defaultValue)) || defaultValue;
    });
}
