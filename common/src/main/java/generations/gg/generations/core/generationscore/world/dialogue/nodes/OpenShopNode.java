package generations.gg.generations.core.generationscore.world.dialogue.nodes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import generations.gg.generations.core.generationscore.world.dialogue.DialogueNodeType;
import generations.gg.generations.core.generationscore.world.dialogue.GenerationsDialogueNodeTypes;
import generations.gg.generations.core.generationscore.world.dialogue.DialoguePlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class OpenShopNode extends AbstractNode {
    public static final Codec<OpenShopNode> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            AbstractNode.CODEC_BY_NAME.optionalFieldOf("next").forGetter(node -> Optional.ofNullable(node.next))
    ).apply(instance, next -> new OpenShopNode(next.orElse(null))));
    private final AbstractNode next;

    public OpenShopNode(AbstractNode next) {
        this.next = next;
    }

    @Override
    public void run(ServerPlayer player, DialoguePlayer dialoguePlayer) {
//        if (dialoguePlayer.getSource() instanceof PlayerNpcEntity npcEntity) {
//            PokeModNetworking.sendPacket(new S2COpenShopPacket(npcEntity.getId()), player);
//        } else {
//            var optional = BlockPos.withinManhattanStream(player.getOnPos(), 10, 10, 10).filter(a -> player.level.getBlockEntity(a) instanceof ShopOfferProvider).findFirst();
//
//            if (optional.isPresent()) {
//                PokeModNetworking.sendPacket(new S2COpenShopPacket(optional.get()), player);
//            } else {
//                dialoguePlayer.nextNode();
//            }
//        }
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
    public Codec<? extends AbstractNode> getCodec() {
        return CODEC;
    }

    @Override
    public DialogueNodeType<?> getType() {
        return GenerationsDialogueNodeTypes.OPEN_SHOP.get();
    }

    @Override
    public void encode(@NotNull FriendlyByteBuf friendlyByteBuf) {
        super.encode(friendlyByteBuf);
        friendlyByteBuf.writeNullable(next, (friendlyByteBuf1, abstractNode) -> abstractNode.encode(friendlyByteBuf1));
    }

    public static OpenShopNode decode(FriendlyByteBuf buf) {
        return new OpenShopNode(
                buf.readNullable(AbstractNode::decode)
        );
    }
}
