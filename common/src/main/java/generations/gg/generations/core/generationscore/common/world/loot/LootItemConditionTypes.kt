package generations.gg.generations.core.generationscore.common.world.loot

import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.util.PlatformRegistry
import net.minecraft.core.Holder
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType

object LootItemConditionTypes: PlatformRegistry<LootItemConditionType>(Registries.LOOT_CONDITION_TYPE, BuiltInRegistries.LOOT_CONDITION_TYPE) {
    val SPECIES_KEY = register("species_key", SpeciesKeyCondition.CODEC)

    fun <T : LootItemCondition> register(name: String, supplier: MapCodec<T>): Holder<LootItemConditionType> = create(name, { LootItemConditionType(supplier) })
}
