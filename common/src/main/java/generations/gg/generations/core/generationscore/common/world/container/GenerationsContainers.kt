package generations.gg.generations.core.generationscore.common.world.container

import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.util.PlatformRegistry
import net.minecraft.core.Holder
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.flag.FeatureFlags
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.MenuType

object GenerationsContainers: PlatformRegistry<MenuType<*>>(Registries.MENU, BuiltInRegistries.MENU) {

    @JvmField
    val COOKING_POT = register("cooking_pot", ::CookingPotContainer)
//    val MACHINE_BLOCK = register("machine_block", ::MachineBlockContainer, MachineBlockEntity::class.java)
    val MELODY_FLUTE = register("melody_flute", ::MelodyFluteContainer)
    val TRASHCAN = register("trashcan", ::TrashCanContainer)
    val RKS_MACHINE = register("rks_machine", ::RksMachineContainer)
    val GENERIC = registerExtended("generic", GenericChestContainer.Companion::fromBuffer)


    fun <T: AbstractContainerMenu> registerExtended(name: String, constructor: (Int, Inventory, FriendlyByteBuf) -> T): Holder<MenuType<T>> = create(name, GenerationsCore.implementation.createExtendedMenu<T>(constructor))

    override fun init() {
        super.init()

//        PlayerEvent.CLOSE_MENU.register(PlayerEvent.CloseMenu { player: Player, container: AbstractContainerMenu ->
//            onContainerClose(
//                player, container
//            )
//        })
    }

    private fun onContainerClose(player: Player, container: AbstractContainerMenu) {
//        if(container instanceof GenericChestContainer genericChestContainer) {
//            genericChestContainer.getContainer().update();
//        }

//        if (container instanceof WalkmonContainer genericChestContainer) genericChestContainer.save(player);
//        if (container instanceof CalyrexSteedContainer genericChestContainer) genericChestContainer.save(player);

//        if (container is RksMachineContainer) container.close()
    }

    fun <T: AbstractContainerMenu> register(name: String, constructor: (Int, Inventory) -> T): Holder<MenuType<T>> = create<MenuType<T>>(name) { MenuType(constructor::invoke, FeatureFlags.VANILLA_SET) }

//    fun <T : AbstractContainerMenu, V : BlockEntity> register(
//        name: String,
//        function: Function<CreationContext<V>, T>,
//        clazz: Class<V>
//    ): MenuType<T> {
//        return create(name.generationsResource(), GenerationsCore.implementation.createExtendedMenu { id: Int, playerInventory: Inventory, arg2: FriendlyByteBuf ->
//            val be = playerInventory.player.level().getBlockEntity(arg2.readBlockPos())
//            if (clazz.isInstance(be)) return@createExtendedMenu function.apply(
//                CreationContext<V>(
//                    id,
//                    playerInventory,
//                    clazz.cast(be)
//                )
//            )
//            else return@createExtendedMenu null
//        })
//    }


//    @JvmRecord
//    data class CreationContext<V : BlockEntity?>(val id: Int, val playerInv: Inventory, val blockEntity: V)
}
