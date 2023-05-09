package generations.gg.generations.core.generationscore.client;

import com.mojang.blaze3d.platform.MacosUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.pokemod.pokemod.api.data.player.PixelmonParty;
import com.pokemod.pokemod.client.battle.BattleClient;
import com.pokemod.pokemod.client.overlay.PartyOverlayRenderer;
import com.pokemod.pokemod.client.particle.PixelmonGlowParticle;
import com.pokemod.pokemod.client.particle.PokeBallGlowParticle;
import com.pokemod.pokemod.client.render.Pipelines;
import com.pokemod.pokemod.client.render.block.entity.*;
import com.pokemod.pokemod.client.render.entity.*;
import com.pokemod.pokemod.network.api.PokeModNetworking;
import com.pokemod.pokemod.network.packets.C2SSendOutPartyMemberPacket;
import com.pokemod.pokemod.network.protocol.PokeModClientPacketListener;
import com.pokemod.pokemod.world.container.*;
import com.pokemod.pokemod.world.item.curry.CurryData;
import com.pokemod.pokemod.world.level.block.PokeModWoodTypes;
import com.pokemod.pokemod.world.particle.PokeModParticles;
import com.pokemod.rarecandy.rendering.RareCandy;
import com.teamwizardry.animation.GameTime;
import dev.architectury.registry.client.keymappings.KeyMappingRegistry;
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
import generations.gg.generations.core.generationscore.client.screen.container.*;
import generations.gg.generations.core.generationscore.world.container.PixelmonContainers;
import generations.gg.generations.core.generationscore.world.entity.PokeModEntities;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.item.MelodyFluteItem;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;

import static generations.gg.generations.core.generationscore.world.item.MelodyFluteItem.isItem;

public class PokeModClient {
//    public static final GameTime GAME_TIME = new GameTime();
//    public static final GameTime WORLD_TIME = new GameTime();
//    private static RareCandy RENDERER;

    public static void onInitialize(IEventBus eventBus) {
        PokeModClient.registerEntityRenderers();
        PokeModClient.registerBlockEntityRenderers();
        PokeModClient.setupClient();
        PokeModClient.clientSetup();
    }

    private static void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            Minecraft.getInstance().particleEngine.register(PokeModParticles.POKE_BALL_GLOW.get(), new PokeBallGlowParticle.Provider());
            Minecraft.getInstance().particleEngine.register(PokeModParticles.PIXELMON_GLOW_SHRINK.get(), new PixelmonGlowParticle.Provider(true));
            Minecraft.getInstance().particleEngine.register(PokeModParticles.PIXELMON_GLOW_GROW.get(), new PixelmonGlowParticle.Provider(false));

            WoodType.register(PokeModWoodTypes.ULTRA_JUNGLE);
            WoodType.register(PokeModWoodTypes.ULTRA_DARK);
            WoodType.register(PokeModWoodTypes.GHOST);

            Sheets.addWoodType(PokeModWoodTypes.ULTRA_JUNGLE);
            Sheets.addWoodType(PokeModWoodTypes.ULTRA_DARK);
            Sheets.addWoodType(PokeModWoodTypes.GHOST);
        });

        PokeModClientPacketListener.getInstance().onFmlClientSetup();
        PokeModClient.updateTitle();
        ForgeConfig.CLIENT.alwaysSetupTerrainOffThread.set(true); // Performance improvement
        ForgeConfig.CLIENT.experimentalForgeLightPipelineEnabled.set(true); // Use Experimental Forge Light Pipeline
    }

    private static void renderLine(PoseStack poseStack, Vec3 pos1, Vec3 pos2, VertexConsumer vertexConsumer, BlockPos blockPos, Vec3 camPos, Vector4f color) {
        PoseStack.Pose pose = poseStack.last();
        float f = (float) (pos2.x - pos1.x);
        float f1 = (float) (pos2.y - pos1.y);
        float f2 = (float) (pos2.z - pos1.z);
        float f3 = Mth.sqrt(f * f + f1 * f1 + f2 * f2);
        f /= f3;
        f1 /= f3;
        f2 /= f3;
        vertexConsumer.vertex(pose.pose(), (float) (pos1.x + (blockPos.getX() - camPos.x + 0.5)), (float) (pos1.y + (blockPos.getY() + 0.5 - camPos.y)), (float) (pos1.z + (blockPos.getZ() - camPos.z + 0.5)))
                .color(color.x(), color.y(), color.z(), color.w()).normal(pose.normal(), f, f1, f2).endVertex();
        vertexConsumer.vertex(pose.pose(), (float) (pos2.x + (blockPos.getX() - camPos.x + 0.5)), (float) (pos2.y + (blockPos.getY() + 0.5 - camPos.y)), (float) (pos2.z + (blockPos.getZ() - camPos.z + 0.5)))
                .color(color.x(), color.y(), color.z(), color.w()).normal(pose.normal(), f, f1, f2).endVertex();
    }

    private static void setupClient(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
//            RunnableKeybind.create("key.throw_pokeball", GLFW.GLFW_KEY_R, "key.categories.pixelmon", () -> PokeModNetworking.sendPacket(new C2SSendOutPartyMemberPacket(Minecraft.getInstance().player)));
//            RunnableKeybind.create("key.next_party_member", GLFW.GLFW_KEY_DOWN, "key.categories.pixelmon", () -> movePartySelection(PixelmonParty.Direction.FORWARD));
//            RunnableKeybind.create("key.prev_party_member", GLFW.GLFW_KEY_UP, "key.categories.pixelmon", () -> movePartySelection(PixelmonParty.Direction.BACKWARD));
//            RunnableKeybind.create("key.toggle_overlay", GLFW.GLFW_KEY_I, "key.categories.pixelmon", PartyOverlayRenderer::toggleOverlay);

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

            PokeModClient.getRareCandy();
            Pipelines.onInitialize();
            registerScreens();
        });
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
        BlockEntityRendererRegistry.register(GenerationsBlockEntities.SIGN_BLOCK_ENTITIES.get(), context -> new GenerationsSignRenderer(context));
        BlockEntityRendererRegistry.register(GenerationsBlockEntities.HANGING_SIGN_BLOCK_ENTITIES.get(), HangingSignRenderer::new);
        BlockEntityRendererRegistry.register(GenerationsBlockEntities.GENERIC_CHEST.get(), GenericChestRenderer::new);
        BlockEntityRendererRegistry.register(GenerationsBlockEntities.GENERIC_SHRINE.get(), GeneralUseBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(GenerationsBlockEntities.GENERIC_DYED_VARIANT.get(), GeneralUseBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(GenerationsBlockEntities.GENERIC_MODEL_PROVIDING.get(), GeneralUseBlockEntityRenderer::new);
        BlockEntityRendererRegistry.register(GenerationsBlockEntities.VENDING_MACHINE.get(), GeneralUseBlockEntityRenderer::new);
    }

    private static void updateTitle() {
        try {
            var client = Minecraft.getInstance();
            var filePrefix = Minecraft.getInstance().getLaunchedVersion().equalsIgnoreCase("mod_dev") ? "dev-" : "";
            if (Minecraft.ON_OSX){
                MacosUtil.loadIcon(() -> client.getResourceManager().getResource(GenerationsCore.id("textures/logo/" + filePrefix + "logo-32x.icns")).orElseThrow().open());
            } else {
                client.getWindow().setIcon(
                        () -> client.getResourceManager().getResource(GenerationsCore.id("textures/logo/logo-16x.png")).orElseThrow().open(),
                        () -> client.getResourceManager().getResource(GenerationsCore.id("textures/logo/" + filePrefix + "logo-32x.png")).orElseThrow().open()
                );
            }
        } catch (IOException e) {
            GenerationsCore.LOGGER.info("Failed to load icon", e);
        }
    }

//    public static RareCandy getRareCandy() {
//        //RareCandy.DEBUG_THREADS = true;
//        if (PokeModClient.RENDERER == null) PokeModClient.RENDERER = new RareCandy();
//        Animation.animationModifier = (animation, s) -> {
//            if (s.equals("gfb")) animation.ticksPerSecond = 60_000; // 60 fps. 1000 ticks per frame?
//        };
//        return RENDERER;
//    }
}
