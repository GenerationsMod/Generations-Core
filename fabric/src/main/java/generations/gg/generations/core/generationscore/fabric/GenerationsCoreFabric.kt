package generations.gg.generations.core.generationscore.fabric

import dev.architectury.registry.registries.DeferredRegister
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.GenerationsCore.init
import generations.gg.generations.core.generationscore.common.GenerationsCore.initBuiltinPacks
import generations.gg.generations.core.generationscore.common.GenerationsCore.onAnvilChange
import generations.gg.generations.core.generationscore.common.GenerationsImplementation
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.compat.ImpactorCompat
import generations.gg.generations.core.generationscore.common.compat.VanillaCompat
import generations.gg.generations.core.generationscore.common.config.ConfigLoader.setConfigDirectory
import generations.gg.generations.core.generationscore.common.world.feature.GenerationsConfiguredFeatures
import generations.gg.generations.core.generationscore.common.world.feature.GenerationsPlacedFeatures
import generations.gg.generations.core.generationscore.common.world.level.block.entities.MutableBlockEntityType
import generations.gg.generations.core.generationscore.fabric.AnvilEvents.AnvilChange
import generations.gg.generations.core.generationscore.fabric.world.item.creativetab.GenerationsCreativeTabsFabric
import generations.gg.generations.core.generationscore.fabric.worldgen.GenerationsFabricBiomemodifiers
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents.SyncDataPackContents
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.fabricmc.fabric.api.resource.ResourcePackActivationType
import net.fabricmc.loader.api.FabricLoader
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint
import net.minecraft.network.chat.MutableComponent
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraft.server.packs.PackType
import net.minecraft.server.packs.resources.PreparableReloadListener
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.util.profiling.ProfilerFiller
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.Block
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import java.util.function.Supplier

/**
 * Fabric Main class and entry point for GenerationsCore.
 * @see ModInitializer
 *
 * @see GenerationsCore
 *
 *
 * @author Joseph T. McQuigg, WaterPicker
 */
class GenerationsCoreFabric : ModInitializer, GenerationsImplementation, PreLaunchEntrypoint {
    override fun onInitialize() {
        init(this)
        VanillaCompat.setup()

        if (FabricLoader.getInstance().isModLoaded("impactor")) ImpactorCompat.init()

        ServerLifecycleEvents.SYNC_DATA_PACK_CONTENTS.register(SyncDataPackContents { player: ServerPlayer?, isLogin: Boolean ->
            if (isLogin) GenerationsCore.dataProvider.sync(
                player!!
            )
        })



        AnvilEvents.ANVIL_CHANGE.register(AnvilChange { result: AnvilChange.Result, left: ItemStack?, right: ItemStack?, name: String?, baseCost: Int, player: Player? ->
            onAnvilChange(
                left!!, right!!, player!!,
                { output: ItemStack? ->
                    result.output =
                        output
                },
                { cost: Int -> result.cost = cost },
                { materialCost: Int -> result.materialCost = materialCost })
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

        MutableBlockEntityType.blocksToAdd.forEach { block -> block.blockEntityType.instanceOrNull<MutableBlockEntityType<*>>()?.addBlock(block) }

        GenerationsConfiguredFeatures.init()
        GenerationsPlacedFeatures.init()
        GenerationsFabricBiomemodifiers.generateOres()
        VanillaCompat.dispenserBehavior()
    }

    override fun registerStrippable(log: Block, stripped: Block) {
        StrippableBlockRegistry.register(log, stripped)
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
        supplier: Supplier<ItemStack>,
        vararg deferredRegister: DeferredRegister<out ItemLike?>
    ): Supplier<CreativeModeTab> {
        return GenerationsCreativeTabsFabric.create(name, supplier, *deferredRegister)
    }

    override fun registerResourceReloader(
        identifier: ResourceLocation,
        reloader: PreparableReloadListener,
        type: PackType,
        dependencies: Collection<ResourceLocation>
    ) {
        ResourceManagerHelper.get(type).registerReloadListener(
            GenerationsReloadListener(
                identifier,
                reloader,
                dependencies
            )
        )
    }

    override fun onPreLaunch() {
        setConfigDirectory(FabricLoader.getInstance().configDir)
    }

    @JvmRecord
    private data class GenerationsReloadListener(
        val identifier: ResourceLocation,
        val reloader: PreparableReloadListener,
        val dependencies: Collection<ResourceLocation>
    ) :
        IdentifiableResourceReloadListener {
        override fun reload(
            preparationBarrier: PreparableReloadListener.PreparationBarrier,
            resourceManager: ResourceManager,
            preparationsProfiler: ProfilerFiller,
            reloadProfiler: ProfilerFiller,
            backgroundExecutor: Executor,
            gameExecutor: Executor
        ): CompletableFuture<Void> {
            return reloader.reload(
                preparationBarrier,
                resourceManager,
                preparationsProfiler,
                reloadProfiler,
                backgroundExecutor,
                gameExecutor
            )
        }

        override fun getFabricId(): ResourceLocation {
            return this.identifier
        }

        override fun getName(): String {
            return reloader.name
        }

        override fun getFabricDependencies(): Collection<ResourceLocation> {
            return this.dependencies
        }
    }
}