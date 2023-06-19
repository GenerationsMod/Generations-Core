package generations.gg.generations.core.generationscore.network.packets.dialogue;

import com.pokemod.pokemod.PokeMod;
import com.pokemod.pokemod.network.api.PokeModPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class C2SRequestNodesDialoguePacket implements PokeModPacket {

    public C2SRequestNodesDialoguePacket() {}

    public C2SRequestNodesDialoguePacket(FriendlyByteBuf buf) {}

    @Override
    public void encode(FriendlyByteBuf buf) {}

    @Override
    public void process(NetworkEvent.Context ctx) {
        PokeMod.PACKET_LISTENER.handleRequestNextDialogueNodePacket(ctx.getSender());
    }
}
