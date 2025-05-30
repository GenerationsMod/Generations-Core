package generations.gg.generations.core.generationscore.common.event;

import com.cobblemon.mod.common.api.events.pokemon.HeldItemEvent;
import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature;
import com.cobblemon.mod.common.api.types.tera.TeraTypes;
import com.cobblemon.mod.common.pokemon.Pokemon;
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems;

public class HeldItemFormeChange {
    public static void ogerMaskChange(HeldItemEvent.Post post) {
        Pokemon pokemon = post.getPokemon();

        if (!pokemon.getSpecies().getName().equals("Ogerpon")) {
            return;
        }
        System.out.println("Reaches HeldItemFormeChange");
        if (post.getReceived().is(GenerationsItems.CORNERSTONE_MASK.get())) {
            new StringSpeciesFeature("ogerpon_mask", "cornerstone").apply(pokemon);
            pokemon.setTeraType(TeraTypes.getROCK());
            System.out.println("Tera type set to Rock");
        } else if (post.getReceived().is(GenerationsItems.HEARTHFLAME_MASK.get())) {
            new StringSpeciesFeature("ogerpon_mask", "hearthflame").apply(pokemon);
            pokemon.setTeraType(TeraTypes.getFIRE());
            System.out.println("Tera type set to Fire");
        } else if (post.getReceived().is(GenerationsItems.WELLSPRING_MASK.get())) {
            new StringSpeciesFeature("ogerpon_mask", "wellspring").apply(pokemon);
            pokemon.setTeraType(TeraTypes.getWATER());
            System.out.println("Tera type set to Water");
        } else if (post.getReturned().is(GenerationsItems.CORNERSTONE_MASK.get()) || post.getReturned().is(GenerationsItems.HEARTHFLAME_MASK.get()) || post.getReturned().is(GenerationsItems.WELLSPRING_MASK.get())) {
            new StringSpeciesFeature("ogerpon_mask", "teal").apply(pokemon);
            pokemon.setTeraType(TeraTypes.getGRASS());
        }
    }
}
