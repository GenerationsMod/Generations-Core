package generations.gg.generations.core.generationscore.client;

import com.mojang.blaze3d.platform.MacosUtil;
import dev.architectury.registry.ReloadListenerRegistry;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import dev.architectury.registry.item.ItemPropertiesRegistry;
import dev.architectury.registry.menu.MenuRegistry;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.client.render.block.entity.*;
import generations.gg.generations.core.generationscore.client.render.entity.PokeModBoatRenderer;
import generations.gg.generations.core.generationscore.client.render.entity.PokeModChestBoatRenderer;
import generations.gg.generations.core.generationscore.client.render.entity.SittableEntityRenderer;
import generations.gg.generations.core.generationscore.client.render.entity.TieredFishingHookRenderer;
import generations.gg.generations.core.generationscore.client.render.rarecandy.ModelRegistry;
import generations.gg.generations.core.generationscore.client.render.rarecandy.Pipelines;
import generations.gg.generations.core.generationscore.client.screen.container.*;
import generations.gg.generations.core.generationscore.world.container.PixelmonContainers;
import generations.gg.generations.core.generationscore.world.entity.PokeModEntities;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.item.MelodyFluteItem;
import generations.gg.generations.core.generationscore.world.item.curry.CurryData;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsWoodTypes;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.io.IOException;

import static generations.gg.generations.core.generationscore.world.item.MelodyFluteItem.isItem;
import static net.minecraft.client.renderer.Sheets.createHangingSignMaterial;
import static net.minecraft.client.renderer.Sheets.createSignMaterial;

public class GenerationsCoreClient {
    public static void onInitialize(Minecraft minecraft) {
        GenerationsCoreClient.registerEntityRenderers();
        GenerationsCoreClient.registerBlockEntityRenderers();
        ModelRegistry.getRareCandy();

        Pipelines.REGISTER.register(Pipelines::initGenerationsPipelines);

        ReloadListenerRegistry.register(PackType.CLIENT_RESOURCES, (ResourceManagerReloadListener) Pipelines::onInitialize);

        GenerationsCoreClient.setupClient(minecraft);
    }

    private static void setupClient(Minecraft event) {
        event.submit(() -> {
            addWoodType(GenerationsWoodTypes.ULTRA_JUNGLE);
            addWoodType(GenerationsWoodTypes.ULTRA_DARK);
            addWoodType(GenerationsWoodTypes.GHOST);

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

            registerScreens();
        });
    }

    public static void addWoodType(WoodType woodType) {
        WoodType.register(woodType);
        Sheets.SIGN_MATERIALS.put(woodType, createSignMaterial(woodType));
        Sheets.HANGING_SIGN_MATERIALS.put(woodType, createHangingSignMaterial(woodType));
    }

    private static void registerScreens() {
        MenuRegistry.registerScreenFactory(PixelmonContainers.COOKING_POT.get(), CookingPotScreen::new);
        MenuRegistry.registerScreenFactory(PixelmonContainers.GENERIC.get(), GenericChestScreen::new);
        MenuRegistry.registerScreenFactory(PixelmonContainers.MACHINE_BLOCK.get(), MachineBlockScreen::new);
        MenuRegistry.registerScreenFactory(PixelmonContainers.MELODY_FLUTE.get(), MelodyFluteScreen::new);
        MenuRegistry.registerScreenFactory(PixelmonContainers.TRASHCAN.get(), TrashCanScreen::new);
    }

    private static void registerEntityRenderers() {
//        EntityRendererRegistry.register(PokeModEntities.PIXELMON.get(), PixelmonEntityRenderer::new);
//        EntityRendererRegistry.register(PokeModEntities.STARTER_PICK.get(), StarterPickEntityRenderer::new);
//        EntityRendererRegistry.register(PokeModEntities.PLAYER_NPC.get(), ctx -> new PlayerNpcEntityRenderer(ctx, true));
//        EntityRendererRegistry.register(PokeModEntities.POKEBALL_ENTITY.get(), PokeBallEntityRenderer::new);
        EntityRendererRegistry.register(PokeModEntities.SEAT, SittableEntityRenderer::new);
//        EntityRendererRegistry.register(PokeModEntities.STATUE.get(), StatueEntityRenderer::new);
        EntityRendererRegistry.register(PokeModEntities.TIERED_FISHING_BOBBER, TieredFishingHookRenderer::new);
        EntityRendererRegistry.register(PokeModEntities.BOAT_ENTITY, PokeModBoatRenderer::new);
        EntityRendererRegistry.register(PokeModEntities.CHEST_BOAT_ENTITY, PokeModChestBoatRenderer::new);
        EntityRendererRegistry.register(PokeModEntities.MAGMA_CRYSTAL, ThrownItemRenderer::new);
    }

    private static void registerBlockEntityRenderers() {
        BlockEntityRendererRegistry.register(GenerationsBlockEntities.POKE_DOLL.get(), GeneralUseBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(GenerationsBlockEntities.HEALER.get(), GeneralUseBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(GenerationsBlockEntities.CLOCK.get(), GeneralUseBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(GenerationsBlockEntities.BOX.get(), GeneralUseBlockEntityRenderer::new);

        BlockEntityRendererRegistry.register(GenerationsBlockEntities.TIMESPACE_ALTAR.get(), TimeSpaceAltarEntityRenderer::new);
        BlockEntityRendererRegistry.register(GenerationsBlockEntities.ABUNDANT_SHRINE.get(), GeneralUseBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(GenerationsBlockEntities.CELESTIAL_ALTAR.get(), GeneralUseBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(GenerationsBlockEntities.LUNAR_SHRINE.get(), GeneralUseBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(GenerationsBlockEntities.MELOETTA_MUSIC_BOX.get(), GeneralUseBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(GenerationsBlockEntities.REGIGIGAS_SHRINE.get(), RegigigasShrineBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(GenerationsBlockEntities.TAO_TRIO_SHRINE.get(), GeneralUseBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(GenerationsBlockEntities.TAPU_SHRINE.get(), GeneralUseBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(GenerationsBlockEntities.BREEDER.get(), BreederBlocEntityRenderer::new);

        BlockEntityRendererRegistry.register(GenerationsBlockEntities.COOKING_POT.get(), CookingPotRenderer::new);
        BlockEntityRendererRegistry.register(GenerationsBlockEntities.WEATHER_TRIO.get(), GeneralUseBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(GenerationsBlockEntities.SIGN_BLOCK_ENTITIES.get(), GenerationsSignRenderer::new);
//        BlockEntityRendererRegistry.register(GenerationsBlockEntities.HANGING_SIGN_BLOCK_ENTITIES.get(), HangingSignRenderer::new); TODO: JT I let you deal with this. ><
        BlockEntityRendererRegistry.register(GenerationsBlockEntities.GENERIC_CHEST.get(), GenericChestRenderer::new);
        BlockEntityRendererRegistry.register(GenerationsBlockEntities.GENERIC_SHRINE.get(), GeneralUseBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(GenerationsBlockEntities.GENERIC_DYED_VARIANT.get(), GeneralUseBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(GenerationsBlockEntities.GENERIC_MODEL_PROVIDING.get(), GeneralUseBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(GenerationsBlockEntities.VENDING_MACHINE.get(), GeneralUseBlockEntityRenderer::new);
    }

}
