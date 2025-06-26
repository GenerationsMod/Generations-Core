package generations.gg.generations.core.generationscore.common.network.packets.statue

import com.cobblemon.mod.common.api.net.ServerNetworkPacketHandler
import com.cobblemon.mod.common.api.pokemon.PokemonProperties
import generations.gg.generations.core.generationscore.common.world.entity.statue.StatueEntity
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.player.Player
import java.util.Optional
import java.util.function.BiConsumer

open class UpdateStatueHandler<V, T : UpdateStatuePacket<V, T>>(private var accessor: EntityDataAccessor<V>) : ServerNetworkPacketHandler<T> {


    override fun handle(packet: T, server: MinecraftServer, player: ServerPlayer) {
        player.level().getEntity(packet.entityId).takeIf { it is StatueEntity }?.let { it as StatueEntity }?.run {
            entityData.set(accessor, packet.value)
        }
    }

    object Properties : UpdateStatueHandler<PokemonProperties, UpdateStatuePacket.Properties>(StatueEntity.PROPERTIES)
    object Label : UpdateStatueHandler<Optional<String>, UpdateStatuePacket.Label>(StatueEntity.LABEL)
    object Scale : UpdateStatueHandler<Float, UpdateStatuePacket.Scale>(StatueEntity.SCALE)
    object PoseType : UpdateStatueHandler<com.cobblemon.mod.common.entity.PoseType, UpdateStatuePacket.PoseType>(StatueEntity.POSE_TYPE)
    object StaticToggle : UpdateStatueHandler<Boolean, UpdateStatuePacket.StaticToggle>(StatueEntity.STATIC_TOGGLE)
    object StaticPartial : UpdateStatueHandler<Float, UpdateStatuePacket.StaticPartial>(StatueEntity.STATIC_PARTIAL)
    object StaticAge : UpdateStatueHandler<Int, UpdateStatuePacket.StaticAge>(StatueEntity.STATIC_AGE)
    object Interactable : UpdateStatueHandler<Boolean, UpdateStatuePacket.Interactable>(StatueEntity.INTERACTABLE)
    object Material : UpdateStatueHandler<Optional<String>, UpdateStatuePacket.Material>(StatueEntity.MATERIAL)
    object Orientation : UpdateStatueHandler<Float, UpdateStatuePacket.Orientation>(StatueEntity.ORIENTATION)
}
