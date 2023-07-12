package generations.gg.generations.core.generationscore.network.packets

import com.cobblemon.mod.common.CobblemonNetwork
import com.cobblemon.mod.common.api.net.NetworkPacket
import com.cobblemon.mod.common.util.server
import generations.gg.generations.core.generationscore.network.GenerationsNetwork
import net.minecraft.resources.ResourceKey
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.level.Level

interface GenerationsNetworkPacket<T: NetworkPacket<T>>: NetworkPacket<T> {
    override fun sendToPlayer(player: ServerPlayer) = GenerationsNetwork.sendPacketToPlayer(player, this)

    /**
     * TODO
     *
     * @param players
     */
    override fun sendToPlayers(players: Iterable<ServerPlayer>) {
        if (players.any()) {
            GenerationsNetwork.sendPacketToPlayers(players, this)
        }
    }

    /**
     * TODO
     *
     */
    override fun sendToAllPlayers() = GenerationsNetwork.sendToAllPlayers(this)

    /**
     * TODO
     *
     */
    override fun sendToServer() = GenerationsNetwork.sendPacketToServer(this)

    // A copy from PlayerManager#sendToAround to work with our packets
    /**
     * TODO
     *
     * @param x
     * @param y
     * @param z
     * @param distance
     * @param worldKey
     * @param exclusionCondition
     */
    override fun sendToPlayersAround(x: Double, y: Double, z: Double, distance: Double, worldKey: ResourceKey<Level>, exclusionCondition: (ServerPlayer) -> Boolean) {
        val server = server() ?: return
        server.playerList.players.filter { player ->
            if (exclusionCondition.invoke(player))
                return@filter false
            val xDiff = x - player.x
            val yDiff = y - player.y
            val zDiff = z - player.z
            return@filter (xDiff * xDiff + yDiff * yDiff + zDiff) < distance * distance
        }
            .forEach { player -> GenerationsNetwork.sendPacketToPlayer(player, this) }
    }
}