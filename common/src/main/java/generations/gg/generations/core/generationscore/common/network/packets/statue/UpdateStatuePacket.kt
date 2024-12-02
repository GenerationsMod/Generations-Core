package generations.gg.generations.core.generationscore.common.network.packets.statue

import com.cobblemon.mod.common.api.pokemon.PokemonProperties
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.network.packets.GenerationsNetworkPacket
import generations.gg.generations.core.generationscore.common.util.readNullableString
import generations.gg.generations.core.generationscore.common.util.readPokemonProperties
import generations.gg.generations.core.generationscore.common.util.writeNullableString
import generations.gg.generations.core.generationscore.common.util.writePokemonProperties
import generations.gg.generations.core.generationscore.common.world.entity.statue.StatueEntity
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.resources.ResourceLocation

abstract class UpdateStatuePacket<V, T : UpdateStatuePacket<V, T>>(var entityId: Int, var value: V) : GenerationsNetworkPacket<T> {
    override fun encode(buf: FriendlyByteBuf) {
        buf.writeInt(entityId).also { encodeValue(buf) }
    }

    abstract fun encodeValue(buf: FriendlyByteBuf)

    class Properties(entityId: Int, value: PokemonProperties) : UpdateStatuePacket<PokemonProperties, Properties>(entityId, value) {
        override fun encodeValue(buf: FriendlyByteBuf) = buf.writePokemonProperties(value)

        override fun getId(): ResourceLocation = PROPERTIES_ID
    }

    class Label(entityId: Int, value: String?) : UpdateStatuePacket<String?, Label>(entityId, value) {
        override fun encodeValue(buf: FriendlyByteBuf) = buf.writeNullableString(value)

        override fun getId(): ResourceLocation = LABEL_ID
    }

    class Scale(entityId: Int, value: Float) : UpdateStatuePacket<Float, Scale>(entityId, value) {
        override fun encodeValue(buf: FriendlyByteBuf) {
            buf.writeFloat(value)
        }

        override fun getId(): ResourceLocation = SCALE_ID
    }

    class PoseType(entityId: Int, value: com.cobblemon.mod.common.entity.PoseType) : UpdateStatuePacket<com.cobblemon.mod.common.entity.PoseType, PoseType>(entityId, value) {
        override fun encodeValue(buf: FriendlyByteBuf) {
            buf.writeEnum(value)
        }

        override fun getId(): ResourceLocation = POSE_TYPE_ID
    }

    class StaticToggle(entityId: Int, value: Boolean) : UpdateStatuePacket<Boolean, StaticToggle>(entityId, value) {
        override fun encodeValue(buf: FriendlyByteBuf) {
            buf.writeBoolean(value)
        }

        override fun getId(): ResourceLocation = STATIC_TOGGLE_ID
    }

    class StaticPartial(entityId: Int, value: Float) : UpdateStatuePacket<Float, StaticPartial>(entityId, value) {
        override fun encodeValue(buf: FriendlyByteBuf) {
            buf.writeFloat(value)
        }

        override fun getId(): ResourceLocation = STATIC_PARTIAL_ID
    }

    class StaticAge(entityId: Int, value: Int) : UpdateStatuePacket<Int, StaticAge>(entityId, value) {
        override fun encodeValue(buf: FriendlyByteBuf) {
            buf.writeInt(value)
        }
        override fun getId(): ResourceLocation = STATIC_AGE_ID
    }

    class Interactable(entityId: Int, value: Boolean) : UpdateStatuePacket<Boolean, Interactable>(entityId, value) {
        override fun encodeValue(buf: FriendlyByteBuf) {
            buf.writeBoolean(value)
        }

        override fun getId(): ResourceLocation = INTERACTABLE_ID
    }

    class Material(entityId: Int, value: String?) : UpdateStatuePacket<String?, Material>(entityId, value) {
        override fun encodeValue(buf: FriendlyByteBuf) {
            buf.writeNullableString(value)
        }

        override fun getId(): ResourceLocation = MATERIAL_ID
    }

    class Orientation(entityId: Int, value: Float) : UpdateStatuePacket<Float, Orientation>(entityId, value) {
        override fun encodeValue(buf: FriendlyByteBuf) {
            buf.writeFloat(value)
        }

        override fun getId(): ResourceLocation = ORIENTATION_ID
    }

    companion object {
        val PROPERTIES_ID: ResourceLocation = GenerationsCore.id("update_statue_properties")
        val LABEL_ID = GenerationsCore.id("update_statue_label")
        val SCALE_ID = GenerationsCore.id("update_statue_scale")
        val POSE_TYPE_ID = GenerationsCore.id("update_statue_pose_type")
        val STATIC_TOGGLE_ID = GenerationsCore.id("update_statue_static_toggle")
        val STATIC_PARTIAL_ID = GenerationsCore.id("update_statue_static_partial")
        val STATIC_AGE_ID = GenerationsCore.id("update_statue_static_age")
        val INTERACTABLE_ID = GenerationsCore.id("update_statue_interactable")
        val MATERIAL_ID = GenerationsCore.id("update_statue_material")
        val ORIENTATION_ID = GenerationsCore.id("update_statue_orientation")

        fun propertiesDecode(buf:FriendlyByteBuf): Properties = decode(buf, FriendlyByteBuf::readPokemonProperties, ::Properties)
        fun labelDecode(buf: FriendlyByteBuf): Label = decode(buf, FriendlyByteBuf::readNullableString, ::Label)
        fun scaleDecode(buf: FriendlyByteBuf): Scale = decode(buf, FriendlyByteBuf::readFloat, ::Scale)
        fun poseTypeDecode(buf: FriendlyByteBuf): PoseType = decode(buf, { it.readEnum(com.cobblemon.mod.common.entity.PoseType::class.java) }, ::PoseType)
        fun staticToggleDecode(buf: FriendlyByteBuf): StaticToggle = decode(buf, FriendlyByteBuf::readBoolean, ::StaticToggle)
        fun staticPartialDecode(buf: FriendlyByteBuf): StaticPartial = decode(buf, FriendlyByteBuf::readFloat, ::StaticPartial)
        fun staticAgeDecode(buf: FriendlyByteBuf): StaticAge = decode(buf, FriendlyByteBuf::readInt, ::StaticAge)
        fun interactableDecode(buf: FriendlyByteBuf): Interactable = decode(buf, FriendlyByteBuf::readBoolean, ::Interactable)
        fun materialDecode(buf: FriendlyByteBuf): Material = decode(buf, FriendlyByteBuf::readNullableString, ::Material)
        fun orientationDecode(buf: FriendlyByteBuf): Orientation = decode(buf, FriendlyByteBuf::readFloat, ::Orientation)
        fun <T, V : UpdateStatuePacket<T, V>> decode(buf: FriendlyByteBuf, function : (FriendlyByteBuf) -> T, factory: (Int, T) -> V): V {
            return factory.invoke(buf.readInt(), function.invoke(buf))
        }
    }
}