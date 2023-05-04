package generations.gg.generations.core.generationscore.api.data;


import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import org.joml.Vector3d;

import java.util.List;
import java.util.Locale;
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
}