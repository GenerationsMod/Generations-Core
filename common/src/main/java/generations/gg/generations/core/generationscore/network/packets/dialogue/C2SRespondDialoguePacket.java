package generations.gg.generations.core.generationscore.network.packets.dialogue;

import com.pokemod.pokemod.PokeMod;
import com.pokemod.pokemod.network.api.PokeModPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class C2SRespondDialoguePacket implements PokeModPacket {

    private final String stringResponse;

    public C2SRespondDialoguePacket(String stringResponse) {
        this.stringResponse = stringResponse;
    }

    public C2SRespondDialoguePacket(FriendlyByteBuf buf) {
        this.stringResponse = buf.readUtf();
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeUtf(stringResponse);
    }

    @Override
    public void process(NetworkEvent.Context ctx) {
        PokeMod.PACKET_LISTENER.handleDialogueClientResponse(ctx.getSender(), stringResponse);
    }
}
