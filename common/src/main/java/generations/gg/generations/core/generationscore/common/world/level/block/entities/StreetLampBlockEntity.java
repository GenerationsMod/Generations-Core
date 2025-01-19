package generations.gg.generations.core.generationscore.common.world.level.block.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Vector3f;

public class StreetLampBlockEntity extends DyedVariantBlockEntity<StreetLampBlockEntity> {
    public StreetLampBlockEntity(BlockPos arg2, BlockState arg3) {
        super(GenerationsBlockEntities.STREET_LAMP.get(), arg2, arg3);
    }

    @Override
    public Vector3f getTint() {
        return COLOR_MAP.get(getColor());
    }
}
