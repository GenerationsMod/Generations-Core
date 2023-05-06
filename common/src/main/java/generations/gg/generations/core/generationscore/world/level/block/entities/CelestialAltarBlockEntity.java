package generations.gg.generations.core.generationscore.world.level.block.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class CelestialAltarBlockEntity extends ShrineBlockEntity {
    public CelestialAltarBlockEntity(BlockPos pos, BlockState state) {
        super(GenerationsBlockEntities.CELESTIAL_ALTAR.get(), pos, state);
    }
}
