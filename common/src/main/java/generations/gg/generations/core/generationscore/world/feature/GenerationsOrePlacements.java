package generations.gg.generations.core.generationscore.world.feature;

import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

/**
 * Holds Ore placement creation methods
 * @see PlacementModifier
 * @author Joseph T. McQuigg
 */
public class GenerationsOrePlacements {

    private static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, InSquarePlacement.spread(), heightModifier, BiomeFilter.biome());
    }

    public static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacement.of(count), heightModifier);
    }

    public static List<PlacementModifier> modifiersWithChanceAndRange(int chance, PlacementModifier heightModifier) {
        return modifiers(RarityFilter.onAverageOnceEvery(chance), heightModifier);
    }

}
