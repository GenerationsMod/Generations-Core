package generations.gg.generations.core.generationscore.world.item.legends;

import generations.gg.generations.core.generationscore.client.model.ModelContextProviders;
import generations.gg.generations.core.generationscore.config.Key;
import generations.gg.generations.core.generationscore.world.item.ItemWithLangTooltipImpl;
import net.minecraft.resources.ResourceLocation;

public class CreationTrioItem extends ItemWithLangTooltipImpl implements ModelContextProviders.ModelProvider {
    private final Key speciesId;
    private final ResourceLocation model;

    public CreationTrioItem(Properties properties, Key speciesId, ResourceLocation model) {
        super(properties);
        this.speciesId = speciesId;
        this.model = model;
    }

    @Override
    public ResourceLocation getModel() {
        return model;
    }

    public Key getSpeciesId() {
        return speciesId;
    }
}
