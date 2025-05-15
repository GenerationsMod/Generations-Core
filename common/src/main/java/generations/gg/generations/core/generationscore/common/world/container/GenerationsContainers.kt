package generations.gg.generations.core.generationscore.common.world.container

import dev.architectury.event.events.common.PlayerEvent
import dev.architectury.registry.menu.MenuRegistry
import dev.architectury.registry.registries.DeferredRegister
import dev.architectury.registry.registries.RegistrySupplier
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.world.level.block.entities.MachineBlockEntity
import net.minecraft.core.registries.Registries
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.flag.FeatureFlagSet
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.MenuType
import net.minecraft.world.level.block.entity.BlockEntity
import java.util.function.Function

object GenerationsContainers {
    val CONTAINERS: DeferredRegister<MenuType<*>> = DeferredRegister.create(GenerationsCore.MOD_ID, Registries.MENU)
    @JvmField
    val COOKING_POT: RegistrySupplier<MenuType<CookingPotContainer>> = CONTAINERS.register(
        "cooking_pot"
    ) {
        MenuType(
            { id: Int, playerInventory: Inventory? ->
                CookingPotContainer(
                    id,
                    playerInventory
                )
            }, FeatureFlagSet.of()
        )
    }
    @JvmField
    val GENERIC: RegistrySupplier<MenuType<GenericChestContainer<*>>> = CONTAINERS.register(
        "generic"
    ) {
        MenuRegistry.ofExtended(
            MenuRegistry.ExtendedMenuTypeFactory { containerId: Int, playerInventory: Inventory?, buf: FriendlyByteBuf? ->
                GenericChestContainer(
                    containerId,
                    playerInventory!!, buf!!
                )
            })
    }
    @JvmField
    val MACHINE_BLOCK: RegistrySupplier<MenuType<MachineBlockContainer>> = register(
        "machine_block",
        Function<CreationContext<MachineBlockEntity?>?, MachineBlockContainer> { ctx: CreationContext<MachineBlockEntity?>? ->
            MachineBlockContainer(
                ctx!!
            )
        },
        MachineBlockEntity::class.java
    )
    @JvmField
    val MELODY_FLUTE: RegistrySupplier<MenuType<MelodyFluteContainer>> = CONTAINERS.register(
        "melody_flute"
    ) {
        MenuType(
            { id: Int, playerInventory: Inventory? ->
                MelodyFluteContainer(
                    id,
                    playerInventory!!
                )
            }, FeatureFlagSet.of()
        )
    }

    @JvmField
    val TRASHCAN: RegistrySupplier<MenuType<TrashCanContainer>> = CONTAINERS.register(
        "trashcan"
    ) {
        MenuType(
            { id: Int, arg: Inventory? -> TrashCanContainer(id, arg) },
            FeatureFlagSet.of()
        )
    }

    //    public static final RegistrySupplier<MenuType<WalkmonContainer>> WALKMON = CONTAINERS.register("walkmon", () -> MenuRegistry.ofExtended(WalkmonContainer::new));
    //    public static final RegistrySupplier<MenuType<CalyrexSteedContainer>> CALYREX_STEED = CONTAINERS.register("calyrex_steed", () -> MenuRegistry.ofExtended(CalyrexSteedContainer::new));
    @JvmField
    val RKS_MACHINE: RegistrySupplier<MenuType<RksMachineContainer>> = CONTAINERS.register(
        "rks_machine"
    ) {
        MenuType(
            { id: Int, playerInventory: Inventory? ->
                RksMachineContainer(
                    id,
                    playerInventory!!
                )
            }, FeatureFlagSet.of()
        )
    }


    fun init() {
        CONTAINERS.register()
        PlayerEvent.CLOSE_MENU.register(PlayerEvent.CloseMenu { obj: Player?, player: AbstractContainerMenu? ->
            onContainerClose(
                player
            )
        })
    }

    private fun onContainerClose(player: Player, container: AbstractContainerMenu) {
//        if(container instanceof GenericChestContainer genericChestContainer) {
//            genericChestContainer.getContainer().update();
//        }

//        if (container instanceof WalkmonContainer genericChestContainer) genericChestContainer.save(player);
//        if (container instanceof CalyrexSteedContainer genericChestContainer) genericChestContainer.save(player);

        if (container is RksMachineContainer) container.close()
    }

    fun <T : AbstractContainerMenu?, V : BlockEntity?> register(
        name: String?,
        function: Function<CreationContext<V>?, T>,
        clazz: Class<V>
    ): RegistrySupplier<MenuType<T>> {
        return CONTAINERS.register(
            name
        ) {
            MenuRegistry.ofExtended { id: Int, playerInventory: Inventory, arg2: FriendlyByteBuf ->
                val be = playerInventory.player.level().getBlockEntity(arg2.readBlockPos())
                if (clazz.isInstance(be)) return@ofExtended function.apply(
                    CreationContext<V>(
                        id,
                        playerInventory,
                        clazz.cast(be)
                    )
                )
                else return@ofExtended null
            }
        }
    }

    @JvmRecord
    data class CreationContext<V : BlockEntity?>(val id: Int, val playerInv: Inventory, val blockEntity: V)
}
