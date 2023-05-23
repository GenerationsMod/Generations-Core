package generations.gg.generations.core.generationscore.mixin;

import generations.gg.generations.core.generationscore.tags.GenerationsItemTags;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsCookers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractFurnaceBlockEntity.class)
public abstract class AbstractFurnaceBlockEntityMixin {

	@Inject(method = "getTotalCookTime", at = @At("RETURN"), cancellable = true)
	private static void getTotalCookTime(Level level, AbstractFurnaceBlockEntity blockEntity, CallbackInfoReturnable<Integer> cir) {
		if (blockEntity instanceof GenerationsCookers && blockEntity.getItem(0).is(GenerationsItemTags.GENERATIONSITEMS))
			cir.setReturnValue((int) (cir.getReturnValueI() * (0.6666667f)));
	}
}
