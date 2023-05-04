package generations.gg.generations.core.generationscore.world.level.block.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class PokeModHangingSignBlockEntity extends HangingSignBlockEntity {
	public PokeModHangingSignBlockEntity(BlockPos arg, BlockState arg2) {
		super(arg, arg2);
	}

	@Override
	public @NotNull BlockEntityType<?> getType() {
		return PokeModBlockEntities.HANGING_SIGN_BLOCK_ENTITIES.get();
	}
}