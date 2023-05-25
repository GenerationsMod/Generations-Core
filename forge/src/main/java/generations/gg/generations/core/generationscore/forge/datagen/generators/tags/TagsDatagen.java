package generations.gg.generations.core.generationscore.forge.datagen.generators.tags;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.tags.GenerationsBlockTags;
import generations.gg.generations.core.generationscore.tags.GenerationsItemTags;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
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
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

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
                if (object instanceof FenceBlock) this.tag(BlockTags.FENCES).add(block);
                else if (object instanceof FenceGateBlock) this.tag(BlockTags.FENCE_GATES).add(block);
                else if (object instanceof DoorBlock) this.tag(BlockTags.DOORS).add(block);
                else if (object instanceof SandBlock) {
                    this.tag(BlockTags.SAND).add(block);
                    this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(block);
                }

                Field material = ObfuscationReflectionHelper.findField(BlockBehaviour.class, "material");
                material.setAccessible(true);
                try {
                    if (material.get(block)  == Material.STONE)
                        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(block);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            });

            GenerationsBlocks.ULTRA_BLOCKS.forEach(block -> {
                this.tag(GenerationsBlockTags.ULTRA).add(block.get());
                EasyBlockTags(block.get());
            });

            GenerationsBlocks.MARBLE.forEach(block -> {
                this.tag(GenerationsBlockTags.MARBLE).add(block.get());
                EasyBlockTags(block.get());
            });

            GenerationsBlocks.POKEBRICKS.forEach(block -> {
                this.tag(GenerationsBlockTags.POKEBRICKS).add(block.get());
                EasyBlockTags(block.get());
            });

            this.tag(GenerationsBlockTags.POKEBALL_CHESTS)
                    .add(
                            GenerationsBlocks.POKEBALL_CHEST.get(),
                            GenerationsBlocks.GREATBALL_CHEST.get(),
                            GenerationsBlocks.ULTRABALL_CHEST.get(),
                            GenerationsBlocks.MASTERBALL_CHEST.get());

            GenerationsShrines.SHRINES.forEach(block -> this.tag(GenerationsBlockTags.SHRINES).add(block.get()));

            this.tag(BlockTags.GUARDED_BY_PIGLINS).addTag(GenerationsBlockTags.POKEBALL_CHESTS);
            this.tag(BlockTags.FEATURES_CANNOT_REPLACE).addTag(GenerationsBlockTags.POKEBALL_CHESTS);


            //Ore Specific tags
            this.tag(GenerationsBlockTags.ALUMINUM_ORES).add(GenerationsOres.ALUMINUM_ORE.get(), GenerationsOres.DEEPSLATE_ALUMINUM_ORE.get(), GenerationsOres.CHARGE_STONE_ALUMINUM_ORE.get());
            this.tag(GenerationsBlockTags.SAPPHIRE_ORES).add(GenerationsOres.SAPPHIRE_ORE.get(), GenerationsOres.DEEPSLATE_SAPPHIRE_ORE.get(), GenerationsOres.CHARGE_STONE_SAPPHIRE_ORE.get());
            this.tag(GenerationsBlockTags.RUBY_ORES).add(GenerationsOres.RUBY_ORE.get(), GenerationsOres.DEEPSLATE_RUBY_ORE.get(), GenerationsOres.CHARGE_STONE_RUBY_ORE.get());
            this.tag(GenerationsBlockTags.SILICON_ORES).add(GenerationsOres.SILICON_ORE.get(), GenerationsOres.DEEPSLATE_SILICON_ORE.get(), GenerationsOres.CHARGE_STONE_SILICON_ORE.get());
            this.tag(GenerationsBlockTags.Z_CRYSTAL_ORES).add(GenerationsOres.Z_CRYSTAL_ORE.get(), GenerationsOres.DEEPSLATE_Z_CRYSTAL_ORE.get(), GenerationsOres.CHARGE_STONE_Z_CRYSTAL_ORE.get());
            this.tag(GenerationsBlockTags.FOSSIL_ORES).add(GenerationsOres.FOSSIL_ORE.get(), GenerationsOres.DEEPSLATE_FOSSIL_ORE.get(), GenerationsOres.CHARGE_STONE_FOSSIL_ORE.get());
            //Vanilla Ores
            this.tag(BlockTags.COAL_ORES).add(GenerationsOres.CHARGE_STONE_COAL_ORE.get());
            this.tag(BlockTags.REDSTONE_ORES).add(GenerationsOres.CHARGE_STONE_REDSTONE_ORE.get());
            this.tag(BlockTags.IRON_ORES).add(GenerationsOres.CHARGE_STONE_IRON_ORE.get());
            this.tag(BlockTags.GOLD_ORES).add(GenerationsOres.CHARGE_STONE_GOLD_ORE.get());
            this.tag(BlockTags.COPPER_ORES).add(GenerationsOres.CHARGE_STONE_COPPER_ORE.get());
            this.tag(BlockTags.LAPIS_ORES).add(GenerationsOres.CHARGE_STONE_LAPIS_LAZULI_ORE.get());
            this.tag(BlockTags.DIAMOND_ORES).add(GenerationsOres.CHARGE_STONE_DIAMOND_ORE.get());
            this.tag(BlockTags.EMERALD_ORES).add(GenerationsOres.CHARGE_STONE_EMERALD_ORE.get());

            this.tag(GenerationsBlockTags.GENERATIONSORES)
                    .addTag(GenerationsBlockTags.ALUMINUM_ORES)
                    .addTag(GenerationsBlockTags.SAPPHIRE_ORES)
                    .addTag(GenerationsBlockTags.RUBY_ORES)
                    .addTag(GenerationsBlockTags.SILICON_ORES)
                    .addTag(GenerationsBlockTags.Z_CRYSTAL_ORES)
                    .addTag(GenerationsBlockTags.FOSSIL_ORES)
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
                this.tag(BlockTags.MINEABLE_WITH_AXE).add(woodBlock);
                if (woodBlock.getName().getString().contains("planks")) this.tag(BlockTags.PLANKS).add(woodBlock);
                else if (woodBlock instanceof TrapDoorBlock) this.tag(BlockTags.WOODEN_TRAPDOORS).add(woodBlock);
                else if (woodBlock instanceof DoorBlock) this.tag(BlockTags.WOODEN_DOORS).add(woodBlock);
                else if (woodBlock instanceof StandingSignBlock) this.tag(BlockTags.STANDING_SIGNS).add(woodBlock);
                else if (woodBlock instanceof SlabBlock) this.tag(BlockTags.WOODEN_SLABS).add(woodBlock);
                else if (woodBlock instanceof StairBlock) this.tag(BlockTags.WOODEN_STAIRS).add(woodBlock);
                else if (woodBlock instanceof FenceBlock) this.tag(BlockTags.WOODEN_FENCES).add(woodBlock);
                else if (woodBlock instanceof FenceGateBlock) this.tag(BlockTags.FENCE_GATES).add(woodBlock);
                else if (woodBlock instanceof PressurePlateBlock)
                    this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(woodBlock);
                else if (woodBlock instanceof ButtonBlock) this.tag(BlockTags.WOODEN_BUTTONS).add(woodBlock);
                else if (woodBlock instanceof CeilingHangingSignBlock)
                    this.tag(BlockTags.CEILING_HANGING_SIGNS).add(woodBlock);
                else if (woodBlock instanceof WallHangingSignBlock)
                    this.tag(BlockTags.WALL_HANGING_SIGNS).add(woodBlock);
            });

            this.tag(GenerationsBlockTags.GHOST_LOGS)
                    .add(GenerationsWood.GHOST_LOG.get(), GenerationsWood.STRIPPED_GHOST_LOG.get());

            this.tag(GenerationsBlockTags.ULTRA_DARK_LOGS)
                    .add(GenerationsWood.ULTRA_DARK_LOG.get(), GenerationsWood.STRIPPED_ULTRA_DARK_LOG.get());

            this.tag(GenerationsBlockTags.ULTRA_JUNGLE_LOGS)
                    .add(GenerationsWood.ULTRA_JUNGLE_LOG.get(), GenerationsWood.STRIPPED_ULTRA_JUNGLE_LOG.get());

            this.tag(BlockTags.LOGS).addTag(GenerationsBlockTags.GHOST_LOGS).addTag(GenerationsBlockTags.ULTRA_DARK_LOGS).addTag(GenerationsBlockTags.ULTRA_JUNGLE_LOGS);
            this.tag(BlockTags.LOGS_THAT_BURN).addTag(GenerationsBlockTags.GHOST_LOGS).addTag(GenerationsBlockTags.ULTRA_DARK_LOGS).addTag(GenerationsBlockTags.ULTRA_JUNGLE_LOGS);

            GenerationsWood.WOOD_SIGN.forEach(sign -> {
                if (sign.get() instanceof WallSignBlock)
                    this.tag(BlockTags.WALL_SIGNS).add(sign.get());
                else if (sign.get() instanceof WallHangingSignBlock)
                    this.tag(BlockTags.WALL_HANGING_SIGNS).add(sign.get());
            });



            //Charge and Volcanic Stone Brick Tags like Vanilla
            this.tag(GenerationsBlockTags.CHARGE_STONE_BRICKS)
                    .add(GenerationsBlocks.CHARGE_STONE_BRICKS.get(), GenerationsBlocks.MOSSY_CHARGE_STONE_BRICKS.get(), GenerationsBlocks.CRACKED_CHARGE_STONE_BRICKS.get(), GenerationsBlocks.CHISELED_CHARGE_STONE_BRICKS.get());

            this.tag(GenerationsBlockTags.VOLCANIC_STONE_BRICKS)
                    .add(GenerationsBlocks.VOLCANIC_STONE_BRICKS.get(), GenerationsBlocks.MOSSY_VOLCANIC_STONE_BRICKS.get(), GenerationsBlocks.CRACKED_VOLCANIC_STONE_BRICKS.get(), GenerationsBlocks.CHISELED_VOLCANIC_STONE_BRICKS.get());


            this.tag(BlockTags.MINEABLE_WITH_AXE).add(GenerationsBlocks.CURSED_PUMPKIN.get(), GenerationsBlocks.CURSED_JACK_O_LANTERN.get(), GenerationsBlocks.CURSED_CARVED_PUMPKIN.get());
            this.tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(GenerationsBlockTags.ULTRA).addTag(GenerationsBlockTags.MARBLE)
                    .addTag(GenerationsBlockTags.POKEBRICKS).addTag(GenerationsBlockTags.GENERATIONSORES).addTag(GenerationsBlockTags.POKEBALL_CHESTS);
            this.tag(BlockTags.NEEDS_IRON_TOOL).addTag(GenerationsBlockTags.GENERATIONSORES);
        }

        private void EasyBlockTags(Block object) {
            if (object instanceof SlabBlock) this.tag(BlockTags.SLABS).add(object);
            else if (object instanceof StairBlock) this.tag(BlockTags.STAIRS).add(object);
            else if (object instanceof WallBlock) this.tag(BlockTags.WALLS).add(object);
            else if (object instanceof ButtonBlock) this.tag(BlockTags.BUTTONS).add(object);
            else if (object instanceof PressurePlateBlock) this.tag(BlockTags.PRESSURE_PLATES).add(object);
        }
    }

    private static class GenerationsItemTagsProvider extends ItemTagsProvider {

        public GenerationsItemTagsProvider(PackOutput arg, CompletableFuture<HolderLookup.Provider> completableFuture, BlockTagsProvider blockTagsProvider, ExistingFileHelper existingFileHelper) {
            super(arg, completableFuture, blockTagsProvider.contentsGetter(), GenerationsCore.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.@NotNull Provider arg) {
            //Copy Block tags to item version
            this.copy(BlockTags.PLANKS, ItemTags.PLANKS);
            this.copy(BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS);
            this.copy(BlockTags.BUTTONS, ItemTags.BUTTONS);
            this.copy(BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS);
            this.copy(BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS);
            this.copy(BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS);
            this.copy(BlockTags.WOODEN_FENCES, ItemTags.WOODEN_FENCES);
            this.copy(BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES);
            //this.copy(BlockTags.DOORS, ItemTags.DOORS);
            //this.copy(BlockTags.SAND, ItemTags.SAND);
            this.copy(BlockTags.SLABS, ItemTags.SLABS);
            this.copy(BlockTags.WALLS, ItemTags.WALLS);
            this.copy(BlockTags.STAIRS, ItemTags.STAIRS);
            this.copy(BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS);
            //this.copy(BlockTags.FENCES, ItemTags.FENCES);
            //this.copy(BlockTags.DIRT, ItemTags.DIRT);
            this.copy(BlockTags.STANDING_SIGNS, ItemTags.SIGNS);
            this.tag(ItemTags.LOGS).addTag(GenerationsItemTags.ULTRA_DARK_LOGS).addTag(GenerationsItemTags.ULTRA_JUNGLE_LOGS).addTag(GenerationsItemTags.GHOST_LOGS);
            this.copy(GenerationsBlockTags.ULTRA_DARK_LOGS, GenerationsItemTags.ULTRA_DARK_LOGS);
            this.copy(GenerationsBlockTags.ULTRA_JUNGLE_LOGS, GenerationsItemTags.ULTRA_JUNGLE_LOGS);
            this.copy(GenerationsBlockTags.GHOST_LOGS, GenerationsItemTags.GHOST_LOGS);

            //PokeBricks
            this.copy(GenerationsBlockTags.POKEBRICKS, GenerationsItemTags.POKEBRICKS);
            //Marble
            this.copy(GenerationsBlockTags.MARBLE, GenerationsItemTags.MARBLE);
            //Ultra
            this.copy(GenerationsBlockTags.ULTRA, GenerationsItemTags.ULTRA);

            //Ore Specific tags like Vanilla
            this.copy(GenerationsBlockTags.GENERATIONSORES, GenerationsItemTags.GENERATIONSORES);
            this.copy(GenerationsBlockTags.ALUMINUM_ORES, GenerationsItemTags.ALUMINUM_ORES);
            this.copy(GenerationsBlockTags.SAPPHIRE_ORES, GenerationsItemTags.SAPPHIRE_ORES);
            this.copy(GenerationsBlockTags.RUBY_ORES, GenerationsItemTags.RUBY_ORES);
            this.copy(GenerationsBlockTags.SILICON_ORES, GenerationsItemTags.SILICON_ORES);
            this.copy(GenerationsBlockTags.Z_CRYSTAL_ORES, GenerationsItemTags.Z_CRYSTAL_ORES);
            this.copy(GenerationsBlockTags.FOSSIL_ORES, GenerationsItemTags.FOSSIL_ORES);
            this.tag(ItemTags.COAL_ORES).add(GenerationsOres.CHARGE_STONE_COAL_ORE.get().asItem());
            this.tag(ItemTags.IRON_ORES).add(GenerationsOres.CHARGE_STONE_IRON_ORE.get().asItem());
            this.tag(ItemTags.GOLD_ORES).add(GenerationsOres.CHARGE_STONE_GOLD_ORE.get().asItem());
            this.tag(ItemTags.COPPER_ORES).add(GenerationsOres.CHARGE_STONE_COPPER_ORE.get().asItem());
            this.tag(ItemTags.DIAMOND_ORES).add(GenerationsOres.CHARGE_STONE_DIAMOND_ORE.get().asItem());
            this.tag(ItemTags.EMERALD_ORES).add(GenerationsOres.CHARGE_STONE_EMERALD_ORE.get().asItem());
            this.tag(ItemTags.LAPIS_ORES).add(GenerationsOres.CHARGE_STONE_LAPIS_LAZULI_ORE.get().asItem());
            this.tag(ItemTags.REDSTONE_ORES).add(GenerationsOres.CHARGE_STONE_REDSTONE_ORE.get().asItem());

            //Charge and Volcanic Stone Brick Tags like Vanilla
            this.copy(GenerationsBlockTags.CHARGE_STONE_BRICKS, GenerationsItemTags.CHARGE_STONE_BRICKS);
            this.copy(GenerationsBlockTags.VOLCANIC_STONE_BRICKS, GenerationsItemTags.VOLCANIC_STONE_BRICKS);

            //Discs
            this.tag(ItemTags.MUSIC_DISCS).add(
                    GenerationsItems.MUSIC_DISC_BEAST_BALL.get(),
                    GenerationsItems.MUSIC_DISC_CHERISH_BALL.get(),
                    GenerationsItems.MUSIC_DISC_DIVE_BALL.get(),
                    GenerationsItems.MUSIC_DISC_DREAM_BALL.get(),
                    GenerationsItems.MUSIC_DISC_DUSK_BALL.get(),
                    GenerationsItems.MUSIC_DISC_FAST_BALL.get(),
                    GenerationsItems.MUSIC_DISC_FEATHER.get(),
                    GenerationsItems.MUSIC_DISC_FRIEND_BALL.get(),
                    GenerationsItems.MUSIC_DISC_GREAT_BALL.get(),
                    GenerationsItems.MUSIC_DISC_GS_BALL.get(),
                    GenerationsItems.MUSIC_DISC_HEAL_BALL.get(),
                    GenerationsItems.MUSIC_DISC_HEAVY_BALL.get(),
                    GenerationsItems.MUSIC_DISC_LEADEN_BALL.get(),
                    GenerationsItems.MUSIC_DISC_LEVEL_BALL.get(),
                    GenerationsItems.MUSIC_DISC_LOVE_BALL.get(),
                    GenerationsItems.MUSIC_DISC_LURE_BALL.get(),
                    GenerationsItems.MUSIC_DISC_LUXURY_BALL.get(),
                    GenerationsItems.MUSIC_DISC_MASTER_BALL.get(),
                    GenerationsItems.MUSIC_DISC_MOON_BALL.get(),
                    GenerationsItems.MUSIC_DISC_NEST_BALL.get(),
                    GenerationsItems.MUSIC_DISC_NET_BALL.get(),
                    GenerationsItems.MUSIC_DISC_OLD_POKEBALL_DISC.get(),
                    GenerationsItems.MUSIC_DISC_ORIGIN_BALL.get(),
                    GenerationsItems.MUSIC_DISC_PARK_BALL.get(),
                    GenerationsItems.MUSIC_DISC_POKE_BALL.get(),
                    GenerationsItems.MUSIC_DISC_PREMIER_BALL.get(),
                    GenerationsItems.MUSIC_DISC_QUICK_BALL.get(),
                    GenerationsItems.MUSIC_DISC_REPEAT_BALL.get(),
                    GenerationsItems.MUSIC_DISC_SAFARI_BALL.get(),
                    GenerationsItems.MUSIC_DISC_SPORT_BALL.get(),
                    GenerationsItems.MUSIC_DISC_STRANGE_BALL.get(),
                    GenerationsItems.MUSIC_DISC_TIMER_BALL.get(),
                    GenerationsItems.MUSIC_DISC_ULTRA_BALL.get()
            );

            this.tag(GenerationsItemTags.WALKMONS).add(
                    GenerationsItems.POKE_WALKMON.get(),
                    GenerationsItems.GREAT_WALKMON.get(),
                    GenerationsItems.ULTRA_WALKMON.get(),
                    GenerationsItems.MASTER_WALKMON.get()
            );

            //GenerationsItems.ITEMS.forEach(item -> this.tag(GenerationsItemTags.GENERATIONSITEMS).add(item.get()));
        }
    }

    private static class GenerationsPaintingTagProvider extends PaintingVariantTagsProvider {

        public GenerationsPaintingTagProvider(PackOutput arg, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable ExistingFileHelper existingFileHelper) {
            super(arg, completableFuture, GenerationsCore.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.@NotNull Provider arg) {
            GenerationsPaintings.PAINTINGS.forEach(painting -> this.tag(PaintingVariantTags.PLACEABLE).add(TagEntry.element(Objects.requireNonNull(ForgeRegistries.PAINTING_VARIANTS.getKey(painting.get())))));
        }
    }
}
