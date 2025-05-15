package generations.gg.generations.core.generationscore.common.world.loot

import com.mojang.serialization.MapCodec
import dev.architectury.registry.registries.DeferredRegister
import dev.architectury.registry.registries.RegistrySupplier
import generations.gg.generations.core.generationscore.common.GenerationsCore
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryType

object LootPoolEntryTypes {
    val REGISTER: DeferredRegister<LootPoolEntryType> =
        DeferredRegister.create(GenerationsCore.MOD_ID, Registries.LOOT_POOL_ENTRY_TYPE)

    fun <T : LootPoolEntryContainer?> register(name: String?, codec: MapCodec<T>): RegistrySupplier<LootPoolEntryType> {
        return REGISTER.register(
            name
        ) { LootPoolEntryType(codec) }
    }

    @JvmField
    val RESOURCE_KEY: RegistrySupplier<LootPoolEntryType> = register("resource_key", ResouceKeyEntry.CODEC)

    fun init() {
        REGISTER.register()
    }
}
