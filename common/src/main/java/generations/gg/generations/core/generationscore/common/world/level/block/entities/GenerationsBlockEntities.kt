package generations.gg.generations.core.generationscore.common.world.level.block.entities

import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.generationsResource
import generations.gg.generations.core.generationscore.common.util.PlatformRegistry
import generations.gg.generations.core.generationscore.common.world.level.block.*
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks.GREATBALL_CHEST
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks.MASTERBALL_CHEST
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks.POKEBALL_CHEST
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks.ULTRABALL_CHEST
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.*
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.*
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.altar.CelestialAltarBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.altar.TimeSpaceAltarBlockEntity
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.entity.HangingSignBlockEntity
import net.minecraft.world.level.block.entity.SignBlockEntity

object GenerationsBlockEntities: PlatformRegistry<BlockEntityType<*>>() {
    override val registry: Registry<BlockEntityType<*>> = BuiltInRegistries.BLOCK_ENTITY_TYPE
    override val resourceKey: ResourceKey<Registry<BlockEntityType<*>>> = Registries.BLOCK_ENTITY_TYPE

    val POKE_DOLL = registerRegular("pokedoll", ::PokeDollBlockEntity,
        GenerationsPokeDolls.ARCEUS_POKEDOLL, GenerationsPokeDolls.ARTICUNO_POKEDOLL, GenerationsPokeDolls.AZELF_POKEDOLL, GenerationsPokeDolls.AZURILL_POKEDOLL,
        GenerationsPokeDolls.BALTOY_POKEDOLL, GenerationsPokeDolls.BLASTOISE_POKEDOLL, GenerationsPokeDolls.BLUEGHOST_POKEDOLL, GenerationsPokeDolls.CELEBI_POKEDOLL,
        GenerationsPokeDolls.CHARIZARD_POKEDOLL, GenerationsPokeDolls.CHIKORITA_POKEDOLL, GenerationsPokeDolls.CLEFAIRY_POKEDOLL, GenerationsPokeDolls.CRESSELIA_POKEDOLL,
        GenerationsPokeDolls.CUBONE_POKEDOLL, GenerationsPokeDolls.CYNDAQUIL_POKEDOLL, GenerationsPokeDolls.DARKRAI_POKEDOLL, GenerationsPokeDolls.DEOXYS_POKEDOLL,
        GenerationsPokeDolls.DIALGA_POKEDOLL, GenerationsPokeDolls.DITTO_POKEDOLL, GenerationsPokeDolls.DUSKULL_POKEDOLL, GenerationsPokeDolls.EEVEE_POKEDOLL,
        GenerationsPokeDolls.EKANS_POKEDOLL, GenerationsPokeDolls.ENTEI_POKEDOLL, GenerationsPokeDolls.GIRATINA_ALTERED_POKEDOLL, GenerationsPokeDolls.GIRATINA_ORIGINAL_POKEDOLL,
        GenerationsPokeDolls.GROUDON_POKEDOLL, GenerationsPokeDolls.GULPIN_POKEDOLL, GenerationsPokeDolls.HERACROSS_POKEDOLL, GenerationsPokeDolls.HOOH_POKEDOLL,
        GenerationsPokeDolls.JIGGLYPUFF_POKEDOLL, GenerationsPokeDolls.JIRACHI_POKEDOLL, GenerationsPokeDolls.KECLEON_POKEDOLL, GenerationsPokeDolls.KRABBY_POKEDOLL,
        GenerationsPokeDolls.KYOGRE_POKEDOLL, GenerationsPokeDolls.LAPRAS_POKEDOLL, GenerationsPokeDolls.LATIAS_POKEDOLL, GenerationsPokeDolls.LATIOS_POKEDOLL,
        GenerationsPokeDolls.LITTEN_POKEDOLL, GenerationsPokeDolls.LOTAD_POKEDOLL, GenerationsPokeDolls.LUGIA_POKEDOLL, GenerationsPokeDolls.MANAPHY_POKEDOLL,
        GenerationsPokeDolls.MARILL_POKEDOLL, GenerationsPokeDolls.MEOWTH_POKEDOLL, GenerationsPokeDolls.MESPRIT_POKEDOLL, GenerationsPokeDolls.MEW_POKEDOLL,
        GenerationsPokeDolls.MEWTWO_POKEDOLL, GenerationsPokeDolls.MOLTRES_POKEDOLL, GenerationsPokeDolls.MUDKIP_POKEDOLL, GenerationsPokeDolls.PALKIA_POKEDOLL,
        GenerationsPokeDolls.PICHU_POKEDOLL, GenerationsPokeDolls.PIKACHU_POKEDOLL, GenerationsPokeDolls.POPPLIO_POKEDOLL, GenerationsPokeDolls.POLIWHIRL_POKEDOLL,
        GenerationsPokeDolls.PURPLEGHOST_POKEDOLL, GenerationsPokeDolls.RAIKOU_POKEDOLL, GenerationsPokeDolls.RAYQUAZA_POKEDOLL, GenerationsPokeDolls.REGICE_POKEDOLL,
        GenerationsPokeDolls.REGIGIGAS_POKEDOLL, GenerationsPokeDolls.REGIROCK_POKEDOLL, GenerationsPokeDolls.REGISTEEL_POKEDOLL, GenerationsPokeDolls.RHYDON_POKEDOLL,
        GenerationsPokeDolls.ROWLET_POKEDOLL, GenerationsPokeDolls.SABLEYE_POKEDOLL, GenerationsPokeDolls.SEEDOT_POKEDOLL, GenerationsPokeDolls.SHAYMIN_LAND_POKEDOLL,
        GenerationsPokeDolls.SHAYMIN_SKY_POKEDOLL, GenerationsPokeDolls.SKITTY_POKEDOLL, GenerationsPokeDolls.SMOOCHUM_POKEDOLL, GenerationsPokeDolls.SNORLAX_POKEDOLL,
        GenerationsPokeDolls.SUBSTITUTE_POKEDOLL, GenerationsPokeDolls.SUICUNE_POKEDOLL, GenerationsPokeDolls.SWABLU_POKEDOLL, GenerationsPokeDolls.TOGEPI_POKEDOLL,
        GenerationsPokeDolls.TORCHIC_POKEDOLL, GenerationsPokeDolls.TOTODILE_POKEDOLL, GenerationsPokeDolls.TREECKO_POKEDOLL, GenerationsPokeDolls.UXIE_POKEDOLL,
        GenerationsPokeDolls.VENUSAUR_POKEDOLL, GenerationsPokeDolls.WAILMER_POKEDOLL, GenerationsPokeDolls.WYNAUT_POKEDOLL, GenerationsPokeDolls.ZAPDOS_POKEDOLL,
        GenerationsPokeDolls.ZERAORA_POKEDOLL, GenerationsPokeDolls.SHINY_ARCEUS_POKEDOLL, GenerationsPokeDolls.SHINY_ARTICUNO_POKEDOLL, GenerationsPokeDolls.SHINY_AZELF_POKEDOLL,
        GenerationsPokeDolls.SHINY_AZURILL_POKEDOLL, GenerationsPokeDolls.SHINY_BALTOY_POKEDOLL, GenerationsPokeDolls.SHINY_BLASTOISE_POKEDOLL, GenerationsPokeDolls.SHINY_CELEBI_POKEDOLL,
        GenerationsPokeDolls.SHINY_CHARIZARD_POKEDOLL, GenerationsPokeDolls.SHINY_CHIKORITA_POKEDOLL, GenerationsPokeDolls.SHINY_CLEFAIRY_POKEDOLL, GenerationsPokeDolls.SHINY_CRESSELIA_POKEDOLL,
        GenerationsPokeDolls.SHINY_CUBONE_POKEDOLL, GenerationsPokeDolls.SHINY_CYNDAQUIL_POKEDOLL, GenerationsPokeDolls.SHINY_DARKRAI_POKEDOLL, GenerationsPokeDolls.SHINY_DEOXYS_POKEDOLL,
        GenerationsPokeDolls.SHINY_DIALGA_POKEDOLL, GenerationsPokeDolls.SHINY_DITTO_POKEDOLL, GenerationsPokeDolls.SHINY_DUSKULL_POKEDOLL, GenerationsPokeDolls.SHINY_EEVEE_POKEDOLL,
        GenerationsPokeDolls.SHINY_EKANS_POKEDOLL, GenerationsPokeDolls.SHINY_ENTEI_POKEDOLL, GenerationsPokeDolls.SHINY_GIRATINA_ALTERED_POKEDOLL, GenerationsPokeDolls.SHINY_GIRATINA_ORIGIN_POKEDOLL,
        GenerationsPokeDolls.SHINY_GROUDON_POKEDOLL, GenerationsPokeDolls.SHINY_GULPIN_POKEDOLL, GenerationsPokeDolls.SHINY_HERACROSS_POKEDOLL, GenerationsPokeDolls.SHINY_HOOH_POKEDOLL,
        GenerationsPokeDolls.SHINY_JIGGLYPUFF_POKEDOLL, GenerationsPokeDolls.SHINY_JIRACHI_POKEDOLL, GenerationsPokeDolls.SHINY_KECLEON_POKEDOLL, GenerationsPokeDolls.SHINY_KRABBY_POKEDOLL,
        GenerationsPokeDolls.SHINY_KYOGRE_POKEDOLL, GenerationsPokeDolls.SHINY_LAPRAS_POKEDOLL, GenerationsPokeDolls.SHINY_LATIAS_POKEDOLL, GenerationsPokeDolls.SHINY_LATIOS_POKEDOLL,
        GenerationsPokeDolls.SHINY_LITTEN_POKEDOLL, GenerationsPokeDolls.SHINY_LOTAD_POKEDOLL, GenerationsPokeDolls.SHINY_LUGIA_POKEDOLL, GenerationsPokeDolls.SHINY_MANAPHY_POKEDOLL,
        GenerationsPokeDolls.SHINY_MARILL_POKEDOLL, GenerationsPokeDolls.SHINY_MEOWTH_POKEDOLL, GenerationsPokeDolls.SHINY_MESPRIT_POKEDOLL, GenerationsPokeDolls.SHINY_MEW_POKEDOLL,
        GenerationsPokeDolls.SHINY_MEWTWO_POKEDOLL, GenerationsPokeDolls.SHINY_MOLTRES_POKEDOLL, GenerationsPokeDolls.SHINY_MUDKIP_POKEDOLL, GenerationsPokeDolls.SHINY_PALKIA_POKEDOLL,
        GenerationsPokeDolls.SHINY_PICHU_POKEDOLL, GenerationsPokeDolls.SHINY_PIKACHU_POKEDOLL, GenerationsPokeDolls.SHINY_POPPLIO_POKEDOLL, GenerationsPokeDolls.SHINY_POLIWHIRL_POKEDOLL,
        GenerationsPokeDolls.SHINY_RAIKOU_POKEDOLL, GenerationsPokeDolls.SHINY_RAYQUAZA_POKEDOLL, GenerationsPokeDolls.SHINY_REGICE_POKEDOLL, GenerationsPokeDolls.SHINY_REGIGIGAS_POKEDOLL,
        GenerationsPokeDolls.SHINY_REGIROCK_POKEDOLL, GenerationsPokeDolls.SHINY_REGISTEEL_POKEDOLL, GenerationsPokeDolls.SHINY_ROWLET_POKEDOLL, GenerationsPokeDolls.SHINY_SABLEYE_POKEDOLL,
        GenerationsPokeDolls.SHINY_SEEDOT_POKEDOLL, GenerationsPokeDolls.SHINY_RHYDON_POKEDOLL, GenerationsPokeDolls.SHINY_SHAYMIN_LAND_POKEDOLL, GenerationsPokeDolls.SHINY_SHAYMIN_SKY_POKEDOLL,
        GenerationsPokeDolls.SHINY_SKITTY_POKEDOLL, GenerationsPokeDolls.SHINY_SMOOCHUM_POKEDOLL, GenerationsPokeDolls.SHINY_SNORLAX_POKEDOLL, GenerationsPokeDolls.SHINY_SUBSTITUTE_POKEDOLL,
        GenerationsPokeDolls.SHINY_SUICUNE_POKEDOLL, GenerationsPokeDolls.SHINY_SWABLU_POKEDOLL, GenerationsPokeDolls.SHINY_TOGEPI_POKEDOLL, GenerationsPokeDolls.SHINY_TORCHIC_POKEDOLL,
        GenerationsPokeDolls.SHINY_TOTODILE_POKEDOLL, GenerationsPokeDolls.SHINY_TREECKO_POKEDOLL, GenerationsPokeDolls.SHINY_UXIE_POKEDOLL, GenerationsPokeDolls.SHINY_VENUSAUR_POKEDOLL,
        GenerationsPokeDolls.SHINY_WAILMER_POKEDOLL, GenerationsPokeDolls.SHINY_WYNAUT_POKEDOLL, GenerationsPokeDolls.SHINY_ZERAORA_POKEDOLL, GenerationsPokeDolls.SHINY_ZAPDOS_POKEDOLL
    )
    val GENERIC_SHRINE = registerRegular("generic_shrine", ::GenericShrineBlockEntity,
        GenerationsShrines.FROZEN_SHRINE, GenerationsShrines.STATIC_SHRINE, GenerationsShrines.FIERY_SHRINE, GenerationsShrines.HO_OH_SHRINE,
        GenerationsShrines.LUGIA_SHRINE, GenerationsShrines.REGIDRAGO_SHRINE, GenerationsShrines.REGIELEKI_SHRINE, GenerationsShrines.REGICE_SHRINE,
        GenerationsShrines.REGISTEEL_SHRINE, GenerationsShrines.REGIROCK_SHRINE, GenerationsShrines.TAPU_SHRINE
    )
    val WEATHER_TRIO = registerRegular("weather_trio", ::WeatherTrioShrineBlockEntity, GenerationsShrines.GROUDON_SHRINE, GenerationsShrines.KYOGRE_SHRINE)
    val TIMESPACE_ALTAR = registerRegular("timespace_altar", ::TimeSpaceAltarBlockEntity, GenerationsShrines.TIMESPACE_ALTAR)
    val ABUNDANT_SHRINE = registerRegular("abundant_shrine", ::AbundantShrineBlockEntity, GenerationsShrines.ABUNDANT_SHRINE, GenerationsShrines.ABUNDANT_SHRINE)
    val CELESTIAL_ALTAR = registerRegular("celestial_altar", ::CelestialAltarBlockEntity, GenerationsShrines.CELESTIAL_ALTAR)

    val LUNAR_SHRINE = registerRegular("lunar_shrine", ::LunarShrineBlockEntity, GenerationsShrines.LUNAR_SHRINE)
    val MELOETTA_MUSIC_BOX = registerRegular("meloetta_music_box", ::MeloettaMusicBoxBlockEntity, GenerationsShrines.MELOETTA_MUSIC_BOX)
    val REGIGIGAS_SHRINE = registerRegular("regigigas_shrine", ::RegigigasShrineBlockEntity, GenerationsShrines.REGIGIGAS_SHRINE)
    val TAO_TRIO_SHRINE = registerRegular("tao_trio_shrine", ::TaoTrioShrineBlockEntity, GenerationsShrines.TAO_TRIO_SHRINE)
    val TAPU_SHRINE = registerRegular("tapu_shrine", ::TapuShrineBlockEntity, GenerationsShrines.TAPU_SHRINE)
    val INTERACT_SHRINE = registerRegular("interact_shrine", ::InteractShrineBlockEntity, GenerationsShrines.PRISON_BOTTLE_STEM)
    val COOKING_POT = registerRegular("cooking_pot", ::CookingPotBlockEntity, GenerationsUtilityBlocks.COOKING_POT)
    val GENERIC_CHEST = registerRegular("generic_chest", ::GenericChestBlockEntity, POKEBALL_CHEST, GREATBALL_CHEST, ULTRABALL_CHEST, MASTERBALL_CHEST)
    val SIGN_BLOCK_ENTITIES = registerRegular<SignBlockEntity>("sign_block_entity", ::SignBlockEntity,
        GenerationsWood.ULTRA_DARK_SIGN, GenerationsWood.ULTRA_DARK_WALL_SIGN,
        GenerationsWood.ULTRA_JUNGLE_SIGN, GenerationsWood.ULTRA_JUNGLE_WALL_SIGN,
        GenerationsWood.GHOST_SIGN, GenerationsWood.GHOST_WALL_SIGN
    )
    val HANGING_SIGN_BLOCK_ENTITIES = registerRegular("hanging_sign_block_entity", ::HangingSignBlockEntity,
        GenerationsWood.ULTRA_DARK_HANGING_SIGN, GenerationsWood.ULTRA_DARK_WALL_HANGING_SIGN,
        GenerationsWood.ULTRA_JUNGLE_HANGING_SIGN, GenerationsWood.ULTRA_JUNGLE_WALL_HANGING_SIGN,
        GenerationsWood.GHOST_HANGING_SIGN, GenerationsWood.GHOST_WALL_HANGING_SIGN
    )

    val GENERIC_FURNACE = registerRegular("generic_furnace", ::GenericFurnaceBlockEntity, GenerationsUtilityBlocks.CHARGE_STONE_FURNACE, GenerationsUtilityBlocks.VOLCANIC_STONE_FURNACE)
    val GENERIC_BLAST_FURNACE = registerRegular("generic_blast_furnace", ::GenericBlastFurnaceBlockEntity, GenerationsUtilityBlocks.CHARGE_STONE_BLAST_FURNACE, GenerationsUtilityBlocks.VOLCANIC_STONE_BLAST_FURNACE)
    val GENERIC_SMOKER = registerRegular("generic_smoker", ::GenericSmokerBlockEntity, GenerationsUtilityBlocks.CHARGE_STONE_SMOKER, GenerationsUtilityBlocks.VOLCANIC_STONE_SMOKER)
    val GENERIC_DYED_VARIANT = registerRegularWithArray("generic_dyed_variant", ::GenericDyedVariantBlockEntity, GenerationsDecorationBlocks.PASTEL_BEAN_BAG.toArray() + GenerationsDecorationBlocks.SWIVEL_CHAIR.toArray())
    val GENERIC_MODEL_PROVIDING = registerRegular("generic_model_providing", ::GenericModelProvidingBlockEntity,
        GenerationsUtilityBlocks.SCARECROW, GenerationsDecorationBlocks.SNORLAX_BEAN_BAG, GenerationsDecorationBlocks.SHELF, GenerationsDecorationBlocks.HOUSE_LAMP,
        GenerationsDecorationBlocks.LITWICK_CANDLE, GenerationsDecorationBlocks.LITWICK_CANDLES, GenerationsBlocks.POKECENTER_SCARLET_SIGN, GenerationsDecorationBlocks.BOOK_SHELF,
        GenerationsDecorationBlocks.SWITCH, GenerationsDecorationBlocks.WORK_DESK, GenerationsDecorationBlocks.BENCH, GenerationsDecorationBlocks.BUSH,
        GenerationsDecorationBlocks.COUCH, GenerationsDecorationBlocks.FOONGUS_CUSHION, GenerationsDecorationBlocks.POKEBALL_CUSHION, GenerationsDecorationBlocks.GREATBALL_CUSHION,
        GenerationsDecorationBlocks.MASTERBALL_CUSHION, GenerationsDecorationBlocks.DESK, GenerationsDecorationBlocks.DOUBLE_STREET_LAMP, GenerationsDecorationBlocks.HDTV,
        GenerationsShrines.DARK_CRYSTAL, GenerationsShrines.LIGHT_CRYSTAL, GenerationsDecorationBlocks.POKEBALL_PILLAR, GenerationsShrines.PRISON_BOTTLE,
        GenerationsDecorationBlocks.SHOP_DISPLAY_CASE_1, GenerationsDecorationBlocks.SHOP_DISPLAY_CASE_2, GenerationsDecorationBlocks.SHOP_DISPLAY_SMALL_1, GenerationsDecorationBlocks.SHOP_DISPLAY_SMALL_2,
        GenerationsDecorationBlocks.SHOP_DISPLAY_LARGE_SHELF_1, GenerationsDecorationBlocks.SHOP_DISPLAY_LARGE_SHELF_2
    )
    val TRASH_CAN = registerRegular("trash_can", ::TrashCanBlockEntity, GenerationsUtilityBlocks.TRASH_CAN)

    @JvmField
    val COUCH = registerRegularWithArray("couch", ::CouchBlockEntity,
        GenerationsDecorationBlocks.COUCH_ARM_LEFT.toArray() +
        GenerationsDecorationBlocks.COUCH_ARM_RIGHT.toArray() +
        GenerationsDecorationBlocks.COUCH_CORNER_LEFT.toArray() +
        GenerationsDecorationBlocks.COUCH_CORNER_RIGHT.toArray() +
        GenerationsDecorationBlocks.COUCH_MIDDLE.toArray() +
        GenerationsDecorationBlocks.COUCH_OTTOMAN.toArray()
    )

    @JvmField
    val MACHINE_BLOCK = registerRegular("machine_block", ::MachineBlockEntity, GenerationsBlocks.MACHINE_BLOCK)

    @JvmField
    val VENDING_MACHINE = registerRegularWithArray("vending_machine", ::VendingMachineBlockEntity, GenerationsDecorationBlocks.VENDING_MACHINE.toArray())
    @JvmField
    val BALL_DISPLAY = registerRegular("ball_display", ::BallDisplayBlockEntity,
        GenerationsDecorationBlocks.EMPTY_BALL_DISPLAY, GenerationsDecorationBlocks.POKE_BALL_DISPLAY,
        GenerationsDecorationBlocks.GREAT_BALL_DISPLAY, GenerationsDecorationBlocks.ULTRA_BALL_DISPLAY,
        GenerationsDecorationBlocks.MASTER_BALL_DISPLAY, GenerationsDecorationBlocks.CHERISH_BALL_DISPLAY,
        GenerationsDecorationBlocks.DIVE_BALL_DISPLAY, GenerationsDecorationBlocks.DUSK_BALL_DISPLAY,
        GenerationsDecorationBlocks.FAST_BALL_DISPLAY, GenerationsDecorationBlocks.FRIEND_BALL_DISPLAY,
        GenerationsDecorationBlocks.HEAVY_BALL_DISPLAY, GenerationsDecorationBlocks.LEVEL_BALL_DISPLAY,
        GenerationsDecorationBlocks.LOVE_BALL_DISPLAY, GenerationsDecorationBlocks.LURE_BALL_DISPLAY,
        GenerationsDecorationBlocks.LUXURY_BALL_DISPLAY, GenerationsDecorationBlocks.MOON_BALL_DISPLAY,
        GenerationsDecorationBlocks.NEST_BALL_DISPLAY, GenerationsDecorationBlocks.NET_BALL_DISPLAY,
        GenerationsDecorationBlocks.PARK_BALL_DISPLAY, GenerationsDecorationBlocks.PREMIER_BALL_DISPLAY,
        GenerationsDecorationBlocks.QUICK_BALL_DISPLAY, GenerationsDecorationBlocks.REPEAT_BALL_DISPLAY,
        GenerationsDecorationBlocks.SAFARI_BALL_DISPLAY, GenerationsDecorationBlocks.SPORT_BALL_DISPLAY,
        GenerationsDecorationBlocks.TIMER_BALL_DISPLAY, GenerationsDecorationBlocks.HEAL_BALL_DISPLAY
    )

    val PC = registerRegular("pc", ::DefaultPcBlockEntity, GenerationsUtilityBlocks.ROTOM_PC, GenerationsUtilityBlocks.TABLE_PC)
    @JvmField
    val DYED_PC = registerRegularWithArray("dyed_pc", ::DyedPcBlockEntity, GenerationsUtilityBlocks.PC.toArray())

    @JvmField
    val BALL_LOOT = registerRegular("poke_loot", ::BallLootBlockEntity,
        GenerationsUtilityBlocks.BEAST_BALL_LOOT,
        GenerationsUtilityBlocks.CHERISH_BALL_LOOT,
        GenerationsUtilityBlocks.DIVE_BALL_LOOT,
        GenerationsUtilityBlocks.DREAM_BALL_LOOT,
        GenerationsUtilityBlocks.DUSK_BALL_LOOT,
        GenerationsUtilityBlocks.FAST_BALL_LOOT,
        GenerationsUtilityBlocks.FRIEND_BALL_LOOT,
        GenerationsUtilityBlocks.GIGATON_BALL_LOOT,
        GenerationsUtilityBlocks.GREAT_BALL_LOOT,
        GenerationsUtilityBlocks.HEAL_BALL_LOOT,
        GenerationsUtilityBlocks.HEAVY_BALL_LOOT,
        GenerationsUtilityBlocks.JET_BALL_LOOT,
        GenerationsUtilityBlocks.LEADEN_BALL_LOOT,
        GenerationsUtilityBlocks.LEVEL_BALL_LOOT,
        GenerationsUtilityBlocks.LOVE_BALL_LOOT,
        GenerationsUtilityBlocks.LURE_BALL_LOOT,
        GenerationsUtilityBlocks.LUXURY_BALL_LOOT,
        GenerationsUtilityBlocks.MASTER_BALL_LOOT,
        GenerationsUtilityBlocks.MOON_BALL_LOOT,
        GenerationsUtilityBlocks.NEST_BALL_LOOT,
        GenerationsUtilityBlocks.NET_BALL_LOOT,
        GenerationsUtilityBlocks.ORIGIN_BALL_LOOT,
        GenerationsUtilityBlocks.PARK_BALL_LOOT,
        GenerationsUtilityBlocks.POKE_BALL_LOOT,
        GenerationsUtilityBlocks.PREMIER_BALL_LOOT,
        GenerationsUtilityBlocks.QUICK_BALL_LOOT,
        GenerationsUtilityBlocks.REPEAT_BALL_LOOT,
        GenerationsUtilityBlocks.SAFARI_BALL_LOOT,
        GenerationsUtilityBlocks.SPORT_BALL_LOOT,
        GenerationsUtilityBlocks.STRANGE_BALL_LOOT,
        GenerationsUtilityBlocks.TIMER_BALL_LOOT,
        GenerationsUtilityBlocks.ULTRA_BALL_LOOT,
        GenerationsUtilityBlocks.WING_BALL_LOOT)

    @JvmField
    val RKS_MACHINE = registerRegular("rks_machine", ::RksMachineBlockEntity, GenerationsUtilityBlocks.RKS_MACHINE)
    @JvmField
    val STREET_LAMP = registerRegularWithArray("street_lamp", ::StreetLampBlockEntity, GenerationsDecorationBlocks.STREET_LAMP.toArray())
    val BOX = registerRegular("box", ::BoxBlockEntity, GenerationsUtilityBlocks.BOX)



    @SafeVarargs
    fun <T : BlockEntity> registerRegular(
        name: String,
        aNew: BlockEntityType.BlockEntitySupplier<T>,
        vararg blocks: Block
    ): BlockEntityType<T> = create(name.generationsResource(), BlockEntityType.Builder.of(aNew, *blocks).build(null))

    fun <T : BlockEntity> registerRegularWithArray(
        name: String,
        aNew: BlockEntityType.BlockEntitySupplier<T>,
        blocks: Array<out Block>
    ): BlockEntityType<T> = create(name.generationsResource(), BlockEntityType.Builder.of(aNew, *blocks).build(null))

    override fun init(consumer: (ResourceLocation, BlockEntityType<*>) -> Unit) {
        GenerationsCore.LOGGER.info("Registering Generations Block Entities")
        super.init(consumer)
    }
}
