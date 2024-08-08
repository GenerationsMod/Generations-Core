package generations.gg.generations.core.generationscore.common.world.item.legends;

import generations.gg.generations.core.generationscore.common.world.item.LangTooltip;
import generations.gg.generations.core.generationscore.common.world.item.TechnicalMachineItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class JadeOrb extends TechnicalMachineItem implements LangTooltip {
    public JadeOrb(Item.Properties properties) {
        super("dragonascent", properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        LangTooltip.super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
    }
}
