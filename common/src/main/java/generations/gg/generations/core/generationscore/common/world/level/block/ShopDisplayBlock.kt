package generations.gg.generations.core.generationscore.common.world.level.block

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders.VariantProvider
import generations.gg.generations.core.generationscore.common.util.Codecs
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.GenericModelProvidingBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock
import net.minecraft.world.level.block.BaseEntityBlock
import net.minecraft.world.level.block.entity.BlockEntityType
import org.bson.codecs.StringCodec

class ShopDisplayBlock(properties: Properties, width: Int, height: Int, length: Int, private val variant: String) :
    GenericRotatableModelBlock<GenericModelProvidingBlockEntity>(
        properties,
        model = GenerationsBlockEntityModels.SHOP,
        width = width,
        height = height,
        length = length
    ), VariantProvider {

    override val blockEntityType: BlockEntityType<GenericModelProvidingBlockEntity>
        get() = GenerationsBlockEntities.GENERIC_MODEL_PROVIDING

    override fun getVariant(): String? {
        return variant
    }

    override fun codec(): MapCodec<ShopDisplayBlock> = CODEC

    data class Type(val width: Int, val height: Int, val length: Int)

    companion object {
        val CODEC: MapCodec<ShopDisplayBlock> = Codecs.mapCodec { group(
            propertiesCodec(),
            Codec.INT.fieldOf("width").forGetter(ShopDisplayBlock::width),
            Codec.INT.fieldOf("height").forGetter(ShopDisplayBlock::height),
            Codec.INT.fieldOf("length").forGetter(ShopDisplayBlock::length),
            Codec.STRING.fieldOf("variant").forGetter(ShopDisplayBlock::variant)).apply(this, ::ShopDisplayBlock)
        }
    }
}
