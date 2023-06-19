package generations.gg.generations.core.generationscore.network;

import dev.architectury.networking.NetworkManager;
import generations.gg.generations.core.generationscore.client.screen.mails.MailEditScreen;
import generations.gg.generations.core.generationscore.client.screen.mails.MailViewScreen;
import generations.gg.generations.core.generationscore.network.packets.C2SEditMailPacket;
import generations.gg.generations.core.generationscore.network.packets.C2SToggleCookingPotPacket;
import generations.gg.generations.core.generationscore.network.packets.S2COpenMailEditScreenPacket;
import generations.gg.generations.core.generationscore.network.packets.S2COpenMailPacket;
import generations.gg.generations.core.generationscore.network.packets.dialogue.S2CSayDialoguePacket;
import generations.gg.generations.core.generationscore.tags.GenerationsItemTags;
import generations.gg.generations.core.generationscore.world.level.block.entities.CookingPotBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;

import java.util.function.Supplier;

public class PacketProccessors {
    void process(S2COpenMailEditScreenPacket packet, Supplier<NetworkManager.PacketContext> ctx) {}

    void process(S2COpenMailPacket packet, Supplier<NetworkManager.PacketContext> ctx) {}

    void process(C2SToggleCookingPotPacket packet, Supplier<NetworkManager.PacketContext> ctx) {
        ctx.get().queue(() -> {
            var sender = ctx.get().getPlayer();
            if (sender.level.getBlockEntity(packet.pos()) instanceof CookingPotBlockEntity pot) pot.setCooking(!pot.isCooking());
        });
    }

    void process(C2SEditMailPacket packet, Supplier<NetworkManager.PacketContext> ctx) {
        ctx.get().queue(() -> {
            packet.handleEditMail((ServerPlayer) ctx.get().getPlayer(), packet.slot(), packet.contents(), packet.title());
        });
    }

    void process(S2CSayDialoguePacket packet, Supplier<NetworkManager.PacketContext> ctx) {}
}
