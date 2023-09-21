package generations.gg.generations.core.generationscore.world.item;

import generations.gg.generations.core.generationscore.GenerationsCore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class RegiKeyItem extends Item implements LangTooltip {
    private final ResourceLocation speciesId;

    public RegiKeyItem(Properties properties, String speciesId) {
        super(properties);
        this.speciesId = GenerationsCore.id(speciesId);
    }

    public ResourceLocation getSpeciesId() {
        return speciesId;
    }
}
