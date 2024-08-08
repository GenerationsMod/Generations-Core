package generations.gg.generations.core.generationscore.common.world.dialogue;

import com.mojang.serialization.Codec;
import generations.gg.generations.core.generationscore.common.world.dialogue.nodes.AbstractNode;
import generations.gg.generations.core.generationscore.common.world.dialogue.nodes.AbstractNode;
import generations.gg.generations.core.generationscore.common.world.dialogue.nodes.AbstractNode;
import net.minecraft.network.FriendlyByteBuf;

import java.util.function.Function;

public record DialogueNodeType<T extends AbstractNode>(
        Codec<T> codec,
        Class<T> nodeClass,
        Function<FriendlyByteBuf, T> decoder) {
    public AbstractNode decode(FriendlyByteBuf buf) {
        return decoder.apply(buf);
    }
}
