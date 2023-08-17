package generations.gg.generations.core.generationscore.world.item;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import dev.architectury.event.EventResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PixelmonInteractions {
    private static final List<PixelmonInteraction> customInteractions = new ArrayList<>();
    public void registerCustomInteraction(PixelmonInteraction customInteraction) {
        customInteractions.add(customInteraction);
    }

//    fun registerDefaultNonItemInteractions() {
//        registerCustomInteraction(PixelmonInteraction { pixelmonEntity: PokemonEntity, player: Player?, itemInHand: ItemStack? ->
//            val data: Unit = pixelmonEntity.getPixelmonData()
//            val holder: Unit = PokeModRegistries.Pixelmon.SPECIES.getHolderOrThrow(data.getSpecies())
//            InteractionResult.PASS
//        })
//    }

    public EventResult triggerCustomInteraction(
        PokemonEntity pixelmonEntity,
        Player player,
        ItemStack itemInHand
    ) {
        return customInteractions.stream().map(interaction -> interaction.interact(pixelmonEntity, player, itemInHand))
            .filter(EventResult::interruptsFurtherEvaluation)
            .findFirst()
            .orElse(EventResult.pass());
    }

    public static EventResult process(Entity entity, Player player, ItemStack stack) {
        if (entity instanceof PokemonEntity pokemon && stack.getItem() instanceof PixelmonInteraction interaction)
            return interaction.interact(pokemon, player, stack);
        else return EventResult.pass();
    }

    public interface PixelmonInteraction {
        EventResult interact(PokemonEntity pokemon, Player player, ItemStack itemInHandItemStack);
        default boolean isConsumed() {
            return true;
        }
    }
}
