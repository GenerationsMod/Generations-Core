package generations.gg.generations.core.generationscore.forge;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import dev.architectury.registry.registries.DeferredRegister;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.GenerationsImplementation;
import generations.gg.generations.core.generationscore.common.api.events.general.EntityEvents;
import generations.gg.generations.core.generationscore.common.compat.ImpactorCompat;
import generations.gg.generations.core.generationscore.common.compat.VanillaCompat;
import generations.gg.generations.core.generationscore.common.config.ConfigLoader;
import generations.gg.generations.core.generationscore.forge.world.item.creativetab.GenerationsCreativeTabsForge;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.MutableBlockEntityType;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ReloadableResourceManager;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.neoforge.event.AnvilUpdateEvent;
import net.neoforged.neoforge.event.OnDatapackSyncEvent;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Forge Main class for GenerationsCore.
 * @see Mod
 * @see GenerationsCore
 * @author Joseph T. McQuigg, WaterPicker
 */
@Mod(GenerationsCore.MOD_ID)
public class GenerationsCoreForge implements GenerationsImplementation {

    private List<PreparableReloadListener> reloadableResources = new ArrayList<>();
    private Map<PackType, List<Pair<ResourceLocation, Component>>> packs = new HashMap<>();

    /**
     * Sets up Forge side of the mod.
     */
    public GenerationsCoreForge(IEventBus MOD_BUS) {
        ConfigLoader.setConfigDirectory(FMLPaths.CONFIGDIR.get());
        GenerationsCreativeTabsForge.init(MOD_BUS);
//        EvenBus.registerModEventBus(GenerationsCore.MOD_ID, MOD_BUS);
        MOD_BUS.addListener(this::onInitialize);
        MOD_BUS.addListener(this::postInit);
        GenerationsCore.init(this);
//        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> GenerationsCoreClientForge.init(MOD_BUS));
        var EVENT_BUS = NeoForge.EVENT_BUS;

        EVENT_BUS.addListener(this::onDataPackSync);
        EVENT_BUS.addListener((Consumer<AnvilUpdateEvent>) event -> GenerationsCore.onAnvilChange(event.getLeft(), event.getRight(), event.getPlayer(), event::setOutput, event::setCost, event::setMaterialCost));
        EVENT_BUS.addListener((Consumer<LivingEvent.LivingJumpEvent>) event -> EntityEvents.JUMP.invoker().jump(event.getEntity()));
//            addListener(this::onLogin)
//            addListener(this::onLogout)
        EVENT_BUS.addListener(this::onReload);

//        GenerationsCore.initBuiltinPacks((packType, id, name) -> packs.computeIfAbsent(packType, a -> new ArrayList<>()).add(new Pair<>(id, name)));
//        MOD_BUS.addListener(this::addPackFinders);
        if (ModList.get().isLoaded("impactor"))
            ImpactorCompat.init();
    }

    public void registerStrippable(@NotNull Block log, @NotNull Block stripped) {
        if (!log.defaultBlockState().hasProperty(RotatedPillarBlock.AXIS))
            throw new IllegalArgumentException("Input block is missing required 'AXIS' property!");
        if (!stripped.defaultBlockState().hasProperty(RotatedPillarBlock.AXIS))
            throw new IllegalArgumentException("Result block is missing required 'AXIS' property!");
        if (AxeItem.STRIPPABLES instanceof ImmutableMap)
            AxeItem.STRIPPABLES = new HashMap<>(AxeItem.STRIPPABLES);

        AxeItem.STRIPPABLES.put(log, stripped);
    }
    public void registerFlammable(@NotNull Block blockIn, int encouragement, int flammability) {
        ((FireBlock) Blocks.FIRE).setFlammable(blockIn, encouragement, flammability);
    }

    public void registerCompostables(@NotNull Block block, float chance) {
        ComposterBlock.COMPOSTABLES.put(block, chance);
    }

    @SafeVarargs
    @Override
    public final Supplier<CreativeModeTab> create(String name, Supplier<ItemStack> o, DeferredRegister<? extends ItemLike>... deferredRegister) {
        return GenerationsCreativeTabsForge.create(name, o, deferredRegister);
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
    private void onInitialize(final FMLCommonSetupEvent event) {
        event.enqueueWork(VanillaCompat::setup);
        MutableBlockEntityType.blocksToAdd.forEach(genericModelBlock -> {
            if (genericModelBlock.getBlockEntityType() instanceof MutableBlockEntityType<?> mutableBlockEntityType) {
                mutableBlockEntityType.addBlock(genericModelBlock);
            }
        });
    }

    private void postInit(final FMLLoadCompleteEvent event) {
        event.enqueueWork(VanillaCompat::dispenserBehavior);
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

}