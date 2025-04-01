package generations.gg.generations.core.generationscore.forge.datagen.generators.lang;

import com.cobblemon.mod.common.api.berry.Flavor;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.world.entity.GenerationsEntities;
import generations.gg.generations.core.generationscore.common.world.item.GenerationsArmor;
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.common.world.item.GenerationsTools;
import generations.gg.generations.core.generationscore.common.world.item.LangTooltip;
import generations.gg.generations.core.generationscore.common.world.item.curry.CurryType;
import generations.gg.generations.core.generationscore.common.world.level.block.*;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import static generations.gg.generations.core.generationscore.common.world.item.GenerationsItems.*;
import static generations.gg.generations.core.generationscore.common.world.level.block.GenerationsShrines.*;

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

        });
        addItemEntries(GenerationsItems.BADGES, this::getNameGens, (item, function) -> {});
        addItemEntries(GenerationsItems.RIBBONS, this::getNameGens, (item, function) -> {});
        addItemEntries(GenerationsItems.UNIMPLEMENTED, this::getNameGens, (item, function) -> add(item.get().asItem().getDescriptionId() + ".desc", "Not currently implemented"));

        addItemEntries(GenerationsItems.CUISINE, this::getNameGens, (item, function) -> {});
        addItemEntries(GenerationsItems.NATURAL, this::getNameGens, (item, function) -> {});
        addItemEntries(GenerationsItems.RESTORATION, this::getNameGens, (item, function) -> {});
        addItemEntries(GenerationsItems.PLAYER_ITEMS, this::getNameGens, (item, function) -> {
            var item1 = item.get();

            if(item1 instanceof RecordItem) {
                add(item.get().asItem().getDescriptionId() + ".desc", "GlitchxCity - " + function.apply(item, item.getId().toString().replace("_disc", "")));
            }
        });
        addItemEntries(GenerationsItems.HELD_ITEMS, this::getNameGens, (item, function) -> {});
        addItemEntries(GenerationsItems.LEGENDARY_ITEMS, this::getNameGens, (item, function) -> {});
        addItemEntries(GenerationsItems.UTILITY, this::getNameGens, (item, function) -> {
            var item1 = item.get();

            if(item1 instanceof RecordItem) {
                add(item.get().asItem().getDescriptionId() + ".desc", "GlitchxCity - " + function.apply(item, item.getId().toString().replace("_disc", "")));
            }
        });
        addItemEntries(GenerationsItems.VALUABLES, this::getNameGens, (item, function) -> {});
        addItemEntries(GenerationsItems.FORM_ITEMS, this::getNameGens, (item, function) -> {});
        addItemEntries(GenerationsItems.BUILDING_BLOCKS, this::getNameGens, (item, function) -> {});
        addItemEntries(GenerationsItems.POKEMAIL, this::getNameGens, (item, function) -> {});

        //Manually add Creative Tabs
        add("itemGroup.generations_core.restoration", "Restoration");
        add("itemGroup.generations_core.tms", "TMs");
        add("itemGroup.generations_core.awards", "Awards");
        add("itemGroup.generations_core.held_items", "Held Items");
        add("itemGroup.generations_core.player_items", "Player Items");
        add("itemGroup.generations_core.legendary_items", "Legendary Items");
        add("itemGroup.generations_core.building_blocks", "Building Blocks");
        add("itemGroup.generations_core.decorations", "Decorations");
        add("itemGroup.generations_core.utility", "Utility");
        add("itemGroup.generations_core.form_items", "Form Items");
        add("itemGroup.generations_core.pokemail", "Pokemail");
        add("itemGroup.generations_core.valuables", "Valuables");
        add("itemGroup.generations_core.pokedolls", "PokeDolls");
        add("itemGroup.generations_core.cuisine", "Cuisine");
        add("itemGroup.generations_core.unimplemented", "Unimplemented");
        add("itemGroup.generations_core.shrines", "Shrines");

        add("container.melody_flute", "Melody Flute");
        add("container.trashcan", "Trash Can");
        add("container.shaderoot_carrot", "Shaderoot Carrot");
        add("container.iceroot_carrot", "Iceroot Carrot");

        add("container.poke_walkmon", "Poke Walkmon");
        add("container.great_walkmon", "Great Walkmon");
        add("container.ultra_walkmon", "Ultra Walkmon");
        add("container.master_walkmon", "Master Walkmon");

        add("container.pokeball_chest", "Pokeball Chest");
        add("container.greatball_chest", "Greatball Chest");
        add("container.ultraball_chest", "Ultraball Chest");
        add("container.masterball_chest", "Masterball Chest");
        add("container.hi_tech_earbuds", "Hi Tech Earbuds");
        add("container.charge_stone_furnace", "Charge Stone Furnace");
        add("container.charge_stone_blast_furnace", "Charge Stone Blast Furnace");
        add("container.charge_stone_smoker", "Charge Stone Smoker");
        add("container.volcanic_stone_furnace", "Volcanic Stone Furnace");
        add("container.volcanic_stone_blast_furnace", "Volcanic Stone Blast Furnace");
        add("container.volcanic_stone_smoker", "Volcanic Stone Smoker");
        add("container.cooking_pot", "Cooking Pot");
        add("container.box", "Box");


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
        add("item.generations_core.enigma_fragment.desc", "A mysterious item gained upon discovering new biomes.");

        //TR/TM lines
        add("move.cantlearn", "%s can't learn %s.");
        add("move.alreadyknows", "%s already knows %s.");
        add("move.learned", "%s has learned %s.");
        add("move.doesntexist", "The move %s doesn't exists so %s couldn't learn it.");
        add("move.newmove1", "1, 2, and... Ta da!");
        add("move.newmove2", "%s forgot %s!");
        add("move.newmove3", "...and learned %s!");

        //Temp Cobblemon
        add("cobblemon.move.upperhand", "Upper Hand");
        add("cobblemon.move.upperhand.name", "Upper Hand");
        add("cobblemon.move.upperhand.desc", "The user strikes with the heel of their palm, causing the target to flinch. Fails if the target is not readying a priority move.");
        add("cobblemon.move.hardpress", "Hard Press");
        add("cobblemon.move.hardpress.name", "Hard Press");
        add("cobblemon.move.hardpress.desc", "The target is crushed with an arm, a claw, or the like to inflict damage. The more HP the target has left, the greater the power.");
        add("cobblemon.move.temperflare", "Temper Flare");
        add("cobblemon.move.temperflare.name", "Temper Flare");
        add("cobblemon.move.temperfale.desc", "Spurred by desperation, the user attacks the target. This power is doubled if the previous move failed.");
        add("cobblemon.move.dragoncheer", "Dragon Cheer");
        add("cobblemon.move.dragoncheer.name", "Dragon Cheer");
        add("cobblemon.move.dragoncheer.desc", "The user cheers a draconic cry to raise its allies critical hit chance. This rouses Dragon types more.");
        add("cobblemon.move.syrupbomb", "Syrup Bomb");
        add("cobblemon.move.syrupbomb.name", "Syrup Bomb");
        add("cobblemon.move.syrupbomb.desc", "Spews an explosion of sticky syrup onto the target and causes their Speed to drop each turn for three turns.");
        add("cobblemon.move.ficklebeam", "Fickle Beam");
        add("cobblemon.move.ficklebeam.name", "Fickle Beam");
        add("cobblemon.move.ficklebeam.desc", "Shoots a beam of light to deal damage. Sometimes all the user's heads shoot beams in unison, doubling the power.");
        add("cobblemon.move.alluringvoice", "Alluring Voice");
        add("cobblemon.move.alluringvoice.name", "Alluring Voice");
        add("cobblemon.move.alluringvoice.desc", "The user attacks with its angelic voice, and also confuses the target if its stats were boosted during the turn.");
        add("cobblemon.move.psychicnoise", "Psychic Noise");
        add("cobblemon.move.psychicnoise.name", "Psychic Noise");
        add("cobblemon.move.psychicnoise.desc", "The user attacks with unpleasant sound waves, preventing the target from recovering HP through moves, abilities, or held items for two turns.");
        add("cobblemon.move.supercellslam", "Supercell Slam");
        add("cobblemon.move.supercellslam.name", "Supercell Slam");
        add("cobblemon.move.supercellslam.desc", "The user electrifies its body and drops onto the target to inflict damage. If this move misses, the user takes damage instead.");
        add("cobblemon.species.ponytagalar.name", "Galarian Ponyta");

        //Posters
        add("painting.generations_core.blue_poster.author", "Pokemon");
        add("painting.generations_core.blue_poster.title", "Blue Poster");
        add("painting.generations_core.blue_poster_sprite.author", "Pokemon");
        add("painting.generations_core.blue_poster_sprite.title", "Blue Poster Sprite");
        add("painting.generations_core.blue_scroll.author", "Pokemon");
        add("painting.generations_core.blue_scroll.title", "Blue Scroll");
        add("painting.generations_core.clefairy_poster_sprite.author", "Pokemon");
        add("painting.generations_core.clefairy_poster_sprite.title", "Clefairy Poster");
        add("painting.generations_core.cute_poster.author", "Pokemon");
        add("painting.generations_core.cute_poster.title", "Cute Poster");
        add("painting.generations_core.cute_poster_sprite.author", "Pokemon");
        add("painting.generations_core.cute_poster_sprite.title", "Cute Poster Sprite");
        add("painting.generations_core.dads_scroll.author", "Pokemon");
        add("painting.generations_core.dads_scroll.title", "Dads Scroll");
        add("painting.generations_core.green_poster.author", "Pokemon");
        add("painting.generations_core.green_poster.title", "Green Poster");
        add("painting.generations_core.green_poster_sprite.author", "Pokemon");
        add("painting.generations_core.green_poster_sprite.title", "Green Poster Sprite");
        add("painting.generations_core.green_scroll.author", "Pokemon");
        add("painting.generations_core.green_scroll.title", "Green Scroll");
        add("painting.generations_core.jigglypuff_poster_sprite.author", "Pokemon");
        add("painting.generations_core.jigglypuff_poster_sprite.title", "Jigglypuff");
        add("painting.generations_core.kiss_poster_sprite.author", "Pokemon");
        add("painting.generations_core.kiss_poster_sprite.title", "Kiss Poster Sprite");
        add("painting.generations_core.long_poster.author", "Pokemon");
        add("painting.generations_core.long_poster.title", "Long Poster");
        add("painting.generations_core.long_poster_sprite.author", "Pokemon");
        add("painting.generations_core.long_poster_sprite.title", "Long Poster Sprite");
        add("painting.generations_core.national_award.author", "Pokemon");
        add("painting.generations_core.national_award.title", "National Award");
        add("painting.generations_core.pika_poster.author", "Pokemon");
        add("painting.generations_core.pika_poster.title", "Pika Poster");
        add("painting.generations_core.pika_poster_sprite.author", "Pokemon");
        add("painting.generations_core.pika_poster_sprite.title", "Pika Poster Sprite");
        add("painting.generations_core.pikachu_poster_sprite.author", "Pokemon");
        add("painting.generations_core.pikachu_poster_sprite.title", "Pikachu Poster Sprite");
        add("painting.generations_core.poke_ball_poster.author", "Pokemon");
        add("painting.generations_core.poke_ball_poster.title", "Poke Ball Poster");
        add("painting.generations_core.red_poster.author", "Pokemon");
        add("painting.generations_core.red_poster.title", "Red Poster");
        add("painting.generations_core.red_poster_sprite.author", "Pokemon");
        add("painting.generations_core.red_poster_sprite.title", "Red Poster Sprite");
        add("painting.generations_core.red_scroll.author", "Pokemon");
        add("painting.generations_core.red_scroll.title", "Red Scroll");
        add("painting.generations_core.regional_award.author", "Pokemon");
        add("painting.generations_core.regional_award.title", "Regional Award");
        add("painting.generations_core.sea_poster.author", "Pokemon");
        add("painting.generations_core.sea_poster.title", "Sea Poster");
        add("painting.generations_core.sea_poster_sprite.author", "Pokemon");
        add("painting.generations_core.sea_poster_sprite.title", "Sea Poster Sprite");
        add("painting.generations_core.sky_poster.author", "Pokemon");
        add("painting.generations_core.sky_poster.title", "Sky Poster");
        add("painting.generations_core.sky_poster_sprite.author", "Pokemon");
        add("painting.generations_core.sky_poster_sprite.title", "Sky Poster Sprite");
        add("painting.generations_core.time_travel_award.author", "Pokemon");
        add("painting.generations_core.time_travel_award.title", "Time Travel Award");
        add("painting.generations_core.town_map_sprite.author", "Pokemon");
        add("painting.generations_core.town_map_sprite.title", "Town Map Sprite");

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

        //Heatran
        addTooltip(GenerationsItems.LAVA_CRYSTAL, "§7HINT: You see a faint image of Heatran from within. You’ll need more, and an Orb.");
        addTooltip(GenerationsItems.MAGMA_CRYSTAL, "§7HINT: Against your better judgment, you feel a sudden urge to throw this crystal into lava (right-click)");

        //Deoxys
        addTooltip(METEORITE, "§7HINT: Strange, otherworldly energy is coming from this item. Try enchanting it and charging it by defeating Pokemon of a relevant type.");
        addTooltip(METEORITE_SHARD, "§7HINT: Maybe if you collect enough you could craft something from out of this world.");

        //
        //Celebi
        add("generations_core.timeglass.wrongbiome", "§7This item only works in a Flower Forest biome!");
        add("generations_core.timeglass.amount", "§7Grass, Psychic, or Fairy-Type Pokemon remaining: ");
        addTooltip(GenerationsItems.TIME_GLASS,  "§7HINT: Try charging it by defeating Pokemon of a relevant type. It may then react in a Flower Forest biome.");
        addTooltip(GenerationsItems.ORB, "§7HINT: This item is flowing with mysterious energy. It’s related to a number of legendary and mythical beings. Maybe you can craft something…");

        //Legendary Titans
        var string = "§7HINT: Try collecting all 4 parts to repair this key.";
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
        string = "§7HINT: This item appears to be related to one of the Regi’s. Find their shrine at a Snowpoint Temple.";
        addTooltip(ROCK_PEAK_KEY, string);
        addTooltip(ICEBERG_KEY, string);
        addTooltip(IRON_KEY, string);
        addTooltip(ELEKI_KEY, string);
        addTooltip(DRAGO_KEY, string);
        string = "§7HINT: Quiet vibrations of what sound like a faint screech are flowing into your ears. All you can make out are “Key” and “Spell”.";
        addTooltip(GenerationsShrines.REGICE_SHRINE, string);
        addTooltip(GenerationsShrines.REGIROCK_SHRINE, string);
        addTooltip(GenerationsShrines.REGISTEEL_SHRINE, string);
        addTooltip(GenerationsShrines.REGIDRAGO_SHRINE, string);
        addTooltip(GenerationsShrines.REGIELEKI_SHRINE, string);

        //Eon Duo
        addTooltip(ENIGMA_FRAGMENT, "§7HINT: You appear to gain one for each new biome discovered. If you collect enough, maybe you can craft something.");
        addTooltip(ENIGMA_SHARD, "§7HINT: You should explore more… Maybe with enough of these you could craft something related to the Eon Duo.");
        addTooltip(ENIGMA_STONE, "§7HINT: You should try capturing or defeating Psychic or Dragon Type Pokemon");

        //Weather Trio
        string = "§7HINT: This item appears to be related to the Weather Trio. Try Charging it by defeating Pokemon of a relevant type. Then, find a relevant shrine.";
        addTooltip(FADED_BLUE_ORB, string);
        addTooltip(FADED_RED_ORB, string);
        addTooltip(FADED_JADE_ORB, "§7HINT: This item appears to be related to the Weather Trio. Try charging it by defeating Pokemon of a relevant type. Then, go VERY high in altitude.");
        addTooltip(JADE_ORB, "§7HINT: This item can teach Dragon Ascent to a certain Pokemon.");

        //Lake Guardians
        addTooltip(SHARD_OF_EMOTION, "§7HINT: Keep fishing. With enough, you could craft something.");
        addTooltip(SHARD_OF_KNOWLEDGE, "§7HINT: Keep fishing. With enough, you could craft something.");
        addTooltip(SHARD_OF_WILLPOWER, "§7HINT: Keep fishing. With enough, you could craft something.");
        addTooltip(CRYSTAL_OF_EMOTION, "§7HINT: There’s even more to this strange item. Collect the other crystals and perhaps you could craft a special chain.");
        addTooltip(CRYSTAL_OF_KNOWLEDGE, "§7HINT: There’s even more to this strange item. Collect the other crystals and perhaps you could craft a special chain.");
        addTooltip(CRYSTAL_OF_WILLPOWER, "§7HINT: There’s even more to this strange item. Collect the other crystals and perhaps you could craft a special chain.");
        addTooltip(CRYSTAL_OF_EMOTION, "enchanted", "§7HINT: Try charging it by defeating Pokemon of a relevant type.");
        addTooltip(CRYSTAL_OF_KNOWLEDGE, "enchanted", "§7HINT: Try charging it by defeating Pokemon of a relevant type.");
        addTooltip(CRYSTAL_OF_WILLPOWER, "enchanted", "§7HINT: Try charging it by defeating Pokemon of a relevant type.");
        addTooltip(RUBY_ROD, "§7HINT: Fish… Lake… Guardian…");
        add("item.generations_core.ruby_rod.fate_defied", "The Ruby Rod shatters as it resists its altered fate");
        //Creation Trio
        addTooltip(RED_CHAIN, "§7HINT: It doesn’t seem to be reacting to anything. The Crystals used may have used up all their energy. Try enchanting it to give it some power.");
        addTooltip(RED_CHAIN, "enchanted", "“HINT: This item appears to be related to the Creation Trio, perhaps if you had their respected Orbs you could activate an altar.");
        addTooltip(ADAMANT_ORB, "§7HINT: This item appears to be related to the Creation Trio. If you crafted a Red Chain, you may be activate an Altar.");
        addTooltip(LUSTROUS_ORB, "§7HINT: This item appears to be related to the Creation Trio. If you crafted a Red Chain, you may be activate an Altar.");
        addTooltip(GRISEOUS_ORB, "§7HINT: This item appears to be related to the Creation Trio. If you crafted a Red Chain, you may be activate an Altar.");

        //Regigigas
        addTooltip(REGICE_ORB, "§7HINT: If you collect the other Orbs, Regigigas may be awoken");
        addTooltip(REGIROCK_ORB, "§7HINT: If you collect the other Orbs, Regigigas may be awoken");
        addTooltip(REGISTEEL_ORB, "§7HINT: If you collect the other Orbs, Regigigas may be awoken");
        addTooltip(REGIELEKI_ORB, "§7HINT: If you collect the other Orbs, Regigigas may be awoken");
        addTooltip(REGIDRAGO_ORB, "§7HINT: If you collect the other Orbs, Regigigas may be awoken");
        addTooltip(REGIGIGAS_SHRINE, "§7HINT: Images of all 5 Regi’s in the distant past begin to swarm into you. They’re playing. You feel an overwhelming sense of… nurturing? Regigigas clearly has no desire to be awakened unless you’ve awakened its friends.");

        //Lunar Duo
        addTooltip(LUNAR_SHRINE, "§7HINT: It seems empty, strangely like the feeling of a heartache. A summoning ritual is to occur here. A deep malevolent cry is suddenly heard in your left ear- “Souls”. Almost immediately afterwards, a higher-pitched angelic-like cry is heard in your right- “Souls”. Creepy.");
        addTooltip(LIGHT_CRYSTAL, "§7HINT: It’s guiding you to a pentagram. You’ll need 5 in total and prevail against the dark.");
        addTooltip(DARK_CRYSTAL, "§7HINT: It’s guiding you to a pentagram. You’ll need 5 in total and prevail against the light.");
        addTooltip(LIGHT_SOUL, "§7HINT: Cresselia appears faintly, you need more.");
        addTooltip(DARK_SOUL, "§7HINT:  Darkrai appears faintly, you need more.");

        //Sea Guardians
        addTooltip(WONDER_EGG, "§7HINT: Oh?");
        addTooltip(PHIONE_EGG, "§7HINT: Oh?");

        //Forces of Nature
        addTooltip(MIRROR, "§7HINT: Instead of seeing your reflection, you see Gems. They’re pink, blue, and white and laid out in a frame.");
        addTooltip(REVEAL_GLASS, "§7HINT: Rather than your reflection, it displays a small Abundant Shrine in the sky.");
        addTooltip(ABUNDANT_SHRINE, "§7HINT: You need something to reveal the secrets within.");

        //Tao Trio
        addTooltip(DRAGON_SOUL, "§7HINT: It screams. They seek their masters- the Tao Trio. An Orb is required to seal these souls. With a certain Gem, you could manipulate it towards a specific Tao.");
        addTooltip(LIGHT_STONE, "§7HINT: It isn’t reacting. More Dragons could do the trick.");
        addTooltip(DARK_STONE, "§7HINT: It isn’t reacting. More Dragons could do the trick.");
        addTooltip(DRAGON_STONE, "§7HINT: It isn’t reacting. More Dragons could do the trick.");
        addTooltip(TAO_TRIO_SHRINE, "§7HINT: It’s radiating a lifeless aura. It’s dead silent but you begin to see battle cries?  Even stranger, you begin to taste powerful blasts of soundwaves– attacks from the distant past. A fierce battle took place long ago. Suddenly, you know what needs to be done– collect Dragon Souls.");

        //Meloetta
        addTooltip(SHATTERED_RELIC_SONG_1, "§7HINT: This appears to be a piece of a music disc. It might be repairable if you obtain all the pieces.");
        addTooltip(SHATTERED_RELIC_SONG_2, "§7HINT: This appears to be a piece of a music disc. It might be repairable if you obtain all the pieces.");
        addTooltip(SHATTERED_RELIC_SONG_3, "§7HINT: This appears to be a piece of a music disc. It might be repairable if you obtain all the pieces.");
        addTooltip(SHATTERED_RELIC_SONG_4, "§7HINT: This appears to be a piece of a music disc. It might be repairable if you obtain all the pieces.");
        addTooltip(RELIC_SONG, "§7HINT: A special music box may play this melody differently.");
        addTooltip(INERT_RELIC_SONG, "Relic Song - Shota Kageyama.");
        add("item.generations_core.inert_relic_song.desc", "Relic Song - Shota Kageyama");

        this.add("item.minecraft.smithing_template.ultrite_upgrade.ingredients", "Ultrite Ingot");
        addTooltip(MELOETTA_MUSIC_BOX, "§7HINT: You hear a wonderful melody from within. You need to hear more.”");
        //Zygarde
        addTooltip(ZYGARDE_CUBE, "cell_add", "Zygarde Cell collected!");
        addTooltip(ZYGARDE_CUBE, "cell_overflow", "Due to Zygarde Cube being full, the zygarde cell atomizes.");
        addTooltip(ZYGARDE_CUBE, "cell_full", "The Zygarde Cube is full!");
        addTooltip(ZYGARDE_CUBE, "lore1", "A strange cube capable of storing up to 100 Zygarde Cells,");
        addTooltip(ZYGARDE_CUBE, "lore2", "as well as fusing them together to reconstruct the legendary Zygarde.");
        addTooltip(ZYGARDE_CUBE, "lore3", "Collect Zygarde Cells to summon the balance.");
        addTooltip(ZYGARDE_CUBE, "lore4", "Cells collected: %s/%s");

        addEntityType(GenerationsEntities.ZYGARDE_CELL, "Zygarde Cells");
        add("gui.zygarde_cube", "Zygarde Cube");
        add("gui.zygarde_cube.select", "Merge Zygarde Cells");
        add("gui.zygarde_cube.merge_10.name", "Create 10%");
        add("gui.zygarde_cube.merge_10.accept", "Your Cells merged to form a Zygarde 10%!");
        add("gui.zygarde_cube.merge_50.name", "Create 50%");
        add("gui.zygarde_cube.merge_50.accept", "Your Cells merged to form a Zygarde 50%!");
        add("gui.zygarde_cube.merge_10_power_construct.name", "Create 10% with Power Construct");
        add("gui.zygarde_cube.merge_10_power_construct.accept", "Your Cells merged to form a Zygarde 10% with Power Construct!");
        add("gui.zygarde_cube.merge_50_power_construct.name", "Create 50% with Power Construct");
        add("gui.zygarde_cube.merge_50_power_construct.accept", "Your Cells merged to form a Zygarde 50% with Power Construct!");
        add("gui.zygarde_cube.merge_10_with_40_to_50.name", "Convert 10% to 50% with Power Construct");
        add("gui.zygarde_cube.merge_10_with_40_to_50.accept", "Your Zygarde 10% was combined with 40 Cells to create a Zygarde 50%!");
        add("gui.zygarde_cube.merge_10_with_90_to_10_power_construct.name", "Convert 10% to 10% Power Construct");
        add("gui.zygarde_cube.merge_10_with_90_to_10_power_construct.accept", "Your Zygarde 10% was combined with 90 Cells to create a Zygarde 10% with Power Construct!");
        add("gui.zygarde_cube.merge_10_with_90_to_50_power_construct.name", "Convert 10% to 50% with Power Construct");
        add("gui.zygarde_cube.merge_10_with_90_to_50_power_construct.accept", "Your Zygarde 10% was combined with 90 Cells to create a Zygarde 50% with Power Construct!");
        add("gui.zygarde_cube.merge_50_with_50_to_50_power_construct.name", "Convert 50% to 50% with Power Construct");
        add("gui.zygarde_cube.merge_50_with_50_to_50_power_construct.accept", "Your Zygarde 50% was combined with 50 Cells to create a Zygarde 50% with Power Construct!");

        //Hoopa
        addTooltip(PRISON_BOTTLE_STEM, "§7HINT: You hear a voice within, guiding you. \"Free me! Make my rings with Gold Blocks\".");
        addTooltip(HOOPA_RING, "§7HINT: If you collect 6 and insert them on a Bottle Stem, you could create a Prison Bottle. A Bottle Stem may be created with Rubies, White Glazed Terracotta, and an Orb.");
        //Guardian Deities
        addTooltip(SPARKLING_SHARD, "§7HINT: A protective presence looms over this shard. Perhaps if you collect more you’ll get a stronger feeling of what to do.");
        addTooltip(SPARKLING_STONE, "§7HINT: Capturing Pokemon of a specific type to care for seems to cause a reaction.");

        //Magearna
        addTooltip(SOUL_HEART, "§7HINT: This item appears to hold the soul of a mysterious Pokemon. You could try creating a body for it in an RKS Machine.");

        //Melmetal
        addTooltip(MELTAN_BOX, "§7HINT: Meltan oddly find peace in this box. Collect an army colony of Meltan! For science, of course.");
        addTooltip(MELTAN_BOX_CHARGED, "§7HINT: Meltan oddly find peace in this box. Collect an army colony of Meltan! For science, of course.");

        //Hero Duo
        addTooltip(RUSTY_FRAGMENT, "§7HINT: Collect more Rusty Fragments from Aegislash to assist in crafting a Sword or Shield");
        addTooltip(RUSTY_SHIELD, "§7HINT: Steel Type Pokemon");
        addTooltip(RUSTY_SWORD, "§7HINT: Steel Type Pokemon");

        //Kubfu
        addTooltip(SCROLL_PAGE, "§7HINT: This item is given to masters of the Martial Arts. Gather more pages to complete the scroll and prove your worth.");
        addTooltip(SECRET_ARMOR_SCROLL, "§7HINT: A Legendary Pokemon has taken notice to you- continue to prove your worth by defeating more Fighting type Pokemon");

        //Steed Duo
        addTooltip(WHITE_MANE_HAIR, "§7HINT: This majestic steed requires a generous offering of carrots if you wish to lay your eyes upon them.");
        addTooltip(BLACK_MANE_HAIR, "§7HINT: This majestic steed requires a generous offering of carrots if you wish to lay your eyes upon them.");

        //Mew two
        addTooltip(MEW_DNA_FIBER, "§7HINT: DNA may be manipulated in an RKS Machine.");
        addTooltip(MEW_FOSSIL, "§7HINT: This may contain valuable DNA traces to recreate something in an RKS Machine.");
        addTooltip(SACRED_ASH, "§7HINT: A sacred item capable of bringing back the dead.");

        //Legendary Beasts
        addTooltip(SACRED_ASH, "§HINT: A sacred item capable of bringing back the dead.");

        add("generations_core.special.shadow.already", "%s heart is already closed.");
        add("generations_core.special.shadow.success", "%s heart has been closed.");
        add("generations_core.special.shadow.failure", "%s refused to clsoe its heart.");

        add("generations_core.ui.interact.head_pat", "Head Pat");
        add("generations_core.ability.formchange", "%s changed form!");

        add("generations_core.pokemon.fused", "%s was fused with %s.");
        add("generations_core.pokemon.defused", "%s was defused from %s.");
        add("generations_core.pokemon.encoded", "%s was encoded.");

        addEntityType(GenerationsEntities.STATUE_ENTITY, "Statue");

        add("item.unimplemented_until_1_dot_6_cobblemon", "Currently unimplemented. Will be looked in 1.6+ cobblemon.");

        add("generations_core.ui.dna_fibers_extracted", "DNA Fibers");

        add("generations_core.pokemon.extracted_dna_fibers_max", "You have extracted all DNA fibers you could from %s.");
        add("generations_core.pokemon.extracted_dna_fiber_succeed", "DNA fiber extraction from %s successful!");
        add("generations_core.pokemon.extracted_fibers_max", "Maximum amount of DNA fibers have been extracted from %s.");

        for(var flavor : Flavor.values()) {
            add("enum.flavor." + flavor.name().toLowerCase(), this.getNameGens(null, flavor.name().toLowerCase()));
        }

        for(var type : CurryType.values()) {
            add("generations_core.curry." + type.name().toLowerCase(), this.getNameGens(null, type.getSerializedName()));
        }

        this.add(ULTRITE_UPGRADE_SMITHING_TEMPLATE.get(), "Ultrite Upgrade Smithing Template");

        this.add("item.minecraft.smithing_template.ultrite_upgrade.ingredients", "Ultrite Ingot");
        this.add("item.minecraft.smithing_template.ultrite_upgrade.base_slot_description", "Add Ultrite armor, weapon, or tool");
        this.add("item.minecraft.smithing_template.ultrite_upgrade.additions_slot_description", "Add Ultrite Ingot");
        this.add("item.minecraft.smithing_template.ultrite_upgrade.applies_to", "Netherite Equipment");
        this.add("upgrade.minecraft.ultrite_upgrade", "Ultrite Upgrade Smithing Template");

        this.add("gui.recipe_viewer.category.rks_machine", "RKS Machine");
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

        if(name.equalsIgnoreCase("hdtv")) name = name.toUpperCase();

        return name;
    }

    protected String getPokeBrickName(RegistrySupplier<? extends ItemLike> item, String name){
        return getNameGens(item, name).replace("Poke Brick", "PokeBrick");
    }

    public <T extends ItemLike> void addTooltip(RegistrySupplier<T> registrySupplier, String entry) {
        addTooltip(registrySupplier, null, entry);
    }

    public <T extends ItemLike> void addTooltip(RegistrySupplier<T> registrySupplier, String sub, String entry) {
        if(registrySupplier.get().asItem() instanceof LangTooltip lang) add(lang.tooltipId() + (sub != null ? "." + sub : ""), entry);
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
