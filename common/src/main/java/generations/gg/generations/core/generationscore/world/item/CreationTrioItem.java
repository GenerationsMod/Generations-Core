package generations.gg.generations.core.generationscore.world.item;

import com.pokemod.pokemod.client.model.ModelContextProviders;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class CreationTrioItem extends Item implements ModelContextProviders.ModelProvider {
    private final ResourceLocation speciesId;
    private final ResourceLocation model;

    public CreationTrioItem(Properties properties, ResourceLocation speciesId, ResourceLocation model) {
        super(properties);
        this.speciesId = speciesId;
        this.model = model;
    }

    @Override
    public ResourceLocation getModel() {
        return model;
    }

    public ResourceLocation getSpeciesId() {
        return speciesId;
    }
}
