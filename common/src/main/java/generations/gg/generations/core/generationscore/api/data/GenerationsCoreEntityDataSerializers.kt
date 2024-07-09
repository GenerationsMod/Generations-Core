package generations.gg.generations.core.generationscore.api.data

import com.cobblemon.mod.common.api.pokemon.PokemonProperties
import com.cobblemon.mod.common.api.pokemon.PokemonProperties.Companion.parse
import generations.gg.generations.core.generationscore.world.entity.StatueEntity.StatueInfo
import generations.gg.generations.core.generationscore.world.npc.display.NpcDisplayData
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.syncher.EntityDataSerializer
import net.minecraft.network.syncher.EntityDataSerializers

object GenerationsCoreEntityDataSerializers {
    val STATUE_INFO: EntityDataSerializer<StatueInfo> = EntityDataSerializer.simple(
        { buf: FriendlyByteBuf?, data: StatueInfo ->
            data.serializeToByteBuf(
                buf!!
            )
        }) { StatueInfo() }

    @JvmField val NPC_PRESET = EntityDataSerializer.simple({ buf: FriendlyByteBuf?, npcDisplayData: NpcDisplayData -> npcDisplayData.serializeToByteBuf(buf) }, { NpcDisplayData(it) })
    @JvmField val NULLABLE_STRING = EntityDataSerializer.simple({ buf: FriendlyByteBuf, string: String? -> buf.writeNullable(string, FriendlyByteBuf::writeUtf) }, { it.readNullable(FriendlyByteBuf::readUtf) })
    @JvmField val PROPERTIES = EntityDataSerializer.simple(
        { buf: FriendlyByteBuf, properties: PokemonProperties -> buf.writeUtf(properties.asString(" ")) }) { buf: FriendlyByteBuf ->
        parse(
            buf.readUtf(),
            " ",
            "="
        )
    }

    @JvmStatic
    fun init() {
        EntityDataSerializers.registerSerializer(STATUE_INFO)
        EntityDataSerializers.registerSerializer(NPC_PRESET)
        EntityDataSerializers.registerSerializer(PROPERTIES)
        EntityDataSerializers.registerSerializer(NULLABLE_STRING)
    }
}
