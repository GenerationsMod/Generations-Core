package generations.gg.generations.core.generationscore.world.item;

import dev.architectury.registry.registries.DeferredRegister;
import generations.gg.generations.core.generationscore.GenerationsCore;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;

public class GenerationsItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.ITEM);

    public static void init() {
        ITEMS.register();
    }
}
