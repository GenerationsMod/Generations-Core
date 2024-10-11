package generations.gg.generations.core.generationscore.common.world.item

import generations.gg.generations.core.generationscore.common.GenerationsCore
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import java.util.*

data class MailType(val name: String) {
    lateinit var sealed: ClosedMailItem

    @JvmField val location: ResourceLocation = GenerationsCore.id("textures/gui/mail/" + name.lowercase(Locale.getDefault()).replace("_", "") + "mail.png")

    fun createMailItem(properties: Item.Properties): MailItem {
        return MailItem(this, properties.stacksTo(1))
    }

    fun createClosedMailItem(properties: Item.Properties): ClosedMailItem {
        val item = ClosedMailItem(this, properties.stacksTo(1))
        this.sealed = item
        return item
    }
}
