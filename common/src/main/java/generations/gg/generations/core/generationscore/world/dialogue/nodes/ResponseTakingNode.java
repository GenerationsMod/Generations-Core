package generations.gg.generations.core.generationscore.world.dialogue.nodes;

import net.minecraft.server.level.ServerPlayer;

public interface ResponseTakingNode {

    void clientResponse(String response);
}
