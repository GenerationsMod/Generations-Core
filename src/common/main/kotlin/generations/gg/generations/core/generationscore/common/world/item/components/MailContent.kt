package generations.gg.generations.core.generationscore.common.world.item.components

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import generations.gg.generations.core.generationscore.common.util.StreamCodecs.asRegistryFriendly
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec

data class MailContent(var title: String = "", var author: String = "", var content: String = "", var resolved: Boolean = false) {
    companion object {
        val EMPTY = MailContent("", "", "", true)
        val CODEC = RecordCodecBuilder.create<MailContent> { it.group(
            Codec.string(0, 32).fieldOf("title").forGetter(MailContent::title),
            Codec.STRING.fieldOf("author").forGetter(MailContent::author),
            Codec.STRING.fieldOf("contents").forGetter(MailContent::content),
            Codec.BOOL.fieldOf("resolved").forGetter(MailContent::resolved)
        ).apply(it, ::MailContent) }
        val STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.stringUtf8(32), MailContent::title,
            ByteBufCodecs.STRING_UTF8, MailContent::author,
            ByteBufCodecs.STRING_UTF8, MailContent::content,
            ByteBufCodecs.BOOL, MailContent::resolved,
            ::MailContent
        ).asRegistryFriendly()
    }
}
