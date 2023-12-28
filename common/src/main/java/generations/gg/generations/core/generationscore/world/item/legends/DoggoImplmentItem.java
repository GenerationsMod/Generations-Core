package generations.gg.generations.core.generationscore.world.item.legends;

import com.cobblemon.mod.common.api.types.ElementalTypes;
import generations.gg.generations.core.generationscore.config.SpeciesKey;
import net.minecraft.world.item.Item;

public class DoggoImplmentItem extends ElementalPostBattleUpdateItemImplImpl {
    public DoggoImplmentItem(Item.Properties properties, SpeciesKey key) {
        super(properties, "pixelmon.rusty_shield.amountfull", key, ElementalTypes.INSTANCE.getFIGHTING());
    }
}
