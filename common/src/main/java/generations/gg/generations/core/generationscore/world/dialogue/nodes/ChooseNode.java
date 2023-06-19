package generations.gg.generations.core.generationscore.world.dialogue.nodes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.pokemod.pokemod.network.api.PokeModNetworking;
import com.pokemod.pokemod.network.packets.dialogue.S2CChooseDialoguePacket;
import com.pokemod.pokemod.world.npc.dialogue.DialogueNodeType;
import com.pokemod.pokemod.world.npc.dialogue.DialogueNodeTypes;
import com.pokemod.pokemod.world.npc.dialogue.DialoguePlayer;
import generations.gg.generations.core.generationscore.network.GenerationsNetworking;
import generations.gg.generations.core.generationscore.network.packets.dialogue.S2CChooseDialoguePacket;
import generations.gg.generations.core.generationscore.world.dialogue.DialogueNodeType;
import generations.gg.generations.core.generationscore.world.dialogue.DialogueNodeTypes;
import generations.gg.generations.core.generationscore.world.dialogue.DialoguePlayer;
import net.minecraft.server.level.ServerPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChooseNode extends AbstractNode implements DialogueContainingNode, ResponseTakingNode {
    public static final Codec<ChooseNode> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                    Codec.STRING.fieldOf("question").forGetter(node -> node.question),
                    Codec.unboundedMap(Codec.STRING, AbstractNode.CODEC_BY_NAME).fieldOf("next").forGetter(node -> node.next))
            .apply(instance, ChooseNode::new));

    private final String question;
    private final Map<String, AbstractNode> next;
    public String response;

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
        GenerationsNetworking.sendPacket(new S2CChooseDialoguePacket(question, new ArrayList<>(next.keySet())), player);
    }

    @Override
    public DialogueNodeType<?> getType() {
        return DialogueNodeTypes.CHOOSE.get();
    }

    @Override
    public Codec<? extends AbstractNode> getCodec() {
        return CODEC;
    }
}
