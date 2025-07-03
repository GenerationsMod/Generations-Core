package generations.gg.generations.core.generationscore.common.client.model

import gg.generations.rarecandy.renderer.rendering.ObjectInstance

interface InstanceProvider {
    fun generateInstance(): ObjectInstance?

    var instanceArray: Array<ObjectInstance?>?
}
