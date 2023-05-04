package generations.gg.generations.core.generationscore.world.container.screen;

import generations.gg.generations.core.generationscore.world.container.TrashCanContainer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class TrashCanScreen extends SingleSlotScreen<TrashCanContainer>{
    public TrashCanScreen(TrashCanContainer arg, Inventory arg2, Component arg3) {
        super(arg, arg2, arg3);
    }
}
