package generations.gg.generations.core.generationscore.common.world.level.block.decorations;

import generations.gg.generations.core.generationscore.common.client.model.ModelContextProviders;
import generations.gg.generations.core.generationscore.common.world.entity.block.SittableEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public interface SittableBlock {

    default InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide && !player.isShiftKeyDown()) {
            return SittableEntity.mount(level, pos, getOffset(), player, getYaw(state));
        }

        return InteractionResult.PASS;
    }

    double getOffset();

    float getYaw(BlockState state);
}
