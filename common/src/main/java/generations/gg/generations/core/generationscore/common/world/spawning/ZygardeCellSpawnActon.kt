package generations.gg.generations.core.generationscore.common.world.spawning

import com.cobblemon.mod.common.api.spawning.context.SpawningContext
import com.cobblemon.mod.common.api.spawning.detail.SingleEntitySpawnAction
import com.cobblemon.mod.common.api.spawning.detail.SpawnAction
import generations.gg.generations.core.generationscore.common.world.entity.GenerationsEntities
import generations.gg.generations.core.generationscore.common.world.entity.ZygardeCellEntity
import net.minecraft.world.item.ItemStack

class ZygardeCellSpawnActon(ctx: SpawningContext, override val detail: ZygardeCellDetail) : SingleEntitySpawnAction<ZygardeCellEntity>(ctx, detail) {
    override fun createEntity(): ZygardeCellEntity? {
        return GenerationsEntities.ZYGARDE_CELL.get().create(ctx.world)
    }
}