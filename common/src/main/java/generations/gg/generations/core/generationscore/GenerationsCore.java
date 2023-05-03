package generations.gg.generations.core.generationscore;

import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsBlocks;

public class GenerationsCore
{
	public static final String MOD_ID = "generations-core";

	public static void init() {
		GenerationsItems.init();
		GenerationsBlocks.init();
	}
}