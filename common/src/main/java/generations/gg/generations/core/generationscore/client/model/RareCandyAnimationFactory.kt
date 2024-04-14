package generations.gg.generations.core.generationscore.client.model

import com.cobblemon.mod.common.client.render.models.blockbench.PoseableEntityModel
import com.cobblemon.mod.common.client.render.models.blockbench.PoseableEntityState
import com.cobblemon.mod.common.client.render.models.blockbench.animation.StatefulAnimation
import com.cobblemon.mod.common.client.render.models.blockbench.animation.StatelessAnimation
import com.cobblemon.mod.common.client.render.models.blockbench.frame.ModelFrame
import com.cobblemon.mod.common.client.render.models.blockbench.pokemon.AnimationReferenceFactory
import com.cobblemon.mod.common.client.render.models.blockbench.pokemon.JsonPokemonPoseableModel
import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import generations.gg.generations.core.generationscore.client.render.PixelmonInstanceProvider
import generations.gg.generations.core.generationscore.client.render.rarecandy.ModelRegistry
import generations.gg.generations.core.generationscore.client.render.rarecandy.PixelmonInstance
import gg.generations.rarecandy.renderer.animation.Animation
import gg.generations.rarecandy.renderer.animation.AnimationInstance
import gg.generations.rarecandy.renderer.components.AnimatedMeshObject
import gg.generations.rarecandy.renderer.storage.AnimatedObjectInstance
import net.minecraft.resources.ResourceLocation
import java.util.function.Supplier

class RareCandyAnimationFactory : AnimationReferenceFactory {
    override fun stateful(
        model: JsonPokemonPoseableModel,
        s: String
    ): StatefulAnimationRareCandy {
        val split =
            s.replace("pk(", "").replace(")", "").split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        val location = ResourceLocation(split[0]).withPrefix("bedrock/pokemon/models/")
        val transforms = if(split.size == 3) split[2].asBoolean() else false
        val pausesPoses = if(split.size == 4) split[3].asBoolean() else false


        val name = split[1].trim { it <= ' ' }
        return StatefulAnimationRareCandy(Supplier<Animation> {
            val objects = ModelRegistry.get(location).renderObject
            if (objects.isReady) {
                return@Supplier (objects.objects[0] as AnimatedMeshObject).animations[name]
            }
            null
        }, Supplier<PixelmonInstance> { return@Supplier ModelRegistry.get(location).guiInstance }, transforms, pausesPoses)
    }

    override fun stateless(
        jsonPokemonPoseableModel: JsonPokemonPoseableModel,
        s: String
    ): StatelessAnimation<PokemonEntity, ModelFrame> {
        val split =
            s.replace("pk(", "").replace(")", "").split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val location = ResourceLocation(split[0]).withPrefix("bedrock/pokemon/models/")
        val name = split[1].trim { it <= ' ' }
        return StatelessAnimationRareCandy(jsonPokemonPoseableModel, Supplier<Animation?> {
            val objects = ModelRegistry.get(location).renderObject
            if (objects.isReady) {
                return@Supplier (objects.objects[0] as AnimatedMeshObject).animations[name]
            }
            null
        }, Supplier<PixelmonInstance> { return@Supplier ModelRegistry.get(location).guiInstance })
    }

    class StatefulAnimationRareCandy(private val animationSuppler: Supplier<Animation>?, private val instanceProvider: Supplier<PixelmonInstance>, transforms: Boolean, pausesPoses: Boolean) : StatefulAnimation<PokemonEntity, ModelFrame> {
        var startedSeconds = -1F
        override val isTransform: Boolean = transforms

        override val isPosePauser: Boolean = pausesPoses

        override fun preventsIdle(
            entity: PokemonEntity?,
            state: PoseableEntityState<PokemonEntity>,
            idleAnimation: StatelessAnimation<PokemonEntity, *>
        ): Boolean {
            return false
        }

        override fun run(
            t: PokemonEntity?,
            poseableEntityModel: PoseableEntityModel<PokemonEntity>,
            poseableEntityState: PoseableEntityState<PokemonEntity>,
            v: Float,
            v1: Float,
            v2: Float,
            v3: Float,
            v4: Float
        ): Boolean {

            if (startedSeconds == -1F) {
                startedSeconds = poseableEntityState.animationSeconds
            }

            val instance = if (t != null) (t as PixelmonInstanceProvider).instance else poseableEntityModel.context.request(RenderContext.ENTITY)?.takeIf { it is PixelmonInstanceProvider }?.let { it as PixelmonInstanceProvider }?.instance ?: instanceProvider.get()
            val animation = animationSuppler?.get()
            if (animation != null && instance != null) {
                instance.setAnimation(animation)
                instance.currentAnimation!!.startTime = startedSeconds.toDouble()
                instance.currentAnimation!!.update(poseableEntityState.animationSeconds.toDouble())
                instance.matrixTransforms = animation.getFrameTransform(instance.currentAnimation!!)
                animation.getFrameOffset(instance.currentAnimation!!)
            }
            return true
        }

        override fun applyEffects(
            pokemon: PokemonEntity,
            poseableEntityState: PoseableEntityState<PokemonEntity>,
            v: Float,
            v1: Float
        ) {
        }
    }

    private class StatelessAnimationRareCandy (
        jsonPokemonPoseableModel: JsonPokemonPoseableModel,
        private val animationSupplier: Supplier<Animation?>,
        private val objectSupplier: Supplier<PixelmonInstance>
    ) : StatelessAnimation<PokemonEntity, ModelFrame>(jsonPokemonPoseableModel) {
        override val targetFrame = ModelFrame::class.java

        override fun setAngles(
            t: PokemonEntity?,
            poseableEntityModel: PoseableEntityModel<PokemonEntity>,
            state: PoseableEntityState<PokemonEntity>?,
            limbSwing: Float,
            limbSwingAmount: Float,
            ageInTicks: Float,
            headYaw: Float,
            headPitch: Float
        ) {
            val instance = if (t != null) (t as PixelmonInstanceProvider).instance else poseableEntityModel.context.request(RenderContext.ENTITY)?.takeIf { it is PixelmonInstanceProvider }?.let { it as PixelmonInstanceProvider }?.instance ?: objectSupplier.get()

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
    this.lowercase().equals("true") -> true
    this.lowercase().equals("false") -> false
    else -> false
}
