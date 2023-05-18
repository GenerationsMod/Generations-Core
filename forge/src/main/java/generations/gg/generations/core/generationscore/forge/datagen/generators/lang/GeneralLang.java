package generations.gg.generations.core.generationscore.forge.datagen.generators.lang;

import dev.architectury.registry.registries.DeferredRegister;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.item.GenerationsArmor;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.item.GenerationsTools;
import generations.gg.generations.core.generationscore.world.level.block.*;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import org.jetbrains.annotations.NotNull;

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
        addBlockEntries(GenerationsDecorationBlocks.DECORATIONS, this::getNameGens);
        GenerationsDecorationBlocks.UMBRELLA_BLOCKS.forEach(item -> add(item.get(), this.getNameGens(item.getId().toString())));
        GenerationsDecorationBlocks.POKEDOLL_RUG_BLOCKS.forEach(item -> add(item.get(), this.getNameGens(item.getId().toString())));
        GenerationsDecorationBlocks.VENDING_MACHINE_BLOCKS.forEach(item -> add(item.get(), this.getNameGens(item.getId().toString())));
        GenerationsDecorationBlocks.PASTEL_BEAN_BAG_BLOCKS.forEach(item -> add(item.get(), this.getNameGens(item.getId().toString())));
        addBlockEntries(GenerationsWood.WOOD_BLOCKS, this::getNameGens);
        GenerationsUtilityBlocks.PC_BLOCKS.forEach(item -> add(item.get(), this.getNameGens(item.getId().toString()).replace("Pc", "PC")));
        GenerationsUtilityBlocks.HEALER_BLOCKS.forEach(item -> add(item.get(), this.getNameGens(item.getId().toString())));
        GenerationsUtilityBlocks.CLOCK_BLOCKS.forEach(item -> add(item.get(), this.getNameGens(item.getId().toString())));
        addBlockEntries(GenerationsShrines.SHRINES, this::getNameGens);
        addBlockEntries(GenerationsPokeDolls.POKEDOLLS, this::getNameGens);
        addBlockEntries(GenerationsUtilityBlocks.UTILITY_BLOCKS, this::getNameGens);
        addBlockEntries(GenerationsOres.ORES, this::getNameGens);

        addItemEntries(GenerationsTools.TOOLS, this::getNameGens);
        addItemEntries(GenerationsArmor.ARMOR, this::getNameGens);
        addItemEntries(GenerationsItems.ITEMS, this::getNameGens);

       // GenerationsEntities.ENTITIES.getEntries().forEach(Pokemon -> add(Pokemon.get(), getNameGens(Pokemon.getId().toString())));

        add(GenerationsBlocks.BLACK_APRICORN.get().asItem(), "Black Apricorn");
        add(GenerationsBlocks.BLUE_APRICORN.get().asItem(), "Blue Apricorn");
        add(GenerationsBlocks.GREEN_APRICORN.get().asItem(), "Green Apricorn");
        add(GenerationsBlocks.PINK_APRICORN.get().asItem(), "Pink Apricorn");
        add(GenerationsBlocks.RED_APRICORN.get().asItem(), "Red Apricorn");
        add(GenerationsBlocks.WHITE_APRICORN.get().asItem(), "White Apricorn");
        add(GenerationsBlocks.YELLOW_APRICORN.get().asItem(), "Yellow Apricorn");

        //Manually add Creative Tabs
        add("item_group.pokeballs", "Poké Balls");
        add("item_group.restoration", "Restoration");
        add("item_group.tms", "TMs");
        add("item_group.badges_ribbons", "Badges/Ribbons");
        add("item_group.held_items", "Held Items");
        add("item_group.player_items", "Player Items");
        add("item_group.legendary_items", "Legendary Items");
        add("item_group.building_blocks", "Building Blocks");
        add("item_group.decorations", "Decorations");
        add("item_group.natural", "Natural");
        add("item_group.utility", "Utility");
        add("item_group.form_items", "Form Items");
        add("item_group.pokemail", "Pokemail");
        add("item_group.valuables", "Valuables");
        add("item_group.pokedolls", "PokeDolls");
        add("item_group.cuisine", "Cuisine");
        add("item_group.unimplemented", "Unimplemented");
        add("item_group.shrines", "Shrines");

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
        add("pixelmon.melody_flute.not_full_imbued1", "Imbue by defeating %s %s Pokemon.");
        add("pixelmon.melody_flute.not_full_imbued2", "Then shift right click on a %s");
        add("pixelmon.melody_flute.not_full_imbued3", "to spawn %s.");

        add("pixelmon.melody_flute.full_imbued1", "Fully imbued with the essence of %s.");
        add("pixelmon.melody_flute.full_imbued2", "Shift right click on a %s");
        add("pixelmon.melody_flute.full_imbued3", "to spawn %s.");

        add("Generations.timeglass.wrongbiome", "You can only summon Celebi in a Flower Forest Biome");
        add("Generations.timeglass.amount", "You've defeated %s Grass, Psychic, or Fairy Type Pokemon out of 100 in a Flower Forest Biome");
    }

    protected String getNameGens(String name){
            name = name.substring(name.indexOf(":") + 1);  //Removes Mod Tag from front of name
            name = name.replace('_', ' ');
            name = name.substring(0, 1).toUpperCase() + name.substring(1);
            for (int i = 0; i < name.length(); i++)
                if (name.charAt(i) == ' ')
                    name = name.substring(0, i + 1) + name.substring(i + 1, i + 2).toUpperCase() + name.substring(i + 2);

            return name;
        }

    protected String getPokeBrickName(String name){
        return getNameGens(name).replace("Poke Brick", "PokeBrick");
    }

    @Deprecated
    public void addLegacyLang(String key, String entry) {
        add(key.replace(" ", "_").replace("-", "_").replace(",", "_"), entry);
    }

    public void addBlockEntries(DeferredRegister<Block> entries, Function<String, String> function) {
        entries.forEach(block -> add(block.get(), function.apply(block.getId().toString())));
    }

    public void addItemEntries(DeferredRegister<Item> entries, Function<String, String> function) {
        entries.forEach(item -> add(item.get(), function.apply(item.getId().toString())));
    }

    public void add(@NotNull String key, @NotNull String value) {
        try {
            super.add(key, value);
        } catch (IllegalStateException e) {
            GenerationsCore.LOGGER.error("Duplicate translation key " + key);
        }
    }
}
