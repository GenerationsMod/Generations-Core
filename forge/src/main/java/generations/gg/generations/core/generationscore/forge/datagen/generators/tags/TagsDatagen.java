package generations.gg.generations.core.generationscore.forge.datagen.generators.tags;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.tags.GenerationsBlockTags;
import generations.gg.generations.core.generationscore.tags.GenerationsItemTags;
import generations.gg.generations.core.generationscore.world.item.GenerationsTools;
import generations.gg.generations.core.generationscore.world.item.tools.GenerationsHammerItem;
import generations.gg.generations.core.generationscore.world.level.block.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.PaintingVariantTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraft.tags.TagEntry;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static generations.gg.generations.core.generationscore.world.item.GenerationsItems.*;

public class TagsDatagen {
    public static void init(DataGenerator generator, PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper helper) {
        GenerationsBlockTagsProvider blockProvider = new GenerationsBlockTagsProvider(output, lookupProvider, helper);
        generator.addProvider(true, blockProvider);
        generator.addProvider(true, new GenerationsItemTagsProvider(output, lookupProvider, blockProvider, helper));
        generator.addProvider(true, new GenerationsPaintingTagProvider(output, lookupProvider, helper));
    }

    private static class GenerationsBlockTagsProvider extends BlockTagsProvider {

        public GenerationsBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, lookupProvider, GenerationsCore.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.@NotNull Provider arg) {

            GenerationsBlocks.BLOCKS.forEach(object -> {
                Block block = object.get();
                EasyBlockTags(block);
                if (object instanceof FenceBlock) tag(BlockTags.FENCES).add(block);
                else if (object instanceof FenceGateBlock) tag(BlockTags.FENCE_GATES).add(block);
                else if (object instanceof DoorBlock) tag(BlockTags.DOORS).add(block);
                else if (object instanceof SandBlock) {
                    tag(BlockTags.SAND).add(block);
                    tag(BlockTags.MINEABLE_WITH_SHOVEL).add(block);
                }

                Field material = ObfuscationReflectionHelper.findField(BlockBehaviour.class, "material");
                material.setAccessible(true);
                try {
                    if (material.get(block) == Material.STONE)
                        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(block);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            });

            GenerationsBlocks.ULTRA_BLOCKS.forEach(block -> {
                tag(GenerationsBlockTags.ULTRA).add(block.get());
                EasyBlockTags(block.get());
            });

            GenerationsBlocks.MARBLE.forEach(block -> {
                tag(GenerationsBlockTags.MARBLE).add(block.get());
                EasyBlockTags(block.get());
            });

            GenerationsBlocks.POKEBRICKS.forEach(block -> {
                tag(GenerationsBlockTags.POKEBRICKS).add(block.get());
                EasyBlockTags(block.get());
            });

            GenerationsBlocks.STONE.forEach(block -> {
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(block.get());
                EasyBlockTags(block.get());
                if (block.get() instanceof PressurePlateBlock) tag(BlockTags.STONE_PRESSURE_PLATES).add(block.get());
            });

            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                    GenerationsBlocks.RAW_ALUMINUM_BLOCK.get(),
                    GenerationsBlocks.ALUMINUM_BLOCK.get(),
                    GenerationsUtilityBlocks.CHARGE_STONE_FURNACE.get(),
                    GenerationsUtilityBlocks.CHARGE_STONE_BLAST_FURNACE.get(),
                    GenerationsUtilityBlocks.CHARGE_STONE_SMOKER.get(),
                    GenerationsUtilityBlocks.VOLCANIC_STONE_FURNACE.get(),
                    GenerationsUtilityBlocks.VOLCANIC_STONE_BLAST_FURNACE.get(),
                    GenerationsUtilityBlocks.VOLCANIC_STONE_SMOKER.get()
            );

            tag(BlockTags.DIRT).add(
                    GenerationsBlocks.POKE_DIRT.get(),
                    GenerationsBlocks.ULTRA_SAND.get(),
                    GenerationsBlocks.RICH_SOIL_1.get(),
                    GenerationsBlocks.RICH_SOIL_2.get(),
                    GenerationsBlocks.RICH_SOIL_3.get(),
                    GenerationsBlocks.RICH_SOIL_4.get()
            );
            
            tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
                    GenerationsBlocks.POKE_GRASS.get(),
                    GenerationsBlocks.POKE_DIRT.get(),
                    GenerationsBlocks.SANDY_GRASS.get(),
                    GenerationsBlocks.RUINS_SAND.get(),
                    GenerationsBlocks.RICH_SOIL_1.get(),
                    GenerationsBlocks.RICH_SOIL_2.get(),
                    GenerationsBlocks.RICH_SOIL_3.get(),
                    GenerationsBlocks.RICH_SOIL_4.get()
            );

            tag(BlockTags.NEEDS_DIAMOND_TOOL).add(
                    GenerationsBlocks.ENCHANTED_OBSIDIAN.get(),
                    GenerationsBlocks.ENCHANTED_OBSIDIAN_STAIRS.get(),
                    GenerationsBlocks.ENCHANTED_OBSIDIAN_SLAB.get(),
                    GenerationsBlocks.ENCHANTED_OBSIDIAN_WALL.get()
            );


            tag(GenerationsBlockTags.POKEBALL_CHESTS)
                    .add(
                            GenerationsBlocks.POKEBALL_CHEST.get(),
                            GenerationsBlocks.GREATBALL_CHEST.get(),
                            GenerationsBlocks.ULTRABALL_CHEST.get(),
                            GenerationsBlocks.MASTERBALL_CHEST.get());
            tag(Tags.Blocks.CHESTS).addTag(GenerationsBlockTags.POKEBALL_CHESTS);

            GenerationsShrines.SHRINES.forEach(block -> tag(GenerationsBlockTags.SHRINES).add(block.get()));

            tag(BlockTags.GUARDED_BY_PIGLINS).addTag(GenerationsBlockTags.POKEBALL_CHESTS);
            tag(BlockTags.FEATURES_CANNOT_REPLACE).addTag(GenerationsBlockTags.POKEBALL_CHESTS);

            GenerationsDecorationBlocks.BALL_DISPLAY_BLOCKS.forEach(block -> tag(GenerationsBlockTags.BALL_DISPLAY_BLOCKS).add(block.get()));


            //Ore Specific tags
            tag(GenerationsBlockTags.ALUMINUM_ORES).add(GenerationsOres.ALUMINUM_ORE.get(), GenerationsOres.DEEPSLATE_ALUMINUM_ORE.get(), GenerationsOres.CHARGE_STONE_ALUMINUM_ORE.get());
            tag(GenerationsBlockTags.SAPPHIRE_ORES).add(GenerationsOres.SAPPHIRE_ORE.get(), GenerationsOres.DEEPSLATE_SAPPHIRE_ORE.get(), GenerationsOres.CHARGE_STONE_SAPPHIRE_ORE.get());
            tag(GenerationsBlockTags.RUBY_ORES).add(GenerationsOres.RUBY_ORE.get(), GenerationsOres.DEEPSLATE_RUBY_ORE.get(), GenerationsOres.CHARGE_STONE_RUBY_ORE.get());
            tag(GenerationsBlockTags.CRYSTAL_ORES).add(GenerationsOres.CRYSTAL_ORE.get(), GenerationsOres.DEEPSLATE_CRYSTAL_ORE.get(), GenerationsOres.CHARGE_STONE_CRYSTAL_ORE.get());
            tag(GenerationsBlockTags.SILICON_ORES).add(GenerationsOres.SILICON_ORE.get(), GenerationsOres.DEEPSLATE_SILICON_ORE.get(), GenerationsOres.CHARGE_STONE_SILICON_ORE.get());
            tag(GenerationsBlockTags.Z_CRYSTAL_ORES).add(GenerationsOres.Z_CRYSTAL_ORE.get(), GenerationsOres.DEEPSLATE_Z_CRYSTAL_ORE.get(), GenerationsOres.CHARGE_STONE_Z_CRYSTAL_ORE.get());
            tag(GenerationsBlockTags.FOSSIL_ORES).add(GenerationsOres.FOSSIL_ORE.get(), GenerationsOres.DEEPSLATE_FOSSIL_ORE.get(), GenerationsOres.CHARGE_STONE_FOSSIL_ORE.get());
            tag(GenerationsBlockTags.DAWN_STONE_ORES).add(GenerationsOres.DAWN_STONE_ORE.get(), GenerationsOres.DEEPSLATE_DAWN_STONE_ORE.get(), GenerationsOres.CHARGE_STONE_DAWN_STONE_ORE.get());
            tag(GenerationsBlockTags.DUSK_STONE_ORES).add(GenerationsOres.DUSK_STONE_ORE.get(), GenerationsOres.DEEPSLATE_DUSK_STONE_ORE.get(), GenerationsOres.CHARGE_STONE_DUSK_STONE_ORE.get());
            tag(GenerationsBlockTags.FIRE_STONE_ORES).add(GenerationsOres.FIRE_STONE_ORE.get(), GenerationsOres.DEEPSLATE_FIRE_STONE_ORE.get(), GenerationsOres.CHARGE_STONE_FIRE_STONE_ORE.get());
            tag(GenerationsBlockTags.ICE_STONE_ORES).add(GenerationsOres.ICE_STONE_ORE.get(), GenerationsOres.DEEPSLATE_ICE_STONE_ORE.get(), GenerationsOres.CHARGE_STONE_ICE_STONE_ORE.get());
            tag(GenerationsBlockTags.LEAF_STONE_ORES).add(GenerationsOres.LEAF_STONE_ORE.get(), GenerationsOres.DEEPSLATE_LEAF_STONE_ORE.get(), GenerationsOres.CHARGE_STONE_LEAF_STONE_ORE.get());
            tag(GenerationsBlockTags.SHINY_STONE_ORES).add(GenerationsOres.SHINY_STONE_ORE.get(), GenerationsOres.DEEPSLATE_SHINY_STONE_ORE.get(), GenerationsOres.CHARGE_STONE_SHINY_STONE_ORE.get());
            tag(GenerationsBlockTags.SUN_STONE_ORES).add(GenerationsOres.SUN_STONE_ORE.get(), GenerationsOres.DEEPSLATE_SUN_STONE_ORE.get(), GenerationsOres.CHARGE_STONE_SUN_STONE_ORE.get());
            tag(GenerationsBlockTags.THUNDER_STONE_ORES).add(GenerationsOres.THUNDER_STONE_ORE.get(), GenerationsOres.DEEPSLATE_THUNDER_STONE_ORE.get(), GenerationsOres.CHARGE_STONE_THUNDER_STONE_ORE.get());
            tag(GenerationsBlockTags.WATER_STONE_ORES).add(GenerationsOres.WATER_STONE_ORE.get(), GenerationsOres.DEEPSLATE_WATER_STONE_ORE.get(), GenerationsOres.CHARGE_STONE_WATER_STONE_ORE.get());
            tag(GenerationsBlockTags.MOON_STONE_ORES).add(GenerationsOres.MOON_STONE_ORE.get(), GenerationsOres.DEEPSLATE_MOON_STONE_ORE.get(), GenerationsOres.CHARGE_STONE_MOON_STONE_ORE.get());
            tag(GenerationsBlockTags.TUMBLESTONE_ORES).add(GenerationsOres.TUMBLESTONE_ORE.get(), GenerationsOres.DEEPSLATE_TUMBLESTONE_ORE.get(), GenerationsOres.CHARGE_STONE_TUMBLESTONE_ORE.get());
            tag(GenerationsBlockTags.RARE_TUMBLESTONE_ORES).add(GenerationsOres.RARE_TUMBLESTONE_ORE.get(), GenerationsOres.DEEPSLATE_RARE_TUMBLESTONE_ORE.get(), GenerationsOres.CHARGE_STONE_RARE_TUMBLESTONE_ORE.get());
            tag(GenerationsBlockTags.SKY_TUMBLESTONE_ORES).add(GenerationsOres.SKY_TUMBLESTONE_ORE.get(), GenerationsOres.DEEPSLATE_SKY_TUMBLESTONE_ORE.get(), GenerationsOres.CHARGE_STONE_SKY_TUMBLESTONE_ORE.get());
            tag(GenerationsBlockTags.BLACK_TUMBLESTONE_ORES).add(GenerationsOres.BLACK_TUMBLESTONE_ORE.get(), GenerationsOres.DEEPSLATE_BLACK_TUMBLESTONE_ORE.get(), GenerationsOres.CHARGE_STONE_BLACK_TUMBLESTONE_ORE.get());
            tag(GenerationsBlockTags.MEGASTONE_ORES).add(GenerationsOres.MEGASTONE_ORE.get(), GenerationsOres.DEEPSLATE_MEGASTONE_ORE.get(), GenerationsOres.CHARGE_STONE_MEGASTONE_ORE.get());
            tag(GenerationsBlockTags.METEORITE_ORES).add(GenerationsOres.METEORITE_ORE.get(), GenerationsOres.DEEPSLATE_METEORITE_ORE.get(), GenerationsOres.CHARGE_STONE_METEORITE_ORE.get());
            //Vanilla Ores
            tag(BlockTags.COAL_ORES).add(GenerationsOres.CHARGE_STONE_COAL_ORE.get());
            tag(Tags.Blocks.ORES_COAL).add(GenerationsOres.CHARGE_STONE_COAL_ORE.get());
            tag(BlockTags.REDSTONE_ORES).add(GenerationsOres.CHARGE_STONE_REDSTONE_ORE.get());
            tag(Tags.Blocks.ORES_REDSTONE).add(GenerationsOres.CHARGE_STONE_REDSTONE_ORE.get());
            tag(BlockTags.IRON_ORES).add(GenerationsOres.CHARGE_STONE_IRON_ORE.get());
            tag(Tags.Blocks.ORES_IRON).add(GenerationsOres.CHARGE_STONE_IRON_ORE.get());
            tag(BlockTags.GOLD_ORES).add(GenerationsOres.CHARGE_STONE_GOLD_ORE.get());
            tag(Tags.Blocks.ORES_GOLD).add(GenerationsOres.CHARGE_STONE_GOLD_ORE.get());
            tag(BlockTags.COPPER_ORES).add(GenerationsOres.CHARGE_STONE_COPPER_ORE.get());
            tag(Tags.Blocks.ORES_COPPER).add(GenerationsOres.CHARGE_STONE_COPPER_ORE.get());
            tag(BlockTags.LAPIS_ORES).add(GenerationsOres.CHARGE_STONE_LAPIS_LAZULI_ORE.get());
            tag(Tags.Blocks.ORES_LAPIS).add(GenerationsOres.CHARGE_STONE_LAPIS_LAZULI_ORE.get());
            tag(BlockTags.DIAMOND_ORES).add(GenerationsOres.CHARGE_STONE_DIAMOND_ORE.get());
            tag(Tags.Blocks.ORES_DIAMOND).add(GenerationsOres.CHARGE_STONE_DIAMOND_ORE.get());
            tag(BlockTags.EMERALD_ORES).add(GenerationsOres.CHARGE_STONE_EMERALD_ORE.get());
            tag(Tags.Blocks.ORES_EMERALD).add(GenerationsOres.CHARGE_STONE_EMERALD_ORE.get());

            tag(GenerationsBlockTags.GENERATIONSORES)
                    .addTag(GenerationsBlockTags.ALUMINUM_ORES)
                    .addTag(GenerationsBlockTags.SAPPHIRE_ORES)
                    .addTag(GenerationsBlockTags.RUBY_ORES)
                    .addTag(GenerationsBlockTags.CRYSTAL_ORES)
                    .addTag(GenerationsBlockTags.SILICON_ORES)
                    .addTag(GenerationsBlockTags.Z_CRYSTAL_ORES)
                    .addTag(GenerationsBlockTags.FOSSIL_ORES)
                    .addTag(GenerationsBlockTags.DAWN_STONE_ORES)
                    .addTag(GenerationsBlockTags.DUSK_STONE_ORES)
                    .addTag(GenerationsBlockTags.FIRE_STONE_ORES)
                    .addTag(GenerationsBlockTags.ICE_STONE_ORES)
                    .addTag(GenerationsBlockTags.LEAF_STONE_ORES)
                    .addTag(GenerationsBlockTags.SHINY_STONE_ORES)
                    .addTag(GenerationsBlockTags.SUN_STONE_ORES)
                    .addTag(GenerationsBlockTags.THUNDER_STONE_ORES)
                    .addTag(GenerationsBlockTags.WATER_STONE_ORES)
                    .addTag(GenerationsBlockTags.MOON_STONE_ORES)
                    .addTag(GenerationsBlockTags.TUMBLESTONE_ORES)
                    .addTag(GenerationsBlockTags.RARE_TUMBLESTONE_ORES)
                    .addTag(GenerationsBlockTags.SKY_TUMBLESTONE_ORES)
                    .addTag(GenerationsBlockTags.BLACK_TUMBLESTONE_ORES)
                    .addTag(GenerationsBlockTags.MEGASTONE_ORES)
                    .addTag(GenerationsBlockTags.METEORITE_ORES)
                    .add(GenerationsOres.CHARGE_STONE_COAL_ORE.get())
                    .add(GenerationsOres.CHARGE_STONE_REDSTONE_ORE.get())
                    .add(GenerationsOres.CHARGE_STONE_IRON_ORE.get())
                    .add(GenerationsOres.CHARGE_STONE_GOLD_ORE.get())
                    .add(GenerationsOres.CHARGE_STONE_COPPER_ORE.get())
                    .add(GenerationsOres.CHARGE_STONE_LAPIS_LAZULI_ORE.get())
                    .add(GenerationsOres.CHARGE_STONE_DIAMOND_ORE.get())
                    .add(GenerationsOres.CHARGE_STONE_EMERALD_ORE.get());



            GenerationsWood.WOOD_BLOCKS.forEach(block -> {
                Block woodBlock = block.get();
                tag(BlockTags.MINEABLE_WITH_AXE).add(woodBlock);
                if (woodBlock.getName().getString().contains("planks")) tag(BlockTags.PLANKS).add(woodBlock);
                else if (woodBlock instanceof TrapDoorBlock) tag(BlockTags.WOODEN_TRAPDOORS).add(woodBlock);
                else if (woodBlock instanceof DoorBlock) tag(BlockTags.WOODEN_DOORS).add(woodBlock);
                else if (woodBlock instanceof StandingSignBlock) tag(BlockTags.STANDING_SIGNS).add(woodBlock);
                else if (woodBlock instanceof SlabBlock) tag(BlockTags.WOODEN_SLABS).add(woodBlock);
                else if (woodBlock instanceof StairBlock) tag(BlockTags.WOODEN_STAIRS).add(woodBlock);
                else if (woodBlock instanceof FenceBlock) tag(BlockTags.WOODEN_FENCES).add(woodBlock);
                else if (woodBlock instanceof FenceGateBlock) tag(BlockTags.FENCE_GATES).add(woodBlock);
                else if (woodBlock instanceof PressurePlateBlock)
                    tag(BlockTags.WOODEN_PRESSURE_PLATES).add(woodBlock);
                else if (woodBlock instanceof ButtonBlock) tag(BlockTags.WOODEN_BUTTONS).add(woodBlock);
                else if (woodBlock instanceof CeilingHangingSignBlock)
                    tag(BlockTags.CEILING_HANGING_SIGNS).add(woodBlock);
                else if (woodBlock instanceof WallHangingSignBlock)
                    tag(BlockTags.WALL_HANGING_SIGNS).add(woodBlock);
            });

            tag(GenerationsBlockTags.GHOST_LOGS)
                    .add(GenerationsWood.GHOST_LOG.get(), GenerationsWood.STRIPPED_GHOST_LOG.get());

            tag(GenerationsBlockTags.ULTRA_DARK_LOGS)
                    .add(GenerationsWood.ULTRA_DARK_LOG.get(), GenerationsWood.STRIPPED_ULTRA_DARK_LOG.get());

            tag(GenerationsBlockTags.ULTRA_JUNGLE_LOGS)
                    .add(GenerationsWood.ULTRA_JUNGLE_LOG.get(), GenerationsWood.STRIPPED_ULTRA_JUNGLE_LOG.get());

            tag(BlockTags.LOGS).addTag(GenerationsBlockTags.GHOST_LOGS).addTag(GenerationsBlockTags.ULTRA_DARK_LOGS).addTag(GenerationsBlockTags.ULTRA_JUNGLE_LOGS);
            tag(BlockTags.LOGS_THAT_BURN).addTag(GenerationsBlockTags.GHOST_LOGS).addTag(GenerationsBlockTags.ULTRA_DARK_LOGS).addTag(GenerationsBlockTags.ULTRA_JUNGLE_LOGS);

            GenerationsWood.WOOD_SIGN.forEach(sign -> {
                if (sign.get() instanceof WallSignBlock)
                    tag(BlockTags.WALL_SIGNS).add(sign.get());
                else if (sign.get() instanceof WallHangingSignBlock)
                    tag(BlockTags.WALL_HANGING_SIGNS).add(sign.get());
            });

            //Charge and Volcanic Stone Brick Tags like Vanilla
            tag(GenerationsBlockTags.CHARGE_STONE_BRICKS)
                    .add(GenerationsBlocks.CHARGE_STONE_BRICKS.get(), GenerationsBlocks.MOSSY_CHARGE_STONE_BRICKS.get(), GenerationsBlocks.CRACKED_CHARGE_STONE_BRICKS.get(), GenerationsBlocks.CHISELED_CHARGE_STONE_BRICKS.get());

            tag(GenerationsBlockTags.VOLCANIC_STONE_BRICKS)
                    .add(GenerationsBlocks.VOLCANIC_STONE_BRICKS.get(), GenerationsBlocks.MOSSY_VOLCANIC_STONE_BRICKS.get(), GenerationsBlocks.CRACKED_VOLCANIC_STONE_BRICKS.get(), GenerationsBlocks.CHISELED_VOLCANIC_STONE_BRICKS.get());

            tag(BlockTags.MINEABLE_WITH_AXE).add(GenerationsBlocks.CURSED_PUMPKIN.get(), GenerationsBlocks.CURSED_JACK_O_LANTERN.get(), GenerationsBlocks.CURSED_CARVED_PUMPKIN.get());
            tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(GenerationsBlockTags.ULTRA).addTag(GenerationsBlockTags.MARBLE)
                    .addTag(GenerationsBlockTags.POKEBRICKS).addTag(GenerationsBlockTags.GENERATIONSORES).addTag(GenerationsBlockTags.POKEBALL_CHESTS)
                    .addTag(GenerationsBlockTags.BALL_DISPLAY_BLOCKS);
            tag(BlockTags.NEEDS_IRON_TOOL).addTag(GenerationsBlockTags.GENERATIONSORES);
            tag(Tags.Blocks.ORES).addTag(GenerationsBlockTags.GENERATIONSORES);
            tag(BlockTags.NEEDS_STONE_TOOL).add(
                    GenerationsBlocks.RAW_ALUMINUM_BLOCK.get(),
                    GenerationsBlocks.ALUMINUM_BLOCK.get(),
                    GenerationsBlocks.SAPPHIRE_BLOCK.get(),
                    GenerationsBlocks.SAPPHIRE_SLAB.get(),
                    GenerationsBlocks.SAPPHIRE_STAIRS.get(),
                    GenerationsBlocks.SAPPHIRE_WALL.get(),
                    GenerationsBlocks.RUBY_BLOCK.get(),
                    GenerationsBlocks.RUBY_SLAB.get(),
                    GenerationsBlocks.RUBY_STAIRS.get(),
                    GenerationsBlocks.RUBY_WALL.get(),
                    GenerationsBlocks.CRYSTAL_BLOCK.get(),
                    GenerationsBlocks.CRYSTAL_SLAB.get(),
                    GenerationsBlocks.CRYSTAL_STAIRS.get(),
                    GenerationsBlocks.CRYSTAL_WALL.get(),
                    GenerationsBlocks.SILICON_BLOCK.get())
                    .addTag(GenerationsBlockTags.BALL_DISPLAY_BLOCKS);

            tag(BlockTags.SNOW_LAYER_CANNOT_SURVIVE_ON).add(GenerationsBlocks.POKECENTER_SCARLET_SIGN.get());
        }

        private void EasyBlockTags(Block object) {
            if (object instanceof SlabBlock) tag(BlockTags.SLABS).add(object);
            else if (object instanceof StairBlock) tag(BlockTags.STAIRS).add(object);
            else if (object instanceof WallBlock) tag(BlockTags.WALLS).add(object);
            else if (object instanceof ButtonBlock) tag(BlockTags.BUTTONS).add(object);
            else if (object instanceof PressurePlateBlock) tag(BlockTags.PRESSURE_PLATES).add(object);
        }
    }

    private static class GenerationsItemTagsProvider extends ItemTagsProvider {

        public GenerationsItemTagsProvider(PackOutput arg, CompletableFuture<HolderLookup.Provider> completableFuture, BlockTagsProvider blockTagsProvider, ExistingFileHelper existingFileHelper) {
            super(arg, completableFuture, blockTagsProvider.contentsGetter(), GenerationsCore.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.@NotNull Provider arg) {
            //Copy Block tags to item version
            copy(BlockTags.PLANKS, ItemTags.PLANKS);
            copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
            copy(BlockTags.BUTTONS, ItemTags.BUTTONS);
            copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
            copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
            copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
            copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
            copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
            //copy(BlockTags.DOORS, ItemTags.DOORS);
            //copy(BlockTags.SAND, ItemTags.SAND);
            copy(BlockTags.SLABS, ItemTags.SLABS);
            copy(BlockTags.WALLS, ItemTags.WALLS);
            copy(BlockTags.STAIRS, ItemTags.STAIRS);
            copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);
            //copy(BlockTags.FENCES, ItemTags.FENCES);
            copy(BlockTags.DIRT, ItemTags.DIRT);
            copy(BlockTags.STANDING_SIGNS, ItemTags.SIGNS);
            tag(ItemTags.LOGS).addTag(GenerationsItemTags.ULTRA_DARK_LOGS).addTag(GenerationsItemTags.ULTRA_JUNGLE_LOGS).addTag(GenerationsItemTags.GHOST_LOGS);
            tag(ItemTags.LOGS_THAT_BURN).addTag(GenerationsItemTags.ULTRA_DARK_LOGS).addTag(GenerationsItemTags.ULTRA_JUNGLE_LOGS).addTag(GenerationsItemTags.GHOST_LOGS);
            copy(GenerationsBlockTags.ULTRA_DARK_LOGS, GenerationsItemTags.ULTRA_DARK_LOGS);
            copy(GenerationsBlockTags.ULTRA_JUNGLE_LOGS, GenerationsItemTags.ULTRA_JUNGLE_LOGS);
            copy(GenerationsBlockTags.GHOST_LOGS, GenerationsItemTags.GHOST_LOGS);

            //PokeBricks
            copy(GenerationsBlockTags.POKEBRICKS, GenerationsItemTags.POKEBRICKS);
            //Marble
            copy(GenerationsBlockTags.MARBLE, GenerationsItemTags.MARBLE);
            //Ultra
            copy(GenerationsBlockTags.ULTRA, GenerationsItemTags.ULTRA);

            //Ore Specific tags like Vanilla
            copy(GenerationsBlockTags.GENERATIONSORES, GenerationsItemTags.GENERATIONSORES);
            copy(Tags.Blocks.ORES, Tags.Items.ORES);
            copy(GenerationsBlockTags.ALUMINUM_ORES, GenerationsItemTags.ALUMINUM_ORES);
            copy(GenerationsBlockTags.SAPPHIRE_ORES, GenerationsItemTags.SAPPHIRE_ORES);
            copy(GenerationsBlockTags.RUBY_ORES, GenerationsItemTags.RUBY_ORES);
            copy(GenerationsBlockTags.CRYSTAL_ORES, GenerationsItemTags.CRYSTAL_ORES);
            copy(GenerationsBlockTags.SILICON_ORES, GenerationsItemTags.SILICON_ORES);
            copy(GenerationsBlockTags.Z_CRYSTAL_ORES, GenerationsItemTags.Z_CRYSTAL_ORES);
            copy(GenerationsBlockTags.FOSSIL_ORES, GenerationsItemTags.FOSSIL_ORES);
            copy(GenerationsBlockTags.DAWN_STONE_ORES, GenerationsItemTags.DAWN_STONE_ORES);
            copy(GenerationsBlockTags.DUSK_STONE_ORES, GenerationsItemTags.DUSK_STONE_ORES);
            copy(GenerationsBlockTags.FIRE_STONE_ORES, GenerationsItemTags.FIRE_STONE_ORES);
            copy(GenerationsBlockTags.ICE_STONE_ORES, GenerationsItemTags.ICE_STONE_ORES);
            copy(GenerationsBlockTags.LEAF_STONE_ORES, GenerationsItemTags.LEAF_STONE_ORES);
            copy(GenerationsBlockTags.MOON_STONE_ORES, GenerationsItemTags.MOON_STONE_ORES);
            copy(GenerationsBlockTags.SUN_STONE_ORES, GenerationsItemTags.SUN_STONE_ORES);
            copy(GenerationsBlockTags.SHINY_STONE_ORES, GenerationsItemTags.SHINY_STONE_ORES);
            copy(GenerationsBlockTags.THUNDER_STONE_ORES, GenerationsItemTags.THUNDER_STONE_ORES);
            copy(GenerationsBlockTags.WATER_STONE_ORES, GenerationsItemTags.WATER_STONE_ORES);
            copy(GenerationsBlockTags.TUMBLESTONE_ORES, GenerationsItemTags.TUMBLESTONE_ORES);
            copy(GenerationsBlockTags.RARE_TUMBLESTONE_ORES, GenerationsItemTags.RARE_TUMBLESTONE_ORES);
            copy(GenerationsBlockTags.SKY_TUMBLESTONE_ORES, GenerationsItemTags.SKY_TUMBLESTONE_ORES);
            copy(GenerationsBlockTags.BLACK_TUMBLESTONE_ORES, GenerationsItemTags.BLACK_TUMBLESTONE_ORES);
            copy(GenerationsBlockTags.MEGASTONE_ORES, GenerationsItemTags.MEGASTONE_ORES);
            copy(GenerationsBlockTags.METEORITE_ORES, GenerationsItemTags.METEORITE_ORES);
            tag(ItemTags.COAL_ORES).add(GenerationsOres.CHARGE_STONE_COAL_ORE.get().asItem());
            tag(ItemTags.IRON_ORES).add(GenerationsOres.CHARGE_STONE_IRON_ORE.get().asItem());
            tag(ItemTags.GOLD_ORES).add(GenerationsOres.CHARGE_STONE_GOLD_ORE.get().asItem());
            tag(ItemTags.COPPER_ORES).add(GenerationsOres.CHARGE_STONE_COPPER_ORE.get().asItem());
            tag(ItemTags.DIAMOND_ORES).add(GenerationsOres.CHARGE_STONE_DIAMOND_ORE.get().asItem());
            tag(ItemTags.EMERALD_ORES).add(GenerationsOres.CHARGE_STONE_EMERALD_ORE.get().asItem());
            tag(ItemTags.LAPIS_ORES).add(GenerationsOres.CHARGE_STONE_LAPIS_LAZULI_ORE.get().asItem());
            tag(ItemTags.REDSTONE_ORES).add(GenerationsOres.CHARGE_STONE_REDSTONE_ORE.get().asItem());

            copy(Tags.Blocks.ORES_COAL, Tags.Items.ORES_COAL);
            copy(Tags.Blocks.ORES_IRON, Tags.Items.ORES_IRON);
            copy(Tags.Blocks.ORES_GOLD, Tags.Items.ORES_GOLD);
            copy(Tags.Blocks.ORES_COPPER, Tags.Items.ORES_COPPER);
            copy(Tags.Blocks.ORES_DIAMOND, Tags.Items.ORES_DIAMOND);
            copy(Tags.Blocks.ORES_EMERALD, Tags.Items.ORES_EMERALD);
            copy(Tags.Blocks.ORES_LAPIS, Tags.Items.ORES_LAPIS);
            copy(Tags.Blocks.ORES_REDSTONE, Tags.Items.ORES_REDSTONE);
            //Charge and Volcanic Stone Brick Tags like Vanilla
            copy(GenerationsBlockTags.CHARGE_STONE_BRICKS, GenerationsItemTags.CHARGE_STONE_BRICKS);
            copy(GenerationsBlockTags.VOLCANIC_STONE_BRICKS, GenerationsItemTags.VOLCANIC_STONE_BRICKS);

            //Discs
            tag(ItemTags.MUSIC_DISCS).add(
                    MUSIC_DISC_BEAST_BALL.get(),
                    MUSIC_DISC_CHERISH_BALL.get(),
                    MUSIC_DISC_DIVE_BALL.get(),
                    MUSIC_DISC_DREAM_BALL.get(),
                    MUSIC_DISC_DUSK_BALL.get(),
                    MUSIC_DISC_FAST_BALL.get(),
                    MUSIC_DISC_FEATHER.get(),
                    MUSIC_DISC_FRIEND_BALL.get(),
                    MUSIC_DISC_GREAT_BALL.get(),
                    MUSIC_DISC_GS_BALL.get(),
                    MUSIC_DISC_HEAL_BALL.get(),
                    MUSIC_DISC_HEAVY_BALL.get(),
                    MUSIC_DISC_LEADEN_BALL.get(),
                    MUSIC_DISC_LEVEL_BALL.get(),
                    MUSIC_DISC_LOVE_BALL.get(),
                    MUSIC_DISC_LURE_BALL.get(),
                    MUSIC_DISC_LUXURY_BALL.get(),
                    MUSIC_DISC_MASTER_BALL.get(),
                    MUSIC_DISC_MOON_BALL.get(),
                    MUSIC_DISC_NEST_BALL.get(),
                    MUSIC_DISC_NET_BALL.get(),
                    MUSIC_DISC_OLD_POKEBALL_DISC.get(),
                    MUSIC_DISC_ORIGIN_BALL.get(),
                    MUSIC_DISC_PARK_BALL.get(),
                    MUSIC_DISC_POKE_BALL.get(),
                    MUSIC_DISC_PREMIER_BALL.get(),
                    MUSIC_DISC_QUICK_BALL.get(),
                    MUSIC_DISC_REPEAT_BALL.get(),
                    MUSIC_DISC_SAFARI_BALL.get(),
                    MUSIC_DISC_SPORT_BALL.get(),
                    MUSIC_DISC_STRANGE_BALL.get(),
                    MUSIC_DISC_TIMER_BALL.get(),
                    MUSIC_DISC_ULTRA_BALL.get()
            );

            tag(GenerationsItemTags.WALKMONS).add(
                    POKE_WALKMON.get(),
                    GREAT_WALKMON.get(),
                    ULTRA_WALKMON.get(),
                    MASTER_WALKMON.get()
            );

            tag(ItemTags.BOATS).add(GHOST_BOAT_ITEM.get()).add(ULTRA_DARK_BOAT_ITEM.get()).add(ULTRA_JUNGLE_BOAT_ITEM.get());
            tag(ItemTags.CHEST_BOATS).add(GHOST_CHEST_BOAT_ITEM.get()).add(ULTRA_DARK_CHEST_BOAT_ITEM.get()).add(ULTRA_JUNGLE_CHEST_BOAT_ITEM.get());

            tag(ItemTags.STONE_TOOL_MATERIALS).add(GenerationsBlocks.CHARGE_COBBLESTONE.get().asItem()).add(GenerationsBlocks.VOLCANIC_COBBLESTONE.get().asItem());
            GenerationsTools.TOOLS.forEach(tool -> {
                Item item = tool.get();
                if (item instanceof PickaxeItem) tag(ItemTags.PICKAXES).add(item);
                else if (item instanceof AxeItem) tag(ItemTags.AXES).add(item);
                else if (item instanceof ShovelItem) tag(ItemTags.SHOVELS).add(item);
                else if (item instanceof HoeItem) tag(ItemTags.HOES).add(item);
                else if (item instanceof SwordItem) tag(ItemTags.SWORDS).add(item);
                else if (item instanceof GenerationsHammerItem) tag(GenerationsItemTags.HAMMERS).add(item);
            });

            tag(ItemTags.TOOLS).addTag(GenerationsItemTags.HAMMERS);
            tag(Tags.Items.TOOLS).addTag(GenerationsItemTags.HAMMERS);

            tag(Tags.Items.INGOTS).add(ALUMINUM_INGOT.get());
            tag(Tags.Items.NUGGETS).add(ALUMINUM_NUGGET.get());

            ITEMS.forEach(item -> tag(GenerationsItemTags.GENERATIONSITEMS).add(item.get()));
            POKEBALLS.forEach(pokeball -> tag(GenerationsItemTags.POKEBALLS).add(pokeball.get()));

            tag(GenerationsItemTags.POKEMAIL).add(
                    POKEMAIL_AIR.get(),
                    POKEMAIL_BLOOM.get(),
                    POKEMAIL_BRICK.get(),
                    POKEMAIL_BRIDGE_D.get(),
                    POKEMAIL_BRIDGE_M.get(),
                    POKEMAIL_BRIDGE_S.get(),
                    POKEMAIL_BRIDGE_T.get(),
                    POKEMAIL_BRIDGE_V.get(),
                    POKEMAIL_BUBBLE.get(),
                    POKEMAIL_DREAM.get(),
                    POKEMAIL_FAB.get(),
                    POKEMAIL_FAVORED.get(),
                    POKEMAIL_FLAME.get(),
                    POKEMAIL_GLITTER.get(),
                    POKEMAIL_GRASS.get(),
                    POKEMAIL_GREET.get(),
                    POKEMAIL_HARBOR.get(),
                    POKEMAIL_HEART.get(),
                    POKEMAIL_INQUIRY.get(),
                    POKEMAIL_LIKE.get(),
                    POKEMAIL_MECH.get(),
                    POKEMAIL_MOSAIC.get(),
                    POKEMAIL_ORANGE.get(),
                    POKEMAIL_REPLY.get(),
                    POKEMAIL_RETRO.get(),
                    POKEMAIL_RSVP.get(),
                    POKEMAIL_SHADOW.get(),
                    POKEMAIL_SNOW.get(),
                    POKEMAIL_SPACE.get(),
                    POKEMAIL_STEEL.get(),
                    POKEMAIL_THANKS.get(),
                    POKEMAIL_TROPIC.get(),
                    POKEMAIL_TUNNEL.get(),
                    POKEMAIL_WAVE.get(),
                    POKEMAIL_WOOD.get());

            tag(GenerationsItemTags.CLOSED_POKEMAIL).add(
                    POKEMAIL_AIR_CLOSED.get(),
                    POKEMAIL_BLOOM_CLOSED.get(),
                    POKEMAIL_BRICK_CLOSED.get(),
                    POKEMAIL_BRIDGE_D_CLOSED.get(),
                    POKEMAIL_BRIDGE_M_CLOSED.get(),
                    POKEMAIL_BRIDGE_T_CLOSED.get(),
                    POKEMAIL_BRIDGE_S_CLOSED.get(),
                    POKEMAIL_BRIDGE_V_CLOSED.get(),
                    POKEMAIL_BUBBLE_CLOSED.get(),
                    POKEMAIL_DREAM_CLOSED.get(),
                    POKEMAIL_FAB_CLOSED.get(),
                    POKEMAIL_FAVORED_CLOSED.get(),
                    POKEMAIL_FLAME_CLOSED.get(),
                    POKEMAIL_GLITTER_CLOSED.get(),
                    POKEMAIL_GRASS_CLOSED.get(),
                    POKEMAIL_GREET_CLOSED.get(),
                    POKEMAIL_HARBOR_CLOSED.get(),
                    POKEMAIL_HEART_CLOSED.get(),
                    POKEMAIL_INQUIRY_CLOSED.get(),
                    POKEMAIL_LIKE_CLOSED.get(),
                    POKEMAIL_MECH_CLOSED.get(),
                    POKEMAIL_MOSAIC_CLOSED.get(),
                    POKEMAIL_ORANGE_CLOSED.get(),
                    POKEMAIL_REPLY_CLOSED.get(),
                    POKEMAIL_RETRO_CLOSED.get(),
                    POKEMAIL_RSVP_CLOSED.get(),
                    POKEMAIL_SHADOW_CLOSED.get(),
                    POKEMAIL_SNOW_CLOSED.get(),
                    POKEMAIL_SPACE_CLOSED.get(),
                    POKEMAIL_STEEL_CLOSED.get(),
                    POKEMAIL_THANKS_CLOSED.get(),
                    POKEMAIL_TROPIC_CLOSED.get(),
                    POKEMAIL_TUNNEL_CLOSED.get(),
                    POKEMAIL_WAVE_CLOSED.get(),
                    POKEMAIL_WOOD_CLOSED.get());

            tag(GenerationsItemTags.SHEARS).add(Items.SHEARS).addOptionalTag(Tags.Items.SHEARS.location());

            //PC
            GenerationsUtilityBlocks.PC_BLOCKS.forEach(pc -> tag(GenerationsItemTags.PC).add(pc.get()));
        }
    }

    private static class GenerationsPaintingTagProvider extends PaintingVariantTagsProvider {

        public GenerationsPaintingTagProvider(PackOutput arg, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
            super(arg, completableFuture, GenerationsCore.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.@NotNull Provider arg) {
            GenerationsPaintings.PAINTINGS.forEach(painting -> tag(PaintingVariantTags.PLACEABLE).add(TagEntry.element(Objects.requireNonNull(ForgeRegistries.PAINTING_VARIANTS.getKey(painting.get())))));
        }
    }
}
