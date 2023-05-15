package generations.gg.generations.core.generationscore.forge.datagen.generators.tags;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.tags.GenerationsBlockTags;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsBlocks;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsOres;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsWood;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.concurrent.CompletableFuture;

public class TagsDatagen {
    public static void init(DataGenerator generator, PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper helper) {
        GenerationsBlockTagsProvider blockProvider = new GenerationsBlockTagsProvider(output, lookupProvider, helper);
        generator.addProvider(true, blockProvider);
    }

    public static class GenerationsBlockTagsProvider extends BlockTagsProvider {

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
}
