package generations.gg.generations.core.generationscore.world.level.block;

import generations.gg.generations.core.generationscore.world.level.block.entities.PokeModHangingSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.jetbrains.annotations.NotNull;

public class GenerationsWallHangingSignBlock extends WallHangingSignBlock {
	public GenerationsWallHangingSignBlock(Properties arg, WoodType arg2) {
		super(arg, arg2);
	}

	@Override
	public BlockEntity newBlockEntity(@NotNull BlockPos arg, @NotNull BlockState arg2) {
		return new PokeModHangingSignBlockEntity(arg, arg2);
	}
}
