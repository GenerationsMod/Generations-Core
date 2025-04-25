package generations.gg.generations.core.generationscore.common.world.item.components

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.ComponentSerialization
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.server.network.Filterable

data class SealedMailContent(val title: Filterable<String>, val content: Filterable<Component>, val resolved: Boolean) {
    companion object {
        val EMPTY = SealedMailContent(Filterable.passThrough(""), Filterable.passThrough(Component.empty()), true)
        val CODEC = RecordCodecBuilder.create<SealedMailContent> { it.group(
            Filterable.codec(Codec.string(0, 32)).fieldOf("title").forGetter(SealedMailContent::title),
            Filterable.codec(ComponentSerialization.flatCodec(32767)).fieldOf("content").forGetter(SealedMailContent::content),
            Codec.BOOL.fieldOf("resolved").forGetter(SealedMailContent::resolved)
        ).apply(it, ::SealedMailContent) }
        val STREAM_CODEC = StreamCodec.composite(
            Filterable.streamCodec(ByteBufCodecs.stringUtf8(32)), SealedMailContent::title,
            Filterable.streamCodec(ComponentSerialization.STREAM_CODEC), SealedMailContent::content,
            ByteBufCodecs.BOOL, SealedMailContent::resolved,
            ::SealedMailContent
        )
    }
}
