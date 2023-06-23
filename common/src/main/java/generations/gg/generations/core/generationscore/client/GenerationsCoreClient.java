package generations.gg.generations.core.generationscore.client;

import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.platform.events.ClientPlayerEvent;
import com.cobblemon.mod.common.platform.events.PlatformEvents;
import dev.architectury.registry.item.ItemPropertiesRegistry;
import dev.architectury.registry.menu.MenuRegistry;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.GenerationsDataProvider;
import generations.gg.generations.core.generationscore.GenerationsImplementation;
import generations.gg.generations.core.generationscore.client.render.block.entity.*;
import generations.gg.generations.core.generationscore.client.render.entity.GenerationsBoatRenderer;
import generations.gg.generations.core.generationscore.client.render.entity.SittableEntityRenderer;
import generations.gg.generations.core.generationscore.client.render.entity.TieredFishingHookRenderer;
import generations.gg.generations.core.generationscore.client.render.rarecandy.ModelRegistry;
import generations.gg.generations.core.generationscore.client.render.rarecandy.Pipelines;
import generations.gg.generations.core.generationscore.client.screen.container.*;
import generations.gg.generations.core.generationscore.world.container.GenerationsContainers;
import generations.gg.generations.core.generationscore.world.entity.GenerationsBoatEntity;
import generations.gg.generations.core.generationscore.world.entity.GenerationsEntities;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.item.MelodyFluteItem;
import generations.gg.generations.core.generationscore.world.item.curry.CurryData;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsWoodTypes;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

import static generations.gg.generations.core.generationscore.world.item.MelodyFluteItem.isItem;
import static net.minecraft.client.renderer.Sheets.createHangingSignMaterial;
import static net.minecraft.client.renderer.Sheets.createSignMaterial;

public class GenerationsCoreClient {

    public static void onInitialize(Minecraft minecraft) {
//      ReloadListenerRegistry.register(PackType.CLIENT_RESOURCES, (ResourceManagerReloadListener) Pipelines::onInitialize);
        GenerationsCoreClient.setupClient(minecraft);

        PlatformEvents.CLIENT_PLAYER_LOGIN.subscribe(Priority.NORMAL, GenerationsCoreClient::onLogin);
        PlatformEvents.CLIENT_PLAYER_LOGOUT.subscribe(Priority.NORMAL, GenerationsCoreClient::onLogout);
    }

    private static void setupClient(Minecraft event) {
        event.submit(() -> {
            addWoodType(GenerationsWoodTypes.ULTRA_JUNGLE);
            addWoodType(GenerationsWoodTypes.ULTRA_DARK);
            addWoodType(GenerationsWoodTypes.GHOST);
            ModelRegistry.getRareCandy();
            Pipelines.REGISTER.register(Pipelines::initGenerationsPipelines);
            Pipelines.onInitialize(event.getResourceManager());
            registerScreens();
        });

        ItemPropertiesRegistry.register(GenerationsItems.CURRY.get(), GenerationsCore.id("curry_type"), (arg, arg2, arg3, i) -> CurryData.fromNbt(arg.getOrCreateTag()).getCurryType().ordinal());
        ItemPropertiesRegistry.register(GenerationsItems.MELODY_FLUTE.get(), GenerationsCore.id("flute_type"), (arg, arg2, arg3, i) -> {
            ItemStack stack = MelodyFluteItem.getImbuedItem(arg);

            if (isItem(GenerationsItems.ICY_WING, stack)) return 0.125f;
            else if (isItem(GenerationsItems.ELEGANT_WING, stack)) return 0.25f;
            else if (isItem(GenerationsItems.STATIC_WING, stack)) return 0.375f;
            else if (isItem(GenerationsItems.BELLIGERENT_WING, stack)) return 0.5f;
            else if (isItem(GenerationsItems.FIERY_WING, stack)) return 0.625f;
            else if (isItem(GenerationsItems.SINISTER_WING, stack)) return 0.75f;
            else if (isItem(GenerationsItems.RAINBOW_WING, stack)) return 0.875f;
            else if (isItem(GenerationsItems.SILVER_WING, stack)) return 1.0f;
            else return 0;
        });
    }

    private static void addWoodType(WoodType woodType) {
        WoodType.register(woodType);
        Sheets.SIGN_MATERIALS.put(woodType, createSignMaterial(woodType));
        Sheets.HANGING_SIGN_MATERIALS.put(woodType, createHangingSignMaterial(woodType));
    }

    private static void registerScreens() {
        MenuRegistry.registerScreenFactory(GenerationsContainers.COOKING_POT.get(), CookingPotScreen::new);
        MenuRegistry.registerScreenFactory(GenerationsContainers.GENERIC.get(), GenericChestScreen::new);
        MenuRegistry.registerScreenFactory(GenerationsContainers.WALKMON.get(), GenericChestScreen::new);
        MenuRegistry.registerScreenFactory(GenerationsContainers.MACHINE_BLOCK.get(), MachineBlockScreen::new);
        MenuRegistry.registerScreenFactory(GenerationsContainers.MELODY_FLUTE.get(), MelodyFluteScreen::new);
        MenuRegistry.registerScreenFactory(GenerationsContainers.TRASHCAN.get(), TrashCanScreen::new);
    }

    /**
     * Registers the entity renderers.
     * @see GenerationsEntities
     */
    public static void registerEntityRenderers(BiConsumer<EntityType<? extends Entity>, EntityRendererProvider> consumer) {
        consumer.accept(GenerationsEntities.SEAT.get(), SittableEntityRenderer::new);
        consumer.accept(GenerationsEntities.TIERED_FISHING_BOBBER.get(), TieredFishingHookRenderer::new);
        consumer.accept(GenerationsEntities.BOAT_ENTITY.get(), (EntityRendererProvider.Context context) -> new GenerationsBoatRenderer(context, false));
        consumer.accept(GenerationsEntities.CHEST_BOAT_ENTITY.get(), context -> new GenerationsBoatRenderer(context, true));
        consumer.accept(GenerationsEntities.MAGMA_CRYSTAL.get(), ThrownItemRenderer::new);
    }

    /**
     * Registers the block entity renderers.
     * @see BlockEntityRenderers
     * @see GenerationsBlockEntities
     */
    public static void registerBlockEntityRenderers(BiConsumer<BlockEntityType<? extends BlockEntity>, BlockEntityRendererProvider> consumer) {
        consumer.accept(GenerationsBlockEntities.POKE_DOLL.get(), GeneralUseBlockEntityRenderer::new);
        consumer.accept(GenerationsBlockEntities.HEALER.get(), GeneralUseBlockEntityRenderer::new);
        consumer.accept(GenerationsBlockEntities.CLOCK.get(), GeneralUseBlockEntityRenderer::new);
        consumer.accept(GenerationsBlockEntities.BOX.get(), GeneralUseBlockEntityRenderer::new);

        consumer.accept(GenerationsBlockEntities.TIMESPACE_ALTAR.get(), TimeSpaceAltarEntityRenderer::new);
        consumer.accept(GenerationsBlockEntities.ABUNDANT_SHRINE.get(), GeneralUseBlockEntityRenderer::new);
        consumer.accept(GenerationsBlockEntities.CELESTIAL_ALTAR.get(), GeneralUseBlockEntityRenderer::new);
        consumer.accept(GenerationsBlockEntities.LUNAR_SHRINE.get(), GeneralUseBlockEntityRenderer::new);
        consumer.accept(GenerationsBlockEntities.MELOETTA_MUSIC_BOX.get(), GeneralUseBlockEntityRenderer::new);
        consumer.accept(GenerationsBlockEntities.REGIGIGAS_SHRINE.get(), RegigigasShrineBlockEntityRenderer::new);
        consumer.accept(GenerationsBlockEntities.TAO_TRIO_SHRINE.get(), GeneralUseBlockEntityRenderer::new);
        consumer.accept(GenerationsBlockEntities.TAPU_SHRINE.get(), GeneralUseBlockEntityRenderer::new);
        consumer.accept(GenerationsBlockEntities.BREEDER.get(), BreederBlocEntityRenderer::new);

        consumer.accept(GenerationsBlockEntities.COOKING_POT.get(), CookingPotRenderer::new);
        consumer.accept(GenerationsBlockEntities.WEATHER_TRIO.get(), GeneralUseBlockEntityRenderer::new);
        consumer.accept(GenerationsBlockEntities.SIGN_BLOCK_ENTITIES.get(), SignRenderer::new);
        consumer.accept(GenerationsBlockEntities.HANGING_SIGN_BLOCK_ENTITIES.get(), HangingSignRenderer::new);
        consumer.accept(GenerationsBlockEntities.GENERIC_CHEST.get(), GenericChestRenderer::new);
        consumer.accept(GenerationsBlockEntities.GENERIC_SHRINE.get(), GeneralUseBlockEntityRenderer::new);
        consumer.accept(GenerationsBlockEntities.GENERIC_DYED_VARIANT.get(), GeneralUseBlockEntityRenderer::new);
        consumer.accept(GenerationsBlockEntities.GENERIC_MODEL_PROVIDING.get(), GeneralUseBlockEntityRenderer::new);
        consumer.accept(GenerationsBlockEntities.VENDING_MACHINE.get(), GeneralUseBlockEntityRenderer::new);
        consumer.accept(GenerationsBlockEntities.BALL_DISPLAY.get(), GeneralUseBlockEntityRenderer::new);
    }

    public static void registerLayerDefinitions(BiConsumer<ModelLayerLocation, Supplier<LayerDefinition>> consumer) {
        Supplier<LayerDefinition> boat = BoatModel::createBodyModel;
        Supplier<LayerDefinition> chestBoat = ChestBoatModel::createBodyModel;
        for (GenerationsBoatEntity.Type type : GenerationsBoatEntity.Type.values()) {
            consumer.accept(GenerationsBoatRenderer.createBoatModelName(type), boat);
            consumer.accept(GenerationsBoatRenderer.createChestBoatModelName(type), chestBoat);
        }
    }

    public static Unit onLogin(ClientPlayerEvent.Login login) {
        GenerationsDataProvider.INSTANCE.setCanReload(false);
        return Unit.INSTANCE;
    }

    public static Unit onLogout(ClientPlayerEvent.Logout logout) {
        GenerationsDataProvider.INSTANCE.setCanReload(true);
        return Unit.INSTANCE;
    }
}
