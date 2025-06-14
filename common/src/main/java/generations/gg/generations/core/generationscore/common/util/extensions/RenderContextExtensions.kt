package generations.gg.generations.core.generationscore.common.util.extensions

import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext
import com.cobblemon.mod.common.util.asResource

val RenderContext.Companion.TERA_TYPE: RenderContext.Key<String>
    get() = RenderContext.key("tera_type".asResource())