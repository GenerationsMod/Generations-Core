package generations.gg.generations.core.generationscore.common.network.packets;

import generations.gg.generations.core.generationscore.common.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.Toggleable;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

public class C2SToggleHandler implements ServerNetworkPacketHandler<C2STogglePacket> {
    @Override
    public void handle(@NotNull C2STogglePacket packet, @NotNull MinecraftServer minecraftServer, @NotNull ServerPlayer player) {
        Toggleable toggleable = null;

        if (packet.pos() != null && player.level().getBlockEntity(packet.pos()) instanceof Toggleable te) {
            toggleable = te;

        } else if(player.containerMenu instanceof Toggleable te) {
            toggleable = te;
        }

        if(toggleable != null) toggleable.setToggled(!toggleable.isToggled());
    }
}
