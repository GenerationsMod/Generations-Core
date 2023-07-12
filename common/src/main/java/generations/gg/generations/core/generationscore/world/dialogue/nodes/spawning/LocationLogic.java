package generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.function.Supplier;

public interface LocationLogic {
    Supplier<Vec3> createSupplier(Player player);
}