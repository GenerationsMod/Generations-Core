package generations.gg.generations.core.generationscore.world.level.block.entities.generic;

import generations.gg.generations.core.generationscore.world.level.block.entities.ModelProvidingBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class GenericModelProvidingBlockEntity extends ModelProvidingBlockEntity {
    public GenericModelProvidingBlockEntity(BlockPos arg2, BlockState arg3) {
        super(GenerationsBlockEntities.GENERIC_MODEL_PROVIDING.get(), arg2, arg3);
    }
}
