package generations.gg.generations.core.generationscore.common.api.events.general;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public class CameraEvents {
    public interface ModifyPhoto {
        ItemStack modify(ServerPlayer player, ServerLevel level, ItemStack photo);
    }

    public static Event<ModifyPhoto> MODIFY_PHOTO = EventFactory.of(modifyPhotos -> (player, level, photo) -> {
        ItemStack stack = null;

        for (var modifyPhoto : modifyPhotos) {
            stack = modifyPhoto.modify(player, level, photo);
        }

        return stack;
    });
}
