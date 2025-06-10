package generations.gg.generations.core.generationscore.common.client.model

import com.cobblemon.mod.common.client.render.models.blockbench.PosableModel
import com.cobblemon.mod.common.client.render.models.blockbench.PosableState
import com.cobblemon.mod.common.client.render.models.blockbench.animation.ActiveAnimation
import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext
import net.minecraft.world.entity.Entity

data class RareCandyActiveAnimation(private val animation: RareCandyAnimation): ActiveAnimation {
    private var startedSeconds = -1F
    override var enduresPrimaryAnimations: Boolean = true

    override val duration: Float
        get() = animation.animation?.animationDuration?.toFloat() ?: -0F

    private var afterAction: (RenderContext, PosableState) -> Unit = { _, _ -> }

    override val isTransition: Boolean = false

    fun andThen(action: (context: RenderContext, PosableState) -> Unit) = this.also {
        it.afterAction = action
    }

    override fun start(state: PosableState) {
        super.start(state)
        startedSeconds = state.animationSeconds
    }

    override fun run(
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
        return animation.run(context, state.animationSeconds - startedSeconds, intensity).also {
            if (!it) {
                afterAction(context, state)
            }
        }
    }

    override fun applyEffects(entity: Entity?, state: PosableState, previousSeconds: Float, newSeconds: Float) {
//        val previousSecondsOffset = previousSeconds - startedSeconds
//        val currentSecondsOffset = newSeconds - startedSeconds
//        animation.applyEffects(entity, state, previousSecondsOffset, currentSecondsOffset)
    }
}