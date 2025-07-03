package generations.gg.generations.core.generationscore.forge.datagen.generators.lang

import com.cobblemon.mod.common.api.berry.Flavor
import com.cobblemon.mod.common.util.asResource
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.generationsResource
import generations.gg.generations.core.generationscore.common.util.ItemPlatformRegistry
import generations.gg.generations.core.generationscore.common.util.extensions.id
import generations.gg.generations.core.generationscore.common.world.entity.GenerationsEntities
import generations.gg.generations.core.generationscore.common.world.item.*
import generations.gg.generations.core.generationscore.common.world.item.curry.CurryType
import generations.gg.generations.core.generationscore.common.world.level.block.*
import net.minecraft.Util
import net.minecraft.core.Holder
import net.minecraft.data.PackOutput
import net.minecraft.world.item.Item
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Block
import net.neoforged.neoforge.common.data.LanguageProvider
import java.util.*

class GeneralLang(packOutput: PackOutput, locale: String) :
    LanguageProvider(packOutput, GenerationsCore.MOD_ID, locale) {
    override fun addTranslations() {
        addBlockEntries(GenerationsBlocks.BLOCKS)
        addBlockEntries(GenerationsBlocks.ULTRA_BLOCKS)
        addBlockEntries(GenerationsBlocks.STONE)
        addBlockEntries(GenerationsDecorationBlocks)
        addBlockEntries(GenerationsWood.WOOD_BLOCKS)
        addBlockEntries(GenerationsShrines)
        addBlockEntries(GenerationsPokeDolls)
        addBlockEntries(GenerationsUtilityBlocks)
        addBlockEntries(GenerationsOres)

        addItemEntries(GenerationsTools)
        addItemEntries(GenerationsArmor)
        addItemEntries(GenerationsItems.ITEMS)
        addItemEntries(GenerationsItems.BADGES)
        addItemEntries(GenerationsItems.RIBBONS)
        addItemEntries(GenerationsItems.UNIMPLEMENTED, additionalActions = { item, _ -> add(item.asItem().descriptionId + ".desc", "Not currently implemented") })

        addItemEntries(GenerationsItems.CUISINE)
        addItemEntries(GenerationsItems.NATURAL)
        addItemEntries(GenerationsItems.RESTORATION)
        addItemEntries(GenerationsItems.PLAYER_ITEMS)
        addItemEntries(GenerationsItems.HELD_ITEMS)
        addItemEntries(GenerationsItems.LEGENDARY_ITEMS)
        addItemEntries(GenerationsItems.UTILITY)
        addItemEntries(GenerationsItems.VALUABLES)
        addItemEntries(GenerationsItems.FORM_ITEMS)
        addItemEntries(GenerationsItems.BUILDING_BLOCKS)
        addItemEntries(GenerationsItems.POKEMAIL)

        recordDescriptions()

        //Manually add Creative Tabs
        add("itemGroup.generations_core.restoration", "Restoration")
        add("itemGroup.generations_core.tms", "TMs")
        add("itemGroup.generations_core.awards", "Awards")
        add("itemGroup.generations_core.held_items", "Held Items")
        add("itemGroup.generations_core.player_items", "Player Items")
        add("itemGroup.generations_core.legendary_items", "Legendary Items")
        add("itemGroup.generations_core.building_blocks", "Building Blocks")
        add("itemGroup.generations_core.decorations", "Decorations")
        add("itemGroup.generations_core.utility", "Utility")
        add("itemGroup.generations_core.form_items", "Form Items")
        add("itemGroup.generations_core.pokemail", "Pokemail")
        add("itemGroup.generations_core.valuables", "Valuables")
        add("itemGroup.generations_core.pokedolls", "PokeDolls")
        add("itemGroup.generations_core.cuisine", "Cuisine")
        add("itemGroup.generations_core.unimplemented", "Unimplemented")
        add("itemGroup.generations_core.shrines", "Shrines")

        add("container.melody_flute", "Melody Flute")
        add("container.trashcan", "Trash Can")
        add("container.shaderoot_carrot", "Shaderoot Carrot")
        add("container.iceroot_carrot", "Iceroot Carrot")

        add("container.poke_walkmon", "Poke Walkmon")
        add("container.great_walkmon", "Great Walkmon")
        add("container.ultra_walkmon", "Ultra Walkmon")
        add("container.master_walkmon", "Master Walkmon")

        add("container.pokeball_chest", "Pokeball Chest")
        add("container.greatball_chest", "Greatball Chest")
        add("container.ultraball_chest", "Ultraball Chest")
        add("container.masterball_chest", "Masterball Chest")
        add("container.hi_tech_earbuds", "Hi Tech Earbuds")
        add("container.charge_stone_furnace", "Charge Stone Furnace")
        add("container.charge_stone_blast_furnace", "Charge Stone Blast Furnace")
        add("container.charge_stone_smoker", "Charge Stone Smoker")
        add("container.volcanic_stone_furnace", "Volcanic Stone Furnace")
        add("container.volcanic_stone_blast_furnace", "Volcanic Stone Blast Furnace")
        add("container.volcanic_stone_smoker", "Volcanic Stone Smoker")
        add("container.cooking_pot", "Cooking Pot")
        add("container.box", "Box")


        add("generations_core.item.melody_flute.no_item", "This flute resonates with feathers.")

        add("generations_core.item.melody_flute.imbued", "Imbued: %s")
        add("generations_core.item.melody_flute.not_full_imbued1", "Imbue by defeating %s %s pokemon.")
        add("generations_core.item.melody_flute.not_full_imbued2", "Then shift right click on a %s")
        add("generations_core.item.melody_flute.not_full_imbued3", "to spawn %s.")

        add("generations_core.item.melody_flute.full_imbued1", "Fully imbued with the essence of %s.")
        add("generations_core.item.melody_flute.full_imbued2", "Shift right click on a %s")
        add("generations_core.item.melody_flute.full_imbued3", "to spawn %s.")

        add("generations_core.item.timeglass.wrongbiome", "You can only summon Celebi in a Flower Forest Biome")
        add(
            "generations_core.item.timeglass.amount",
            "You've defeated %s Grass, Psychic, or Fairy Type Pokemon out of 100 in a Flower Forest Biome"
        )
        add("item.generations_core.enigma_fragment.desc", "A mysterious item gained upon discovering new biomes.")

        //TR/TM lines
        add("move.cantlearn", "%s can't learn %s.")
        add("move.alreadyknows", "%s already knows %s.")
        add("move.learned", "%s has learned %s.")
        add("move.doesntexist", "The move %s doesn't exists so %s couldn't learn it.")
        add("move.newmove1", "1, 2, and... Ta da!")
        add("move.newmove2", "%s forgot %s!")
        add("move.newmove3", "...and learned %s!")

        //Temp Cobblemon
        add("cobblemon.move.upperhand", "Upper Hand")
        add("cobblemon.move.upperhand.name", "Upper Hand")
        add(
            "cobblemon.move.upperhand.desc",
            "The user strikes with the heel of their palm, causing the target to flinch. Fails if the target is not readying a priority move."
        )
        add("cobblemon.move.hardpress", "Hard Press")
        add("cobblemon.move.hardpress.name", "Hard Press")
        add(
            "cobblemon.move.hardpress.desc",
            "The target is crushed with an arm, a claw, or the like to inflict damage. The more HP the target has left, the greater the power."
        )
        add("cobblemon.move.temperflare", "Temper Flare")
        add("cobblemon.move.temperflare.name", "Temper Flare")
        add(
            "cobblemon.move.temperfale.desc",
            "Spurred by desperation, the user attacks the target. This power is doubled if the previous move failed."
        )
        add("cobblemon.move.dragoncheer", "Dragon Cheer")
        add("cobblemon.move.dragoncheer.name", "Dragon Cheer")
        add(
            "cobblemon.move.dragoncheer.desc",
            "The user cheers a draconic cry to raise its allies critical hit chance. This rouses Dragon types more."
        )
        add("cobblemon.move.syrupbomb", "Syrup Bomb")
        add("cobblemon.move.syrupbomb.name", "Syrup Bomb")
        add(
            "cobblemon.move.syrupbomb.desc",
            "Spews an explosion of sticky syrup onto the target and causes their Speed to drop each turn for three turns."
        )
        add("cobblemon.move.ficklebeam", "Fickle Beam")
        add("cobblemon.move.ficklebeam.name", "Fickle Beam")
        add(
            "cobblemon.move.ficklebeam.desc",
            "Shoots a beam of light to deal damage. Sometimes all the user's heads shoot beams in unison, doubling the power."
        )
        add("cobblemon.move.alluringvoice", "Alluring Voice")
        add("cobblemon.move.alluringvoice.name", "Alluring Voice")
        add(
            "cobblemon.move.alluringvoice.desc",
            "The user attacks with its angelic voice, and also confuses the target if its stats were boosted during the turn."
        )
        add("cobblemon.move.psychicnoise", "Psychic Noise")
        add("cobblemon.move.psychicnoise.name", "Psychic Noise")
        add(
            "cobblemon.move.psychicnoise.desc",
            "The user attacks with unpleasant sound waves, preventing the target from recovering HP through moves, abilities, or held items for two turns."
        )
        add("cobblemon.move.supercellslam", "Supercell Slam")
        add("cobblemon.move.supercellslam.name", "Supercell Slam")
        add(
            "cobblemon.move.supercellslam.desc",
            "The user electrifies its body and drops onto the target to inflict damage. If this move misses, the user takes damage instead."
        )
        add("cobblemon.species.ponytagalar.name", "Galarian Ponyta")

        //Posters
        add("painting.generations_core.blue_poster.author", "Pokemon")
        add("painting.generations_core.blue_poster.title", "Blue Poster")
        add("painting.generations_core.blue_poster_sprite.author", "Pokemon")
        add("painting.generations_core.blue_poster_sprite.title", "Blue Poster Sprite")
        add("painting.generations_core.blue_scroll.author", "Pokemon")
        add("painting.generations_core.blue_scroll.title", "Blue Scroll")
        add("painting.generations_core.clefairy_poster_sprite.author", "Pokemon")
        add("painting.generations_core.clefairy_poster_sprite.title", "Clefairy Poster")
        add("painting.generations_core.cute_poster.author", "Pokemon")
        add("painting.generations_core.cute_poster.title", "Cute Poster")
        add("painting.generations_core.cute_poster_sprite.author", "Pokemon")
        add("painting.generations_core.cute_poster_sprite.title", "Cute Poster Sprite")
        add("painting.generations_core.dads_scroll.author", "Pokemon")
        add("painting.generations_core.dads_scroll.title", "Dads Scroll")
        add("painting.generations_core.green_poster.author", "Pokemon")
        add("painting.generations_core.green_poster.title", "Green Poster")
        add("painting.generations_core.green_poster_sprite.author", "Pokemon")
        add("painting.generations_core.green_poster_sprite.title", "Green Poster Sprite")
        add("painting.generations_core.green_scroll.author", "Pokemon")
        add("painting.generations_core.green_scroll.title", "Green Scroll")
        add("painting.generations_core.jigglypuff_poster_sprite.author", "Pokemon")
        add("painting.generations_core.jigglypuff_poster_sprite.title", "Jigglypuff")
        add("painting.generations_core.kiss_poster_sprite.author", "Pokemon")
        add("painting.generations_core.kiss_poster_sprite.title", "Kiss Poster Sprite")
        add("painting.generations_core.long_poster.author", "Pokemon")
        add("painting.generations_core.long_poster.title", "Long Poster")
        add("painting.generations_core.long_poster_sprite.author", "Pokemon")
        add("painting.generations_core.long_poster_sprite.title", "Long Poster Sprite")
        add("painting.generations_core.national_award.author", "Pokemon")
        add("painting.generations_core.national_award.title", "National Award")
        add("painting.generations_core.pika_poster.author", "Pokemon")
        add("painting.generations_core.pika_poster.title", "Pika Poster")
        add("painting.generations_core.pika_poster_sprite.author", "Pokemon")
        add("painting.generations_core.pika_poster_sprite.title", "Pika Poster Sprite")
        add("painting.generations_core.pikachu_poster_sprite.author", "Pokemon")
        add("painting.generations_core.pikachu_poster_sprite.title", "Pikachu Poster Sprite")
        add("painting.generations_core.poke_ball_poster.author", "Pokemon")
        add("painting.generations_core.poke_ball_poster.title", "Poke Ball Poster")
        add("painting.generations_core.red_poster.author", "Pokemon")
        add("painting.generations_core.red_poster.title", "Red Poster")
        add("painting.generations_core.red_poster_sprite.author", "Pokemon")
        add("painting.generations_core.red_poster_sprite.title", "Red Poster Sprite")
        add("painting.generations_core.red_scroll.author", "Pokemon")
        add("painting.generations_core.red_scroll.title", "Red Scroll")
        add("painting.generations_core.regional_award.author", "Pokemon")
        add("painting.generations_core.regional_award.title", "Regional Award")
        add("painting.generations_core.sea_poster.author", "Pokemon")
        add("painting.generations_core.sea_poster.title", "Sea Poster")
        add("painting.generations_core.sea_poster_sprite.author", "Pokemon")
        add("painting.generations_core.sea_poster_sprite.title", "Sea Poster Sprite")
        add("painting.generations_core.sky_poster.author", "Pokemon")
        add("painting.generations_core.sky_poster.title", "Sky Poster")
        add("painting.generations_core.sky_poster_sprite.author", "Pokemon")
        add("painting.generations_core.sky_poster_sprite.title", "Sky Poster Sprite")
        add("painting.generations_core.time_travel_award.author", "Pokemon")
        add("painting.generations_core.time_travel_award.title", "Time Travel Award")
        add("painting.generations_core.town_map_sprite.author", "Pokemon")
        add("painting.generations_core.town_map_sprite.title", "Town Map Sprite")

        add("generations_core.blocks.lootfound", "You found one %s!")
        add("generations_core.blocks.timedclaim", "You've already claimed this timed loot! Try again later!")
        add("generations_core.blocks.claimedloot", "You've already claimed this loot!")
        add("generations_core.blocks.ownerchanged", "Block owner changed to server!")
        add("generations_core.blocks.balllootset", "Custom Ball Loot Set: %s")
        add("generations_core.blocks.visible", "Ball Loot Visibility: %s")
        add("generations_core.blocks.lootmode", "Loot Mode: %s")
        add("generations_core.blocks.lootmode.once_per_player", "Permanent, limit 1 drop")
        add("generations_core.blocks.lootmode.timed", "Timed drops")
        add("generations_core.blocks.lootmode.once", "First come, first served")
        add("generations_core.blocks.lootmode.unlimited", "Permanent, unlimited drops")

        //Heatran
        addTooltip(GenerationsItems.LAVA_CRYSTAL, "§7HINT: You see a faint image of Heatran from within. You’ll need more, and an Orb.")
        addTooltip(GenerationsItems.MAGMA_CRYSTAL, "§7HINT: Against your better judgment, you feel a sudden urge to throw this crystal into lava (right-click)")

        //Deoxys
        addTooltip(GenerationsItems.METEORITE, "§7HINT: Strange, otherworldly energy is coming from this item. Try enchanting it and charging it by defeating Pokemon of a relevant type.")
        addTooltip(GenerationsItems.METEORITE_SHARD, "§7HINT: Maybe if you collect enough you could craft something from out of this world.")

        //
        //Celebi
        add("generations_core.timeglass.wrongbiome", "§7This item only works in a Flower Forest biome!")
        add("generations_core.timeglass.amount", "§7Grass, Psychic, or Fairy-Type Pokemon remaining: ")
        addTooltip(GenerationsItems.TIME_GLASS, "§7HINT: Try charging it by defeating Pokemon of a relevant type. It may then react in a Flower Forest biome.")
        addTooltip(GenerationsItems.ORB, "§7HINT: This item is flowing with mysterious energy. It’s related to a number of legendary and mythical beings. Maybe you can craft something…")

        //Legendary Titans
        var string = "§7HINT: Try collecting all 4 parts to repair this key."
        addTooltip(GenerationsItems.CRUMBLED_ROCK_KEY_1, string)
        addTooltip(GenerationsItems.CRUMBLED_ROCK_KEY_2, string)
        addTooltip(GenerationsItems.CRUMBLED_ROCK_KEY_3, string)
        addTooltip(GenerationsItems.CRUMBLED_ROCK_KEY_4, string)
        addTooltip(GenerationsItems.SHATTERED_ICE_KEY_1, string)
        addTooltip(GenerationsItems.CRUMBLED_ROCK_KEY_2, string)
        addTooltip(GenerationsItems.CRUMBLED_ROCK_KEY_3, string)
        addTooltip(GenerationsItems.CRUMBLED_ROCK_KEY_4, string)
        addTooltip(GenerationsItems.RUSTY_IRON_KEY_1, string)
        addTooltip(GenerationsItems.RUSTY_IRON_KEY_2, string)
        addTooltip(GenerationsItems.RUSTY_IRON_KEY_3, string)
        addTooltip(GenerationsItems.RUSTY_IRON_KEY_4, string)
        addTooltip(GenerationsItems.DISCHARGED_ELEKI_KEY_1, string)
        addTooltip(GenerationsItems.DISCHARGED_ELEKI_KEY_2, string)
        addTooltip(GenerationsItems.DISCHARGED_ELEKI_KEY_3, string)
        addTooltip(GenerationsItems.DISCHARGED_ELEKI_KEY_4, string)
        addTooltip(GenerationsItems.FRAGMENTED_DRAGO_KEY_1, string)
        addTooltip(GenerationsItems.FRAGMENTED_DRAGO_KEY_2, string)
        addTooltip(GenerationsItems.FRAGMENTED_DRAGO_KEY_3, string)
        addTooltip(GenerationsItems.FRAGMENTED_DRAGO_KEY_4, string)
        string =
            "§7HINT: This item appears to be related to one of the Regi’s. Find their shrine at a Snowpoint Temple."
        addTooltip(GenerationsItems.ROCK_PEAK_KEY, string)
        addTooltip(GenerationsItems.ICEBERG_KEY, string)
        addTooltip(GenerationsItems.IRON_KEY, string)
        addTooltip(GenerationsItems.ELEKI_KEY, string)
        addTooltip(GenerationsItems.DRAGO_KEY, string)
        string = "§7HINT: Quiet vibrations of what sound like a faint screech are flowing into your ears. All you can make out are “Key” and “Spell”."
        addTooltip(GenerationsShrines.REGICE_SHRINE, string)
        addTooltip(GenerationsShrines.REGIROCK_SHRINE, string)
        addTooltip(GenerationsShrines.REGISTEEL_SHRINE, string)
        addTooltip(GenerationsShrines.REGIDRAGO_SHRINE, string)
        addTooltip(GenerationsShrines.REGIELEKI_SHRINE, string)

        //Eon Duo
        addTooltip(GenerationsItems.ENIGMA_FRAGMENT, "§7HINT: You appear to gain one for each new biome discovered. If you collect enough, maybe you can craft something.")
        addTooltip(GenerationsItems.ENIGMA_SHARD, "§7HINT: You should explore more… Maybe with enough of these you could craft something related to the Eon Duo.")
        addTooltip(GenerationsItems.ENIGMA_STONE, "§7HINT: You should try capturing or defeating Psychic or Dragon Type Pokemon")

        //Weather Trio
        string =
            "§7HINT: This item appears to be related to the Weather Trio. Try Charging it by defeating Pokemon of a relevant type. Then, find a relevant shrine."
        addTooltip(GenerationsItems.FADED_BLUE_ORB, string)
        addTooltip(GenerationsItems.FADED_RED_ORB, string)
        addTooltip(GenerationsItems.FADED_JADE_ORB, "§7HINT: This item appears to be related to the Weather Trio. Try charging it by defeating Pokemon of a relevant type. Then, go VERY high in altitude.")
        addTooltip(GenerationsItems.JADE_ORB, "§7HINT: This item can teach Dragon Ascent to a certain Pokemon.")

        //Lake Guardians
        addTooltip(GenerationsItems.SHARD_OF_EMOTION, "§7HINT: Keep fishing. With enough, you could craft something." )
        addTooltip(GenerationsItems.SHARD_OF_KNOWLEDGE, "§7HINT: Keep fishing. With enough, you could craft something.")
        addTooltip(GenerationsItems.SHARD_OF_WILLPOWER, "§7HINT: Keep fishing. With enough, you could craft something.")
        addTooltip(GenerationsItems.CRYSTAL_OF_EMOTION, "§7HINT: There’s even more to this strange item. Collect the other crystals and perhaps you could craft a special chain.")
        addTooltip(GenerationsItems.CRYSTAL_OF_KNOWLEDGE, "§7HINT: There’s even more to this strange item. Collect the other crystals and perhaps you could craft a special chain.")
        addTooltip(GenerationsItems.CRYSTAL_OF_WILLPOWER, "§7HINT: There’s even more to this strange item. Collect the other crystals and perhaps you could craft a special chain.")
        addTooltip(GenerationsItems.CRYSTAL_OF_EMOTION, "enchanted", "§7HINT: Try charging it by defeating Pokemon of a relevant type.")
        addTooltip(GenerationsItems.CRYSTAL_OF_KNOWLEDGE, "enchanted", "§7HINT: Try charging it by defeating Pokemon of a relevant type.")
        addTooltip(GenerationsItems.CRYSTAL_OF_WILLPOWER, "enchanted", "§7HINT: Try charging it by defeating Pokemon of a relevant type.")
        addTooltip(GenerationsItems.RUBY_ROD, "§7HINT: Fish… Lake… Guardian…")
        add("item.generations_core.ruby_rod.fate_defied", "The Ruby Rod shatters as it resists its altered fate")
        //Creation Trio
        addTooltip(GenerationsItems.RED_CHAIN, "§7HINT: It doesn’t seem to be reacting to anything. The Crystals used may have used up all their energy. Try enchanting it to give it some power.")
        addTooltip(GenerationsItems.RED_CHAIN, "enchanted", "“HINT: This item appears to be related to the Creation Trio, perhaps if you had their respected Orbs you could activate an altar.")
        addTooltip(GenerationsItems.ADAMANT_ORB, "§7HINT: This item appears to be related to the Creation Trio. If you crafted a Red Chain, you may be activate an Altar.")
        addTooltip(GenerationsItems.LUSTROUS_ORB, "§7HINT: This item appears to be related to the Creation Trio. If you crafted a Red Chain, you may be activate an Altar.")
        addTooltip(GenerationsItems.GRISEOUS_ORB, "§7HINT: This item appears to be related to the Creation Trio. If you crafted a Red Chain, you may be activate an Altar.")

        //Regigigas
        addTooltip(GenerationsItems.REGICE_ORB, "§7HINT: If you collect the other Orbs, Regigigas may be awoken")
        addTooltip(GenerationsItems.REGIROCK_ORB, "§7HINT: If you collect the other Orbs, Regigigas may be awoken")
        addTooltip(GenerationsItems.REGISTEEL_ORB, "§7HINT: If you collect the other Orbs, Regigigas may be awoken")
        addTooltip(GenerationsItems.REGIELEKI_ORB, "§7HINT: If you collect the other Orbs, Regigigas may be awoken")
        addTooltip(GenerationsItems.REGIDRAGO_ORB, "§7HINT: If you collect the other Orbs, Regigigas may be awoken")
        addTooltip(GenerationsShrines.REGIGIGAS_SHRINE, "§7HINT: Images of all 5 Regi’s in the distant past begin to swarm into you. They’re playing. You feel an overwhelming sense of… nurturing? Regigigas clearly has no desire to be awakened unless you’ve awakened its friends.")

        //Lunar Duo
        addTooltip(GenerationsShrines.LUNAR_SHRINE, "§7HINT: It seems empty, strangely like the feeling of a heartache. A summoning ritual is to occur here. A deep malevolent cry is suddenly heard in your left ear- “Souls”. Almost immediately afterwards, a higher-pitched angelic-like cry is heard in your right- “Souls”. Creepy.")
        addTooltip(GenerationsShrines.LIGHT_CRYSTAL, "§7HINT: It’s guiding you to a pentagram. You’ll need 5 in total and prevail against the dark.")
        addTooltip(GenerationsShrines.DARK_CRYSTAL, "§7HINT: It’s guiding you to a pentagram. You’ll need 5 in total and prevail against the light.")
        addTooltip(GenerationsItems.LIGHT_SOUL, "§7HINT: Cresselia appears faintly, you need more.")
        addTooltip(GenerationsItems.DARK_SOUL, "§7HINT:  Darkrai appears faintly, you need more.")

        //Sea Guardians
        addTooltip(GenerationsItems.WONDER_EGG, "§7HINT: Oh?")
        addTooltip(GenerationsItems.PHIONE_EGG, "§7HINT: Oh?")

        //Forces of Nature
        addTooltip(GenerationsItems.MIRROR, "§7HINT: Instead of seeing your reflection, you see Gems. They’re pink, blue, and white and laid out in a frame.")
        addTooltip(GenerationsItems.REVEAL_GLASS, "§7HINT: Rather than your reflection, it displays a small Abundant Shrine in the sky.")
        addTooltip(GenerationsShrines.ABUNDANT_SHRINE, "§7HINT: You need something to reveal the secrets within.")

        //Tao Trio
        addTooltip(
            GenerationsItems.DRAGON_SOUL,
            "§7HINT: It screams. They seek their masters- the Tao Trio. An Orb is required to seal these souls. With a certain Gem, you could manipulate it towards a specific Tao."
        )
        addTooltip(GenerationsItems.LIGHT_STONE, "§7HINT: It isn’t reacting. More Dragons could do the trick.")
        addTooltip(GenerationsItems.DARK_STONE, "§7HINT: It isn’t reacting. More Dragons could do the trick.")
        addTooltip(GenerationsItems.DRAGON_STONE, "§7HINT: It isn’t reacting. More Dragons could do the trick.")
        addTooltip(
            GenerationsShrines.TAO_TRIO_SHRINE,
            "§7HINT: It’s radiating a lifeless aura. It’s dead silent but you begin to see battle cries?  Even stranger, you begin to taste powerful blasts of soundwaves– attacks from the distant past. A fierce battle took place long ago. Suddenly, you know what needs to be done– collect Dragon Souls."
        )

        //Meloetta
        addTooltip(
            GenerationsItems.SHATTERED_RELIC_SONG_1,
            "§7HINT: This appears to be a piece of a music disc. It might be repairable if you obtain all the pieces."
        )
        addTooltip(
            GenerationsItems.SHATTERED_RELIC_SONG_2,
            "§7HINT: This appears to be a piece of a music disc. It might be repairable if you obtain all the pieces."
        )
        addTooltip(
            GenerationsItems.SHATTERED_RELIC_SONG_3,
            "§7HINT: This appears to be a piece of a music disc. It might be repairable if you obtain all the pieces."
        )
        addTooltip(
            GenerationsItems.SHATTERED_RELIC_SONG_4,
            "§7HINT: This appears to be a piece of a music disc. It might be repairable if you obtain all the pieces."
        )
        addTooltip(GenerationsItems.RELIC_SONG, "§7HINT: A special music box may play this melody differently.")
        addTooltip(GenerationsItems.INERT_RELIC_SONG, "Relic Song - Shota Kageyama.")
        add("jukebox_song.generations_core.meloettas_relic_song", "Relic Song - Shota Kageyama")

        this.add("item.minecraft.smithing_template.ultrite_upgrade.ingredients", "Ultrite Ingot")
        addTooltip(
            GenerationsShrines.MELOETTA_MUSIC_BOX,
            "§7HINT: You hear a wonderful melody from within. You need to hear more.”"
        )
        //Zygarde
        addTooltip(GenerationsItems.ZYGARDE_CUBE, "cell_add", "Zygarde Cell collected!")
        addTooltip(
            GenerationsItems.ZYGARDE_CUBE,
            "cell_overflow",
            "Due to Zygarde Cube being full, the zygarde cell atomizes."
        )
        addTooltip(GenerationsItems.ZYGARDE_CUBE, "cell_full", "The Zygarde Cube is full!")
        addTooltip(GenerationsItems.ZYGARDE_CUBE, "lore1", "A strange cube capable of storing up to 100 Zygarde Cells,")
        addTooltip(
            GenerationsItems.ZYGARDE_CUBE,
            "lore2",
            "as well as fusing them together to reconstruct the legendary Zygarde."
        )
        addTooltip(GenerationsItems.ZYGARDE_CUBE, "lore3", "Collect Zygarde Cells to summon the balance.")
        addTooltip(GenerationsItems.ZYGARDE_CUBE, "lore4", "Cells collected: %s/%s")

        add(GenerationsEntities.ZYGARDE_CELL.value(), "Zygarde Cells")
        add("gui.zygarde_cube", "Zygarde Cube")
        add("gui.zygarde_cube.select", "Merge Zygarde Cells")
        add("gui.zygarde_cube.merge_10.name", "Create 10%")
        add("gui.zygarde_cube.merge_10.accept", "Your Cells merged to form a Zygarde 10%!")
        add("gui.zygarde_cube.merge_50.name", "Create 50%")
        add("gui.zygarde_cube.merge_50.accept", "Your Cells merged to form a Zygarde 50%!")
        add("gui.zygarde_cube.merge_10_power_construct.name", "Create 10% with Power Construct")
        add(
            "gui.zygarde_cube.merge_10_power_construct.accept",
            "Your Cells merged to form a Zygarde 10% with Power Construct!"
        )
        add("gui.zygarde_cube.merge_50_power_construct.name", "Create 50% with Power Construct")
        add(
            "gui.zygarde_cube.merge_50_power_construct.accept",
            "Your Cells merged to form a Zygarde 50% with Power Construct!"
        )
        add("gui.zygarde_cube.merge_10_with_40_to_50.name", "Convert 10% to 50% with Power Construct")
        add(
            "gui.zygarde_cube.merge_10_with_40_to_50.accept",
            "Your Zygarde 10% was combined with 40 Cells to create a Zygarde 50%!"
        )
        add("gui.zygarde_cube.merge_10_with_90_to_10_power_construct.name", "Convert 10% to 10% Power Construct")
        add(
            "gui.zygarde_cube.merge_10_with_90_to_10_power_construct.accept",
            "Your Zygarde 10% was combined with 90 Cells to create a Zygarde 10% with Power Construct!"
        )
        add("gui.zygarde_cube.merge_10_with_90_to_50_power_construct.name", "Convert 10% to 50% with Power Construct")
        add(
            "gui.zygarde_cube.merge_10_with_90_to_50_power_construct.accept",
            "Your Zygarde 10% was combined with 90 Cells to create a Zygarde 50% with Power Construct!"
        )
        add("gui.zygarde_cube.merge_50_with_50_to_50_power_construct.name", "Convert 50% to 50% with Power Construct")
        add(
            "gui.zygarde_cube.merge_50_with_50_to_50_power_construct.accept",
            "Your Zygarde 50% was combined with 50 Cells to create a Zygarde 50% with Power Construct!"
        )

        //Hoopa
        addTooltip(
            GenerationsShrines.PRISON_BOTTLE_STEM,
            "§7HINT: You hear a voice within, guiding you. \"Free me! Make my rings with Gold Blocks\"."
        )
        addTooltip(
            GenerationsItems.HOOPA_RING,
            "§7HINT: If you collect 6 and insert them on a Bottle Stem, you could create a Prison Bottle. A Bottle Stem may be created with Rubies, White Glazed Terracotta, and an Orb."
        )
        //Guardian Deities
        addTooltip(
            GenerationsItems.SPARKLING_SHARD,
            "§7HINT: A protective presence looms over this shard. Perhaps if you collect more you’ll get a stronger feeling of what to do."
        )
        addTooltip(
            GenerationsItems.SPARKLING_STONE,
            "§7HINT: Capturing Pokemon of a specific type to care for seems to cause a reaction."
        )

        //Magearna
        addTooltip(
            GenerationsItems.SOUL_HEART,
            "§7HINT: This item appears to hold the soul of a mysterious Pokemon. You could try creating a body for it in an RKS Machine."
        )

        //Melmetal
        addTooltip(
            GenerationsItems.MELTAN_BOX,
            "§7HINT: Meltan oddly find peace in this box. Collect an army colony of Meltan! For science, of course."
        )
        addTooltip(
            GenerationsItems.MELTAN_BOX_CHARGED,
            "§7HINT: Meltan oddly find peace in this box. Collect an army colony of Meltan! For science, of course."
        )

        //Hero Duo
        addTooltip(
            GenerationsItems.RUSTY_FRAGMENT,
            "§7HINT: Collect more Rusty Fragments from Aegislash to assist in crafting a Sword or Shield"
        )
        addTooltip(GenerationsItems.RUSTY_SHIELD, "§7HINT: Steel Type Pokemon")
        addTooltip(GenerationsItems.RUSTY_SWORD, "§7HINT: Steel Type Pokemon")

        //Kubfu
        addTooltip(
            GenerationsItems.SCROLL_PAGE,
            "§7HINT: This item is given to masters of the Martial Arts. Gather more pages to complete the scroll and prove your worth."
        )
        addTooltip(
            GenerationsItems.SECRET_ARMOR_SCROLL,
            "§7HINT: A Legendary Pokemon has taken notice to you- continue to prove your worth by defeating more Fighting type Pokemon"
        )

        //Steed Duo
        addTooltip(
            GenerationsItems.WHITE_MANE_HAIR,
            "§7HINT: This majestic steed requires a generous offering of carrots if you wish to lay your eyes upon them."
        )
        addTooltip(
            GenerationsItems.BLACK_MANE_HAIR,
            "§7HINT: This majestic steed requires a generous offering of carrots if you wish to lay your eyes upon them."
        )

        //Mew two
        addTooltip(GenerationsItems.MEW_DNA_FIBER, "§7HINT: DNA may be manipulated in an RKS Machine.")
        addTooltip(
            GenerationsItems.MEW_FOSSIL,
            "§7HINT: This may contain valuable DNA traces to recreate something in an RKS Machine."
        )
        addTooltip(GenerationsItems.SACRED_ASH, "§7HINT: A sacred item capable of bringing back the dead.")

        //Legendary Beasts
        addTooltip(GenerationsItems.SACRED_ASH, "§HINT: A sacred item capable of bringing back the dead.")

        add("generations_core.special.shadow.already", "%s heart is already closed.")
        add("generations_core.special.shadow.success", "%s heart has been closed.")
        add("generations_core.special.shadow.failure", "%s refused to clsoe its heart.")

        add("generations_core.ui.interact.head_pat", "Head Pat")
        add("generations_core.ability.formchange", "%s changed form!")

        add("generations_core.pokemon.fused", "%s was fused with %s.")
        add("generations_core.pokemon.defused", "%s was defused from %s.")
        add("generations_core.pokemon.encoded", "%s was encoded.")

        add(GenerationsEntities.STATUE_ENTITY.value(), "Statue")

        add("item.unimplemented_until_1_dot_6_cobblemon", "Currently unimplemented. Will be looked in 1.6+ cobblemon.")

        add("generations_core.ui.dna_fibers_extracted", "DNA Fibers")

        add("generations_core.pokemon.extracted_dna_fibers_max", "You have extracted all DNA fibers you could from %s.")
        add("generations_core.pokemon.extracted_dna_fiber_succeed", "DNA fiber extraction from %s successful!")
        add(
            "generations_core.pokemon.extracted_fibers_max",
            "Maximum amount of DNA fibers have been extracted from %s."
        )

        for (flavor in Flavor.entries) {
            add(
                "enum.flavor." + flavor.name.lowercase(Locale.getDefault()), this.getNameGens(
                    null, flavor.name.lowercase(
                        Locale.getDefault()
                    )
                )
            )
        }

        for (type in CurryType.entries) {
            add(
                "generations_core.curry." + type.name.lowercase(Locale.getDefault()),
                this.getNameGens(null, type.serializedName)
            )
        }

        this.add(GenerationsItems.ULTRITE_UPGRADE_SMITHING_TEMPLATE.value(), "Ultrite Upgrade Smithing Template")

        this.add("item.minecraft.smithing_template.ultrite_upgrade.ingredients", "Ultrite Ingot")
        this.add(
            "item.minecraft.smithing_template.ultrite_upgrade.base_slot_description",
            "Add Ultrite armor, weapon, or tool"
        )
        this.add("item.minecraft.smithing_template.ultrite_upgrade.additions_slot_description", "Add Ultrite Ingot")
        this.add("item.minecraft.smithing_template.ultrite_upgrade.applies_to", "Netherite Equipment")
        this.add("upgrade.minecraft.ultrite_upgrade", "Ultrite Upgrade Smithing Template")

        this.add("gui.recipe_viewer.category.rks_machine", "RKS Machine")
    }

    private fun recordDescriptions() {
        glitchCityRecordDescription(GenerationsItems.AZALEA_TOWN_DISC)
        glitchCityRecordDescription(GenerationsItems.CASCARRAFA_CITY_DISC)
        glitchCityRecordDescription(GenerationsItems.CERULEAN_CITY_DISC)
        glitchCityRecordDescription(GenerationsItems.ETERNA_CITY_DISC)
        glitchCityRecordDescription(GenerationsItems.GOLDENROD_CITY_DISC)
        glitchCityRecordDescription(GenerationsItems.ICIRRUS_CITY_DISC)
        glitchCityRecordDescription(GenerationsItems.JUBILIFE_VILLAGE_DISC)
        glitchCityRecordDescription(GenerationsItems.LAKE_OF_RAGE_DISC)
        glitchCityRecordDescription(GenerationsItems.LAVERRE_CITY_DISC)
        glitchCityRecordDescription(GenerationsItems.LILLIE_DISC)
        glitchCityRecordDescription(GenerationsItems.POKEMON_CENTER_DISC)
        glitchCityRecordDescription(GenerationsItems.ROUTE_228_DISC)
        glitchCityRecordDescription(GenerationsItems.SLUMBERING_WEALD_DISC)
        glitchCityRecordDescription(GenerationsItems.SURF_DISC)
        glitchCityRecordDescription(GenerationsItems.VERMILION_CITY_DISC)
        glitchCityRecordDescription(GenerationsItems.CYNTHIA_DISC)
        glitchCityRecordDescription(GenerationsItems.DEOXYS_DISC)
        glitchCityRecordDescription(GenerationsItems.IRIS_DISC)
        glitchCityRecordDescription(GenerationsItems.KANTO_DISC)
        glitchCityRecordDescription(GenerationsItems.LUSAMINE_DISC)
        glitchCityRecordDescription(GenerationsItems.NEMONA_DISC)
        glitchCityRecordDescription(GenerationsItems.NESSA_DISC)
        glitchCityRecordDescription(GenerationsItems.PENNY_DISC)
        glitchCityRecordDescription(GenerationsItems.RIVAL_DISC)
        glitchCityRecordDescription(GenerationsItems.SADA_AND_TURO_DISC)
        glitchCityRecordDescription(GenerationsItems.SOUTH_PROVINCE_DISC)
        glitchCityRecordDescription(GenerationsItems.TEAM_ROCKET_DISC)
        glitchCityRecordDescription(GenerationsItems.ULTRA_NECROZMA_DISC)
        glitchCityRecordDescription(GenerationsItems.XY_LEGENDARY_DISC)
        glitchCityRecordDescription(GenerationsItems.ZINNIA_DISC)
        glitchCityRecordDescription(GenerationsItems.LAVENDER_TOWN_DISC)
        glitchCityRecordDescription(GenerationsItems.LUGIA_DISC)
        glitchCityRecordDescription(GenerationsItems.MT_PYRE_DISC)
    }

    private fun glitchCityRecordDescription(item: Holder<Item>) {
        val name = item.id.toString().replace("_disc", "").asResource()
        add(
            Util.makeDescriptionId("jukebox_song", name),
            "GlitchxCity - " + getNameGens(item.value(), name.toString())
        )
    }


    protected fun getNameGens(item: ItemLike?, name: String): String {
        var name = name
        name = name.substring(name.indexOf(":") + 1) //Removes Mod Tag from front of name
        name = name.replace('_', ' ')
        name = name.substring(0, 1).uppercase(Locale.getDefault()) + name.substring(1)
        for (i in 0 until name.length) if (name[i] == ' ') name =
            name.substring(0, i + 1) + name.substring(i + 1, i + 2).uppercase(
                Locale.getDefault()
            ) + name.substring(i + 2)

        name = name.replace("Tm".toRegex(), "TM")
        if (name.contains("poke_brick")) return name.replace("Poke Brick", "PokeBrick")

        if (name.equals("hdtv", ignoreCase = true)) name = name.uppercase(Locale.getDefault())

        return name
    }

    protected fun getPokeBrickName(item: ItemLike?, name: String): String {
        return getNameGens(item, name).replace("Poke Brick", "PokeBrick")
    }

    fun addTooltip(registrySupplier: Holder<out ItemLike>, entry: String) {
        addTooltip(registrySupplier, null, entry)
    }

    fun addTooltip(registrySupplier: Holder<out ItemLike>, sub: String?, entry: String) {
        registrySupplier.value().asItem().instanceOrNull<LangTooltip>()?.run {
            add(this.tooltipId() + (if (sub != null) ".$sub" else ""), entry)

        }
    }

    @Deprecated("")
    fun addLegacyLang(key: String, entry: String) {
        add(key.replace(" ", "_").replace("-", "_").replace(",", "_"), entry)
    }

    fun addBlockEntries(
        entries: BlockPlatformRegistry,
        function: (Block?, String) -> String = this::getNameGens
    ) {
        entries.all().forEach({ block: Block ->
            add(
                block,
                function.invoke(block, block.id.toString())
            )
        })
    }

    fun addItemEntries(
        entries: ItemPlatformRegistry,
        function: (ItemLike?, String) -> String = this::getNameGens,
        additionalActions: (ItemLike, (ItemLike?, String) -> String?) -> Unit = { _, _ ->}
    ) {
        entries.all().forEach { item: Item ->
            add(item, function.invoke(item, item.id.toString()))
            additionalActions.invoke(item, function)
        }
    }

    override fun add(key: String, value: String) {
        try {
            super.add(key, value)
        } catch (e: IllegalStateException) {
            GenerationsCore.LOGGER.error("Duplicate translation key $key")
        }
    }
}
