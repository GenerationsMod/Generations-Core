package generations.gg.generations.core.generationscore.common.client.model

import com.bedrockk.molang.runtime.MoParams
import com.cobblemon.mod.common.api.molang.ObjectValue
import com.cobblemon.mod.common.client.ClientMoLangFunctions
import com.cobblemon.mod.common.client.render.models.blockbench.PosableModel
import com.cobblemon.mod.common.client.render.models.blockbench.PosableState
import com.cobblemon.mod.common.client.render.models.blockbench.PoseableEntityState
import com.cobblemon.mod.common.client.render.models.blockbench.animation.ActiveAnimation
import com.cobblemon.mod.common.client.render.models.blockbench.animation.PrimaryAnimation
import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext
import com.cobblemon.mod.common.client.render.models.blockbench.wavefunction.WaveFunction
import com.cobblemon.mod.common.util.asResource
import com.cobblemon.mod.common.util.getBooleanOrNull
import generations.gg.generations.core.generationscore.common.client.render.CobblemonInstanceProvider
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.CobblemonInstance
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.ModelRegistry
import gg.generations.rarecandy.renderer.animation.Animation
import gg.generations.rarecandy.renderer.components.AnimatedMeshObject
import net.minecraft.world.entity.Entity
import java.util.function.Function
import java.util.function.Supplier

object RareCandyAnimationFactory {
    data class ActiveRareCandyAnimation(private val animation: RareCandyAnimation): ActiveAnimation {
        private var startedSeconds = -1F
        override val isTransition: Boolean = false
        override var enduresPrimaryAnimations: Boolean = false

        override val duration: Float
            get() = animation.duration


    }

    fun <T : Entity> stateful(loc: String, name: String, transforms: Boolean): RareCandyAnimation<T> {
        val location = loc.asResource().withPrefix("bedrock/pokemon/models/")

        return RareCandyAnimation(Supplier<Animation?> {
            val objects = ModelRegistry[location]?.renderObject
            if (objects != null && objects.isReady) {
                return@Supplier (objects.objects[0] as AnimatedMeshObject).animations[name]
            }
            null
        }, Supplier<CobblemonInstance?> { return@Supplier ModelRegistry[location]?.guiInstance }, transforms)
    }

    fun stateless(model: PosableModel, loc: String, name: String): ActiveAnimation {
        val location = loc.asResource().withPrefix("bedrock/pokemon/models/")
        return RareCandyAnimation(
            {
                val objects = ModelRegistry[location]?.renderObject
                if (objects != null && objects.isReady) {
                    (objects.objects[0] as AnimatedMeshObject).animations[name]
                }
                null
            },
            { ModelRegistry[location]?.guiInstance },
        )
    }

    fun function(function: (PosableModel, MoParams) -> Any): Function<PosableModel, Function<MoParams, Any>> {
        return Function { model -> Function { params -> function.invoke(model, params) } }
    }

    @JvmStatic
    fun <T : Entity> addAnimationFunctions() {

        var map = ClientMoLangFunctions.animationFunctions

        map["pk_stateful"] = function { model, params ->
            val group = params.getString(0)
            val animation = params.getString(1)
            val anim = stateful<T>(group, animation, params.getBooleanOrNull(2) ?: false)
            ObjectValue(anim)
        }

        map["pk_primary"] = function { model, params ->
            val group = params.getString(0)
            val animation = params.getString(1)
            val anim = stateful<T>(group, animation, params.getBooleanOrNull(2) ?: false)

            val excludedLabels = mutableSetOf<String>()
            val curve: WaveFunction = { 2F }

            for (index in 2 until params.params.size) {
                val label = params.getString(index) ?: continue
                excludedLabels.add(label)
            }

            ObjectValue(PrimaryAnimation(animation = anim, excludedLabels = excludedLabels, curve = curve))
        }

        map["pk"] = function { model, params ->
            val group = params.getString(0)
            val animation = params.getString(1)
            val anim = stateless(model, group, animation)
            ObjectValue(anim)
        }
    }


    class RareCandyAnimation(
        private val animationSuppler: Supplier<Animation?>,
        private val instanceProvider: Supplier<CobblemonInstance?>,
    ) {


        fun run(
            context: RenderContext,
            model: PosableModel,
            state: PosableState,
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


            val instance = model.context.request(
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
}

