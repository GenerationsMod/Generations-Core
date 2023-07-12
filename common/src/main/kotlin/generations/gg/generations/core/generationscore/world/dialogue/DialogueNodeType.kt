package generations.gg.generations.core.generationscore.world.dialogue

import com.mojang.serialization.Codec
import generations.gg.generations.core.generationscore.world.dialogue.nodes.AbstractNode
import net.minecraft.network.FriendlyByteBuf

data class DialogueNodeType<T : AbstractNode>(
    @JvmField val codec: Codec<T>, val nodeClass: Class<out AbstractNode>, private val decoder: (FriendlyByteBuf) -> T) {
    fun decode(buf: FriendlyByteBuf): AbstractNode = decoder.invoke(buf);
}
