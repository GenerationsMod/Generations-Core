package generations.gg.generations.core.generationscore.common.util

import com.google.gson.*
import com.mojang.datafixers.kinds.App
import com.mojang.datafixers.util.Either
import com.mojang.datafixers.util.Pair
import com.mojang.serialization.Codec
import com.mojang.serialization.DataResult
import com.mojang.serialization.JsonOps
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericModelBlock
import io.netty.buffer.ByteBuf
import net.minecraft.core.NonNullList
import net.minecraft.core.Registry
import net.minecraft.network.codec.StreamCodec
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.item.ItemStack
import java.lang.reflect.Type
import java.util.*

object Codecs {
    @JvmStatic
    inline fun <reified T : Enum<T>> enumCodec(clazz: Class<T>): Codec<T> = Codec.STRING.xmap({ enumValueOf<T>(it.uppercase()) }, { it.name })

    @JvmStatic fun <T> listCodec(codec: Codec<T>): Codec<List<T>> = Codec.either(codec, codec.listOf()).xmap({ either -> either.map({ listOf(it) }, { it.toList()}) }, { if(it.size == 1) Either.left(it[0]) else Either.right(it) })

    @JvmStatic fun <T> fromCodec(codec: Codec<T>): CodecSerializer<T> = CodecSerializer(codec)

    data class CodecSerializer<T>(val codec: Codec<T>): JsonSerializer<T>, JsonDeserializer<T> {
        override fun serialize(src: T, typeOfSrc: Type, context: JsonSerializationContext): JsonElement = JsonOps.INSTANCE.withEncoder(codec).andThen(DataResult<JsonElement>::result).andThen(Optional<JsonElement>::orElseThrow).apply(src)

        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): T = JsonOps.INSTANCE.withDecoder(codec).andThen(DataResult<Pair<T, JsonElement>>::result).andThen(Optional<Pair<T, JsonElement>>::orElseThrow).andThen({ it.first }).apply(json)
    }

    fun <T, V> mapCodec(name: String, codec: Codec<V>, getter: (T) -> V, constructor: (V) -> T): MapCodec<T> = RecordCodecBuilder.mapCodec { it.group(codec.fieldOf(name).forGetter(getter)).apply(it, constructor) }

    fun <T> ResourceKey<Registry<T>>.tagCodec(): Codec<TagKey<T>> {
        return TagKey.codec(this)
    }

    fun <T> ResourceKey<Registry<T>>.codec(): Codec<ResourceKey<T>> = ResourceKey.codec(this)

    fun <T> ResourceKey<Registry<T>>.tagStreamCodec(): StreamCodec<ByteBuf, TagKey<T>> {
        return ResourceLocation.STREAM_CODEC.map({ TagKey.create(this, it) }, TagKey<*>::location)
    }

    fun <A> Codec<A>.set(): Codec<Set<A>> = listOf().xmap({ it.toSet() }, { it.toList() })

    fun <T1, V> mapCodec1(
        name1: String, c1: Codec<T1>, g1: (V) -> T1,
        ctor: (T1) -> V
    ): MapCodec<V> =
        RecordCodecBuilder.mapCodec {
            it.group(c1.fieldOf(name1).forGetter(g1))
                .apply(it, ctor)
        }

    fun <T1, V> codec1(
        name1: String, c1: Codec<T1>, g1: (V) -> T1,
        ctor: (T1) -> V
    ): Codec<V> = RecordCodecBuilder.create {
            it.group(c1.fieldOf(name1).forGetter(g1))
                .apply(it, ctor)
        }

    fun <T1, T2, V> mapCodec2(
        name1: String, c1: Codec<T1>, g1: (V) -> T1,
        name2: String, c2: Codec<T2>, g2: (V) -> T2,
        ctor: (T1, T2) -> V
    ): MapCodec<V> =
        RecordCodecBuilder.mapCodec {
            it.group(
                c1.fieldOf(name1).forGetter(g1),
                c2.fieldOf(name2).forGetter(g2)
            ).apply(it, ctor)
        }

    fun <T1, T2, V> codec2(
        name1: String, c1: Codec<T1>, g1: (V) -> T1,
        name2: String, c2: Codec<T2>, g2: (V) -> T2,
        ctor: (T1, T2) -> V
    ): Codec<V> =
        RecordCodecBuilder.create {
            it.group(
                c1.fieldOf(name1).forGetter(g1),
                c2.fieldOf(name2).forGetter(g2)
            ).apply(it, ctor)
        }


    fun <T1, T2, T3, V> codec3(
        name1: String, c1: Codec<T1>, g1: (V) -> T1,
        name2: String, c2: Codec<T2>, g2: (V) -> T2,
        name3: String, c3: Codec<T3>, g3: (V) -> T3,
        ctor: (T1, T2, T3) -> V
    ): Codec<V> =
        RecordCodecBuilder.create {
            it.group(
                c1.fieldOf(name1).forGetter(g1),
                c2.fieldOf(name2).forGetter(g2),
                c3.fieldOf(name3).forGetter(g3)
            ).apply(it, ctor)
        }

    fun <T1, T2, T3, V> mapCodec3(
        name1: String, c1: Codec<T1>, g1: (V) -> T1,
        name2: String, c2: Codec<T2>, g2: (V) -> T2,
        name3: String, c3: Codec<T3>, g3: (V) -> T3,
        ctor: (T1, T2, T3) -> V
    ): MapCodec<V> = RecordCodecBuilder.mapCodec {
            it.group(
                c1.fieldOf(name1).forGetter(g1),
                c2.fieldOf(name2).forGetter(g2),
                c3.fieldOf(name3).forGetter(g3)
            ).apply(it, ctor)
        }

    fun <T1, T2, T3, T4, V> codec4(
        name1: String, c1: Codec<T1>, g1: (V) -> T1,
        name2: String, c2: Codec<T2>, g2: (V) -> T2,
        name3: String, c3: Codec<T3>, g3: (V) -> T3,
        name4: String, c4: Codec<T4>, g4: (V) -> T4,
        ctor: (T1, T2, T3, T4) -> V
    ): Codec<V> {
        return RecordCodecBuilder.create { it.group(
            c1.fieldOf(name1).forGetter(g1),
            c2.fieldOf(name2).forGetter(g2),
            c3.fieldOf(name3).forGetter(g3),
            c4.fieldOf(name4).forGetter(g4)
        ).apply(it, ctor) }
    }


    fun <T1, T2, T3, T4, V> mapCodec4(
        name1: String, c1: Codec<T1>, g1: (V) -> T1,
        name2: String, c2: Codec<T2>, g2: (V) -> T2,
        name3: String, c3: Codec<T3>, g3: (V) -> T3,
        name4: String, c4: Codec<T4>, g4: (V) -> T4,
        ctor: (T1, T2, T3, T4) -> V
    ): MapCodec<V> {
        return RecordCodecBuilder.mapCodec { it.group(
            c1.fieldOf(name1).forGetter(g1),
            c2.fieldOf(name2).forGetter(g2),
            c3.fieldOf(name3).forGetter(g3),
            c4.fieldOf(name4).forGetter(g4)
        ).apply(it, ctor) }
    }

    fun <T1, T2, T3, T4, T5, V> codec5(
        name1: String, c1: Codec<T1>, g1: (V) -> T1,
        name2: String, c2: Codec<T2>, g2: (V) -> T2,
        name3: String, c3: Codec<T3>, g3: (V) -> T3,
        name4: String, c4: Codec<T4>, g4: (V) -> T4,
        name5: String, c5: Codec<T5>, g5: (V) -> T5,
        ctor: (T1, T2, T3, T4, T5) -> V
    ): MapCodec<V> = RecordCodecBuilder.mapCodec {
            it.group(
                c1.fieldOf(name1).forGetter(g1),
                c2.fieldOf(name2).forGetter(g2),
                c3.fieldOf(name3).forGetter(g3),
                c4.fieldOf(name4).forGetter(g4),
                c5.fieldOf(name5).forGetter(g5)
            ).apply(it, ctor)
        }

    fun <T1, T2, T3, T4, T5, T6, V> codec6(
        name1: String, c1: Codec<T1>, g1: (V) -> T1,
        name2: String, c2: Codec<T2>, g2: (V) -> T2,
        name3: String, c3: Codec<T3>, g3: (V) -> T3,
        name4: String, c4: Codec<T4>, g4: (V) -> T4,
        name5: String, c5: Codec<T5>, g5: (V) -> T5,
        name6: String, c6: Codec<T6>, g6: (V) -> T6,
        ctor: (T1, T2, T3, T4, T5, T6) -> V
    ): MapCodec<V> =
        RecordCodecBuilder.mapCodec {
            it.group(
                c1.fieldOf(name1).forGetter(g1),
                c2.fieldOf(name2).forGetter(g2),
                c3.fieldOf(name3).forGetter(g3),
                c4.fieldOf(name4).forGetter(g4),
                c5.fieldOf(name5).forGetter(g5),
                c6.fieldOf(name6).forGetter(g6)
            ).apply(it, ctor)
        }

    fun <T1, T2, T3, T4, T5, T6, T7, V> codec7(
        name1: String, c1: Codec<T1>, g1: (V) -> T1,
        name2: String, c2: Codec<T2>, g2: (V) -> T2,
        name3: String, c3: Codec<T3>, g3: (V) -> T3,
        name4: String, c4: Codec<T4>, g4: (V) -> T4,
        name5: String, c5: Codec<T5>, g5: (V) -> T5,
        name6: String, c6: Codec<T6>, g6: (V) -> T6,
        name7: String, c7: Codec<T7>, g7: (V) -> T7,
        ctor: (T1, T2, T3, T4, T5, T6, T7) -> V
    ): MapCodec<V> = RecordCodecBuilder.mapCodec {
        it.group(
            c1.fieldOf(name1).forGetter(g1),
            c2.fieldOf(name2).forGetter(g2),
            c3.fieldOf(name3).forGetter(g3),
            c4.fieldOf(name4).forGetter(g4),
            c5.fieldOf(name5).forGetter(g5),
            c6.fieldOf(name6).forGetter(g6),
            c7.fieldOf(name7).forGetter(g7)
        ).apply(it, ctor)
    }

    fun <T1, T2, T3, T4, T5, T6, T7, T8, V> codec8(
        name1: String, c1: Codec<T1>, g1: (V) -> T1,
        name2: String, c2: Codec<T2>, g2: (V) -> T2,
        name3: String, c3: Codec<T3>, g3: (V) -> T3,
        name4: String, c4: Codec<T4>, g4: (V) -> T4,
        name5: String, c5: Codec<T5>, g5: (V) -> T5,
        name6: String, c6: Codec<T6>, g6: (V) -> T6,
        name7: String, c7: Codec<T7>, g7: (V) -> T7,
        name8: String, c8: Codec<T8>, g8: (V) -> T8,
        ctor: (T1, T2, T3, T4, T5, T6, T7, T8) -> V
    ): MapCodec<V> = RecordCodecBuilder.mapCodec {
        it.group(
            c1.fieldOf(name1).forGetter(g1),
            c2.fieldOf(name2).forGetter(g2),
            c3.fieldOf(name3).forGetter(g3),
            c4.fieldOf(name4).forGetter(g4),
            c5.fieldOf(name5).forGetter(g5),
            c6.fieldOf(name6).forGetter(g6),
            c7.fieldOf(name7).forGetter(g7),
            c8.fieldOf(name8).forGetter(g8)
        ).apply(it, ctor)
    }

    fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, V> codec10(
        name1: String, c1: Codec<T1>, g1: (V) -> T1,
        name2: String, c2: Codec<T2>, g2: (V) -> T2,
        name3: String, c3: Codec<T3>, g3: (V) -> T3,
        name4: String, c4: Codec<T4>, g4: (V) -> T4,
        name5: String, c5: Codec<T5>, g5: (V) -> T5,
        name6: String, c6: Codec<T6>, g6: (V) -> T6,
        name7: String, c7: Codec<T7>, g7: (V) -> T7,
        name8: String, c8: Codec<T8>, g8: (V) -> T8,
        name9: String, c9: Codec<T9>, g9: (V) -> T9,
        name10: String, c10: Codec<T10>, g10: (V) -> T10,
        ctor: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) -> V
    ): MapCodec<V> = RecordCodecBuilder.mapCodec {
        it.group(
            c1.fieldOf(name1).forGetter(g1),
            c2.fieldOf(name2).forGetter(g2),
            c3.fieldOf(name3).forGetter(g3),
            c4.fieldOf(name4).forGetter(g4),
            c5.fieldOf(name5).forGetter(g5),
            c6.fieldOf(name6).forGetter(g6),
            c7.fieldOf(name7).forGetter(g7),
            c8.fieldOf(name8).forGetter(g8),
            c9.fieldOf(name9).forGetter(g9),
            c10.fieldOf(name10).forGetter(g10)
        ).apply(it, ctor)
    }

    fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, V> codec11(
        name1: String, c1: Codec<T1>, g1: (V) -> T1,
        name2: String, c2: Codec<T2>, g2: (V) -> T2,
        name3: String, c3: Codec<T3>, g3: (V) -> T3,
        name4: String, c4: Codec<T4>, g4: (V) -> T4,
        name5: String, c5: Codec<T5>, g5: (V) -> T5,
        name6: String, c6: Codec<T6>, g6: (V) -> T6,
        name7: String, c7: Codec<T7>, g7: (V) -> T7,
        name8: String, c8: Codec<T8>, g8: (V) -> T8,
        name9: String, c9: Codec<T9>, g9: (V) -> T9,
        name10: String, c10: Codec<T10>, g10: (V) -> T10,
        name11: String, c11: Codec<T11>, g11: (V) -> T11,
        ctor: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) -> V
    ): MapCodec<V> = RecordCodecBuilder.mapCodec {
        it.group(
            c1.fieldOf(name1).forGetter(g1),
            c2.fieldOf(name2).forGetter(g2),
            c3.fieldOf(name3).forGetter(g3),
            c4.fieldOf(name4).forGetter(g4),
            c5.fieldOf(name5).forGetter(g5),
            c6.fieldOf(name6).forGetter(g6),
            c7.fieldOf(name7).forGetter(g7),
            c8.fieldOf(name8).forGetter(g8),
            c9.fieldOf(name9).forGetter(g9),
            c10.fieldOf(name10).forGetter(g10),
            c11.fieldOf(name11).forGetter(g11)
        ).apply(it, ctor)
    }

    fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, V> codec12(
        name1: String, c1: Codec<T1>, g1: (V) -> T1,
        name2: String, c2: Codec<T2>, g2: (V) -> T2,
        name3: String, c3: Codec<T3>, g3: (V) -> T3,
        name4: String, c4: Codec<T4>, g4: (V) -> T4,
        name5: String, c5: Codec<T5>, g5: (V) -> T5,
        name6: String, c6: Codec<T6>, g6: (V) -> T6,
        name7: String, c7: Codec<T7>, g7: (V) -> T7,
        name8: String, c8: Codec<T8>, g8: (V) -> T8,
        name9: String, c9: Codec<T9>, g9: (V) -> T9,
        name10: String, c10: Codec<T10>, g10: (V) -> T10,
        name11: String, c11: Codec<T11>, g11: (V) -> T11,
        name12: String, c12: Codec<T12>, g12: (V) -> T12,
        ctor: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) -> V
    ): MapCodec<V> = RecordCodecBuilder.mapCodec {
        it.group(
            c1.fieldOf(name1).forGetter(g1),
            c2.fieldOf(name2).forGetter(g2),
            c3.fieldOf(name3).forGetter(g3),
            c4.fieldOf(name4).forGetter(g4),
            c5.fieldOf(name5).forGetter(g5),
            c6.fieldOf(name6).forGetter(g6),
            c7.fieldOf(name7).forGetter(g7),
            c8.fieldOf(name8).forGetter(g8),
            c9.fieldOf(name9).forGetter(g9),
            c10.fieldOf(name10).forGetter(g10),
            c11.fieldOf(name11).forGetter(g11),
            c12.fieldOf(name12).forGetter(g12)
        ).apply(it, ctor)
    }

    fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, V> codec13(
        name1: String, c1: Codec<T1>, g1: (V) -> T1,
        name2: String, c2: Codec<T2>, g2: (V) -> T2,
        name3: String, c3: Codec<T3>, g3: (V) -> T3,
        name4: String, c4: Codec<T4>, g4: (V) -> T4,
        name5: String, c5: Codec<T5>, g5: (V) -> T5,
        name6: String, c6: Codec<T6>, g6: (V) -> T6,
        name7: String, c7: Codec<T7>, g7: (V) -> T7,
        name8: String, c8: Codec<T8>, g8: (V) -> T8,
        name9: String, c9: Codec<T9>, g9: (V) -> T9,
        name10: String, c10: Codec<T10>, g10: (V) -> T10,
        name11: String, c11: Codec<T11>, g11: (V) -> T11,
        name12: String, c12: Codec<T12>, g12: (V) -> T12,
        name13: String, c13: Codec<T13>, g13: (V) -> T13,
        ctor: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) -> V
    ): MapCodec<V> = RecordCodecBuilder.mapCodec {
        it.group(
            c1.fieldOf(name1).forGetter(g1),
            c2.fieldOf(name2).forGetter(g2),
            c3.fieldOf(name3).forGetter(g3),
            c4.fieldOf(name4).forGetter(g4),
            c5.fieldOf(name5).forGetter(g5),
            c6.fieldOf(name6).forGetter(g6),
            c7.fieldOf(name7).forGetter(g7),
            c8.fieldOf(name8).forGetter(g8),
            c9.fieldOf(name9).forGetter(g9),
            c10.fieldOf(name10).forGetter(g10),
            c11.fieldOf(name11).forGetter(g11),
            c12.fieldOf(name12).forGetter(g12),
            c13.fieldOf(name13).forGetter(g13)
        ).apply(it, ctor)
    }

    fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, V> codec14(
        name1: String, c1: Codec<T1>, g1: (V) -> T1,
        name2: String, c2: Codec<T2>, g2: (V) -> T2,
        name3: String, c3: Codec<T3>, g3: (V) -> T3,
        name4: String, c4: Codec<T4>, g4: (V) -> T4,
        name5: String, c5: Codec<T5>, g5: (V) -> T5,
        name6: String, c6: Codec<T6>, g6: (V) -> T6,
        name7: String, c7: Codec<T7>, g7: (V) -> T7,
        name8: String, c8: Codec<T8>, g8: (V) -> T8,
        name9: String, c9: Codec<T9>, g9: (V) -> T9,
        name10: String, c10: Codec<T10>, g10: (V) -> T10,
        name11: String, c11: Codec<T11>, g11: (V) -> T11,
        name12: String, c12: Codec<T12>, g12: (V) -> T12,
        name13: String, c13: Codec<T13>, g13: (V) -> T13,
        name14: String, c14: Codec<T14>, g14: (V) -> T14,
        ctor: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) -> V
    ): MapCodec<V> = RecordCodecBuilder.mapCodec {
        it.group(
            c1.fieldOf(name1).forGetter(g1),
            c2.fieldOf(name2).forGetter(g2),
            c3.fieldOf(name3).forGetter(g3),
            c4.fieldOf(name4).forGetter(g4),
            c5.fieldOf(name5).forGetter(g5),
            c6.fieldOf(name6).forGetter(g6),
            c7.fieldOf(name7).forGetter(g7),
            c8.fieldOf(name8).forGetter(g8),
            c9.fieldOf(name9).forGetter(g9),
            c10.fieldOf(name10).forGetter(g10),
            c11.fieldOf(name11).forGetter(g11),
            c12.fieldOf(name12).forGetter(g12),
            c13.fieldOf(name13).forGetter(g13),
            c14.fieldOf(name14).forGetter(g14)
        ).apply(it, ctor)
    }

    fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, V> codec15(
        name1: String, c1: Codec<T1>, g1: (V) -> T1,
        name2: String, c2: Codec<T2>, g2: (V) -> T2,
        name3: String, c3: Codec<T3>, g3: (V) -> T3,
        name4: String, c4: Codec<T4>, g4: (V) -> T4,
        name5: String, c5: Codec<T5>, g5: (V) -> T5,
        name6: String, c6: Codec<T6>, g6: (V) -> T6,
        name7: String, c7: Codec<T7>, g7: (V) -> T7,
        name8: String, c8: Codec<T8>, g8: (V) -> T8,
        name9: String, c9: Codec<T9>, g9: (V) -> T9,
        name10: String, c10: Codec<T10>, g10: (V) -> T10,
        name11: String, c11: Codec<T11>, g11: (V) -> T11,
        name12: String, c12: Codec<T12>, g12: (V) -> T12,
        name13: String, c13: Codec<T13>, g13: (V) -> T13,
        name14: String, c14: Codec<T14>, g14: (V) -> T14,
        name15: String, c15: Codec<T15>, g15: (V) -> T15,
        ctor: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) -> V
    ): MapCodec<V> = RecordCodecBuilder.mapCodec {
        it.group(
            c1.fieldOf(name1).forGetter(g1),
            c2.fieldOf(name2).forGetter(g2),
            c3.fieldOf(name3).forGetter(g3),
            c4.fieldOf(name4).forGetter(g4),
            c5.fieldOf(name5).forGetter(g5),
            c6.fieldOf(name6).forGetter(g6),
            c7.fieldOf(name7).forGetter(g7),
            c8.fieldOf(name8).forGetter(g8),
            c9.fieldOf(name9).forGetter(g9),
            c10.fieldOf(name10).forGetter(g10),
            c11.fieldOf(name11).forGetter(g11),
            c12.fieldOf(name12).forGetter(g12),
            c13.fieldOf(name13).forGetter(g13),
            c14.fieldOf(name14).forGetter(g14),
            c15.fieldOf(name15).forGetter(g15)
        ).apply(it, ctor)
    }

    fun <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, V> codec16(
        name1: String, c1: Codec<T1>, g1: (V) -> T1,
        name2: String, c2: Codec<T2>, g2: (V) -> T2,
        name3: String, c3: Codec<T3>, g3: (V) -> T3,
        name4: String, c4: Codec<T4>, g4: (V) -> T4,
        name5: String, c5: Codec<T5>, g5: (V) -> T5,
        name6: String, c6: Codec<T6>, g6: (V) -> T6,
        name7: String, c7: Codec<T7>, g7: (V) -> T7,
        name8: String, c8: Codec<T8>, g8: (V) -> T8,
        name9: String, c9: Codec<T9>, g9: (V) -> T9,
        name10: String, c10: Codec<T10>, g10: (V) -> T10,
        name11: String, c11: Codec<T11>, g11: (V) -> T11,
        name12: String, c12: Codec<T12>, g12: (V) -> T12,
        name13: String, c13: Codec<T13>, g13: (V) -> T13,
        name14: String, c14: Codec<T14>, g14: (V) -> T14,
        name15: String, c15: Codec<T15>, g15: (V) -> T15,
        name16: String, c16: Codec<T16>, g16: (V) -> T16,
        ctor: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) -> V
    ): MapCodec<V> = RecordCodecBuilder.mapCodec {
        it.group(
            c1.fieldOf(name1).forGetter(g1),
            c2.fieldOf(name2).forGetter(g2),
            c3.fieldOf(name3).forGetter(g3),
            c4.fieldOf(name4).forGetter(g4),
            c5.fieldOf(name5).forGetter(g5),
            c6.fieldOf(name6).forGetter(g6),
            c7.fieldOf(name7).forGetter(g7),
            c8.fieldOf(name8).forGetter(g8),
            c9.fieldOf(name9).forGetter(g9),
            c10.fieldOf(name10).forGetter(g10),
            c11.fieldOf(name11).forGetter(g11),
            c12.fieldOf(name12).forGetter(g12),
            c13.fieldOf(name13).forGetter(g13),
            c14.fieldOf(name14).forGetter(g14),
            c15.fieldOf(name15).forGetter(g15),
            c16.fieldOf(name16).forGetter(g16)
        ).apply(it, ctor)
    }

    fun <A> mapCodec(block: RecordCodecBuilder.Instance<A>.() -> App<RecordCodecBuilder.Mu<A>, A>): MapCodec<A> = RecordCodecBuilder.mapCodec(block)
    fun <T : GenericModelBlock> modelCodec(): RecordCodecBuilder<T, ResourceLocation> = ResourceLocation.CODEC.fieldOf("model").forGetter { it.getModel()!! }

    val ITEM_STACK_LIST_CODEC = ItemStack.OPTIONAL_CODEC.listOf().xmap<NonNullList<ItemStack>>({
        val list = NonNullList.withSize(it.size, ItemStack.EMPTY)
        list.addAll(it)
        return@xmap list
    }, { it })
}