package generations.gg.generations.core.generationscore.common.compat;

import generations.gg.generations.core.generationscore.common.world.entity.GenerationsBoatEntity;
import generations.gg.generations.core.generationscore.common.world.entity.GenerationsChestBoatEntity;
import generations.gg.generations.core.generationscore.common.world.entity.GenerationsEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import org.jetbrains.annotations.NotNull;

public class BoatDispenseItemBehavior extends DefaultDispenseItemBehavior {
	private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();
	private final GenerationsBoatEntity.Type type;
	private final boolean isChestBoat;

	public BoatDispenseItemBehavior(GenerationsBoatEntity.Type type) {
		this(type, false);
	}

	public BoatDispenseItemBehavior(GenerationsBoatEntity.Type type, boolean isChestBoat) {
		this.type = type;
		this.isChestBoat = isChestBoat;
	}

	@Override
	public @NotNull ItemStack execute(net.minecraft.core.dispenser.BlockSource source, @NotNull ItemStack stack) {
		Direction direction = source.state().getValue(DispenserBlock.FACING);
		Level level = source.level();
		double d = 0.5625 + (double) GenerationsEntities.BOAT_ENTITY.get().getWidth() / 2.0;
		double e = source.pos().getX() + (double)direction.getStepX() * d;
		double f = source.pos().getY() + (double)((float)direction.getStepY() * 1.125F);
		double g = source.pos().getZ() + (double)direction.getStepZ() * d;
		BlockPos blockPos = source.pos().relative(direction);
		double h;
		if (level.getFluidState(blockPos).is(FluidTags.WATER))
			h = 1.0;
		else {
			if (!level.getBlockState(blockPos).isAir() || !level.getFluidState(blockPos.below()).is(FluidTags.WATER))
				return this.defaultDispenseItemBehavior.dispense(source, stack);

			h = 0.0;
		}

		if (this.isChestBoat) {
			GenerationsChestBoatEntity entity = new GenerationsChestBoatEntity(level, e, f + h, g);
			entity.setBoatType(this.type);
			entity.setYRot(direction.toYRot());
			level.addFreshEntity(entity);
		} else {
			GenerationsBoatEntity entity = new GenerationsBoatEntity(level, e, f + h, g);
			entity.setBoatType(this.type);
			entity.setYRot(direction.toYRot());
			level.addFreshEntity(entity);
		}
		stack.shrink(1);
		return stack;
	}
}
