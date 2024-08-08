package generations.gg.generations.core.generationscore.common.world.dialogue.nodes;

import com.google.gson.JsonObject;
import generations.gg.generations.core.generationscore.common.world.dialogue.DialogueNodeType;
import generations.gg.generations.core.generationscore.common.world.dialogue.DialoguePlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ConsumerNode extends AbstractNode {
    private final Consumer<ServerPlayer> consumer;

    public ConsumerNode(Consumer<ServerPlayer> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void run(ServerPlayer serverPlayer, DialoguePlayer dialoguePlayer) {
        consumer.accept(serverPlayer);
    }

    @Override
    public void encode(@NotNull FriendlyByteBuf friendlyByteBuf) {

    }

    @Override
    public void toJson(JsonObject json) {

    }

    @Override
    public AbstractNodeType<?> type() {
        return AbstractNodeTypes.CONSUMER;
    }
}
