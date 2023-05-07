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
        //addBlockEntries(GenerationsBlocks.POKE_BRICKS.getEntries(), this::getPokeBrickName);
       // addBlockEntries(GenerationsBlocks.MARBLE.getEntries(), this::getNameGens);
        //addBlockEntries(GenerationsBlocks.STONE.getEntries(), this::getNameGens);
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

        // Battle Gui Entries
        add("button.pixelmon.battle.fight", "Fight");
        add("button.pixelmon.battle.bag", "Bag");
        add("button.pixelmon.battle.pokemon", "Party");
        add("button.pixelmon.battle.run", "Run");

        //Manually add Creative Tabs
        add("item_group.pokeballs", "Poké Balls");
        add("item_group.restoration", "Restoration");
        add("item_group.tms_trs", "TMs/TRs");
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

        //TR/TM lines
        add("move.cantlearn", "%s can't learn %s.");
        add("move.alreadyknows", "%s already knows %s.");
        add("move.learned", "%s has learned %s.");
        add("move.doesntexist", "The move %s doesn't exists so %s couldn't learn it.");
        add("move.newmove1", "1, 2, and... Ta da!");
        add("move.newmove2", "%s forgot %s!");
        add("move.newmove3", "...and learned %s!");

        //commands
        add("pixelmon.command.cosmetic.added", "Successfully given %s the cosmetic, %s");
        add("pixelmon.command.cosmetic.alreadyowned", "%s already owns the cosmetic, %s");
        add("pixelmon.command.cosmetic.unknowntype", "Could not find a Cosmetic with the name, %s");
        add("pixelmon.command.general.nopokemontospawn", "You need to specify a Pokemon to spawn.");
        add("pixelmon.command.schematic.novalidwanddetected", "You aren't holding a Zone Wand or don't have both points selected.");
        add("pixelmon.command.megaring.notifygave", "§7Successfully given §#FCFDA4Me§#BCFFC2ga §#BFFCFFRi§#B4A4F9ng §7to §#CCD3E1%s");
        add("pixelmon.command.shinycharm.notifygave", "§7Successfully given §#98F0F1Shiny Charm §7to §#9AF866%s");
        add("pixelmon.command.battle.battlebetween", "Battle between %s and %s started!");
        add("pixelmon.command.battle.cannotchallenge", "Cannot challenge %s while they are in battle!");
        add("pixelmon.command.battle.dimension", "Participants are not in same dimension.");
        add("pixelmon.command.battle.sameperson", "Both participants cannot be the same person.");
        add("pixelmon.command.battle.started", "Battle between %s and %s started!");
        add("pixelmon.command.battle.nopokemon", "%s has no Pokémon that can battle!");
        add("pixelmon.command.battle2.duplicate", "Can't have a duplicate player in the battle.");
        add("pixelmon.command.battle2.first", "First participant must be a player.");
        add("pixelmon.command.battle2.invalid", "Participant %d was invalid. Please try again.");
        add("pixelmon.command.battle2.level", "Please provide a level!");
        add("pixelmon.command.battle2.cantstart", "Invalid participants.");
        add("pixelmon.command.breed.giveegg", "%s and %s made an Egg!");
        add("pixelmon.command.breed.invalidslot", "Slot argument %s is invalid!");
        add("pixelmon.command.breed.notcompatible", "%s and %s are not compatible!");
        add("pixelmon.command.breed.novalidpairs", "No compatible breeding pairs were found in %s's party.");
        add("pixelmon.command.breed.nullslot", "There is no Pokémon in slot %s!");
        add("pixelmon.command.checkspawns.betterspawningisoff", "Better Spawning is off. Turn it on with /spawning beta.");
        add("pixelmon.command.checkspawns.spawns.inthisarea", "Spawns in this area, %s");
        add("pixelmon.command.checkspawns.spawns.atthisposition", "Spawns at this position, %s");
        add("pixelmon.command.copyToDB.disabled", "Saving to database is not enabled in the config!");
        add("pixelmon.command.endbattle.notinbattle", "%s is not in a battle.");
        add("pixelmon.command.endbattle.endbattle", "Battle ended successfully.");
        add("pixelmon.command.endbattle.permissionself", "You don't have permission to end your battle.");
        add("pixelmon.command.endbattle.permissionother", "You don't have permission to end someone else's battle.");
        add("pixelmon.command.freeze.frozen", "Pokémon are frozen in place!");
        add("pixelmon.command.freeze.unfrozen", "Pokémon are unfrozen!");
        add("pixelmon.command.general.cheater", "Cheater!");
        add("pixelmon.command.general.lvlerror", "Error in level.");
        add("pixelmon.command.general.notingame", "%s is not in the game!");
        add("pixelmon.command.general.invalid", "Invalid arguments!");
        add("pixelmon.command.general.invalidplayer", "Invalid name! Try again.");
        add("pixelmon.command.general.inbattle", "You can't use this command while in a battle!");
        add("pixelmon.command.general.needtobeplayer", "You must be a player to use this command!");
        add("pixelmon.command.give.givesuccess", "Successfully gave %s a %s!");
        add("pixelmon.command.give.notifygive", "%s gave %s a %s!");
        add("pixelmon.command.give.givesuccessegg", "Successfully gave %s a %s Egg!");
        add("pixelmon.command.give.notifygiveegg", "%s gave %s a %s Egg!");
        add("pixelmon.command.heal.cantheal", "Cannot heal %s's Pokémon while they are in battle!");
        add("pixelmon.command.heal.healed", "Successfully healed %s's Pokémon!");
        add("pixelmon.command.heal.notifyhealed", "%s successfully healed %s's Pokémon!");
        add("pixelmon.command.snapshot.corners", "Corners,");
        add("pixelmon.command.snapshot.convert", "%s has been converted and saved.");
        add("pixelmon.command.snapshot.errorread", "There was an error reading %s.");
        add("pixelmon.command.snapshot.load", "Loaded block snapshot from %s.snapshot.");
        add("pixelmon.command.snapshot.nobounds", "No bounds have been defined to save yet!");
        add("pixelmon.command.snapshot.nosave", "No blocks have been saved yet!");
        add("pixelmon.command.snapshot.place", "Block snapshot placed.");
        add("pixelmon.command.snapshot.save", "Block snapshot saved.");
        add("pixelmon.command.snapshot.savefile", "Block snapshot saved to %s.snapshot.");
        add("pixelmon.command.spawn.spawned", "Successfully spawned a %s.");
        add("pixelmon.command.spawn.spawnednotify", "%s successfully spawned %s.");
        add("pixelmon.command.spectate.nobattle", "%s is not in a battle.");
        add("pixelmon.command.spectate.self", "You can't spectate yourself!");
        add("pixelmon.command.stats.losses", "Losses,");
        add("pixelmon.command.stats.playerstats", "'s stats,");
        add("pixelmon.command.stats.wins", "Wins,");
        add("pixelmon.command.stats.yourstats", "Your stats,");
        add("pixelmon.command.statsreset.reset", "'s data has been reset.");
        add("pixelmon.command.struc.cantfit", "The structure was unable to fit at %s.");
        add("pixelmon.command.struc.nostruc", "No structures found for this biome. (%s)");
        add("pixelmon.command.struc.notfound", "Structure %s not found.");
        add("pixelmon.command.teach.busy", "%s is busy!");
        add("pixelmon.command.teach.knowsmove", "%s's %s already knows %s!");
        add("pixelmon.command.teach.nomove", "%s is not a move!");
        add("pixelmon.command.teach.nothing", "%s has nothing in that slot!");
        add("pixelmon.command.teach.sentmove", "%s's %s is now learning %s.");
        add("pixelmon.command.teach.slot", "Slot number must be between 1 and 6.");
        add("pixelmon.command.unlock", "Successfully unlocked %s's locked Pokémon.");
        add("pixelmon.command.warpplate.notstanding", "You are not standing on a warp plate.");
        add("pixelmon.command.warpplate.set", "Set warp target to %s.");
        add("pixelmon.command.transfer.notenoughmoney", "Not enough money to complete transfer.");
        add("pixelmon.command.transfer.sameplayer", "You cannot send money to yourself!");
        add("pixelmon.command.transfer.transferred", "Successfully transferred %s PokéDollars to %s!");
        add("pixelmon.command.transfer.notifytransfer", "%s successfully transferred %s PokéDollars to %s!");
        add("pixelmon.command.transfer.received", "%s successfully transferred %s PokéDollars to you!");
        add("pixelmon.command.pokedollar.balance", "%s currently has %s PokéDollars!");
        add("pixelmon.command.pokedollar.set", "Successfully set %s's balance to %s PokéDollars!");
        add("pixelmon.command.pokedollar.set.notify", "Your balance was set to %s PokéDollars!");
        add("pixelmon.command.pokedollar.add", "Successfully added %s PokéDollars to %s's balance!");
        add("pixelmon.command.pokedollar.add.notify", "You received %s PokéDollars to your balance!");
        add("pixelmon.command.pokedollar.add.limit", "%s cannot hold any more PokéDollars!");
        add("pixelmon.command.pokedollar.remove", "Successfully removed %s PokéDollars from %s's balance!");
        add("pixelmon.command.pokedollar.remove.notify", "You lost %s PokéDollars from your balance!");
        add("pixelmon.command.pokedollar.remove.limit", "%s does not have enough PokéDollars to take %s away!");
        add("pixelmon.command.trainerhat.invalidcolour", "Invalid colors entered for hat. Should be red/green/blue from 0-255.");
        add("pixelmon.command.reload.error.shopitems", "Cannot reload shop items data!");
        add("pixelmon.command.reload.error.npcs", "Cannot reload NPC data!");
        add("pixelmon.command.reload.done", "NPC and shop item data and pixelmon.hocon reloaded.");
        add("pixelmon.command.pokekill.done", "Successfully killed %s Wild Pokemon.");
        add("pixelmon.command.pokeretrieve.done", "Successfully retrieved nearby player's Pokémon.");
        add("pixelmon.command.dynamaxband.notifygave", "Successfully given %s a dynamaxband.");
        add("pixelmon.command.givebackground.notifygave", "Successfully given %s the background %s.");

        //pixelmon.stats
        add("pixelmon.stats.ivmaxed", "%s's IVs are already maxed!");
        add("pixelmon.stats.mint", "Your %s's stats have been changed!");
        add("pixelmon.stats.gainexp", "Your %s gained %d Exp.!");
        add("pixelmon.stats.learnedmove", "%s just learned %s!");

        //Abilities


        //Battle Text
        add("pixelmon.battletext.noeffect", "It doesn't affect %s...");
        add("pixelmon.battletext.movefailed", "But it failed!");
        add("pixelmon.battletext.criticalhit", "A critical hit!");
        add("pixelmon.battletext.criticalhittarget", "A critical hit on %s!");
        add("pixelmon.battletext.wasnoteffective", "It wasn't very effective...");
        add("pixelmon.battletext.wasnoteffectivetarget", "It wasn't very effective on %s...");
        add("pixelmon.battletext.supereffective", "It's super effective!");
        add("pixelmon.battletext.supereffectivetarget", "It's super effective on %s!");
        add("pixelmon.battletext.missedattack", "%s avoided the attack!");
        add("pixelmon.battletext.butmovefailed", "But it failed!");
        add("pixelmon.battletext.used", "%s used %s!");
        add("pixelmon.battletext.sentout", "%s sent out %s!");
        add("pixelmon.battletext.megareact", "%s's %s is reacting to %s's Key Stone!");
        add("pixelmon.battletext.ashgreninja.react", "Greninja became fully charged due to its bond with %s!");
        add("pixelmon.battletext.megaevolve", "%s has Mega Evolved into Mega %s!");
        add("pixelmon.battletext.ashgreninja.evolve", "Greninja has become Ash Greninja!");
        add("pixelmon.battletext.brightlight", "Bright light is about to burst out of the opposing %s!");
        add("pixelmon.battletext.ultraburst", "The opposing %s regained its true power through Ultra Burst!");
        add("pixelmon.battletext.dynamaxing", "%s is Dynamaxing!");
        add("pixelmon.battletext.gigantamaxing", "%s is Gigantamaxing!");

        add("container.melody_flute", "Melody Flute");
        add("container.trashcan", "Trash Can");
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

        SkinLang.addSkins(this);

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
