package generations.gg.generations.core.generationscore.common.util.extensions

import generations.gg.generations.core.generationscore.common.world.item.components.GenerationsDataComponents
import net.minecraft.core.component.DataComponentType
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block

fun Item.Properties.distance(): Item.Properties = this.component(GenerationsDataComponents.DISTANCE, 0.0)

fun Item.resourceKey(): ResourceKey<Item> = this.builtInRegistryHolder().key()

val Block.id: ResourceLocation
    get() = BuiltInRegistries.BLOCK.getKey(this)
val Item.id: ResourceLocation
    get() = BuiltInRegistries.ITEM.getKey(this)
