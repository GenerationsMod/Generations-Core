package generations.gg.generations.core.generationscore.forge.datagen.generators.loot;

import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.common.world.loot.GenerationCoreLootTables;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.function.BiConsumer;

public class GenerationsCoreFishingLoot implements LootTableSubProvider {
    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> biConsumer) {
        LootTable.Builder builder = LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0f))
                        .add(LootItem.lootTableItem(Items.POTATO)));

        biConsumer.accept(GenerationCoreLootTables.FISHING_OLD, builder);
        biConsumer.accept(GenerationCoreLootTables.FISHING_GOOD, builder);
        biConsumer.accept(GenerationCoreLootTables.FISHING_SUPER, builder);
        biConsumer.accept(GenerationCoreLootTables.FISHING_RUBY, LootTable.lootTable().withPool(
                LootPool.lootPool()
                        .add(LootItem.lootTableItem(GenerationsItems.SHARD_OF_EMOTION.get()))
                        .add(LootItem.lootTableItem(GenerationsItems.SHARD_OF_KNOWLEDGE.get()))
                        .add(LootItem.lootTableItem(GenerationsItems.SHARD_OF_WILLPOWER.get()))));
    }
}