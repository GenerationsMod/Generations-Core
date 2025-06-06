package generations.gg.generations.core.generationscore.fabric.client

import com.mojang.blaze3d.platform.GlStateManager
import com.mojang.blaze3d.platform.GlStateManager.BlendState
import com.mojang.blaze3d.systems.RenderSystem
import dev.architectury.registry.registries.RegistrySupplier
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient.BlockEntityRendererHandler
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient.EntityRendererHandler
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient.onInitialize
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient.registerBlockEntityRenderers
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient.registerEntityRenderers
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient.registerLayerDefinitions
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient.renderHighlightedPath
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient.renderRareCandy
import generations.gg.generations.core.generationscore.common.client.MatrixCache
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsMushroomBlock
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsWood
import generations.gg.generations.core.generationscore.fabric.networking.GenerationsFabricNetwork
import gg.generations.rarecandy.renderer.rendering.RenderStage
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents.AfterTranslucent
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.DoorBlock
import net.minecraft.world.level.block.TransparentBlock
import net.minecraft.world.level.block.TrapDoorBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import org.joml.Matrix4f
import java.util.function.Consumer

/**
 * The client initializer for the fabric client portion of the mod.
 * @see ClientModInitializer
 *
 * @see GenerationsCoreClient
 *
 * @author Joseph T. McQuigg, WaterPicker
 */
@Environment(EnvType.CLIENT)
class GenerationsCoreClientFabric : ClientModInitializer {
    /**
     * Initializes the client.
     * @see ClientModInitializer.onInitializeClient
     */
    override fun onInitializeClient() {
        WorldRenderEvents.AFTER_TRANSLUCENT.register(AfterTranslucent { context: WorldRenderContext ->
            renderHighlightedPath(
                context.matrixStack()!!, Minecraft.getInstance().levelRenderer.ticks, context.camera()
            )
        })

        WorldRenderEvents.BEFORE_ENTITIES.register {
            MatrixCache.projectionMatrix = RenderSystem.getProjectionMatrix()
        }

        WorldRenderEvents.AFTER_ENTITIES.register {
            MatrixCache.viewMatrix = RenderSystem.getModelViewMatrix()
        }

        WorldRenderEvents.BEFORE_DEBUG_RENDER.register {
            RenderStateRecord.push()

            renderRareCandy(RenderStage.SOLID, false)
            renderRareCandy(RenderStage.TRANSPARENT, false)

            RenderStateRecord.pop()
        }

        WorldRenderEvents.AFTER_TRANSLUCENT.register {
            RenderStateRecord.push()

            renderRareCandy(RenderStage.TRANSPARENT, true)
            RenderStateRecord.pop()

        }

        GenerationsFabricNetwork.registerClientHandlers()

        onInitialize(Minecraft.getInstance())
        registerRenderTypes()
        registerEntityRenderers(object : EntityRendererHandler {
            override fun <T : Entity> register(type: EntityType<T>, provider: EntityRendererProvider<T>) = EntityRendererRegistry.register(type, provider)
        })
        registerBlockEntityRenderers(object : BlockEntityRendererHandler {
            override fun <T : BlockEntity> register(type: BlockEntityType<T>, provider: BlockEntityRendererProvider<T>) = BlockEntityRenderers.register(type, provider)
        })
        registerLayerDefinitions({ a, b -> EntityModelLayerRegistry.registerModelLayer(a, b::invoke) })
    }

    companion object {
        /**
         * Registers the render types for the blocks.
         * @see BlockRenderLayerMap
         */
        private fun registerRenderTypes() {
            val renderLayerMap = BlockRenderLayerMap.INSTANCE
            GenerationsWood.WOOD_BLOCKS.forEach(Consumer { `object`: RegistrySupplier<Block?> ->
                val block = `object`.get()
                if (block is DoorBlock || block is TrapDoorBlock) renderLayerMap.putBlock(block, RenderType.cutout())
            })
            GenerationsBlocks.BLOCKS.forEach(Consumer { `object`: RegistrySupplier<Block?> ->
                val block = `object`.get()
                if (block is DoorBlock || block is GenerationsMushroomBlock) renderLayerMap.putBlock(
                    block,
                    RenderType.cutout()
                )
                else if (block is TransparentBlock) renderLayerMap.putBlock(block, RenderType.cutoutMipped())
            })
            renderLayerMap.putBlock(GenerationsBlocks.POINTED_CHARGE_DRIPSTONE.get(), RenderType.cutout())
        }
    }

    private object RenderStateRecord {
        var blendEnabled: Boolean = false
        var srcRgb: Int = 0
        var dstRgb: Int = 0
        var srcAlpha: Int = 0
        var dstAlpha: Int = 0

        var depthTestEnabled: Boolean = false
        var depthMask: Boolean = false
        var depthFunc: Int = 0

        var cullEnabled: Boolean = false

        fun push() {
            // Blend
            blendEnabled = GlStateManager.BLEND.mode.enabled
            srcRgb = GlStateManager.BLEND.srcRgb
            dstRgb = GlStateManager.BLEND.dstRgb
            srcAlpha = GlStateManager.BLEND.srcAlpha
            dstAlpha = GlStateManager.BLEND.dstAlpha

            // Depth
            depthTestEnabled = GlStateManager.DEPTH.mode.enabled
            depthMask = GlStateManager.DEPTH.mask
            depthFunc = GlStateManager.DEPTH.func

            // Cull
            cullEnabled = GlStateManager.CULL.enable.enabled
        }

        fun pop() {
            // Blend
            if (blendEnabled) RenderSystem.enableBlend() else RenderSystem.disableBlend()
            RenderSystem.blendFuncSeparate(srcRgb, dstRgb, srcAlpha, dstAlpha)

            // Depth
            if (depthTestEnabled) RenderSystem.enableDepthTest() else RenderSystem.disableDepthTest()
            RenderSystem.depthMask(depthMask)
            RenderSystem.depthFunc(depthFunc)

            // Cull
            if (cullEnabled) RenderSystem.enableCull() else RenderSystem.disableCull()
        }
    }
}
