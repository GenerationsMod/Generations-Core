package generations.gg.generations.core.generationscore.config;

import com.cobblemon.mod.common.pokemon.Pokemon;
import generations.gg.generations.core.generationscore.GenerationsCore;
import net.minecraft.resources.ResourceLocation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record Key(ResourceLocation species, Set<String> aspects) {
    public Key(String species, Set<String> aspects) {
        this(new ResourceLocation(species), aspects);
    }

    public Key(String species) {
        this(species, null);
    }

    @Override
    public String toString() {
        String setString = aspects != null ? aspects.stream().collect(Collectors.joining(",", "[", "]")) : "";
        return species.toString() + setString;
    }

    public Key(ResourceLocation species) {
        this(species, null);
    }

    public static Key fromPokemon(Pokemon pokemon) {
        Set<String> aspects;

        if (pokemon.getAspects().size() != 0) {
            var trackedAspects = GenerationsCore.CONFIG.caught.trackedAspects;
            aspects = pokemon.getAspects().stream().filter(trackedAspects::contains).collect(Collectors.toSet());
        } else {
            aspects = Set.of();
        }

        return new Key(pokemon.getSpecies().resourceIdentifier, aspects.isEmpty() ? null : aspects);
    }

    public static Key fromString(String input) {
        String[] parts = input.split("\\[|\\]");

        ResourceLocation species = new ResourceLocation(parts[0]);

        Set<String> aspects = null;
        if (parts.length == 2) {

            String values = parts[1];


            // Check if the values are not null and not empty
            if (values != null && !values.isEmpty()) {
                String[] valueArray = values.split(",");

                aspects = new HashSet<>(Arrays.asList(valueArray));
            }
        }
        return new Key(species, aspects);
    }
}
