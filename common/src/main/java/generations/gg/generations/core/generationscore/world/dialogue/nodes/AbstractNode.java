package generations.gg.generations.core.generationscore.world.dialogue.nodes;

import generations.gg.generations.core.generationscore.world.dialogue.DialoguePlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public abstract class AbstractNode {
    transient private AbstractNode parent;

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

    public void encode(@NotNull FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeUtf(AbstractNodeAdapter.INSTANCE.getId(this));
    }

    public static AbstractNode decode(FriendlyByteBuf buf) {
        return AbstractNodeAdapter.decode(buf);
    }
}
