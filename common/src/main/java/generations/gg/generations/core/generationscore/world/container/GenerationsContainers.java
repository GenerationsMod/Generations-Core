package generations.gg.generations.core.generationscore.world.container;

import dev.architectury.event.events.common.PlayerEvent;
import dev.architectury.registry.menu.MenuRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.world.level.block.entities.CookingPotBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.MachineBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.RksMachineBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.function.Function;

public class GenerationsContainers {
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.MENU);
    public static final RegistrySupplier<MenuType<CookingPotContainer>> COOKING_POT = register("cooking_pot", CookingPotContainer::new, CookingPotBlockEntity.class);
    public static final RegistrySupplier<MenuType<GenericChestContainer>> GENERIC = CONTAINERS.register("generic", () -> MenuRegistry.ofExtended(GenericChestContainer::new));
    public static final RegistrySupplier<MenuType<MachineBlockContainer>> MACHINE_BLOCK = register("machine_block", MachineBlockContainer::new, MachineBlockEntity.class);
    public static final RegistrySupplier<MenuType<MelodyFluteContainer>> MELODY_FLUTE = CONTAINERS.register("melody_flute", () -> MenuRegistry.ofExtended(MelodyFluteContainer::new));

    public static final RegistrySupplier<MenuType<TrashCanContainer>> TRASHCAN = CONTAINERS.register("trashcan", () -> new MenuType<>(TrashCanContainer::new, FeatureFlagSet.of()));
    public static final RegistrySupplier<MenuType<WalkmonContainer>> WALKMON = CONTAINERS.register("walkmon", () -> MenuRegistry.ofExtended(WalkmonContainer::new));

    public static final RegistrySupplier<MenuType<RksMachineContainer>> RKS_MACHINE = register("rks_machine", RksMachineContainer::new, RksMachineBlockEntity.class);


    public static void init() {
        CONTAINERS.register();
        PlayerEvent.CLOSE_MENU.register(GenerationsContainers::onContainerClose);
    }

    private static void onContainerClose(Player player, AbstractContainerMenu container) {
        if (container instanceof MelodyFluteContainer melodyFluteContainer) melodyFluteContainer.save();
        if (container instanceof WalkmonContainer genericChestContainer) genericChestContainer.save();
    }

    public static <T extends AbstractContainerMenu, V extends BlockEntity> RegistrySupplier<MenuType<T>> register(String name, Function<CreationContext<V>, T> function, Class<V> clazz) {
        return CONTAINERS.register(name, () -> MenuRegistry.ofExtended((id, playerInventory, arg2) -> {
            var be = playerInventory.player.level().getBlockEntity(arg2.readBlockPos());
            if (clazz.isInstance(be)) return function.apply(new CreationContext<>(id, playerInventory, clazz.cast(be)));
            else return null;
        }));
    }

    public record CreationContext<V extends BlockEntity>(int id, Inventory playerInv, V blockEntity) {}
}
