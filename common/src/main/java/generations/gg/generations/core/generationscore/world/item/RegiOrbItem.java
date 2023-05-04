package generations.gg.generations.core.generationscore.world.item;

import generations.gg.generations.core.generationscore.GenerationsCore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class RegiOrbItem extends Item {
    private final ResourceLocation speciesId;

    public RegiOrbItem(Properties arg, String speciesId) {
        super(arg);
        this.speciesId = GenerationsCore.id(speciesId);
    }

    public ResourceLocation getSpeciesId() {
        return speciesId;
    }
}
