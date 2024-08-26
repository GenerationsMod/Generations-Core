package generations.gg.generations.core.generationscore.common.network.spawn

import generations.gg.generations.core.generationscore.common.network.ClientNetworkPacketHandler
import net.minecraft.client.Minecraft
import net.minecraft.world.entity.Entity

class SpawnExtraDataEntityHandler<T : SpawnExtraDataEntityPacket<T, E>, E : Entity> : ClientNetworkPacketHandler<T> {

    override fun handle(packet: T, client: Minecraft) {
        packet.spawnAndApply(client)
    }
}