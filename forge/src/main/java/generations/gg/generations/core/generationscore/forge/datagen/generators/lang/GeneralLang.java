package generations.gg.generations.core.generationscore.forge.datagen.generators.lang;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.item.GenerationsArmor;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.item.GenerationsTools;
import generations.gg.generations.core.generationscore.world.level.block.*;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class GeneralLang extends LanguageProvider {

    public GeneralLang(PackOutput packOutput, String locale) {
        super(packOutput, GenerationsCore.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        addBlockEntries(GenerationsBlocks.BLOCKS, this::getNameGens);
        addBlockEntries(GenerationsBlocks.ULTRA_BLOCKS, this::getNameGens);
        addBlockEntries(GenerationsBlocks.POKEBRICKS, this::getPokeBrickName);
        addBlockEntries(GenerationsBlocks.MARBLE, this::getNameGens);
        addBlockEntries(GenerationsBlocks.STONE, this::getNameGens);
        addBlockEntries(GenerationsDecorationBlocks.DECORATIONS, this::getNameGens);
        addBlockEntries(GenerationsWood.WOOD_BLOCKS, this::getNameGens);
        addBlockEntries(GenerationsShrines.SHRINES, this::getNameGens);
        addBlockEntries(GenerationsPokeDolls.POKEDOLLS, this::getNameGens);
        addBlockEntries(GenerationsUtilityBlocks.UTILITY_BLOCKS, this::getNameGens);
        addBlockEntries(GenerationsOres.ORES, this::getNameGens);

        addItemEntries(GenerationsTools.TOOLS, this::getNameGens, (item, function) -> {});
        addItemEntries(GenerationsArmor.ARMOR, this::getNameGens, (item, function) -> {});
        addItemEntries(GenerationsItems.ITEMS, this::getNameGens, (item, function) -> {
            var item1 = item.get();

            if(item1 instanceof RecordItem) {
                add(item.get().asItem().getDescriptionId() + ".desc", "GlitchxCity - " + function.apply(item, item.getId().toString().replace("_disc", "")));
            }
        });
        addItemEntries(GenerationsItems.POKEBALLS, this::getNameGens, (item, function) -> {});
        addItemEntries(GenerationsItems.BADGES, this::getNameGens, (item, function) -> {});
        addItemEntries(GenerationsItems.RIBBONS, this::getNameGens, (item, function) -> {});

        //Manually add Creative Tabs
        add("pokeballs.generations_core", "Poké Balls");
        add("restoration.generations_core", "Restoration");
        add("tms.generations_core", "TMs");
        add("badges.generations_core", "Badges");
        add("ribbons.generations_core", "Ribbons");
        add("held_items.generations_core", "Held Items");
        add("player_items.generations_core", "Player Items");
        add("legendary_items.generations_core", "Legendary Items");
        add("building_blocks.generations_core", "Building Blocks");
        add("decorations.generations_core", "Decorations");
        add("natural.generations_core", "Natural");
        add("utility.generations_core", "Utility");
        add("form_items.generations_core", "Form Items");
        add("pokemail.generations_core", "Pokemail");
        add("valuables.generations_core", "Valuables");
        add("pokedolls.generations_core", "PokeDolls");
        add("cuisine.generations_core", "Cuisine");
        add("unimplemented.generations_core", "Unimplemented");
        add("shrines.generations_core", "Shrines");

        add("container.melody_flute", "Melody Flute");
        add("container.trashcan", "Trash Can");

        add("container.poke_walkmon", "Poke Walkmon");
        add("container.great_walkmon", "Great Walkmon");
        add("container.ultra_walkmon", "Ultra Walkmon");
        add("container.master_walkmon", "Master Walkmon");

        add("container.pokeball_chest", "Pokeball Chest");
        add("container.greatball_chest", "Greatball Chest");
        add("container.ultraball_chest", "Ultraball Chest");
        add("container.masterball_chest", "Masterball Chest");
        add("container.charge_stone_furnace", "Charge Stone Furnace");
        add("container.charge_stone_blast_furnace", "Charge Stone Blast Furnace");
        add("container.charge_stone_smoker", "Charge Stone Smoker");
        add("container.volcanic_stone_furnace", "Volcanic Stone Furnace");
        add("container.volcanic_stone_blast_furnace", "Volcanic Stone Blast Furnace");
        add("container.volcanic_stone_smoker", "Volcanic Stone Smoker");
        add("container.cooking_pot", "Cooking Pot");


        add("pixelmon.melody_flute.no_item", "This flute resonates with feathers.");

        add("pixelmon.melody_flute.imbued", "Imbued: %s");
        add("pixelmon.melody_flute.not_full_imbued1", "Imbue by defeating %s %s pokemon.");
        add("pixelmon.melody_flute.not_full_imbued2", "Then shift right click on a %s");
        add("pixelmon.melody_flute.not_full_imbued3", "to spawn %s.");

        add("pixelmon.melody_flute.full_imbued1", "Fully imbued with the essence of %s.");
        add("pixelmon.melody_flute.full_imbued2", "Shift right click on a %s");
        add("pixelmon.melody_flute.full_imbued3", "to spawn %s.");

        add("Generations.timeglass.wrongbiome", "You can only summon Celebi in a Flower Forest Biome");
        add("Generations.timeglass.amount", "You've defeated %s Grass, Psychic, or Fairy Type Pokemon out of 100 in a Flower Forest Biome");

        //TR/TM lines
        add("move.cantlearn", "%s can't learn %s.");
        add("move.alreadyknows", "%s already knows %s.");
        add("move.learned", "%s has learned %s.");
        add("move.doesntexist", "The move %s doesn't exists so %s couldn't learn it.");
        add("move.newmove1", "1, 2, and... Ta da!");
        add("move.newmove2", "%s forgot %s!");
        add("move.newmove3", "...and learned %s!");

        add("generations_core.blocks.lootfound", "You found one %s!");
        add("generations_core.blocks.timedclaim", "You've already claimed this timed loot! Try again later!");
        add("generations_core.blocks.claimedloot", "You've already claimed this loot!");
        add("generations_core.blocks.ownerchanged", "Block owner changed to server!");
        add("generations_core.blocks.balllootset", "Custom Ball Loot Set: %s");
        add("generations_core.blocks.visible", "Ball Loot Visibility: %s");
        add("generations_core.blocks.lootmode", "Loot Mode: %s");
        add("generations_core.blocks.lootmodePL1D", "Permanent, limit 1 drop");
        add("generations_core.blocks.lootmodeTD", "Timed drops");
        add("generations_core.blocks.lootmodeFCFS", "First come, first served");
        add("generations_core.blocks.lootmodePUD", "Permanent, unlimited drops");
    }


    protected String getNameGens(RegistrySupplier<? extends ItemLike> item, String name){
        name = name.substring(name.indexOf(":") + 1);  //Removes Mod Tag from front of name
        name = name.replace('_', ' ');
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        for (int i = 0; i < name.length(); i++)
            if (name.charAt(i) == ' ')
                name = name.substring(0, i + 1) + name.substring(i + 1, i + 2).toUpperCase() + name.substring(i + 2);

        name = name.replaceAll("Tm", "TM");

        return name;
    }

    protected String getPokeBrickName(RegistrySupplier<? extends ItemLike> item, String name){
        return getNameGens(item, name).replace("Poke Brick", "PokeBrick");
    }

    @Deprecated
    public void addLegacyLang(String key, String entry) {
        add(key.replace(" ", "_").replace("-", "_").replace(",", "_"), entry);
    }

    public void addBlockEntries(DeferredRegister<Block> entries, BiFunction<RegistrySupplier<Block>, String, String> function) {
        entries.forEach(block -> add(block.get(), function.apply(block, block.getId().toString())));
    }

    public void addItemEntries(DeferredRegister<Item> entries, BiFunction<RegistrySupplier<? extends ItemLike>, String, String> function, BiConsumer<RegistrySupplier<? extends ItemLike>, BiFunction<RegistrySupplier<? extends ItemLike>, String, String>> additionalActions) {
        entries.forEach(item -> {
            add(item.get(), function.apply(item, item.getId().toString()));
            additionalActions.accept(item, function);
        });
    }

    public void add(@NotNull String key, @NotNull String value) {
        try {
            super.add(key, value);
        } catch (IllegalStateException e) {
            GenerationsCore.LOGGER.error("Duplicate translation key " + key);
        }
    }
}
