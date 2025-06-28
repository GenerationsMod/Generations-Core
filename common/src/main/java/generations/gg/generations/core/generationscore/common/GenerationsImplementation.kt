package generations.gg.generations.core.generationscore.common

import com.cobblemon.mod.common.NetworkManager
import generations.gg.generations.core.generationscore.common.util.EntryRegister
import generations.gg.generations.core.generationscore.common.util.PlatformRegistry
import generations.gg.generations.core.generationscore.common.world.container.ExtendedMenuProvider
import net.minecraft.core.Holder
import net.minecraft.core.Registry
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.syncher.EntityDataSerializer
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraft.server.packs.resources.PreparableReloadListener
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.MenuType
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Block
import java.util.function.Supplier

interface GenerationsImplementation {

    fun registerResourceReloader(
        identifier: ResourceLocation,
        reloader: PreparableReloadListener,
        dependencies: Collection<ResourceLocation>,
    )

    /**
     * Register a block as Strippable.
     * @param log The log block of wood
     * @param stripped The stripped log block of wood
     */
    fun registerStrippable(log: Holder<out Block>, stripped: Holder<out Block>)

    /**
     * Register a block as flammable.
     * @param blockIn The block to register
     * @param encouragement How much more likely the block is to catch fire compared to vanilla
     * @param flammability How much more likely the block is to spread fire compared to vanilla
     */
    fun registerFlammable(blockIn: Block, encouragement: Int, flammability: Int)

    /**
     * Register a block as compostable.
     * @param block The block to register
     * @param chance The chance of the block to compost
     */
    fun registerCompostables(block: Block, chance: Float)

    fun create(
        name: String,
        o: Supplier<ItemStack>,
        deferredRegister: Array<out PlatformRegistry<out ItemLike>>,
    ): CreativeModeTab

    fun <T: Any> registerEntityDataSerializer(name: String, dataSerializer: EntityDataSerializer<T>)
    fun <T : AbstractContainerMenu> createExtendedMenu(constructor: (Int, Inventory, FriendlyByteBuf) -> T): () -> MenuType<T>
    fun openExtendedMenu(serverPlayer: ServerPlayer, menuProvider: ExtendedMenuProvider)

    fun <T: Any> createRegistry(key: ResourceKey<Registry<T>>, sync: Boolean): Registry<T>

    val networkManager: NetworkManager

    fun <T> entryRegister(registry: Registry<T>, resourceKey: ResourceKey<Registry<T>>): EntryRegister<T>
}
