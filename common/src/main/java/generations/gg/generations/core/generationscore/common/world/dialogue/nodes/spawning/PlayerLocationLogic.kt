package generations.gg.generations.core.generationscore.common.world.dialogue.nodes.spawning

import com.google.gson.JsonObject
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.world.entity.player.Player
import net.minecraft.world.phys.Vec3
import java.util.function.Supplier

object PlayerLocationLogic :
    LocationLogic {
    override fun createSupplier(player: Player): Supplier<Vec3> = Supplier { player.position() }

    override fun type(): LocationLogicType<*>? = LocationLogicTypes.PLAYER

    override fun encode(buf: FriendlyByteBuf) {}
    override fun toJson(json: JsonObject) {}
}
