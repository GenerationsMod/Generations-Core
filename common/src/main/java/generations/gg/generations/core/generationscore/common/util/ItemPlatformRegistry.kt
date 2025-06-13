package generations.gg.generations.core.generationscore.common.util

import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.Item

open class ItemPlatformRegistry: PlatformRegistry<Item>() {
    override val registry: Registry<Item> = BuiltInRegistries.ITEM
    override val resourceKey: ResourceKey<Registry<Item>> = Registries.ITEM
}