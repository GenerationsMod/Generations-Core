package generations.gg.generations.core.generationscore.common.world.item.creativetab

import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.generationsResource
import generations.gg.generations.core.generationscore.common.util.PlatformRegistry
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.level.block.*
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.ItemLike

/**
 * Generations Creative Tabs
 * @author Joseph T. McQuigg
 * @author WaterPicker
 * @see CreativeModeTab
 */
object GenerationsCreativeTabs: PlatformRegistry<CreativeModeTab>() {
    override val registry: Registry<CreativeModeTab> = BuiltInRegistries.CREATIVE_MODE_TAB
    override val resourceKey: ResourceKey<Registry<CreativeModeTab>> = Registries.CREATIVE_MODE_TAB

    val BUILDING_BLOCKS = create("building_blocks", { GenerationsBlocks.LIGHT_BLUE_POKE_BRICK_SET.baseBlock.asItem().defaultInstance }, GenerationsWood.WOOD_BLOCKS, GenerationsBlocks.BLOCK_ITEMS, GenerationsOres, GenerationsItems.BUILDING_BLOCKS)
    val RESTORATION = create("restoration", { GenerationsItems.LEMONADE.defaultInstance }, GenerationsItems.RESTORATION)
    val AWARDS = create("awards", { GenerationsItems.RAINBOW_BADGE.defaultInstance }, GenerationsItems.BADGES, GenerationsItems.RIBBONS)
    val HELD_ITEMS = create("held_items", { GenerationsItems.AMULET_COIN.defaultInstance }, GenerationsItems.HELD_ITEMS)
    val PLAYER_ITEMS = create("player_items", { GenerationsItems.POKEDEX.defaultInstance }, GenerationsItems.PLAYER_ITEMS, GenerationsItems.NATURAL)
    val LEGENDARY_ITEMS = create("legendary_items",{ GenerationsItems.DNA_SPLICERS.defaultInstance }, GenerationsItems.LEGENDARY_ITEMS)
    val DECORATIONS = create("decorations", { GenerationsDecorationBlocks.SWITCH.asItem().defaultInstance }, GenerationsDecorationBlocks)
    val UTILITY = create("utility", { GenerationsUtilityBlocks.RKS_MACHINE.asItem().defaultInstance }, GenerationsItems.UTILITY, GenerationsUtilityBlocks)
    val FORM_ITEMS = create("form_items", { GenerationsItems.METEORITE.defaultInstance }, GenerationsItems.FORM_ITEMS)
    val POKEMAIL = create("pokemail", { GenerationsItems.POKEMAIL_AIR.defaultInstance }, GenerationsItems.POKEMAIL)
    val VALUABLES = create("valuables", { GenerationsItems.STRANGE_SOUVENIR.defaultInstance }, GenerationsItems.VALUABLES)
    val POKEDOLLS = create("pokedolls", { GenerationsPokeDolls.PIKACHU_POKEDOLL.asItem().defaultInstance }, GenerationsPokeDolls)
    val CUISINE = create("cuisine", { GenerationsItems.GIGANTAMIX.defaultInstance }, GenerationsItems.CUISINE)
    val UNIMPLEMENTED = create("unimplemented", { GenerationsItems.ABILITY_URGE.defaultInstance }, GenerationsItems.UNIMPLEMENTED)
    val SHRINES = create("shrines", { GenerationsShrines.FROZEN_SHRINE.asItem().defaultInstance }, GenerationsShrines)

    override fun init(consumer: (ResourceLocation, CreativeModeTab) -> Unit) {
        GenerationsCore.LOGGER.info("Registering Generations Creative Tabs...")
        super.init(consumer)
        GenerationsCore.LOGGER.info("Registered Generations Creative Tabs!")
    }

    private fun create(name: String, suppler: () -> ItemStack, vararg items: PlatformRegistry<out ItemLike>): CreativeModeTab = create(name.generationsResource(), GenerationsCore.implementation.create(name, suppler, items))
}
