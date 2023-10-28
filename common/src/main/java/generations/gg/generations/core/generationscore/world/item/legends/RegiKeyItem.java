package generations.gg.generations.core.generationscore.world.item.legends;

import generations.gg.generations.core.generationscore.config.SpeciesKey;
import generations.gg.generations.core.generationscore.world.item.ItemWithLangTooltipImpl;

public class RegiKeyItem extends ItemWithLangTooltipImpl {
    private final SpeciesKey speciesKey;

    public RegiKeyItem(Properties properties, SpeciesKey speciesKey) {
        super(properties);
        this.speciesKey = speciesKey;
    }

    public SpeciesKey getSpeciesKey() {
        return speciesKey;
    }
}
