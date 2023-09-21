package generations.gg.generations.core.generationscore.world.item;

import generations.gg.generations.core.generationscore.client.model.ModelContextProviders;
import net.minecraft.resources.ResourceLocation;

public class CreationTrioItem extends ItemWithLangTooltipImpl implements ModelContextProviders.ModelProvider {
    private final String speciesId;
    private final ResourceLocation model;

    public CreationTrioItem(Properties properties, String speciesId, ResourceLocation model) {
        super(properties);
        this.speciesId = speciesId;
        this.model = model;
    }

    @Override
    public ResourceLocation getModel() {
        return model;
    }

    public String getSpeciesId() {
        return speciesId;
    }
}
