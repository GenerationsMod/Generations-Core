package generations.gg.generations.core.generationscore.forge

import com.cobblemon.mod.common.NetworkManager
import com.google.common.collect.ImmutableMap
import com.mojang.datafixers.util.Pair
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.GenerationsCore.init
import generations.gg.generations.core.generationscore.common.GenerationsCore.onAnvilChange
import generations.gg.generations.core.generationscore.common.GenerationsImplementation
import generations.gg.generations.core.generationscore.common.api.events.general.EntityEvents
import generations.gg.generations.core.generationscore.common.api.events.general.InteractionEvents
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.compat.ImpactorCompat
import generations.gg.generations.core.generationscore.common.compat.VanillaCompat
import generations.gg.generations.core.generationscore.common.config.ConfigLoader.setConfigDirectory
import generations.gg.generations.core.generationscore.common.util.PlatformRegistry
import generations.gg.generations.core.generationscore.common.util.extensions.supplier
import generations.gg.generations.core.generationscore.common.world.container.ExtendedMenuProvider
import generations.gg.generations.core.generationscore.forge.networking.GenerationsNeoForgeNetworkManager
import net.minecraft.client.Minecraft
import net.minecraft.core.Registry
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.network.chat.Component
import net.minecraft.network.syncher.EntityDataSerializer
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraft.server.packs.PackType
import net.minecraft.server.packs.resources.PreparableReloadListener
import net.minecraft.server.packs.resources.ReloadableResourceManager
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.MenuType
import net.minecraft.world.item.AxeItem
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.*
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.ModList
import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent
import net.neoforged.fml.loading.FMLPaths
import net.neoforged.neoforge.common.NeoForge
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension
import net.neoforged.neoforge.common.util.TriState
import net.neoforged.neoforge.event.*
import net.neoforged.neoforge.event.entity.living.LivingEvent.LivingJumpEvent
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent.EntityInteract
import net.neoforged.neoforge.registries.NeoForgeRegistries
import net.neoforged.neoforge.registries.NewRegistryEvent
import net.neoforged.neoforge.registries.RegisterEvent
import net.neoforged.neoforge.registries.RegistryBuilder
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS
import java.util.*
import java.util.function.Consumer
import java.util.function.Supplier

fun generations.gg.generations.core.generationscore.common.api.events.TriState.asNeoForge(): TriState = when(this) {
    generations.gg.generations.core.generationscore.common.api.events.TriState.TRUE -> TriState.TRUE
    generations.gg.generations.core.generationscore.common.api.events.TriState.FALSE -> TriState.FALSE
    generations.gg.generations.core.generationscore.common.api.events.TriState.INDETERMINATE -> TriState.DEFAULT
}

/**
 * Forge Main class for GenerationsCore.
 * @see Mod
 *
 * @see GenerationsCore
 *
 * @author Joseph T. McQuigg, WaterPicker
 */
@Mod(GenerationsCore.MOD_ID)
class GenerationsCoreForge(MOD_BUS: IEventBus) : GenerationsImplementation {
    private val registries = mutableListOf<Registry<*>>()
    private val reloadableResources: MutableList<PreparableReloadListener> = ArrayList()
    private val packs: Map<PackType, List<Pair<ResourceLocation, Component>>> = EnumMap(net.minecraft.server.packs.PackType::class.java)

    private val ENTITY_DATA_SERIALIZER_REGISTER = net.neoforged.neoforge.registries.DeferredRegister.create(NeoForgeRegistries.ENTITY_DATA_SERIALIZERS, GenerationsCore.MOD_ID)

    /**
     * Sets up Forge side of the mod.
     */
    init {
        setConfigDirectory(FMLPaths.CONFIGDIR.get())

        with(MOD_BUS) {
            ENTITY_DATA_SERIALIZER_REGISTER.register(MOD_BUS)
            addListener(::onInitialize)
            addListener(::postInit)

            addListener(GenerationsNeoForgeNetworkManager::registerMessages)

            addListener<NewRegistryEvent> { event ->
                registries.forEach { event.register(it) }
            }
        }

        init(this)

        with(NeoForge.EVENT_BUS) {
            addListener(::onDataPackSync)
            addListener<BuildCreativeModeTabContentsEvent> {
                GenerationsCore.tabToItem.get(it.tabKey).forEach(it::accept)
            }
            addListener { event: LootTableLoadEvent ->
                GenerationsCore.processLootTable(event.name) { event.table.addPool(it.build()) }
            }

            addListener { event: AnvilUpdateEvent ->
                onAnvilChange(event.left, event.right, event.player,
                    { output: ItemStack ->
                        event.output =
                            output
                    },
                    { cost: Int -> event.cost = cost.toLong() },
                    { materialCost: Int -> event.materialCost = materialCost })
            }

            addListener<LivingJumpEvent> { event -> EntityEvents.jump(event.entity) }
            addListener<PlayerInteractEvent.RightClickBlock> {
                val result = InteractionEvents.fireRightClick(it.entity, it.hand, it.pos, it.face)

                if (result != InteractionResult.PASS) {
                    it.isCanceled = true
                    it.cancellationResult = result
                    it.useItem = TriState.FALSE
                    it.useBlock = TriState.FALSE
                }
            }

            addListener<EntityInteract> { event ->
                val result = InteractionEvents.fireEntityInteract(event.entity, event.target, event.hand)

                if (result != InteractionResult.PASS) {
                    event.isCanceled = true
                    event.cancellationResult = result
                }
            }

            addListener(::onReload)
        }
        if (ModList.get().isLoaded("impactor")) ImpactorCompat.init()
    }

    override fun <T: Any> register(register: PlatformRegistry<T>) {
        with(MOD_BUS) {
            this.addListener<RegisterEvent> { event ->
                if(event.registryKey.equals(register.resourceKey)) {
                    event.register(register.resourceKey) { helper ->
                        register.init(helper::register)
                    }
                }
            }
        }
    }

    override fun registerStrippable(log: Block, stripped: Block) {
        require(
            log.defaultBlockState().hasProperty(RotatedPillarBlock.AXIS)
        ) { "Input block is missing required 'AXIS' property!" }
        require(
            stripped.defaultBlockState().hasProperty(RotatedPillarBlock.AXIS)
        ) { "Result block is missing required 'AXIS' property!" }
        if (AxeItem.STRIPPABLES is ImmutableMap<*, *>) AxeItem.STRIPPABLES = HashMap(AxeItem.STRIPPABLES)

        AxeItem.STRIPPABLES[log] = stripped
    }

    override fun registerFlammable(blockIn: Block, encouragement: Int, flammability: Int) {
        (Blocks.FIRE as FireBlock).setFlammable(blockIn, encouragement, flammability)
    }

    override fun registerCompostables(block: Block, chance: Float) {
        ComposterBlock.COMPOSTABLES.put(block, chance)
    }

    @SafeVarargs
    override fun create(
        name: String,
        icon: Supplier<ItemStack>,
        items: Array<out PlatformRegistry<out ItemLike>>,
    ): CreativeModeTab = CreativeModeTab.builder()
                .title(Component.translatable("itemGroup." + GenerationsCore.MOD_ID + "." + name))
                .icon(icon)
            .displayItems { _, context ->
                for (item in items) item.all().forEach({ itemEntry ->
                        context.accept(
                            itemEntry.asItem().defaultInstance
                        )
                    })
            }.withSearchBar()
            .build()

    override fun <T : Any> registerEntityDataSerializer(
        name: String,
        dataSerializer: EntityDataSerializer<T>,
    ) { ENTITY_DATA_SERIALIZER_REGISTER.register(name, dataSerializer.supplier()) }

    override fun <T : AbstractContainerMenu> createExtendedMenu(constructor: (Int, Inventory, FriendlyByteBuf) -> T): MenuType<T> = IMenuTypeExtension.create(constructor::invoke)

    override fun openExtendedMenu(serverPlayer: ServerPlayer, menuProvider: ExtendedMenuProvider) {
        serverPlayer.openMenu(menuProvider, menuProvider::saveExtraData)
    }

    override fun <T : Any> createRegistry(key: ResourceKey<Registry<T>>, sync: Boolean): Registry<T> {
        return RegistryBuilder(key).sync(sync).create().also(registries::add)
    }

    //    public void addPackFinders(AddPackFindersEvent event) {
    //        var packList = packs.get(event.getPackType());
    //        if (packList != null) {
    //            for (var pack1 : packList) {
    //                var id = pack1.getFirst();
    //                var displayName = pack1.getSecond();
    //
    //                IModFileInfo info = getPackInfo(id);
    //                Path resourcePath = info.getFile().findResource("resourcepacks/" + id.getPath());
    //
    //                final Pack.Info packInfo = createInfoForLatest(displayName, false);
    //                final Pack pack = Pack.create(
    //                        GenerationsCore.MOD_ID + ":add_pack/" + id.getPath(), displayName,
    //                        false,
    //                        (path) -> new PathPackResources(path, resourcePath, true),
    //                        packInfo, PackType.CLIENT_RESOURCES, Pack.Position.BOTTOM, false, PackSource.BUILT_IN);
    //                event.addRepositorySource((packConsumer) ->
    //                        packConsumer.accept(pack));
    //            }
    //        }
    //    }
    //    private static IModFileInfo getPackInfo(ResourceLocation pack) {
    //        if (!FMLLoader.isProduction()) {
    //            for (IModInfo mod : ModList.get().getMods()) {
    //                if (mod.getModId().startsWith("generated_") && fileExists(mod, "resourcepacks/" + pack.getPath())) {
    //                    return mod.getOwningFile();
    //                }
    //            }
    //        }
    //        return ModList.get().getModFileById(pack.getNamespace());
    //    }
    //    private static boolean fileExists(IModInfo info, String path) {
    //        return Files.exists(info.getOwningFile().getFile().findResource(path.split("/")));
    //    }
    //
    //    private static Pack.Info createInfoForLatest(Component description, boolean hidden) {
    //        return new Pack.Info(
    //                description,
    //                SharedConstants.getCurrentVersion().getPackVersion(PackType.SERVER_DATA),
    //                SharedConstants.getCurrentVersion().getPackVersion(PackType.CLIENT_RESOURCES),
    //                FeatureFlagSet.of(),
    //                hidden
    //        );
    //    }
    //
    //    private static PackSource createSource() {
    //        final Component text = Component.translatable("pack.source.builtin");
    //        return PackSource.create(
    //                component -> Component.translatable("pack.nameAndSource", component, text).withStyle(ChatFormatting.GRAY),
    //                true
    //        );
    //    }
    /**
     * Should initialize everything where a specific event does not cover it.
     */
    private fun onInitialize(event: FMLCommonSetupEvent) {
        event.enqueueWork { VanillaCompat.setup() }
    }

    private fun postInit(event: FMLLoadCompleteEvent) {
        event.enqueueWork { VanillaCompat.dispenserBehavior() }
    }

    private fun onDataPackSync(event: OnDatapackSyncEvent) {
        if (event.player != null) GenerationsCore.dataProvider.sync(event.player!!)
    }

    private fun onReload(e: AddReloadListenerEvent) {
        reloadableResources.forEach(Consumer { listener: PreparableReloadListener? -> e.addListener(listener) })
    }

    //    fun onLogin(event: PlayerEvent.PlayerLoggedInEvent) {
    //        this.hasBeenSynced.add(event.key.uuid)
    //    }
    //
    //    fun onLogout(event: PlayerEvent.PlayerLoggedOutEvent) {
    //        this.hasBeenSynced.remove(event.key.uuid)
    //    }
    override fun registerResourceReloader(
        identifier: ResourceLocation,
        reloader: PreparableReloadListener,
        type: PackType,
        dependencies: Collection<ResourceLocation>,
    ) {
        if (type == PackType.SERVER_DATA) reloadableResources.add(reloader)
        else Minecraft.getInstance().resourceManager.instanceOrNull<ReloadableResourceManager>()?.registerReloadListener(reloader)
    }

    override val networkManager: NetworkManager = GenerationsNeoForgeNetworkManager
}