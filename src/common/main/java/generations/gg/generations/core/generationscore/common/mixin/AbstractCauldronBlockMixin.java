package generations.gg.generations.core.generationscore.common.mixin;

import generations.gg.generations.core.generationscore.common.world.level.block.PointedChargeDripstoneBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author JT122406
 */
@Mixin(AbstractCauldronBlock.class)
public abstract class AbstractCauldronBlockMixin {

	@Inject(method = "tick", at = @At("HEAD"))
	public void tick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random, CallbackInfo cir) {
		BlockPos blockPos = PointedDripstoneBlock.findStalactiteTipAboveCauldron(level, pos);
		if (blockPos == null) {
			blockPos = PointedChargeDripstoneBlock.findStalactiteTipAboveCauldron(level, pos);
			if (blockPos != null) {
				Fluid fluid = PointedChargeDripstoneBlock.getCauldronFillFluidType(level, blockPos);
				if (fluid != Fluids.EMPTY && this.canReceiveStalactiteDrip(fluid))
					this.receiveStalactiteDrip(state, level, pos, fluid);
				return;
			}
			return;
		}
		Fluid fluid = PointedDripstoneBlock.getCauldronFillFluidType(level, blockPos);
		if (fluid != Fluids.EMPTY && this.canReceiveStalactiteDrip(fluid))
			this.receiveStalactiteDrip(state, level, pos, fluid);
	}

	@Shadow
	public boolean canReceiveStalactiteDrip(Fluid fluid) {
		return false;
	}

	@Shadow
	protected void receiveStalactiteDrip(BlockState state, Level level, BlockPos pos, Fluid fluid) {
	}
}
