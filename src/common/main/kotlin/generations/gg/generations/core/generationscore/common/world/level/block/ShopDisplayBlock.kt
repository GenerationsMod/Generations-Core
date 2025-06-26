package generations.gg.generations.core.generationscore.common.world.level.block

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders.VariantProvider
import generations.gg.generations.core.generationscore.common.util.Codecs
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock

class ShopDisplayBlock(properties: Properties, width: Int, height: Int, length: Int, private val variant: String) :
    GenericRotatableModelBlock(
        properties,
        model = GenerationsBlockEntityModels.SHOP,
        width = width,
        height = height,
        length = length
    ), VariantProvider {

    override val blockEntityType
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
