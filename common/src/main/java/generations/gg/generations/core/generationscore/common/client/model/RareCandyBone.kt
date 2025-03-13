package generations.gg.generations.core.generationscore.common.client.model

import com.cobblemon.mod.common.api.pokemon.PokemonSpecies
import com.cobblemon.mod.common.client.render.VaryingRenderableResolver
import com.cobblemon.mod.common.client.render.layer.CobblemonRenderLayers
import com.cobblemon.mod.common.client.render.models.blockbench.PoseableEntityModel
import com.cobblemon.mod.common.client.render.models.blockbench.pokemon.PokemonPoseableModel
import com.cobblemon.mod.common.client.render.models.blockbench.pose.Bone
import com.cobblemon.mod.common.client.render.models.blockbench.repository.PokemonModelRepository
import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext
import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext.RenderState
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.net.messages.client.pokemon.update.AspectsUpdatePacket
import com.cobblemon.mod.common.pokemon.FormData
import com.cobblemon.mod.common.pokemon.Species
import com.cobblemon.mod.common.util.asResource
import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import com.mojang.math.Axis
import generations.gg.generations.core.generationscore.common.client.IVariant
import generations.gg.generations.core.generationscore.common.client.model.SpriteRegistry.getPokemonSprite
import generations.gg.generations.core.generationscore.common.client.render.CobblemonInstanceProvider
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.CobblemonInstance
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.CompiledModel
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.ModelRegistry
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.Pipelines
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.StatueInstance
import generations.gg.generations.core.generationscore.common.mixin.client.ModelAssetVariationMixin
import net.minecraft.client.Minecraft
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite
import net.minecraft.core.Direction
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.Entity
import org.joml.Vector3f
import java.util.*
import java.util.function.Supplier

private val RenderContext.form: FormData?
    get() = this.species?.getForm(this.request(RenderContext.ASPECTS) ?: Collections.emptySet())

private val RenderContext.species: Species?
    get() = this.request(RenderContext.SPECIES)?.let { PokemonSpecies.getByIdentifier(it) }

class RareCandyBone /*Remove when cobblemon doesn't have parts of code that assumes Bone will always be a ModelPart */(
    location: ResourceLocation) : ModelPart(CUBE_LIST, BLANK_MAP), Supplier<Bone>, Bone {
    private val objectSupplier: () -> CompiledModel?
    private val spriteProvider: (RenderState, String) -> ResourceLocation

    init {
        val spriteLoc = location.toString().replace("bedrock/pokemon/models/", "").replace(".pk", "").asResource()
        objectSupplier = { ModelRegistry[location] }
        spriteProvider = { state: RenderState, s: String ->
            val sprite = getPokemonSprite(state, spriteLoc, s)
            sprite ?: MissingTextureAtlasSprite.getLocation()
        }


    }

    override fun getChildren(): Map<String, Bone> {
        return DUMMY
    }

    override fun render(
        context: RenderContext,
        stack: PoseStack,
        buffer: VertexConsumer,
        packedLight: Int,
        packedOverlay: Int,
        r: Float,
        g: Float,
        b: Float,
        a: Float
    ) {
        renderModel(context, buffer, stack, packedLight, packedOverlay, r, g, b, a)
    }

    @JvmOverloads
    fun renderSprite(
        context: RenderContext,
        stack: PoseStack,
        packedLight: Int,
        packedOverlay: Int,
        r: Float,
        g: Float,
        b: Float,
        a: Float,
        isSprite: Boolean = false
    ) {
        val id = getSprite(context)
        val sources = Minecraft.getInstance().renderBuffers().bufferSource()
        val buffer = sources.getBuffer(CobblemonRenderLayers.ENTITY_CUTOUT.apply(id))
        val scale = if (isSprite) 1f else 2f
        val matrix = stack.last()
        matrix.pose().translate(-scale / 2f, 0f, 0f)
        buffer.vertex(matrix.pose(), scale, 0f, 0.0f).color(r, g, b, a).uv(1f, 0f).overlayCoords(packedOverlay)
            .uv2(packedLight).normal(matrix.normal(), 0f, 1f, 0f).endVertex()
        buffer.vertex(matrix.pose(), 0f, 0f, 0.0f).color(r, g, b, a).uv(0f, 0f).overlayCoords(packedOverlay)
            .uv2(packedLight).normal(matrix.normal(), 0f, 1f, 0f).endVertex()
        buffer.vertex(matrix.pose(), 0f, scale, 0.0f).color(r, g, b, a).uv(0f, 1f).overlayCoords(packedOverlay)
            .uv2(packedLight).normal(matrix.normal(), 0f, 1f, 0f).endVertex()
        buffer.vertex(matrix.pose(), scale, scale, 0.0f).color(r, g, b, a).uv(1f, 1f).overlayCoords(packedOverlay)
            .uv2(packedLight).normal(matrix.normal(), 0f, 1f, 0f).endVertex()
        sources.endBatch()
    }

    private fun renderModel(
        context: RenderContext,
        buffer: VertexConsumer,
        stack: PoseStack,
        packedLight: Int,
        packedOverlay: Int,
        r: Float,
        g: Float,
        b: Float,
        a: Float
    ) {
        val model = objectSupplier.invoke()
        if (model?.renderObject == null) return

        var instance = context.request(Pipelines.INSTANCE)
        if (instance == null) {
            val entity = context.request<Entity>(RenderContext.Companion.ENTITY)
            if (entity is CobblemonInstanceProvider) {
                instance = entity.instance
            }
        }
        val isStatue = instance is StatueInstance
        var scale = model.renderObject!!.scale // / context.requires(RenderContext.SCALE)
        if (instance == null) {
            return
        } else {
            if (isStatue) {
                if (model.guiInstance == null) return
                instance.matrixTransforms = model.guiInstance!!.matrixTransforms
                instance.offsets = model.guiInstance!!.offsets
            } else {
                val entity = context.request(RenderContext.ENTITY) as? PokemonEntity

                scale *= if(entity != null) {
                    1f / entity.pokemon.form.baseScale
                } else {
                    1f / (context.form?.baseScale ?: 1f)
                }
            }
        }
        if (model.renderObject!!.isReady) {
            instance.light = packedLight
            instance.tint.set(r, g, b)
            val variant = getVariant(context)
            if (variant != null) {
                instance.setVariant(variant)
            }
            stack.pushPose()
            stack.mulPose(ROTATION_CORRECTION)
            stack.scale(-scale, -scale, scale)
            stack.translate(0.0, -1.501 / scale, 0.0)
            instance.transformationMatrix().set(stack.last().pose())
//            instance.viewMatrix().set(RenderSystem.getModelViewMatrix())


//            if(!isGui) {
            model.render(instance)
            //            } else {
//                model.renderGui(instance);
//            }

            stack.popPose()
        }
    }

    val compiledModel: CompiledModel?
        get() = objectSupplier.invoke()

    private fun getVariant(context: RenderContext): String? {
        return try {
            val aspects = context.request<Set<String>>(RenderContext.Companion.ASPECTS)
            val species = context.request<ResourceLocation>(RenderContext.Companion.SPECIES)!!
            return PokemonModelRepository.variations[species]?.getResolvedVariant(aspects ?: emptySet<String>())
        } catch (e: Exception) {
            null
        }
    }

    override fun transform(poseStack: PoseStack) {}
    override fun get(): Bone = this

    fun getSprite(context: RenderContext): ResourceLocation = getVariant(context)?.let { spriteProvider.invoke(context.requires(RenderContext.RENDER_STATE), it) } ?: MissingTextureAtlasSprite.getLocation()

    companion object {
        val CUBE_LIST = listOf(Cube(0, 0, 0f, 0f, 0f, 1f, 1f, 1f, 0f, 0f, 0f, false, 1.0f, 1.0f, java.util.Set.of(Direction.NORTH))) //TODO: Remove when assumpt of Bone is always ModelPart is gone.
        private val BLANK_MAP = mapOf("root" to ModelPart(CUBE_LIST, mapOf()))
        private val temp = Vector3f()
        private val ROTATION_CORRECTION = Axis.YP.rotationDegrees(180f)
        private val DUMMY = emptyMap<String, Bone>()
    }
}

private fun VaryingRenderableResolver<PokemonEntity, PokemonPoseableModel>.getResolvedVariant(aspects: Set<String>): String? {
     return variations.lastOrNull { it.aspects.all { it in aspects } && (it as IVariant).variant != null }?.let { (it as IVariant).variant }
}
