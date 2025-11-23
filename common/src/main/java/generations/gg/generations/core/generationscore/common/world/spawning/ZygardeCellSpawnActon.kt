package generations.gg.generations.core.generationscore.common.world.spawning

import com.cobblemon.mod.common.api.spawning.SpawnBucket
import com.cobblemon.mod.common.api.spawning.detail.SingleEntitySpawnAction
import com.cobblemon.mod.common.api.spawning.position.SpawnablePosition
import generations.gg.generations.core.generationscore.common.world.entity.GenerationsEntities
import generations.gg.generations.core.generationscore.common.world.entity.ZygardeCellEntity
import generations.gg.generations.core.generationscore.common.world.entity.asValue

class ZygardeCellSpawnActon(spawnablePosition: SpawnablePosition, bucket: SpawnBucket, override val detail: ZygardeCellDetail) : SingleEntitySpawnAction<ZygardeCellEntity>(spawnablePosition, bucket, detail) {
    override fun createEntity(): ZygardeCellEntity? = GenerationsEntities.ZYGARDE_CELL.asValue<ZygardeCellEntity>().create(spawnablePosition.world)
}