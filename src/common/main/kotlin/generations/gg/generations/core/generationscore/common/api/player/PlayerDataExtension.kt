package generations.gg.generations.core.generationscore.common.api.player

import com.cobblemon.mod.common.api.storage.player.PlayerDataExtension
import com.google.gson.JsonObject

abstract class PlayerDataExtension : PlayerDataExtension {
    override fun serialize(): JsonObject {
        val json = JsonObject()
        json.addProperty(PlayerDataExtension.NAME_KEY, name())
        return json
    }
}
