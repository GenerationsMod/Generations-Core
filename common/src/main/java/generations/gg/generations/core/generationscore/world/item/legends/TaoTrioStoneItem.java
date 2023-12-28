package generations.gg.generations.core.generationscore.world.item.legends;

import com.cobblemon.mod.common.api.types.ElementalTypes;
import generations.gg.generations.core.generationscore.config.SpeciesKey;

public class TaoTrioStoneItem extends ElementalPostBattleUpdateItemImpl {
    private final SpeciesKey species;

    public TaoTrioStoneItem(Properties arg, SpeciesKey species) {
        super(arg, ElementalTypes.INSTANCE.getDRAGON());
        this.species = species;
    }

    public SpeciesKey getSpecies() {
        return species;
    }
}
