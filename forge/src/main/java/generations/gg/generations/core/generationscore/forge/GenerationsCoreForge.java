package generations.gg.generations.core.generationscore.forge;

import com.mojang.datafixers.util.Pair;
import dev.architectury.platform.forge.EventBuses;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.GenerationsImplementation;
import generations.gg.generations.core.generationscore.compat.VanillaCompat;
import generations.gg.generations.core.generationscore.config.ConfigLoader;
import generations.gg.generations.core.generationscore.forge.client.GenerationsCoreClientForge;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ReloadableResourceManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.OnDatapackSyncEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;

/**
 * Forge Main class for GenerationsCore.
 * @see Mod
 * @see GenerationsCore
 * @author J.T. McQuigg, WaterPicker
 */
@Mod(GenerationsCore.MOD_ID)
public class GenerationsCoreForge implements GenerationsImplementation {
    private List<PreparableReloadListener> reloadableResources = new ArrayList<>();
    private Map<PackType, List<Pair<String, String>>> packs = new HashMap<>();

    /**
     * Sets up Forge side of the mod.
     * @see FMLJavaModLoadingContext
     */
    public GenerationsCoreForge() {
        IEventBus MOD_BUS = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(GenerationsCore.MOD_ID, MOD_BUS);
        MOD_BUS.addListener(this::onInitialize);
        ConfigLoader.CONFIG_DIRECTORY = FMLPaths.CONFIGDIR.get();
        GenerationsCore.init(this);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> GenerationsCoreClientForge.init(MOD_BUS));
        var EVENT_BUS = MinecraftForge.EVENT_BUS;

        EVENT_BUS.addListener(this::onDataPackSync);
        EVENT_BUS.addListener((Consumer<AnvilUpdateEvent>) event -> GenerationsCore.onAnvilChange(event.getLeft(), event.getRight(), event.getPlayer(), event::setOutput, event::setCost, event::setMaterialCost));
//            addListener(this::onLogin)
//            addListener(this::onLogout)
        EVENT_BUS.addListener(this::onReload);

        GenerationsCore.initBuiltinPacks((packType, id, name) -> packs.computeIfAbsent(packType, a -> new ArrayList<>()).add(new Pair<>(id, name)));
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::addPackFinders);
    }

    public void addPackFinders(AddPackFindersEvent event) {
        var packList = packs.get(event.getPackType());
        if (packList != null) {
            event.addRepositorySource(consumer -> {
                packList.stream().map(a -> createPack(a.getFirst(), a.getSecond())).forEach(consumer);
            });
        }
    }

    public static Pack createPack(String id, String name) {
        var resourcePath = ModList.get().getModFileById(GenerationsCore.MOD_ID).getFile().findResource("resourcepacks", id);
        return Pack.readMetaAndCreate("builtin/" + id, Component.literal(name), false,
                (path) -> new PathPackResources(path, resourcePath, false), PackType.SERVER_DATA, Pack.Position.BOTTOM, PackSource.BUILT_IN);
    }

    /**
     * Should initialize everything where a specific event does not cover it.
     */
    private void onInitialize(FMLCommonSetupEvent event) {
        getNetworkManager().registerClientBound();
        getNetworkManager().registerServerBound();
        event.enqueueWork(VanillaCompat::setup);
    }

    private void onDataPackSync(OnDatapackSyncEvent event) {
        if(event.getPlayer() != null) GenerationsCore.dataProvider.sync(event.getPlayer());
    }

    private void onReload(AddReloadListenerEvent e) {
        this.reloadableResources.forEach(e::addListener);
    }

//    fun onLogin(event: PlayerEvent.PlayerLoggedInEvent) {
//        this.hasBeenSynced.add(event.key.uuid)
//    }
//
//    fun onLogout(event: PlayerEvent.PlayerLoggedOutEvent) {
//        this.hasBeenSynced.remove(event.key.uuid)
//    }

    @Override
    public void registerResourceReloader(ResourceLocation identifier, PreparableReloadListener reloader, PackType type, Collection<ResourceLocation> dependencies) {
        if (type == PackType.SERVER_DATA) this.reloadableResources.add(reloader);
        else if(Minecraft.getInstance().getResourceManager() instanceof ReloadableResourceManager manager) manager.registerReloadListener(reloader);
    }

    @NotNull
    @Override
    public NetworkManager getNetworkManager() {
        return GenerationsForgeNetworkManager.INSTANCE;
    }
}