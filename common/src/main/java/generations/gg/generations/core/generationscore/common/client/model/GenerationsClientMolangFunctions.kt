package generations.gg.generations.core.generationscore.common.client.model

import com.bedrockk.molang.runtime.MoParams
import com.cobblemon.mod.common.api.molang.ObjectValue
import com.cobblemon.mod.common.client.ClientMoLangFunctions
import com.cobblemon.mod.common.client.render.models.blockbench.PosableModel
import com.cobblemon.mod.common.client.render.models.blockbench.animation.ActiveAnimation
import com.cobblemon.mod.common.client.render.models.blockbench.animation.PrimaryAnimation
import com.cobblemon.mod.common.client.render.models.blockbench.wavefunction.WaveFunction
import com.cobblemon.mod.common.util.asResource
import com.cobblemon.mod.common.util.getBooleanOrNull
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.CobblemonInstance
import generations.gg.generations.core.generationscore.common.client.render.rarecandy.ModelRegistry
import gg.generations.rarecandy.renderer.animation.Animation
import gg.generations.rarecandy.renderer.components.AnimatedMeshObject
import net.minecraft.world.entity.Entity
import java.util.function.Function
import java.util.function.Supplier

object RareCandyAnimationFactory {

    fun <T : Entity> PosableModel.pkStateful(model: String, name: String) {
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
        return RareCandyAnimation {
            val objects = ModelRegistry[location]?.renderObject
            if (objects != null && objects.isReady) {
                (objects.objects[0] as AnimatedMeshObject).animations[name]
            }
            null
        }
    }

    fun function(function: (PosableModel, MoParams) -> Any): Function<PosableModel, Function<MoParams, Any>> {
        return Function { model -> Function { params -> function.invoke(model, params) } }
    }

    @JvmStatic
    fun <T : Entity> addAnimationFunctions() {

        var map = ClientMoLangFunctions.animationFunctions

        map["pk_primary"] = function { model, params ->
            val group = params.getString(0)
            val animation = params.getString(1)
            val anim = stateful<T>(group, animation, params.getBooleanOrNull(2) ?: false)

            val excludedLabels = mutableSetOf<String>()
            var curve: WaveFunction = { t ->
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

        map["pk_stateful"] = function { model, params ->
            val group = params.getString(0)
            val animation = params.getString(1)
            val anim = stateful<T>(group, animation, params.getBooleanOrNull(2) ?: false)
            ObjectValue(anim)
        }



        map["pk"] = function { model, params ->
            val group = params.getString(0)
            val animation = params.getString(1)
            val anim = stateless(model, group, animation)
            ObjectValue(anim)
        }
    }


}

