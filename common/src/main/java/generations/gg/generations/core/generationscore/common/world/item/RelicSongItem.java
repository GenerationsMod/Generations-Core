package generations.gg.generations.core.generationscore.common.world.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RelicSongItem extends Item implements LangTooltip {
    private final boolean notInert;

    public RelicSongItem(Item.Properties properties, boolean notInert) {
        super(properties.jukeboxPlayable(RecordSongs.INSTANCE.getMELOETTAS_RELIC_SONG()));
        this.notInert = notInert;
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return !notInert;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        LangTooltip.super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
    }
}
