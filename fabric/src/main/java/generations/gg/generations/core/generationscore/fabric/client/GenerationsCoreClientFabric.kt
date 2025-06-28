package generations.gg.generations.core.generationscore.fabric.client

import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient.BlockEntityRendererHandler
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient.EntityRendererHandler
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient.onInitialize
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient.registerBlockEntityRenderers
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient.registerEntityRenderers
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClient.registerLayerDefinitions
import generations.gg.generations.core.generationscore.common.client.GenerationsCoreClientImplementation
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsBlocks
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsMushroomBlock
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsWood
import generations.gg.generations.core.generationscore.fabric.GenerationsCoreFabric
import generations.gg.generations.core.generationscore.fabric.GenerationsCoreFabric.GenerationsReloadListener
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.PackType
import net.minecraft.server.packs.resources.PreparableReloadListener
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.level.block.DoorBlock
import net.minecraft.world.level.block.TransparentBlock
import net.minecraft.world.level.block.TrapDoorBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType

/**
 * The client initializer for the fabric client portion of the mod.
 * @see ClientModInitializer
 *
 * @see GenerationsCoreClient
 *
 * @author Joseph T. McQuigg, WaterPicker
 */
@Environment(EnvType.CLIENT)
class GenerationsCoreClientFabric : ClientModInitializer, GenerationsCoreClientImplementation {
    /**
     * Initializes the client.
     * @see ClientModInitializer.onInitializeClient
     */
    override fun onInitializeClient() {
//        WorldRenderEvents.AFTER_TRANSLUCENT.register { secondRenderPass() }

//        WorldRenderEvents.BEFORE_DEBUG_RENDER.register { firstRenderPass() }

        GenerationsCoreFabric.networkManager.registerClientHandlers()

        onInitialize(this)

        ClientLifecycleEvents.CLIENT_STARTED.register(GenerationsCoreClient::setupClient)

        registerRenderTypes()
        registerEntityRenderers(object : EntityRendererHandler {
            override fun <T : Entity> register(type: EntityType<T>, provider: EntityRendererProvider<T>) = EntityRendererRegistry.register(type, provider)
        })
        registerBlockEntityRenderers(object : BlockEntityRendererHandler {
            override fun <T : BlockEntity> register(type: BlockEntityType<T>, provider: BlockEntityRendererProvider<T>) = BlockEntityRenderers.register(type, provider)
        })
        registerLayerDefinitions({ a, b -> EntityModelLayerRegistry.registerModelLayer(a, b::invoke) })

        ClientTickEvents.END_CLIENT_TICK.register {
            GenerationsCoreClient.onTick()
        }
    }

    companion object {
        /**
         * Registers the render types for the blocks.
         * @see BlockRenderLayerMap
         */
        private fun registerRenderTypes() {
            val renderLayerMap = BlockRenderLayerMap.INSTANCE
            GenerationsWood.WOOD_BLOCKS.all().forEach { block ->
                if (block is DoorBlock || block is TrapDoorBlock) renderLayerMap.putBlock(block, RenderType.cutout())
            }
            GenerationsBlocks.BLOCKS.all().forEach { block ->
                if (block is DoorBlock || block is GenerationsMushroomBlock) renderLayerMap.putBlock(
                    block,
                    RenderType.cutout()
                )
                else if (block is TransparentBlock) renderLayerMap.putBlock(block, RenderType.cutoutMipped())
            }
            renderLayerMap.putBlock(GenerationsBlocks.POINTED_CHARGE_DRIPSTONE.value(), RenderType.cutout())
        }
    }

    override fun registerResourceReloader(
        identifier: ResourceLocation,
        reloader: PreparableReloadListener,
        dependencies: Collection<ResourceLocation>,
    ) {
        ResourceManagerHelper.get(PackType.CLIENT_RESOURCES).registerReloadListener(GenerationsReloadListener(identifier, reloader, dependencies))
    }
}


