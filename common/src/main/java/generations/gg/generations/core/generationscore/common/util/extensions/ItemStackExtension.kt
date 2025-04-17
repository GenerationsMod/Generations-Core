package generations.gg.generations.core.generationscore.common.util.extensions

import dev.architectury.registry.registries.RegistrySupplier
import generations.gg.generations.core.generationscore.common.util.removePokemon
import generations.gg.generations.core.generationscore.common.world.item.components.GenerationsItemComponents
import net.minecraft.core.component.DataComponentType
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

fun <T : Any> ItemStack.get(registrySupplier: RegistrySupplier<DataComponentType<T>>): T? = this.get(registrySupplier.get())
fun <T : Any> ItemStack.has(registrySupplier: RegistrySupplier<DataComponentType<T>>): Boolean = this.has(registrySupplier.get())

fun <T : Any> ItemStack.getOrDefault(registrySupplier: RegistrySupplier<DataComponentType<T>>, defaultValue: T): T = this.getOrDefault(registrySupplier.get(), defaultValue)

fun <T : Any> ItemStack.set(registrySupplier: RegistrySupplier<DataComponentType<T>>, value: T) = this.set(registrySupplier.get(), value)
fun <T : Any> ItemStack.remove(registrySupplier: RegistrySupplier<DataComponentType<T>>) = this.remove(registrySupplier.get())

fun Item.Properties.distance(): Item.Properties = this.component(GenerationsItemComponents.DISTANCE.get(), 0.0)

fun <T : Any> Item.Properties.component(registrySupplier: RegistrySupplier<DataComponentType<T>>, value: T): Item.Properties = this.component(registrySupplier.get(), value)

fun <T : Any> ItemStack.update(registrySupplier: RegistrySupplier<DataComponentType<T>>, value: T, function: (t: T) -> T) {
    this.update(registrySupplier.get(), value, function)
}
