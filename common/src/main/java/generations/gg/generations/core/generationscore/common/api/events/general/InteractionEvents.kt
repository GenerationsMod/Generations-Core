package generations.gg.generations.core.generationscore.common.api.events.general

import com.cobblemon.mod.common.api.events.Cancelable
import com.cobblemon.mod.common.api.reactive.CancelableObservable
import dev.architectury.event.EventResult
import generations.gg.generations.core.generationscore.common.api.events.TriState
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player


object InteractionEvents {
    val RIGHT_CLICK_BLOCK = CancelableObservable<RightClickBlock>()

    @JvmStatic fun fireRightClick(player: Player, hand: InteractionHand, pos:BlockPos, face: Direction?): InteractionResult {
        var result = InteractionResult.PASS

        InteractionEvents.RIGHT_CLICK_BLOCK.postThen(InteractionEvents.RightClickBlock(player, hand, pos, face, null), {
            result = InteractionResult.FAIL
        }, {
            if(it.result != null) result = it.result!!.asMinecraft()
        })

        return result;
    }

    val ENTITY_INTERACT = CancelableObservable<EntityInteract>()

    @JvmStatic fun fireEntityInteract(player: Player, entity: Entity, hand: InteractionHand): InteractionResult {
        var result = InteractionResult.PASS

        InteractionEvents.ENTITY_INTERACT.postThen(InteractionEvents.EntityInteract(player, entity, hand, null), {
            result = InteractionResult.FAIL
        }, {
            if(it.result != null) result = it.result!!.asMinecraft()
        })

        return result;
    }

    data class RightClickBlock(val player: Player, val hand: InteractionHand, val pos: BlockPos, val face: Direction?, var result: TriState?) : Cancelable()

    data class EntityInteract(val player: Player, val entity: Entity, val hand: InteractionHand, var result: TriState? = null): Cancelable()
}