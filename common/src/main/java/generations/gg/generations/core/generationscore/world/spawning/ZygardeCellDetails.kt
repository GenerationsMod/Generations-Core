package generations.gg.generations.core.generationscore.world.spawning

import com.cobblemon.mod.common.api.spawning.context.SpawningContext
import com.cobblemon.mod.common.api.spawning.detail.SpawnAction
import com.cobblemon.mod.common.api.spawning.detail.SpawnDetail
import com.cobblemon.mod.common.util.asTranslated
import com.cobblemon.mod.common.util.lang
import net.minecraft.network.chat.MutableComponent

class ZygardeCellDetail : SpawnDetail() {
    companion object {
        val TYPE = "zygarde"
    }

    override val type: String = TYPE;
    override fun doSpawn(ctx: SpawningContext): SpawnAction<*> {
        TODO("Not yet implemented")
    }

    override fun getName(): MutableComponent {
        displayName?.let { return it.asTranslated() }

        return "generations_core.entity.zygarde_cel".asTranslated()
    }

}