package generations.gg.generations.core.generationscore.common.world.item.creativetab

import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.level.block.*
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.ItemLike
import java.util.function.Supplier

/**
 * Generations Creative Tabs
 * @author Joseph T. McQuigg
 * @author WaterPicker
 * @see CreativeModeTab
 */
object GenerationsCreativeTabs {
    var RESTORATION: Supplier<CreativeModeTab>? = null
    var AWARDS: Supplier<CreativeModeTab>? = null
    var HELD_ITEMS: Supplier<CreativeModeTab>? = null
    var PLAYER_ITEMS: Supplier<CreativeModeTab>? = null
    var LEGENDARY_ITEMS: Supplier<CreativeModeTab>? = null
    var BUILDING_BLOCKS: Supplier<CreativeModeTab>? = null
    var DECORATIONS: Supplier<CreativeModeTab>? = null
    var UTILITY: Supplier<CreativeModeTab>? = null
    var FORM_ITEMS: Supplier<CreativeModeTab>? = null
    var POKEMAIL: Supplier<CreativeModeTab>? = null
    var VALUABLES: Supplier<CreativeModeTab>? = null
    var POKEDOLLS: Supplier<CreativeModeTab>? = null
    var CUISINE: Supplier<CreativeModeTab>? = null
    var UNIMPLEMENTED: Supplier<CreativeModeTab>? = null
    var SHRINES: Supplier<CreativeModeTab>? = null

    fun init() {
        GenerationsCore.LOGGER.info("Registering Generations Creative Tabs...")
        BUILDING_BLOCKS = GenerationsCore.implementation.create(
            "building_blocks",
            { GenerationsBlocks.LIGHT_BLUE_POKE_BRICK_SET.baseBlockSupplier.get().asItem().defaultInstance },
            GenerationsWood.WOOD_BLOCKS,
            GenerationsBlocks.BLOCK_ITEMS,
            GenerationsOres.ORES,
            GenerationsItems.BUILDING_BLOCKS
        )
        RESTORATION = GenerationsCore.implementation.create(
            "restoration",
            { GenerationsItems.LEMONADE.get().defaultInstance }, GenerationsItems.RESTORATION
        )
        AWARDS = GenerationsCore.implementation.create(
            "awards",
            { GenerationsItems.RAINBOW_BADGE.get().defaultInstance }, GenerationsItems.BADGES, GenerationsItems.RIBBONS
        )
        HELD_ITEMS = GenerationsCore.implementation.create(
            "held_items",
            { GenerationsItems.AMULET_COIN.get().defaultInstance }, GenerationsItems.HELD_ITEMS
        )
        PLAYER_ITEMS = GenerationsCore.implementation.create(
            "player_items",
            { GenerationsItems.POKEDEX.get().defaultInstance }, GenerationsItems.PLAYER_ITEMS, GenerationsItems.NATURAL
        )
        LEGENDARY_ITEMS = GenerationsCore.implementation.create(
            "legendary_items",
            { GenerationsItems.DNA_SPLICERS.get().defaultInstance }, GenerationsItems.LEGENDARY_ITEMS
        )
        DECORATIONS = GenerationsCore.implementation.create(
            "decorations",
            { GenerationsDecorationBlocks.SWITCH.get().asItem().defaultInstance },
            GenerationsDecorationBlocks.DECORATIONS
        )
        UTILITY = GenerationsCore.implementation.create(
            "utility",
            { GenerationsUtilityBlocks.RKS_MACHINE.get().asItem().defaultInstance },
            GenerationsItems.UTILITY,
            GenerationsUtilityBlocks.UTILITY_BLOCKS
        )
        FORM_ITEMS = GenerationsCore.implementation.create(
            "form_items",
            { GenerationsItems.METEORITE.get().defaultInstance }, GenerationsItems.FORM_ITEMS
        )
        POKEMAIL = GenerationsCore.implementation.create(
            "pokemail",
            { GenerationsItems.POKEMAIL_AIR.get().defaultInstance }, GenerationsItems.POKEMAIL
        )
        VALUABLES = GenerationsCore.implementation.create(
            "valuables",
            { GenerationsItems.STRANGE_SOUVENIR.get().defaultInstance }, GenerationsItems.VALUABLES
        )
        POKEDOLLS = GenerationsCore.implementation.create(
            "pokedolls",
            { GenerationsPokeDolls.PIKACHU_POKEDOLL.get().asItem().defaultInstance }, GenerationsPokeDolls.POKEDOLLS
        )
        CUISINE = GenerationsCore.implementation.create(
            "cuisine",
            { GenerationsItems.GIGANTAMIX.get().defaultInstance }, GenerationsItems.CUISINE
        )
        UNIMPLEMENTED = GenerationsCore.implementation.create(
            "unimplemented",
            { GenerationsItems.ABILITY_URGE.get().defaultInstance }, GenerationsItems.UNIMPLEMENTED
        )
        SHRINES = GenerationsCore.implementation.create(
            "shrines",
            { GenerationsShrines.FROZEN_SHRINE.get().asItem().defaultInstance }, GenerationsShrines.SHRINES
        )
        GenerationsCore.LOGGER.info("Registered Generations Creative Tabs!")
    }
}
