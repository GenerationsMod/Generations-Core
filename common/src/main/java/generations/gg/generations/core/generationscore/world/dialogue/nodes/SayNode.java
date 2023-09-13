package generations.gg.generations.core.generationscore.world.dialogue.nodes;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.network.packets.dialogue.S2CSayDialoguePacket;
import generations.gg.generations.core.generationscore.world.dialogue.DialoguePlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SayNode extends AbstractNode implements DialogueContainingNode {
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
    public AbstractNodeType<?> type() {
        return AbstractNodeTypes.SAY;
    }

    @Override
    public void encode(@NotNull FriendlyByteBuf buf) {
        buf.writeCollection(text, FriendlyByteBuf::writeUtf);
        buf.writeNullable(next, AbstractNodeTypes::encode);
    }

    public static SayNode decode(FriendlyByteBuf buf) {
        return new SayNode(
                buf.readList(FriendlyByteBuf::readUtf),
                buf.readNullable(AbstractNodeTypes::decode)
        );
    }

    @Override
    public void toJson(JsonObject json) {
        json.add("text", JsonUtils.toJsonList(text, JsonArray::add));
        JsonUtils.toNullable("next", json, next, AbstractNodeTypes::toJson);
    }

    public static SayNode fromJson(JsonObject object) {
        return new SayNode(JsonUtils.fromJsonList("next", object, JsonElement::getAsString), JsonUtils.fromNullable("next", object, AbstractNodeTypes::fromJson));
    }
}
