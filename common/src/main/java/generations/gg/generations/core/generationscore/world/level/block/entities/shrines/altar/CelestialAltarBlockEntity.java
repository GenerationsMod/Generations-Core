package generations.gg.generations.core.generationscore.world.level.block.entities.shrines.altar;

import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.world.level.block.entities.shrines.ShrineBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.shrines.CelestialAltarBlock;
import generations.gg.generations.core.generationscore.world.level.block.shrines.LunarShrineBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class CelestialAltarBlockEntity extends ShrineBlockEntity {
    public CelestialAltarBlockEntity(BlockPos pos, BlockState state) {
        super(GenerationsBlockEntities.CELESTIAL_ALTAR.get(), pos, state);
    }

    @Override
    public String getVariant() {
        return getBlockState().getValue(CelestialAltarBlock.IS_SUN) ? "sun" : "moon";
    }
}
