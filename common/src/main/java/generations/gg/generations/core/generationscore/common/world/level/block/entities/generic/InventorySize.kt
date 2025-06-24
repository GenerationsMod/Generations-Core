package generations.gg.generations.core.generationscore.common.world.level.block.entities.generic

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import generations.gg.generations.core.generationscore.common.util.StreamCodecs
import generations.gg.generations.core.generationscore.common.util.StreamCodecs.asRegistryFriendly
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec

data class InventorySize(val width: Int, val height: Int) {

    companion object {
        val DEFAULT = InventorySize(9, 1)
        val CODEC = RecordCodecBuilder.create { it.group(Codec.INT.fieldOf("width").forGetter(InventorySize::width), Codec.INT.fieldOf("height").forGetter(InventorySize::height)).apply(it, ::InventorySize) }
        val STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.INT, InventorySize::width, ByteBufCodecs.INT, InventorySize::height, ::InventorySize).asRegistryFriendly()
    }
}
