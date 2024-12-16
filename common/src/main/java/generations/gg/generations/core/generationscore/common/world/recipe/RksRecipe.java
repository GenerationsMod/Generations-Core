package generations.gg.generations.core.generationscore.common.world.recipe;

import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.config.SpeciesKey;
import generations.gg.generations.core.generationscore.common.recipe.GenerationsIngredidents;
import generations.gg.generations.core.generationscore.common.util.PokemonFunctionsKt;
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.RksMachineBlockEntity;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public abstract class RksRecipe implements Recipe<RksMachineBlockEntity> {

    public final RksResult<?> result;

    private final ResourceLocation id;
    public final SpeciesKey key;
    public final String group;

    public final boolean consumesTimeCapsules;
    public final float experience;
    public final int processingTime;

    public final boolean showNotification;

    public RksRecipe(ResourceLocation id, String group, RksResult<?> result, boolean consumesTimeCapsules, SpeciesKey key, float experience, int processingTime, boolean showNotification) {
        this.id = id;
        this.group = group;
        this.result = result;
        this.consumesTimeCapsules = consumesTimeCapsules;
        this.key = key;
        this.experience = experience;
        this.processingTime = processingTime;
        this.showNotification = showNotification;
    }


    public RksRecipe(ResourceLocation id, String group, RksResult<?> result, boolean consumesTimeCapsules, float experience, int processingTime) {
        this(id, group, result, consumesTimeCapsules, null, experience, processingTime, true);
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return this.id;
    }

    @Override
    public abstract @NotNull RecipeSerializer<?> getSerializer();

    @Override
    public @NotNull String getGroup() {
        return this.group;
    }

    @Override
    public @NotNull ItemStack getResultItem(@NotNull RegistryAccess registryAccess) {
        try {
            return result.getStack();
        } catch (Exception e) {
            GenerationsCore.LOGGER.warn("Result for RKS machine recipe " + id + " is missing.");
            return ItemStack.EMPTY;
        }
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        return NonNullList.create();
    }

    @Override
    public boolean showNotification() {
        return this.showNotification;
    }

    public @NotNull NonNullList<ItemStack> getRemainingItems(RksMachineBlockEntity container) {

        NonNullList<ItemStack> nonNullList = NonNullList.withSize(9, ItemStack.EMPTY);
        for (int i = 0; i < nonNullList.size(); ++i) {
            ItemStack itemStack = container.inventory.get(i).copy();
            var item = itemStack.getItem();
            if (itemStack.is(GenerationsItems.TIME_CAPSULE.get())) {
                container.pokemon = Optional.ofNullable(PokemonFunctionsKt.getPokemon(itemStack));
                if (!consumesTimeCapsules) nonNullList.set(i, itemStack.copy());
            } else if (item.hasCraftingRemainingItem())
                nonNullList.set(i, new ItemStack(item.getCraftingRemainingItem()));
        }
        return nonNullList;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull RksMachineBlockEntity container, @NotNull RegistryAccess registryAccess) {
        return this.getResultItem(registryAccess).copy();
    }

    public int processingTime() {
        return processingTime;
    }

    public float experience() {
        return experience;
    }

    public boolean isPokemonResult() {
        return result.isPokemon();
    }

    public RksResult<?> getResult() {
        return result;
    }

    public void process(Player player, RksMachineBlockEntity rksMachineBlockEntity, ItemStack stack) {
        result.process(player, rksMachineBlockEntity, stack);
    }

    @Override
    public RecipeType<?> getType() {
        return GenerationsCoreRecipeTypes.RKS.get();
    }
}

