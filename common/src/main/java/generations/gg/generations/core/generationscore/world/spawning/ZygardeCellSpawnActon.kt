package generations.gg.generations.core.generationscore.world.spawning

import com.cobblemon.mod.common.api.spawning.context.SpawningContext
import com.cobblemon.mod.common.api.spawning.detail.SpawnAction
import generations.gg.generations.core.generationscore.world.entity.GenerationsEntities
import generations.gg.generations.core.generationscore.world.entity.ZygardeCellEntity
import net.minecraft.world.item.ItemStack

class ZygardeCellSpawnActon(ctx: SpawningContext, override val detail: ZygardeCellDetail) : SpawnAction<ZygardeCellEntity>(ctx, detail) {
    override fun run(): ZygardeCellEntity? {
        val start = ItemStack.EMPTY
        start.item.hasCraftingRemainingItem()

        return GenerationsEntities.ZYGARDE_CELL.get().create(ctx.world)
    }
}