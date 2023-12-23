package generations.gg.generations.core.generationscore.client.model

import com.cobblemon.mod.common.client.render.models.blockbench.PoseableEntityModel
import com.cobblemon.mod.common.client.render.models.blockbench.PoseableEntityState
import com.cobblemon.mod.common.client.render.models.blockbench.animation.StatefulAnimation
import com.cobblemon.mod.common.client.render.models.blockbench.animation.StatelessAnimation
import com.cobblemon.mod.common.client.render.models.blockbench.frame.ModelFrame
import com.cobblemon.mod.common.client.render.models.blockbench.pokemon.AnimationReferenceFactory
import com.cobblemon.mod.common.client.render.models.blockbench.pokemon.JsonPokemonPoseableModel
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import generations.gg.generations.core.generationscore.client.render.PixelmonInstanceProvider
import generations.gg.generations.core.generationscore.client.render.rarecandy.ModelRegistry
import gg.generations.rarecandy.renderer.animation.Animation
import gg.generations.rarecandy.renderer.components.AnimatedMeshObject
import net.minecraft.resources.ResourceLocation
import java.util.function.Supplier

class RareCandyAnimationFactory : AnimationReferenceFactory {
    override fun stateful(
            model: JsonPokemonPoseableModel,
            s: String
    ): StatefulAnimation<PokemonEntity, ModelFrame> {
        val split =
            s.replace("pk(", "").replace(")", "").split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        val location = ResourceLocation(split[0]).withPrefix("bedrock/pokemon/models/")
        val transforms = if(split.size == 3) split[2].asBoolean() else false;
        val pausesPoses = if(split.size == 4) split[3].asBoolean() else false;


        val name = split[1].trim { it <= ' ' }
        return StatefulAnimationRareCandy(Supplier<Animation<Any>> {
            val objects = ModelRegistry.get(location).renderObject
            if (objects.isReady) {
                return@Supplier (objects.objects[0] as AnimatedMeshObject).animations[name]
            }
            null
        }, transforms, pausesPoses)
    }

    override fun stateless(
        jsonPokemonPoseableModel: JsonPokemonPoseableModel,
        s: String
    ): StatelessAnimation<PokemonEntity, ModelFrame> {
        val split =
            s.replace("pk(", "").replace(")", "").split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val location = ResourceLocation(split[0]).withPrefix("bedrock/pokemon/models/")
        val name = split[1].trim { it <= ' ' }
        return StatelessAnimationRareCandy(jsonPokemonPoseableModel, Supplier<Animation<Any>?> {
            val objects = ModelRegistry.get(location).renderObject
            if (objects.isReady) {
                return@Supplier (objects.objects[0] as AnimatedMeshObject).animations[name]
            }
            null
        })
    }

    class StatefulAnimationRareCandy(private val animationSuppler: Supplier<Animation<Any>>?, private val transforms: Boolean, private val pausesPoses: Boolean) : StatefulAnimation<PokemonEntity, ModelFrame> {
        private var secondsPassed = 0f
        override val isTransform: Boolean = false

        override val isPosePauser: Boolean = false //TODO: Implment this.

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
            secondsPassed += poseableEntityState.animationSeconds
            val instance = if (t != null) (t as PixelmonInstanceProvider).instance else ModelRegistry.getGuiInstance()
            val animation = animationSuppler?.get()
            if (instance != null && animation != null) {
                instance.matrixTransforms = animation.getFrameTransform((secondsPassed * animation_factor).toDouble())
                System.out.println(secondsPassed.toString() + " " + animation.animationDuration)
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
        private val animationSupplier: Supplier<Animation<Any>?>
    ) : StatelessAnimation<PokemonEntity, ModelFrame>(jsonPokemonPoseableModel) {
        override val targetFrame = ModelFrame::class.java

        override fun setAngles(
            pokemonEntity: PokemonEntity?,
            poseableEntityModel: PoseableEntityModel<PokemonEntity>,
            state: PoseableEntityState<PokemonEntity>?,
            v: Float,
            v1: Float,
            v2: Float,
            v3: Float,
            v4: Float
        ) {
//            val prev = if (state == null) 0F else (state.previousAnimationSeconds - state.timeEnteredPose)
            val cur = if (state == null) 0f else (state.animationSeconds - state.timeEnteredPose) * animation_factor

            val instance =
                if (pokemonEntity != null) (pokemonEntity as PixelmonInstanceProvider).instance else ModelRegistry.getGuiInstance()
            val animation = animationSupplier.get()

            if (instance != null && animation != null) {
                instance.matrixTransforms = animation.getFrameTransform(cur.toDouble())
            }
        }
    }

    companion object {
        const val animation_factor = 0.001f
    }
}

private fun String.asBoolean(): Boolean = when {
        this.lowercase().equals("true") -> true
        this.lowercase().equals("false") -> false
        else -> false
    }
