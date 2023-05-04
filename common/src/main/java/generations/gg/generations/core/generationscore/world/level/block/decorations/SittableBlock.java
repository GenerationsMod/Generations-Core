package generations.gg.generations.core.generationscore.world.level.block.decorations;

import com.pokemod.pokemod.world.entity.block.SittableEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public interface SittableBlock {

    default @NotNull InteractionResult use(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        if (!level.isClientSide && !player.isShiftKeyDown()) {
            return SittableEntity.mount(level, pos, getOffset(), player);
        }

        return InteractionResult.PASS;
    }

    double getOffset();
}
