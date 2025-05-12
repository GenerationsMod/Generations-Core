package generations.gg.generations.core.generationscore.common.world.loot

import com.mojang.serialization.MapCodec
import dev.architectury.registry.registries.DeferredRegister
import dev.architectury.registry.registries.RegistrySupplier
import generations.gg.generations.core.generationscore.common.GenerationsCore
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType

object LootItemConditionTypes {
    val REGISTER: DeferredRegister<LootItemConditionType> = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.LOOT_CONDITION_TYPE)

    fun <T : LootItemCondition?> register(name: String, supplier: MapCodec<T>): RegistrySupplier<LootItemConditionType> = REGISTER.register(name) { LootItemConditionType(supplier) }

    val SPECIES_KEY: RegistrySupplier<LootItemConditionType> = register("species_key", SpeciesKeyCondition.CODEC)

    @JvmStatic
    fun init() {
        REGISTER.register()
    }
}
