package generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning;

import dev.architectury.utils.value.FloatSupplier;
import net.minecraft.world.entity.player.Player;

public interface YawLogic {
    FloatSupplier createSupplier(Player player);
}