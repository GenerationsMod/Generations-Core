package generations.gg.generations.core.generationscore.world.level.block.entities;

import generations.gg.generations.core.generationscore.client.model.ModelContextProviders;
import generations.gg.generations.core.generationscore.world.level.block.PokeLootBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class PokeLootBlockEntity extends ModelProvidingBlockEntity implements ModelContextProviders.VariantProvider {
    public PokeLootBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(GenerationsBlockEntities.POKE_LOOT.get(), pPos, pBlockState);
    }

    @Override
    public String getVariant() {
        return getBlockState().getBlock() instanceof PokeLootBlock loot ? loot.getType() : "poke";
    }
}
