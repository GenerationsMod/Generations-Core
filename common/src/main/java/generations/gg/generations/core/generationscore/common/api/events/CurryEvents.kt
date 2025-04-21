package generations.gg.generations.core.generationscore.common.api.events

import com.cobblemon.mod.common.api.berry.Berry
import dev.architectury.event.Event
import dev.architectury.event.EventActor
import dev.architectury.event.EventFactory
import generations.gg.generations.core.generationscore.common.api.player.CurryDex.CurryDexEntry
import generations.gg.generations.core.generationscore.common.world.item.curry.CurryData
import generations.gg.generations.core.generationscore.common.world.item.curry.CurryTasteRating
import generations.gg.generations.core.generationscore.common.world.item.curry.CurryType
import net.minecraft.server.level.ServerPlayer
import java.util.function.Function

object CurryEvents {
    val COOK: Event<EventActor<Cook>> = EventFactory.createEventActorLoop()

    val MODIFY_RATING: Event<ModifyRating> = EventFactory.of(
        { eventActors: List<ModifyRating> ->
            return@of ModifyRating { original: CurryTasteRating, player: ServerPlayer, data: CurryData ->
                var rating: CurryTasteRating? = null
                for (actor in eventActors) {
                    rating = actor.modifyRating(original, player, data)
                }
                rating ?: original
            }
        })

    val ADD_ENTRY: Event<EventActor<AddEntry>> = EventFactory.createEventActorLoop()

    class Cook(val mainIngredient: CurryType, val berries: List<Berry>, var output: CurryData)

    class AddEntry(val player: ServerPlayer, val curry: CurryData, var entry: CurryDexEntry)

    fun interface ModifyRating {
        fun modifyRating(original: CurryTasteRating, player: ServerPlayer, data: CurryData): CurryTasteRating?
    }
}
