package generations.gg.generations.core.generationscore.world.item;

import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemWithLangTooltip extends Item {
    public ItemWithLangTooltip(Properties properties) {
        super(properties);
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        var tooltipId = tooltipId(stack);
        if(Language.getInstance().has(this.getDescriptionId(stack))) tooltipComponents.add(Component.translatable(tooltipId));
    }

    public String tooltipId() {
        return tooltipId(null);
    }

    private String tooltipId(ItemStack stack) {
        return this.getDescriptionId(stack) + ".tooltip";
    }
}
