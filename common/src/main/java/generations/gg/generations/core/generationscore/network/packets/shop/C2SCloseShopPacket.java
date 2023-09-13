package generations.gg.generations.core.generationscore.network.packets.shop;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.network.ServerNetworkPacketHandler;
import generations.gg.generations.core.generationscore.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.world.dialogue.DialogueManager;
import generations.gg.generations.core.generationscore.world.dialogue.DialoguePlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

public class C2SCloseShopPacket implements GenerationsNetworkPacket<C2SCloseShopPacket> {
    public static final ResourceLocation ID = GenerationsCore.id("close_shop");

    public C2SCloseShopPacket() {
    }

    public C2SCloseShopPacket(FriendlyByteBuf buf) {
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    public static class Handler implements ServerNetworkPacketHandler<C2SCloseShopPacket> {
        @Override
        public void handle(C2SCloseShopPacket packet, MinecraftServer server, ServerPlayer player) {
            ServerPlayer sender = player;
            DialoguePlayer dialoguePlayer = DialogueManager.DIALOGUE_MAP.get(sender);
            if (dialoguePlayer == null)
                return;

            dialoguePlayer.openAndNextNode();
        }
    }
}