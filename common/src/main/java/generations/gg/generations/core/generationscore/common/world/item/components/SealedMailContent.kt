package generations.gg.generations.core.generationscore.common.world.item.components

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import generations.gg.generations.core.generationscore.common.util.StreamCodecs.asRegistryFriendly
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.ComponentSerialization
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.server.network.Filterable

data class SealedMailContent(var title: String = "", var author: String = "", var content: String = "", var resolved: Boolean = false) {
    companion object {
        val EMPTY = SealedMailContent("", "", "", true)
        val CODEC = RecordCodecBuilder.create<SealedMailContent> { it.group(
            Codec.string(0, 32).fieldOf("title").forGetter(SealedMailContent::title),
            Codec.STRING.fieldOf("author").forGetter(SealedMailContent::author),
            Codec.STRING.fieldOf("contents").forGetter(SealedMailContent::content),
            Codec.BOOL.fieldOf("resolved").forGetter(SealedMailContent::resolved)
        ).apply(it, ::SealedMailContent) }
        val STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.stringUtf8(32), SealedMailContent::title,
            ByteBufCodecs.STRING_UTF8, SealedMailContent::author,
            ByteBufCodecs.STRING_UTF8, SealedMailContent::content,
            ByteBufCodecs.BOOL, SealedMailContent::resolved,
            ::SealedMailContent
        ).asRegistryFriendly()
    }
}
