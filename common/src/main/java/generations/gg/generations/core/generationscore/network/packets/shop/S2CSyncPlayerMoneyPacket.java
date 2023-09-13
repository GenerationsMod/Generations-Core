package generations.gg.generations.core.generationscore.network.packets.shop;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.api.player.ClientPlayerMoney;
import generations.gg.generations.core.generationscore.network.ClientNetworkPacketHandler;
import generations.gg.generations.core.generationscore.network.packets.GenerationsNetworkPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

public class S2CSyncPlayerMoneyPacket implements GenerationsNetworkPacket<S2CSyncPlayerMoneyPacket> {
    public static ResourceLocation ID = GenerationsCore.id("player_money_sync");

    public final int balance;

    public S2CSyncPlayerMoneyPacket(int balance) {
        this.balance = balance;
    }

    public S2CSyncPlayerMoneyPacket(FriendlyByteBuf buf) {
        this.balance = buf.readInt();
    }

    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(balance);
    }

    public static class Handler implements ClientNetworkPacketHandler<S2CSyncPlayerMoneyPacket> {
        @Override
        public void handle(S2CSyncPlayerMoneyPacket packet) {
            ClientPlayerMoney.balance = packet.balance;
        }
    }
}