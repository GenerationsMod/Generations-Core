package generations.gg.generations.core.generationscore.forge.datagen.generators.lang;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.item.GenerationsArmor;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.item.GenerationsTools;
import generations.gg.generations.core.generationscore.world.item.LangTooltip;
import generations.gg.generations.core.generationscore.world.level.block.*;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import static generations.gg.generations.core.generationscore.world.item.GenerationsItems.*;
import static generations.gg.generations.core.generationscore.world.level.block.GenerationsShrines.*;

public class GeneralLang extends LanguageProvider {

    public GeneralLang(PackOutput packOutput, String locale) {
        super(packOutput, GenerationsCore.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        addBlockEntries(GenerationsBlocks.BLOCKS, this::getNameGens);
        addBlockEntries(GenerationsBlocks.ULTRA_BLOCKS, this::getNameGens);
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
        addItemEntries(GenerationsItems.BADGES, this::getNameGens, (item, function) -> {});
        addItemEntries(GenerationsItems.RIBBONS, this::getNameGens, (item, function) -> {});

        //Manually add Creative Tabs
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


        add("generations_core.item.melody_flute.no_item", "This flute resonates with feathers.");

        add("generations_core.item.melody_flute.imbued", "Imbued: %s");
        add("generations_core.item.melody_flute.not_full_imbued1", "Imbue by defeating %s %s pokemon.");
        add("generations_core.item.melody_flute.not_full_imbued2", "Then shift right click on a %s");
        add("generations_core.item.melody_flute.not_full_imbued3", "to spawn %s.");

        add("generations_core.item.melody_flute.full_imbued1", "Fully imbued with the essence of %s.");
        add("generations_core.item.melody_flute.full_imbued2", "Shift right click on a %s");
        add("generations_core.item.melody_flute.full_imbued3", "to spawn %s.");

        add("generations_core.item.timeglass.wrongbiome", "You can only summon Celebi in a Flower Forest Biome");
        add("generations_core.item.timeglass.amount", "You've defeated %s Grass, Psychic, or Fairy Type Pokemon out of 100 in a Flower Forest Biome");

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
        add("generations_core.blocks.lootmode.once_per_player", "Permanent, limit 1 drop");
        add("generations_core.blocks.lootmode.timed", "Timed drops");
        add("generations_core.blocks.lootmode.once", "First come, first served");
        add("generations_core.blocks.lootmode.unlimited", "Permanent, unlimited drops");

        add("generations_core.enigma_biome", "Upon entering %s for the first, a fragment of a shard of an engima appears.");

        //Heatran
        addTooltip(GenerationsItems.LAVA_CRYSTAL, "HINT: You see a faint image of Heatran from within. You’ll need more, and an Orb.");
        addTooltip(GenerationsItems.MAGMA_CRYSTAL, "HINT: Against your better judgment, you feel a sudden urge to throw this crystal into lava (right-click)");

        //Deoxys
        addTooltip(METEORITE, "HINT: Strange, otherworldly energy is coming from this item. Try enchanting it and charging it by defeating Pokemon of a relevant type.");
        addTooltip(METEORITE_SHARD, "HINT: Maybe if you collect enough you could craft something from out of this world.");

        //
        //Celebi
        addTooltip(GenerationsItems.TIME_GLASS,  "HINT: Try charging it by defeating Pokemon of a relevant type. It may then react in a Flower Forest biome.");
        addTooltip(GenerationsItems.ORB, "HINT: This item is flowing with mysterious energy. It’s related to a number of legendary and mythical beings. Maybe you can craft something…");

        //Legendary Titans
        var string = "HINT: Try collecting all 4 parts to repair this key.";
        addTooltip(CRUMBLED_ROCK_KEY_1, string);
        addTooltip(CRUMBLED_ROCK_KEY_2, string);
        addTooltip(CRUMBLED_ROCK_KEY_3, string);
        addTooltip(CRUMBLED_ROCK_KEY_4, string);
        addTooltip(SHATTERED_ICE_KEY_1, string);
        addTooltip(CRUMBLED_ROCK_KEY_2, string);
        addTooltip(CRUMBLED_ROCK_KEY_3, string);
        addTooltip(CRUMBLED_ROCK_KEY_4, string);
        addTooltip(RUSTY_IRON_KEY_1, string);
        addTooltip(RUSTY_IRON_KEY_2, string);
        addTooltip(RUSTY_IRON_KEY_3, string);
        addTooltip(RUSTY_IRON_KEY_4, string);
        addTooltip(DISCHARGED_ELEKI_KEY_1, string);
        addTooltip(DISCHARGED_ELEKI_KEY_2, string);
        addTooltip(DISCHARGED_ELEKI_KEY_3, string);
        addTooltip(DISCHARGED_ELEKI_KEY_4, string);
        addTooltip(FRAGMENTED_DRAGO_KEY_1, string);
        addTooltip(FRAGMENTED_DRAGO_KEY_2, string);
        addTooltip(FRAGMENTED_DRAGO_KEY_3, string);
        addTooltip(FRAGMENTED_DRAGO_KEY_4, string);
        string = "HINT: This item appears to be related to one of the Regi’s. Find their shrine at a Snowpoint Temple.";
        addTooltip(ROCK_PEAK_KEY, string);
        addTooltip(ICEBERG_KEY, string);
        addTooltip(IRON_KEY, string);
        addTooltip(ELEKI_KEY, string);
        addTooltip(DRAGO_KEY, string);
        string = "HINT: Quiet vibrations of what sound like a faint screech are flowing into your ears. All you can make out are “Key” and “Spell”.";
        addTooltip(GenerationsShrines.REGICE_SHRINE, string);
        addTooltip(GenerationsShrines.REGIROCK_SHRINE, string);
        addTooltip(GenerationsShrines.REGISTEEL_SHRINE, string);
        addTooltip(GenerationsShrines.REGIDRAGO_SHRINE, string);
        addTooltip(GenerationsShrines.REGIELEKI_SHRINE, string);

        //Eon Duo
        addTooltip(ENIGMA_FRAGMENT, "HINT: You appear to gain one for each new biome discovered. If you collect enough, maybe you can craft something.");
        addTooltip(ENIGMA_SHARD, "HINT: You should explore more… Maybe with enough of these you could craft something related to the Eon Duo.");
        addTooltip(ENIGMA_STONE, "HINT: You should try capturing or defeating Psychic or Dragon Type Pokemon");

        //Weather Trio
        string = "HINT: This item appears to be related to the Weather Trio. Try Charging it by defeating Pokemon of a relevant type. Then, find a relevant shrine.";
        addTooltip(FADED_BLUE_ORB, string);
        addTooltip(FADED_RED_ORB, string);
        addTooltip(FADED_JADE_ORB, "HINT: This item appears to be related to the Weather Trio. Try charging it by defeating Pokemon of a relevant type. Then, go VERY high in altitude.");
        addTooltip(JADE_ORB, "HINT: This item can teach Dragon Ascent to a certain Pokemon.");

        //Lake Guardians
        addTooltip(SHARD_OF_EMOTION, "HINT: Keep fishing. With enough, you could craft something.");
        addTooltip(SHARD_OF_KNOWLEDGE, "HINT: Keep fishing. With enough, you could craft something.");
        addTooltip(SHARD_OF_WILLPOWER, "HINT: Keep fishing. With enough, you could craft something.");
        addTooltip(CRYSTAL_OF_EMOTION, "HINT: There’s even more to this strange item. Collect the other crystals and perhaps you could craft a special chain.");
        addTooltip(CRYSTAL_OF_KNOWLEDGE, "HINT: There’s even more to this strange item. Collect the other crystals and perhaps you could craft a special chain.");
        addTooltip(CRYSTAL_OF_WILLPOWER, "HINT: There’s even more to this strange item. Collect the other crystals and perhaps you could craft a special chain.");
        addTooltip(CRYSTAL_OF_EMOTION, "enchanted", "HINT: Try charging it by defeating Pokemon of a relevant type.");
        addTooltip(CRYSTAL_OF_KNOWLEDGE, "enchanted", "HINT: Try charging it by defeating Pokemon of a relevant type.");
        addTooltip(CRYSTAL_OF_WILLPOWER, "enchanted", "HINT: Try charging it by defeating Pokemon of a relevant type.");
        addTooltip(RUBY_ROD, "HINT: Fish… Lake… Guardian…");

        //Creation Trio
        addTooltip(RED_CHAIN, "HINT: It doesn’t seem to be reacting to anything. The Crystals used may have used up all their energy. Try enchanting it to give it some power.");
        addTooltip(RED_CHAIN, "enchanted", "“HINT: This item appears to be related to the Creation Trio, perhaps if you had their respected Orbs you could activate an altar.");
        addTooltip(ADAMANT_ORB, "HINT: This item appears to be related to the Creation Trio. If you crafted a Red Chain, you may be activate an Altar.");
        addTooltip(LUSTROUS_ORB, "HINT: This item appears to be related to the Creation Trio. If you crafted a Red Chain, you may be activate an Altar.");
        addTooltip(GRISEOUS_ORB, "HINT: This item appears to be related to the Creation Trio. If you crafted a Red Chain, you may be activate an Altar.");

        //Regigigas
        addTooltip(REGICE_ORB, "HINT: If you collect the other Orbs, Regigigas may be awoken");
        addTooltip(REGIROCK_ORB, "HINT: If you collect the other Orbs, Regigigas may be awoken");
        addTooltip(REGISTEEL_ORB, "HINT: If you collect the other Orbs, Regigigas may be awoken");
        addTooltip(REGIELEKI_ORB, "HINT: If you collect the other Orbs, Regigigas may be awoken");
        addTooltip(REGIDRAGO_ORB, "HINT: If you collect the other Orbs, Regigigas may be awoken");
        addTooltip(REGIGIGAS_SHRINE, "HINT: Images of all 5 Regi’s in the distant past begin to swarm into you. They’re playing. You feel an overwhelming sense of… nurturing? Regigigas clearly has no desire to be awakened unless you’ve awakened its friends.");

        //Lunar Duo
        addTooltip(LUNAR_SHRINE, "HINT: It seems empty, strangely like the feeling of a heartache. A summoning ritual is to occur here. A deep malevolent cry is suddenly heard in your left ear- “Souls”. Almost immediately afterwards, a higher-pitched angelic-like cry is heard in your right- “Souls”. Creepy.");
        addTooltip(LIGHT_CRYSTAL, "HINT: It’s guiding you to a pentagram. You’ll need 5 in total and prevail against the dark.");
        addTooltip(DARK_CRYSTAL, "HINT: It’s guiding you to a pentagram. You’ll need 5 in total and prevail against the light.");
        addTooltip(LIGHT_SOUL, "HINT: Cresselia appears faintly, you need more.");
        addTooltip(DARK_SOUL, "HINT:  Darkrai appears faintly, you need more.");

        //Sea Guardians
        addTooltip(WONDER_EGG, "HINT: Oh?");
        addTooltip(PHIONE_EGG, "HINT: Oh?");

        //Forces of Nature
        addTooltip(MIRROR, "HINT: Instead of seeing your reflection, you see Gems. They’re pink, blue, and white and laid out in a frame.");
        addTooltip(REVEAL_GLASS, "HINT: Rather than your reflection, it displays a small Abundant Shrine in the sky.");
        addTooltip(ABUNDANT_SHRINE, "HINT: You need something to reveal the secrets within.");

        //Tao Trio
        addTooltip(DRAGON_SOUL, "HINT: It screams. They seek their masters- the Tao Trio. An Orb is required to seal these souls. With a certain Gem, you could manipulate it towards a specific Tao.");
        addTooltip(LIGHT_STONE, "HINT: It isn’t reacting. More Dragons could do the trick.");
        addTooltip(DARK_STONE, "HINT: It isn’t reacting. More Dragons could do the trick.");
        addTooltip(DRAGON_STONE, "HINT: It isn’t reacting. More Dragons could do the trick.");
        addTooltip(TAO_TRIO_SHRINE, "HINT: It’s radiating a lifeless aura. It’s dead silent but you begin to see battle cries?  Even stranger, you begin to taste powerful blasts of soundwaves– attacks from the distant past. A fierce battle took place long ago. Suddenly, you know what needs to be done– collect Dragon Souls.");

        //Meloetta
        addTooltip(SHATTERED_RELIC_SONG_1, "HINT: This appears to be a piece of a music disc. It might be repairable if you obtain all the pieces.");
        addTooltip(SHATTERED_RELIC_SONG_2, "HINT: This appears to be a piece of a music disc. It might be repairable if you obtain all the pieces.");
        addTooltip(SHATTERED_RELIC_SONG_3, "HINT: This appears to be a piece of a music disc. It might be repairable if you obtain all the pieces.");
        addTooltip(SHATTERED_RELIC_SONG_4, "HINT: This appears to be a piece of a music disc. It might be repairable if you obtain all the pieces.");
        addTooltip(RELIC_SONG, "HINT: A special music box may play this melody differently.");
        addTooltip(MELOETTA_MUSIC_BOX, "HINT: You hear a wonderful melody from within. You need to hear more.”");
        //Zygarde
        addTooltip(ZYGARDE_CUBE, "A strange cube capable of storing up to 100 Zygarde Cells, as well as fusing them together to reconstruct the legendary Zygarde.");

        //Hoopa
        addTooltip(PRISON_BOTTLE_STEM, "HINT: You hear a voice within, guiding you. \"Free me! Make my rings with Gold Blocks\".");
        addTooltip(HOOPA_RING, "HINT: If you collect 6 and insert them on a Bottle Stem, you could create a Prison Bottle. A Bottle Stem may be created with Rubies, White Glazed Terracotta, and an Orb.");
        //Guardian Deities
        addTooltip(SPARKLING_SHARD, "HINT: A protective presence looms over this shard. Perhaps if you collect more you’ll get a stronger feeling of what to do.");
        addTooltip(SPARKLING_STONE, "HINT: Capturing Pokemon of a specific type to care for seems to cause a reaction.");

        //Magearna
        addTooltip(SOUL_HEART, "HINT: This item appears to hold the soul of a mysterious Pokemon. You could try creating a body for it in an RKS Machine.");

        //Melmetal
        addTooltip(MELTAN_BOX, "HINT: Meltan oddly find peace in this box. Collect an army colony of Meltan! For science, of course.");
        addTooltip(MELTAN_BOX_CHARGED, "HINT: Meltan oddly find peace in this box. Collect an army colony of Meltan! For science, of course.");

        //Hero Duo
        addTooltip(RUSTY_FRAGMENT, "HINT: Collect more Rusty Fragments from Aegislash to assist in crafting a Sword or Shield");
        addTooltip(RUSTY_SHIELD, "HINT: Steel Type Pokemon");
        addTooltip(RUSTY_SWORD, "HINT: Steel Type Pokemon");

        //Kubfu
        addTooltip(SCROLL_PAGE, "HINT: This item is given to masters of the Martial Arts. Gather more pages to complete the scroll and prove your worth.");
        addTooltip(SECRET_ARMOR_SCROLL, "HINT: A Legendary Pokemon has taken notice to you- continue to prove your worth by defeating more Fighting type Pokemon");

        //Steed Duo
        addTooltip(WHITE_MANE_HAIR, "HINT: This majestic steed requires a generous offering of carrots if you wish to lay your eyes upon them.");
        addTooltip(BLACK_MANE_HAIR, "HINT: This majestic steed requires a generous offering of carrots if you wish to lay your eyes upon them.");

        //Mew two
        addTooltip(MEW_DNA_FIBER, "HINT: DNA may be manipulated in an RKS Machine.");
        addTooltip(MEW_FOSSIL, "HINT: This may contain valuable DNA traces to recreate something in an RKS Machine.");

        //Legendary Beasts
        addTooltip(SACRED_ASH, "HINT: A sacred item capable of bringing back the dead.");
    }


    protected String getNameGens(RegistrySupplier<? extends ItemLike> item, String name) {
        name = name.substring(name.indexOf(":") + 1);  //Removes Mod Tag from front of name
        name = name.replace('_', ' ');
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        for (int i = 0; i < name.length(); i++)
            if (name.charAt(i) == ' ')
                name = name.substring(0, i + 1) + name.substring(i + 1, i + 2).toUpperCase() + name.substring(i + 2);

        name = name.replaceAll("Tm", "TM");
        if (name.contains("poke_brick"))
            return name.replace("Poke Brick", "PokeBrick");

        return name;
    }

    protected String getPokeBrickName(RegistrySupplier<? extends ItemLike> item, String name){
        return getNameGens(item, name).replace("Poke Brick", "PokeBrick");
    }

    public <T extends ItemLike> void addTooltip(RegistrySupplier<T> registrySupplier, String entry) {
        addTooltip(registrySupplier, null, entry);
    }

    public <T extends ItemLike> void addTooltip(RegistrySupplier<T> registrySupplier, String sub, String entry) {
        if(registrySupplier.get() instanceof LangTooltip lang) add(lang.tooltipId() + (sub != null ? "." + sub : ""), entry);
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
