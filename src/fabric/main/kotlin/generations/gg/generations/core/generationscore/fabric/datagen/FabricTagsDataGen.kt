package generations.gg.generations.core.generationscore.fabric.datagen

import generations.gg.generations.core.generationscore.common.tags.GenerationsBlockTags
import generations.gg.generations.core.generationscore.common.tags.GenerationsItemTags
import generations.gg.generations.core.generationscore.common.world.entity.GenerationsEntities
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsWood
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.BlockTagProvider
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.EntityTypeTagProvider
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.ItemTagProvider
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBlockTags
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalEntityTypeTags
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags
import net.minecraft.core.HolderLookup
import java.util.concurrent.CompletableFuture

/**
 * Fabric Data Generator for Generations Tags
 * @author Joseph T. McQuigg
 * @see DataGen
 */
internal object FabricTagsDataGen {
    internal fun init(pack: FabricDataGenerator.Pack) {
        pack.addProvider { output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider?>? ->
            BlockTagsProvider(
                output,
                registriesFuture
            )
        }
        pack.addProvider { output: FabricDataOutput, completableFuture: CompletableFuture<HolderLookup.Provider?>? ->
            ItemTagsProvider(
                output,
                completableFuture
            )
        }
        pack.addProvider { output: FabricDataOutput, completableFuture: CompletableFuture<HolderLookup.Provider?>? ->
            EntityTypeTagsProvider(
                output,
                completableFuture
            )
        }
    }
}

/**
 * Fabric Block Tag Provider for Generations Tags
 * @see FabricTagProvider.BlockTagProvider
 */
internal class BlockTagsProvider(
    output: FabricDataOutput,
    registriesFuture: CompletableFuture<HolderLookup.Provider?>?
) :
    BlockTagProvider(output, registriesFuture) {
    override fun addTags(arg: HolderLookup.Provider) {
        getOrCreateTagBuilder(ConventionalBlockTags.ORES).forceAddTag(GenerationsBlockTags.GENERATIONSORES)
        getOrCreateTagBuilder(ConventionalBlockTags.BOOKSHELVES).add(
            GenerationsWood.GHOST_BOOKSHELF.value(),
            GenerationsWood.ULTRA_DARK_BOOKSHELF.value(),
            GenerationsWood.ULTRA_JUNGLE_BOOKSHELF.value()
        )
        getOrCreateTagBuilder(ConventionalBlockTags.CHESTS).forceAddTag(GenerationsBlockTags.POKEBALL_CHESTS)
    }
}

/**
 * Fabric Item Tag Provider for Generations Tags
 * @see FabricTagProvider.ItemTagProvider
 */
internal class ItemTagsProvider(
    output: FabricDataOutput,
    completableFuture: CompletableFuture<HolderLookup.Provider?>?
) :
    ItemTagProvider(output, completableFuture) {
    override fun addTags(arg: HolderLookup.Provider) {
        getOrCreateTagBuilder(ConventionalItemTags.ORES).forceAddTag(GenerationsItemTags.GENERATIONSORES)
        getOrCreateTagBuilder(ConventionalItemTags.BOOKSHELVES).add(
            GenerationsWood.GHOST_BOOKSHELF.value().asItem(),
            GenerationsWood.ULTRA_DARK_BOOKSHELF.value().asItem(),
            GenerationsWood.ULTRA_JUNGLE_BOOKSHELF.value().asItem()
        )
        getOrCreateTagBuilder(ConventionalItemTags.INGOTS).add(GenerationsItems.Z_INGOT.value())
        getOrCreateTagBuilder(ConventionalItemTags.CHESTS).forceAddTag(GenerationsItemTags.POKEBALL_CHESTS)
    }
}

internal class EntityTypeTagsProvider(
    output: FabricDataOutput,
    completableFuture: CompletableFuture<HolderLookup.Provider?>?
) :
    EntityTypeTagProvider(output, completableFuture) {
    override fun addTags(arg: HolderLookup.Provider) {
        getOrCreateTagBuilder(ConventionalEntityTypeTags.BOATS)
            .add(GenerationsEntities.BOAT_ENTITY.value(), GenerationsEntities.CHEST_BOAT_ENTITY.value())
    }
}
