package generations.gg.generations.core.generationscore.world.item.legends;

import com.cobblemon.mod.common.api.types.ElementalType;
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class ElementalPostBattleUpdateItemImpl extends ElementalPostBattleUpdateItem {
    private final List<ElementalType> types;

    public ElementalPostBattleUpdateItemImpl(Properties properties, ElementalType... types) {
        super(properties);
        this.types = List.of(types);
    }

    @Override
    public boolean checkType(PlayerBattleActor actor, ItemStack stack, ElementalType type) {
        return types.contains(type);
    }
}
