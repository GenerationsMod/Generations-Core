package generations.gg.generations.core.generationscore.world.dialogue.nodes;

import com.cobblemon.mod.common.api.net.Encodable;
import com.mojang.serialization.Codec;
import generations.gg.generations.core.generationscore.world.dialogue.DialogueNodeType;
import generations.gg.generations.core.generationscore.world.dialogue.GenerationsDialogueNodeTypes;
import generations.gg.generations.core.generationscore.world.dialogue.DialoguePlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public abstract class AbstractNode implements Encodable {
    public static final Codec<AbstractNode> CODEC_BY_NAME = ResourceLocation.CODEC.dispatch("type", abstractNode -> GenerationsDialogueNodeTypes.DIALOGUE_NODE_TYPES.getId(abstractNode.getType()), location -> GenerationsDialogueNodeTypes.DIALOGUE_NODE_TYPES.get(location).codec);
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

    @Override
    public void encode(@NotNull FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeResourceLocation(GenerationsDialogueNodeTypes.DIALOGUE_NODE_TYPES.getId(getType()));
    }

    public static AbstractNode decode(FriendlyByteBuf buf) {
        return GenerationsDialogueNodeTypes.DIALOGUE_NODE_TYPES.get(buf.readResourceLocation()).decode(buf);
    }
}
