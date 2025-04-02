package generations.gg.generations.core.generationscore.common.api.data

//import generations.gg.generations.core.generationscore.common.world.entity.StatueEntity.StatueInfo
import generations.gg.generations.core.generationscore.common.util.readNullableString
import generations.gg.generations.core.generationscore.common.util.readPokemonProperties
import generations.gg.generations.core.generationscore.common.util.writeNullableString
import generations.gg.generations.core.generationscore.common.util.writePokemonProperties
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.syncher.EntityDataSerializer
import net.minecraft.network.syncher.EntityDataSerializers

object GenerationsCoreEntityDataSerializers {

    @JvmField
    val NULLABLE_STRING = simple(FriendlyByteBuf::writeNullableString, FriendlyByteBuf::readNullableString)
    val PROPERTIES = simple(FriendlyByteBuf::writePokemonProperties, FriendlyByteBuf::readPokemonProperties)

    private fun <T> simple(encoder: (RegistryFriendlyByteBuf, T) -> Unit, decoder: (RegistryFriendlyByteBuf) -> T): EntityDataSerializer<T> = EntityDataSerializer.forValueType(StreamCodec.of(encoder, decoder))

    @JvmStatic
    fun init() {
        EntityDataSerializers.registerSerializer(PROPERTIES)
        EntityDataSerializers.registerSerializer(NULLABLE_STRING)
    }
}