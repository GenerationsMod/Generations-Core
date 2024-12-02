package generations.gg.generations.core.generationscore.common.network.packets.statue

import com.cobblemon.mod.common.api.pokemon.PokemonProperties
import generations.gg.generations.core.generationscore.common.network.ServerNetworkPacketHandler
import generations.gg.generations.core.generationscore.common.world.entity.statue.StatueEntity
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.player.Player
import java.util.function.BiConsumer

open class UpdateStatueHandler<V, T : UpdateStatuePacket<V, T>>(val consumer: (StatueEntity, V) -> Unit ) : BiConsumer<T, Player> { //: ServerNetworkPacketHandler<T> {


    override fun accept(packet: T, player: Player) {
        player.level().getEntity(packet.entityId).takeIf { it is StatueEntity }?.let { it as StatueEntity }?.run {
            consumer.invoke(this, packet.value)
        }
    }

    object Properties : UpdateStatueHandler<PokemonProperties, UpdateStatuePacket.Properties>({ statue, value -> statue.properties = value })
    object Label : UpdateStatueHandler<String?, UpdateStatuePacket.Label>({ statue, value -> statue.label = value })
    object Scale : UpdateStatueHandler<Float, UpdateStatuePacket.Scale>({ statue, value -> statue.scale = value })
    object PoseType : UpdateStatueHandler<com.cobblemon.mod.common.entity.PoseType, UpdateStatuePacket.PoseType>({ statue, value -> statue.poseType = value })
    object StaticToggle : UpdateStatueHandler<Boolean, UpdateStatuePacket.StaticToggle>({ statue, value -> statue.staticToggle = value })
    object StaticPartial : UpdateStatueHandler<Float, UpdateStatuePacket.StaticPartial>({ statue, value -> statue.staticPartial = value })
    object StaticAge : UpdateStatueHandler<Int, UpdateStatuePacket.StaticAge>({ statue, value -> statue.staticAge = value })
    object Interactable : UpdateStatueHandler<Boolean, UpdateStatuePacket.Interactable>({ statue, value -> statue.interactable = value })
    object Material : UpdateStatueHandler<String?, UpdateStatuePacket.Material>({ statue, value -> statue.material = value })
    object Orientation : UpdateStatueHandler<Float, UpdateStatuePacket.Orientation>({ statue, value -> statue.orientation = value })

}
