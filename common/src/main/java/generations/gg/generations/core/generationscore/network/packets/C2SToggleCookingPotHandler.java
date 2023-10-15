package generations.gg.generations.core.generationscore.network.packets;

import generations.gg.generations.core.generationscore.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.world.level.block.entities.CookingPotBlockEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

public class C2SToggleCookingPotHandler implements ServerNetworkPacketHandler<C2SToggleCookingPotPacket> {
    @Override
    public void handle(@NotNull C2SToggleCookingPotPacket packet, @NotNull MinecraftServer minecraftServer, @NotNull ServerPlayer player) {
        if (player.level().getBlockEntity(packet.pos) instanceof CookingPotBlockEntity te)
            te.setCooking(!te.isCooking());
    }
}
