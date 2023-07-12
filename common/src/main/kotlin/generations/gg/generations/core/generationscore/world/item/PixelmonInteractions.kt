package generations.gg.generations.core.generationscore.world.item

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import dev.architectury.event.EventResult
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack

object PixelmonInteractions {
    private val customInteractions: MutableList<PixelmonInteraction> = ArrayList()
    fun registerCustomInteraction(customInteraction: PixelmonInteraction) {
        customInteractions.add(customInteraction)
    }

//    fun registerDefaultNonItemInteractions() {
//        registerCustomInteraction(PixelmonInteraction { pixelmonEntity: PokemonEntity, player: Player?, itemInHand: ItemStack? ->
//            val data: Unit = pixelmonEntity.getPixelmonData()
//            val holder: Unit = PokeModRegistries.Pixelmon.SPECIES.getHolderOrThrow(data.getSpecies())
//            InteractionResult.PASS
//        })
//    }

    fun triggerCustomInteraction(
        pixelmonEntity: PokemonEntity,
        player: Player,
        itemInHand: ItemStack
    ): EventResult {
        return customInteractions.stream()
            .map { a: PixelmonInteraction -> a.interact(pixelmonEntity, player, itemInHand) }
            .filter { obj: EventResult -> obj.interruptsFurtherEvaluation() }
            .findFirst()
            .orElse(EventResult.pass())
    }

    @JvmStatic
    fun process(entity: Entity, player: Player, stack: ItemStack): EventResult =
        if (entity is PokemonEntity && stack.item is PixelmonInteraction) (stack.item as PixelmonInteraction).interact(entity, player, stack)
        else EventResult.pass()

    interface PixelmonInteraction {
        fun interact(pokemon: PokemonEntity, player: Player, itemInHand: ItemStack): EventResult
        val isConsumed: Boolean
            get() = true
    }
}
