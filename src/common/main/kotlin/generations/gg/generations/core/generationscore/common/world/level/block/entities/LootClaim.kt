package generations.gg.generations.core.generationscore.common.world.level.block.entities

import generations.gg.generations.core.generationscore.common.GenerationsCore
import java.time.Instant
import java.util.*

data class LootClaim(val uuid: UUID, var time: Instant) {
    val isExpired: Boolean
        get() = Instant.now().isAfter(time)

    fun refresh() {
        time = Instant.now().plus(GenerationsCore.CONFIG!!.lootTime)
    }
}
