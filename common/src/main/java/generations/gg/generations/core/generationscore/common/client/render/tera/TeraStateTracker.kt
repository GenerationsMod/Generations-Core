package generations.gg.generations.core.generationscore.common.client.render.tera

import java.util.UUID

object TeraStateTracker {
    private val teraAlready = mutableSetOf<UUID>()

    @JvmStatic
    fun hasPlayed(uuid: UUID?): Boolean {
        return uuid != null && teraAlready.contains(uuid)
    }

    @JvmStatic
    fun markPlayed(uuid: UUID?) {
        if (uuid != null) {
            teraAlready.add(uuid)
        }
    }

    @JvmStatic
    fun reset(uuid: UUID?) {
        if (uuid != null) {
            teraAlready.remove(uuid)
        }
    }
}