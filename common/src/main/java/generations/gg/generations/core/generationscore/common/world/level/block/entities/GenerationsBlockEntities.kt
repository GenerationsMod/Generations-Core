package generations.gg.generations.core.generationscore.common.world.level.block.entities

import dev.architectury.registry.registries.DeferredRegister
import dev.architectury.registry.registries.RegistrySupplier
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks.GREATBALL_CHEST
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks.MASTERBALL_CHEST
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks.POKEBALL_CHEST
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks.ULTRABALL_CHEST
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsUtilityBlocks
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsWood
import generations.gg.generations.core.generationscore.common.world.level.block.entities.MutableBlockEntityType.BlockEntityFactory
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.*
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.*
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.altar.CelestialAltarBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.altar.TimeSpaceAltarBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.entity.HangingSignBlockEntity
import net.minecraft.world.level.block.entity.SignBlockEntity
import net.minecraft.world.level.block.state.BlockState
import java.util.function.Supplier
import java.util.stream.Stream

object GenerationsBlockEntities {
    val BLOCK_ENTITIES: DeferredRegister<BlockEntityType<*>> = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.BLOCK_ENTITY_TYPE)

    @JvmField
    val POKE_DOLL: RegistrySupplier<MutableBlockEntityType<PokeDollBlockEntity>> = registerMutable("pokedoll", ::PokeDollBlockEntity)
    @JvmField
    val GENERIC_SHRINE: RegistrySupplier<MutableBlockEntityType<GenericShrineBlockEntity>> = registerMutable("generic_shrine", ::GenericShrineBlockEntity)
    @JvmField
    val WEATHER_TRIO: RegistrySupplier<MutableBlockEntityType<WeatherTrioShrineBlockEntity>> = registerMutable("weather_trio", ::WeatherTrioShrineBlockEntity)
    @JvmField
    val TIMESPACE_ALTAR: RegistrySupplier<MutableBlockEntityType<TimeSpaceAltarBlockEntity>> = registerMutable("timespace_altar", ::TimeSpaceAltarBlockEntity)

    @JvmField
    val ABUNDANT_SHRINE: RegistrySupplier<MutableBlockEntityType<AbundantShrineBlockEntity>> = registerMutable("abundant_shrine", ::AbundantShrineBlockEntity)
    @JvmField
    val CELESTIAL_ALTAR: RegistrySupplier<MutableBlockEntityType<CelestialAltarBlockEntity>> = registerMutable("celestial_altar", ::CelestialAltarBlockEntity)

    @JvmField
    val LUNAR_SHRINE: RegistrySupplier<MutableBlockEntityType<LunarShrineBlockEntity>> = registerMutable("lunar_shrine", ::LunarShrineBlockEntity)
    @JvmField
    val MELOETTA_MUSIC_BOX: RegistrySupplier<MutableBlockEntityType<MeloettaMusicBoxBlockEntity>> = registerMutable("meloetta_music_box", ::MeloettaMusicBoxBlockEntity)
    @JvmField
    val REGIGIGAS_SHRINE: RegistrySupplier<MutableBlockEntityType<RegigigasShrineBlockEntity>> = registerMutable("regigigas_shrine", ::RegigigasShrineBlockEntity)
    @JvmField
    val TAO_TRIO_SHRINE: RegistrySupplier<MutableBlockEntityType<TaoTrioShrineBlockEntity>> = registerMutable("tao_trio_shrine", ::TaoTrioShrineBlockEntity)
    @JvmField
    val TAPU_SHRINE: RegistrySupplier<MutableBlockEntityType<TapuShrineBlockEntity>> = registerMutable("tapu_shrine", ::TapuShrineBlockEntity)
    @JvmField
    val INTERACT_SHRINE: RegistrySupplier<MutableBlockEntityType<InteractShrineBlockEntity>> = registerMutable("interact_shrine", ::InteractShrineBlockEntity)
    @JvmField
    val COOKING_POT: RegistrySupplier<MutableBlockEntityType<CookingPotBlockEntity>> = registerMutable("cooking_pot", ::CookingPotBlockEntity)
    @JvmField
    val GENERIC_CHEST: RegistrySupplier<BlockEntityType<GenericChestBlockEntity>> = registerRegular("generic_chest", ::GenericChestBlockEntity,
            POKEBALL_CHEST,
            GREATBALL_CHEST,
            ULTRABALL_CHEST,
            MASTERBALL_CHEST
        )
    @JvmField
    val SIGN_BLOCK_ENTITIES: RegistrySupplier<BlockEntityType<SignBlockEntity>> = registerRegular<SignBlockEntity>(
        "sign_block_entity",
        ::SignBlockEntity,
        GenerationsWood.ULTRA_DARK_SIGN,
        GenerationsWood.ULTRA_DARK_WALL_SIGN,
        GenerationsWood.ULTRA_JUNGLE_SIGN,
        GenerationsWood.ULTRA_JUNGLE_WALL_SIGN,
        GenerationsWood.GHOST_SIGN,
        GenerationsWood.GHOST_WALL_SIGN
    )

    @JvmField
    val HANGING_SIGN_BLOCK_ENTITIES: RegistrySupplier<BlockEntityType<HangingSignBlockEntity>> = registerRegular(
            "hanging_sign_block_entity",
            ::HangingSignBlockEntity,
            GenerationsWood.ULTRA_DARK_HANGING_SIGN,
            GenerationsWood.ULTRA_DARK_WALL_HANGING_SIGN,
            GenerationsWood.ULTRA_JUNGLE_HANGING_SIGN,
            GenerationsWood.ULTRA_JUNGLE_WALL_HANGING_SIGN,
            GenerationsWood.GHOST_HANGING_SIGN,
            GenerationsWood.GHOST_WALL_HANGING_SIGN
        )
    @JvmField
    val BREEDER: RegistrySupplier<MutableBlockEntityType<BreederBlockEntity>> = registerMutable("breeder", ::BreederBlockEntity)
    val GENERIC_FURNACE: RegistrySupplier<BlockEntityType<GenericFurnaceBlockEntity>> = registerRegular(
        "generic_furnace",
        ::GenericFurnaceBlockEntity,
        GenerationsUtilityBlocks.CHARGE_STONE_FURNACE,
        GenerationsUtilityBlocks.VOLCANIC_STONE_FURNACE
    )
    @JvmField
    val GENERIC_BLAST_FURNACE: RegistrySupplier<BlockEntityType<GenericBlastFurnaceBlockEntity>> = registerRegular(
        "generic_blast_furnace",
        ::GenericBlastFurnaceBlockEntity,
        GenerationsUtilityBlocks.CHARGE_STONE_BLAST_FURNACE,
        GenerationsUtilityBlocks.VOLCANIC_STONE_BLAST_FURNACE
    )
    @JvmField
    val GENERIC_SMOKER: RegistrySupplier<BlockEntityType<GenericSmokerBlockEntity>> = registerRegular(
        "generic_smoker",
        ::GenericSmokerBlockEntity,
        GenerationsUtilityBlocks.CHARGE_STONE_SMOKER,
        GenerationsUtilityBlocks.VOLCANIC_STONE_SMOKER
    )
    @JvmField
    val GENERIC_DYED_VARIANT: RegistrySupplier<MutableBlockEntityType<GenericDyedVariantBlockEntity>> = registerMutable("generic_dyed_variant", ::GenericDyedVariantBlockEntity)

    @JvmField
    val GENERIC_MODEL_PROVIDING: RegistrySupplier<MutableBlockEntityType<GenericModelProvidingBlockEntity>> = registerMutable("generic_model_providing", ::GenericModelProvidingBlockEntity)

    @JvmField
    val COUCH: RegistrySupplier<MutableBlockEntityType<CouchBlockEntity>> = registerMutable("couch", ::CouchBlockEntity)

    @JvmField
    val MACHINE_BLOCK: RegistrySupplier<BlockEntityType<MachineBlockEntity>> = registerRegular("machine_block", ::MachineBlockEntity, GenerationsBlocks.MACHINE_BLOCK
    )

    @JvmField
    val VENDING_MACHINE: RegistrySupplier<MutableBlockEntityType<VendingMachineBlockEntity>> = registerMutable("vending_machine", ::VendingMachineBlockEntity)
    @JvmField
    val BALL_DISPLAY: RegistrySupplier<MutableBlockEntityType<BallDisplayBlockEntity>> = registerMutable("ball_display", ::BallDisplayBlockEntity)

    @JvmField
    val PC: RegistrySupplier<MutableBlockEntityType<DefaultPcBlockEntity>> = registerMutable("pc",
        { blockPos: BlockPos?, blockState: BlockState? ->
            DefaultPcBlockEntity(
                blockPos!!, blockState!!
            )
        })
    @JvmField
    val DYED_PC: RegistrySupplier<MutableBlockEntityType<DyedPcBlockEntity>> = registerMutable("dyed_pc", ::DyedPcBlockEntity)

    @JvmField
    val BALL_LOOT: RegistrySupplier<MutableBlockEntityType<BallLootBlockEntity>> = registerMutable("poke_loot", ::BallLootBlockEntity)

    @JvmField
    val RKS_MACHINE: RegistrySupplier<MutableBlockEntityType<RksMachineBlockEntity>> = registerMutable("rks_machine", ::RksMachineBlockEntity)
    @JvmField
    val STREET_LAMP: RegistrySupplier<MutableBlockEntityType<StreetLampBlockEntity>> = registerMutable("street_lamp", ::StreetLampBlockEntity)
    val BOX: RegistrySupplier<BlockEntityType<BoxBlockEntity>> = registerRegular("box", ::BoxBlockEntity, GenerationsUtilityBlocks.BOX)


    @SafeVarargs
    private fun <T : BlockEntity?> registerRegular(
        name: String,
        aNew: BlockEntityType.BlockEntitySupplier<T>,
        vararg blocks: Supplier<out Block>
    ): RegistrySupplier<BlockEntityType<T>> {
        return BLOCK_ENTITIES.register(name) {
            BlockEntityType.Builder.of<T>(aNew, *blocks.map(Supplier<out Block>::get).toTypedArray()).build(null)
        }
    }

    private fun <T : BlockEntity> registerMutable(
        name: String,
        factory: (BlockPos, BlockState) -> T): RegistrySupplier<MutableBlockEntityType<T>> {
        return BLOCK_ENTITIES.register(name) {
            MutableBlockEntityType.Builder.create(factory).build(null)
        }
    }

    @JvmStatic
    fun init() {
        GenerationsCore.LOGGER.info("Registering Generations Block Entities")
        BLOCK_ENTITIES.register()
    }
}
