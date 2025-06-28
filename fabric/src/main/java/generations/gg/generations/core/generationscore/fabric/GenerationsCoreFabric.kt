package generations.gg.generations.core.generationscore.fabric

import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.GenerationsCore.init
import generations.gg.generations.core.generationscore.common.GenerationsCore.initBuiltinPacks
import generations.gg.generations.core.generationscore.common.GenerationsCore.onAnvilChange
import generations.gg.generations.core.generationscore.common.GenerationsImplementation
import generations.gg.generations.core.generationscore.common.api.events.general.InteractionEvents
import generations.gg.generations.core.generationscore.common.compat.ImpactorCompat
import generations.gg.generations.core.generationscore.common.compat.VanillaCompat
import generations.gg.generations.core.generationscore.common.config.ConfigLoader.setConfigDirectory
import generations.gg.generations.core.generationscore.common.generationsResource
import generations.gg.generations.core.generationscore.common.util.EntryRegister
import generations.gg.generations.core.generationscore.common.util.PlatformRegistry
import generations.gg.generations.core.generationscore.common.world.container.ExtendedMenuProvider
import generations.gg.generations.core.generationscore.common.world.feature.GenerationsConfiguredFeatures
import generations.gg.generations.core.generationscore.common.world.feature.GenerationsPlacedFeatures
import generations.gg.generations.core.generationscore.common.world.item.creativetab.GenerationsCreativeTabs
import generations.gg.generations.core.generationscore.fabric.AnvilEvents.AnvilChange
import generations.gg.generations.core.generationscore.fabric.networking.GenerationsFabricNetwork
import generations.gg.generations.core.generationscore.fabric.worldgen.GenerationsFabricBiomemodifiers
import io.netty.buffer.ByteBufUtil
import io.netty.buffer.Unpooled
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents.SyncDataPackContents
import net.fabricmc.fabric.api.event.player.UseBlockCallback
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder
import net.fabricmc.fabric.api.event.registry.RegistryAttribute
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents.ModifyEntriesAll
import net.fabricmc.fabric.api.loot.v3.LootTableEvents
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.fabricmc.fabric.api.resource.ResourcePackActivationType
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType
import net.fabricmc.loader.api.FabricLoader
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint
import net.minecraft.core.Holder
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.syncher.EntityDataSerializer
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer
import net.minecraft.server.packs.PackType
import net.minecraft.server.packs.resources.PreparableReloadListener
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.util.profiling.ProfilerFiller
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.MenuType
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Block
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import java.util.function.Function
import java.util.function.Supplier
import kotlin.jvm.optionals.getOrNull


/**
 * Fabric Main class and entry point for GenerationsCore.
 * @see ModInitializer
 *
 * @see GenerationsCore
 *
 *
 * @author Joseph T. McQuigg, WaterPicker
 */
object GenerationsCoreFabric : ModInitializer, GenerationsImplementation, PreLaunchEntrypoint {
    private var serverBacking: MinecraftServer? = null

    override fun onInitialize() {
        init(this)

        networkManager.registerMessages()
        networkManager.registerServerHandlers()

        VanillaCompat.setup()

        if (FabricLoader.getInstance().isModLoaded("impactor")) ImpactorCompat.init()

        ServerLifecycleEvents.SYNC_DATA_PACK_CONTENTS.register(SyncDataPackContents { player: ServerPlayer, isLogin: Boolean ->
            if (isLogin) GenerationsCore.dataProvider.sync(
                player
            )
        })

        ItemGroupEvents.MODIFY_ENTRIES_ALL.register(ModifyEntriesAll { creativeModeTab, helper ->
            val list = BuiltInRegistries.CREATIVE_MODE_TAB.getResourceKey(creativeModeTab).getOrNull()?.let(GenerationsCore.tabToItem::get) ?: return@ModifyEntriesAll
            list.forEach(helper::accept)
        })

        LootTableEvents.MODIFY.register{ key, builder, source, lookup ->
            GenerationsCore.processLootTable(key.location(), builder::withPool)
        }

        ServerLifecycleEvents.SERVER_STARTING.register { server ->
            serverBacking = server
        }
        ServerLifecycleEvents.SERVER_STOPPED.register {
            serverBacking = null
        }

        UseBlockCallback.EVENT.register({ player, level, hand, hitResult ->
            return@register InteractionEvents.fireRightClick(player, hand, hitResult.blockPos, hitResult.direction);
        })

        GenerationsCreativeTabs.register { provider ->
            Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, provider.key, FabricItemGroup.builder()
                .title(provider.displayName)
                .icon(provider.displayIconProvider)
                .displayItems(provider.entryCollector)
                .build())
        }

        AnvilEvents.ANVIL_CHANGE.register(AnvilChange { result, left, right, name, baseCost, player ->
            onAnvilChange(
                left, right, player,
                { output -> result.output = output },
                { cost -> result.cost = cost.toLong() },
                { materialCost -> result.materialCost = materialCost })
            false
        })

        initBuiltinPacks { type: PackType?, s: ResourceLocation?, s2: MutableComponent? ->
            ResourceManagerHelper.registerBuiltinResourcePack(
                s,
                FabricLoader.getInstance().getModContainer("generations_core").get(),
                s2,
                ResourcePackActivationType.DEFAULT_ENABLED
            )
        }

        GenerationsConfiguredFeatures.init()
        GenerationsPlacedFeatures.init()
        GenerationsFabricBiomemodifiers.generateOres()
        VanillaCompat.dispenserBehavior()
    }

    override fun registerStrippable(log: Holder<out Block>, stripped: Holder<out Block>) {
        StrippableBlockRegistry.register(log.value(), stripped.value())
    }

    override fun registerFlammable(blockIn: Block, encouragement: Int, flammability: Int) {
        FlammableBlockRegistry.getDefaultInstance().add(blockIn, encouragement, flammability)
    }

    override fun registerCompostables(block: Block, chance: Float) {
        CompostingChanceRegistry.INSTANCE.add(block, chance)
    }

    @SafeVarargs
    override fun create(
            name: String,
            icon: Supplier<ItemStack>,
            items: Array<out PlatformRegistry<out ItemLike>>,
        ): CreativeModeTab = FabricItemGroup.builder()
                .title(Component.translatable("itemGroup." + GenerationsCore.MOD_ID + "." + name))
                .icon(icon)
                .displayItems { _, context ->
                    for (item in items) item.all().forEach { itemEntry ->
                            context.accept(
                                itemEntry.asItem().defaultInstance
                            )
                        }
                }.build()

    override fun <T : Any> registerEntityDataSerializer(
        name: String,
        dataSerializer: EntityDataSerializer<T>,
    ) {
        return EntityDataSerializers.registerSerializer(dataSerializer)
    }

    override fun <T : AbstractContainerMenu> createExtendedMenu(constructor: (Int, Inventory, FriendlyByteBuf) -> T): () -> MenuType<T> {
        return { ExtendedScreenHandlerType({ id, inventory, data ->
            var buf = FriendlyByteBuf(Unpooled.wrappedBuffer(data))
            val menu = constructor.invoke(id, inventory, buf)
            buf.release()
            menu
        }, ByteBufCodecs.BYTE_ARRAY.mapStream(Function.identity())) }
    }

    override fun openExtendedMenu(serverPlayer: ServerPlayer, menuProvider: ExtendedMenuProvider) {
        serverPlayer.openMenu(object : ExtendedScreenHandlerFactory<ByteArray> {
            override fun getScreenOpeningData(player: ServerPlayer): ByteArray {
                val buf = PacketByteBufs.create()
                menuProvider.saveExtraData(buf)
                val bytes = ByteBufUtil.getBytes(buf)
                buf.release()
                return bytes
            }

            override fun getDisplayName(): Component {
                return menuProvider.displayName
            }

            override fun createMenu(i: Int, inventory: Inventory, player: Player): AbstractContainerMenu? {
                return menuProvider.createMenu(i, inventory, player)
            }
        })
    }

    override fun <T : Any> createRegistry(key: ResourceKey<Registry<T>>, sync: Boolean): Registry<T> = FabricRegistryBuilder.createSimple(key).attribute(RegistryAttribute.SYNCED).buildAndRegister()
    override val networkManager: GenerationsFabricNetwork = GenerationsFabricNetwork

    override fun registerResourceReloader(
        identifier: ResourceLocation,
        reloader: PreparableReloadListener,
        dependencies: Collection<ResourceLocation>,
    ) {
        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(GenerationsReloadListener(identifier, reloader, dependencies))
    }


    class GenerationsReloadListener(private val identifier: ResourceLocation, private val reloader: PreparableReloadListener, private val dependencies: Collection<ResourceLocation>) : IdentifiableResourceReloadListener {

        override fun reload(synchronizer: PreparableReloadListener.PreparationBarrier, manager: ResourceManager, prepareProfiler: ProfilerFiller, applyProfiler: ProfilerFiller, prepareExecutor: Executor, applyExecutor: Executor): CompletableFuture<Void> = this.reloader.reload(synchronizer, manager, prepareProfiler, applyProfiler, prepareExecutor, applyExecutor)

        override fun getFabricId(): ResourceLocation = this.identifier

        override fun getName(): String = this.reloader.name

        override fun getFabricDependencies(): MutableCollection<ResourceLocation> = this.dependencies.toMutableList()
    }

    override fun onPreLaunch() {
        setConfigDirectory(FabricLoader.getInstance().configDir)
    }

    override fun <T> entryRegister(registry: Registry<T>, resourceKey: ResourceKey<Registry<T>>): EntryRegister<T> {
        return FabricEntryRegister(registry)
    }

    class FabricEntryRegister<T>(val registry: Registry<T>): EntryRegister<T>() {
        override fun <V : T> holder(name: String, supplier: () -> V): Holder<V>  = Registry.registerForHolder(registry, name.generationsResource(), supplier.invoke()) as Holder<V>
    }
}