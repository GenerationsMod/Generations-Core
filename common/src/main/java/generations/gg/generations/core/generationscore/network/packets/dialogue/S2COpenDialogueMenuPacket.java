package generations.gg.generations.core.generationscore.network.packets.dialogue;

import com.pokemod.pokemod.network.api.PokeModPacket;
import com.pokemod.pokemod.network.protocol.PokeModClientPacketListener;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

public class S2COpenDialogueMenuPacket implements PokeModPacket {

    private final boolean closable;

    public S2COpenDialogueMenuPacket(boolean closable) {
        this.closable = closable;
    }

    public S2COpenDialogueMenuPacket(FriendlyByteBuf buf) {
        this.closable = buf.readBoolean();
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeBoolean(closable);
    }

    @Override
    public void process(NetworkEvent.Context ctx) {
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () ->
                PokeModClientPacketListener.getInstance().handleOpenDialogueScreen(closable));
    }
}
