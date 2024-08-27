package generations.gg.generations.core.generationscore.common.world.item;

import com.cobblemon.mod.common.api.types.ElementalTypes;
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor;
import com.google.common.collect.Streams;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class EnigmaStoneItem extends ItemWithLangTooltipImpl implements PostBattleUpdatingItem {
    public EnigmaStoneItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public boolean checkData(PlayerBattleActor player, ItemStack stack, BattleData pixelmonData) {
        return Streams.stream(pixelmonData.pokemon().getSpecies().getTypes()).anyMatch(type -> type.equals(ElementalTypes.INSTANCE.getPSYCHIC()) || type.equals(ElementalTypes.INSTANCE.getDRAGON()));
    }
}
