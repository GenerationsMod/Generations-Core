package generations.gg.generations.core.generationscore.common.world.recipe;

import com.mojang.serialization.Codec;
import dev.architectury.registry.registries.*;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiConsumer;
import java.util.function.Function;

public record RksResultType<T extends RksResult<T>>(ResourceLocation id, Codec<T> codec, Function<FriendlyByteBuf, T> fromBuffer, BiConsumer<FriendlyByteBuf, T> toBuffer) {
    public static Registrar<RksResultType<?>> RKS_RESULT = RegistrarManager.get(GenerationsCore.MOD_ID).<RksResultType<?>>builder(GenerationsCore.id("rks_result")).syncToClients().build();

    public static final RegistrySupplier<RksResultType<PokemonResult>> POKEMON = register("pokemon", PokemonResult.CODEC, PokemonResult.FROM_BUFFER, PokemonResult.TO_BUFFER);
    public static final RegistrySupplier<RksResultType<ItemResult>> ITEM = register("item", ItemResult.CODEC, ItemResult.FROM_BUFFER, ItemResult.TO_BUFFER);

    public static <T extends RksResult<T>> RegistrySupplier<RksResultType<T>> register(String name, Codec<T> codec, Function<FriendlyByteBuf, T> function, BiConsumer<FriendlyByteBuf, T> consumer) {
        var id = GenerationsCore.id(name);

        var type = new RksResultType<>(id, codec, function, consumer);

        return RKS_RESULT.register(id, () -> type);
    }

    public static void init() {
    }
}
