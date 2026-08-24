package generations.gg.generations.core.generationscore.common.world.spawning

import com.cobblemon.mod.common.api.spawning.detail.SpawnAction
import com.cobblemon.mod.common.api.spawning.detail.SpawnDetail
import com.cobblemon.mod.common.api.spawning.position.SpawnablePosition
import com.cobblemon.mod.common.api.spawning.selection.SpawnSelectionData
import com.cobblemon.mod.common.util.asTranslated
import net.minecraft.network.chat.MutableComponent

class ZygardeCellDetail : SpawnDetail() {
    companion object {
        const val TYPE = "zygarde"
    }

    override val type: String = TYPE

    override fun createSpawnAction(
        spawnablePosition: SpawnablePosition,
        bucket: String,
        selectionData: SpawnSelectionData
    ): SpawnAction<*> = ZygardeCellSpawnActon(spawnablePosition, bucket, ZygardeCellDetail())

    override fun getName(): MutableComponent = displayName?.asTranslated() ?: "generations_core.entity.zygarde_cell".asTranslated()

}