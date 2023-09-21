package generations.gg.generations.core.generationscore.world.item;

import com.cobblemon.mod.common.battles.actor.PlayerBattleActor;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PostBattleUpdatingWithItem extends PostBattleUpdatingItemImpl implements LangTooltip {
    public PostBattleUpdatingWithItem(Item.Properties properties, String species, String lang, TriPredicate<PlayerBattleActor, ItemStack, BattleData> triPredicate) {
        super(properties, species, lang, triPredicate);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        LangTooltip.super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
    }
}
