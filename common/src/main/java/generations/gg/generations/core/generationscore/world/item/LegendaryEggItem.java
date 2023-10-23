package generations.gg.generations.core.generationscore.world.item;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.config.Key;
import generations.gg.generations.core.generationscore.util.GenerationsUtils;
import generations.gg.generations.core.generationscore.world.entity.block.PokemonUtil;
import generations.gg.generations.core.generationscore.world.item.legends.DistanceTraveledImplItem;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LegendaryEggItem extends DistanceTraveledImplItem implements LangTooltip {

    private final Key key;

    public LegendaryEggItem(Properties properties, Key key, double maxDistance) {
        super(properties, maxDistance);
        this.key = key;
    }

    @Override
    protected void onCompletion(ServerLevel level, Player player, InteractionHand usedHand) {
        PokemonUtil.spawn(GenerationsUtils.parseProperties(key.species().getPath()), level, player.position());
    }

    @Override
    public boolean checkPlayerState(Player player) {
        return !GenerationsCore.CONFIG.caught.capped(player, key);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, @NotNull TooltipFlag isAdvanced) {
        LangTooltip.super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
    }
}
