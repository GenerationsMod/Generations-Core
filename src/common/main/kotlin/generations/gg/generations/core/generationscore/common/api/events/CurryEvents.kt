package generations.gg.generations.core.generationscore.common.api.events

import com.cobblemon.mod.common.api.berry.Berry
import com.cobblemon.mod.common.api.events.Cancelable
import com.cobblemon.mod.common.api.reactive.CancelableObservable
import com.cobblemon.mod.common.api.reactive.EventObservable
import generations.gg.generations.core.generationscore.common.api.player.CurryDex.CurryDexEntry
import generations.gg.generations.core.generationscore.common.world.item.curry.CurryData
import generations.gg.generations.core.generationscore.common.world.item.curry.CurryTasteRating
import generations.gg.generations.core.generationscore.common.world.item.curry.CurryType
import net.minecraft.server.level.ServerPlayer

object CurryEvents {
    val COOK = CancelableObservable<Cook>()

    val MODIFY_RATING = EventObservable<ModifyRating>()

    val ADD_ENTRY = CancelableObservable<AddEntry>()

    data class Cook(val mainIngredient: CurryType, val berries: List<Berry>, var output: CurryData): Cancelable()

    data class AddEntry(val player: ServerPlayer, val curry: CurryData, var entry: CurryDexEntry): Cancelable()

    data class ModifyRating(val player: ServerPlayer, val data: CurryData, var rating: CurryTasteRating)
}
