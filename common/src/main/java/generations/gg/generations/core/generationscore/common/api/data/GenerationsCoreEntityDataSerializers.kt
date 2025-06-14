package generations.gg.generations.core.generationscore.common.api.data

//import generations.gg.generations.core.generationscore.common.world.entity.StatueEntity.StatueInfo
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.util.StreamCodecs.asRegistryFriendly
import generations.gg.generations.core.generationscore.common.util.StreamCodecs.optional
import generations.gg.generations.core.generationscore.common.util.readNullableString
import generations.gg.generations.core.generationscore.common.util.readPokemonProperties
import generations.gg.generations.core.generationscore.common.util.writeNullableString
import generations.gg.generations.core.generationscore.common.util.writePokemonProperties
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.network.syncher.EntityDataSerializer
import net.minecraft.network.syncher.EntityDataSerializers

object GenerationsCoreEntityDataSerializers {
    val NULLABLE_STRING = EntityDataSerializer.forValueType(ByteBufCodecs.STRING_UTF8.optional().asRegistryFriendly())
    val PROPERTIES = EntityDataSerializer.forValueType(StreamCodec.of(FriendlyByteBuf::writePokemonProperties, FriendlyByteBuf::readPokemonProperties))

    @JvmStatic fun init() {
        GenerationsCore.implementation.registerEntityDataSerializer("properties", PROPERTIES)
        GenerationsCore.implementation.registerEntityDataSerializer("nullable_string", NULLABLE_STRING)
    }
}