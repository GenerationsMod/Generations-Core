package generations.gg.generations.core.generationscore.world.dialogue.nodes;

import com.google.gson.JsonObject;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.api.player.PlayerMoneyHandler;
import generations.gg.generations.core.generationscore.network.GenerationsNetwork;
import generations.gg.generations.core.generationscore.network.packets.shop.S2COpenShopPacket;
import generations.gg.generations.core.generationscore.network.packets.shop.S2CSyncPlayerMoneyPacket;
import generations.gg.generations.core.generationscore.world.dialogue.DialoguePlayer;
import generations.gg.generations.core.generationscore.world.entity.PlayerNpcEntity;
import generations.gg.generations.core.generationscore.world.entity.ShopOfferProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

public class OpenShopNode extends AbstractNode {
    private final AbstractNode next;

    public OpenShopNode(AbstractNode next) {
        this.next = next;
    }

    @Override
    public void run(ServerPlayer player, DialoguePlayer dialoguePlayer) {
        if (dialoguePlayer.getSource() instanceof PlayerNpcEntity npcEntity) {
            GenerationsNetwork.INSTANCE.sendPacketToPlayer(player, new S2COpenShopPacket(npcEntity.getId()));
        } else {
            var optional = BlockPos.withinManhattanStream(player.getOnPos(), 10, 10, 10).filter(a -> player.level().getBlockEntity(a) instanceof ShopOfferProvider).findFirst();

            if (optional.isPresent()) {
                GenerationsCore.implementation.getNetworkManager().sendPacketToPlayer(player, new S2CSyncPlayerMoneyPacket(PlayerMoneyHandler.of(player).balance()));
                GenerationsCore.implementation.getNetworkManager().sendPacketToPlayer(player, new S2COpenShopPacket(optional.get()));
            } else {
                dialoguePlayer.nextNode();
            }
        }
    }

    @Override
    public AbstractNode getNext() {
        return next;
    }

    @Override
    public void setParent(AbstractNode parent) {
        super.setParent(parent);
        if (next != null) {
            next.setParent(this);
        }
    }

    @Override
    public AbstractNodeType<?> type() {
        return AbstractNodeTypes.OPEN_SHOP;
    }

    @Override
    public void encode(@NotNull FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeNullable(next, AbstractNodeTypes::encode);
    }

    public static OpenShopNode decode(FriendlyByteBuf buf) {
        return new OpenShopNode(
                buf.readNullable(AbstractNodeTypes::decode)
        );
    }

    @Override
    public void toJson(JsonObject json) {
        JsonUtils.toNullable("next", json, next, AbstractNodeTypes::toJson);
    }

    public static OpenShopNode fromJson(JsonObject object) {
        return new OpenShopNode(JsonUtils.fromNullable("next", object, AbstractNodeTypes::fromJson));
    }
}
