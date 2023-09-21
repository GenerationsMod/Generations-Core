package generations.gg.generations.core.generationscore.world.item;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.config.LegendKeys;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RedChainItem extends EnchantableItem implements LangTooltip {
    public static final int MAX_USES = 3;

    public RedChainItem(Properties properties) {
        super(properties);
    }

    @Override
    public int neededEnchantmentLevel(Player player) {
        var caught = GenerationsCore.CONFIG.caught;
        if(caught.capped(player, LegendKeys.DIALGA) || caught.capped(player, LegendKeys.GIRATINA) || caught.capped(player, LegendKeys.PALKIA)) return super.neededEnchantmentLevel(player);
        else return 0;
    }

    public static void incrementUsage(ItemStack stack) {
        setEnchanted(stack, false);
        stack.getOrCreateTag().putInt("uses", stack.getOrCreateTag().getInt("uses") + 1);
    }

    public static int getUses(ItemStack stack) {
        return stack.getOrCreateTag().getInt("uses");
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, @NotNull TooltipFlag isAdvanced) {
        LangTooltip.super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
        if(isAdvanced.isAdvanced()) tooltipComponents.add(Component.nullToEmpty("Remaining Uses: %s".formatted(MAX_USES - getUses(stack))));
    }

    @Override
    public String tooltipId(ItemStack stack) {
        return this.getDescriptionId() + (isEnchanted(stack) ? ".enchanted" : "") + ".tooltip";
    }
}
