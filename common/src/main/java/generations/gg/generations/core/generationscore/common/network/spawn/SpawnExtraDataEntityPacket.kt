package generations.gg.generations.core.generationscore.common.network.spawn

import com.cobblemon.mod.common.mixin.invoker.ClientPlayNetworkHandlerInvoker
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket
import net.minecraft.client.Minecraft
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.protocol.PacketUtils
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket
import net.minecraft.world.entity.Entity
import net.minecraft.world.phys.Vec3

abstract class SpawnExtraDataEntityPacket<T: GenerationsNetworkPacket<T>, E : Entity>(private val vanillaSpawnPacket: ClientboundAddEntityPacket) : GenerationsNetworkPacket<T> {
    override fun encode(buffer: FriendlyByteBuf) {
        this.encodeEntityData(buffer)
        this.vanillaSpawnPacket.write(buffer)
    }

    abstract fun encodeEntityData(buffer: FriendlyByteBuf)

    abstract fun applyData(entity: E)

    abstract fun checkType(entity: Entity): Boolean

    fun spawnAndApply(client: Minecraft) {
        client.execute {
            val player = client.player ?: return@execute
            val world = player.level() as? ClientLevel ?: return@execute
            // This is a copy pasta of ClientPlayNetworkHandler#onEntitySpawn
            // This exists due to us needing to do everything it does except spawn the entity in the world.
            // We invoke applyData then we add the entity to the world.
            PacketUtils.ensureRunningOnSameThread(this.vanillaSpawnPacket, player.connection, client)
            val entityType = this.vanillaSpawnPacket.type
            val entity = entityType.create(world) ?: return@execute
            entity.recreateFromPacket(this.vanillaSpawnPacket)
            entity.deltaMovement = Vec3(this.vanillaSpawnPacket.xa, this.vanillaSpawnPacket.ya, this.vanillaSpawnPacket.za)
            // Cobblemon start
            if (this.checkType(entity)) {
                this.applyData(entity as E)
            }
            // Cobblemon end
            world.putNonPlayerEntity(this.vanillaSpawnPacket.id, entity)
            (player.connection as ClientPlayNetworkHandlerInvoker).callPlaySpawnSound(entity)
        }
    }

    companion object {
        fun decodeVanillaPacket(buffer: FriendlyByteBuf) = ClientboundAddEntityPacket(buffer)
    }
}