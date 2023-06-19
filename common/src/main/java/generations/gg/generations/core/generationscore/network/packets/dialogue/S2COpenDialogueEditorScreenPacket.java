package generations.gg.generations.core.generationscore.network.packets.dialogue;

import com.pokemod.pokemod.network.api.PokeModPacket;
import com.pokemod.pokemod.network.protocol.PokeModClientPacketListener;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

public class S2COpenDialogueEditorScreenPacket implements PokeModPacket {

    private final ResourceLocation location;

    public S2COpenDialogueEditorScreenPacket(ResourceLocation location) {
        this.location = location;
    }

    public S2COpenDialogueEditorScreenPacket(FriendlyByteBuf buf) {
        this.location = buf.readResourceLocation();
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeResourceLocation(location);
    }

    @Override
    public void process(NetworkEvent.Context ctx) {
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () ->
                PokeModClientPacketListener.getInstance().handleOpenDialogueEditorScreen(location));
    }
}
