package generations.gg.generations.core.generationscore.common.util

import com.cobblemon.mod.common.platform.PlatformRegistry
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation

abstract class PlatformRegistry<T>: PlatformRegistry<Registry<T>, ResourceKey<Registry<T>>, T>() {
    open fun init(consumer: (ResourceLocation, T) -> Unit) = register(consumer)
}