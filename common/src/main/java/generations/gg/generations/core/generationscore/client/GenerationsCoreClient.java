package generations.gg.generations.core.generationscore.client;

import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.item.ItemPropertiesRegistry;
import dev.architectury.registry.menu.MenuRegistry;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.client.render.entity.GenerationsBoatRenderer;
import generations.gg.generations.core.generationscore.client.render.entity.GenerationsChestBoatRenderer;
import generations.gg.generations.core.generationscore.client.render.entity.SittableEntityRenderer;
import generations.gg.generations.core.generationscore.client.render.entity.TieredFishingHookRenderer;
import generations.gg.generations.core.generationscore.client.render.rarecandy.ModelRegistry;
import generations.gg.generations.core.generationscore.client.render.rarecandy.Pipelines;
import generations.gg.generations.core.generationscore.client.screen.container.*;
import generations.gg.generations.core.generationscore.world.container.GenerationsContainers;
import generations.gg.generations.core.generationscore.world.entity.GenerationsEntities;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.item.MelodyFluteItem;
import generations.gg.generations.core.generationscore.world.item.curry.CurryData;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsWoodTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.WoodType;

import static generations.gg.generations.core.generationscore.world.item.MelodyFluteItem.isItem;
import static net.minecraft.client.renderer.Sheets.createHangingSignMaterial;
import static net.minecraft.client.renderer.Sheets.createSignMaterial;

public class GenerationsCoreClient {
    public static void onInitialize(Minecraft minecraft) {
        GenerationsCoreClient.registerEntityRenderers();
//      ReloadListenerRegistry.register(PackType.CLIENT_RESOURCES, (ResourceManagerReloadListener) Pipelines::onInitialize);
        GenerationsCoreClient.setupClient(minecraft);
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

            if (isItem(GenerationsItems.ICY_WING, stack)) return 1f;
            else if (isItem(GenerationsItems.ELEGANT_WING, stack)) return 2f;
            else if (isItem(GenerationsItems.STATIC_WING, stack)) return 3f;
            else if (isItem(GenerationsItems.BELLIGERENT_WING, stack)) return 4f;
            else if (isItem(GenerationsItems.FIERY_WING, stack)) return 5f;
            else if (isItem(GenerationsItems.SINISTER_WING, stack)) return 6f;
            else if (isItem(GenerationsItems.RAINBOW_WING, stack)) return 7f;
            else if (isItem(GenerationsItems.SILVER_WING, stack)) return 8f;
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

    private static void registerEntityRenderers() {
//        EntityRendererRegistry.register(PokeModEntities.PIXELMON.get(), PixelmonEntityRenderer::new);
//        EntityRendererRegistry.register(PokeModEntities.STARTER_PICK.get(), StarterPickEntityRenderer::new);
//        EntityRendererRegistry.register(PokeModEntities.PLAYER_NPC.get(), ctx -> new PlayerNpcEntityRenderer(ctx, true));
//        EntityRendererRegistry.register(PokeModEntities.POKEBALL_ENTITY.get(), PokeBallEntityRenderer::new);
        EntityRendererRegistry.register(GenerationsEntities.SEAT, SittableEntityRenderer::new);
//        EntityRendererRegistry.register(PokeModEntities.STATUE.get(), StatueEntityRenderer::new);
        EntityRendererRegistry.register(GenerationsEntities.TIERED_FISHING_BOBBER, TieredFishingHookRenderer::new);
        EntityRendererRegistry.register(GenerationsEntities.BOAT_ENTITY, GenerationsBoatRenderer::new);
        EntityRendererRegistry.register(GenerationsEntities.CHEST_BOAT_ENTITY, GenerationsChestBoatRenderer::new);
        EntityRendererRegistry.register(GenerationsEntities.MAGMA_CRYSTAL, ThrownItemRenderer::new);
    }
}
