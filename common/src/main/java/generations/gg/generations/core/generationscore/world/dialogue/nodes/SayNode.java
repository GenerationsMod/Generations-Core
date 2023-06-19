package generations.gg.generations.core.generationscore.world.dialogue.nodes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.api.data.Codecs;
import generations.gg.generations.core.generationscore.network.GenerationsNetworking;
import generations.gg.generations.core.generationscore.network.packets.dialogue.S2CSayDialoguePacket;
import generations.gg.generations.core.generationscore.world.dialogue.DialogueNodeType;
import generations.gg.generations.core.generationscore.world.dialogue.DialogueNodeTypes;
import generations.gg.generations.core.generationscore.world.dialogue.DialoguePlayer;
import net.minecraft.server.level.ServerPlayer;

import java.util.List;
import java.util.Optional;

public class SayNode extends AbstractNode implements DialogueContainingNode{
    public static final Codec<SayNode> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codecs.listCodec(Codec.STRING).fieldOf("text").forGetter(node -> node.text),
            AbstractNode.CODEC_BY_NAME.optionalFieldOf("next").forGetter(node -> Optional.ofNullable(node.next)))
            .apply(instance, (text, next) -> new SayNode(text, next.orElse(null))));

    private final List<String> text;
    private final AbstractNode next;

    public SayNode(List<String> text, AbstractNode next) {
        this.text = text;
        this.next = next;
    }

    @Override
    public void run(ServerPlayer player, DialoguePlayer dialoguePlayer) {
        GenerationsNetworking.sendPacket(new S2CSayDialoguePacket(text, next instanceof DialogueContainingNode), player);
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
        return DialogueNodeTypes.SAY.get();
    }

    @Override
    public Codec<? extends AbstractNode> getCodec() {
        return CODEC;
    }
}
