package generations.gg.generations.core.generationscore.common.mixin.client;

import generations.gg.generations.core.generationscore.common.world.recipe.RksRecipe;
import net.minecraft.client.ClientRecipeBook;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientRecipeBook.class)
public class ClientRecipeBookMixin {
    @Inject(method = "getCategory", at = @At("HEAD"), cancellable = true)
    private static void getCategoryInject(RecipeHolder<?> recipe, CallbackInfoReturnable<RecipeBookCategories> cir) {
        if(recipe.value() instanceof RksRecipe) cir.setReturnValue(RecipeBookCategories.UNKNOWN);

    }
}
