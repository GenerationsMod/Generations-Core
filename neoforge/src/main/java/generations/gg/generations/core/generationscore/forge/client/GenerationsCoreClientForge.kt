package generations.gg.generations.core.generationscore.forge.client

import com.mojang.blaze3d.systems.RenderSystem
import generations.gg.generations.core.generationscore.common.GenerationsCore
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient.BlockEntityRendererHandler
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient.EntityRendererHandler
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient.onInitialize
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient.registerBlockEntityRenderers
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient.registerEntityRenderers
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient.registerLayerDefinitions
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient.renderHighlightedPath
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient.renderRareCandySolid
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient.renderRareCandyTransparent
import generations.gg.generations.core.generationscore.common.client.MatrixCache
import generations.gg.generations.core.generationscore.common.client.model.Keybinds
import generations.gg.generations.core.generationscore.common.client.render.RenderStateRecord
import generations.gg.generations.core.generationscore.common.client.screen.container.*
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.server.packs.resources.PreparableReloadListener
import net.minecraft.server.packs.resources.ReloadableResourceManager
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import net.neoforged.neoforge.client.event.ClientTickEvent
import net.neoforged.neoforge.client.event.EntityRenderersEvent.RegisterLayerDefinitions
import net.neoforged.neoforge.client.event.EntityRenderersEvent.RegisterRenderers
import net.neoforged.neoforge.client.event.InputEvent
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent
import net.neoforged.neoforge.client.event.RenderLevelStageEvent
import net.neoforged.neoforge.common.NeoForge
import java.util.ArrayList

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

        eventBus.addListener<RegisterClientReloadListenersEvent> {
            for(loader in reloadableResources) {
                System.out.println("[GenerationsTextureLoaderLoader] Actually ${loader.name}")
                it.registerReloadListener(loader)
            }
        }

        with(NeoForge.EVENT_BUS) {
            addListener<InputEvent.Key> { Keybinds.pressDown(it.key, it.scanCode, it.action, it.modifiers) }
            addListener<ClientTickEvent.Post> {
                GenerationsCoreClient.onTick()
            }
        }

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
//        NeoForge.EVENT_BUS.addListener { event: RenderLevelStageEvent ->
//            renderHighlightedPath(
//                event
//            )
//        }
    }

    companion object {
        private val reloadableResources: MutableList<PreparableReloadListener> = ArrayList()

        private fun renderHighlightedPath(event: RenderLevelStageEvent) {
            when (event.stage) {
                RenderLevelStageEvent.Stage.AFTER_TRANSLUCENT_BLOCKS -> {
                    renderHighlightedPath(
                        event.poseStack,
                        event.partialTick.getGameTimeDeltaPartialTick(false).toInt(),
                        event.camera
                    )

                    MatrixCache.projectionMatrix.set(RenderSystem.getProjectionMatrix())
                    MatrixCache.viewMatrix.set(RenderSystem.getModelViewMatrix())

                    RenderStateRecord.push()
                    RenderSystem.enableDepthTest()
                    RenderSystem.defaultBlendFunc()
                    RenderSystem.enableBlend()
                    renderRareCandyTransparent(true)
                    RenderStateRecord.pop()
                }

                RenderLevelStageEvent.Stage.AFTER_LEVEL -> {
                    RenderStateRecord.push()
                    renderRareCandySolid()
                    renderRareCandyTransparent()
                    RenderStateRecord.pop()
                }

                else -> {}
            }
        }


        private fun forgeClientSetup(event: FMLClientSetupEvent) {
            onInitialize(Minecraft.getInstance())
            //        ForgeConfig.CLIENT.alwaysSetupTerrainOffThread.set(true); // Performance improvement
//        ForgeConfig.CLIENT.experimentalForgeLightPipelineEnabled.set(true); // Use Experimental Forge Light Pipeline
        }

        fun registerResourceReloader(reloader: PreparableReloadListener) {
            System.out.println("[GenerationsTextureLoaderLoader] Loading ${reloader.name}")

            reloadableResources.add(reloader)
        }
    }
}
