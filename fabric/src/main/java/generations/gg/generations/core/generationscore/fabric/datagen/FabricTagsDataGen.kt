package generations.gg.generations.core.generationscore.fabric.datagen

import com.cobblemon.mod.common.util.codec.pairCodec
import generations.gg.generations.core.generationscore.common.tags.GenerationsBlockTags
import generations.gg.generations.core.generationscore.common.tags.GenerationsItemTags
import generations.gg.generations.core.generationscore.common.world.entity.GenerationsEntities
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsPaintings
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsWood
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.BlockTagProvider
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.EntityTypeTagProvider
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.ItemTagProvider
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBlockTags
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalEntityTypeTags
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.data.tags.PaintingVariantTagsProvider
import net.minecraft.data.tags.TagsProvider
import net.minecraft.tags.PaintingVariantTags
import net.minecraft.world.entity.decoration.PaintingVariant
import java.util.concurrent.CompletableFuture

/**
 * Fabric Data Generator for Generations Tags
 * @author Joseph T. McQuigg
 * @see DataGen
 */
internal object FabricTagsDataGen {
    internal fun init(pack: FabricDataGenerator.Pack) {
        pack.addProvider(::BlockTagsProvider)
        pack.addProvider(::ItemTagsProvider)
        pack.addProvider(::EntityTypeTagsProvider)
        pack.addProvider(::PaintingTagsProvider)
    }
}

/**
 * Fabric Block Tag Provider for Generations Tags
 * @see FabricTagProvider.BlockTagProvider
 */
internal class BlockTagsProvider(
    output: FabricDataOutput,
    registriesFuture: CompletableFuture<HolderLookup.Provider>
) : BlockTagProvider(output, registriesFuture) {
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

internal class PaintingTagsProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) : FabricTagProvider<PaintingVariant>(output, Registries.PAINTING_VARIANT, registriesFuture) {
    override fun addTags(p0: HolderLookup.Provider) {
        this.tag(PaintingVariantTags.PLACEABLE).add(
            GenerationsPaintings.BLUE_POSTER,
            GenerationsPaintings.BLUE_POSTER_SPRITE,
            GenerationsPaintings.BLUE_SCROLL,
            GenerationsPaintings.CLEFAIRY_POSTER_SPRITE,
            GenerationsPaintings.CUTE_POSTER,
            GenerationsPaintings.CUTE_POSTER_SPRITE,
            GenerationsPaintings.DADS_SCROLL,
            GenerationsPaintings.GREEN_POSTER,
            GenerationsPaintings.GREEN_POSTER_SPRITE,
            GenerationsPaintings.GREEN_SCROLL,
            GenerationsPaintings.JIGGLYPUFF_POSTER_SPRITE,
            GenerationsPaintings.KISS_POSTER_SPRITE,
            GenerationsPaintings.LONG_POSTER,
            GenerationsPaintings.LONG_POSTER_SPRITE,
            GenerationsPaintings.NATIONAL_AWARD,
            GenerationsPaintings.PIKA_POSTER,
            GenerationsPaintings.PIKA_POSTER_SPRITE,
            GenerationsPaintings.PIKACHU_POSTER_SPRITE,
            GenerationsPaintings.POKE_BALL_POSTER,
            GenerationsPaintings.RED_POSTER
        )
    }

}

/**
 * Fabric Item Tag Provider for Generations Tags
 * @see FabricTagProvider.ItemTagProvider
 */
internal class ItemTagsProvider(
    output: FabricDataOutput,
    completableFuture: CompletableFuture<HolderLookup.Provider>
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
    completableFuture: CompletableFuture<HolderLookup.Provider>
) :
    EntityTypeTagProvider(output, completableFuture) {
    override fun addTags(arg: HolderLookup.Provider) {
        getOrCreateTagBuilder(ConventionalEntityTypeTags.BOATS)
            .add(GenerationsEntities.BOAT_ENTITY.value(), GenerationsEntities.CHEST_BOAT_ENTITY.value())
    }
}
