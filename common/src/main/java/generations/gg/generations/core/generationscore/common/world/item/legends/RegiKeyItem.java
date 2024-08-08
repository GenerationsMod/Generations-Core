package generations.gg.generations.core.generationscore.common.world.item.legends;

import generations.gg.generations.core.generationscore.common.config.SpeciesKey;
import generations.gg.generations.core.generationscore.common.config.SpeciesKey;
import generations.gg.generations.core.generationscore.common.config.SpeciesKey;
import generations.gg.generations.core.generationscore.common.world.item.ItemWithLangTooltipImpl;

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
