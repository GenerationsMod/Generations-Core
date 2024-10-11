package generations.gg.generations.core.generationscore.common.world.level.block.entities;

import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Vector3f;

public class CouchBlockEntity extends DyedVariantBlockEntity<CouchBlockEntity> implements ModelContextProviders.TintProvider {
    public CouchBlockEntity(BlockPos arg2, BlockState arg3) {
        super(GenerationsBlockEntities.COUCH.get(), arg2, arg3);
    }

    @Override
    public Vector3f getTint() {
        return DyedVariantBlockEntity.COLOR_MAP.get(getColor());
    }

    @Override
    public String getVariant() {
        return null;
    }
}
