package generations.gg.generations.core.generationscore.common.network.packets;

import generations.gg.generations.core.generationscore.common.GenerationsCore;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.RecordItem;

public record S2CPlaySoundPacket(SoundEvent soundEvent, int length, boolean play) implements GenerationsNetworkPacket<S2CPlaySoundPacket> {

    public S2CPlaySoundPacket(SoundEvent event) {
        this(event, 0, false);
    }

    public S2CPlaySoundPacket(RecordItem record) {
        this(record.getSound(), record.getLengthInTicks(), true);
    }

    public static ResourceLocation ID = GenerationsCore.id("play_sound");

    public void encode(FriendlyByteBuf buf) {
        soundEvent.writeToNetwork(buf);
        buf.writeVarInt(length);
        buf.writeBoolean(play);
    }

    public static S2CPlaySoundPacket decode(FriendlyByteBuf buf) {
        return new S2CPlaySoundPacket(SoundEvent.readFromNetwork(buf), buf.readVarInt(), buf.readBoolean());
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }
}
