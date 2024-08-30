package generations.gg.generations.core.generationscore.common.world.recipe;

import com.mojang.serialization.Codec;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiConsumer;
import java.util.function.Function;

public record RksResultType<T extends RksResult<T>>(ResourceLocation id, Codec<T> codec, Function<FriendlyByteBuf, T> fromBuffer, BiConsumer<FriendlyByteBuf, T> toBuffer) {

    public static final RksResultType<RksResult.PokemonResult> POKEMON = register("pokemon", RksResult.PokemonResult.CODEC, RksResult.PokemonResult.FROM_BUFFER, RksResult.PokemonResult.TO_BUFFER);
    public static final RksResultType<RksResult.ItemResult> ITEM = register("item", RksResult.ItemResult.CODEC, RksResult.ItemResult.FROM_BUFFER, RksResult.ItemResult.TO_BUFFER);

    public static <T extends RksResult<T>> RksResultType<T> register(String name, Codec<T> codec, Function<FriendlyByteBuf, T> function, BiConsumer<FriendlyByteBuf, T> consumer) {
        var id = GenerationsCore.id(name);

        return new RksResultType<>(id, codec, function, consumer);
    }

    public static void init() {

    }
}
