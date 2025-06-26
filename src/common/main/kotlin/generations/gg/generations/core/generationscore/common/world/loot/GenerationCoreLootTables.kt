package generations.gg.generations.core.generationscore.common.world.loot

import generations.gg.generations.core.generationscore.common.GenerationsCore.id
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.storage.loot.LootTable

object GenerationCoreLootTables {
    //FISHING
    val FISHING_OLD: ResourceKey<LootTable> = ResourceKey.create(Registries.LOOT_TABLE, id("gameplay/fishing/old"))
    val FISHING_GOOD: ResourceKey<LootTable> = ResourceKey.create(Registries.LOOT_TABLE, id("gameplay/fishing/good"))
    val FISHING_SUPER: ResourceKey<LootTable> = ResourceKey.create(Registries.LOOT_TABLE, id("gameplay/fishing/super"))
    val FISHING_RUBY: ResourceKey<LootTable> = ResourceKey.create(Registries.LOOT_TABLE, id("gameplay/fishing/ruby"))
}