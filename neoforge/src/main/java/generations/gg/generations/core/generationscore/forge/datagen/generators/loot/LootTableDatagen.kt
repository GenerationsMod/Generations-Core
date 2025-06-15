package generations.gg.generations.core.generationscore.forge.datagen.generators.loot

import com.google.common.collect.ImmutableList
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.config.LegendKeys
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.loot.SpeciesKeyCondition
import net.minecraft.advancements.critereon.StatePropertiesPredicate
import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.loot.LootTableProvider
import net.minecraft.data.loot.LootTableSubProvider
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.CropBlock
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue
import java.util.concurrent.CompletableFuture
import java.util.function.BiConsumer

class LootTableDatagen(output: PackOutput, registries: CompletableFuture<HolderLookup.Provider>) : LootTableProvider(
    output, setOf(), ImmutableList.of(
        SubProviderEntry({ provider: HolderLookup.Provider ->
            GenerationsBlockLoot(
                provider
            )
        }, LootContextParamSets.BLOCK),
        SubProviderEntry({ GenerationsChestLoot() }, LootContextParamSets.CHEST),
        SubProviderEntry({ GenerationsCoreFishingLoot() }, LootContextParamSets.FISHING),
        SubProviderEntry({
            LootTableSubProvider { op ->
                op.accept(
                    GenerationsChestLoot.CALYREX_ROOTS, LootTable.lootTable().withPool(
                        LootPool.lootPool()
                            .setRolls(ConstantValue.exactly(1f))
                            .`when`(LootItemRandomChanceCondition.randomChance(1 / 400f))
                            .`when`(
                                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.CARROTS)
                                    .setProperties(
                                        StatePropertiesPredicate.Builder.properties().hasProperty(CropBlock.AGE, 7)
                                    )
                            )
                            .add(
                                AlternativesEntry.alternatives(
                                    LootItem.lootTableItem(GenerationsItems.SHADEROOT_CARROT)
                                        .`when`(LootItemRandomChanceCondition.randomChance(0.5f))
                                        .`when`(SpeciesKeyCondition.Builder().key(LegendKeys.SPECTRIER)),
                                    LootItem.lootTableItem(GenerationsItems.ICEROOT_CARROT)
                                        .`when`(SpeciesKeyCondition.Builder().key(LegendKeys.GLASTRIER))

                                )
                            )
                    )
                )
            }
        }, LootContextParamSets.BLOCK) //            Pair.of(ChestLoot::new, LootContextParamSets.CHEST),
        //            Pair.of(EntityLoot::new, LootContextParamSets.ENTITY),
        //            Pair.of(PiglinBarterLoot::new, LootContextParamSets.PIGLIN_BARTER),
        //            Pair.of(GiftLoot::new, LootContextParamSets.GIFT)
    ), registries
) {
    init {
        GenerationsCore.LOGGER.info("LootTableDatagen")
    }
}
