package generations.gg.generations.core.generationscore.world.level.block.entities.shrines;

import generations.gg.generations.core.generationscore.client.model.ModelContextProviders;
import generations.gg.generations.core.generationscore.world.level.block.entities.ModelProvidingBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.MutableBlockEntityType;
import generations.gg.generations.core.generationscore.world.level.block.shrines.ShrineBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class ShrineBlockEntity extends ModelProvidingBlockEntity implements ModelContextProviders.VariantProvider {
    private boolean active = false;
    public ShrineBlockEntity(MutableBlockEntityType<? extends ModelProvidingBlockEntity> arg, BlockPos arg2, BlockState arg3) {
        super(arg, arg2, arg3);
    }

    public boolean isActive() {
        return active;
    }

    public void toggleActive() {
        active = !active;
    }

    @Override
    public String getVariant() {
        return getBlockState().getBlock() instanceof ShrineBlock<?> shrine ? shrine.getActiveVariant(isActive()) : null;
    }
}
