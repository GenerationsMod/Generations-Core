package generations.gg.generations.core.generationscore.world.item;

import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface LangTooltip {

    default void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        var tooltipId = tooltipId(stack);
        if(Language.getInstance().has(self().getDescriptionId(stack))) tooltipComponents.add(Component.translatable(tooltipId));
    }

    default String tooltipId() {
        return tooltipId(null);
    }

    private String tooltipId(ItemStack stack) {
        return self().getDescriptionId(stack) + ".tooltip";
    }

    private Item self() {
        return (Item) this;
    }
}
