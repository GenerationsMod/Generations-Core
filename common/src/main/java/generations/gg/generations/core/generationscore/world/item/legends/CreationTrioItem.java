package generations.gg.generations.core.generationscore.world.item.legends;

import generations.gg.generations.core.generationscore.client.model.ModelContextProviders;
import generations.gg.generations.core.generationscore.config.SpeciesKey;
import generations.gg.generations.core.generationscore.world.item.ItemWithLangTooltipImpl;
import net.minecraft.resources.ResourceLocation;

public class CreationTrioItem extends ItemWithLangTooltipImpl implements ModelContextProviders.ModelProvider {
    private final SpeciesKey speciesId;
    private final ResourceLocation model;

    public CreationTrioItem(Properties properties, SpeciesKey speciesId, ResourceLocation model) {
        super(properties);
        this.speciesId = speciesId;
        this.model = model;
    }

    @Override
    public ResourceLocation getModel() {
        return model;
    }

    public SpeciesKey getSpeciesId() {
        return speciesId;
    }
}
