package generations.gg.generations.core.generationscore.world.level.block.entities;

import generations.gg.generations.core.generationscore.client.model.ModelContextProviders;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericRotatableModelBlock;

public class BallDisplayBlock extends GenericRotatableModelBlock<BallDisplayBlockEntity> {
    private final String variant;

    public BallDisplayBlock(Properties properties, String variant) {
        super(properties, GenerationsBlockEntities.BALL_DISPLAY, GenerationsBlockEntityModels.BALL_DISPLAY);
        this.variant = variant;
    }

    public String getVariant() {
        return variant;
    }
}
