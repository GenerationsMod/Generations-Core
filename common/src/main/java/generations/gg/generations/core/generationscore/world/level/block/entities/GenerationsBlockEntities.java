package generations.gg.generations.core.generationscore.world.level.block.entities;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.api.player.AccountInfo;
import generations.gg.generations.core.generationscore.client.render.block.entity.BoxBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.*;
import generations.gg.generations.core.generationscore.world.level.block.entities.generic.*;
import generations.gg.generations.core.generationscore.world.level.block.entities.shrines.*;
import generations.gg.generations.core.generationscore.world.level.block.entities.shrines.altar.CelestialAltarBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.shrines.altar.TimeSpaceAltarBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class GenerationsBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.BLOCK_ENTITY_TYPE);

    public static final RegistrySupplier<MutableBlockEntityType<PokeDollBlockEntity>> POKE_DOLL = registerMutable("pokedoll", PokeDollBlockEntity::new);
    public static final RegistrySupplier<MutableBlockEntityType<GenericShrineBlockEntity>> GENERIC_SHRINE = registerMutable("generic_shrine", GenericShrineBlockEntity::new);
    public static final RegistrySupplier<MutableBlockEntityType<WeatherTrioShrineBlockEntity>> WEATHER_TRIO = registerMutable("weather_trio", WeatherTrioShrineBlockEntity::new);
    public static final RegistrySupplier<MutableBlockEntityType<TimeSpaceAltarBlockEntity>> TIMESPACE_ALTAR = registerMutable("timespace_altar", TimeSpaceAltarBlockEntity::new);
    public static final RegistrySupplier<MutableBlockEntityType<AbundantShrineBlockEntity>> ABUNDANT_SHRINE = registerMutable("abundant_shrine", AbundantShrineBlockEntity::new);
    public static final RegistrySupplier<MutableBlockEntityType<CelestialAltarBlockEntity>> CELESTIAL_ALTAR = registerMutable("celestial_altar", CelestialAltarBlockEntity::new);
    public static final RegistrySupplier<MutableBlockEntityType<LunarShrineBlockEntity>> LUNAR_SHRINE = registerMutable("lunar_shrine", LunarShrineBlockEntity::new);
    public static final RegistrySupplier<MutableBlockEntityType<MeloettaMusicBoxBlockEntity>> MELOETTA_MUSIC_BOX = registerMutable("meloetta_music_box", MeloettaMusicBoxBlockEntity::new);
    public static final RegistrySupplier<MutableBlockEntityType<RegigigasShrineBlockEntity>> REGIGIGAS_SHRINE = registerMutable("regigigas_shrine", RegigigasShrineBlockEntity::new);
    public static final RegistrySupplier<MutableBlockEntityType<TaoTrioShrineBlockEntity>> TAO_TRIO_SHRINE = registerMutable("tao_trio_shrine", TaoTrioShrineBlockEntity::new);
    public static final RegistrySupplier<MutableBlockEntityType<TapuShrineBlockEntity>> TAPU_SHRINE = registerMutable("tapu_shrine", TapuShrineBlockEntity::new);
    public static final RegistrySupplier<MutableBlockEntityType<CookingPotBlockEntity>> COOKING_POT = registerMutable("cooking_pot", CookingPotBlockEntity::new);
    public static final RegistrySupplier<MutableBlockEntityType<GenericChestBlockEntity>> GENERIC_CHEST = registerMutable("generic_chest", GenericChestBlockEntity::new);
    public static final RegistrySupplier<BlockEntityType<SignBlockEntity>> SIGN_BLOCK_ENTITIES = registerRegular("sign_block_entity", SignBlockEntity::new,
            GenerationsWood.ULTRA_DARK_SIGN,
            GenerationsWood.ULTRA_DARK_WALL_SIGN,
            GenerationsWood.ULTRA_JUNGLE_SIGN,
            GenerationsWood.ULTRA_JUNGLE_WALL_SIGN,
            GenerationsWood.GHOST_SIGN,
            GenerationsWood.GHOST_WALL_SIGN
    );

    public static final RegistrySupplier<BlockEntityType<HangingSignBlockEntity>> HANGING_SIGN_BLOCK_ENTITIES = registerRegular("hanging_sign_block_entity", HangingSignBlockEntity::new,
            GenerationsWood.ULTRA_DARK_HANGING_SIGN,
            GenerationsWood.ULTRA_DARK_WALL_HANGING_SIGN,
            GenerationsWood.ULTRA_JUNGLE_HANGING_SIGN,
            GenerationsWood.ULTRA_JUNGLE_WALL_HANGING_SIGN,
            GenerationsWood.GHOST_HANGING_SIGN,
            GenerationsWood.GHOST_WALL_HANGING_SIGN);
    public static final RegistrySupplier<MutableBlockEntityType<BreederBlockEntity>> BREEDER = registerMutable("breeder", BreederBlockEntity::new);
    public static final RegistrySupplier<BlockEntityType<GenericFurnaceBlockEntity>> GENERIC_FURNACE = registerRegular("generic_furnace", GenericFurnaceBlockEntity::new,
            GenerationsUtilityBlocks.CHARGE_STONE_FURNACE,
            GenerationsUtilityBlocks.VOLCANIC_STONE_FURNACE);
    public static final RegistrySupplier<BlockEntityType<GenericBlastFurnaceBlockEntity>> GENERIC_BLAST_FURNACE = registerRegular("generic_blast_furnace", GenericBlastFurnaceBlockEntity::new,
            GenerationsUtilityBlocks.CHARGE_STONE_BLAST_FURNACE,
            GenerationsUtilityBlocks.VOLCANIC_STONE_BLAST_FURNACE);
    public static final RegistrySupplier<BlockEntityType<GenericSmokerBlockEntity>> GENERIC_SMOKER = registerRegular("generic_smoker", GenericSmokerBlockEntity::new,
            GenerationsUtilityBlocks.CHARGE_STONE_SMOKER,
            GenerationsUtilityBlocks.VOLCANIC_STONE_SMOKER);
    public static final RegistrySupplier<MutableBlockEntityType<GenericDyedVariantBlockEntity>> GENERIC_DYED_VARIANT = registerMutable("generic_dyed_variant", GenericDyedVariantBlockEntity::new);

    public static final RegistrySupplier<MutableBlockEntityType<GenericModelProvidingBlockEntity>> GENERIC_MODEL_PROVIDING = registerMutable("generic_model_providing", GenericModelProvidingBlockEntity::new);

    public static final RegistrySupplier<MutableBlockEntityType<CouchBlockEntity>> COUCH = registerMutable("couch", CouchBlockEntity::new);

    public static final RegistrySupplier<BlockEntityType<MachineBlockEntity>> MACHINE_BLOCK = registerRegular("machine_block", MachineBlockEntity::new, GenerationsBlocks.MACHINE_BLOCK);

    public static final RegistrySupplier<MutableBlockEntityType<VendingMachineBlockEntity>> VENDING_MACHINE = registerMutable("vending_machine", VendingMachineBlockEntity::new);
    public static final RegistrySupplier<MutableBlockEntityType<BallDisplayBlockEntity>> BALL_DISPLAY = registerMutable("ball_display", BallDisplayBlockEntity::new);

    public static final RegistrySupplier<MutableBlockEntityType<PcBlockEntity>> PC = registerMutable("pc", PcBlockEntity::new);

    public static final RegistrySupplier<MutableBlockEntityType<BallLootBlockEntity>> BALL_LOOT = registerMutable("poke_loot", BallLootBlockEntity::new);

    public static final RegistrySupplier<MutableBlockEntityType<RksMachineBlockEntity>> RKS_MACHINE = registerMutable("rks_machine", RksMachineBlockEntity::new);
    public static final RegistrySupplier<MutableBlockEntityType<StreetLampBlockEntity>> STREET_LAMP = registerMutable("street_lamp", StreetLampBlockEntity::new);
    public static final RegistrySupplier<BlockEntityType<BoxBlockEntity>> BOX = registerRegular("box", BoxBlockEntity::new, GenerationsUtilityBlocks.BOX);


    @SafeVarargs
    private static <T extends BlockEntity> RegistrySupplier<BlockEntityType<T>> registerRegular(String name, BlockEntityType.BlockEntitySupplier<T> aNew, Supplier<? extends Block>... blocks) {
        return BLOCK_ENTITIES.register(name, () -> BlockEntityType.Builder.of(aNew, Stream.of(blocks).map(Supplier::get).toArray(Block[]::new)).build(null));
    }

    private static <T extends BlockEntity> RegistrySupplier<MutableBlockEntityType<T>> registerMutable(String name, MutableBlockEntityType.BlockEntityFactory<T> factory) {
        return BLOCK_ENTITIES.register(name, () -> MutableBlockEntityType.Builder.create(factory).build(null));
    }

    public static void init() {
        GenerationsCore.LOGGER.info("Registering Generations Block Entities");
        BLOCK_ENTITIES.register();
    }
}
