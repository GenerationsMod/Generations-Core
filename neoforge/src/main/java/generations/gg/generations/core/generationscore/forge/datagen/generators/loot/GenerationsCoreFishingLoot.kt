package generations.gg.generations.core.generationscore.forge.datagen.generators.loot

import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems
import generations.gg.generations.core.generationscore.common.world.loot.GenerationCoreLootTables

class GenerationsCoreFishingLoot : net.minecraft.data.loot.LootTableSubProvider {
    override fun generate(biConsumer: java.util.function.BiConsumer<net.minecraft.resources.ResourceKey<net.minecraft.world.level.storage.loot.LootTable>, net.minecraft.world.level.storage.loot.LootTable.Builder>) {
        val builder = net.minecraft.world.level.storage.loot.LootTable.lootTable()
            .withPool(
                net.minecraft.world.level.storage.loot.LootPool.lootPool()
                    .setRolls(net.minecraft.world.level.storage.loot.providers.number.ConstantValue.exactly(1.0f))
                    .add(net.minecraft.world.level.storage.loot.entries.LootItem.lootTableItem(net.minecraft.world.item.Items.POTATO))
            )

        biConsumer.accept(GenerationCoreLootTables.FISHING_OLD, builder)
        biConsumer.accept(GenerationCoreLootTables.FISHING_GOOD, builder)
        biConsumer.accept(GenerationCoreLootTables.FISHING_SUPER, builder)
        biConsumer.accept(
            GenerationCoreLootTables.FISHING_RUBY,
            net.minecraft.world.level.storage.loot.LootTable.lootTable().withPool(
                net.minecraft.world.level.storage.loot.LootPool.lootPool()
                    .add(net.minecraft.world.level.storage.loot.entries.LootItem.lootTableItem(GenerationsItems.SHARD_OF_EMOTION.get()))
                    .add(net.minecraft.world.level.storage.loot.entries.LootItem.lootTableItem(GenerationsItems.SHARD_OF_KNOWLEDGE.get()))
                    .add(net.minecraft.world.level.storage.loot.entries.LootItem.lootTableItem(GenerationsItems.SHARD_OF_WILLPOWER.get()))
            )
        )
    }
}