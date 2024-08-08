package generations.gg.generations.core.generationscore.common.world.dialogue.nodes.spawning

import com.google.gson.JsonObject
import dev.architectury.utils.value.FloatSupplier
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.world.entity.player.Player

object PlayerYawLogic :
    YawLogic {
    override fun createSupplier(player: Player?): FloatSupplier = FloatSupplier { player!!.getYHeadRot() }

    override fun type(): YawLogicType<*> = YawLogicTypes.PLAYER

    override fun encode(buffer: FriendlyByteBuf) {

    }

    override fun toJson(json: JsonObject?) {

    }
}