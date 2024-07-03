package generations.gg.generations.core.generationscore.recipe;

import dev.architectury.injectables.annotations.ExpectPlatform;
import generations.gg.generations.core.generationscore.world.recipe.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

public class GenerationsIngredidents {

    @ExpectPlatform
    public static <T extends GenerationsIngredient> void register(String name, Class<T> clazz, GenerationsIngredientSerializer<T> serializer) {
        throw new RuntimeException();
    }

    @ExpectPlatform
    public static ResourceLocation getId(GenerationsIngredient ingredient) {
        throw new RuntimeException();
    }

    public static void init() {
        register("time_capsule", TimeCapsuleIngredient.class, TimeCapsuleIngredientSerializer.INSTANCE);
        register("pokemon_item", PokemonItemIngredient.class, PokemonItemIngredient.PokemonItemIngredientSerializer.INSTANCE);
        register("damage", DamageIngredient.class, DamageIngredientSerializer.INSTANCE);
    }

    @NotNull
    @ExpectPlatform
    public static Ingredient convert(@NotNull GenerationsIngredient generationsIngredient) {
        throw new RuntimeException();
    }
}
