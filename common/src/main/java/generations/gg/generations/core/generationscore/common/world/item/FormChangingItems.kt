package generations.gg.generations.core.generationscore.common.world.item

import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.item.Item.Properties


abstract class FormChangingItem(properties: Properties, override val provider: String): Item(properties), FormChanging

open class FormChangingItemString(properties: Properties, provider: String, override val value: String, override val species: ResourceLocation? = null): FormChangingItem(properties, provider), FormChangingString
open class FormChangingItemBoolean(properties: Properties, provider: String, override val species: ResourceLocation? = null): FormChangingItem(properties, provider), FormChangingToggle

class FormChangingItems {
    companion object {
        fun createFormChangingItem(properties: Properties, provider: String, value: String? = null, species: ResourceLocation? = null): FormChangingItem {
            return when {
                value != null -> FormChangingItemString(properties, provider, value, species)
                else -> FormChangingItemBoolean(properties, provider, species)
            }
        }
    }
}
