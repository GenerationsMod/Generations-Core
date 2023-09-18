package generations.gg.generations.core.generationscore.world.dialogue.nodes;

import com.google.gson.JsonObject;
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

import java.util.concurrent.CompletableFuture;

public class OpenShopNode extends AbstractNode {
    private final AbstractNode next;

    public OpenShopNode(AbstractNode next) {
        this.next = next;
    }

    @Override
    public void run(ServerPlayer player, DialoguePlayer dialoguePlayer) {
        if (dialoguePlayer.getSource() instanceof PlayerNpcEntity npcEntity) {
            var amount = PlayerMoneyHandler.of(player).balance();
            var playerFuture = CompletableFuture.completedFuture(player);
            var npcIdFuture = CompletableFuture.completedFuture(npcEntity.getId());

            amount.thenAccept(amount1 -> {
                var player1 = playerFuture.join();
                var npcId = npcIdFuture.join();

                new S2CSyncPlayerMoneyPacket(amount1).sendToPlayer(player1);
                new S2COpenShopPacket(npcId).sendToPlayer(player1);
            });

            GenerationsNetwork.INSTANCE.sendPacketToPlayer(player, new S2COpenShopPacket(npcEntity.getId()));
        } else {
            var optional = BlockPos.withinManhattanStream(player.getOnPos(), 10, 10, 10).filter(a -> player.level().getBlockEntity(a) instanceof ShopOfferProvider).findFirst();

            if (optional.isPresent()) {
                var blockPos = CompletableFuture.supplyAsync(optional::get);
                var amount = PlayerMoneyHandler.of(player).balance();
                var playerFuture = CompletableFuture.completedFuture(player);

                blockPos.thenAccept(pos -> {
                    var amount1 = amount.join();
                    var player1 = playerFuture.join();
                    new S2CSyncPlayerMoneyPacket(amount1).sendToPlayer(player1);
                    new S2COpenShopPacket(pos).sendToPlayer(player1);
                });
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
