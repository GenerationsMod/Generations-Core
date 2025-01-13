package generations.gg.generations.core.generationscore.common.client;

import com.cobblemon.mod.common.CobblemonItems;
import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.api.spawning.TimeRange;
import com.cobblemon.mod.common.api.types.ElementalTypes;
import com.cobblemon.mod.common.client.render.item.CobblemonBuiltinItemRendererRegistry;
import com.cobblemon.mod.common.client.render.models.blockbench.pokeball.PokeBallModel;
import com.cobblemon.mod.common.client.render.models.blockbench.pose.Bone;
import com.cobblemon.mod.common.client.render.models.blockbench.repository.PokeBallModelRepository;
import com.cobblemon.mod.common.client.render.models.blockbench.repository.VaryingModelRepository;
import com.cobblemon.mod.common.platform.events.ClientPlayerEvent;
import com.cobblemon.mod.common.platform.events.PlatformEvents;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.architectury.registry.item.ItemPropertiesRegistry;
import dev.architectury.registry.menu.MenuRegistry;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.GenerationsDataProvider;
import generations.gg.generations.core.generationscore.common.client.model.RareCandyBone;
import generations.gg.generations.core.generationscore.common.client.model.inventory.GenericChestItemStackRenderer;
import generations.gg.generations.core.generationscore.common.client.render.TimeCapsuleItemRenderer;
import generations.gg.generations.core.generationscore.common.client.render.block.entity.*;
import generations.gg.generations.core.generationscore.common.client.render.entity.*;
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.MinecraftClientGameProvider;
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.ModelRegistry;
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.Pipelines;
import generations.gg.generations.core.generationscore.common.client.screen.container.*;
import generations.gg.generations.core.generationscore.common.world.container.GenerationsContainers;
import generations.gg.generations.core.generationscore.common.world.entity.GenerationsBoatEntity;
import generations.gg.generations.core.generationscore.common.world.entity.GenerationsEntities;
import generations.gg.generations.core.generationscore.common.world.item.*;
import generations.gg.generations.core.generationscore.common.world.item.curry.CurryData;
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks;
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsShrines;
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsWoodTypes;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.GenericChestBlockEntity;
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericChestBlock;
import gg.generations.rarecandy.pokeutils.reader.ITextureLoader;
import gg.generations.rarecandy.renderer.rendering.RareCandy;
import kotlin.Unit;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.Tuple;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.joml.Vector4f;

import java.io.File;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import static dev.architectury.registry.item.ItemPropertiesRegistry.register;
import static generations.gg.generations.core.generationscore.common.GenerationsCore.LOGGER;
import static generations.gg.generations.core.generationscore.common.world.item.MelodyFluteItem.isItem;
import static net.minecraft.client.renderer.Sheets.createHangingSignMaterial;
import static net.minecraft.client.renderer.Sheets.createSignMaterial;

public class GenerationsCoreClient {

    public static void onInitialize(Minecraft minecraft) {
        if (GenerationsCore.CONFIG.client.useRenderDoc) {
            try {
                System.loadLibrary("renderdoc");
            } catch (UnsatisfiedLinkError e) {
                LOGGER.warn("Attempted to use renderdoc without renderdoc installed.");
            }
        }

        ModelRegistry.init();

        ITextureLoader.setInstance(GenerationsTextureLoader.INSTANCE);

        var renderer = TimeCapsuleItemRenderer.INSTANCE;

        CobblemonBuiltinItemRendererRegistry.INSTANCE.register(GenerationsItems.TIME_CAPSULE.get(), renderer);
        CobblemonBuiltinItemRendererRegistry.INSTANCE.register(GenerationsItems.SUICUNE_STATUE.get(), renderer);
        CobblemonBuiltinItemRendererRegistry.INSTANCE.register(GenerationsItems.RAIKOU_STATUE.get(), renderer);
        CobblemonBuiltinItemRendererRegistry.INSTANCE.register(GenerationsItems.ENTEI_STATUE.get(), renderer);
        CobblemonBuiltinItemRendererRegistry.INSTANCE.register(CobblemonItems.POKEMON_MODEL, renderer);

//      ReloadListenerRegistry.register(PackType.CLIENT_RESOURCES, (ResourceManagerReloadListener) Pipelines::onInitialize);
        GenerationsCoreClient.setupClient(minecraft);
        RareCandy.DEBUG_THREADS = true;

        PokeBallModelRepository.INSTANCE.inbuilt("strange_ball", PokeBallModel::new);

        VaryingModelRepository.Companion.registerFactory(".pk", (resourceLocation, resource) -> new Tuple<>(new ResourceLocation(resourceLocation.getNamespace(), new File(resourceLocation.getPath()).getName()), b -> (Bone) new ModelPart(RareCandyBone.Companion.getCUBE_LIST(), Map.of("root", new RareCandyBone(resourceLocation)))));

//        GenerationsCore.implementation.registerResourceReloader(
//                id("texture_loader"),
//                (ResourceManagerReloadListener) resourceManager -> textureLoader.initialize(resourceManager),
//                PackType.CLIENT_RESOURCES,
//                emptyList());

        PlatformEvents.CLIENT_PLAYER_LOGIN.subscribe(Priority.NORMAL, GenerationsCoreClient::onLogin);
        PlatformEvents.CLIENT_PLAYER_LOGOUT.subscribe(Priority.NORMAL, GenerationsCoreClient::onLogout);
    }

    private static void setupClient(Minecraft event) {
        event.tell(() -> {
            addWoodType(GenerationsWoodTypes.ULTRA_JUNGLE);
            addWoodType(GenerationsWoodTypes.ULTRA_DARK);
            addWoodType(GenerationsWoodTypes.GHOST);
            Pipelines.REGISTER.register(Pipelines::initGenerationsPipelines);

            Pipelines.onInitialize(event.getResourceManager());
            registerScreens();
        });

        ItemPropertiesRegistry.registerGeneric(GenerationsCore.id("type"), (arg, arg2, arg3, i) -> {
            var type = ((MoveTeachingItem) arg.getItem()).getType(arg);

            if(type == ElementalTypes.INSTANCE.getNORMAL()) return 0.00f;
            else if(type == ElementalTypes.INSTANCE.getFIRE()) return 0.01f;
            else if(type == ElementalTypes.INSTANCE.getWATER()) return 0.02f;
            else if(type == ElementalTypes.INSTANCE.getGRASS()) return 0.03f;
            else if(type == ElementalTypes.INSTANCE.getELECTRIC()) return 0.04f;
            else if(type == ElementalTypes.INSTANCE.getICE()) return 0.05f;
            else if(type == ElementalTypes.INSTANCE.getFIGHTING()) return 0.06f;
            else if(type == ElementalTypes.INSTANCE.getPOISON()) return 0.07f;
            else if(type == ElementalTypes.INSTANCE.getGROUND()) return 0.08f;
            else if(type == ElementalTypes.INSTANCE.getFLYING()) return 0.09f;
            else if(type == ElementalTypes.INSTANCE.getPSYCHIC()) return 0.10f;
            else if(type == ElementalTypes.INSTANCE.getBUG()) return 0.11f;
            else if(type == ElementalTypes.INSTANCE.getROCK()) return 0.12f;
            else if(type == ElementalTypes.INSTANCE.getGHOST()) return 0.13f;
            else if(type == ElementalTypes.INSTANCE.getDRAGON()) return 0.14f;
            else if(type == ElementalTypes.INSTANCE.getDARK()) return 0.15f;
            else if(type == ElementalTypes.INSTANCE.getSTEEL()) return 0.16f;
            else if(type == ElementalTypes.INSTANCE.getFAIRY()) return 0.17f;
            else return 0.00f;
        });

        register(GenerationsItems.CURRY.get(), GenerationsCore.id("curry_type"), (arg, arg2, arg3, i) -> CurryData.fromNbt(arg.getOrCreateTag()).getCurryType().ordinal());
        register(GenerationsItems.MELODY_FLUTE.get(), GenerationsCore.id("flute_type"), (arg, arg2, arg3, i) -> {
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

        register(GenerationsShrines.CELESTIAL_ALTAR.get(), GenerationsCore.id("time"), (itemStack, clientLevel, livingEntity, i) -> {
                Entity entity = livingEntity != null ? livingEntity : itemStack.getEntityRepresentation();
                if (entity == null) {
                    return 0.0F;
                } else {
                    if (clientLevel == null && entity.level() instanceof ClientLevel level) {
                        clientLevel = level;
                    }

                    if (clientLevel == null) {
                        return 0.0F;
                    } else {
                        double d;
                        if (clientLevel.dimensionType().natural()) {
                            d = TimeRange.Companion.getTimeRanges().get("day").contains((int) clientLevel.dayTime()) ? 0.1f : 0.2f;
                        } else {
                            d = 0F;
                        }

                        return (float)d;
                    }
                }
            });

        register(GenerationsShrines.LUNAR_SHRINE.get(), GenerationsCore.id("light_level"), (itemStack, clientLevel, livingEntity, i) -> {
            Entity entity = livingEntity != null ? livingEntity : itemStack.getEntityRepresentation();
            if (entity == null) return 0.0F;
            else {
                if (clientLevel == null && entity.level() instanceof ClientLevel level) clientLevel = level;
                return clientLevel == null ? 0.0F : clientLevel.getMaxLocalRawBrightness(entity.blockPosition()) >= 10 ? 0.1f : 0F;
            }
        });

        TimeCapsule.Companion.registerItemProperty();

        registerFishingRod(GenerationsItems.OLD_ROD);
        registerFishingRod(GenerationsItems.GOOD_ROD);
        registerFishingRod(GenerationsItems.SUPER_ROD);
        registerFishingRod(GenerationsItems.RUBY_ROD);

//        ItemPropertiesRegistry.register(GenerationsShrines.LUNAR_SHRINE.get(), GenerationsCore.id("lit"), (itemStack, clientLevel, livingEntity, i) -> clientLevel.isDay() ? 0.0f : 1.0f);

        registerChestRenderer(GenerationsBlocks.POKEBALL_CHEST.get());
        registerChestRenderer(GenerationsBlocks.GREATBALL_CHEST.get());
        registerChestRenderer(GenerationsBlocks.ULTRABALL_CHEST.get());
        registerChestRenderer(GenerationsBlocks.MASTERBALL_CHEST.get());
    }

    private static <T extends Item> void registerFishingRod(Supplier<T> item) {
        register(item.get(), GenerationsCore.id("cast"), (arg, arg2, arg3, i) -> {
            if (arg3 == null) {
                return 0.0F;
            } else {
                boolean flag = arg3.getMainHandItem() == arg;
                boolean flag1 = arg3.getOffhandItem() == arg;
                if (arg3.getMainHandItem().getItem() instanceof TieredFishingRodItem) {
                    flag1 = false;
                }

                return (flag || flag1) && arg3 instanceof Player player && player.fishing != null ? 1.0F : 0.0F;
            }
        });
    }

    private static void registerChestRenderer(GenericChestBlock chest) {
        CobblemonBuiltinItemRendererRegistry.INSTANCE.register(chest.asItem(), new GenericChestItemStackRenderer(() -> new GenericChestBlockEntity(BlockPos.ZERO, chest.defaultBlockState())));
    }

    private static void addWoodType(WoodType woodType) {
        Sheets.SIGN_MATERIALS.put(woodType, createSignMaterial(woodType));
        Sheets.HANGING_SIGN_MATERIALS.put(woodType, createHangingSignMaterial(woodType));
    }

    private static void registerScreens() {
        MenuRegistry.registerScreenFactory(GenerationsContainers.COOKING_POT.get(), CookingPotScreen::new);
        MenuRegistry.registerScreenFactory(GenerationsContainers.GENERIC.get(), GenericChestScreen::new);
        MenuRegistry.registerScreenFactory(GenerationsContainers.WALKMON.get(), GenericChestScreen::new);
        MenuRegistry.registerScreenFactory(GenerationsContainers.CALYREX_STEED.get(), GenericChestScreen::new);
        MenuRegistry.registerScreenFactory(GenerationsContainers.MACHINE_BLOCK.get(), MachineBlockScreen::new);
        MenuRegistry.registerScreenFactory(GenerationsContainers.MELODY_FLUTE.get(), MelodyFluteScreen::new);
        MenuRegistry.registerScreenFactory(GenerationsContainers.TRASHCAN.get(), TrashCanScreen::new);
        MenuRegistry.registerScreenFactory(GenerationsContainers.RKS_MACHINE.get(), RksMachineScreen::new);
    }

    /**
     * Registers the key renderers.
     * @see GenerationsEntities
     */
    public static void registerEntityRenderers(BiConsumer<EntityType<? extends Entity>, EntityRendererProvider> consumer) {
        consumer.accept(GenerationsEntities.SEAT.get(), SittableEntityRenderer::new);
        consumer.accept(GenerationsEntities.TIERED_FISHING_BOBBER.get(), TieredFishingHookRenderer::new);
        consumer.accept(GenerationsEntities.BOAT_ENTITY.get(), (EntityRendererProvider.Context context) -> new GenerationsBoatRenderer(context, false));
        consumer.accept(GenerationsEntities.CHEST_BOAT_ENTITY.get(), context -> new GenerationsBoatRenderer(context, true));
        consumer.accept(GenerationsEntities.MAGMA_CRYSTAL.get(), ThrownItemRenderer::new);
        consumer.accept(GenerationsEntities.STATUE_ENTITY.get(), StatueEntityRenderer::new);
        consumer.accept(GenerationsEntities.ZYGARDE_CELL.get(), ZygardeCellRenderer::new);
    }

    /**
     * Registers the block key renderers.
     * @see BlockEntityRenderers
     * @see GenerationsBlockEntities
     */
    public static void registerBlockEntityRenderers(BiConsumer<BlockEntityType<? extends BlockEntity>, BlockEntityRendererProvider> consumer) {
        consumer.accept(GenerationsBlockEntities.POKE_DOLL.get(), GeneralUseBlockEntityRenderer::new);

        consumer.accept(GenerationsBlockEntities.TIMESPACE_ALTAR.get(), TimeSpaceAltarEntityRenderer::new);
        consumer.accept(GenerationsBlockEntities.ABUNDANT_SHRINE.get(), GeneralUseBlockEntityRenderer::new);
        consumer.accept(GenerationsBlockEntities.CELESTIAL_ALTAR.get(), GeneralUseBlockEntityRenderer::new);
        consumer.accept(GenerationsBlockEntities.LUNAR_SHRINE.get(), GeneralUseBlockEntityRenderer::new);
        consumer.accept(GenerationsBlockEntities.MELOETTA_MUSIC_BOX.get(), GeneralUseBlockEntityRenderer::new);
        consumer.accept(GenerationsBlockEntities.REGIGIGAS_SHRINE.get(), RegigigasShrineBlockEntityRenderer::new);
        consumer.accept(GenerationsBlockEntities.TAO_TRIO_SHRINE.get(), GeneralUseBlockEntityRenderer::new);
        consumer.accept(GenerationsBlockEntities.TAPU_SHRINE.get(), GeneralUseBlockEntityRenderer::new);
        consumer.accept(GenerationsBlockEntities.INTERACT_SHRINE.get(), GeneralUseBlockEntityRenderer::new);
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
        consumer.accept(GenerationsBlockEntities.BALL_LOOT.get(), PokeLootRendrer::new);
        consumer.accept(GenerationsBlockEntities.RKS_MACHINE.get(), GeneralUseBlockEntityRenderer::new);
        consumer.accept(GenerationsBlockEntities.PC.get(), GeneralUseBlockEntityRenderer::new);
        consumer.accept(GenerationsBlockEntities.DYED_PC.get(), GeneralUseBlockEntityRenderer::new);

        consumer.accept(GenerationsBlockEntities.COUCH.get(), GeneralUseBlockEntityRenderer::new);
        consumer.accept(GenerationsBlockEntities.STREET_LAMP.get(), GeneralUseBlockEntityRenderer::new);
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
        GenerationsDataProvider.INSTANCE.canReload = false;
        return Unit.INSTANCE;
    }

    public static Unit onLogout(ClientPlayerEvent.Logout logout) {
        GenerationsDataProvider.INSTANCE.canReload = true;
        return Unit.INSTANCE;
    }

    public static void renderHighlightedPath(PoseStack poseStack, int renderTick, Camera camera) {
            Player player = Minecraft.getInstance().player;
            if (player == null) return;
            ItemStack heldItem = player.getItemInHand(InteractionHand.MAIN_HAND);
            if (!(heldItem.getItem() instanceof NpcPathTool)) {
                return;
            }

            // Get the block positions to highlight.
            var blockPositions = NpcPathTool.getPath(heldItem);

            // Set up variables for drawing the path lines.
            float speed = 10;
            float percentage = 0;
            float holdTimeMod = 2.5f; // Determines how long the path ray will remain.
            float percPerSegment = -1;
            if (blockPositions.size() > 1) {
                int numEdges = blockPositions.size() - 1;
                percentage = (renderTick % (numEdges * speed * holdTimeMod)) / (numEdges * speed);
                percPerSegment = (1.0f / numEdges);
            }
            BlockPos prevBlockPos = null;

            // Set up the drawing objects.
            poseStack.pushPose();
            MultiBufferSource.BufferSource buffers = Minecraft.getInstance().renderBuffers().bufferSource();
            VertexConsumer vertexconsumer = buffers.getBuffer(RenderType.lines());
            Vector4f color = new Vector4f(227F / 255, 28F / 255, 121F / 255, 0.8F);

            for (BlockPos blockPos : blockPositions) {
                BlockState blockstate = player.level().getBlockState(blockPos);
                // figure out the percentage of the segment to draw.
                float segmentPercentage;
                if (prevBlockPos == null) {
                    segmentPercentage = 0;
                } else if (percPerSegment > percentage) {
                    segmentPercentage = percentage / percPerSegment;
                    percentage = 0;
                } else {
                    percentage -= percPerSegment;
                    segmentPercentage = 1;
                }
                // Draw the block highlight.
                if (!blockstate.isAir()) {
                    renderHitOutline(
                            poseStack, vertexconsumer, player.level(), camera, blockPos, blockstate, color);
                }
                // Draw the animated path segment.
                if (prevBlockPos != null && segmentPercentage > 0) {
                    Vec3 pos1 = new Vec3(0, 0, 0);
                    Vec3 pos2 = new Vec3(blockPos.getX() - prevBlockPos.getX(), blockPos.getY() - prevBlockPos.getY(), blockPos.getZ() - prevBlockPos.getZ());
                    pos2 = pos2.multiply(segmentPercentage, segmentPercentage, segmentPercentage);
                    renderLine(poseStack, pos1, pos2, vertexconsumer, prevBlockPos.above(), camera.getPosition(), color);
                }
                prevBlockPos = blockPos;
            }

            buffers.endBatch();
            poseStack.popPose();
    }

    private static void renderShape(PoseStack poseStack, VertexConsumer vertexConsumer, VoxelShape voxelShape, double x, double y, double z, Vector4f color) {
        PoseStack.Pose pose = poseStack.last();
        // Create the vertex consumer shape by creating every edge.
        voxelShape.forAllEdges((x1, y1, z1, x2, y2, z2) -> {
            float f = (float) (x2 - x1);
            float f1 = (float) (y2 - y1);
            float f2 = (float) (z2 - z1);
            float f3 = Mth.sqrt(f * f + f1 * f1 + f2 * f2);
            f /= f3;
            f1 /= f3;
            f2 /= f3;
            vertexConsumer.vertex(pose.pose(), (float) (x1 + x), (float) (y1 + y), (float) (z1 + z))
                    .color(color.x(), color.y(), color.z(), color.w()).normal(pose.normal(), f, f1, f2).endVertex();
            vertexConsumer.vertex(pose.pose(), (float) (x2 + x), (float) (y2 + y), (float) (z2 + z))
                    .color(color.x(), color.y(), color.z(), color.w()).normal(pose.normal(), f, f1, f2).endVertex();
        });
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


    private static void renderHitOutline(PoseStack poseStack, VertexConsumer vertexConsumer, Level level, Camera camera, BlockPos blockPos, BlockState blockState, Vector4f color) {
        Position pos = camera.getPosition();
        renderShape(poseStack, vertexConsumer, blockState.getShape(level, blockPos, CollisionContext.of(camera.getEntity())),
                (double) blockPos.getX() - pos.x(), (double) blockPos.getY() - pos.y(), (double) blockPos.getZ() - pos.z(), color);
    }

    public static void renderRareCandy(ClientLevel level) {
        if(GenerationsCore.CONFIG.client.useVanilla) return;

        var startTime = System.currentTimeMillis();
        assert level != null;
        level.getProfiler().popPush("render_models");
        RenderSystem.enableDepthTest();
        BufferUploader.reset();

        ModelRegistry.getWorldRareCandy().render(true, MinecraftClientGameProvider.getTimePassed());
        if (shouldRenderFpsPie()) LOGGER.warn("RareCandy render took " + (System.currentTimeMillis() - startTime) + "ms");
    }

    private static boolean shouldRenderFpsPie() {
        return Minecraft.getInstance().options.renderDebug && Minecraft.getInstance().options.renderDebugCharts && !Minecraft.getInstance().options.hideGui;
    }

}
