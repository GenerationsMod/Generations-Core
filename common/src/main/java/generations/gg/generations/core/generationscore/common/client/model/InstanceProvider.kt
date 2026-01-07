package generations.gg.generations.core.generationscore.common.client.model

import generations.gg.generations.core.generationscore.common.client.render.rarecandy.CobblemonInstance
import gg.generations.rarecandy.renderer.rendering.ObjectInstance

interface InstanceProvider {
    fun generateInstance(): CobblemonInstance

    var instanceArray: MutableList<CobblemonInstance>?
}
