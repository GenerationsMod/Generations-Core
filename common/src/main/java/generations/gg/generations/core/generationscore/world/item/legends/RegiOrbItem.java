package generations.gg.generations.core.generationscore.world.item.legends;

import generations.gg.generations.core.generationscore.world.item.ItemWithLangTooltipImpl;

public class RegiOrbItem extends ItemWithLangTooltipImpl {
    private final String speciesId;

    public RegiOrbItem(Properties arg, String speciesId) {
        super(arg);
        this.speciesId = speciesId;
    }

    public String getSpeciesId() {
        return speciesId;
    }
}
