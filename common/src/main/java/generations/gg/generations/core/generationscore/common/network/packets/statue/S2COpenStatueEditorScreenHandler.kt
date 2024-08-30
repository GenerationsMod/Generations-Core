package generations.gg.generations.core.generationscore.common.network.packets.statue

import dev.architectury.utils.Env
import dev.architectury.utils.EnvExecutor
import generations.gg.generations.core.generationscore.common.client.screen.statue.StatueEditorScreen
import generations.gg.generations.core.generationscore.common.network.ClientNetworkPacketHandler
import generations.gg.generations.core.generationscore.common.world.entity.statue.StatueEntity
import net.minecraft.client.Minecraft

object S2COpenStatueEditorScreenHandler : ClientNetworkPacketHandler<S2COpenStatueEditorScreenPacket> {
    override fun handle(packet: S2COpenStatueEditorScreenPacket, minecraft: Minecraft) {
        EnvExecutor.runInEnv(Env.CLIENT) {
            Runnable {
                Minecraft.getInstance().tell(Runnable {
                    val statueEntity = Minecraft.getInstance().level!!.getEntity(packet.entityId) as StatueEntity?
                    Minecraft.getInstance().setScreen(StatueEditorScreen(statueEntity!!))
                })
            }
        }
    }
}
