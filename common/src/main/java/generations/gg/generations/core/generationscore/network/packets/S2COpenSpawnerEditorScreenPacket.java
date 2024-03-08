package generations.gg.generations.core.generationscore.network.packets;

import generations.gg.generations.core.generationscore.client.screen.SpawnerEditScreen;
import generations.gg.generations.core.generationscore.client.screen.mails.MailEditScreen;
import generations.gg.generations.core.generationscore.client.screen.mails.MailViewScreen;
import generations.gg.generations.core.generationscore.client.screen.mails.MailViewScreen.WrittenMailAccess;
import generations.gg.generations.core.generationscore.network.ClientNetworkPacketHandler;
import generations.gg.generations.core.generationscore.tags.GenerationsItemTags;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;

import static generations.gg.generations.core.generationscore.GenerationsCore.id;

public record S2COpenSpawnerEditorScreenPacket(BlockPos pos) implements GenerationsNetworkPacket<S2COpenSpawnerEditorScreenPacket> {
    public ResourceLocation getId() {
        return ID;
    }
    public void encode(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
    }

    public static ResourceLocation ID = id("open_spawner_editor");
    public static S2COpenSpawnerEditorScreenPacket decode(FriendlyByteBuf buf) {
        return new S2COpenSpawnerEditorScreenPacket(buf.readBlockPos());
    }

    public static class Handler implements ClientNetworkPacketHandler<S2COpenSpawnerEditorScreenPacket> {
        public static final Handler INSTANCE = new Handler();
        public void handle(S2COpenSpawnerEditorScreenPacket packet) {
            Minecraft.getInstance().setScreen(new SpawnerEditScreen(packet.pos()));
        }
    }
}