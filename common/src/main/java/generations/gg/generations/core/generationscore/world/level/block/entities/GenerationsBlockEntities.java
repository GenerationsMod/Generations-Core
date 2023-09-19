package generations.gg.generations.core.generationscore.world.level.block.entities;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.level.block.*;
import generations.gg.generations.core.generationscore.world.level.block.entities.generic.*;
import generations.gg.generations.core.generationscore.world.level.block.entities.shrines.*;
import generations.gg.generations.core.generationscore.world.level.block.entities.shrines.altar.CelestialAltarBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.shrines.altar.TimeSpaceAltarBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;

public class GenerationsBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.BLOCK_ENTITY_TYPE);

    public static final RegistrySupplier<BlockEntityType<PokeDollBlockEntity>> POKE_DOLL = BLOCK_ENTITIES.register("pokedoll", () -> BlockEntityType.Builder.of(PokeDollBlockEntity::new,
            GenerationsPokeDolls.SHINY_CELEBI_POKEDOLL.get(),
            GenerationsPokeDolls.CHARIZARD_POKEDOLL.get(),
            GenerationsPokeDolls.BLASTOISE_POKEDOLL.get(),
            GenerationsPokeDolls.CLEFAIRY_POKEDOLL.get(),
            GenerationsPokeDolls.CYNDAQUIL_POKEDOLL.get(),
            GenerationsPokeDolls.PIKACHU_POKEDOLL.get(),
            GenerationsPokeDolls.TREECKO_POKEDOLL.get(),
            GenerationsPokeDolls.VENUSAUR_POKEDOLL.get(),
            GenerationsPokeDolls.DUSKULL_POKEDOLL.get(),
            GenerationsPokeDolls.MEOWTH_POKEDOLL.get(),
            GenerationsPokeDolls.MUDKIP_POKEDOLL.get(),
            GenerationsPokeDolls.AZURILL_POKEDOLL.get(),
            GenerationsPokeDolls.BALTOY_POKEDOLL.get(),
            GenerationsPokeDolls.CHIKORITA_POKEDOLL.get(),
            GenerationsPokeDolls.DITTO_POKEDOLL.get(),
            GenerationsPokeDolls.GULPIN_POKEDOLL.get(),
            GenerationsPokeDolls.JIGGLYPUFF_POKEDOLL.get(),
            GenerationsPokeDolls.KECLEON_POKEDOLL.get(),
            GenerationsPokeDolls.LAPRAS_POKEDOLL.get(),
            GenerationsPokeDolls.LOTAD_POKEDOLL.get(),
            GenerationsPokeDolls.MARILL_POKEDOLL.get(),
            GenerationsPokeDolls.PICHU_POKEDOLL.get(),
            GenerationsPokeDolls.REGICE_POKEDOLL.get(),
            GenerationsPokeDolls.REGIROCK_POKEDOLL.get(),
            GenerationsPokeDolls.REGISTEEL_POKEDOLL.get(),
            GenerationsPokeDolls.RHYDON_POKEDOLL.get(),
            GenerationsPokeDolls.SEEDOT_POKEDOLL.get(),
            GenerationsPokeDolls.SKITTY_POKEDOLL.get(),
            GenerationsPokeDolls.SMOOCHUM_POKEDOLL.get(),
            GenerationsPokeDolls.SNORLAX_POKEDOLL.get(),
            GenerationsPokeDolls.SUBSTITUTE_POKEDOLL.get(),
            GenerationsPokeDolls.SWABLU_POKEDOLL.get(),
            GenerationsPokeDolls.TOGEPI_POKEDOLL.get(),
            GenerationsPokeDolls.TORCHIC_POKEDOLL.get(),
            GenerationsPokeDolls.TOTODILE_POKEDOLL.get(),
            GenerationsPokeDolls.WAILMER_POKEDOLL.get(),
            GenerationsPokeDolls.WYNAUT_POKEDOLL.get(),
            GenerationsPokeDolls.EEVEE_POKEDOLL.get(),
            GenerationsPokeDolls.ROWLET_POKEDOLL.get(),
            GenerationsPokeDolls.POPPLIO_POKEDOLL.get(),
            GenerationsPokeDolls.POLIWHIRL_POKEDOLL.get(),
            GenerationsPokeDolls.ZERAORA_POKEDOLL.get(),
            GenerationsPokeDolls.CRESSELIA_POKEDOLL.get(),
            GenerationsPokeDolls.LITTEN_POKEDOLL.get(),
            GenerationsPokeDolls.CUBONE_POKEDOLL.get(),
            GenerationsPokeDolls.DIALGA_POKEDOLL.get(),
            GenerationsPokeDolls.ARCEUS_POKEDOLL.get(),
            GenerationsPokeDolls.SABLEYE_POKEDOLL.get(),
            GenerationsPokeDolls.BLUEGHOST_POKEDOLL.get(),
            GenerationsPokeDolls.PURPLEGHOST_POKEDOLL.get(),
            GenerationsPokeDolls.EKANS_POKEDOLL.get(),
            GenerationsPokeDolls.ARTICUNO_POKEDOLL.get(),
            GenerationsPokeDolls.AZELF_POKEDOLL.get(),
            GenerationsPokeDolls.DARKRAI_POKEDOLL.get(),
            GenerationsPokeDolls.DEOXYS_POKEDOLL.get(),
            GenerationsPokeDolls.ENTEI_POKEDOLL.get(),
            GenerationsPokeDolls.GIRATINA_ALTERED_POKEDOLL.get(),
            GenerationsPokeDolls.GIRATINA_ORIGINAL_POKEDOLL.get(),
            GenerationsPokeDolls.GROUDON_POKEDOLL.get(),
            GenerationsPokeDolls.HOOH_POKEDOLL.get(),
            GenerationsPokeDolls.JIRACHI_POKEDOLL.get(),
            GenerationsPokeDolls.KYOGRE_POKEDOLL.get(),
            GenerationsPokeDolls.LATIAS_POKEDOLL.get(),
            GenerationsPokeDolls.LATIOS_POKEDOLL.get(),
            GenerationsPokeDolls.LUGIA_POKEDOLL.get(),
            GenerationsPokeDolls.MANAPHY_POKEDOLL.get(),
            GenerationsPokeDolls.MESPRIT_POKEDOLL.get(),
            GenerationsPokeDolls.MEW_POKEDOLL.get(),
            GenerationsPokeDolls.MEWTWO_POKEDOLL.get(),
            GenerationsPokeDolls.MOLTRES_POKEDOLL.get(),
            GenerationsPokeDolls.PALKIA_POKEDOLL.get(),
            GenerationsPokeDolls.RAIKOU_POKEDOLL.get(),
            GenerationsPokeDolls.RAYQUAZA_POKEDOLL.get(),
            GenerationsPokeDolls.REGIGIGAS_POKEDOLL.get(),
            GenerationsPokeDolls.SHAYMIN_LAND_POKEDOLL.get(),
            GenerationsPokeDolls.SHAYMIN_SKY_POKEDOLL.get(),
            GenerationsPokeDolls.SUICUNE_POKEDOLL.get(),
            GenerationsPokeDolls.UXIE_POKEDOLL.get(),
            GenerationsPokeDolls.ZAPDOS_POKEDOLL.get(),
            GenerationsPokeDolls.HERACROSS_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_CHARIZARD_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_BLASTOISE_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_CLEFAIRY_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_CYNDAQUIL_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_PIKACHU_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_TREECKO_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_VENUSAUR_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_DUSKULL_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_MEOWTH_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_MUDKIP_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_AZURILL_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_BALTOY_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_CHIKORITA_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_DITTO_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_GULPIN_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_JIGGLYPUFF_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_KECLEON_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_LAPRAS_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_LOTAD_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_MARILL_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_PICHU_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_REGICE_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_REGIROCK_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_REGISTEEL_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_RHYDON_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_SEEDOT_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_SKITTY_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_SMOOCHUM_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_SNORLAX_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_SUBSTITUTE_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_SWABLU_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_TOGEPI_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_TORCHIC_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_TOTODILE_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_WAILMER_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_WYNAUT_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_EEVEE_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_ROWLET_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_POPPLIO_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_POLIWHIRL_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_ZERAORA_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_CRESSELIA_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_LITTEN_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_CUBONE_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_DIALGA_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_ARCEUS_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_KRABBY_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_SABLEYE_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_EKANS_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_ARTICUNO_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_AZELF_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_DARKRAI_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_DEOXYS_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_ENTEI_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_GIRATINA_ALTERED_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_GIRATINA_ORIGIN_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_GROUDON_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_HERACROSS_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_HOOH_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_JIRACHI_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_KYOGRE_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_LATIAS_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_LATIOS_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_LUGIA_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_MANAPHY_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_MESPRIT_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_MEW_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_MEWTWO_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_MOLTRES_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_PALKIA_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_RAIKOU_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_RAYQUAZA_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_REGIGIGAS_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_SHAYMIN_LAND_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_SHAYMIN_SKY_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_SUICUNE_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_UXIE_POKEDOLL.get(),
            GenerationsPokeDolls.SHINY_ZAPDOS_POKEDOLL.get(),
            GenerationsPokeDolls.KRABBY_POKEDOLL.get(),
            GenerationsPokeDolls.CELEBI_POKEDOLL.get()
    ).build(null));

    public static final RegistrySupplier<BlockEntityType<HealerBlockEntity>> HEALER = BLOCK_ENTITIES.register("healer", () -> BlockEntityType.Builder.of(HealerBlockEntity::new, GenerationsUtilityBlocks.HEALER.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<ClockBlockEntity>> CLOCK = BLOCK_ENTITIES.register("clock", () -> BlockEntityType.Builder.of(ClockBlockEntity::new, GenerationsUtilityBlocks.CLOCK.get()).build(null));

    public static final RegistrySupplier<BlockEntityType<GenericShrineBlockEntity>> GENERIC_SHRINE = BLOCK_ENTITIES.register("generic_shrine", () -> BlockEntityType.Builder.of(GenericShrineBlockEntity::new,
                    GenerationsShrines.STATIC_SHRINE.get(),
                    GenerationsShrines.FIERY_SHRINE.get(),
                    GenerationsShrines.FROZEN_SHRINE.get(),
                    GenerationsShrines.LUGIA_SHRINE.get(),
                    GenerationsShrines.HO_OH_SHRINE.get(),
                    GenerationsShrines.REGICE_SHRINE.get(),
                    GenerationsShrines.REGIDRAGO_SHRINE.get(),
                    GenerationsShrines.REGIELEKI_SHRINE.get(),
                    GenerationsShrines.REGIROCK_SHRINE.get(),
                    GenerationsShrines.REGISTEEL_SHRINE.get(),
                    GenerationsShrines.TAPU_SHRINE.get())
            .build(null));
    public static final RegistrySupplier<BlockEntityType<WeatherTrioShrineBlockEntity>> WEATHER_TRIO = BLOCK_ENTITIES.register("weather_trio", () -> BlockEntityType.Builder.of(WeatherTrioShrineBlockEntity::new,
                    GenerationsShrines.KYOGRE_SHRINE.get(),
                    GenerationsShrines.GROUDON_SHRINE.get(),
                    GenerationsShrines.RAYQUAZA_SHRINE.get())
            .build(null));
    public static final RegistrySupplier<BlockEntityType<TimeSpaceAltarBlockEntity>> TIMESPACE_ALTAR = BLOCK_ENTITIES.register("timespace_altar", () -> BlockEntityType.Builder.of(TimeSpaceAltarBlockEntity::new, GenerationsShrines.TIMESPACE_ALTAR.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<AbundantShrineBlockEntity>> ABUNDANT_SHRINE = BLOCK_ENTITIES.register("abundant_shrine", () -> BlockEntityType.Builder.of(AbundantShrineBlockEntity::new, GenerationsShrines.ABUNDANT_SHRINE.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<CelestialAltarBlockEntity>> CELESTIAL_ALTAR = BLOCK_ENTITIES.register("celestial_altar", () -> BlockEntityType.Builder.of(CelestialAltarBlockEntity::new, GenerationsShrines.CELESTIAL_ALTAR.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<LunarShrineBlockEntity>> LUNAR_SHRINE = BLOCK_ENTITIES.register("lunar_shrine", () -> BlockEntityType.Builder.of(LunarShrineBlockEntity::new, GenerationsShrines.LUNAR_SHRINE.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<MeloettaMusicBoxBlockEntity>> MELOETTA_MUSIC_BOX = BLOCK_ENTITIES.register("meloetta_music_box", () -> BlockEntityType.Builder.of(MeloettaMusicBoxBlockEntity::new, GenerationsShrines.MELOETTA_MUSIC_BOX.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<RegigigasShrineBlockEntity>> REGIGIGAS_SHRINE = BLOCK_ENTITIES.register("regigigas_shrine", ()-> BlockEntityType.Builder.of(RegigigasShrineBlockEntity::new, GenerationsShrines.REGIGIGAS_SHRINE.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<TaoTrioShrineBlockEntity>> TAO_TRIO_SHRINE = BLOCK_ENTITIES.register("tao_trio_shrine", () -> BlockEntityType.Builder.of(TaoTrioShrineBlockEntity::new, GenerationsShrines.TAO_TRIO_SHRINE.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<TapuShrineBlockEntity>> TAPU_SHRINE = BLOCK_ENTITIES.register("tapu_shrine", () -> BlockEntityType.Builder.of(TapuShrineBlockEntity::new, GenerationsShrines.TAPU_SHRINE.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<CookingPotBlockEntity>> COOKING_POT = BLOCK_ENTITIES.register("cooking_pot", () -> BlockEntityType.Builder.of(CookingPotBlockEntity::new, GenerationsUtilityBlocks.COOKING_POT.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<GenericChestBlockEntity>> GENERIC_CHEST = BLOCK_ENTITIES.register("generic_chest", () -> BlockEntityType.Builder.of(GenericChestBlockEntity::new, GenerationsBlocks.POKEBALL_CHEST.get(), GenerationsBlocks.GREATBALL_CHEST.get(), GenerationsBlocks.ULTRABALL_CHEST.get(), GenerationsBlocks.MASTERBALL_CHEST.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<SignBlockEntity>> SIGN_BLOCK_ENTITIES =
            BLOCK_ENTITIES.register("sign_block_entity", () ->
                    BlockEntityType.Builder.of(SignBlockEntity::new,
                            GenerationsWood.ULTRA_DARK_SIGN.get(),
                            GenerationsWood.ULTRA_DARK_WALL_SIGN.get(),
                            GenerationsWood.ULTRA_JUNGLE_SIGN.get(),
                            GenerationsWood.ULTRA_JUNGLE_WALL_SIGN.get(),
                            GenerationsWood.GHOST_SIGN.get(),
                            GenerationsWood.GHOST_WALL_SIGN.get()
                    ).build(null));

    public static final RegistrySupplier<BlockEntityType<HangingSignBlockEntity>> HANGING_SIGN_BLOCK_ENTITIES =
            BLOCK_ENTITIES.register("hanging_sign_block_entity", () ->
                    BlockEntityType.Builder.of(HangingSignBlockEntity::new,
                            GenerationsWood.ULTRA_DARK_HANGING_SIGN.get(),
                            GenerationsWood.ULTRA_DARK_WALL_HANGING_SIGN.get(),
                            GenerationsWood.ULTRA_JUNGLE_HANGING_SIGN.get(),
                            GenerationsWood.ULTRA_JUNGLE_WALL_HANGING_SIGN.get(),
                            GenerationsWood.GHOST_HANGING_SIGN.get(),
                            GenerationsWood.GHOST_WALL_HANGING_SIGN.get()
                    ).build(null));
    public static final RegistrySupplier<BlockEntityType<BreederBlockEntity>> BREEDER = BLOCK_ENTITIES.register("breeder", () -> BlockEntityType.Builder.of(BreederBlockEntity::new, GenerationsUtilityBlocks.BREEDER.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<GenericFurnaceBlockEntity>> GENERIC_FURNACE = BLOCK_ENTITIES.register("generic_furnace", () -> BlockEntityType.Builder.of(GenericFurnaceBlockEntity::new,
            GenerationsUtilityBlocks.CHARGE_STONE_FURNACE.get(),
            GenerationsUtilityBlocks.VOLCANIC_STONE_FURNACE.get())
            .build(null));
    public static final RegistrySupplier<BlockEntityType<GenericBlastFurnaceBlockEntity>> GENERIC_BLAST_FURNACE = BLOCK_ENTITIES.register("generic_blast_furnace", () -> BlockEntityType.Builder.of(GenericBlastFurnaceBlockEntity::new,
            GenerationsUtilityBlocks.CHARGE_STONE_BLAST_FURNACE.get(),
            GenerationsUtilityBlocks.VOLCANIC_STONE_BLAST_FURNACE.get())
    .build(null));
    public static final RegistrySupplier<BlockEntityType<GenericSmokerBlockEntity>> GENERIC_SMOKER = BLOCK_ENTITIES.register("generic_smoker", () -> BlockEntityType.Builder.of(GenericSmokerBlockEntity::new,
            GenerationsUtilityBlocks.CHARGE_STONE_SMOKER.get(),
            GenerationsUtilityBlocks.VOLCANIC_STONE_SMOKER.get()
    ).build(null));
    public static final RegistrySupplier<BlockEntityType<GenericDyedVariantBlockEntity>> GENERIC_DYED_VARIANT = BLOCK_ENTITIES.register("generic_dyed_variant", () -> BlockEntityType.Builder.of(GenericDyedVariantBlockEntity::new,
            GenerationsDecorationBlocks.PASTEL_BEAN_BAG.get(),
            GenerationsUtilityBlocks.PC.get(),
            GenerationsUtilityBlocks.TRASH_CAN.get())
            .build(null));

    public static final RegistrySupplier<BlockEntityType<GenericModelProvidingBlockEntity>> GENERIC_MODEL_PROVIDING = BLOCK_ENTITIES.register("generic_model_providing", () -> BlockEntityType.Builder.of(GenericModelProvidingBlockEntity::new,
                    GenerationsDecorationBlocks.SNORLAX_BEAN_BAG.get(),
                    GenerationsDecorationBlocks.SWITCH.get(),
                    GenerationsDecorationBlocks.HOUSE_LAMP.get(),
                    GenerationsDecorationBlocks.LITWICK_CANDLE.get(),
                    GenerationsDecorationBlocks.LITWICK_CANDLES.get(),
                    GenerationsBlocks.POKECENTER_SCARLET_SIGN.get(),
                    GenerationsUtilityBlocks.TRASH_CAN.get(),
                    GenerationsUtilityBlocks.RKS_MACHINE.get())
            .build(null));
    public static final RegistrySupplier<BlockEntityType<MachineBlockEntity>> MACHINE_BLOCK = BLOCK_ENTITIES.register("machine_block", () -> BlockEntityType.Builder.of(MachineBlockEntity::new, GenerationsBlocks.MACHINE_BLOCK.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<VendingMachineBlockEntity>> VENDING_MACHINE = BLOCK_ENTITIES.register("vending_machine", () -> BlockEntityType.Builder.of(VendingMachineBlockEntity::new, GenerationsDecorationBlocks.VENDING_MACHINE.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<BallDisplayBlockEntity>> BALL_DISPLAY = BLOCK_ENTITIES.register("ball_display", () -> BlockEntityType.Builder.of(BallDisplayBlockEntity::new,
            GenerationsDecorationBlocks.EMPTY_BALL_DISPLAY.get(),
            GenerationsDecorationBlocks.POKE_BALL_DISPLAY.get(),
            GenerationsDecorationBlocks.GREAT_BALL_DISPLAY.get(),
            GenerationsDecorationBlocks.ULTRA_BALL_DISPLAY.get(),
            GenerationsDecorationBlocks.MASTER_BALL_DISPLAY.get(),
            GenerationsDecorationBlocks.CHERISH_BALL_DISPLAY.get(),
            GenerationsDecorationBlocks.DIVE_BALL_DISPLAY.get(),
            GenerationsDecorationBlocks.DUSK_BALL_DISPLAY.get(),
            GenerationsDecorationBlocks.FAST_BALL_DISPLAY.get(),
            GenerationsDecorationBlocks.FRIEND_BALL_DISPLAY.get(),
            GenerationsDecorationBlocks.GS_BALL_DISPLAY.get(),
            GenerationsDecorationBlocks.HEAL_BALL_DISPLAY.get(),
            GenerationsDecorationBlocks.HEAVY_BALL_DISPLAY.get(),
            GenerationsDecorationBlocks.LEVEL_BALL_DISPLAY.get(),
            GenerationsDecorationBlocks.LOVE_BALL_DISPLAY.get(),
            GenerationsDecorationBlocks.LURE_BALL_DISPLAY.get(),
            GenerationsDecorationBlocks.LUXURY_BALL_DISPLAY.get(),
            GenerationsDecorationBlocks.MOON_BALL_DISPLAY.get(),
            GenerationsDecorationBlocks.NEST_BALL_DISPLAY.get(),
            GenerationsDecorationBlocks.NET_BALL_DISPLAY.get(),
            GenerationsDecorationBlocks.PARK_BALL_DISPLAY.get(),
            GenerationsDecorationBlocks.PREMIER_BALL_DISPLAY.get(),
            GenerationsDecorationBlocks.QUICK_BALL_DISPLAY.get(),
            GenerationsDecorationBlocks.REPEAT_BALL_DISPLAY.get(),
            GenerationsDecorationBlocks.SAFARI_BALL_DISPLAY.get(),
            GenerationsDecorationBlocks.SPORT_BALL_DISPLAY.get(),
            GenerationsDecorationBlocks.TIMER_BALL_DISPLAY.get()
    ).build(null));

    public static final RegistrySupplier<BlockEntityType<PcBlockEntity>> PC = BLOCK_ENTITIES.register("pc", () -> BlockEntityType.Builder.of(PcBlockEntity::new, GenerationsUtilityBlocks.PC.get()).build(null));

    public static final RegistrySupplier<BlockEntityType<BallLootBlockEntity>> BALL_LOOT = BLOCK_ENTITIES.register("poke_loot", () -> BlockEntityType.Builder.of(BallLootBlockEntity::new,
            GenerationsUtilityBlocks.BEAST_BALL_LOOT.get(),
            GenerationsUtilityBlocks.CHERISH_BALL_LOOT.get(),
            GenerationsUtilityBlocks.DIVE_BALL_LOOT.get(),
            GenerationsUtilityBlocks.DREAM_BALL_LOOT.get(),
            GenerationsUtilityBlocks.DUSK_BALL_LOOT.get(),
            GenerationsUtilityBlocks.FAST_BALL_LOOT.get(),
            GenerationsUtilityBlocks.FRIEND_BALL_LOOT.get(),
            GenerationsUtilityBlocks.GIGATON_BALL_LOOT.get(),
            GenerationsUtilityBlocks.GREAT_BALL_LOOT.get(),
            GenerationsUtilityBlocks.HEAL_BALL_LOOT.get(),
            GenerationsUtilityBlocks.HEAVY_BALL_LOOT.get(),
            GenerationsUtilityBlocks.JET_BALL_LOOT.get(),
            GenerationsUtilityBlocks.LEADEN_BALL_LOOT.get(),
            GenerationsUtilityBlocks.LEVEL_BALL_LOOT.get(),
            GenerationsUtilityBlocks.LOVE_BALL_LOOT.get(),
            GenerationsUtilityBlocks.LURE_BALL_LOOT.get(),
            GenerationsUtilityBlocks.LUXURY_BALL_LOOT.get(),
            GenerationsUtilityBlocks.MASTER_BALL_LOOT.get(),
            GenerationsUtilityBlocks.MOON_BALL_LOOT.get(),
            GenerationsUtilityBlocks.NEST_BALL_LOOT.get(),
            GenerationsUtilityBlocks.NET_BALL_LOOT.get(),
            GenerationsUtilityBlocks.ORIGIN_BALL_LOOT.get(),
            GenerationsUtilityBlocks.PARK_BALL_LOOT.get(),
            GenerationsUtilityBlocks.POKE_BALL_LOOT.get(),
            GenerationsUtilityBlocks.PREMIER_BALL_LOOT.get(),
            GenerationsUtilityBlocks.QUICK_BALL_LOOT.get(),
            GenerationsUtilityBlocks.REPEAT_BALL_LOOT.get(),
            GenerationsUtilityBlocks.SAFARI_BALL_LOOT.get(),
            GenerationsUtilityBlocks.SPORT_BALL_LOOT.get(),
            GenerationsUtilityBlocks.STRANGE_BALL_LOOT.get(),
            GenerationsUtilityBlocks.TIMER_BALL_LOOT.get(),
            GenerationsUtilityBlocks.ULTRA_BALL_LOOT.get(),
            GenerationsUtilityBlocks.WING_BALL_LOOT.get()
    ).build(null));


    public static void init() {
        GenerationsCore.LOGGER.info("Registering Generations Block Entities");
        BLOCK_ENTITIES.register();
    }
}
