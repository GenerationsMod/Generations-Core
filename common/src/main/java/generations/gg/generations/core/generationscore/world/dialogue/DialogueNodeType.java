package generations.gg.generations.core.generationscore.world.dialogue;

import com.mojang.serialization.Codec;
import com.pokemod.pokemod.world.npc.dialogue.nodes.AbstractNode;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.AbstractNode;

public record DialogueNodeType<T extends AbstractNode>(Codec<T> codec, Class<? extends AbstractNode> nodeClass) {
}
