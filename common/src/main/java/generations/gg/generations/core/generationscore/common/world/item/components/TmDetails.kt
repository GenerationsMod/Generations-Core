package generations.gg.generations.core.generationscore.common.world.item.components

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec

data class TmDetails(val move: String, val number: Int) {
    companion object {
        val CODEC: Codec<TmDetails> = RecordCodecBuilder.create { it.group(
            Codec.STRING.fieldOf("move").forGetter(TmDetails::move),
            Codec.INT.fieldOf("number").forGetter(TmDetails::number)
        ).apply(it, ::TmDetails)
        }
        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, TmDetails> = StreamCodec.composite(ByteBufCodecs.STRING_UTF8, TmDetails::move, ByteBufCodecs.INT, TmDetails::number, ::TmDetails)
    }
}
