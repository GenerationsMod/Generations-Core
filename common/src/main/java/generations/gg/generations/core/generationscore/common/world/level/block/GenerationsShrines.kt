package generations.gg.generations.core.generationscore.common.world.level.block

import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.config.LegendKeys
import generations.gg.generations.core.generationscore.common.generationsResource
import generations.gg.generations.core.generationscore.common.util.GenerationsUtils.registerBlock
import generations.gg.generations.core.generationscore.common.util.ItemPlatformRegistry
import generations.gg.generations.core.generationscore.common.world.item.BlockItemWithLang
import generations.gg.generations.core.generationscore.common.world.item.DarkCrystalItem
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.FieryShrineBlock
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.FrozenShrineBlock
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.HoohShrineBlock
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.StaticShrineBlock
import generations.gg.generations.core.generationscore.common.world.level.block.shrines.*
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockState

object GenerationsShrines: BlockPlatformRegistry() {

    val SHRINE_PROPERTIES: BlockBehaviour.Properties =
        BlockBehaviour.Properties.of() /*of(Material.HEAVY_METAL) TODO: Verify we have all properties*/
            .strength(-1.0f, 3600000.8f).noLootTable()
            .isValidSpawn(Blocks::never).lightLevel { 5 }
    val BOTTLE_PROPERTIES: BlockBehaviour.Properties = BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)
        .isValidSpawn(Blocks::never)

    /**
     * Shrine Blocks
     */
    val FIERY_SHRINE = registerBlockItem("fiery_shrine", FieryShrineBlock(SHRINE_PROPERTIES))
    val FROZEN_SHRINE = registerBlockItem("frozen_shrine", FrozenShrineBlock(SHRINE_PROPERTIES))
    val STATIC_SHRINE = registerBlockItem("static_shrine", StaticShrineBlock(SHRINE_PROPERTIES))
    val LUGIA_SHRINE = registerBlockItem("lugia_shrine", LugiaShrineBlock(SHRINE_PROPERTIES))
    val HO_OH_SHRINE = registerBlockItem("ho_oh_shrine", HoohShrineBlock(SHRINE_PROPERTIES))
    val GROUDON_SHRINE = registerBlockItem("groudon_shrine", WeatherTrioShrineBlock(SHRINE_PROPERTIES, GenerationsBlockEntityModels.GROUDON_SHRINE, LegendKeys.GROUDON, GenerationsItems.FADED_RED_ORB))
    val KYOGRE_SHRINE = registerBlockItem("kyogre_shrine", WeatherTrioShrineBlock(SHRINE_PROPERTIES, GenerationsBlockEntityModels.KYOGRE_SHRINE, LegendKeys.KYOGRE, GenerationsItems.FADED_BLUE_ORB))
    val TIMESPACE_ALTAR = registerBlockItem("timespace_altar", TimespaceAltarBlock(SHRINE_PROPERTIES))
    @JvmField val ABUNDANT_SHRINE = registerBlockItem("abundant_shrine", AbundantShrineBlock(SHRINE_PROPERTIES))
    val CELESTIAL_ALTAR = registerBlockItem("celestial_altar", CelestialAltarBlock(SHRINE_PROPERTIES),
        { block, properties ->
            object : BlockItemWithLang(block, properties) {
                override fun placeBlock(context: BlockPlaceContext, state: BlockState): Boolean {
                    if (context.level.dimensionType().natural() && super.placeBlock(
                            context,
                            state
                        )
                    ) return true
                    else {
                        context.player!!.displayClientMessage(
                            Component.literal("You can not place the celestial altar in an unnatural place!"),
                            true
                        )
                        return false
                    }
                }
            }
        })
    @JvmField val LUNAR_SHRINE = registerBlockItem("lunar_shrine", LunarShrineBlock(SHRINE_PROPERTIES))
    @JvmField val LIGHT_CRYSTAL = registerBlockItem("light_crystal", LunarCystalBlock(BlockBehaviour.Properties.of(), GenerationsBlockEntityModels.LIGHT_CRYSTAL))
    @JvmField val DARK_CRYSTAL = registerBlockItem("dark_crystal", LunarCystalBlock(BlockBehaviour.Properties.of(), GenerationsBlockEntityModels.DARK_CRYSTAL), ::DarkCrystalItem)

    @JvmField val MELOETTA_MUSIC_BOX = registerBlockItem("meloetta_music_box", MeloettaMusicBoxBlock(SHRINE_PROPERTIES))
    @JvmField val REGICE_SHRINE = registerBlockItem("regice_shrine", RegiShrineBlock(SHRINE_PROPERTIES, GenerationsBlockEntityModels.REGICE_SHRINE, LegendKeys.REGICE))
    @JvmField val REGIDRAGO_SHRINE = registerBlockItem("regidrago_shrine", RegiShrineBlock(SHRINE_PROPERTIES, GenerationsBlockEntityModels.REGIDRAGO_SHRINE, LegendKeys.REGIDRAGO))
    @JvmField val REGIELEKI_SHRINE = registerBlockItem("regieleki_shrine", RegiShrineBlock(SHRINE_PROPERTIES, GenerationsBlockEntityModels.REGIELEKI_SHRINE, LegendKeys.REGIELEKI))
    @JvmField val REGIROCK_SHRINE = registerBlockItem("regirock_shrine", RegiShrineBlock(SHRINE_PROPERTIES, GenerationsBlockEntityModels.REGIROCK_SHRINE, LegendKeys.REGIROCK))
    @JvmField val REGISTEEL_SHRINE = registerBlockItem("registeel_shrine", RegiShrineBlock(SHRINE_PROPERTIES, GenerationsBlockEntityModels.REGISTEEL_SHRINE, LegendKeys.REGISTEEL))
    @JvmField val REGIGIGAS_SHRINE = registerBlockItem("regigigas_shrine", RegigigasShrineBlock(SHRINE_PROPERTIES))
    @JvmField val TAO_TRIO_SHRINE = registerBlockItem("tao_trio_shrine", TaoTrioShrineBlock(SHRINE_PROPERTIES))
    val TAPU_SHRINE = registerBlockItem("tapu_shrine", TapuShrineBlock(SHRINE_PROPERTIES))

    @JvmField val PRISON_BOTTLE_STEM = registerBlockItem("prison_bottle_stem", PrisonBottleStemBlock(BOTTLE_PROPERTIES), ::BlockItemWithLang, GenerationsItems.LEGENDARY_ITEMS)
    val PRISON_BOTTLE = registerBlockItem("prison_bottle", PrisonBottleBlock(BOTTLE_PROPERTIES), { block, properties -> PokemonInteractBlockItem(block, properties, "unbound")  }, GenerationsItems.LEGENDARY_ITEMS)


    private fun <T : Block> registerBlockItem(name: String, blockSupplier: T, function: (Block, Item.Properties) ->  Item, register: ItemPlatformRegistry): T {
        val block = registerBlock(this, name, blockSupplier)
        register.create(name.generationsResource(), function.invoke(block, Item.Properties()))
        return block
    }


    private fun <T : Block> registerBlockItem(name: String, blockSupplier: T): T = registerBlockItem(name, blockSupplier, ::BlockItemWithLang)


    private fun <T : Block> registerBlockItem(name: String, blockSupplier: T, function: (Block, Item.Properties) -> Item): T = registerBlockItem(name, blockSupplier, function, GenerationsItems.ITEMS)

    override fun init(consumer: (ResourceLocation, Block) -> Unit) {
        GenerationsCore.LOGGER.info("Registering Generations Shrines")
        super.init(consumer)
    }
}
