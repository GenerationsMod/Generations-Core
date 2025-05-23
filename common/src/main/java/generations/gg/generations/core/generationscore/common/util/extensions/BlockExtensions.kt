package generations.gg.generations.core.generationscore.common.util.extensions

import net.minecraft.world.InteractionResult
import net.minecraft.world.ItemInteractionResult
import java.util.function.Supplier

fun Int.between(min: Int, max: Int): Boolean {
    return this in min..max
}

fun Boolean?.toItemInteractionResult(): ItemInteractionResult {
    return when(this) {
        true -> ItemInteractionResult.SUCCESS
        false -> ItemInteractionResult.FAIL
        null -> ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION
    }
}

fun Boolean?.toInteractionResult(): InteractionResult {
    return when(this) {
        true -> InteractionResult.SUCCESS
        false -> InteractionResult.FAIL
        null -> InteractionResult.PASS
    }
}

fun <T> T.supplier(): Supplier<T> = Supplier { this }
