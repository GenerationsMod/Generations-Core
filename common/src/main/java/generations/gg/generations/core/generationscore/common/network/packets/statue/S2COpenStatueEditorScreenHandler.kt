package generations.gg.generations.core.generationscore.common.network.packets.statue

import com.cobblemon.mod.common.api.net.ClientNetworkPacketHandler
import dev.architectury.utils.Env
import dev.architectury.utils.EnvExecutor
import generations.gg.generations.core.generationscore.common.client.screen.statue.StatueEditorScreen
import generations.gg.generations.core.generationscore.common.world.entity.statue.StatueEntity
import net.minecraft.client.Minecraft

object S2COpenStatueEditorScreenHandler : ClientNetworkPacketHandler<S2COpenStatueEditorScreenPacket> {
    override fun handle(packet: S2COpenStatueEditorScreenPacket, minecraft: Minecraft) {
        val statueEntity = minecraft.level!!.getEntity(packet.entityId) as StatueEntity?
        minecraft.setScreen(StatueEditorScreen(statueEntity!!))
    }
}
