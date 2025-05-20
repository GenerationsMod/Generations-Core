package generations.gg.generations.core.generationscore.common.world.level.block

import dev.architectury.registry.registries.DeferredRegister
import dev.architectury.registry.registries.RegistrySupplier
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.config.LegendKeys
import generations.gg.generations.core.generationscore.common.util.GenerationsUtils.registerBlock
import generations.gg.generations.core.generationscore.common.world.item.BlockItemWithLang
import generations.gg.generations.core.generationscore.common.world.item.DarkCrystalItem
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.FieryShrineBlock
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.FrozenShrineBlock
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.HoohShrineBlock
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.StaticShrineBlock
import generations.gg.generations.core.generationscore.common.world.level.block.shrines.*
import net.minecraft.core.BlockPos
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.EntityType
import net.minecraft.world.item.Item
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockState
import java.util.function.BiFunction
import java.util.function.Supplier

object GenerationsShrines {
    @JvmField
	val SHRINES: DeferredRegister<Block> = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.BLOCK)


    val SHRINE_PROPERTIES: BlockBehaviour.Properties =
        BlockBehaviour.Properties.of() /*of(Material.HEAVY_METAL) TODO: Verify we have all properties*/
            .strength(-1.0f, 3600000.8f).noLootTable()
            .isValidSpawn(Blocks::never).lightLevel { 5 }
    val BOTTLE_PROPERTIES: BlockBehaviour.Properties = BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)
        .isValidSpawn(Blocks::never)

    /**
     * Shrine Blocks
     */
    val FIERY_SHRINE: RegistrySupplier<Block> = registerBlockItem("fiery_shrine") { FieryShrineBlock(SHRINE_PROPERTIES) }
    val FROZEN_SHRINE: RegistrySupplier<Block> = registerBlockItem("frozen_shrine") { FrozenShrineBlock(SHRINE_PROPERTIES) }
    val STATIC_SHRINE: RegistrySupplier<Block> = registerBlockItem("static_shrine") { StaticShrineBlock(SHRINE_PROPERTIES) }
    val LUGIA_SHRINE: RegistrySupplier<Block> = registerBlockItem("lugia_shrine") { LugiaShrineBlock(SHRINE_PROPERTIES) }
    val HO_OH_SHRINE: RegistrySupplier<Block> = registerBlockItem("ho_oh_shrine") { HoohShrineBlock(SHRINE_PROPERTIES) }
    val GROUDON_SHRINE: RegistrySupplier<Block> = registerBlockItem("groudon_shrine") { WeatherTrioShrineBlock(SHRINE_PROPERTIES, GenerationsBlockEntityModels.GROUDON_SHRINE, LegendKeys.GROUDON, GenerationsItems.FADED_RED_ORB) }
    val KYOGRE_SHRINE: RegistrySupplier<Block> = registerBlockItem("kyogre_shrine") { WeatherTrioShrineBlock(SHRINE_PROPERTIES, GenerationsBlockEntityModels.KYOGRE_SHRINE, LegendKeys.KYOGRE, GenerationsItems.FADED_BLUE_ORB) }
    val TIMESPACE_ALTAR: RegistrySupplier<Block> = registerBlockItem("timespace_altar") { TimespaceAltarBlock(SHRINE_PROPERTIES) }
    @JvmField val ABUNDANT_SHRINE: RegistrySupplier<Block> = registerBlockItem("abundant_shrine") { AbundantShrineBlock(SHRINE_PROPERTIES) }
    val CELESTIAL_ALTAR: RegistrySupplier<Block> = registerBlockItem("celestial_altar",
        { CelestialAltarBlock(SHRINE_PROPERTIES) },
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
    @JvmField val LUNAR_SHRINE: RegistrySupplier<Block> = registerBlockItem("lunar_shrine") { LunarShrineBlock(SHRINE_PROPERTIES) }
    @JvmField val LIGHT_CRYSTAL: RegistrySupplier<Block> = registerBlockItem("light_crystal") { LunarCystalBlock(BlockBehaviour.Properties.of(), GenerationsBlockEntityModels.LIGHT_CRYSTAL) }
    @JvmField val DARK_CRYSTAL: RegistrySupplier<Block> = registerBlockItem("dark_crystal", { LunarCystalBlock(BlockBehaviour.Properties.of(), GenerationsBlockEntityModels.DARK_CRYSTAL) }, ::DarkCrystalItem)

    @JvmField val MELOETTA_MUSIC_BOX: RegistrySupplier<Block> = registerBlockItem("meloetta_music_box") { MeloettaMusicBoxBlock(SHRINE_PROPERTIES) }
    @JvmField val REGICE_SHRINE: RegistrySupplier<Block> = registerBlockItem("regice_shrine") { RegiShrineBlock(SHRINE_PROPERTIES, GenerationsBlockEntityModels.REGICE_SHRINE, LegendKeys.REGICE) }
    @JvmField val REGIDRAGO_SHRINE: RegistrySupplier<Block> = registerBlockItem("regidrago_shrine") { RegiShrineBlock(SHRINE_PROPERTIES, GenerationsBlockEntityModels.REGIDRAGO_SHRINE, LegendKeys.REGIDRAGO) }
    @JvmField val REGIELEKI_SHRINE: RegistrySupplier<Block> = registerBlockItem("regieleki_shrine") { RegiShrineBlock(SHRINE_PROPERTIES, GenerationsBlockEntityModels.REGIELEKI_SHRINE, LegendKeys.REGIELEKI) }
    @JvmField val REGIROCK_SHRINE: RegistrySupplier<Block> = registerBlockItem("regirock_shrine") { RegiShrineBlock(SHRINE_PROPERTIES, GenerationsBlockEntityModels.REGIROCK_SHRINE, LegendKeys.REGIROCK) }
    @JvmField val REGISTEEL_SHRINE: RegistrySupplier<Block> = registerBlockItem("registeel_shrine") { RegiShrineBlock(SHRINE_PROPERTIES, GenerationsBlockEntityModels.REGISTEEL_SHRINE, LegendKeys.REGISTEEL) }
    @JvmField val REGIGIGAS_SHRINE: RegistrySupplier<Block> = registerBlockItem("regigigas_shrine") { RegigigasShrineBlock(SHRINE_PROPERTIES) }
    @JvmField val TAO_TRIO_SHRINE: RegistrySupplier<Block> = registerBlockItem("tao_trio_shrine") { TaoTrioShrineBlock(SHRINE_PROPERTIES) }
    val TAPU_SHRINE: RegistrySupplier<Block> = registerBlockItem("tapu_shrine") { TapuShrineBlock(SHRINE_PROPERTIES) }

    @JvmField val PRISON_BOTTLE_STEM: RegistrySupplier<PrisonBottleStemBlock> = registerBlockItem("prison_bottle_stem", { PrisonBottleStemBlock(BOTTLE_PROPERTIES) }, ::BlockItemWithLang, GenerationsItems.LEGENDARY_ITEMS)
    val PRISON_BOTTLE: RegistrySupplier<PrisonBottleBlock> = registerBlockItem(
        "prison_bottle",
        { PrisonBottleBlock(BOTTLE_PROPERTIES) },
        { block, properties -> FormChangingBlockItem(block, properties, "unbound") }, GenerationsItems.LEGENDARY_ITEMS
    )


    private fun <T : Block> registerBlockItem(name: String, blockSupplier: Supplier<T>, function: BiFunction<Block, Item.Properties, Item>, register: DeferredRegister<Item>): RegistrySupplier<T> {
        val block = registerBlock(SHRINES, name, blockSupplier)
        register.register(name) { function.apply(block.get(), Item.Properties()) }
        return block
    }


    private fun <T : Block> registerBlockItem(name: String, blockSupplier: Supplier<T>): RegistrySupplier<T> = registerBlockItem(name, blockSupplier, ::BlockItemWithLang)


    private fun <T : Block> registerBlockItem(name: String, blockSupplier: Supplier<T>, function: BiFunction<Block, Item.Properties, Item>): RegistrySupplier<T> = registerBlockItem(name, blockSupplier, function, GenerationsItems.ITEMS)


    fun init() {
        GenerationsCore.LOGGER.info("Registering Generations Shrines")
        SHRINES.register()
    }
}
