package generations.gg.generations.core.generationscore.network.packets.dialogue;

import com.pokemod.pokemod.network.api.PokeModPacket;
import com.pokemod.pokemod.network.protocol.PokeModClientPacketListener;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.List;

public class S2CSayDialoguePacket {

    private final List<String> text;
    private final boolean useNextArrow;

    public S2CSayDialoguePacket(List<String> text, boolean useNextArrow) {
        this.text = text;
        this.useNextArrow = useNextArrow;
    }

    public S2CSayDialoguePacket(FriendlyByteBuf buf) {
        this.text = buf.readList(FriendlyByteBuf::readUtf);
        this.useNextArrow = buf.readBoolean();
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeCollection(text, FriendlyByteBuf::writeUtf);
        buf.writeBoolean(useNextArrow);
    }

    @Override
    public void process(NetworkEvent.Context ctx) {
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () ->
                PokeModClientPacketListener.getInstance().handleDialogueSayText(text, useNextArrow));
    }
}
