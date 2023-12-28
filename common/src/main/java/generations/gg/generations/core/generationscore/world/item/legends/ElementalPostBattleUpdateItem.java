package generations.gg.generations.core.generationscore.world.item.legends;

import com.cobblemon.mod.common.api.types.ElementalType;
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor;
import com.google.common.collect.Streams;
import generations.gg.generations.core.generationscore.world.item.ItemWithLangTooltipImpl;
import generations.gg.generations.core.generationscore.world.item.LangTooltip;
import generations.gg.generations.core.generationscore.world.item.PostBattleUpdatingItem;
import net.minecraft.world.item.ItemStack;

public abstract class ElementalPostBattleUpdateItem extends ItemWithLangTooltipImpl implements PostBattleUpdatingItem, LangTooltip {

    public ElementalPostBattleUpdateItem(Properties properties) {
        super(properties);
    }

    public abstract boolean checkType(PlayerBattleActor actor, ItemStack stack, ElementalType type);

    @Override
    public boolean checkData(PlayerBattleActor player, ItemStack stack, BattleData pixelmonData) {
        return Streams.stream(pixelmonData.pokemon().getTypes()).anyMatch(elementalType -> checkType(player, stack, elementalType));
    }
}
