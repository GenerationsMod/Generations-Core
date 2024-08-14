package generations.gg.generations.core.generationscore.common.client.model

import com.cobblemon.mod.common.client.render.models.blockbench.PoseableEntityModel
import com.cobblemon.mod.common.client.render.models.blockbench.PoseableEntityState
import com.cobblemon.mod.common.client.render.models.blockbench.animation.StatefulAnimation
import com.cobblemon.mod.common.client.render.models.blockbench.animation.StatelessAnimation
import com.cobblemon.mod.common.client.render.models.blockbench.frame.ModelFrame
import com.cobblemon.mod.common.client.render.models.blockbench.pokemon.AnimationReferenceFactory
import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext
import generations.gg.generations.core.generationscore.common.client.render.CobblemonInstanceProvider
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.CobblemonInstance
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.ModelRegistry
import gg.generations.rarecandy.renderer.animation.Animation
import gg.generations.rarecandy.renderer.components.AnimatedMeshObject
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.Entity
import java.util.function.Supplier

class RareCandyAnimationFactory : AnimationReferenceFactory {
    override fun <T : Entity> stateful(model: PoseableEntityModel<T>, animString: String): StatefulAnimationRareCandy<T> {
        val split = animString.replace("pk(", "").replace(")", "").split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        val location = ResourceLocation(split[0]).withPrefix("bedrock/pokemon/models/")
        val transforms = if(split.size == 3) split[2].asBoolean() else false

        val name = split[1].trim { it <= ' ' }
        return StatefulAnimationRareCandy(Supplier<Animation> {
            val objects = ModelRegistry[location]?.renderObject
            if (objects != null && objects.isReady) {
                return@Supplier (objects.objects[0] as AnimatedMeshObject).animations[name]
            }
            null
        }, Supplier<CobblemonInstance?> { return@Supplier ModelRegistry[location]?.guiInstance }, transforms)
    }

    override fun <T: Entity> stateless(model: PoseableEntityModel<T>, animString: String): StatelessAnimation<T, ModelFrame> {
        val split = animString.replace("pk(", "").replace(")", "").split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val location = ResourceLocation(split[0]).withPrefix("bedrock/pokemon/models/")
        val name = split[1].trim { it <= ' ' }

        return StatelessAnimationRareCandy(model, Supplier<Animation?> {
            val objects = ModelRegistry[location]?.renderObject
            if (objects != null && objects.isReady) {
                return@Supplier (objects.objects[0] as AnimatedMeshObject).animations[name]
            }
            null
        }, Supplier<CobblemonInstance?> { return@Supplier ModelRegistry[location]?.guiInstance })
    }

    class StatefulAnimationRareCandy<T: Entity>(
        private val animationSuppler: Supplier<Animation>?,
        private val instanceProvider: Supplier<CobblemonInstance?>,
        transforms: Boolean
    ) : StatefulAnimation<T, ModelFrame> {
        private var startedSeconds = -1F
        override val isTransform: Boolean = transforms

        override val duration: Float get() = animationSuppler?.get()?.animationDuration?.toFloat() ?: 0.0f

        override fun run(
            entity: T?,
            model: PoseableEntityModel<T>,
            state: PoseableEntityState<T>,
            limbSwing: Float,
            limbSwingAmount: Float,
            ageInTicks: Float,
            headYaw: Float,
            headPitch: Float,
            intensity: Float
        ): Boolean {

            if (startedSeconds == -1F) {
                startedSeconds = state.animationSeconds
            }

            val instance =
                if (entity != null) (entity as CobblemonInstanceProvider).instance else model.context.request(
                    RenderContext.ENTITY
                )?.takeIf { it is CobblemonInstanceProvider }?.let { it as CobblemonInstanceProvider }?.instance
                    ?: instanceProvider.get()
            val animation = animationSuppler?.get()
            if (animation != null && instance != null) {
                instance.setAnimation(animation)
                instance.currentAnimation!!.startTime = startedSeconds.toDouble()
                instance.currentAnimation!!.update(state.animationSeconds.toDouble())
                instance.matrixTransforms = animation.getFrameTransform(instance.currentAnimation!!)
                animation.getFrameOffset(instance.currentAnimation!!)
            }
            return true
        }

        override fun applyEffects(entity: T, state: PoseableEntityState<T>, previousSeconds: Float, newSeconds: Float) = run { }

    }

    private class StatelessAnimationRareCandy<T: Entity>(frame: ModelFrame, private val animationSupplier: Supplier<Animation?>, private val objectSupplier: Supplier<CobblemonInstance?>) : StatelessAnimation<T, ModelFrame>(frame) {
        override val targetFrame = ModelFrame::class.java

        override fun setAngles(entity: T?, model: PoseableEntityModel<T>,
                               state: PoseableEntityState<T>?,
                               limbSwing: Float,
                               limbSwingAmount: Float,
                               ageInTicks: Float,
                               headYaw: Float,
                               headPitch: Float,
                               intensity: Float
        ) {
            val instance = if (entity != null) (entity as CobblemonInstanceProvider).instance else model.context.request(RenderContext.ENTITY)?.takeIf { it is CobblemonInstanceProvider }?.let { it as CobblemonInstanceProvider }?.instance ?: objectSupplier.get()

            val animation = animationSupplier.get()

            if (instance != null && animation != null) {
                instance.setAnimation(animation)
                instance.currentAnimation!!.startTime = 0.0
                instance.currentAnimation!!.update(state?.animationSeconds?.toDouble() ?: 0.0)
                instance.matrixTransforms = animation.getFrameTransform(instance.currentAnimation!!)
                animation.getFrameOffset(instance.currentAnimation!!)
            }
        }
    }

}

private fun String.asBoolean(): Boolean = when {
    this.lowercase() == "true" -> true
    this.lowercase() == "false" -> false
    else -> false
}
