package generations.gg.generations.core.generationscore.fabric.datagen;

import generations.gg.generations.core.generationscore.tags.GenerationsBlockTags;
import generations.gg.generations.core.generationscore.tags.GenerationsItemTags;
import generations.gg.generations.core.generationscore.world.entity.GenerationsEntities;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsWood;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBlockTags;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalEntityTypeTags;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

/**
 * Fabric Data Generator for Generations Tags
 * @author J.T. McQuigg
 * @see DataGen
 */
public class FabricTagsDataGen {
    public static void init(FabricDataGenerator.Pack pack) {
        pack.addProvider(BlockTagsProvider::new);
        pack.addProvider(ItemTagsProvider::new);
        pack.addProvider(EntityTypeTagsProvider::new);
    }
}

/**
 * Fabric Block Tag Provider for Generations Tags
 * @see FabricTagProvider.BlockTagProvider
 */
class BlockTagsProvider extends FabricTagProvider.BlockTagProvider {
    public BlockTagsProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        getOrCreateTagBuilder(ConventionalBlockTags.ORES).forceAddTag(GenerationsBlockTags.GENERATIONSORES);
        getOrCreateTagBuilder(ConventionalBlockTags.BOOKSHELVES).add(GenerationsWood.GHOST_BOOKSHELF.get(), GenerationsWood.ULTRA_DARK_BOOKSHELF.get(), GenerationsWood.ULTRA_JUNGLE_BOOKSHELF.get());
        getOrCreateTagBuilder(ConventionalBlockTags.CHESTS).forceAddTag(GenerationsBlockTags.POKEBALL_CHESTS);
    }
}

/**
 * Fabric Item Tag Provider for Generations Tags
 * @see FabricTagProvider.ItemTagProvider
 */
class ItemTagsProvider extends FabricTagProvider.ItemTagProvider {

    public ItemTagsProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        getOrCreateTagBuilder(ConventionalItemTags.ORES).forceAddTag(GenerationsItemTags.GENERATIONSORES);
        getOrCreateTagBuilder(ConventionalItemTags.BOOKSHELVES).add(GenerationsWood.GHOST_BOOKSHELF.get().asItem(), GenerationsWood.ULTRA_DARK_BOOKSHELF.get().asItem(), GenerationsWood.ULTRA_JUNGLE_BOOKSHELF.get().asItem());
        getOrCreateTagBuilder(ConventionalItemTags.INGOTS).add(GenerationsItems.Z_INGOT.get());
        getOrCreateTagBuilder(ConventionalItemTags.CHESTS).forceAddTag(GenerationsItemTags.POKEBALL_CHESTS);
    }
}

class EntityTypeTagsProvider extends FabricTagProvider.EntityTypeTagProvider {
    public EntityTypeTagsProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        getOrCreateTagBuilder(ConventionalEntityTypeTags.BOATS)
                .add(GenerationsEntities.BOAT_ENTITY.get(), GenerationsEntities.CHEST_BOAT_ENTITY.get());
    }
}
