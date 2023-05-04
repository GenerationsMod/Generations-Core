package generations.gg.generations.core.generationscore.world.level.block.entities;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.level.block.*;
import generations.gg.generations.core.generationscore.world.level.block.entities.generic.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class PokeModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.BLOCK_ENTITY_TYPE);

    public static final RegistrySupplier<BlockEntityType<PokeDollBlockEntity>> POKE_DOLL = BLOCK_ENTITIES.register("pokedoll", () -> BlockEntityType.Builder.of(PokeDollBlockEntity::new,
            PokeModPokeDolls.SHINY_CELEBI_POKEDOLL.get(),
            PokeModPokeDolls.CHARIZARD_POKEDOLL.get(),
            PokeModPokeDolls.BLASTOISE_POKEDOLL.get(),
            PokeModPokeDolls.CLEFAIRY_POKEDOLL.get(),
            PokeModPokeDolls.CYNDAQUIL_POKEDOLL.get(),
            PokeModPokeDolls.PIKACHU_POKEDOLL.get(),
            PokeModPokeDolls.TREECKO_POKEDOLL.get(),
            PokeModPokeDolls.VENUSAUR_POKEDOLL.get(),
            PokeModPokeDolls.DUSKULL_POKEDOLL.get(),
            PokeModPokeDolls.MEOWTH_POKEDOLL.get(),
            PokeModPokeDolls.MUDKIP_POKEDOLL.get(),
            PokeModPokeDolls.AZURILL_POKEDOLL.get(),
            PokeModPokeDolls.BALTOY_POKEDOLL.get(),
            PokeModPokeDolls.CHIKORITA_POKEDOLL.get(),
            PokeModPokeDolls.DITTO_POKEDOLL.get(),
            PokeModPokeDolls.GULPIN_POKEDOLL.get(),
            PokeModPokeDolls.JIGGLYPUFF_POKEDOLL.get(),
            PokeModPokeDolls.KECLEON_POKEDOLL.get(),
            PokeModPokeDolls.LAPRAS_POKEDOLL.get(),
            PokeModPokeDolls.LOTAD_POKEDOLL.get(),
            PokeModPokeDolls.MARILL_POKEDOLL.get(),
            PokeModPokeDolls.PICHU_POKEDOLL.get(),
            PokeModPokeDolls.REGICE_POKEDOLL.get(),
            PokeModPokeDolls.REGIROCK_POKEDOLL.get(),
            PokeModPokeDolls.REGISTEEL_POKEDOLL.get(),
            PokeModPokeDolls.RHYDON_POKEDOLL.get(),
            PokeModPokeDolls.SEEDOT_POKEDOLL.get(),
            PokeModPokeDolls.SKITTY_POKEDOLL.get(),
            PokeModPokeDolls.SMOOCHUM_POKEDOLL.get(),
            PokeModPokeDolls.SNORLAX_POKEDOLL.get(),
            PokeModPokeDolls.SUBSTITUTE_POKEDOLL.get(),
            PokeModPokeDolls.SWABLU_POKEDOLL.get(),
            PokeModPokeDolls.TOGEPI_POKEDOLL.get(),
            PokeModPokeDolls.TORCHIC_POKEDOLL.get(),
            PokeModPokeDolls.TOTODILE_POKEDOLL.get(),
            PokeModPokeDolls.WAILMER_POKEDOLL.get(),
            PokeModPokeDolls.WYNAUT_POKEDOLL.get(),
            PokeModPokeDolls.EEVEE_POKEDOLL.get(),
            PokeModPokeDolls.ROWLET_POKEDOLL.get(),
            PokeModPokeDolls.POPPLIO_POKEDOLL.get(),
            PokeModPokeDolls.POLIWHIRL_POKEDOLL.get(),
            PokeModPokeDolls.ZERAORA_POKEDOLL.get(),
            PokeModPokeDolls.CRESSELIA_POKEDOLL.get(),
            PokeModPokeDolls.LITTEN_POKEDOLL.get(),
            PokeModPokeDolls.CUBONE_POKEDOLL.get(),
            PokeModPokeDolls.DIALGA_POKEDOLL.get(),
            PokeModPokeDolls.ARCEUS_POKEDOLL.get(),
            PokeModPokeDolls.SABLEYE_POKEDOLL.get(),
            PokeModPokeDolls.BLUEGHOST_POKEDOLL.get(),
            PokeModPokeDolls.PURPLEGHOST_POKEDOLL.get(),
            PokeModPokeDolls.EKANS_POKEDOLL.get(),
            PokeModPokeDolls.ARTICUNO_POKEDOLL.get(),
            PokeModPokeDolls.AZELF_POKEDOLL.get(),
            PokeModPokeDolls.DARKRAI_POKEDOLL.get(),
            PokeModPokeDolls.DEOXYS_POKEDOLL.get(),
            PokeModPokeDolls.ENTEI_POKEDOLL.get(),
            PokeModPokeDolls.GIRATINA_ALTERED_POKEDOLL.get(),
            PokeModPokeDolls.GIRATINA_ORIGINAL_POKEDOLL.get(),
            PokeModPokeDolls.GROUDON_POKEDOLL.get(),
            PokeModPokeDolls.HOOH_POKEDOLL.get(),
            PokeModPokeDolls.JIRACHI_POKEDOLL.get(),
            PokeModPokeDolls.KYOGRE_POKEDOLL.get(),
            PokeModPokeDolls.LATIAS_POKEDOLL.get(),
            PokeModPokeDolls.LATIOS_POKEDOLL.get(),
            PokeModPokeDolls.LUGIA_POKEDOLL.get(),
            PokeModPokeDolls.MANAPHY_POKEDOLL.get(),
            PokeModPokeDolls.MESPRIT_POKEDOLL.get(),
            PokeModPokeDolls.MEW_POKEDOLL.get(),
            PokeModPokeDolls.MEWTWO_POKEDOLL.get(),
            PokeModPokeDolls.MOLTRES_POKEDOLL.get(),
            PokeModPokeDolls.PALKIA_POKEDOLL.get(),
            PokeModPokeDolls.RAIKOU_POKEDOLL.get(),
            PokeModPokeDolls.RAYQUAZA_POKEDOLL.get(),
            PokeModPokeDolls.REGIGIGAS_POKEDOLL.get(),
            PokeModPokeDolls.SHAYMIN_LAND_POKEDOLL.get(),
            PokeModPokeDolls.SHAYMIN_SKY_POKEDOLL.get(),
            PokeModPokeDolls.SUICUNE_POKEDOLL.get(),
            PokeModPokeDolls.UXIE_POKEDOLL.get(),
            PokeModPokeDolls.ZAPDOS_POKEDOLL.get(),
            PokeModPokeDolls.HERACROSS_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_CHARIZARD_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_BLASTOISE_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_CLEFAIRY_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_CYNDAQUIL_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_PIKACHU_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_TREECKO_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_VENUSAUR_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_DUSKULL_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_MEOWTH_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_MUDKIP_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_AZURILL_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_BALTOY_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_CHIKORITA_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_DITTO_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_GULPIN_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_JIGGLYPUFF_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_KECLEON_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_LAPRAS_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_LOTAD_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_MARILL_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_PICHU_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_REGICE_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_REGIROCK_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_REGISTEEL_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_RHYDON_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_SEEDOT_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_SKITTY_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_SMOOCHUM_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_SNORLAX_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_SUBSTITUTE_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_SWABLU_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_TOGEPI_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_TORCHIC_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_TOTODILE_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_WAILMER_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_WYNAUT_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_EEVEE_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_ROWLET_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_POPPLIO_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_POLIWHIRL_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_ZERAORA_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_CRESSELIA_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_LITTEN_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_CUBONE_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_DIALGA_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_ARCEUS_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_KRABBY_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_SABLEYE_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_EKANS_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_ARTICUNO_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_AZELF_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_DARKRAI_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_DEOXYS_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_ENTEI_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_GIRATINA_ALTERED_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_GIRATINA_ORIGIN_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_GROUDON_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_HERACROSS_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_HOOH_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_JIRACHI_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_KYOGRE_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_LATIAS_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_LATIOS_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_LUGIA_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_MANAPHY_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_MESPRIT_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_MEW_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_MEWTWO_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_MOLTRES_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_PALKIA_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_RAIKOU_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_RAYQUAZA_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_REGIGIGAS_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_SHAYMIN_LAND_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_SHAYMIN_SKY_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_SUICUNE_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_UXIE_POKEDOLL.get(),
            PokeModPokeDolls.SHINY_ZAPDOS_POKEDOLL.get(),
            PokeModPokeDolls.KRABBY_POKEDOLL.get(),
            PokeModPokeDolls.CELEBI_POKEDOLL.get()
    ).build(null));

    public static final RegistrySupplier<BlockEntityType<HealerBlockEntity>> HEALER = BLOCK_ENTITIES.register("healer", () -> BlockEntityType.Builder.of(HealerBlockEntity::new, PokeModUtilityBlocks.HEALER.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<ClockBlockEntity>> CLOCK = BLOCK_ENTITIES.register("clock", () -> BlockEntityType.Builder.of(ClockBlockEntity::new, PokeModUtilityBlocks.CLOCK.get()).build(null));

    public static final RegistrySupplier<BlockEntityType<BoxBlockEntity>> BOX = BLOCK_ENTITIES.register("box", () -> BlockEntityType.Builder.of(BoxBlockEntity::new, PokeModUtilityBlocks.BOX.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<GenericShrineBlockEntity>> GENERIC_SHRINE = BLOCK_ENTITIES.register("generic_shrine", () -> BlockEntityType.Builder.of(GenericShrineBlockEntity::new,
                    PokeModShrines.STATIC_SHRINE.get(),
                    PokeModShrines.FIERY_SHRINE.get(),
                    PokeModShrines.FROZEN_SHRINE.get(),
                    PokeModShrines.LUGIA_SHRINE.get(),
                    PokeModShrines.CRYSTAL_BELL.get(),
                    PokeModShrines.REGICE_SHRINE.get(),
                    PokeModShrines.REGIDRAGO_SHRINE.get(),
                    PokeModShrines.REGIELEKI_SHRINE.get(),
                    PokeModShrines.REGIROCK_SHRINE.get(),
                    PokeModShrines.REGISTEEL_SHRINE.get(),
                    PokeModShrines.TAPU_SHRINE.get())
            .build(null));
    public static final RegistrySupplier<BlockEntityType<WeatherTrioShrineBlockEntity>> WEATHER_TRIO = BLOCK_ENTITIES.register("weather_trio", () -> BlockEntityType.Builder.of(WeatherTrioShrineBlockEntity::new,
                    PokeModShrines.KYOGRE_SHRINE.get(),
                    PokeModShrines.GROUDON_SHRINE.get(),
                    PokeModShrines.RAYQUAZA_SHRINE.get())
            .build(null));
    public static final RegistrySupplier<BlockEntityType<TimeSpaceAltarBlockEntity>> TIMESPACE_ALTAR = BLOCK_ENTITIES.register("timespace_altar", () -> BlockEntityType.Builder.of(TimeSpaceAltarBlockEntity::new, PokeModShrines.TIMESPACE_ALTAR.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<AbundantShrineBlockEntity>> ABUNDANT_SHRINE = BLOCK_ENTITIES.register("abundant_shrine", () -> BlockEntityType.Builder.of(AbundantShrineBlockEntity::new, PokeModShrines.ABUNDANT_SHRINE.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<CelestialAltarBlockEntity>> CELESTIAL_ALTAR = BLOCK_ENTITIES.register("celestial_altar", () -> BlockEntityType.Builder.of(CelestialAltarBlockEntity::new, PokeModShrines.CELESTIAL_ALTAR.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<LunarShrineBlockEntity>> LUNAR_SHRINE = BLOCK_ENTITIES.register("lunar_shrine", () -> BlockEntityType.Builder.of(LunarShrineBlockEntity::new, PokeModShrines.LUNAR_SHRINE.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<MeloettaMusicBoxBlockEntity>> MELOETTA_MUSIC_BOX = BLOCK_ENTITIES.register("meloetta_music_box", () -> BlockEntityType.Builder.of(MeloettaMusicBoxBlockEntity::new, PokeModShrines.MELOETTA_MUSIC_BOX.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<RegigigasShrineBlockEntity>> REGIGIGAS_SHRINE = BLOCK_ENTITIES.register("regigigas_shrine", ()-> BlockEntityType.Builder.of(RegigigasShrineBlockEntity::new, PokeModShrines.REGIGIGAS_SHRINE.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<TaoTrioShrineBlockEntity>> TAO_TRIO_SHRINE = BLOCK_ENTITIES.register("tao_trio_shrine", () -> BlockEntityType.Builder.of(TaoTrioShrineBlockEntity::new, PokeModShrines.TAO_TRIO_SHRINE.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<TapuShrineBlockEntity>> TAPU_SHRINE = BLOCK_ENTITIES.register("tapu_shrine", () -> BlockEntityType.Builder.of(TapuShrineBlockEntity::new, PokeModShrines.TAPU_SHRINE.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<CookingPotBlockEntity>> COOKING_POT = BLOCK_ENTITIES.register("cooking_pot", () -> BlockEntityType.Builder.of(CookingPotBlockEntity::new, PokeModUtilityBlocks.COOKING_POT.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<GenericChestBlockEntity>> GENERIC_CHEST = BLOCK_ENTITIES.register("generic_chest", () -> BlockEntityType.Builder.of(GenericChestBlockEntity::new, GenerationsBlocks.POKEBALL_CHEST.get(), GenerationsBlocks.GREATBALL_CHEST.get(), GenerationsBlocks.ULTRABALL_CHEST.get(), GenerationsBlocks.MASTERBALL_CHEST.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<PokeModSignBlockEntity>> SIGN_BLOCK_ENTITIES =
            BLOCK_ENTITIES.register("sign_block_entity", () ->
                    BlockEntityType.Builder.of(PokeModSignBlockEntity::new,
                            PokeModWood.ULTRA_DARK_SIGN.get(),
                            PokeModWood.ULTRA_DARK_WALL_SIGN.get(),
                            PokeModWood.ULTRA_JUNGLE_SIGN.get(),
                            PokeModWood.ULTRA_JUNGLE_WALL_SIGN.get(),
                            PokeModWood.GHOST_SIGN.get(),
                            PokeModWood.GHOST_WALL_SIGN.get()
                    ).build(null));

    public static final RegistrySupplier<BlockEntityType<PokeModHangingSignBlockEntity>> HANGING_SIGN_BLOCK_ENTITIES =
            BLOCK_ENTITIES.register("hanging_sign_block_entity", () ->
                    BlockEntityType.Builder.of(PokeModHangingSignBlockEntity::new,
                            PokeModWood.ULTRA_DARK_HANGING_SIGN.get(),
                            PokeModWood.ULTRA_DARK_WALL_HANGING_SIGN.get(),
                            PokeModWood.ULTRA_JUNGLE_HANGING_SIGN.get(),
                            PokeModWood.ULTRA_JUNGLE_WALL_HANGING_SIGN.get(),
                            PokeModWood.GHOST_HANGING_SIGN.get(),
                            PokeModWood.GHOST_WALL_HANGING_SIGN.get()
                    ).build(null));
    public static final RegistrySupplier<BlockEntityType<BreederBlockEntity>> BREEDER = BLOCK_ENTITIES.register("breeder", () -> BlockEntityType.Builder.of(BreederBlockEntity::new, PokeModUtilityBlocks.BREEDER.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<GenericFurnaceBlockEntity>> GENERIC_FURNACE = BLOCK_ENTITIES.register("generic_furnace", () -> BlockEntityType.Builder.of(GenericFurnaceBlockEntity::new,
            PokeModUtilityBlocks.CHARGE_STONE_FURNACE.get(),
            PokeModUtilityBlocks.VOLCANIC_STONE_FURNACE.get())
            .build(null));
    public static final RegistrySupplier<BlockEntityType<GenericBlastFurnaceBlockEntity>> GENERIC_BLAST_FURNACE = BLOCK_ENTITIES.register("generic_blast_furnace", () -> BlockEntityType.Builder.of(GenericBlastFurnaceBlockEntity::new,
            PokeModUtilityBlocks.CHARGE_STONE_BLAST_FURNACE.get(),
            PokeModUtilityBlocks.VOLCANIC_STONE_BLAST_FURNACE.get())
    .build(null));
    public static final RegistrySupplier<BlockEntityType<GenericSmokerBlockEntity>> GENERIC_SMOKER = BLOCK_ENTITIES.register("generic_smoker", () -> BlockEntityType.Builder.of(GenericSmokerBlockEntity::new,
            PokeModUtilityBlocks.CHARGE_STONE_SMOKER.get(),
            PokeModUtilityBlocks.VOLCANIC_STONE_SMOKER.get()
    ).build(null));
    public static final RegistrySupplier<BlockEntityType<GenericDyedVariantBlockEntity>> GENERIC_DYED_VARIANT = BLOCK_ENTITIES.register("generic_dyed_variant", () -> BlockEntityType.Builder.of(GenericDyedVariantBlockEntity::new,
            PokeModDecorationBlocks.PASTEL_BEAN_BAG.get(),
            PokeModDecorationBlocks.UMBRELLA.get(),
            PokeModUtilityBlocks.PC.get(),
            PokeModUtilityBlocks.TRASH_CAN.get(),
            PokeModDecorationBlocks.POKEBALL_RUG.get())
            .build(null));

    public static final RegistrySupplier<BlockEntityType<GenericModelProvidingBlockEntity>> GENERIC_MODEL_PROVIDING = BLOCK_ENTITIES.register("generic_model_providing", () -> BlockEntityType.Builder.of(GenericModelProvidingBlockEntity::new,
                    PokeModDecorationBlocks.SNORLAX_BEAN_BAG.get(),
                    PokeModDecorationBlocks.SWITCH.get(),
                    PokeModDecorationBlocks.HOUSE_LAMP.get(),
                    PokeModDecorationBlocks.LITWICK_CANDLE.get(),
                    PokeModDecorationBlocks.LITWICK_CANDLES.get(),
                    GenerationsBlocks.POKECENTER_SCARLET_SIGN.get(),
                    PokeModUtilityBlocks.TRASH_CAN.get())
            .build(null));
    public static final RegistrySupplier<BlockEntityType<MachineBlockEntity>> MACHINE_BLOCK = BLOCK_ENTITIES.register("machine_block", () -> BlockEntityType.Builder.of(MachineBlockEntity::new, GenerationsBlocks.MACHINE_BLOCK.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<VendingMachineBlockEntity>> VENDING_MACHINE = BLOCK_ENTITIES.register("vending_machine", () -> BlockEntityType.Builder.of(VendingMachineBlockEntity::new, PokeModDecorationBlocks.VENDING_MACHINE.get()).build(null));


    public static void onInitialize() {
        GenerationsCore.LOGGER.info("Registering PokeMod Block Entities");
        BLOCK_ENTITIES.register();
    }
}
