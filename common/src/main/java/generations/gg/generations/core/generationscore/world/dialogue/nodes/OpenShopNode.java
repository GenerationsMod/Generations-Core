package generations.gg.generations.core.generationscore.world.dialogue.nodes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.pokemod.pokemod.network.api.PokeModNetworking;
import com.pokemod.pokemod.network.packets.shop.S2COpenShopPacket;
import com.pokemod.pokemod.world.entity.PlayerNpcEntity;
import com.pokemod.pokemod.world.entity.ShopOfferProvider;
import com.pokemod.pokemod.world.npc.dialogue.DialogueNodeType;
import com.pokemod.pokemod.world.npc.dialogue.DialogueNodeTypes;
import com.pokemod.pokemod.world.npc.dialogue.DialoguePlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

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
        if (dialoguePlayer.getSource() instanceof PlayerNpcEntity npcEntity) {
            PokeModNetworking.sendPacket(new S2COpenShopPacket(npcEntity.getId()), player);
        } else {
            var optional = BlockPos.withinManhattanStream(player.getOnPos(), 10, 10, 10).filter(a -> player.level.getBlockEntity(a) instanceof ShopOfferProvider).findFirst();

            if (optional.isPresent()) {
                PokeModNetworking.sendPacket(new S2COpenShopPacket(optional.get()), player);
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
    public Codec<? extends AbstractNode> getCodec() {
        return CODEC;
    }

    @Override
    public DialogueNodeType<?> getType() {
        return DialogueNodeTypes.OPEN_SHOP;
    }
}
