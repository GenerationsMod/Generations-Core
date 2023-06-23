package generations.gg.generations.core.generationscore.world.dialogue.nodes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.api.data.Codecs;
import generations.gg.generations.core.generationscore.network.packets.dialogue.S2CSayDialoguePacket;
import generations.gg.generations.core.generationscore.world.dialogue.DialogueNodeType;
import generations.gg.generations.core.generationscore.world.dialogue.GenerationsDialogueNodeTypes;
import generations.gg.generations.core.generationscore.world.dialogue.DialoguePlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class SayNode extends AbstractNode implements DialogueContainingNode{
    public static final Codec<SayNode> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codecs.listCodec(Codec.STRING).fieldOf("text").forGetter(node -> node.text),
            AbstractNode.CODEC_BY_NAME.optionalFieldOf("next").forGetter(node -> Optional.ofNullable(node.next)))
            .apply(instance, (text, next) -> new SayNode(text, next.orElse(null))));

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
    public DialogueNodeType<?> getType() {
        return GenerationsDialogueNodeTypes.SAY.get();
    }

    @Override
    public Codec<? extends AbstractNode> getCodec() {
        return CODEC;
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
