package generations.gg.generations.core.generationscore.client.render;

import generations.gg.generations.core.generationscore.client.render.rarecandy.CobblemonInstance;
import net.minecraft.resources.ResourceLocation;

public interface CobblemonInstanceProvider {
    CobblemonInstance getInstance();
    void setInstance(CobblemonInstance instance);

    ResourceLocation getSpecies();

}