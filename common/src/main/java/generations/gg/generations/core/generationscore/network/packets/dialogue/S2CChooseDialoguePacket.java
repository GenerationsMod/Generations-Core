package generations.gg.generations.core.generationscore.network.packets.dialogue;

import com.pokemod.pokemod.network.api.PokeModPacket;
import com.pokemod.pokemod.network.protocol.PokeModClientPacketListener;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.List;

public class S2CChooseDialoguePacket implements PokeModPacket {

    private final String text;
    private final List<String> options;

    public S2CChooseDialoguePacket(String text, List<String> options) {
        this.text = text;
        this.options = options;
    }

    public S2CChooseDialoguePacket(FriendlyByteBuf buf) {
        this.text = buf.readUtf();
        this.options = buf.readList(FriendlyByteBuf::readUtf);
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeUtf(text);
        buf.writeCollection(options, FriendlyByteBuf::writeUtf);
    }

    @Override
    public void process(NetworkEvent.Context ctx) {
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () ->
                PokeModClientPacketListener.getInstance().handleDialogueChoose(text, options));
    }
}
