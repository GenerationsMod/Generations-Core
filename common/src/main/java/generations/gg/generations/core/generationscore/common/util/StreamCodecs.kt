package generations.gg.generations.core.generationscore.common.util

import dev.architectury.registry.registries.Registrar
import io.netty.buffer.ByteBuf
import net.minecraft.core.NonNullList
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.resources.ResourceLocation
import java.util.*
import javax.swing.text.html.Option
import kotlin.jvm.functions.Function8
import kotlin.jvm.optionals.getOrNull

object StreamCodecs {
    @JvmField val CHAR: StreamCodec<FriendlyByteBuf, Char> = object : StreamCodec<FriendlyByteBuf, Char> {
        override fun decode(buffer: FriendlyByteBuf): Char = buffer.readChar()

        override fun encode(buffer: FriendlyByteBuf, obj: Char) {
            buffer.writeChar(obj.code)
        }
    }

    fun <V> StreamCodec<ByteBuf, V>.asRegistryFriendly(): StreamCodec<RegistryFriendlyByteBuf, V> = mapStream({ it as RegistryFriendlyByteBuf })

    fun <B, V : Any> StreamCodec<B, V>.optional(): StreamCodec<B, Optional<V>> where B : ByteBuf = ByteBufCodecs.optional(this)

    fun <B, V : Any> StreamCodec<B, V>.nullable(): StreamCodec<B, V?> where B : ByteBuf = ByteBufCodecs.optional(this).map({ it.getOrNull() }, { Optional.ofNullable(it) })

    fun <B : ByteBuf, V> set(): StreamCodec.CodecOperation<B, V, Set<V>> where V: Any = StreamCodec.CodecOperation { ByteBufCodecs.collection({ HashSet(it) }, it) }

    fun <B : ByteBuf, V> nonNullList(value: V): StreamCodec.CodecOperation<B, V, NonNullList<V>> where V: Any = StreamCodec.CodecOperation { ByteBufCodecs.collection({ NonNullList.withSize(it, value) }, it) }

    fun <B : ByteBuf, V> StreamCodec<B, V>.set(): StreamCodec<B, Set<V>> where V: Any {
        return this.apply(StreamCodecs.set())
    }

    fun <T> Registrar<T>.streamCodec(): StreamCodec<RegistryFriendlyByteBuf, T> = ResourceLocation.STREAM_CODEC.map({ this.get(it)!! }, this::getId).asRegistryFriendly()

    fun <B : Any, C, T1: Any, T2: Any, T3: Any, T4: Any, T5: Any, T6: Any, T7: Any, T8: Any> composite(
        codec1: StreamCodec<B, T1>, getter1: (C) -> T1,
        codec2: StreamCodec<B, T2>, getter2: (C) -> T2,
        codec3: StreamCodec<B, T3>, getter3: (C) -> T3,
        codec4: StreamCodec<B, T4>, getter4: (C) -> T4,
        codec5: StreamCodec<B, T5>, getter5: (C) -> T5,
        codec6: StreamCodec<B, T6>, getter6: (C) -> T6,
        codec7: StreamCodec<B, T7>, getter7: (C) -> T7,
        codec8: StreamCodec<B, T8>, getter8: (C) -> T8,
        factory: (T1, T2, T3, T4, T5, T6, T7, T8) -> C,
    ): StreamCodec<B, C> {
        return object : StreamCodec<B, C> {
            override fun decode(buffer: B): C {
                val v1 = codec1.decode(buffer)
                val v2 = codec2.decode(buffer)
                val v3 = codec3.decode(buffer)
                val v4 = codec4.decode(buffer)
                val v5 = codec5.decode(buffer)
                val v6 = codec6.decode(buffer)
                val v7 = codec7.decode(buffer)
                val v8 = codec8.decode(buffer)
                return factory.invoke(v1, v2, v3, v4, v5, v6, v7, v8)
            }

            override fun encode(buffer: B, value: C) {
                codec1.encode(buffer, getter1.invoke(value))
                codec2.encode(buffer, getter2.invoke(value))
                codec3.encode(buffer, getter3.invoke(value))
                codec4.encode(buffer, getter4.invoke(value))
                codec5.encode(buffer, getter5.invoke(value))
                codec6.encode(buffer, getter6.invoke(value))
                codec7.encode(buffer, getter7.invoke(value))
                codec8.encode(buffer, getter8.invoke(value))
            }
        }
    }

}