package generations.gg.generations.core.generationscore.world.item.legends;

import com.cobblemon.mod.common.battles.actor.PlayerBattleActor;
import generations.gg.generations.core.generationscore.config.SpeciesKey;
import generations.gg.generations.core.generationscore.world.item.LangTooltip;
import generations.gg.generations.core.generationscore.world.item.PostBattleUpdatingItem;
import generations.gg.generations.core.generationscore.world.item.TriPredicate;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PostBattleUpdatingWithItem extends PostBattleUpdatingItemImpl implements LangTooltip {
    public PostBattleUpdatingWithItem(Item.Properties properties, SpeciesKey species, String lang, TriPredicate<PlayerBattleActor, ItemStack, PostBattleUpdatingItem.BattleData> triPredicate) {
        super(properties, species, lang, triPredicate);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        LangTooltip.super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
    }
}
