package generations.gg.generations.core.generationscore.forge.datagen;

import com.cobblemon.mod.common.CobblemonItems;
import com.cobblemon.mod.common.item.CobblemonItem;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.config.LegendKeys;
import generations.gg.generations.core.generationscore.forge.datagen.generators.recipe.GenerationsRecipeProvider;
import generations.gg.generations.core.generationscore.forge.datagen.generators.recipe.RksRecipeJsonBuilder;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class RksRecipeProvider extends GenerationsRecipeProvider.Proxied {
    public RksRecipeProvider(PackOutput arg) {
        super(arg);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> exporter) {
        RksRecipeJsonBuilder.create(LegendKeys.MEWTWO.createProperties(70))
                .key(LegendKeys.MEWTWO)
                .pattern("XAX")
                .pattern("XBX")
                .pattern("XCX")
                .input('X', GenerationsItems.MEW_FOSSIL.get())
                .input('A', GenerationsItems.DNA_SPLICERS.get())
                .input('B', GenerationsItems.ORB.get())
                .input('C', GenerationsItems.MEW_DNA_FIBER.get())
                .criterion("mew_fossil", InventoryChangeTrigger.TriggerInstance.hasItems(GenerationsItems.MEW_FOSSIL.get()))
                .offerTo(exporter, GenerationsCore.id("mewtwo"));

        RksRecipeJsonBuilder.create(GenerationsItems.WONDER_EGG.get())
                .key(LegendKeys.MANAPHY)
                .pattern("XXX")
                .pattern("ABC")
                .pattern("ZZZ")
                .input('X', GenerationsItems.WATER_GEM.get())
                .input('A', Items.EGG)
                .input('B', GenerationsItems.ORB.get())
                .input('C', Items.HEART_OF_THE_SEA)
                .input('Z', CobblemonItems.MYSTIC_WATER)
                .criterion("heart_of_the_sea", InventoryChangeTrigger.TriggerInstance.hasItems(Items.HEART_OF_THE_SEA))
                .offerTo(exporter, GenerationsCore.id("wonder_egg"));

        RksRecipeJsonBuilder.create(LegendKeys.TYPE_NULL)
                .key(LegendKeys.TYPE_NULL)
                .pattern("DAE")
                .pattern("FBG")
                .pattern("ZCZ")
                .input('A', Items.NETHERITE_CHESTPLATE)
                .input('B', Items.NETHERITE_LEGGINGS)
                .input('C', Items.NETHERITE_BOOTS)
                .input('D', Items.NETHERITE_AXE)
                .input('E', Items.NETHERITE_HELMET)
                .input('F', Items.TOTEM_OF_UNDYING)
                .input('G', GenerationsItems.BUG_MEMORY_DRIVE.get()) //TODO: Replace with memory drive tag
                .input('Z', Items.NETHERITE_BLOCK)
                .criterion("netherite_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHERITE_INGOT))
                .offerTo(exporter, GenerationsCore.id("type_null"));
        createFossil(GenerationsItems.OLD_AMBER, "aerodactyl", exporter);
        createFossil(GenerationsItems.HELIX_FOSSIL, "omanyte", exporter);
        createFossil(GenerationsItems.DOME_FOSSIL, "kabuto", exporter);
        createFossil(GenerationsItems.ROOT_FOSSIL, "lileep", exporter);
        createFossil(GenerationsItems.CLAW_FOSSIL, "anorith", exporter);
        createFossil(GenerationsItems.SKULL_FOSSIL, "cranidos", exporter);
        createFossil(GenerationsItems.ARMOR_FOSSIL, "shieldon", exporter);
        createFossil(GenerationsItems.COVER_FOSSIL, "tirtouga", exporter);
        createFossil(GenerationsItems.PLUME_FOSSIL, "archen", exporter);
        createFossil(GenerationsItems.JAW_FOSSIL, "tyrunt", exporter);
        createFossil(GenerationsItems.SAIL_FOSSIL, "amaura", exporter);
    }

    private void createFossil(RegistrySupplier<Item> item, String name, Consumer<FinishedRecipe> exporter) {
        RksRecipeJsonBuilder.create(name)
                .pattern("A")
                .input('A', item.get())
                .criterion(item.getId().getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(item.get()))
                .offerTo(exporter, GenerationsCore.id(name));
    }
}
