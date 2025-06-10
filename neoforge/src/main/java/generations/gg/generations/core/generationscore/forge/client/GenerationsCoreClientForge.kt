package generations.gg.generations.core.generationscore.forge.client

import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.GenerationsCore.id
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient.BlockEntityRendererHandler
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient.EntityRendererHandler
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient.onInitialize
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient.registerBlockEntityRenderers
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient.registerEntityRenderers
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient.registerLayerDefinitions
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient.renderHighlightedPath
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient.renderRareCandy
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient.renderRareCandySolid
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient.renderRareCandyTransparent
import generations.gg.generations.core.generationscore.common.client.render.RenderStateRecord
import generations.gg.generations.core.generationscore.common.mixin.client.LevelRendererMixin
import net.minecraft.client.DeltaTracker
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.model.geom.ModelLayerLocation
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import net.neoforged.neoforge.client.event.EntityRenderersEvent.RegisterLayerDefinitions
import net.neoforged.neoforge.client.event.EntityRenderersEvent.RegisterRenderers
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent
import net.neoforged.neoforge.client.event.RenderLevelStageEvent
import net.neoforged.neoforge.client.gui.VanillaGuiLayers
import net.neoforged.neoforge.common.NeoForge
import java.util.function.BiConsumer
import java.util.function.Consumer
import java.util.function.Supplier

/**
 * This class is used to initialize the Forge client side of the mod.
 * @see FMLClientSetupEvent
 *
 * @see GenerationsCoreClient
 *
 * @author Joseph T. McQuigg, WaterPicker
 */
@Mod(value = GenerationsCore.MOD_ID, dist = [Dist.CLIENT])
class GenerationsCoreClientForge(eventBus: IEventBus) {
    /**
     * Initializes the client side of the Forge mod.
     * @param eventBus The event bus to register the client side of the mod to.
     */
    init {
        eventBus.addListener({ event: RegisterRenderers ->
            registerBlockEntityRenderers(object : BlockEntityRendererHandler {
                override fun <T : BlockEntity> register(type: BlockEntityType<T>, provider: BlockEntityRendererProvider<T>, ) = event.registerBlockEntityRenderer(type, provider)
            })
            registerEntityRenderers(object : EntityRendererHandler {
                override fun <T : Entity> register(type: EntityType<T>, provider: EntityRendererProvider<T>) = event.registerEntityRenderer(type, provider)
            })
        })
        eventBus.addListener({ event: RegisterLayerDefinitions ->
            registerLayerDefinitions({ layerLocation, supplier -> event.registerLayerDefinition(layerLocation, supplier) })
        })
        eventBus.addListener { event: FMLClientSetupEvent ->
            forgeClientSetup(
                event
            )
        }
//        eventBus.addListener { event: RegisterGuiLayersEvent ->
//            registerGUILayers(
//                event
//            )
//        }
        NeoForge.EVENT_BUS.addListener { event: RenderLevelStageEvent ->
            renderHighlightedPath(
                event
            )
        }
    }

    companion object {
        private fun renderHighlightedPath(event: RenderLevelStageEvent) {
            if (event.stage === RenderLevelStageEvent.Stage.AFTER_PARTICLES) {
                renderHighlightedPath(event.poseStack, event.renderTick, event.camera)
                renderRareCandyTransparent(true)
            } else if (event.stage === RenderLevelStageEvent.Stage.AFTER_BLOCK_ENTITIES) {
                RenderStateRecord.push()

                renderRareCandySolid()
                renderRareCandyTransparent()

                RenderStateRecord.pop()
            }
        }


        private fun forgeClientSetup(event: FMLClientSetupEvent) {
            onInitialize(Minecraft.getInstance())
            //        ForgeConfig.CLIENT.alwaysSetupTerrainOffThread.set(true); // Performance improvement
//        ForgeConfig.CLIENT.experimentalForgeLightPipelineEnabled.set(true); // Use Experimental Forge Light Pipeline
        }

//        private fun registerGUILayers(event: RegisterGuiLayersEvent) {
//            event.registerAbove(
//                VanillaGuiLayers.CAMERA_OVERLAYS, id("overlays")
//            ) { obj: GuiGraphics, guiGraphics: DeltaTracker? ->
//                GuiO.render(
//                    guiGraphics
//                )
//            }
//        }
    }
}
