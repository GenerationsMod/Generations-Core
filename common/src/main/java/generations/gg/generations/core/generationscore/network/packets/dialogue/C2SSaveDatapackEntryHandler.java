package generations.gg.generations.core.generationscore.network.packets.dialogue;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.api.data.datapack.DatapackSaver;
import generations.gg.generations.core.generationscore.network.ServerNetworkPacketHandler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

public class C2SSaveDatapackEntryHandler implements ServerNetworkPacketHandler<C2SSaveDatapackEntryPacket> {

    @Override
    public void handle(C2SSaveDatapackEntryPacket packet, MinecraftServer server, ServerPlayer player) {
        if (player.hasPermissions(4)) { // Operators only can change configs. Too dangerous. TODO: look into forge's PermissionsAPI class?
            var namespace = packet.location.getNamespace().equals("minecraft") ? "" : ("/" + packet.location.getNamespace());
            DatapackSaver.savePokemodData(server, path -> path.resolve("generated" + namespace + "/" + packet.location.getPath()), packet.data);
        } else {
            GenerationsCore.LOGGER.warn("{} tried saving config data without permission. This player is probably cheating or this is a bug.", player);
        }
    }
}
