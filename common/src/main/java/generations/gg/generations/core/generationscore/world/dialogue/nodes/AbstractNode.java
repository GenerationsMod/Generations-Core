package generations.gg.generations.core.generationscore.world.dialogue.nodes;

import com.mojang.serialization.Codec;
import generations.gg.generations.core.generationscore.api.data.Codecs;
import generations.gg.generations.core.generationscore.world.dialogue.DialogueNodeType;
import generations.gg.generations.core.generationscore.world.dialogue.DialogueNodeTypes;
import generations.gg.generations.core.generationscore.world.dialogue.DialoguePlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public abstract class AbstractNode {
    public static final Codec<AbstractNode> CODEC_BY_NAME = ResourceLocation.CODEC.dispatch("type", abstractNode -> DialogueNodeTypes.DIALOGUE_NODE_TYPES.getId(abstractNode.getType()), location -> DialogueNodeTypes.DIALOGUE_NODE_TYPES.get(location).codec());
    private AbstractNode parent;

    public abstract void run(ServerPlayer serverPlayer, DialoguePlayer dialoguePlayer);

    public AbstractNode getNext() {
        return null;
    }

    public List<AbstractNode> getAllPossibleNexts() {
        return getNext() == null ? Collections.emptyList() : List.of(getNext());
    }

    public AbstractNode getParent() {
        return parent;
    }

    public void setParent(AbstractNode parent) {
        this.parent = parent;
    }

    public String getDescription() {
        return null;
    }

    public abstract Codec<? extends AbstractNode> getCodec();

    public abstract DialogueNodeType<?> getType();
}
