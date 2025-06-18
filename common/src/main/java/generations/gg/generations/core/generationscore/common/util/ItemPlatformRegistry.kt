package generations.gg.generations.core.generationscore.common.util

import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.Item

open class ItemPlatformRegistry: PlatformRegistry<Item>(Registries.ITEM, BuiltInRegistries.ITEM)