package generations.gg.generations.core.generationscore.common.network.spawn

import com.cobblemon.mod.common.api.pokemon.PokemonProperties
import com.cobblemon.mod.common.entity.PoseType
import com.cobblemon.mod.common.net.messages.client.spawn.SpawnExtraDataEntityPacket
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.util.readNullableString
import generations.gg.generations.core.generationscore.common.util.readPokemonProperties
import generations.gg.generations.core.generationscore.common.util.writeNullableString
import generations.gg.generations.core.generationscore.common.util.writePokemonProperties
import generations.gg.generations.core.generationscore.common.world.entity.statue.StatueEntity
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket
import net.minecraft.world.entity.Entity

class SpawnStatuePacket(
    val properties: PokemonProperties,
    val label: String?,
    val scale: Float,
    val poseType: PoseType,
    val staticToggle: Boolean,
    val staticPartial: Float,
    val staticAge: Int,
    val interactable: Boolean,
    val material: String?,
    val orientation: Float,
    vanillaSpawnPacket: ClientboundAddEntityPacket
) : SpawnExtraDataEntityPacket<SpawnStatuePacket, StatueEntity>(vanillaSpawnPacket) {
    override val id = ID
    override fun encodeEntityData(buffer: RegistryFriendlyByteBuf) {
        buffer.writePokemonProperties(properties)
        buffer.writeNullableString(label)
        buffer.writeFloat(scale)
        buffer.writeEnum(poseType)
        buffer.writeBoolean(staticToggle)
        buffer.writeFloat(staticPartial)
        buffer.writeVarInt(staticAge)
        buffer.writeBoolean(interactable)
        buffer.writeNullableString(material)
        buffer.writeFloat(orientation)
    }

    override fun applyData(entity: StatueEntity) {
        entity.properties = this.properties
        entity.label = label
        entity.scale = this.scale
        entity.poseType = this.poseType
        entity.staticToggle = this.staticToggle
        entity.staticPartial = this.staticPartial
        entity.staticAge = this.staticAge
        entity.interactable = this.interactable
        entity.material = this.material
        entity.orientation = this.orientation
        entity.delegate.initialize(entity)
//        (entity.delegate as GenericBedrockClientDelegate).updateAge(startAge)
    }

    override fun checkType(entity: Entity): Boolean = entity is StatueEntity

    companion object {
        val ID = GenerationsCore.id("spawn_statue_entity")
        fun decode(buffer: RegistryFriendlyByteBuf): SpawnStatuePacket {
            val properties = buffer.readPokemonProperties()
            val label = buffer.readNullableString()
            val scale = buffer.readFloat()
            val poseType = buffer.readEnum(PoseType::class.java)
            val staticToggle = buffer.readBoolean()
            val staticPartial = buffer.readFloat()
            val staticAge = buffer.readVarInt()
            val interactable = buffer.readBoolean()
            val material = buffer.readNullableString()
            val orientation = buffer.readFloat()
            val vanillaPacket = decodeVanillaPacket(buffer)
            return SpawnStatuePacket(properties, label, scale, poseType, staticToggle, staticPartial, staticAge, interactable, material, orientation, vanillaPacket)
        }
    }
}