package generations.gg.generations.core.generationscore.client;

import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.api.types.ElementalTypes;
import com.cobblemon.mod.common.client.render.item.CobblemonBuiltinItemRendererRegistry;
import com.cobblemon.mod.common.client.render.models.blockbench.pokemon.JsonPokemonPoseableModel;
import com.cobblemon.mod.common.client.render.models.blockbench.repository.VaryingModelRepository;
import com.cobblemon.mod.common.platform.events.ClientPlayerEvent;
import com.cobblemon.mod.common.platform.events.PlatformEvents;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.architectury.registry.item.ItemPropertiesRegistry;
import dev.architectury.registry.menu.MenuRegistry;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.GenerationsDataProvider;
import generations.gg.generations.core.generationscore.client.model.RareCandyAnimationFactory;
import generations.gg.generations.core.generationscore.client.model.RareCandyBone;
import generations.gg.generations.core.generationscore.client.model.inventory.GenericChestItemStackRenderer;
import generations.gg.generations.core.generationscore.client.render.block.entity.*;
import generations.gg.generations.core.generationscore.client.render.entity.*;
import generations.gg.generations.core.generationscore.client.render.rarecandy.MinecraftClientGameProvider;
import generations.gg.generations.core.generationscore.client.render.rarecandy.ModelRegistry;
import generations.gg.generations.core.generationscore.client.render.rarecandy.Pipelines;
import generations.gg.generations.core.generationscore.client.screen.container.*;
import generations.gg.generations.core.generationscore.world.container.GenerationsContainers;
import generations.gg.generations.core.generationscore.world.entity.GenerationsBoatEntity;
import generations.gg.generations.core.generationscore.world.entity.GenerationsEntities;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.item.MelodyFluteItem;
import generations.gg.generations.core.generationscore.world.item.MoveTeachingItem;
import generations.gg.generations.core.generationscore.world.item.NpcPathTool;
import generations.gg.generations.core.generationscore.world.item.curry.CurryData;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsBlocks;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsWoodTypes;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.generic.GenericChestBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericChestBlock;
import gg.generations.rarecandy.pokeutils.reader.ITextureLoader;
import gg.generations.rarecandy.pokeutils.reader.TextureReference;
import gg.generations.rarecandy.renderer.loading.ITexture;
import gg.generations.rarecandy.tools.TextureLoader;
import kotlin.Unit;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.Mth;
import net.minecraft.util.Tuple;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.joml.Vector4f;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import static generations.gg.generations.core.generationscore.GenerationsCore.id;
import static generations.gg.generations.core.generationscore.world.item.MelodyFluteItem.isItem;
import static java.util.Collections.emptyList;
import static net.minecraft.client.renderer.Sheets.createHangingSignMaterial;
import static net.minecraft.client.renderer.Sheets.createSignMaterial;

public class GenerationsCoreClient {
    private static final TypeToken<Map<String, String>> RARE_CANDY_TYPE = new TypeToken<>() {
    };

    public static GenerationsTextureLoader textureLoader;

    public static void onInitialize(Minecraft minecraft) {
//        System.loadLibrary("renderdoc");
//      ReloadListenerRegistry.register(PackType.CLIENT_RESOURCES, (ResourceManagerReloadListener) Pipelines::onInitialize);
        GenerationsCoreClient.setupClient(minecraft);

        JsonPokemonPoseableModel.Companion.registerFactory("pk", new RareCandyAnimationFactory());

        VaryingModelRepository.Companion.registerFactory(".pk", (resourceLocation, resource) -> new Tuple<>(new ResourceLocation(resourceLocation.getNamespace(), new File(resourceLocation.getPath()).getName()), b -> new RareCandyBone(resourceLocation)));


        GenerationsCore.implementation.registerResourceReloader(
                id("model_registry"),
                (ResourceManagerReloadListener) resourceManager -> {
                    ModelRegistry.LOADER.invalidateAll();
                    ModelRegistry.LOADER.cleanUp();
                },
                PackType.CLIENT_RESOURCES,
                emptyList());

        GenerationsCore.implementation.registerResourceReloader(
                id("texture_loader"),
                (ResourceManagerReloadListener) resourceManager -> textureLoader.initialize(resourceManager),
                PackType.CLIENT_RESOURCES,
                emptyList());

        PlatformEvents.CLIENT_PLAYER_LOGIN.subscribe(Priority.NORMAL, GenerationsCoreClient::onLogin);
        PlatformEvents.CLIENT_PLAYER_LOGOUT.subscribe(Priority.NORMAL, GenerationsCoreClient::onLogout);
    }

    private static void setupClient(Minecraft event) {
        event.tell(() -> {
            addWoodType(GenerationsWoodTypes.ULTRA_JUNGLE);
            addWoodType(GenerationsWoodTypes.ULTRA_DARK);
            addWoodType(GenerationsWoodTypes.GHOST);
            Pipelines.REGISTER.register(Pipelines::initGenerationsPipelines);

            TextureLoader.setInstance(textureLoader = new GenerationsTextureLoader(event));
            textureLoader.initialize(event.getResourceManager());

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

        registerChestRenderer(GenerationsBlocks.POKEBALL_CHEST.get());
        registerChestRenderer(GenerationsBlocks.GREATBALL_CHEST.get());
        registerChestRenderer(GenerationsBlocks.ULTRABALL_CHEST.get());
        registerChestRenderer(GenerationsBlocks.MASTERBALL_CHEST.get());
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
        consumer.accept(GenerationsEntities.PLAYER_NPC.get(), context -> new PlayerNpcEntityRenderer(context, true));
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

    public static void renderRareCandy() {
        ModelRegistry.getWorldRareCandy().render(true, MinecraftClientGameProvider.getTimePassed());
    }

    public static class GenerationsTextureLoader extends ITextureLoader {
        final Map<String, ResourceLocation> MAP = new HashMap<>();

        public GenerationsTextureLoader(Minecraft minecraft) {}

        public void initialize(ResourceManager manager) {
            clear();
            var gson = new Gson();

            try {
                for(var namespace : manager.getNamespaces()) {
                    var list = manager.getResourceStack(new ResourceLocation(namespace, "rare_candy_texture.json"));

                    for(var resource : list) {
                        try(var reader = resource.openAsReader()) {
                            var map = GsonHelper.fromJson(gson, reader, RARE_CANDY_TYPE);

                            map.forEach((key, value) -> register(key, new TextureReference(fromResourceLocation(manager, Objects.requireNonNull(ResourceLocation.tryParse(value))), key)));

                        } catch (IOException e) {

                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
//
//            register("dark", new TextureReference(fromColor("000000"), "dark"));
//            register("bright", new TextureReference(fromColor("ffffff"), "bright"));
//            register("neutral", new TextureReference(fromColor("999999"), "neutral"));
//
//            register("concrete", new TextureReference(fromResourceLocationStatue(manager, GenerationsCore.id("concrete")), "concrete"));
//            register("glass", new TextureReference(fromResourceLocationStatue(manager, GenerationsCore.id("glass")), "glass"));
//            register("gold", new TextureReference(fromResourceLocationStatue(manager, GenerationsCore.id("gold")), "gold"));
//            register("marble", new TextureReference(fromResourceLocationStatue(manager, GenerationsCore.id("marble")), "marble"));
//            register("metal", new TextureReference(fromResourceLocationStatue(manager, GenerationsCore.id("metal")), "metal"));
//            register("moss", new TextureReference(fromResourceLocationStatue(manager, GenerationsCore.id("moss")), "moss"));
//            register("silver", new TextureReference(fromResourceLocationStatue(manager, GenerationsCore.id("silver")), "silver"));
//
//            register("couch_diffuse", new TextureReference(fromResourceLocationExternal(manager, GenerationsCore.id("couch_diffuse"));
        }

        private BufferedImage fromResourceLocation(ResourceManager manager, ResourceLocation location) {
            try {
                return ImageIO.read(manager.getResourceOrThrow(new ResourceLocation("%s:textures/%s.png".formatted(location.getNamespace(), location.getPath()))).open());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
//
//        private BufferedImage fromResourceLocationStatue(ResourceManager manager, ResourceLocation location) {
//            return fromResourceLocation(manager, new ResourceLocation("%s:textures/entity/statue_material/%s.png".formatted(location.getNamespace(), location.getPath()))).open())
//        }
//
//        private BufferedImage fromResourceLocationExternal(ResourceManager manager, ResourceLocation location) {
//            return fromResourceLocation(manager, new ResourceLocation("%s:textures/pk_eternal/%s.png".formatted(location.getNamespace(), location.getPath()))).open())
//        }
//
//
//        private static BufferedImage fromColor(String color) {
//            int colorValue = Integer.parseInt(color.replace("#", ""), 16);
//
//            var image = new BufferedImage(2, 2, BufferedImage.TYPE_INT_ARGB);
//            image.setRGB(0, 0, colorValue);
//            image.setRGB(0, 1, colorValue);
//            image.setRGB(1, 0, colorValue);
//            image.setRGB(1, 1, colorValue);
//            return image;
//        }

        @Override
        public ITexture getTexture(String s) {
            var texture = MAP.getOrDefault(s, null);
            if(texture != null) {
                var image = Minecraft.getInstance().getTextureManager().getTexture(MAP.get(s));
                if(image instanceof ITexture) {
                    return (ITexture) image;
                }
            }

            return null;
        }

        @Override
        public void register(String s, ITexture iTexture) {
            if(iTexture instanceof DynamicTexture texture) {
                var location = Minecraft.getInstance().getTextureManager().register(s.replace(":", "_"), texture);
                MAP.putIfAbsent(s, location);
            }
        }

        @Override
        public void register(String s, TextureReference textureReference) {
            register(s, new generations.gg.generations.core.generationscore.client.render.rarecandy.Texture(textureReference));
        }

        @Override
        protected ITexture loadFromReference(TextureReference textureReference) {
            return null;
        }

        @Override
        public void remove(String s) {
            Minecraft.getInstance().getTextureManager().release(MAP.remove(s));
        }

        @Override
        public void clear() {
            var manager = Minecraft.getInstance().getTextureManager();

            for (var entry : MAP.keySet()) {
                manager.release(MAP.get(entry));
            }

            MAP.clear();
        }

        @Override
        public TextureReference generateDirectReference(String s) {
            return null;
        }

        @Override
        public ITexture getDarkFallback() {
            return getTexture("dark");
        }

        @Override
        public ITexture getBrightFallback() {
            return getTexture("light");
        }

        @Override
        public ITexture getNuetralFallback() {
            return getTexture("neutral");
        }

        @Override
        public Set<String> getTextureEntries() {
            return null;
        }

        public boolean has(String texture) {
            return MAP.containsKey(texture);
        }

        public ResourceLocation getLocation(ResourceLocation material) {
            return MAP.getOrDefault(material.toString(), null);
        }
    }
}
