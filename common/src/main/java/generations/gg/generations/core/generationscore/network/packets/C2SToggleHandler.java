package generations.gg.generations.core.generationscore.network.packets;

import generations.gg.generations.core.generationscore.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.world.level.block.entities.Toggleable;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

public class C2SToggleHandler implements ServerNetworkPacketHandler<C2STogglePacket> {
    @Override
    public void handle(@NotNull C2STogglePacket packet, @NotNull MinecraftServer minecraftServer, @NotNull ServerPlayer player) {
        if (player.level().getBlockEntity(packet.pos()) instanceof Toggleable te)
            te.setToggled(!te.isToggled());
    }
}
