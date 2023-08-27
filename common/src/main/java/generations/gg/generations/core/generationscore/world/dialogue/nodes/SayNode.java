package generations.gg.generations.core.generationscore.world.dialogue.nodes;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.network.packets.dialogue.S2CSayDialoguePacket;
import generations.gg.generations.core.generationscore.world.dialogue.DialoguePlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SayNode extends AbstractNode implements DialogueContainingNode{
    private final List<String> text;
    private final AbstractNode next;

    public SayNode(List<String> text, @Nullable AbstractNode next) {
        this.text = text;
        this.next = next;
    }

    @Override
    public void run(ServerPlayer player, DialoguePlayer dialoguePlayer) {
        GenerationsCore.getImplementation().getNetworkManager().sendPacketToPlayer(player, new S2CSayDialoguePacket(text, next instanceof DialogueContainingNode));
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
    public void encode(@NotNull FriendlyByteBuf buf) {
        super.encode(buf);
        buf.writeCollection(text, FriendlyByteBuf::writeUtf);
        buf.writeNullable(next, (friendlyByteBuf, abstractNode) -> abstractNode.encode(friendlyByteBuf));
    }

    public static SayNode decode(FriendlyByteBuf buf) {
        return new SayNode(
                buf.readList(FriendlyByteBuf::readUtf),
                buf.readNullable(AbstractNode::decode)
        );
    }
}
