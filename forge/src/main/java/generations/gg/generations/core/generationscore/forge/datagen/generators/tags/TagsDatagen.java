package generations.gg.generations.core.generationscore.forge.datagen.generators.tags;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.tags.GenerationsBlockTags;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.*;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class TagsDatagen {
    public static void init(DataGenerator generator, PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper helper) {
        GenerationsCore.LOGGER.info("TagsDatagen init");
        GenerationsBlockTagsProvider blockProvider = new GenerationsBlockTagsProvider(output, lookupProvider, helper);
        generator.addProvider(true, blockProvider);
    }

    public static class GenerationsBlockTagsProvider extends BlockTagsProvider {

        public GenerationsBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, lookupProvider, GenerationsCore.MOD_ID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.@NotNull Provider arg) {
            GenerationsCore.LOGGER.info("addTags");
            GenerationsBlocks.ULTRA_BLOCKS.forEach(block -> {
                GenerationsCore.LOGGER.info("Doing ULtra Blocks");
                this.tag(GenerationsBlockTags.ULTRA).add(block.get());
                EasyBlockTags(block.get());
            });

            GenerationsBlocks.MARBLE.forEach(block -> {
                GenerationsCore.LOGGER.info("Marble");
                this.tag(GenerationsBlockTags.MARBLE).add(block.get());
                EasyBlockTags(block.get());
            });

            GenerationsBlocks.POKEBRICKS.forEach(block -> {
                GenerationsCore.LOGGER.info("Pokebricks");
                this.tag(GenerationsBlockTags.POKEBRICKS).add(block.get());
                EasyBlockTags(block.get());
            });

            this.tag(GenerationsBlockTags.POKEBALL_CHESTS)
                    .add(
                            GenerationsBlocks.POKEBALL_CHEST.get(),
                            GenerationsBlocks.GREATBALL_CHEST.get(),
                            GenerationsBlocks.ULTRABALL_CHEST.get(),
                            GenerationsBlocks.MASTERBALL_CHEST.get());

            GenerationsCore.LOGGER.info("Done");
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
