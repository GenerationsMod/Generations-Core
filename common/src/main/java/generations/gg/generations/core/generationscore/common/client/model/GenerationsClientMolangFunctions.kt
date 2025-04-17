package generations.gg.generations.core.generationscore.common.client.model

import com.bedrockk.molang.runtime.MoParams
import com.cobblemon.mod.common.api.molang.ObjectValue
import com.cobblemon.mod.common.client.ClientMoLangFunctions
import com.cobblemon.mod.common.client.render.models.blockbench.PosableModel
import com.cobblemon.mod.common.client.render.models.blockbench.animation.PrimaryAnimation
import com.cobblemon.mod.common.client.render.models.blockbench.wavefunction.WaveFunction
import com.cobblemon.mod.common.util.asResource
import net.minecraft.world.entity.Entity
import java.util.function.Function

object GenerationsClientMolangFunctions {

    private fun pkStateful(
        model: String,
        name: String
    ) = RareCandyActiveAnimation(RareCandyAnimation(model.asResource().withPrefix("bedrock/pokemon/models/"), name))

    fun pk(
        model: String,
        name: String
    ) = RareCandyPoseAnimation(RareCandyAnimation(model.asResource().withPrefix("bedrock/pokemon/models/"), name))

    fun function(function: (PosableModel, MoParams) -> Any): Function<PosableModel, Function<MoParams, Any>> {
        return Function { model -> Function { params -> function.invoke(model, params) } }
    }

    @JvmStatic
    fun addAnimationFunctions() {

        val map = ClientMoLangFunctions.animationFunctions

        map["pk_primary"] = function { _, params ->
            val group = params.getString(0)
            val animation = params.getString(1)
            val anim = pkStateful(group, animation)

            val excludedLabels = mutableSetOf<String>()
            val curve: WaveFunction = { t ->
                if (t < 0.1) {
                    t * 10
                } else if (t < 0.9) {
                    1F
                } else {
                    1F
                }
            }

            for (index in 2 until params.params.size) {
                val label = params.getString(index) ?: continue
                excludedLabels.add(label)
            }

            ObjectValue(PrimaryAnimation(animation = anim, excludedLabels = excludedLabels, curve = curve))
        }

        map["pk_stateful"] = function { _, params ->
            val group = params.getString(0)
            val animation = params.getString(1)
            val anim = pkStateful(group, animation)
            val enduresPrimary = "endures_primary_animations" in params.params.mapNotNull { it.asString() }
            anim.enduresPrimaryAnimations = enduresPrimary
            ObjectValue(anim)
        }



        map["pk"] = function { _, params ->
            val group = params.getString(0)
            val animation = params.getString(1)
            val anim = pk(group, animation)
            ObjectValue(anim)
        }
    }
}

