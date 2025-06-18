package generations.gg.generations.core.generationscore.common.world.level.block

import generations.gg.generations.core.generationscore.common.generationsResource
import generations.gg.generations.core.generationscore.common.util.PlatformRegistry
import net.minecraft.core.Holder
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation

object GenerationsCoreStats: PlatformRegistry<ResourceLocation>(Registries.CUSTOM_STAT, BuiltInRegistries.CUSTOM_STAT) {

    val HIDDEN_LOOT_FOUND = register("hidden_loot_found")
    val NORMAL_LOOT_FOUND = register("normal_loot_found")

    private fun register(name: String): Holder<ResourceLocation> = name.let { create(it, { it.generationsResource() }) }
}
