
package generations.gg.generations.core.generationscore.common.client.screen.container;

import dev.architectury.registry.registries.DeferredRegister;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;

public class ModScreenHandlerTypes {
	public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.MENU);

	@SuppressWarnings("removal")
	public static void init() {
		MENU_TYPES.register();
	}
}
