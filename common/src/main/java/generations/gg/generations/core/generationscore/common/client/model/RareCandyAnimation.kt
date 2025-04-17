package generations.gg.generations.core.generationscore.common.client.model

import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext
import generations.gg.generations.core.generationscore.common.client.render.CobblemonInstanceProvider
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.ModelRegistry
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.Pipelines
import gg.generations.rarecandy.renderer.animation.Animation
import gg.generations.rarecandy.renderer.components.AnimatedMeshObject
import net.minecraft.resources.ResourceLocation

data class RareCandyAnimation(private val model: ResourceLocation, private val name: String) {

    val animation: Animation?
        get() {
            val objects = ModelRegistry[model]?.renderObject ?: return null
            if (objects.isReady) {
                return (objects.objects[0] as AnimatedMeshObject).animations[name]
            }

            return null
        }

    fun run(
        context: RenderContext,
        animationSeconds: Float,
        shouldLoop: Boolean
    ): Boolean {
        val instance = context.request(Pipelines.INSTANCE) ?: context.request(RenderContext.ENTITY)?.takeIf { it is CobblemonInstanceProvider }?.let { it as CobblemonInstanceProvider }?.instance ?: return false
        val anim = animation ?: return false

        val duration = anim.animationDuration.toFloat()
        var currentSeconds = animationSeconds
        if (shouldLoop) {
            currentSeconds %= duration
        } else if (animationSeconds > duration && duration > 0) {
            return false
        }

        instance.setAnimation(anim)

        instance.matrixTransforms = anim.getFrameTransform((currentSeconds).toDouble())
        anim.getFrameOffset(instance.currentAnimation!!)

        return true
    }
}