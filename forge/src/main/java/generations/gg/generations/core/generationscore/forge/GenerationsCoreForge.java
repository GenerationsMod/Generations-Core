package generations.gg.generations.core.generationscore.forge;

import com.google.gson.Gson;
import com.mojang.datafixers.util.Pair;
import dev.architectury.platform.forge.EventBuses;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.GenerationsImplementation;
import generations.gg.generations.core.generationscore.api.events.general.EntityEvents;
import generations.gg.generations.core.generationscore.compat.ImpactorCompat;
import generations.gg.generations.core.generationscore.compat.VanillaCompat;
import generations.gg.generations.core.generationscore.config.ConfigLoader;
import generations.gg.generations.core.generationscore.forge.client.GenerationsCoreClientForge;
import generations.gg.generations.core.generationscore.world.item.creativetab.forge.GenerationsCreativeTabsImpl;
import generations.gg.generations.core.generationscore.world.level.block.entities.MutableBlockEntityType;
import net.minecraft.ChatFormatting;
import net.minecraft.SharedConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ReloadableResourceManager;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.OnDatapackSyncEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.forgespi.language.IModFileInfo;
import net.minecraftforge.forgespi.language.IModInfo;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Files;
import java.nio.file.Path;
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
    private static final Gson gson = new Gson();

    private List<PreparableReloadListener> reloadableResources = new ArrayList<>();
    private Map<PackType, List<Pair<ResourceLocation, Component>>> packs = new HashMap<>();

    /**
     * Sets up Forge side of the mod.
     * @see FMLJavaModLoadingContext
     */
    public GenerationsCoreForge() {
        ConfigLoader.setConfigDirectory(FMLPaths.CONFIGDIR.get());
        IEventBus MOD_BUS = FMLJavaModLoadingContext.get().getModEventBus();
        GenerationsCreativeTabsImpl.init(MOD_BUS);
        EventBuses.registerModEventBus(GenerationsCore.MOD_ID, MOD_BUS);
        MOD_BUS.addListener(this::onInitialize);
        GenerationsCore.init(this);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> GenerationsCoreClientForge.init(MOD_BUS));
        var EVENT_BUS = MinecraftForge.EVENT_BUS;

        EVENT_BUS.addListener(this::onDataPackSync);
        EVENT_BUS.addListener((Consumer<AnvilUpdateEvent>) event -> GenerationsCore.onAnvilChange(event.getLeft(), event.getRight(), event.getPlayer(), event::setOutput, event::setCost, event::setMaterialCost));
        EVENT_BUS.addListener((Consumer<LivingEvent.LivingJumpEvent>) event -> EntityEvents.JUMP.invoker().jump(event.getEntity()));
//            addListener(this::onLogin)
//            addListener(this::onLogout)
        EVENT_BUS.addListener(this::onReload);

        GenerationsCore.initBuiltinPacks((packType, id, name) -> packs.computeIfAbsent(packType, a -> new ArrayList<>()).add(new Pair<>(id, name)));
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::addPackFinders);
        if (ModList.get().isLoaded("impactor"))
            ImpactorCompat.init();
    }

    public void addPackFinders(AddPackFindersEvent event) {
        var packList = packs.get(event.getPackType());
        if (packList != null) {
            for (var pack1 : packList) {
                var id = pack1.getFirst();
                var displayName = pack1.getSecond();

                IModFileInfo info = getPackInfo(id);
                Path resourcePath = info.getFile().findResource("resourcepacks/" + id.getPath());

                final Pack.Info packInfo = createInfoForLatest(displayName, false);
                final Pack pack = Pack.create(
                        GenerationsCore.MOD_ID + ":add_pack/" + id.getPath(), displayName,
                        false,
                        (path) -> new PathPackResources(path, resourcePath, true),
                        packInfo, PackType.CLIENT_RESOURCES, Pack.Position.BOTTOM, false, PackSource.BUILT_IN);
                event.addRepositorySource((packConsumer) ->
                        packConsumer.accept(pack));
            }
        }
    }


    private static IModFileInfo getPackInfo(ResourceLocation pack) {
        if (!FMLLoader.isProduction()) {
            for (IModInfo mod : ModList.get().getMods()) {
                if (mod.getModId().startsWith("generated_") && fileExists(mod, "resourcepacks/" + pack.getPath())) {
                    return mod.getOwningFile();
                }
            }
        }
        return ModList.get().getModFileById(pack.getNamespace());
    }

    private static boolean fileExists(IModInfo info, String path) {
        return Files.exists(info.getOwningFile().getFile().findResource(path.split("/")));
    }

    private static Pack.Info createInfoForLatest(Component description, boolean hidden) {
        return new Pack.Info(
                description,
                SharedConstants.getCurrentVersion().getPackVersion(PackType.SERVER_DATA),
                SharedConstants.getCurrentVersion().getPackVersion(PackType.CLIENT_RESOURCES),
                FeatureFlagSet.of(),
                hidden
        );
    }

    private static PackSource createSource() {
        final Component text = Component.translatable("pack.source.builtin");
        return PackSource.create(
                component -> Component.translatable("pack.nameAndSource", component, text).withStyle(ChatFormatting.GRAY),
                true
        );
    }


    /**
     * Should initialize everything where a specific event does not cover it.
     */
    private void onInitialize(final FMLCommonSetupEvent event) {
        getNetworkManager().registerClientBound();
        getNetworkManager().registerServerBound();
        event.enqueueWork(VanillaCompat::setup);
        MutableBlockEntityType.blocksToAdd.forEach(genericModelBlock -> {
            if (genericModelBlock.getBlockEntityType() instanceof MutableBlockEntityType<?> mutableBlockEntityType) {
                mutableBlockEntityType.addBlock(genericModelBlock);
            }
        });
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
        else if(Minecraft.getInstance() != null /*Jt do not touch. Had issues with this being null during datagen for some reason.*/ && Minecraft.getInstance().getResourceManager() instanceof ReloadableResourceManager manager) manager.registerReloadListener(reloader);
    }

    @NotNull
    @Override
    public NetworkManager getNetworkManager() {
        return GenerationsForgeNetworkManager.INSTANCE;
    }
}