package generations.gg.generations.core.generationscore.common.world.loot

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.generationsResource
import generations.gg.generations.core.generationscore.common.util.PlatformRegistry
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryType

object LootPoolEntryTypes: PlatformRegistry<LootPoolEntryType>() {
    override val registry: Registry<LootPoolEntryType> = BuiltInRegistries.LOOT_POOL_ENTRY_TYPE
    override val resourceKey: ResourceKey<Registry<LootPoolEntryType>> = Registries.LOOT_POOL_ENTRY_TYPE

    val RESOURCE_KEY: LootPoolEntryType = register("resource_key", ResouceKeyEntry.CODEC)

    fun <T : LootPoolEntryContainer> register(name: String, codec: MapCodec<T>): LootPoolEntryType {
        return create(name.generationsResource(), LootPoolEntryType(codec))
    }
}
