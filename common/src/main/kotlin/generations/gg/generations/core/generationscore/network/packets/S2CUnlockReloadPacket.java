package generations.gg.generations.core.generationscore.network.packets

import com.cobblemon.mod.common.api.net.NetworkPacket
import generations.gg.generations.core.generationscore.GenerationsCore.id
import generations.gg.generations.core.generationscore.GenerationsDataProvider
import generations.gg.generations.core.generationscore.network.ClientNetworkPacketHandler;
import net.minecraft.client.Minecraft
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.resources.ResourceLocation;

import static generations.gg.generations.core.generationscore.GenerationsCore.id;

public class S2CUnlockReloadPacket implements GenerationsNetworkPacket<S2CUnlockReloadPacket> {
    public static ResourceLocation ID = id("unlock_reload");

    public static S2CUnlockReloadPacket decode(FriendlyByteBuf buffer) {
        return new S2CUnlockReloadPacket();
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    public void encode(FriendlyByteBuf buffer) {}

     public static class UnlockReloadPacketHandler implements ClientNetworkPacketHandler<S2CUnlockReloadPacket> {
         public void handle(S2CUnlockReloadPacket packet, Minecraft client) {
             GenerationsDataProvider.INSTANCE.setCanReload(true);
         }
     }
}