package generations.gg.generations.core.generationscore.forge.datagen.generators.recipe;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.level.ItemLike;

import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

import static generations.gg.generations.core.generationscore.world.item.GenerationsItems.*;

public class TmRecipeDatagen extends GenerationsRecipeProvider.Proxied {
    public TmRecipeDatagen(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        registerTmRecipe(consumer, TM_1, LECHONK_HAIR, YUNGOOS_FUR);
        registerTmRecipe(consumer, TM_2, AZURILL_FUR, TEDDIURSA_CLAW);
        registerTmRecipe(consumer, TM_3, BONSLY_TEARS, TEDDIURSA_CLAW);
        registerTmRecipe(consumer, TM_4, FLETCHLING_FEATHER, ORICORIO_FEATHER);
        registerTmRecipe(consumer, TM_5, WOOPER_SLIME);
        registerTmRecipe(consumer, TM_6, STANTLER_HAIR, SANDILE_CLAW);
        registerTmRecipe(consumer, TM_7, LECHONK_HAIR, SCATTERBUG_POWDER);
        registerTmRecipe(consumer, TM_8, HOUNDOUR_FANG);
        registerTmRecipe(consumer, TM_9, SHINX_FANG);
        registerTmRecipe(consumer, TM_10, CUBCHOO_FUR);
        registerTmRecipe(consumer, TM_11, BUIZEL_FUR, MAGIKARP_SCALES);
        registerTmRecipe(consumer, TM_12, MANKEY_FUR);
        registerTmRecipe(consumer, TM_13, TOXEL_SPARKS, WOOPER_SLIME);
        registerTmRecipe(consumer, TM_14, WATTREL_FEATHER, BOMBIRDIER_FEATHER);
        registerTmRecipe(consumer, TM_15, TAROUNTULA_THREAD);
        registerTmRecipe(consumer, TM_16, PSYDUCK_DOWN);
        registerTmRecipe(consumer, TM_17, GASTLY_GAS, MAREEP_WOOL);
        registerTmRecipe(consumer, TM_18, HOUNDOUR_FANG, SHROODLE_INK);
        registerTmRecipe(consumer, TM_19, RALTS_DUST);
        registerTmRecipe(consumer, TM_20, PETILIL_LEAF, HOPPIP_LEAF);
        registerTmRecipe(consumer, TM_21, NYMBLE_CLAW, TAROUNTULA_THREAD);
        registerTmRecipe(consumer, TM_22, PSYDUCK_DOWN, SURSKIT_SYRUP);
        registerTmRecipe(consumer, TM_23, MAREEP_WOOL, DEDENNE_FUR);
        registerTmRecipe(consumer, TM_24, GROWLITHE_FUR, TORKOAL_COAL);
        registerTmRecipe(consumer, TM_25, KOMALA_CLAW, TINKATINK_HAIR, STANTLER_HAIR);
        registerTmRecipe(consumer, TM_26, SEVIPER_FANG, SHROODLE_INK);
        registerTmRecipe(consumer, TM_27, STARLY_FEATHER, FLETCHLING_FEATHER);
        registerTmRecipe(consumer, TM_28, MUDBRAY_MUD, SANDYGAST_SAND);
        registerTmRecipe(consumer, TM_29, MIMIKYU_SCRAP, GREAVARD_WAX);
        registerTmRecipe(consumer, TM_30, MASCHIFF_FANG, SQUAWKABILLY_FEATHER);
        registerTmRecipe(consumer, TM_31, TEDDIURSA_CLAW);
        registerTmRecipe(consumer, TM_32, FLETCHLING_FEATHER);
        registerTmRecipe(consumer, TM_33, SMOLIV_OIL, PETILIL_LEAF);
        registerTmRecipe(consumer, TM_34, SNOM_THREAD, SNOVER_BERRIES);
        registerTmRecipe(consumer, TM_35, SANDILE_CLAW, WOOPER_SLIME);
        registerTmRecipe(consumer, TM_36, ROCKRUFF_ROCK, KLAWF_CLAW);
        registerTmRecipe(consumer, TM_37, IGGLYBUFF_FLUFF, FLABEBE_POLLEN);
        registerTmRecipe(consumer, TM_38, FLETCHLING_FEATHER, TORKOAL_COAL);
        registerTmRecipe(consumer, TM_39, CROAGUNK_POISON, MANKEY_FUR);
        registerTmRecipe(consumer, TM_40, COMBEE_HONEY);
        registerTmRecipe(consumer, TM_41, RALTS_DUST, GOTHITA_EYELASH);
        registerTmRecipe(consumer, TM_42, GASTLY_GAS, MURKROW_BAUBLE);
        registerTmRecipe(consumer, TM_43, SNEASEL_CLAW, HAPPINY_DUST);
        registerTmRecipe(consumer, TM_44, SANDILE_CLAW, DRATINI_SCALES);
        registerTmRecipe(consumer, TM_45, TOXEL_SPARKS, SALANDIT_GAS);
        registerTmRecipe(consumer, TM_46, BERGMITE_ICE, SNORUNT_FUR);
        registerTmRecipe(consumer, TM_47, SCATTERBUG_POWDER);
        registerTmRecipe(consumer, TM_48, DEDENNE_FUR, SHINX_FANG);
        registerTmRecipe(consumer, TM_49, SUNKERN_LEAF, TORKOAL_COAL, LITLEO_TUFT);
        registerTmRecipe(consumer, TM_50, SHELLOS_MUCUS, AZURILL_FUR, WATTREL_FEATHER);
        registerTmRecipe(consumer, TM_51, HIPPOPOTAS_SAND, SILICOBRA_SAND, SANDYGAST_SAND);
        registerTmRecipe(consumer, TM_52, SNOVER_BERRIES, DELIBIRD_PARCEL, SNOM_THREAD);
        registerTmRecipe(consumer, TM_53, CHEWTLE_CLAW, HERACROSS_CLAW);
        registerTmRecipe(consumer, TM_54, SPOINK_PEARL, MEDITITE_SWEAT, DROWZEE_FUR);
        registerTmRecipe(consumer, TM_55, DIGLETT_DIRT, GREAVARD_WAX, ORTHWORM_TARNISH);
        registerTmRecipe(consumer, TM_56, SUNKERN_LEAF, HOPPIP_LEAF);
        registerTmRecipe(consumer, TM_57, KRICKETOT_SHELL, CHEWTLE_CLAW);
        registerTmRecipe(consumer, TM_58, MAKUHITA_SWEAT, HAWLUCHA_DOWN, CRABRAWLER_SHELL);
        registerTmRecipe(consumer, TM_59, VELUZA_FILLET, GIRAFARIG_FUR, DUNSPARCE_SCALES);
        registerTmRecipe(consumer, TM_60, NYMBLE_CLAW, SCYTHER_CLAW);
        registerTmRecipe(consumer, TM_61, MIMIKYU_SCRAP, KOMALA_CLAW);
        registerTmRecipe(consumer, TM_62, MURKROW_BAUBLE, SANDILE_CLAW);
        registerTmRecipe(consumer, TM_63, BRUXISH_TOOTH, BASCULIN_FANG, VELUZA_FILLET);
        registerTmRecipe(consumer, TM_64, MAKUHITA_SWEAT, AXEW_SCALES);
        registerTmRecipe(consumer, TM_65, NOIBAT_FUR, WINGULL_FEATHER, FLAMIGO_DOWN);
        registerTmRecipe(consumer, TM_66, SKWOVET_FUR, ALOMOMOLA_MUCUS, CHEWTLE_CLAW);
        registerTmRecipe(consumer, TM_67, MEDITITE_SWEAT, CHARCADET_SOOT);
        registerTmRecipe(consumer, TM_68, TOXEL_SPARKS, MEDITITE_SWEAT);
        registerTmRecipe(consumer, TM_69, CUBCHOO_FUR, MEDITITE_SWEAT);
        registerTmRecipe(consumer, TM_70, HIPPOPOTAS_SAND, SLOWPOKE_CLAW);
        registerTmRecipe(consumer, TM_71, SHROOMISH_SPORES, BRAMBLIN_TWIG, SMOLIV_OIL);
        registerTmRecipe(consumer, TM_72, TADBULB_MUCUS, PACHIRISU_FUR, VOLTORB_SPARKS);
        registerTmRecipe(consumer, TM_73, CROAGUNK_POISON, MANKEY_FUR, CRABRAWLER_SHELL);
        registerTmRecipe(consumer, TM_74, FLITTLE_DOWN, DROWZEE_FUR);
        registerTmRecipe(consumer, TM_75, MAGNEMITE_SCREW, VOLTORB_SPARKS);
        registerTmRecipe(consumer, TM_76, NACLI_SALT, CHEWTLE_CLAW);
        registerTmRecipe(consumer, TM_77, MAGIKARP_SCALES, BASCULIN_FANG, ARROKUDA_SCALES);
        registerTmRecipe(consumer, TM_78, AXEW_SCALES, NOIBAT_FUR, GIBLE_SCALES);
        registerTmRecipe(consumer, TM_79, HATENNA_DUST, SWABLU_FLUFF, FIDOUGH_FUR);
        registerTmRecipe(consumer, TM_80, IGGLYBUFF_FLUFF, HAPPINY_DUST);
        registerTmRecipe(consumer, TM_81, CACNEA_NEEDLE, SHROOMISH_SPORES);
        registerTmRecipe(consumer, TM_82, MAREEP_WOOL, PAWMI_FUR);
        registerTmRecipe(consumer, TM_83, SHROODLE_INK, SEVIPER_FANG, MAREANIE_SPIKE);
        registerTmRecipe(consumer, TM_84, MUDBRAY_MUD, PHANPY_NAIL);
        registerTmRecipe(consumer, TM_85, DROWZEE_FUR);
        registerTmRecipe(consumer, TM_86, NACLI_SALT, ROCKRUFF_ROCK, BONSLY_TEARS);
        registerTmRecipe(consumer, TM_87, MEOWTH_FUR, SABLEYE_GEM, SNEASEL_CLAW);
        registerTmRecipe(consumer, TM_88, ZANGOOSE_CLAW, GIBLE_SCALES, SCYTHER_CLAW);
        registerTmRecipe(consumer, TM_89, CETODDLE_GREASE, HAWLUCHA_DOWN, PAWNIARD_BLADE);
        registerTmRecipe(consumer, TM_90, PINCURCHIN_SPINES, QWILFISH_SPINES);
        registerTmRecipe(consumer, TM_91, MAREANIE_SPIKE, PINECO_HUSK);
        registerTmRecipe(consumer, TM_92, BRONZOR_FRAGMENT, ZORUA_FUR);
        registerTmRecipe(consumer, TM_93, VAROOM_FUME, KLEFKI_KEY, TINKATINK_HAIR);
        registerTmRecipe(consumer, TM_94, ZORUA_FUR, IMPIDIMP_HAIR, SPIRITOMB_FRAGMENT);
        registerTmRecipe(consumer, TM_95, SURSKIT_SYRUP, VENONAT_FANG, KRICKETOT_SHELL);
        registerTmRecipe(consumer, TM_96, VOLTORB_SPARKS, SHINX_FANG);
        registerTmRecipe(consumer, TM_97, SQUAWKABILLY_FEATHER, BOMBIRDIER_FEATHER, RUFFLET_FEATHER);
        registerTmRecipe(consumer, TM_98, GIRAFARIG_FUR, FLITTLE_DOWN);
        registerTmRecipe(consumer, TM_99, CUFANT_TARNISH, PAWNIARD_BLADE, ROOKIDEE_FEATHER);
        registerTmRecipe(consumer, TM_100, TATSUGIRI_SCALES, GIBLE_SCALES, NOIBAT_FUR);
        registerTmRecipe(consumer, TM_101, SPOINK_PEARL, SABLEYE_GEM, MAREEP_WOOL);
        registerTmRecipe(consumer, TM_102, GRIMER_TOXIN, CROAGUNK_POISON, VAROOM_FUME);
        registerTmRecipe(consumer, TM_103, MIMIKYU_SCRAP, AZURILL_FUR, FALINKS_SWEAT);
        registerTmRecipe(consumer, TM_104, PINECO_HUSK, BRONZOR_FRAGMENT);
        registerTmRecipe(consumer, TM_105, FOMANTIS_LEAF, TAROUNTULA_THREAD, KRICKETOT_SHELL);
        registerTmRecipe(consumer, TM_106, PINECO_HUSK, DUNSPARCE_SCALES, ARROKUDA_SCALES);
        registerTmRecipe(consumer, TM_107, SHUPPET_SCRAP, SALANDIT_GAS);
        registerTmRecipe(consumer, TM_108, MASCHIFF_FANG, BRUXISH_TOOTH, YUNGOOS_FUR);
        registerTmRecipe(consumer, TM_109, SHUPPET_SCRAP, SABLEYE_GEM, SINISTEA_CHIP);
        registerTmRecipe(consumer, TM_110, ARROKUDA_SCALES, WIGLETT_SAND, BUIZEL_FUR);
        registerTmRecipe(consumer, TM_111, CAPSAKID_SEED, HOPPIP_LEAF, SKIDDO_LEAF);
        registerTmRecipe(consumer, TM_112, RALTS_DUST, RIOLU_FUR, CHARCADET_SOOT);
        registerTmRecipe(consumer, TM_113, ROOKIDEE_FEATHER, BOMBIRDIER_FEATHER, RUFFLET_FEATHER);
        registerTmRecipe(consumer, TM_114, GASTLY_GAS, SANDYGAST_SAND, SINISTEA_CHIP);
        registerTmRecipe(consumer, TM_115, GOOMY_GOO, SWABLU_FLUFF, TATSUGIRI_SCALES);
        registerTmRecipe(consumer, TM_116, ROLYCOLY_COAL, ROCKRUFF_ROCK);
        registerTmRecipe(consumer, TM_117, LITLEO_TUFT, TANDEMAUS_FUR, SKWOVET_FUR);
        registerTmRecipe(consumer, TM_118, GROWLITHE_FUR, TORKOAL_COAL, LARVESTA_FUZZ);
        registerTmRecipe(consumer, TM_119, DEERLING_HAIR, BRAMBLIN_TWIG, APPLIN_JUICE);
        registerTmRecipe(consumer, TM_120, RELLOR_MUD, INDEEDEE_FUR, RALTS_DUST);
        registerTmRecipe(consumer, TM_121, CUFANT_TARNISH, BRONZOR_FRAGMENT, DONDOZO_WHISKER);
        registerTmRecipe(consumer, TM_122, HAWLUCHA_DOWN, SLAKOTH_FUR);
        registerTmRecipe(consumer, TM_123, FINNEON_SCALES, FINIZEN_MUCUS, FRIGIBAX_SCALES);
        registerTmRecipe(consumer, TM_124, CETODDLE_GREASE, BERGMITE_ICE, FRIGIBAX_SCALES);
        registerTmRecipe(consumer, TM_125, LITLEO_TUFT, HOUNDOUR_FANG, NUMEL_LAVA);
        registerTmRecipe(consumer, TM_126, PACHIRISU_FUR, TADBULB_MUCUS);
        registerTmRecipe(consumer, TM_127, FIDOUGH_FUR, TANDEMAUS_FUR, TINKATINK_HAIR);
        registerTmRecipe(consumer, TM_128, SLOWPOKE_CLAW, SLAKOTH_FUR);
        registerTmRecipe(consumer, TM_129, STANTLER_HAIR, SLAKOTH_FUR);
        registerTmRecipe(consumer, TM_130, EEVEE_FUR);
        registerTmRecipe(consumer, TM_131, RELLOR_MUD, PETILIL_LEAF, KRICKETOT_SHELL);
        registerTmRecipe(consumer, TM_132, EEVEE_FUR, GIRAFARIG_FUR);
        registerTmRecipe(consumer, TM_133, SILICOBRA_SAND, SHELLOS_MUCUS, BARBOACH_SLIME);
        registerTmRecipe(consumer, TM_134, FALINKS_SWEAT, HERACROSS_CLAW, MANKEY_FUR);
        registerTmRecipe(consumer, TM_135, CRYOGONAL_ICE, SHELLDER_PEARL, DELIBIRD_PARCEL);
        registerTmRecipe(consumer, TM_136, PINCURCHIN_SPINES, TADBULB_MUCUS, PAWMI_FUR);
        registerTmRecipe(consumer, TM_137, SUNKERN_LEAF, FLABEBE_POLLEN, FOMANTIS_LEAF);
        registerTmRecipe(consumer, TM_138, SLOWPOKE_CLAW, INDEEDEE_FUR, DROWZEE_FUR);
        registerTmRecipe(consumer, TM_139, KLEFKI_KEY, IGGLYBUFF_FLUFF, FLABEBE_POLLEN);
        registerTmRecipe(consumer, TM_140, MEOWTH_FUR, SPIRITOMB_FRAGMENT, TATSUGIRI_SCALES);
        registerTmRecipe(consumer, TM_141, NUMEL_LAVA, SALANDIT_GAS, TORKOAL_COAL);
        registerTmRecipe(consumer, TM_142, FINIZEN_MUCUS, FINNEON_SCALES, LUVDISC_SCALES);
        registerTmRecipe(consumer, TM_143, SNORUNT_FUR, SNOVER_BERRIES, CRYOGONAL_ICE);
        registerTmRecipe(consumer, TM_144, SALANDIT_GAS, NUMEL_LAVA, CAPSAKID_SEED);
        registerTmRecipe(consumer, TM_145, LUVDISC_SCALES, ALOMOMOLA_MUCUS, SHELLDER_PEARL);
        registerTmRecipe(consumer, TM_146, APPLIN_JUICE, TOEDSCOOL_FLAPS, DEERLING_HAIR);
        registerTmRecipe(consumer, TM_147, SHINX_FANG, PICHU_FUR, TYNAMO_SLIME);
        registerTmRecipe(consumer, TM_148, CROAGUNK_POISON, GRIMER_TOXIN, FOONGUS_SPORES);
        registerTmRecipe(consumer, TM_149, PHANPY_NAIL, DIGLETT_DIRT, BARBOACH_SLIME);
        registerTmRecipe(consumer, TM_150, ROLYCOLY_COAL, ROCKRUFF_ROCK, KLAWF_CLAW);
        registerTmRecipe(consumer, TM_151, SINISTEA_CHIP, SHUPPET_SCRAP, GREAVARD_WAX);
        registerTmRecipe(consumer, TM_152, TAUROS_HAIR, ZANGOOSE_CLAW, SLAKOTH_FUR);
        registerTmRecipe(consumer, TM_153, HOUNDOUR_FANG, CHARCADET_SOOT, GROWLITHE_FUR);
        registerTmRecipe(consumer, TM_154, QWILFISH_SPINES, DONDOZO_WHISKER, LUVDISC_SCALES);
        registerTmRecipe(consumer, TM_155, TROPIUS_LEAF, SKIDDO_LEAF, CACNEA_NEEDLE);
        registerTmRecipe(consumer, TM_156, AXEW_SCALES, DRATINI_SCALES, FRIGIBAX_SCALES);
        registerTmRecipe(consumer, TM_157, LITLEO_TUFT, NUMEL_LAVA, CAPSAKID_SEED);
        registerTmRecipe(consumer, TM_158, FLAMIGO_DOWN, MEDITITE_SWEAT, IMPIDIMP_HAIR);
        registerTmRecipe(consumer, TM_159, BOUNSWEET_SWEAT, TROPIUS_LEAF, TOEDSCOOL_FLAPS);
        registerTmRecipe(consumer, TM_160, SWABLU_FLUFF, ORICORIO_FEATHER, WINGULL_FEATHER);
        registerTmRecipe(consumer, TM_161, HATENNA_DUST, BRONZOR_FRAGMENT, GOTHITA_EYELASH);
        registerTmRecipe(consumer, TM_162, KRICKETOT_SHELL, COMBEE_HONEY, VENONAT_FANG);
        registerTmRecipe(consumer, TM_163, DRATINI_SCALES, GOOMY_GOO, TAUROS_HAIR);
        registerTmRecipe(consumer, TM_164, STARLY_FEATHER, RUFFLET_FEATHER, ROOKIDEE_FEATHER);
        registerTmRecipe(consumer, TM_165, GROWLITHE_FUR, FLETCHLING_FEATHER, CHARCADET_SOOT);
        registerTmRecipe(consumer, TM_166, DEDENNE_FUR, PICHU_FUR, TYNAMO_SLIME);
        registerTmRecipe(consumer, TM_167, RIOLU_FUR, CRABRAWLER_SHELL, MAKUHITA_SWEAT);
        registerTmRecipe(consumer, TM_168, BOUNSWEET_SWEAT, TROPIUS_LEAF, FOONGUS_SPORES);
        registerTmRecipe(consumer, TM_169, GOOMY_GOO, FRIGIBAX_SCALES, APPLIN_JUICE);
        registerTmRecipe(consumer, TM_170, MAGNEMITE_SCREW, ORTHWORM_TARNISH, CUFANT_TARNISH);
        registerTmRecipe(consumer, TM_171, GLIMMET_CRYSTAL);

    }

    public void registerTmRecipe(Consumer<FinishedRecipe> consumer, RegistrySupplier<? extends ItemLike> RegistrySupplier, RegistrySupplier<? extends ItemLike> first) {
        registerTmRecipe(consumer, RegistrySupplier, first, null, null);
    }

    public void registerTmRecipe(Consumer<FinishedRecipe> consumer, RegistrySupplier<? extends ItemLike> RegistrySupplier, RegistrySupplier<? extends ItemLike> first, RegistrySupplier<? extends ItemLike> second) {
        registerTmRecipe(consumer, RegistrySupplier, first, second, null);
    }

    public void registerTmRecipe(Consumer<FinishedRecipe> consumer, RegistrySupplier<? extends ItemLike> RegistrySupplier, RegistrySupplier<? extends ItemLike> first, RegistrySupplier<? extends ItemLike> second, RegistrySupplier<? extends ItemLike> third) {
        ShapelessRecipeBuilder builder = ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, RegistrySupplier.get());

        if (first != null) {
            builder.requires(first.get(), 3);
            builder.unlockedBy(getHasName(first.get()), has(first.get()));
        }

        if (second != null) {
            builder.requires(second.get(), 3);
            builder.unlockedBy(getHasName(second.get()), has(second.get()));
        }

        if (third != null) {
            builder.requires(third.get(), 3);
            builder.unlockedBy(getHasName(third.get()), has(third.get()));
        }

        builder.save(consumer);
    }
}
