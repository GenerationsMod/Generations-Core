package generations.gg.generations.core.generationscore.common.util

import com.google.gson.*
import com.mojang.datafixers.util.Either
import com.mojang.datafixers.util.Pair
import com.mojang.serialization.Codec
import com.mojang.serialization.DataResult
import com.mojang.serialization.JsonOps
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import dev.architectury.registry.registries.Registrar
import io.netty.buffer.ByteBuf
import net.minecraft.core.Registry
import net.minecraft.network.codec.StreamCodec
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import org.joml.Vector3d
import java.lang.reflect.Type
import java.util.*
import kotlin.jvm.optionals.getOrNull

object Codecs {
    val VECTOR_3D_CODEC: Codec<Vector3d> = RecordCodecBuilder.create {
        it.group(
            Codec.DOUBLE.fieldOf("x").forGetter({ it.x }),
            Codec.DOUBLE.fieldOf("y").forGetter({ it.y }),
            Codec.DOUBLE.fieldOf("z").forGetter({ it.z })
        ).apply(it, ::Vector3d)
    }

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

    fun <T> ResourceKey<Registry<T>>.tagStreamCodec(): StreamCodec<ByteBuf, TagKey<T>> {
        return ResourceLocation.STREAM_CODEC.map({ TagKey.create(this, it) }, TagKey<*>::location)
    }

    fun <A, T> Codec<T>.nullable(name: String, getter: (A) -> T?): RecordCodecBuilder<A, T?> where T: Any = this.lenientOptionalFieldOf(name).xmap({ it.getOrNull() }, { Optional.ofNullable(it) }).forGetter({ getter.invoke(it) })

    fun <A> Codec<A>.set(): Codec<Set<A>> = listOf().xmap({ it.toSet() }, { it.toList() })

    fun <T> Registrar<T>.codec(): Codec<T> = ResourceLocation.CODEC.xmap({ this.get(it)!! }, { this.getId(it) })
}