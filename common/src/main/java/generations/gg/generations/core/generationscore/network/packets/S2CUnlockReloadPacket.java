package generations.gg.generations.core.generationscore.network.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;;

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

}