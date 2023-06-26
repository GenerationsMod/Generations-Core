package generations.gg.generations.core.generationscore.fabric.mixin;

import generations.gg.generations.core.generationscore.world.level.IPortalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockBehaviour.class)
public class BlockMixin implements IPortalBlock {
    @Override
    public boolean isPortalFrame(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(Blocks.OBSIDIAN);
    }
}
