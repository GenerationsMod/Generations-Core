
package generations.gg.generations.core.generationscore.client.screen.container;

import dev.architectury.registry.menu.MenuRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;

public class ModScreenHandlerTypes {
	public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.MENU);

	@SuppressWarnings("removal")
	public static void init() {
		MENU_TYPES.register();
	}
}
