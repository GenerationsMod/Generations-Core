package generations.gg.generations.core.generationscore.common.api.player

import com.google.gson.JsonObject
import generations.gg.generations.core.generationscore.common.GenerationsCore
import net.minecraft.world.entity.player.Player
import java.math.BigDecimal

class AccountInfo(var balance: BigDecimal = BigDecimal.ZERO) : PlayerDataExtension() {

    override fun name(): String {
        return KEY
    }

    override fun serialize(): JsonObject {
        val json = super.serialize()
        json.addProperty("balance", balance)
        return json
    }

    override fun deserialize(jsonObject: JsonObject): PlayerDataExtension {
        return AccountInfo(jsonObject.getAsJsonPrimitive("balance").asBigDecimal)
    }

    companion object {
        var KEY: String = "account_info"
        fun get(player: Player?): AccountInfo {
            return AccountInfo(GenerationsCore.CONFIG!!.economy.startingFunds) //Cobblemon.playerDataManager.getGenericData(player).getExtraData().computeIfAbsent(KEY, key -> new AccountInfo(GenerationsCore.CONFIG.economy.startingFunds)); TODO: rework
        }
    }
}
