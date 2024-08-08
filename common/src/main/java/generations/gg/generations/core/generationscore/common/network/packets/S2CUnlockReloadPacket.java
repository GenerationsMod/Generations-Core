package generations.gg.generations.core.generationscore.common.network.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static generations.gg.generations.core.generationscore.common.GenerationsCore.id;

public class S2CUnlockReloadPacket implements GenerationsNetworkPacket<S2CUnlockReloadPacket> {
    public static ResourceLocation ID = id("unlock_reload");

    public static S2CUnlockReloadPacket decode(FriendlyByteBuf buffer) {
        return new S2CUnlockReloadPacket();
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    public void encode(@NotNull FriendlyByteBuf buffer) {}

}