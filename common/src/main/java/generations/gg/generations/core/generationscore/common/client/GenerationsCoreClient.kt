package generations.gg.generations.core.generationscore.common.client

import com.cobblemon.mod.common.api.Priority
import com.cobblemon.mod.common.api.spawning.TimeRange
import com.cobblemon.mod.common.api.types.ElementalTypes
import com.cobblemon.mod.common.client.render.item.CobblemonBuiltinItemRendererRegistry
import com.cobblemon.mod.common.client.render.item.PokemonItemRenderer
import com.cobblemon.mod.common.client.render.models.blockbench.pokeball.PokeBallModel
import com.cobblemon.mod.common.client.render.models.blockbench.pose.Bone
import com.cobblemon.mod.common.client.render.models.blockbench.repository.PokeBallModelRepository
import com.cobblemon.mod.common.client.render.models.blockbench.repository.VaryingModelRepository
import com.cobblemon.mod.common.platform.events.ClientPlayerEvent
import com.cobblemon.mod.common.platform.events.PlatformEvents
import com.cobblemon.mod.common.util.toVec3d
import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.BufferUploader
import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.GenerationsCore.LOGGER
import generations.gg.generations.core.generationscore.common.client.model.GenerationsClientMolangFunctions
import generations.gg.generations.core.generationscore.common.client.model.RareCandyBone
import generations.gg.generations.core.generationscore.common.client.model.inventory.GenericChestItemStackRenderer
import generations.gg.generations.core.generationscore.common.client.render.block.entity.*
import generations.gg.generations.core.generationscore.common.client.render.entity.*
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.MinecraftClientGameProvider
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.ModelRegistry
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.Pipelines
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.instanceOrNull
import generations.gg.generations.core.generationscore.common.client.screen.container.*
import generations.gg.generations.core.generationscore.common.orFalse
import generations.gg.generations.core.generationscore.common.world.container.GenerationsContainers
import generations.gg.generations.core.generationscore.common.world.entity.GenerationsBoatEntity
import generations.gg.generations.core.generationscore.common.world.entity.GenerationsEntities
import generations.gg.generations.core.generationscore.common.world.item.*
import generations.gg.generations.core.generationscore.common.world.item.MelodyFluteItem.Companion.isItem
import generations.gg.generations.core.generationscore.common.world.item.components.GenerationsDataComponents
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsShrines
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsWoodTypes
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.GenericChestBlockEntity
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericChestBlock
import gg.generations.rarecandy.pokeutils.reader.ITextureLoader
import gg.generations.rarecandy.renderer.rendering.RareCandy
import gg.generations.rarecandy.renderer.rendering.RenderStage
import net.minecraft.client.Camera
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.screens.MenuScreens
import net.minecraft.client.model.BoatModel
import net.minecraft.client.model.ChestBoatModel
import net.minecraft.client.model.geom.ModelLayerLocation
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.Sheets
import net.minecraft.client.renderer.Sheets.createHangingSignMaterial
import net.minecraft.client.renderer.Sheets.createSignMaterial
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.client.renderer.blockentity.HangingSignRenderer
import net.minecraft.client.renderer.blockentity.SignRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.ThrownItemRenderer
import net.minecraft.client.renderer.item.ItemProperties
import net.minecraft.core.BlockPos
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.PackType
import net.minecraft.util.Mth
import net.minecraft.world.InteractionHand
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.entity.SignBlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.WoodType
import net.minecraft.world.phys.Vec3
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import org.joml.Matrix4f
import org.joml.Vector4f
import java.io.File
import java.util.function.Function

private operator fun BlockPos.minus(pos: BlockPos): BlockPos {
    return this.subtract(pos)
}

private operator fun Vec3.times(scale: Float): Vec3 {
    return this.multiply(scale.toDouble(), scale.toDouble(), scale.toDouble())
}

private operator fun Vec3.minus(vec3: Vec3): Vec3 {
    return this.subtract(vec3)
}

object MatrixCache {
    var projectionMatrix = Matrix4f()
    var viewMatrix = Matrix4f()
}

object GenerationsCoreClient {
    fun onInitialize(minecraft: Minecraft) {
        if (GenerationsCore.CONFIG.client.useRenderDoc) {
            try {
                System.loadLibrary("renderdoc")
            } catch (e: UnsatisfiedLinkError) {
                LOGGER.warn("Attempted to use renderdoc without renderdoc installed.")
            }
        }

        ModelRegistry.init()

        ITextureLoader.setInstance(GenerationsTextureLoader)

        val renderer = PokemonItemRenderer()

        CobblemonBuiltinItemRendererRegistry.register(GenerationsItems.TIME_CAPSULE, renderer)
        CobblemonBuiltinItemRendererRegistry.register(GenerationsItems.SUICUNE_STATUE, renderer)
        CobblemonBuiltinItemRendererRegistry.register(GenerationsItems.RAIKOU_STATUE, renderer)
        CobblemonBuiltinItemRendererRegistry.register(GenerationsItems.ENTEI_STATUE, renderer)


        GenerationsCore.implementation.registerResourceReloader(GenerationsCore.id("model_registry"), CompiledModelLoader(), PackType.CLIENT_RESOURCES, emptyList())

        setupClient(minecraft)

        GenerationsClientMolangFunctions.addAnimationFunctions()

        RareCandy.DEBUG_THREADS = true

        PokeBallModelRepository.inbuilt("strange_ball", ::PokeBallModel)

        VaryingModelRepository.registerFactory(".pk", { resourceLocation, resource ->
            ResourceLocation.fromNamespaceAndPath(resourceLocation.namespace, File(resourceLocation.path).getName()) to
                    Function<Boolean, Bone> { bool ->
                        (ModelPart(
                            RareCandyBone.CUBE_LIST,
                            mapOf("root" to RareCandyBone(resourceLocation))
                        )) as Bone
                    }
        })
//        VaryingModelRepository.registerFactory(".pk", { resourceLocation, resource) -> new Pair<>(, b -> (Bone) new ModelPart(RareCandyBone.Companion.getCUBE_LIST(), Map.of("root", new RareCandyBone(resourceLocation))}));

//        GenerationsCore.implementation.registerResourceReloader(
//                id("texture_loader"),
//                (ResourceManagerReloadListener) resourceManager -> textureLoader.initialize(resourceManager),
//                PackType.CLIENT_RESOURCES,
//                emptyList());

        PlatformEvents.CLIENT_PLAYER_LOGIN.subscribe(Priority.NORMAL, GenerationsCoreClient::onLogin)
        PlatformEvents.CLIENT_PLAYER_LOGOUT.subscribe(Priority.NORMAL, GenerationsCoreClient::onLogout)
    }


    private fun setupClient(event: Minecraft) {

        event.tell({
            addWoodType(GenerationsWoodTypes.ULTRA_JUNGLE)
            addWoodType(GenerationsWoodTypes.ULTRA_DARK)
            addWoodType(GenerationsWoodTypes.GHOST)
            Pipelines.REGISTER.subscribe(handler = Pipelines::initGenerationsPipelines)

            Pipelines.onInitialize(event.resourceManager)

            registerScreens()
        })

        ItemProperties.registerGeneric(GenerationsCore.id("type")) { itemStack, clientLevel, livingEntity, i ->
            val type =
                itemStack.item.instanceOrNull<MoveTeachingItem>()?.getType(itemStack) ?: return@registerGeneric 0.0f
            return@registerGeneric when (type) {
                ElementalTypes.NORMAL -> 0.00f
                ElementalTypes.FIRE -> 0.01f
                ElementalTypes.WATER -> 0.02f
                ElementalTypes.GRASS -> 0.03f
                ElementalTypes.ELECTRIC -> 0.04f
                ElementalTypes.ICE -> 0.05f
                ElementalTypes.FIGHTING -> 0.06f
                ElementalTypes.POISON -> 0.07f
                ElementalTypes.GROUND -> 0.08f
                ElementalTypes.FLYING -> 0.09f
                ElementalTypes.PSYCHIC -> 0.10f
                ElementalTypes.BUG -> 0.11f
                ElementalTypes.ROCK -> 0.12f
                ElementalTypes.GHOST -> 0.13f
                ElementalTypes.DRAGON -> 0.14f
                ElementalTypes.DARK -> 0.15f
                ElementalTypes.STEEL -> 0.16f
                ElementalTypes.FAIRY -> 0.17f
                else -> 0.00f
            }
        }

        ItemProperties.register(
            GenerationsItems.CURRY,
            GenerationsCore.id("curry_type")
        ) { itemStack, _, _, _ -> return@register itemStack.get(GenerationsDataComponents.CURRY_DATA)?.curryType?.ordinal?.let { it / 100f } ?: 0f }

        ItemProperties.register(
            GenerationsItems.MELODY_FLUTE,
            GenerationsCore.id("flute_type"),
            { arg, arg2, arg3, i ->
                val stack = MelodyFluteItem.getImbuedItem(arg)

                return@register when {
                    isItem(GenerationsItems.ICY_WING, stack) -> 0.125f
                    isItem(GenerationsItems.ELEGANT_WING, stack) -> 0.25f
                    isItem(GenerationsItems.STATIC_WING, stack) -> 0.375f
                    isItem(GenerationsItems.BELLIGERENT_WING, stack) -> 0.5f
                    isItem(GenerationsItems.FIERY_WING, stack) -> 0.625f
                    isItem(GenerationsItems.SINISTER_WING, stack) -> 0.75f
                    isItem(GenerationsItems.RAINBOW_WING, stack) -> 0.875f
                    isItem(GenerationsItems.SILVER_WING, stack) -> 1.0f
                    else -> 0f
                }
            })

        ItemProperties.register(
            GenerationsShrines.CELESTIAL_ALTAR.asItem(),
            GenerationsCore.id("time")
        ) { itemStack, clientLevel, livingEntity, i ->
            val entity = livingEntity ?: itemStack.entityRepresentation ?: return@register 0f

            val level = entity.level().instanceOrNull<ClientLevel>() ?: return@register 0f

            if (clientLevel?.dimensionType().orFalse { it.natural() }) {
                return@register if (TimeRange.timeRanges["day"].orFalse { it.contains(level.dayTime.toInt()) }) 0.1f else 0.2f
            } else return@register 0f
        }

        ItemProperties.register(
            GenerationsShrines.LUNAR_SHRINE.asItem(),
            GenerationsCore.id("light_level")
        ) { itemStack, clientLevel, livingEntity, i ->
            val entity = livingEntity ?: itemStack.entityRepresentation ?: return@register 0f

            var level = entity.level().instanceOrNull<ClientLevel>() ?: return@register 0f

            return@register if (clientLevel.orFalse { it.getMaxLocalRawBrightness(entity.blockPosition()) > 10 }) 0.1f else 0f
        }

        TimeCapsule.registerItemProperty()

        registerFishingRod(GenerationsItems.OLD_ROD)
        registerFishingRod(GenerationsItems.GOOD_ROD)
        registerFishingRod(GenerationsItems.SUPER_ROD)
        registerFishingRod(GenerationsItems.RUBY_ROD)

//        ItemPropertiesRegistry.register(GenerationsShrines.LUNAR_SHRINE, GenerationsCore.id("lit"), (itemStack, clientLevel, livingEntity, i) -> clientLevel.isDay() ? 0.0f : 1.0f);

        registerChestRenderer(GenerationsBlocks.POKEBALL_CHEST)
        registerChestRenderer(GenerationsBlocks.GREATBALL_CHEST)
        registerChestRenderer(GenerationsBlocks.ULTRABALL_CHEST)
        registerChestRenderer(GenerationsBlocks.MASTERBALL_CHEST)
    }

    private fun registerFishingRod(item: Item) {
        ItemProperties.register(item, GenerationsCore.id("cast")) { arg, arg2, arg3, i ->
            val entity = arg3 ?: return@register 0f

            val flag = entity.mainHandItem == arg
            var flag1 = entity.offhandItem == arg

            if (entity.mainHandItem.item is TieredFishingRodItem) {
                flag1 = false
            }

            return@register if ((flag || flag1) && arg3.instanceOrNull<Player>()?.fishing != null) 1.0F else 0.0F
        }
    }

    private fun registerChestRenderer(chest: GenericChestBlock) {
        CobblemonBuiltinItemRendererRegistry.register(
            chest.asItem(),
            GenericChestItemStackRenderer({ GenericChestBlockEntity(BlockPos.ZERO, chest.defaultBlockState()) })
        )
    }

    private fun addWoodType(woodType: WoodType) {
        Sheets.SIGN_MATERIALS.put(woodType, createSignMaterial(woodType))
        Sheets.HANGING_SIGN_MATERIALS.put(woodType, createHangingSignMaterial(woodType))
    }

    fun registerScreens() {
        MenuScreens.register(GenerationsContainers.COOKING_POT, ::CookingPotScreen)
        MenuScreens.register(GenerationsContainers.GENERIC, ::GenericChestScreen)
//        MenuRegistry.registerScreenFactory(GenerationsContainers.WALKMON, GenericChestScreen::new);
//        MenuRegistry.registerScreenFactory(GenerationsContainers.CALYREX_STEED, GenericChestScreen::new);
//        MenuRegistry.registerScreenFactory(GenerationsContainers.MACHINE_BLOCK, ::MachineBlockScreen)
        MenuScreens.register(GenerationsContainers.MELODY_FLUTE, ::MelodyFluteScreen)
        MenuScreens.register(GenerationsContainers.TRASHCAN, ::TrashCanScreen)
        MenuScreens.register(GenerationsContainers.RKS_MACHINE, ::RksMachineScreen)
    }

    /**
     * Registers the key renderers.
     * @see GenerationsEntities
     */

    interface EntityRendererHandler {
        fun <T : Entity> register(type: EntityType<T>, provider: EntityRendererProvider<T>)
    }

    fun registerEntityRenderers(consumer: EntityRendererHandler) {
        consumer.register(GenerationsEntities.SEAT, ::SittableEntityRenderer)
        consumer.register(GenerationsEntities.TIERED_FISHING_BOBBER, ::TieredFishingHookRenderer)
        consumer.register(GenerationsEntities.BOAT_ENTITY, { GenerationsBoatRenderer(it, false) })
        consumer.register(GenerationsEntities.CHEST_BOAT_ENTITY, { GenerationsBoatRenderer(it, true) })
        consumer.register(GenerationsEntities.MAGMA_CRYSTAL, ::ThrownItemRenderer)
        consumer.register(GenerationsEntities.STATUE_ENTITY, ::StatueEntityRenderer)
        consumer.register(GenerationsEntities.ZYGARDE_CELL, ::ZygardeCellRenderer)
    }

    interface BlockEntityRendererHandler {
        fun <T : BlockEntity> register(type: BlockEntityType<T>, provider: BlockEntityRendererProvider<T>)
    }

    /**
     * Registers the block key renderers.
     * @see BlockEntityRenderers
     * @see GenerationsBlockEntities
     */
    fun registerBlockEntityRenderers(consumer: BlockEntityRendererHandler) {
        consumer.register(GenerationsBlockEntities.POKE_DOLL, ::GeneralUseBlockEntityRenderer)

        consumer.register(GenerationsBlockEntities.TIMESPACE_ALTAR, ::TimeSpaceAltarEntityRenderer)
        consumer.register(GenerationsBlockEntities.ABUNDANT_SHRINE, ::GeneralUseBlockEntityRenderer)
        consumer.register(GenerationsBlockEntities.CELESTIAL_ALTAR, ::GeneralUseBlockEntityRenderer)
        consumer.register(GenerationsBlockEntities.LUNAR_SHRINE, ::GeneralUseBlockEntityRenderer)
        consumer.register(GenerationsBlockEntities.MELOETTA_MUSIC_BOX, ::GeneralUseBlockEntityRenderer)
        consumer.register(GenerationsBlockEntities.REGIGIGAS_SHRINE, ::RegigigasShrineBlockEntityRenderer)
        consumer.register(GenerationsBlockEntities.TAO_TRIO_SHRINE, ::GeneralUseBlockEntityRenderer)
        consumer.register(GenerationsBlockEntities.TAPU_SHRINE, ::GeneralUseBlockEntityRenderer)
        consumer.register(GenerationsBlockEntities.INTERACT_SHRINE, ::GeneralUseBlockEntityRenderer)
        consumer.register(GenerationsBlockEntities.COOKING_POT, ::CookingPotRenderer)
        consumer.register(GenerationsBlockEntities.WEATHER_TRIO, ::GeneralUseBlockEntityRenderer)
        consumer.register(GenerationsBlockEntities.SIGN_BLOCK_ENTITIES, ::SignRenderer)
        GenerationsBlockEntities.HANGING_SIGN_BLOCK_ENTITIES.instanceOrNull<BlockEntityType<SignBlockEntity>>()?.run { consumer.register(this, ::HangingSignRenderer) }
        consumer.register(GenerationsBlockEntities.GENERIC_CHEST, ::GenericChestRenderer)
        consumer.register(GenerationsBlockEntities.GENERIC_SHRINE, ::GeneralUseBlockEntityRenderer)
        consumer.register(GenerationsBlockEntities.GENERIC_DYED_VARIANT, ::GeneralUseBlockEntityRenderer)
        consumer.register(GenerationsBlockEntities.GENERIC_MODEL_PROVIDING, ::GeneralUseBlockEntityRenderer)
        consumer.register(GenerationsBlockEntities.TRASH_CAN, ::GeneralUseBlockEntityRenderer)
        consumer.register(GenerationsBlockEntities.VENDING_MACHINE, ::GeneralUseBlockEntityRenderer)
        consumer.register(GenerationsBlockEntities.BALL_DISPLAY, ::GeneralUseBlockEntityRenderer)
        consumer.register(GenerationsBlockEntities.BALL_LOOT, ::PokeLootRendrer)
        consumer.register(GenerationsBlockEntities.RKS_MACHINE, ::GeneralUseBlockEntityRenderer)
        consumer.register(GenerationsBlockEntities.PC, ::GeneralUseBlockEntityRenderer)
        consumer.register(GenerationsBlockEntities.DYED_PC, ::GeneralUseBlockEntityRenderer)

        consumer.register(GenerationsBlockEntities.COUCH, ::GeneralUseBlockEntityRenderer)
        consumer.register(GenerationsBlockEntities.STREET_LAMP, ::GeneralUseBlockEntityRenderer)
    }

    fun registerLayerDefinitions(consumer: (ModelLayerLocation, () -> LayerDefinition) -> Unit) {
        val boat = BoatModel::createBodyModel
        val chestBoat: () -> LayerDefinition = ChestBoatModel::createBodyModel


        for (type in GenerationsBoatEntity.Type.entries) {
            consumer.invoke(GenerationsBoatRenderer.createBoatModelName(type), boat)
            consumer.invoke(GenerationsBoatRenderer.createChestBoatModelName(type), chestBoat)
        }
    }

    fun onLogin(login: ClientPlayerEvent.Login) {
//        GenerationsDataProvider.INSTANCE.canReload = false;
    }

    fun onLogout(logout: ClientPlayerEvent.Logout) {
//        GenerationsDataProvider.INSTANCE.canReload = true;
    }

    fun renderHighlightedPath(poseStack: PoseStack, renderTick: Int, camera: Camera) {
        val player: Player = Minecraft.getInstance().player ?: return

        val heldItem = player.getItemInHand(InteractionHand.MAIN_HAND)
        if (heldItem.item !is NpcPathTool) {
            return
        }

        // Get the block positions to highlight.
        var blockPositions = NpcPathTool.getPath(heldItem)

        // Set up variables for drawing the path lines.
        var speed = 10f
        var percentage = 0f
        var holdTimeMod = 2.5f // Determines how long the path ray will remain.
        var percPerSegment = -1f
        if (blockPositions.size > 1) {
            val numEdges = blockPositions.size - 1
            percentage = (renderTick % (numEdges * speed * holdTimeMod)) / (numEdges * speed)
            percPerSegment = (1.0f / numEdges)
        }
        var prevBlockPos: BlockPos? = null

        // Set up the drawing objects.
        poseStack.pushPose()
        val buffers: MultiBufferSource.BufferSource = Minecraft.getInstance().renderBuffers().bufferSource()
        val vertexconsumer = buffers.getBuffer(RenderType.lines())
        val color = Vector4f(227F / 255, 28F / 255, 121F / 255, 0.8F)

        for (blockPos in blockPositions) {
            val blockstate = player.level().getBlockState(blockPos)
            // figure out the percentage of the segment to draw.
            var segmentPercentage: Float = 0f
            if (prevBlockPos == null) {
                segmentPercentage = 0f
            } else if (percPerSegment > percentage) {
                segmentPercentage = percentage / percPerSegment
                percentage = 0f
            } else {
                percentage -= percPerSegment
                segmentPercentage = 1f
            }
            // Draw the block highlight.
            if (!blockstate.isAir) {
                renderHitOutline(
                    poseStack, vertexconsumer, player.level(), camera, blockPos, blockstate, color
                )
            }
            // Draw the animated path segment.
            if (prevBlockPos != null && segmentPercentage > 0) {
                val pos1 = Vec3(0.0, 0.0, 0.0)
                val pos2 = (blockPos - prevBlockPos).toVec3d() * segmentPercentage
                renderLine(poseStack, pos1, pos2, vertexconsumer, prevBlockPos.above(), camera.position, color)
            }
            prevBlockPos = blockPos
        }

        buffers.endBatch()
        poseStack.popPose()
    }

    private fun renderShape(
        poseStack: PoseStack,
        vertexConsumer: VertexConsumer,
        voxelShape: VoxelShape,
        x: Double,
        y: Double,
        z: Double,
        color: Vector4f,
    ) {
        val pose = poseStack.last()
        // Create the vertex consumer shape by creating every edge.
        voxelShape.forAllEdges { x1, y1, z1, x2, y2, z2 ->
            {
                var f = (x2 - x1).toFloat()
                var f1 = (y2 - y1).toFloat()
                var f2 = (z2 - z1).toFloat()
                var f3 = Mth.sqrt(f * f + f1 * f1 + f2 * f2)
                f /= f3
                f1 /= f3
                f2 /= f3
                vertexConsumer.addVertex(pose.pose(), (x1 + x).toFloat(), (y1 + y).toFloat(), (z1 + z).toFloat())
                    .setColor(color.x(), color.y(), color.z(), color.w()).setNormal(pose, f, f1, f2)
                vertexConsumer.addVertex(pose.pose(), (x2 + x).toFloat(), (y2 + y).toFloat(), (z2 + z).toFloat())
                    .setColor(color.x(), color.y(), color.z(), color.w()).setNormal(pose, f, f1, f2)
            }
        }
    }

    private fun renderLine(
        poseStack: PoseStack,
        pos1: Vec3,
        pos2: Vec3,
        vertexConsumer: VertexConsumer,
        blockPos: BlockPos,
        camPos: Vec3,
        color: Vector4f,
    ) {
        val pose = poseStack.last()
        val pos = pos2 - pos1
        var f = pos.x.toFloat()
        var f1 = pos.y.toFloat()
        var f2 = pos.z.toFloat()
        var f3 = Mth.sqrt(f * f + f1 * f1 + f2 * f2)
        f /= f3
        f1 /= f3
        f2 /= f3
        vertexConsumer.addVertex(
            pose,
            (pos1.x + (blockPos.x - camPos.x + 0.5)).toFloat(),
            (pos1.y + (blockPos.y + 0.5 - camPos.y)).toFloat(),
            (pos1.z + (blockPos.z - camPos.z + 0.5)).toFloat()
        ).setColor(color.x(), color.y(), color.z(), color.w()).setNormal(pose, f, f1, f2)

        vertexConsumer.addVertex(
            pose,
            (pos2.x + (blockPos.x - camPos.x + 0.5)).toFloat(),
            (pos2.y + (blockPos.y + 0.5 - camPos.y)).toFloat(),
            (pos2.z + (blockPos.z - camPos.z + 0.5)).toFloat()
        ).setColor(color.x(), color.y(), color.z(), color.w()).setNormal(pose, f, f1, f2)
    }

    private fun renderHitOutline(
        poseStack: PoseStack,
        vertexConsumer: VertexConsumer,
        level: Level,
        camera: Camera,
        blockPos: BlockPos,
        blockState: BlockState,
        color: Vector4f,
    ) {
        val pos = camera.position
        renderShape(
            poseStack,
            vertexConsumer,
            blockState.getShape(level, blockPos, CollisionContext.of(camera.entity)),
            blockPos.x - pos.x(),
            blockPos.y - pos.y(),
            blockPos.z - pos.z(),
            color
        )
    }

    private fun shouldRenderFpsPie(): Boolean {
        return Minecraft.getInstance().options.reducedDebugInfo()
            .get() /*&& Minecraft.getInstance().options.renderDebugCharts*/ && !Minecraft.getInstance().options.hideGui
    }

    fun renderRareCandySolid() {
        renderRareCandy(RenderStage.SOLID, false)
    }

    fun renderRareCandyTransparent(clear: Boolean = false) {
        renderRareCandy(RenderStage.TRANSPARENT, clear)
    }

    fun renderRareCandy(stage: RenderStage, clear: Boolean) {
        if (GenerationsCore.CONFIG.client.useVanilla) return

        var startTime = System.currentTimeMillis()
//        level.profiler.popPush("render_models")
        RenderSystem.enableDepthTest()
        BufferUploader.reset()

        ModelRegistry.worldRareCandy.render(stage, clear, MinecraftClientGameProvider.getTimePassed())
        if (shouldRenderFpsPie()) LOGGER.warn("RareCandy render took " + (System.currentTimeMillis() - startTime) + "ms")
    }
}

