package generations.gg.generations.core.generationscore.common.world.item;

import com.cobblemon.mod.common.api.types.ElementalType;
import generations.gg.generations.core.generationscore.common.config.SpeciesKey;
import generations.gg.generations.core.generationscore.common.config.SpeciesKey;
import generations.gg.generations.core.generationscore.common.config.SpeciesKey;

public class WingItem extends ItemWithLangTooltipImpl {
    private final String name;
    private final ElementalType type;
    private final SpeciesKey key;

    public WingItem(Properties properties, String name, ElementalType type, SpeciesKey key) {
        super(properties);
        this.name = name;
        this.type = type;
        this.key = key;
    }

    public ElementalType getType() {
        return type;
    }

    public SpeciesKey getKey() {
        return key;
    }

    public String getName() {
        return name;
    }
}
