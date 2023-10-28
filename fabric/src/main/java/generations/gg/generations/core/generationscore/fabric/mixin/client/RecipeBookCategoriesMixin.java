package generations.gg.generations.core.generationscore.fabric.mixin.client;

import generations.gg.generations.core.generationscore.client.RecipeBookManager;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.world.inventory.RecipeBookType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(RecipeBookCategories.class)
public class RecipeBookCategoriesMixin {

    @Inject(method = "getCategories(Lnet/minecraft/world/inventory/RecipeBookType;)Ljava/util/List;", at = @At("HEAD"), cancellable = true)
    private static void getCategories(RecipeBookType recipeBookType, CallbackInfoReturnable<List<RecipeBookCategories>> cir) {
        var categories = RecipeBookManager.getCustomCategoriesOrEmpty(recipeBookType);

        if(!categories.isEmpty()) cir.setReturnValue(categories);
    }
}
