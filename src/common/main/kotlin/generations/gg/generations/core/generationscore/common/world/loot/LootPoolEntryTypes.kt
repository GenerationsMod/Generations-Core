package generations.gg.generations.core.generationscore.common.world.loot

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.util.PlatformRegistry
import net.minecraft.core.Holder
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryType

object LootPoolEntryTypes: PlatformRegistry<LootPoolEntryType>(Registries.LOOT_POOL_ENTRY_TYPE, BuiltInRegistries.LOOT_POOL_ENTRY_TYPE) {
    val RESOURCE_KEY = register("resource_key", ResouceKeyEntry.CODEC)

    fun <T : LootPoolEntryContainer> register(name: String, codec: MapCodec<T>): Holder<LootPoolEntryType> {
        return create(name, { LootPoolEntryType(codec) })
    }
}
