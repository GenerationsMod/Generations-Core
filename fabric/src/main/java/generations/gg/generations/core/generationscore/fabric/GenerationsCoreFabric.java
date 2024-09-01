package generations.gg.generations.core.generationscore.fabric;

import dev.architectury.registry.registries.DeferredRegister;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.GenerationsImplementation;
import generations.gg.generations.core.generationscore.common.compat.ImpactorCompat;
import generations.gg.generations.core.generationscore.common.compat.VanillaCompat;
import generations.gg.generations.core.generationscore.common.config.ConfigLoader;
import generations.gg.generations.core.generationscore.common.world.entity.GenerationsEntities;
import generations.gg.generations.core.generationscore.common.world.entity.PlayerNpcEntity;
import generations.gg.generations.core.generationscore.common.world.feature.GenerationsConfiguredFeatures;
import generations.gg.generations.core.generationscore.common.world.feature.GenerationsPlacedFeatures;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.MutableBlockEntityType;
import generations.gg.generations.core.generationscore.fabric.world.item.creativetab.GenerationsCreativeTabsFabric;
import generations.gg.generations.core.generationscore.fabric.worldgen.GenerationsFabricBiomemodifiers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

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

    public void registerStrippable(@NotNull Block log, @NotNull Block stripped) {
        StrippableBlockRegistry.register(log, stripped);
    }

    public void registerFlammable(@NotNull Block blockIn, int encouragement, int flammability) {
        FlammableBlockRegistry.getDefaultInstance().add(blockIn, encouragement, flammability);
    }

    public void registerCompostables(@NotNull Block block, float chance) {
        CompostingChanceRegistry.INSTANCE.add(block, chance);
    }

    @Override
    public Supplier<CreativeModeTab> create(String name, Supplier<ItemStack> supplier, DeferredRegister<? extends ItemLike>... deferredRegister) {
        return GenerationsCreativeTabsFabric.create(name, supplier, deferredRegister);
    }

    @Override
    public boolean canEquip(ItemStack carried, EquipmentSlot equipmentslottype, Entity entity) {
        return Mob.getEquipmentSlotForItem(carried) == equipmentslottype;
    }

    @Override
    public CompoundTag serializeStack(ItemStack itemStack) {
        return itemStack.save(new CompoundTag());
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
//        FabricDefaultAttributeRegistry.register(GenerationsEntities.STATUE_ENTITY.get(), StatueEntity.createLivingAttributes());
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