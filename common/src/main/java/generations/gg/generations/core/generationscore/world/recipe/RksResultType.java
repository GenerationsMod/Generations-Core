package generations.gg.generations.core.generationscore.world.recipe;

import com.mojang.serialization.Codec;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrarManager;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import net.minecraft.network.FriendlyByteBuf;

import java.util.function.BiConsumer;
import java.util.function.Function;

public record RksResultType<T extends RksResult<T>>(Codec<T> codec, Function<FriendlyByteBuf, T> fromBuffer, BiConsumer<FriendlyByteBuf, T> toBuffer) {
    public static final Registrar<RksResultType<?>> REGISTRAR = RegistrarManager.get(GenerationsCore.MOD_ID).<RksResultType<?>>builder(GenerationsCore.id("rks_result")).build();

    public static final RegistrySupplier<RksResultType<RksResult.PokemonResult>> POKEMON = register("pokemon", RksResult.PokemonResult.CODEC, RksResult.PokemonResult.FROM_BUFFER, RksResult.PokemonResult.TO_BUFFER);
    public static final RegistrySupplier<RksResultType<RksResult.ItemResult>> ITEM = register("item", RksResult.ItemResult.CODEC, RksResult.ItemResult.FROM_BUFFER, RksResult.ItemResult.TO_BUFFER);

    public static <T extends RksResult<T>> RegistrySupplier<RksResultType<T>> register(String name, Codec<T> codec, Function<FriendlyByteBuf, T> function, BiConsumer<FriendlyByteBuf, T> consumer) {
        return REGISTRAR.register(GenerationsCore.id(name), () -> new RksResultType<>(codec, function, consumer));
    }

    public static RksResultType<?> fromBuffer(FriendlyByteBuf buffer) {
        return REGISTRAR.get(buffer.readResourceLocation());
    }

    public static void init() {

    }
}
