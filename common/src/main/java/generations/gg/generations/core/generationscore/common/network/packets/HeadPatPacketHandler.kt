package generations.gg.generations.core.generationscore.common.network.packets

import com.cobblemon.mod.common.CobblemonNetwork.sendPacket
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.net.messages.client.animation.PlayPoseableAnimationPacket
import generations.gg.generations.core.generationscore.common.network.ServerNetworkPacketHandler
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.phys.AABB

class HeadPatPacketHandler : ServerNetworkPacketHandler<HeadPatPacket> {
    override fun handle(packet: HeadPatPacket, server: MinecraftServer, player: ServerPlayer) {
        val pokemonEntity = player.serverLevel().getEntity(packet.pokemonId)
        if (pokemonEntity is PokemonEntity) {
            if(pokemonEntity.isSilent) return
                val pkt = PlayPoseableAnimationPacket(pokemonEntity.id, setOf("glad"), emptySet())
                player.level().getEntitiesOfClass(ServerPlayer::class.java, AABB.ofSize(pokemonEntity.position(), 64.0, 64.0, 64.0)) { true }
                    .forEach {
                    it.sendPacket(pkt)
                }
            }
        }
}