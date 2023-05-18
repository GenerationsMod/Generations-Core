package generations.gg.generations.core.generationscore.forge.datagen.generators.ores;

import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsOres;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class OreGenDatagen {

    public static CompletableFuture<HolderLookup.Provider> onInitialize(CompletableFuture<HolderLookup.Provider> original) {
        RuleTest STONE_ORE_REPLACEABLES = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest DEEPSLATE_ORE_REPLACEABLES = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<Consumer<BootstapContext<ConfiguredFeature<?,?>>>> CONFIGURED_FEATURE = new ArrayList<>();
        List<Consumer<BootstapContext<PlacedFeature>>> PLACED_FEATURE = new ArrayList<>();
        List<Consumer<BootstapContext<BiomeModifier>>> BIOME_MODIFIER = new ArrayList<>();

        registerCommonOreGen(
                CONFIGURED_FEATURE,
                PLACED_FEATURE,
                BIOME_MODIFIER,
                GenerationsOres.ALUMINUM_ORE,
                List.of(OreConfiguration.target(STONE_ORE_REPLACEABLES, GenerationsOres.ALUMINUM_ORE.get().defaultBlockState()), OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, GenerationsOres.DEEPSLATE_ALUMINUM_ORE.get().defaultBlockState())),
                12,
                20,
                -16,
                144,
                BiomeTags.IS_OVERWORLD,
                false
        );
        registerCommonOreGen(
                CONFIGURED_FEATURE,
                PLACED_FEATURE,
                BIOME_MODIFIER,
                GenerationsOres.RUBY_ORE,
                List.of(OreConfiguration.target(STONE_ORE_REPLACEABLES, GenerationsOres.RUBY_ORE.get().defaultBlockState()), OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, GenerationsOres.DEEPSLATE_RUBY_ORE.get().defaultBlockState())),
                5,
                3,
                -48,
                16,
                BiomeTags.IS_OVERWORLD,
                false
        );
        registerCommonOreGen(
                CONFIGURED_FEATURE,
                PLACED_FEATURE,
                BIOME_MODIFIER,
                GenerationsOres.SAPPHIRE_ORE,
                List.of(OreConfiguration.target(STONE_ORE_REPLACEABLES, GenerationsOres.SAPPHIRE_ORE.get().defaultBlockState()), OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, GenerationsOres.DEEPSLATE_SAPPHIRE_ORE.get().defaultBlockState())),
                5,
                3,
                -16,
                48,
                BiomeTags.IS_OVERWORLD,
                false
        );
        registerCommonOreGen(
                CONFIGURED_FEATURE,
                PLACED_FEATURE,
                BIOME_MODIFIER,
                GenerationsOres.CRYSTAL_ORE,
                List.of(OreConfiguration.target(STONE_ORE_REPLACEABLES, GenerationsOres.CRYSTAL_ORE.get().defaultBlockState()), OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, GenerationsOres.DEEPSLATE_CRYSTAL_ORE.get().defaultBlockState())),
                5,
                3,
                -32,
                32,
                BiomeTags.IS_OVERWORLD,
                false
        );
        registerCommonOreGen(
                CONFIGURED_FEATURE,
                PLACED_FEATURE,
                BIOME_MODIFIER,
                GenerationsOres.SILICON_ORE,
                List.of(OreConfiguration.target(STONE_ORE_REPLACEABLES, GenerationsOres.SILICON_ORE.get().defaultBlockState()), OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, GenerationsOres.DEEPSLATE_SILICON_ORE.get().defaultBlockState())),
                10,
                5,
                0,
                192,
                BiomeTags.IS_OVERWORLD,
                false
        );
        registerCommonOreGen(
                CONFIGURED_FEATURE,
                PLACED_FEATURE,
                BIOME_MODIFIER,
                GenerationsOres.FOSSIL_ORE,
                List.of(OreConfiguration.target(STONE_ORE_REPLACEABLES, GenerationsOres.FOSSIL_ORE.get().defaultBlockState()), OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, GenerationsOres.DEEPSLATE_FOSSIL_ORE.get().defaultBlockState())),
                1,
                6,
                -48,
                48,
                BiomeTags.IS_OVERWORLD,
                true
        );
        registerCommonOreGen(
                CONFIGURED_FEATURE,
                PLACED_FEATURE,
                BIOME_MODIFIER,
                GenerationsOres.METEORITE_ORE,
                List.of(OreConfiguration.target(STONE_ORE_REPLACEABLES, GenerationsOres.METEORITE_ORE.get().defaultBlockState()), OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, GenerationsOres.DEEPSLATE_METEORITE_ORE.get().defaultBlockState())),
                1,
                6,
                -64,
                64,
                BiomeTags.IS_OVERWORLD,
                false
        );
        registerCommonOreGen(
                CONFIGURED_FEATURE,
                PLACED_FEATURE,
                BIOME_MODIFIER,
                GenerationsOres.Z_CRYSTAL_ORE,
                List.of(OreConfiguration.target(STONE_ORE_REPLACEABLES, GenerationsOres.Z_CRYSTAL_ORE.get().defaultBlockState()), OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, GenerationsOres.DEEPSLATE_Z_CRYSTAL_ORE.get().defaultBlockState())),
                5,
                3,
                -64,
                64,
                BiomeTags.IS_OVERWORLD,
                false
        );
        registerCommonOreGen(
                CONFIGURED_FEATURE,
                PLACED_FEATURE,
                BIOME_MODIFIER,
                GenerationsOres.MEGASTONE_ORE,
                List.of(OreConfiguration.target(STONE_ORE_REPLACEABLES, GenerationsOres.MEGASTONE_ORE.get().defaultBlockState()), OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, GenerationsOres.DEEPSLATE_MEGASTONE_ORE.get().defaultBlockState())),
                5,
                2,
                -64,
                64,
                BiomeTags.IS_OVERWORLD,
                false
        );
        registerCommonOreGen(
                CONFIGURED_FEATURE,
                PLACED_FEATURE,
                BIOME_MODIFIER,
                GenerationsOres.THUNDER_STONE_ORE,
                List.of(OreConfiguration.target(STONE_ORE_REPLACEABLES, GenerationsOres.THUNDER_STONE_ORE.get().defaultBlockState()), OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, GenerationsOres.DEEPSLATE_THUNDER_STONE_ORE.get().defaultBlockState())),
                6,
                3,
                0,
                192,
                BiomeTags.IS_MOUNTAIN,
                false
        );
        registerCommonOreGen(
                CONFIGURED_FEATURE,
                PLACED_FEATURE,
                BIOME_MODIFIER,
                GenerationsOres.LEAF_STONE_ORE,
                List.of(OreConfiguration.target(STONE_ORE_REPLACEABLES, GenerationsOres.LEAF_STONE_ORE.get().defaultBlockState()), OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, GenerationsOres.DEEPSLATE_LEAF_STONE_ORE.get().defaultBlockState())),
                6,
                3,
                -64,
                64,
                Tags.Biomes.IS_LUSH,
                false
        );
        registerCommonOreGen(
                CONFIGURED_FEATURE,
                PLACED_FEATURE,
                BIOME_MODIFIER,
                GenerationsOres.WATER_STONE_ORE,
                List.of(OreConfiguration.target(STONE_ORE_REPLACEABLES, GenerationsOres.WATER_STONE_ORE.get().defaultBlockState()), OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, GenerationsOres.DEEPSLATE_WATER_STONE_ORE.get().defaultBlockState())),
                6,
                3,
                -16,
                64,
                Tags.Biomes.IS_WATER,
                false
        );
        registerCommonOreGen(
                CONFIGURED_FEATURE,
                PLACED_FEATURE,
                BIOME_MODIFIER,
                GenerationsOres.FIRE_STONE_ORE,
                List.of(OreConfiguration.target(STONE_ORE_REPLACEABLES, GenerationsOres.FIRE_STONE_ORE.get().defaultBlockState()), OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, GenerationsOres.DEEPSLATE_FIRE_STONE_ORE.get().defaultBlockState())),
                6,
                3,
                -64,
                64,
                Tags.Biomes.IS_HOT_OVERWORLD,
                false
        );
        registerCommonOreGen(
                CONFIGURED_FEATURE,
                PLACED_FEATURE,
                BIOME_MODIFIER,
                GenerationsOres.SUN_STONE_ORE,
                List.of(OreConfiguration.target(STONE_ORE_REPLACEABLES, GenerationsOres.SUN_STONE_ORE.get().defaultBlockState()), OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, GenerationsOres.DEEPSLATE_SUN_STONE_ORE.get().defaultBlockState())),
                6,
                3,
                -64,
                64,
                Tags.Biomes.IS_HOT_OVERWORLD,
                false
        );
        registerCommonOreGen(
                CONFIGURED_FEATURE,
                PLACED_FEATURE,
                BIOME_MODIFIER,
                GenerationsOres.ICE_STONE_ORE,
                List.of(OreConfiguration.target(STONE_ORE_REPLACEABLES, GenerationsOres.ICE_STONE_ORE.get().defaultBlockState()), OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, GenerationsOres.DEEPSLATE_ICE_STONE_ORE.get().defaultBlockState())),
                6,
                3,
                0,
                128,
                Tags.Biomes.IS_COLD_OVERWORLD,
                false
        );
        registerCommonBiomeOreGen(
                CONFIGURED_FEATURE,
                PLACED_FEATURE,
                BIOME_MODIFIER,
                List.of(OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, GenerationsOres.DEEPSLATE_MOON_STONE_ORE.get().defaultBlockState())),
                List.of(Biomes.DEEP_DARK, Biomes.PLAINS)
        );
        registerCommonOreGen(
                CONFIGURED_FEATURE,
                PLACED_FEATURE,
                BIOME_MODIFIER,
                GenerationsOres.TUMBLESTONE_ORE,
                List.of(OreConfiguration.target(STONE_ORE_REPLACEABLES, GenerationsOres.TUMBLESTONE_ORE.get().defaultBlockState()), OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, GenerationsOres.DEEPSLATE_TUMBLESTONE_ORE.get().defaultBlockState())),
                8,
                6,
                -16,
                96,
                BiomeTags.IS_OVERWORLD,
                false
        );
        registerCommonOreGen(
                CONFIGURED_FEATURE,
                PLACED_FEATURE,
                BIOME_MODIFIER,
                GenerationsOres.SKY_TUMBLESTONE_ORE,
                List.of(OreConfiguration.target(STONE_ORE_REPLACEABLES, GenerationsOres.SKY_TUMBLESTONE_ORE.get().defaultBlockState()), OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, GenerationsOres.DEEPSLATE_SKY_TUMBLESTONE_ORE.get().defaultBlockState())),
                8,
                6,
                128,
                320,
                BiomeTags.IS_OVERWORLD,
                false
        );
        registerCommonOreGen(
                CONFIGURED_FEATURE,
                PLACED_FEATURE,
                BIOME_MODIFIER,
                GenerationsOres.BLACK_TUMBLESTONE_ORE,
                List.of(OreConfiguration.target(STONE_ORE_REPLACEABLES, GenerationsOres.BLACK_TUMBLESTONE_ORE.get().defaultBlockState()), OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, GenerationsOres.DEEPSLATE_BLACK_TUMBLESTONE_ORE.get().defaultBlockState())),
                8,
                6,
                -24,
                56,
                BiomeTags.IS_OVERWORLD,
                false
        );
        registerRareOreGen(
                CONFIGURED_FEATURE,
                PLACED_FEATURE,
                BIOME_MODIFIER,
                List.of(OreConfiguration.target(STONE_ORE_REPLACEABLES, GenerationsOres.RARE_TUMBLESTONE_ORE.get().defaultBlockState()), OreConfiguration.target(DEEPSLATE_ORE_REPLACEABLES, GenerationsOres.DEEPSLATE_RARE_TUMBLESTONE_ORE.get().defaultBlockState())),
                true
        );

        return null;
    }

    private static void registerCommonOreGen(List<Consumer<BootstapContext<ConfiguredFeature<?,?>>>> configuredFeatureMap, List<Consumer<BootstapContext<PlacedFeature>>> placedFeatureMap, List<Consumer<BootstapContext<BiomeModifier>>> biomeModifierMap, RegistrySupplier<DropExperienceBlock> oreBlock, List<OreConfiguration.TargetBlockState> targets, int veinSize, int veinsPerChunk, int minHeight, int maxHeight, TagKey<Biome> biomeTag, boolean triangle) {
        ResourceLocation blockId = oreBlock.getId();
        OreConfiguration config = new OreConfiguration(targets, veinSize);

        configuredFeatureMap.add(context -> context.register(ResourceKey.create(Registries.CONFIGURED_FEATURE, blockId), new ConfiguredFeature<>(Feature.ORE, config)));
        placedFeatureMap.add(context -> {
                    Holder<ConfiguredFeature<?, ?>> configuredFeature = context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ResourceKey.create(Registries.CONFIGURED_FEATURE, blockId));
                    PlacedFeature placedOreFeature = triangle ? new PlacedFeature(configuredFeature, commonOrePlacement(veinsPerChunk, HeightRangePlacement.triangle(VerticalAnchor.absolute(minHeight), VerticalAnchor.absolute(maxHeight)))) : new PlacedFeature(configuredFeature, commonOrePlacement(veinsPerChunk, HeightRangePlacement.uniform(VerticalAnchor.absolute(minHeight), VerticalAnchor.absolute(maxHeight))));

        context.register(ResourceKey.create(Registries.PLACED_FEATURE, blockId), placedOreFeature);
            });

        biomeModifierMap.add(context -> {
            HolderSet.Named<Biome> tag = context.lookup(Registries.BIOME).getOrThrow(biomeTag);
            BiomeModifier biomeModifier = new ForgeBiomeModifiers.AddFeaturesBiomeModifier(tag, HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(ResourceKey.create(Registries.PLACED_FEATURE, blockId))), GenerationStep.Decoration.UNDERGROUND_ORES);
            context.register(ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, blockId), biomeModifier);
        });
    }

    private static void registerCommonBiomeOreGen(List<Consumer<BootstapContext<ConfiguredFeature<?,?>>>> configuredFeatureMap, List<Consumer<BootstapContext<PlacedFeature>>> placedFeatureMap, List<Consumer<BootstapContext<BiomeModifier>>> biomeModifierMap, List<OreConfiguration.TargetBlockState> targets, List<ResourceKey<Biome>> biome) {
        // Create Data
        ResourceLocation blockId = GenerationsOres.MOON_STONE_ORE.getId();
        OreConfiguration config = new OreConfiguration(targets, 6);
        configuredFeatureMap.add(context -> context.register(ResourceKey.create(Registries.CONFIGURED_FEATURE, blockId), new ConfiguredFeature<>(Feature.ORE, config)));
        placedFeatureMap.add(context -> {
            Holder<ConfiguredFeature<?, ?>> configuredFeature = context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ResourceKey.create(Registries.CONFIGURED_FEATURE, blockId));
            PlacedFeature placedOreFeature = new PlacedFeature(configuredFeature, commonOrePlacement(4, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(0))));
            context.register(ResourceKey.create(Registries.PLACED_FEATURE, blockId), placedOreFeature);
        });

        biomeModifierMap.add(context -> {
            HolderGetter<Biome> lookup = context.lookup(Registries.BIOME);
            List<Holder<Biome>> holders = new ArrayList<>();
            for (ResourceKey<Biome> biomeKey : biome) holders.add(lookup.getOrThrow(biomeKey));
            BiomeModifier biomeModifier = new ForgeBiomeModifiers.AddFeaturesBiomeModifier(HolderSet.direct(holders), HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(ResourceKey.create(Registries.PLACED_FEATURE, blockId))), GenerationStep.Decoration.UNDERGROUND_ORES);
            context.register(ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, blockId), biomeModifier);
        });
    }

    private static void registerRareOreGen(List<Consumer<BootstapContext<ConfiguredFeature<?,?>>>> configuredFeatureMap, List<Consumer<BootstapContext<PlacedFeature>>> placedFeatureMap, List<Consumer<BootstapContext<BiomeModifier>>> biomeModifierMap, List<OreConfiguration.TargetBlockState> targets, boolean triangle) {
        ResourceLocation blockId = GenerationsOres.RARE_TUMBLESTONE_ORE.getId();
        OreConfiguration config = new OreConfiguration(targets, 5);
        configuredFeatureMap.add(context -> context.register(ResourceKey.create(Registries.CONFIGURED_FEATURE, blockId), new ConfiguredFeature<>(Feature.ORE, config)));
        placedFeatureMap.add(context -> {
             Holder<ConfiguredFeature<?, ?>> configuredFeature = context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ResourceKey.create(Registries.CONFIGURED_FEATURE, blockId));
             PlacedFeature placedOreFeature = triangle ? new PlacedFeature(configuredFeature, rareOrePlacement(6, HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(14)))) : new PlacedFeature(configuredFeature, rareOrePlacement(6, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(14))));

             context.register(ResourceKey.create(Registries.PLACED_FEATURE, blockId), placedOreFeature);
        });

        biomeModifierMap.add(context -> {
            HolderSet.Named<Biome> tag = context.lookup(Registries.BIOME).getOrThrow(BiomeTags.IS_OVERWORLD);
                BiomeModifier biomeModifier = new ForgeBiomeModifiers.AddFeaturesBiomeModifier(tag, HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(ResourceKey.create(Registries.PLACED_FEATURE, blockId))), GenerationStep.Decoration.UNDERGROUND_ORES);
                context.register(ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, blockId), biomeModifier);
            });
    }

    //Vanilla Placement methods
    private static List<PlacementModifier> orePlacement(PlacementModifier arg, PlacementModifier arg2) {return List.of(arg, InSquarePlacement.spread(), arg2, BiomeFilter.biome());}

    public static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier heightRange) {return orePlacement(CountPlacement.of(count), heightRange);}

    private static List<PlacementModifier> rareOrePlacement(int chance, PlacementModifier heightRange) {return orePlacement(RarityFilter.onAverageOnceEvery(chance), heightRange);}
}

