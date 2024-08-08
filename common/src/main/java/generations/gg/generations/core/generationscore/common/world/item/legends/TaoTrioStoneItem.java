package generations.gg.generations.core.generationscore.common.world.item.legends;

import com.cobblemon.mod.common.api.types.ElementalTypes;
import generations.gg.generations.core.generationscore.common.config.SpeciesKey;
import generations.gg.generations.core.generationscore.common.config.SpeciesKey;
import generations.gg.generations.core.generationscore.common.config.SpeciesKey;

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
