package generations.gg.generations.core.generationscore.common.world.level.block;

import dev.architectury.registry.registries.DeferredRegister;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;

public class GenerationsCoreStats {
    public static final DeferredRegister<ResourceLocation> STATS = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.CUSTOM_STAT);

    public final static ResourceLocation HIDDEN_LOOT_FOUND = register("hidden_loot_found");
    public final static ResourceLocation NORMAL_LOOT_FOUND = register("normal_loot_found");

    private static ResourceLocation register(String name) {
        var location = GenerationsCore.id(name);
        STATS.register(name, () -> location);
        return location;
    }

    public static void init() {
        STATS.register();
    }
}
