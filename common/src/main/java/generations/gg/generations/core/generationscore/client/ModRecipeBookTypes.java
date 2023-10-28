package generations.gg.generations.core.generationscore.client;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.inventory.RecipeBookType;

public class ModRecipeBookTypes {
    public static RecipeBookType RKS = getRecipeBookType("RKS");

    @ExpectPlatform
    private static RecipeBookType getRecipeBookType(String name) {
        throw new RuntimeException();
    }

    public static void init() {
    }
}
