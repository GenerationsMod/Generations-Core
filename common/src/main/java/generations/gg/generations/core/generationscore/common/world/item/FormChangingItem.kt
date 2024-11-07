package generations.gg.generations.core.generationscore.common.world.item

import net.minecraft.world.item.Item


open class FormChangingItem(properties: Properties, override val provider: String, override val value: String): Item(properties), FormChangingString
