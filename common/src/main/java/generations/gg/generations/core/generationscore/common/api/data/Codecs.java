package generations.gg.generations.core.generationscore.common.api.data;


import com.google.gson.*;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.joml.Vector3d;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Function;

public class Codecs {
    public static final Codec<Vector3d> VECTOR_3D_CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Codec.DOUBLE.fieldOf("x").forGetter(a -> a.x),
                    Codec.DOUBLE.fieldOf("y").forGetter(a -> a.y),
                    Codec.DOUBLE.fieldOf("z").forGetter(a -> a.z))
                    .apply(instance, Vector3d::new));

    public static <T extends Enum<T>> Codec<T> enumCodec(Class<T> clazz) {
        return Codec.STRING.xmap(a -> Enum.valueOf(clazz, a.toUpperCase(Locale.ENGLISH)), Enum::name);
    }

    public static <T> Codec<List<T>> listCodec(Codec<T> codec) {
        return Codec.either(codec, codec.listOf()).xmap(either -> either.map(List::of, Function.identity()), list -> list.size() == 1 ? Either.left(list.get(0)) : Either.right(list));
    }

    public static <T> CodecSerializer<T> fromCodec(Codec<T> codec) {
        return new CodecSerializer<>(codec);
    }

    public record CodecSerializer<T>(Codec<T> codec) implements JsonSerializer<T>, JsonDeserializer<T> {
        @Override
        public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return JsonOps.INSTANCE.withDecoder(codec).andThen(DataResult::result).apply(json).orElseThrow().getFirst();
        }

        @Override
        public JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context) {
            return JsonOps.INSTANCE.withEncoder(codec).andThen(DataResult::result).andThen(Optional::orElseThrow).apply(src);
        }
    }
}