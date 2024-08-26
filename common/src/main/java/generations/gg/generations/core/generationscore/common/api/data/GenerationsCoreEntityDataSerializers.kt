package generations.gg.generations.core.generationscore.common.api.data

//import generations.gg.generations.core.generationscore.common.world.entity.StatueEntity.StatueInfo
import generations.gg.generations.core.generationscore.common.util.readNullableString
import generations.gg.generations.core.generationscore.common.util.readPokemonProperties
import generations.gg.generations.core.generationscore.common.util.writeNullableString
import generations.gg.generations.core.generationscore.common.util.writePokemonProperties
import generations.gg.generations.core.generationscore.common.world.npc.display.NpcDisplayData
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.syncher.EntityDataSerializer
import net.minecraft.network.syncher.EntityDataSerializers

object GenerationsCoreEntityDataSerializers {

    @JvmField
    val NPC_PRESET = EntityDataSerializer.simple(
        { buf: FriendlyByteBuf?, npcDisplayData: NpcDisplayData ->
            npcDisplayData.serializeToByteBuf(
                buf
            )
        }, { NpcDisplayData(it) })

    val NULLABLE_STRING = EntityDataSerializer.simple(FriendlyByteBuf::writeNullableString, FriendlyByteBuf::readNullableString)
    val PROPERTIES = EntityDataSerializer.simple(FriendlyByteBuf::writePokemonProperties, FriendlyByteBuf::readPokemonProperties)

    @JvmStatic
    fun init() {
        EntityDataSerializers.registerSerializer(NPC_PRESET)
        EntityDataSerializers.registerSerializer(PROPERTIES)
        EntityDataSerializers.registerSerializer(NULLABLE_STRING)
    }
}