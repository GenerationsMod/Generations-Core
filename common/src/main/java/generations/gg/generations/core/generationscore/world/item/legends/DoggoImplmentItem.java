package generations.gg.generations.core.generationscore.world.item.legends;

import com.cobblemon.mod.common.api.types.ElementalTypes;
import com.google.common.collect.Streams;
import net.minecraft.world.item.Item;

public class DoggoImplmentItem extends PostBattleUpdatingWithItem {
    public DoggoImplmentItem(Item.Properties properties) {
        super(properties, "zamazenta", "pixelmon.rusty_shield.amountfull", (player, stack, battle) -> Streams.stream(battle.pokemon().getTypes()).anyMatch(type -> type.equals(ElementalTypes.INSTANCE.getFIGHTING())));
    }
}
