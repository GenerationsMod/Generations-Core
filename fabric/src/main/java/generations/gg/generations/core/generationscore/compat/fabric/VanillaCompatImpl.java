package generations.gg.generations.core.generationscore.compat.fabric;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

public class VanillaCompatImpl {


    public static void registerStrippable(@NotNull Block log, @NotNull Block stripped) {
        StrippableBlockRegistry.register(log, stripped);
    }

    public static void registerFlammable(@NotNull Block blockIn, int encouragement, int flammability) {
        FlammableBlockRegistry.getDefaultInstance().add(blockIn, encouragement, flammability);
    }

    public static void registerCompostables(@NotNull Block block, float chance) {
        CompostingChanceRegistry.INSTANCE.add(block, chance);
    }
}
