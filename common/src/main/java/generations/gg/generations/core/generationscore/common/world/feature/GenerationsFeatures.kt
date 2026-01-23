package generations.gg.generations.core.generationscore.common.world.feature

import dev.architectury.registry.registries.DeferredRegister
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.world.feature.configurations.LargeTeraCrystalConfiguration
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration
import java.util.function.Supplier

object GenerationsFeatures {
    lateinit var LARGE_TERA_CRYSTAL: Supplier<LargeTeraCrystalFeature>
    lateinit var TERA_CRYSTAL_CLUSTER: Supplier<TeraCrystalClusterFeature>

    fun init() {
        LARGE_TERA_CRYSTAL = create("large_tera_crystal") { LargeTeraCrystalFeature(LargeTeraCrystalConfiguration.CODEC) }
        TERA_CRYSTAL_CLUSTER = create("tera_crystal_cluster") { TeraCrystalClusterFeature(NoneFeatureConfiguration.CODEC) }
    }

    fun <C : FeatureConfiguration, F : Feature<C>> create(id: String, supplier: Supplier<F>): Supplier<F> {
        return PlatformFeatureRegistry.INSTANCE.register(BuiltInRegistries.FEATURE, id, supplier)
    }
}