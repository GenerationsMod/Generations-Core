package generations.gg.generations.core.generationscore.world.dialogue;

import com.cobblemon.mod.common.api.net.Encodable;
import com.cobblemon.mod.common.net.messages.client.data.DataRegistrySyncPacket;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.AbstractNode;
import net.minecraft.network.FriendlyByteBuf;
import org.jetbrains.annotations.NotNull;

public record DialogueGraph(AbstractNode root) implements Encodable {
    public static final Codec<DialogueGraph> CODEC = RecordCodecBuilder.create(instance -> instance.group(AbstractNode.CODEC_BY_NAME.fieldOf("root").forGetter(DialogueGraph::root)).apply(instance, root1 -> {
        DialogueGraph system = new DialogueGraph(root1);
        system.root.setParent(null);
        return system;
    }));

    @Override
    public void encode(@NotNull FriendlyByteBuf friendlyByteBuf) {
        root.encode(friendlyByteBuf);
    }
}
