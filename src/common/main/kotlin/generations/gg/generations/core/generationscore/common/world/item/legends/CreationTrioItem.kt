package generations.gg.generations.core.generationscore.common.world.item.legends

import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders
import generations.gg.generations.core.generationscore.common.config.SpeciesKey
import generations.gg.generations.core.generationscore.common.world.item.ItemWithLangTooltipImpl
import net.minecraft.resources.ResourceLocation

class CreationTrioItem(properties: Properties, @JvmField val speciesId: SpeciesKey, private val model: ResourceLocation) :
    ItemWithLangTooltipImpl(properties), ModelContextProviders.ModelProvider {
    override fun getModel(): ResourceLocation {
        return model
    }
}
