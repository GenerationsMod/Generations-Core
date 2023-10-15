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
import net.minecraft.world.entity.Entity
import java.util.function.Supplier

class RareCandyAnimationFactory : AnimationReferenceFactory {
    override fun stateful(
        jsonPokemonPoseableModel: JsonPokemonPoseableModel,
        s: String
    ): StatefulAnimation<PokemonEntity, ModelFrame> {
        val split =
            s.replace("pk(", "").replace(")", "").split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()



        val location = ResourceLocation(split[0]).withPrefix("bedrock/pokemon/models/")
        val name = split[1].trim { it <= ' ' }
        return StatefulAnimationRareCandy(Supplier<Animation?> {
            val objects = ModelRegistry.get(location, "animated_block").renderObject
            if (objects.isReady) {
                return@Supplier (objects.objects[0] as AnimatedMeshObject).animations[name]
            }
            null
        })
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
            val objects = ModelRegistry.get(location, "animated_block").renderObject
            if (objects.isReady) {
                return@Supplier (objects.objects[0] as AnimatedMeshObject).animations[name]
            }
            null
        })
    }

    class StatefulAnimationRareCandy(private val animationSuppler: Supplier<Animation?>) :
        StatefulAnimation<PokemonEntity, ModelFrame> {
        private var secondsPassed = 0f
        override val isTransform: Boolean
            get() = false

        override val isPosePauser: Boolean
            get() = false //TODO: Implment this.

        override fun preventsIdle(
            t: PokemonEntity?,
            poseableEntityState: PoseableEntityState<PokemonEntity>,
            statelessAnimation: StatelessAnimation<PokemonEntity, *>
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
            val animation = animationSuppler.get()
            if (instance != null && animation != null) instance.matrixTransforms =
                animation.getFrameTransform((secondsPassed * animation_factor).toDouble())
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
        private val animationSupplier: Supplier<Animation?>
    ) : StatelessAnimation<PokemonEntity, ModelFrame>(jsonPokemonPoseableModel) {
        override val targetFrame: Class<ModelFrame>
            get() = frame!!.javaClass

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
            if (instance != null && animation != null) instance.matrixTransforms =
                animationSupplier.get()!!.getFrameTransform(cur.toDouble())
        }
    }

    companion object {
        const val animation_factor = 0.0625f
    }
}