package generations.gg.generations.core.generationscore.common.world.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mojang.serialization.JsonOps;
import generations.gg.generations.core.generationscore.common.config.SpeciesKey;
import generations.gg.generations.core.generationscore.common.recipe.GenerationsIngredidents;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.RksMachineBlockEntity;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ShapelessRksRecipe extends RksRecipe {
    public ShapelessRksRecipe(ResourceLocation id, String group, RksResult<?> result, NonNullList<GenerationsIngredient> recipeItems, boolean consumesTimeCapsules, SpeciesKey key, float experience, int processingTime, boolean showNotification) {
        super(id, group, result, recipeItems, consumesTimeCapsules, key, experience, processingTime, showNotification);
    }

    public ShapelessRksRecipe(ResourceLocation id, String group, RksResult<?> result, NonNullList<GenerationsIngredient> ingredients, boolean consumesTimeCapsules, SpeciesKey key, float experience, int processingTime) {
        this(id, group, result, ingredients, consumesTimeCapsules, key, experience, processingTime, false);
    }

    @Override
    public boolean matches(RksMachineBlockEntity inv, Level level) {
        // Track which ingredients have been matched
        boolean[] matchedIngredients = new boolean[this.recipeItems.size()];

        int matchedCount = 0;

        // Iterate over inventory slots
        for (int i = 0; i < inv.getContainerSize(); i++) {
            ItemStack stack = inv.getItem(i);

            if (!stack.isEmpty()) {
                boolean foundMatch = false;

                // Match the stack against ingredients
                for (int j = 0; j < this.recipeItems.size(); j++) {
                    if (!matchedIngredients[j] && this.recipeItems.get(j).matches(stack)) {
                        matchedIngredients[j] = true; // Mark ingredient as matched
                        matchedCount++;
                        foundMatch = true;
                        break; // Stop checking this stack once matched
                    }
                }

                // If this stack couldn't be matched, return false early
                if (!foundMatch) {
                    return false;
                }
            }
        }

        // Ensure all ingredients were matched
        return matchedCount == this.recipeItems.size();
    }


    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= this.recipeItems.size();
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return GenerationsCoreRecipeSerializers.SHAPELESS_RKS.get();
    }

    public record Serializer() implements RecipeSerializer<ShapelessRksRecipe> {

        private static NonNullList<GenerationsIngredient> itemsFromJson(JsonArray ingredientArray) {
            NonNullList<GenerationsIngredient> nonNullList = NonNullList.create();

            for(int i = 0; i < ingredientArray.size(); ++i) {
                var ingredient = GenerationsIngredidents.fromJson(ingredientArray.get(i).getAsJsonObject());
                if (!ingredient.isEmpty()) {
                    nonNullList.add(ingredient);
                }
            }

            return nonNullList;
        }

        @Override
        public @NotNull ShapelessRksRecipe fromJson(@NotNull ResourceLocation recipeId, @NotNull JsonObject json) {
            String string = GsonHelper.getAsString(json, "group", "");

            NonNullList<GenerationsIngredient> nonNullList = itemsFromJson(GsonHelper.getAsJsonArray(json, "ingredients"));

            if (nonNullList.isEmpty()) {
                throw new JsonParseException("No ingredients for shapeless recipe");
            } else if (nonNullList.size() > 9) {
                throw new JsonParseException("Too many ingredients for shapeless recipe");
            } else {
                var result = RksResult.CODEC.decode(JsonOps.INSTANCE, json.get("result")).getOrThrow(false, System.out::println).getFirst();

                var speciesKey = json.has("speciesKey") ? SpeciesKey.fromString(json.getAsJsonPrimitive("speciesKey").getAsString()) : null;

                float experience = GsonHelper.getAsFloat(json, "experience", 0.0F);
                int processingTime = GsonHelper.getAsInt(json, "processingTime", 200);

                boolean bl = GsonHelper.getAsBoolean(json, "show_notification", true);
                boolean consumesTimeCapsules = GsonHelper.getAsBoolean(json, "consumesTimeCapsules", true);
                return new ShapelessRksRecipe(recipeId, string, result, nonNullList, consumesTimeCapsules, speciesKey, experience, processingTime, bl);
            }
        }

        @Override
        public @NotNull ShapelessRksRecipe fromNetwork(@NotNull ResourceLocation recipeId, FriendlyByteBuf buffer) {
            var group = buffer.readUtf();
            var result = RksResultType.RKS_RESULT.get(buffer.readResourceLocation()).fromBuffer().apply(buffer);
            var recipeItems = buffer.readCollection(NonNullList::createWithCapacity, GenerationsIngredidents::fromNetwork);
            var consumesTimeCapsules = buffer.readBoolean();
            var key = buffer.readNullable(TimeCapsuleIngredientKt::readSpeciesKey);
            float experience = buffer.readFloat();
            int processingTime = buffer.readInt();
            boolean showNotification = buffer.readBoolean();

            return new ShapelessRksRecipe(recipeId, group, result, recipeItems, consumesTimeCapsules, key, experience, processingTime, showNotification);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, ShapelessRksRecipe recipe) {
            buffer.writeUtf(recipe.group);
            recipe.result.toBuffer(buffer);
            buffer.writeCollection(recipe.recipeItems, GenerationsIngredidents::toNetwork);
            buffer.writeBoolean(recipe.consumesTimeCapsules);
            buffer.writeNullable(recipe.key, TimeCapsuleIngredientKt::writeSpeciesKey);
            buffer.writeFloat(recipe.experience());
            buffer.writeInt(recipe.processingTime());
            buffer.writeBoolean(recipe.showNotification);
        }
    }
}
