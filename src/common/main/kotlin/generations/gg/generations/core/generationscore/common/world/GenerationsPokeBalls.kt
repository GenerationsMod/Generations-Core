package generations.gg.generations.core.generationscore.common.world

import com.cobblemon.mod.common.api.pokeball.catching.CaptureEffect
import com.cobblemon.mod.common.api.pokeball.catching.CatchRateModifier
import com.cobblemon.mod.common.api.pokeball.catching.modifiers.MultiplierModifier
import com.cobblemon.mod.common.pokeball.PokeBall
import com.cobblemon.mod.common.util.cobblemonResource
import net.minecraft.resources.ResourceLocation

object GenerationsPokeBalls {
    val STRANGE_BALL = createDefault("strange_ball")

    private fun createDefault(
        name: String,
        modifier: CatchRateModifier = MultiplierModifier(1F) { _, _ -> true },
        effects: List<CaptureEffect> = emptyList(),
        waterDragValue: Float = 0.8F,
        model2d: ResourceLocation = cobblemonResource(name),
        model3d: ResourceLocation = cobblemonResource("${name}_model"),
        throwPower: Float = 1.25f,
        ancient: Boolean = false
    ): PokeBall {
        val identifier = cobblemonResource(name)
        val pokeball = PokeBall(identifier, modifier, effects, waterDragValue, model2d, model3d, throwPower, ancient)
        return pokeball
    }
}
