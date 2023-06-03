package generations.gg.generations.core.generationscore.world.level.block.entities;

import generations.gg.generations.core.generationscore.client.model.ModelContextProviders;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BallDisplayBlockEntity extends ModelProvidingBlockEntity implements ModelContextProviders.VariantProvider {
    public BallDisplayBlockEntity(BlockPos pos, BlockState state) {
        super(GenerationsBlockEntities.BALL_DISPLAY.get(), pos, state);
    }

    @Override
    public String getVariant() {
        return getBlockState().getBlock() instanceof BallDisplayBlock ballDisplayBlock ? ballDisplayBlock.getVariant() : "empty";
    }
}
