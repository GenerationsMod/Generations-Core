package generations.gg.generations.core.generationscore.fabric.mixin;

import generations.gg.generations.core.generationscore.world.level.IPortalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.portal.PortalShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BaseFireBlock.class)
public abstract class BaseFireBlockMixin extends Block {
    @Shadow
    private static boolean inPortalDimension(Level world) {
        return false;
    }

    public BaseFireBlockMixin(Properties settings) {
        super(settings);
    }

    @Inject(at = @At(value = "HEAD"), method = "isPortal", cancellable = true)
    private static void isPortalRedirect(Level level, BlockPos blockPos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        if (!inPortalDimension(level)) {
            cir.setReturnValue(false);
        }
        BlockPos.MutableBlockPos mutableBlockPos = blockPos.mutable();
        boolean bl = false;
        for (Direction direction2 : Direction.values()) {
            var pos = mutableBlockPos.set(blockPos).move(direction2);
            var state = level.getBlockState(pos);
            if (!((IPortalBlock) (Object) state.getBlock()).isPortalFrame(state, level, pos)) continue;
            bl = true;
            break;
        }
        if (!bl) cir.setReturnValue(false);
        Direction.Axis axis = direction.getAxis().isHorizontal() ? direction.getCounterClockWise().getAxis() : Direction.Plane.HORIZONTAL.getRandomAxis(level.random);

        cir.setReturnValue(PortalShape.findEmptyPortalShape(level, blockPos, axis).isPresent());
    }
}