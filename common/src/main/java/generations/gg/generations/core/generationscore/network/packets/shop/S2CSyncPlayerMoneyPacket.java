package generations.gg.generations.core.generationscore.network.packets.shop;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.api.player.ClientPlayerMoney;
import generations.gg.generations.core.generationscore.network.ClientNetworkPacketHandler;
import generations.gg.generations.core.generationscore.network.packets.GenerationsNetworkPacket;
import generations.gg.generations.core.generationscore.util.GenerationsUtils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

import java.math.BigDecimal;

public class S2CSyncPlayerMoneyPacket implements GenerationsNetworkPacket<S2CSyncPlayerMoneyPacket> {
    public static ResourceLocation ID = GenerationsCore.id("player_money_sync");

    public final BigDecimal balance;

    public S2CSyncPlayerMoneyPacket(BigDecimal balance) {
        this.balance = balance;
    }

    public S2CSyncPlayerMoneyPacket(FriendlyByteBuf buf) {
        this.balance = GenerationsUtils.readBigDecimal(buf);
    }

    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        GenerationsUtils.writeBigDecimal(buf, balance);
    }

    public static class Handler implements ClientNetworkPacketHandler<S2CSyncPlayerMoneyPacket> {
        @Override
        public void handle(S2CSyncPlayerMoneyPacket packet) {
            ClientPlayerMoney.balance = packet.balance;
        }
    }
}