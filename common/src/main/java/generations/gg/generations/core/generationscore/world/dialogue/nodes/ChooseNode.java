package generations.gg.generations.core.generationscore.world.dialogue.nodes;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.network.packets.dialogue.S2CChooseDialoguePacket;
import generations.gg.generations.core.generationscore.world.dialogue.DialoguePlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChooseNode extends AbstractNode implements DialogueContainingNode, ResponseTakingNode {

    private final String question;
    private final Map<String, AbstractNode> next;
    public transient String response;

    public ChooseNode(String question, Map<String, AbstractNode> next) {
        this.question = question;
        this.next = next;
    }

    @Override
    public void clientResponse(String response) {
        this.response = response;
    }

    @Override
    public void setParent(AbstractNode parent) {
        super.setParent(parent);
        next.values().forEach(node -> node.setParent(this));
    }

    @Override
    public AbstractNode getNext() {
        if(response == null) throw new RuntimeException("Tried getting next node before response");
        return next.get(response);
    }

    @Override
    public List<AbstractNode> getAllPossibleNexts() {
        return new ArrayList<>(next.values());
    }

    @Override
    public void run(ServerPlayer player, DialoguePlayer dialoguePlayer) {
        GenerationsCore.getImplementation().getNetworkManager().sendPacketToPlayer(player, new S2CChooseDialoguePacket(question, new ArrayList<>(next.keySet())));
    }

    @Override
    public void encode(@NotNull FriendlyByteBuf buf) {
        super.encode(buf);
        buf.writeUtf(question);
        buf.writeMap(next, FriendlyByteBuf::writeUtf, (friendlyByteBuf, abstractNode) -> abstractNode.encode(friendlyByteBuf));
    }

    public static ChooseNode decode(FriendlyByteBuf buf) {
        return new ChooseNode(
                buf.readUtf(),
                buf.readMap(FriendlyByteBuf::readUtf, AbstractNode::decode)
        );
    }
}
