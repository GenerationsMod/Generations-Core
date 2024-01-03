package generations.gg.generations.core.generationscore.world.feature;

import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

/**
 * Holds Ore placement creation methods
 * @see PlacementModifier
 * @author Joseph T. McQuigg
 */
public class GenerationsOrePlacements {

    private static List<PlacementModifier> orePlacement(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, InSquarePlacement.spread(), heightModifier, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier heightRange) {
        return orePlacement(CountPlacement.of(count), heightRange);
    }

    public static List<PlacementModifier> rareOrePlacement(int chance, PlacementModifier heightRange) {
        return orePlacement(RarityFilter.onAverageOnceEvery(chance), heightRange);
    }

}
