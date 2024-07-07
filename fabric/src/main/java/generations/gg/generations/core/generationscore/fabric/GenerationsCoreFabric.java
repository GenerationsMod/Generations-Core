package generations.gg.generations.core.generationscore.fabric;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.GenerationsImplementation;
import generations.gg.generations.core.generationscore.compat.ImpactorCompat;
import generations.gg.generations.core.generationscore.compat.VanillaCompat;
import generations.gg.generations.core.generationscore.config.ConfigLoader;
import generations.gg.generations.core.generationscore.fabric.worldgen.GenerationsFabricBiomemodifiers;
import generations.gg.generations.core.generationscore.world.entity.GenerationsEntities;
import generations.gg.generations.core.generationscore.world.entity.PlayerNpcEntity;
import generations.gg.generations.core.generationscore.world.entity.StatueEntity;
import generations.gg.generations.core.generationscore.world.feature.GenerationsConfiguredFeatures;
import generations.gg.generations.core.generationscore.world.feature.GenerationsPlacedFeatures;
import generations.gg.generations.core.generationscore.world.level.block.entities.MutableBlockEntityType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * Fabric Main class and entry point for GenerationsCore.
 * @see ModInitializer
 * @see GenerationsCore
 *
 * @author Joseph T. McQuigg, WaterPicker
 */
public class GenerationsCoreFabric implements ModInitializer, GenerationsImplementation, PreLaunchEntrypoint {
    public void onInitialize() {
        GenerationsCore.init(this);
        VanillaCompat.setup();

        this.getNetworkManager().registerClientBound();
        this.getNetworkManager().registerServerBound();

        if (FabricLoader.getInstance().isModLoaded("impactor"))
            ImpactorCompat.init();

        ServerLifecycleEvents.SYNC_DATA_PACK_CONTENTS.register((player, isLogin) -> {
            if (isLogin)
                GenerationsCore.dataProvider.sync(player);
        });

        AnvilEvents.ANVIL_CHANGE.register((result, left, right, name, baseCost, player) -> {
            GenerationsCore.onAnvilChange(left, right, player, result::setOutput, result::setCost, result::setMaterialCost);
            return false;
        });

        GenerationsCore.initBuiltinPacks((type, s, s2) -> ResourceManagerHelper.registerBuiltinResourcePack(s, FabricLoader.getInstance().getModContainer("generations_core").get(), s2, ResourcePackActivationType.DEFAULT_ENABLED));

        MutableBlockEntityType.blocksToAdd.forEach(genericModelBlock -> {
            if(genericModelBlock.getBlockEntityType() instanceof MutableBlockEntityType<?> mutableBlockEntityType) {
                mutableBlockEntityType.addBlock(genericModelBlock);
            }
        });

        GenerationsConfiguredFeatures.init();
        GenerationsPlacedFeatures.init();
        GenerationsFabricBiomemodifiers.generateOres();
        registerEntityAttributes();
        VanillaCompat.dispenserBehavior();
    }

    @Override
    public NetworkManager getNetworkManager() {
        return GenerationsFabricNetworkManager.INSTANCE;
    }

    @Override
    public void registerResourceReloader(ResourceLocation identifier, PreparableReloadListener reloader, PackType type, Collection<ResourceLocation> dependencies) {
        ResourceManagerHelper.get(type).registerReloadListener(
            new GenerationsReloadListener(
                identifier,
                reloader,
                dependencies
            )
        );
    }

    public static void registerEntityAttributes(){
        FabricDefaultAttributeRegistry.register(GenerationsEntities.STATUE_ENTITY.get(), StatueEntity.createLivingAttributes());
        FabricDefaultAttributeRegistry.register(GenerationsEntities.PLAYER_NPC.get(), PlayerNpcEntity.createMobAttributes());
    }

    @Override
    public void onPreLaunch() {
        ConfigLoader.setConfigDirectory(FabricLoader.getInstance().getConfigDir());
    }

    private record GenerationsReloadListener(ResourceLocation identifier, PreparableReloadListener reloader, Collection<ResourceLocation> dependencies) implements IdentifiableResourceReloadListener {

        @Override
        public @NotNull CompletableFuture<Void> reload(PreparableReloadListener.PreparationBarrier preparationBarrier, ResourceManager resourceManager, ProfilerFiller preparationsProfiler, ProfilerFiller reloadProfiler, Executor backgroundExecutor, Executor gameExecutor) {
            return reloader.reload(preparationBarrier, resourceManager, preparationsProfiler, reloadProfiler, backgroundExecutor, gameExecutor);
        }

        @Override
        public ResourceLocation getFabricId() {
            return this.identifier;
        }

        @Override
        public @NotNull String getName() {
            return this.reloader.getName();
        }

        @Override
        public Collection<ResourceLocation> getFabricDependencies() {
            return this.dependencies;
        }
    }
}