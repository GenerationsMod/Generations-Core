package generations.gg.generations.core.generationscore.common.client.model

import com.cobblemon.mod.common.api.molang.ObjectValue
import com.cobblemon.mod.common.client.render.models.blockbench.PoseableEntityModel
import com.cobblemon.mod.common.client.render.models.blockbench.PoseableEntityState
import com.cobblemon.mod.common.client.render.models.blockbench.animation.PrimaryAnimation
import com.cobblemon.mod.common.client.render.models.blockbench.animation.StatefulAnimation
import com.cobblemon.mod.common.client.render.models.blockbench.animation.StatelessAnimation
import com.cobblemon.mod.common.client.render.models.blockbench.frame.ModelFrame
import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext
import com.cobblemon.mod.common.client.render.models.blockbench.wavefunction.WaveFunction
import com.cobblemon.mod.common.util.getBooleanOrNull
import generations.gg.generations.core.generationscore.common.client.render.CobblemonInstanceProvider
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.CobblemonInstance
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.ModelRegistry
import gg.generations.rarecandy.renderer.animation.Animation
import gg.generations.rarecandy.renderer.components.AnimatedMeshObject
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.Entity
import java.util.function.Supplier

object RareCandyAnimationFactory {
    fun <T : Entity> stateful(loc: String, name: String, transforms: Boolean): StatefulAnimationRareCandy<T> {
        val location = ResourceLocation(loc).withPrefix("bedrock/pokemon/models/")

        return StatefulAnimationRareCandy(Supplier<Animation?> {
            val objects = ModelRegistry[location]?.renderObject
            if (objects != null && objects.isReady) {
                return@Supplier (objects.objects[0] as AnimatedMeshObject).animations[name]
            }
            null
        }, Supplier<CobblemonInstance?> { return@Supplier ModelRegistry[location]?.guiInstance }, transforms)
    }

    fun <T: Entity> stateless(model: PoseableEntityModel<T>, loc: String, name: String): StatelessAnimation<T, ModelFrame> {
        val location = ResourceLocation(loc).withPrefix("bedrock/pokemon/models/")
        return StatelessAnimationRareCandy(model, Supplier<Animation?> {
            val objects = ModelRegistry[location]?.renderObject
            if (objects != null && objects.isReady) {
                return@Supplier (objects.objects[0] as AnimatedMeshObject).animations[name]
            }
            null
        }, Supplier<CobblemonInstance?> { return@Supplier ModelRegistry[location]?.guiInstance })
    }

    @JvmStatic
    fun <T : Entity> addAnimationFunctions(model: PoseableEntityModel<T>) {
        model.functions.addFunction("pk_stateful") { params ->
            val group = params.getString(0)
            val animation = params.getString(1)
            val anim = stateful<T>(group, animation, params.getBooleanOrNull(2) ?: false)
            return@addFunction ObjectValue(anim)
        }.addFunction("pk_primary") { params ->
            val group = params.getString(0)
            val animation = params.getString(1)
            val anim = stateful<T>(group, animation, params.getBooleanOrNull(2) ?: false)

            val excludedLabels = mutableSetOf<String>()
            val curve: WaveFunction = { 2F }

            for (index in 2 until params.params.size) {
                val label = params.getString(index) ?: continue
                excludedLabels.add(label)
            }

            return@addFunction ObjectValue(PrimaryAnimation(animation = anim, excludedLabels = excludedLabels, curve = curve))
        }.addFunction("pk") { params ->
                val group = params.getString(0)
                val animation = params.getString(1)
                val anim = stateless(model, group, animation)
                return@addFunction ObjectValue(anim)
        }
    }


    class StatefulAnimationRareCandy<T: Entity>(
        private val animationSuppler: Supplier<Animation?>,
        private val instanceProvider: Supplier<CobblemonInstance?>,
        transforms: Boolean
    ) : StatefulAnimation<T, ModelFrame> {
        private var startedSeconds = -1F
        override val isTransform: Boolean = transforms


        override val duration: Float
            get() = animationSuppler.get()?.let { (it.animationDuration / it.ticksPerSecond * 20).toFloat() } ?: 0.0f

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


            val instance = if (entity != null) (entity as CobblemonInstanceProvider).instance else model.context.request(
                    RenderContext.ENTITY
                )?.takeIf { it is CobblemonInstanceProvider }?.let { it as CobblemonInstanceProvider }?.instance
                    ?: instanceProvider.get()
            val animation = animationSuppler.get()

            if (animation != null) {
                val currentSeconds = state.animationSeconds - startedSeconds
                val animationLength = animation.animationDuration / animation.ticksPerSecond

                if(currentSeconds > animationLength) {
                    return false
                }

                if (instance != null) {
                    instance.setAnimation(animation)

                    instance.matrixTransforms = animation.getFrameTransform((currentSeconds).toDouble())
                    animation.getFrameOffset(instance.currentAnimation!!)
                }

                return true
            }

            return false

        }

        override fun applyEffects(entity: T, state: PoseableEntityState<T>, previousSeconds: Float, newSeconds: Float) = run { }

        fun asMolang() = ObjectValue(this)
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
            val instance =
                if (entity != null) (entity as CobblemonInstanceProvider).instance else model.context.request(
                    RenderContext.ENTITY
                )?.takeIf { it is CobblemonInstanceProvider }?.let { it as CobblemonInstanceProvider }?.instance
                    ?: objectSupplier.get()

            val animation = animationSupplier.get()

            if (instance != null && animation != null) {
                instance.setAnimation(animation)

                instance.matrixTransforms = animation.getFrameTransform((state?.animationSeconds?.toDouble() ?: 0.0).toDouble())

                animation.getFrameOffset(instance.currentAnimation!!)
            }
        }

        fun asMolang() = ObjectValue(this)
    }
}

