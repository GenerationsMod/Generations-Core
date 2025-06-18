package generations.gg.generations.core.generationscore.common.world.item.creativetab

import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.util.PlatformRegistry
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.level.block.*
import net.minecraft.core.Holder
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.ItemLike

/**
 * Generations Creative Tabs
 * @author Joseph T. McQuigg
 * @author WaterPicker
 * @see CreativeModeTab
 */
object GenerationsCreativeTabs: PlatformRegistry<CreativeModeTab>(Registries.CREATIVE_MODE_TAB, BuiltInRegistries.CREATIVE_MODE_TAB) {

    val BUILDING_BLOCKS = createTab("building_blocks", { GenerationsBlocks.LIGHT_BLUE_POKE_BRICK_SET.baseBlock.asItem().defaultInstance }, GenerationsWood.WOOD_BLOCKS, GenerationsItems.BLOCK_ITEMS, GenerationsOres, GenerationsItems.BUILDING_BLOCKS)
    val RESTORATION = createTab("restoration", { GenerationsItems.LEMONADE.value().defaultInstance }, GenerationsItems.RESTORATION)
    val AWARDS = createTab("awards", { GenerationsItems.RAINBOW_BADGE.value().defaultInstance }, GenerationsItems.BADGES, GenerationsItems.RIBBONS)
    val HELD_ITEMS = createTab("held_items", { GenerationsItems.AMULET_COIN.value().defaultInstance }, GenerationsItems.HELD_ITEMS)
    val PLAYER_ITEMS = createTab("player_items", { GenerationsItems.POKEDEX.value().defaultInstance }, GenerationsItems.PLAYER_ITEMS, GenerationsItems.NATURAL)
    val LEGENDARY_ITEMS = createTab("legendary_items",{ GenerationsItems.DNA_SPLICERS.value().defaultInstance }, GenerationsItems.LEGENDARY_ITEMS)
    val DECORATIONS = createTab("decorations", { GenerationsDecorationBlocks.SWITCH.value().asItem().defaultInstance }, GenerationsDecorationBlocks)
    val UTILITY = createTab("utility", { GenerationsUtilityBlocks.RKS_MACHINE.value().asItem().defaultInstance }, GenerationsItems.UTILITY, GenerationsUtilityBlocks)
    val FORM_ITEMS = createTab("form_items", { GenerationsItems.METEORITE.value().defaultInstance }, GenerationsItems.FORM_ITEMS)
    val POKEMAIL = createTab("pokemail", { GenerationsItems.POKEMAIL_AIR.value().defaultInstance }, GenerationsItems.POKEMAIL)
    val VALUABLES = createTab("valuables", { GenerationsItems.STRANGE_SOUVENIR.value().defaultInstance }, GenerationsItems.VALUABLES)
    val POKEDOLLS = createTab("pokedolls", { GenerationsPokeDolls.PIKACHU_POKEDOLL.value().asItem().defaultInstance }, GenerationsPokeDolls)
    val CUISINE = createTab("cuisine", { GenerationsItems.GIGANTAMIX.value().defaultInstance }, GenerationsItems.CUISINE)
    val UNIMPLEMENTED = createTab("unimplemented", { GenerationsItems.ABILITY_URGE.value().defaultInstance }, GenerationsItems.UNIMPLEMENTED)
    val SHRINES = createTab("shrines", { GenerationsShrines.FROZEN_SHRINE.value().asItem().defaultInstance }, GenerationsShrines)

    override fun init() {
        GenerationsCore.LOGGER.info("Registering Generations Creative Tabs...")
        super.init()
        GenerationsCore.LOGGER.info("Registered Generations Creative Tabs!")
    }

    private fun createTab(name: String, suppler: () -> ItemStack, vararg items: PlatformRegistry<out ItemLike>): Holder<CreativeModeTab> = create(name, { GenerationsCore.implementation.create(name, suppler, items) })
}
