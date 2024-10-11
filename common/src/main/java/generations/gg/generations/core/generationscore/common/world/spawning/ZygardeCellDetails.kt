package generations.gg.generations.core.generationscore.common.world.spawning

import com.cobblemon.mod.common.api.spawning.context.SpawningContext
import com.cobblemon.mod.common.api.spawning.detail.SpawnAction
import com.cobblemon.mod.common.api.spawning.detail.SpawnDetail
import com.cobblemon.mod.common.util.asTranslated
import net.minecraft.network.chat.MutableComponent

class ZygardeCellDetail : SpawnDetail() {
    companion object {
        const val TYPE = "zygarde"
    }

    override val type: String = TYPE
    override fun doSpawn(ctx: SpawningContext): SpawnAction<*> = ZygardeCellSpawnActon(ctx, ZygardeCellDetail())

    override fun getName(): MutableComponent {
        return displayName?.let { return it.asTranslated() } ?: return "generations_core.entity.zygarde_cell".asTranslated()
    }

}