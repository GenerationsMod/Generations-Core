package generations.gg.generations.core.generationscore.common.world.level.block;

import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.GenericModelProvidingBlockEntity;
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericRotatableModelBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ShopDisplayBlock extends GenericRotatableModelBlock<GenericModelProvidingBlockEntity> implements ModelContextProviders.VariantProvider {
    private final String variant;

    public ShopDisplayBlock(BlockBehaviour.Properties properties, int width, int height, int length, String variant) {
        super(properties, GenerationsBlockEntities.GENERIC_MODEL_PROVIDING, GenerationsBlockEntityModels.SHOP, width, height, length);

        this.variant = variant;
    }

    @Override
    public String getVariant() {
        return variant;
    }


    public record Type(int width, int height, int length) {

    }
}
