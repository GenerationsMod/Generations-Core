package generations.gg.generations.core.generationscore.world.item.legends;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.config.Key;
import generations.gg.generations.core.generationscore.world.item.ItemWithLangTooltipImpl;
import net.minecraft.resources.ResourceLocation;

public class RegiKeyItem extends ItemWithLangTooltipImpl {
    private final Key speciesId;

    public RegiKeyItem(Properties properties, Key speciesKey) {
        super(properties);
        this.speciesId = speciesKey;
    }

    public Key getSpeciesId() {
        return speciesId;
    }
}
