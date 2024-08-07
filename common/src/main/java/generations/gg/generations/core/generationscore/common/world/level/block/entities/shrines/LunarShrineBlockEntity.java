package generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines;

import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.common.world.level.block.shrines.LunarShrineBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;

public class LunarShrineBlockEntity extends ShrineBlockEntity {
    public LunarShrineBlockEntity(BlockPos pos, BlockState state) {
        super(GenerationsBlockEntities.LUNAR_SHRINE.get(), pos, state);
    }

    @Override
    public ResourceLocation getModel() {
        return getBlockState().getValue(LunarShrineBlock.IS_LIGHT) ? GenerationsBlockEntityModels.LIGHT_MODEL : GenerationsBlockEntityModels.DARK_MODEL;
    }
}
