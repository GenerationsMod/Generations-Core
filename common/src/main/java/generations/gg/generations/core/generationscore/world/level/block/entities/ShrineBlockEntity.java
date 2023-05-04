package generations.gg.generations.core.generationscore.world.level.block.entities;

import com.pokemod.pokemod.client.model.ModelContextProviders;
import com.pokemod.pokemod.world.level.block.shrines.ShrineBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class ShrineBlockEntity extends ModelProvidingBlockEntity implements ModelContextProviders.VariantProvider {
    private boolean active = false;
    public ShrineBlockEntity(BlockEntityType<? extends ModelProvidingBlockEntity> arg, BlockPos arg2, BlockState arg3) {
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
        return getBlockState().getBlock() instanceof ShrineBlock<?> shrine ? shrine.getActiveVariant(isActive()) : "inactive";
    }
}
