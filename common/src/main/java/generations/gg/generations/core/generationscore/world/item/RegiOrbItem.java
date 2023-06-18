package generations.gg.generations.core.generationscore.world.item;

import com.cobblemon.mod.common.Cobblemon;
import generations.gg.generations.core.generationscore.GenerationsCore;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class RegiOrbItem extends Item {
    private final String speciesId;

    public RegiOrbItem(Properties arg, String speciesId) {
        super(arg);
        this.speciesId = speciesId;
    }

    public String getSpeciesId() {
        return speciesId;
    }
}
