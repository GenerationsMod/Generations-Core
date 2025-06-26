package generations.gg.generations.core.generationscore.common.api.events

import net.minecraft.world.InteractionResult

enum class TriState {
    TRUE, FALSE, INDETERMINATE;

    fun asMinecraft(): InteractionResult = when(this) {
        TRUE -> InteractionResult.SUCCESS
        FALSE -> InteractionResult.FAIL
        INDETERMINATE -> InteractionResult.PASS
    }
}