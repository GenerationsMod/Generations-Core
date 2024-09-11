package generations.gg.generations.core.generationscore.common.network.packets;

import generations.gg.generations.core.generationscore.common.GenerationsCore;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

public record S2CPlaySoundPacket(ResourceLocation soundLocation, boolean play) implements GenerationsNetworkPacket<S2CPlaySoundPacket> {
    public static ResourceLocation ID = GenerationsCore.id("play_sound");

    public void encode(FriendlyByteBuf buf) {
        buf.writeResourceLocation(soundLocation);
        buf.writeBoolean(play);
    }

    public static S2CPlaySoundPacket decode(FriendlyByteBuf buf) {
        return new S2CPlaySoundPacket(buf.readResourceLocation(), buf.readBoolean());
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }
}
