package generations.gg.generations.core.generationscore.common.client.render;

import generations.gg.generations.core.generationscore.common.client.render.rarecandy.CobblemonInstance;
import net.minecraft.resources.ResourceLocation;

import java.util.Set;

public interface CobblemonInstanceProvider {
    CobblemonInstance getInstance();

    default void setInstance(CobblemonInstance instance) {}

    ResourceLocation species();

    Set<String> aspects();
}