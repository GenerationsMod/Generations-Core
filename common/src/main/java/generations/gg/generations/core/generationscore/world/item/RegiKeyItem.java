package generations.gg.generations.core.generationscore.world.item;

import generations.gg.generations.core.generationscore.GenerationsCore;
import net.minecraft.resources.ResourceLocation;

public class RegiKeyItem extends ItemWithLangTooltipImpl {
    private final ResourceLocation speciesId;

    public RegiKeyItem(Properties properties, String speciesId) {
        super(properties);
        this.speciesId = GenerationsCore.id(speciesId);
    }

    public ResourceLocation getSpeciesId() {
        return speciesId;
    }
}
