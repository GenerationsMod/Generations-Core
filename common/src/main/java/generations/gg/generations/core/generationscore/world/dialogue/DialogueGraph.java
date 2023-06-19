package generations.gg.generations.core.generationscore.world.dialogue;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.pokemod.pokemod.world.npc.dialogue.nodes.AbstractNode;

public record DialogueGraph(AbstractNode root) {
    public static final Codec<DialogueGraph> CODEC = RecordCodecBuilder.create(instance -> instance.group(AbstractNode.CODEC_BY_NAME.fieldOf("root").forGetter(DialogueGraph::root)).apply(instance, root1 -> {
        DialogueGraph system = new DialogueGraph(root1);
        system.root.setParent(null);
        return system;
    }));
}
