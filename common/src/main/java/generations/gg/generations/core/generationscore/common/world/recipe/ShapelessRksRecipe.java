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
    final NonNullList<GenerationsIngredient> ingredients;

    public ShapelessRksRecipe(ResourceLocation id, String group, RksResult<?> result, NonNullList<GenerationsIngredient> ingredients, boolean consumesTimeCapsules, SpeciesKey key, float experience, int processingTime, boolean showNotification) {
        super(id, group, result, consumesTimeCapsules, key, experience, processingTime, showNotification);
        this.ingredients = ingredients;
    }

    public ShapelessRksRecipe(ResourceLocation id, String group, RksResult<?> result, NonNullList<GenerationsIngredient> ingredients, boolean consumesTimeCapsules, SpeciesKey key, float experience, int processingTime) {
        this(id, group, result, ingredients, consumesTimeCapsules, key, experience, processingTime, false);
    }
    public boolean matches(RksMachineBlockEntity inv, Level level) {
        StackedContents stackedContents = new StackedContents();
        int i = 0;

        for(int j = 0; j < inv.getContainerSize(); ++j) {
            ItemStack itemStack = inv.getItem(j);
            if (!itemStack.isEmpty()) {
                ++i;
                stackedContents.accountStack(itemStack, 1);
            }
        }

        return i == this.ingredients.size() && stackedContents.canCraft(this, (IntList)null);
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= this.ingredients.size();
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
            String string = buffer.readUtf();

            int i = buffer.readInt();
            NonNullList<GenerationsIngredient> nonNullList = NonNullList.withSize(i, GenerationsIngredient.EmptyIngredient.INSTANCE);
            nonNullList.replaceAll(ignored -> GenerationsIngredidents.fromNetwork(buffer));


            var result = RksResultType.RKS_RESULT.get(buffer.readResourceLocation()).fromBuffer().apply(buffer);

            var consumesTimeCapsules = buffer.readBoolean();

            var speciesKey = buffer.readNullable(buf -> {
                var location = buf.readResourceLocation();
                var aspects = buf.readNullable((FriendlyByteBuf.Reader<Set<String>>) buf1 -> buf1.readCollection(HashSet::new, FriendlyByteBuf::readUtf));

                return new SpeciesKey(location, aspects);
            });

            float experience = buffer.readFloat();
            int weavingTime = buffer.readInt();
            boolean bl = buffer.readBoolean();

            return new ShapelessRksRecipe(recipeId, string, result, nonNullList, consumesTimeCapsules, speciesKey, experience, weavingTime, bl);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, ShapelessRksRecipe recipe) {
            buffer.writeUtf(recipe.group);

            buffer.writeVarInt(recipe.ingredients.size());
            for (GenerationsIngredient generationsIngredient : recipe.ingredients) {
                GenerationsIngredidents.toNetwork(buffer, generationsIngredient);
            }

            recipe.result.toBuffer(buffer);

            buffer.writeBoolean(recipe.consumesTimeCapsules);

            buffer.writeNullable(recipe.key, (buf, speciesKey) -> {
                buffer.writeResourceLocation(speciesKey.species());
                buffer.writeNullable(speciesKey.aspects(), (buf1, strings) -> buf1.writeCollection(strings, FriendlyByteBuf::writeUtf));
            });

            buffer.writeFloat(recipe.experience());
            buffer.writeInt(recipe.processingTime());
            buffer.writeBoolean(recipe.showNotification);
        }
    }
}
