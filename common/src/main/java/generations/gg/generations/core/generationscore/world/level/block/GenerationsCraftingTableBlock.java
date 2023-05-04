package generations.gg.generations.core.generationscore.world.level.block;

import generations.gg.generations.core.generationscore.world.container.PokeModWorkBenchContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class GenerationsCraftingTableBlock extends CraftingTableBlock {
	private static final Component CONTAINER_TITLE = Component.translatable("container.crafting");

	public GenerationsCraftingTableBlock(Properties arg) {
		super(arg);
	}

	@Override
	public MenuProvider getMenuProvider(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos) {
		return new SimpleMenuProvider((i, arg3, arg4) -> new PokeModWorkBenchContainer(i, arg3, ContainerLevelAccess.create(level, pos), this), CONTAINER_TITLE);
	}
}
