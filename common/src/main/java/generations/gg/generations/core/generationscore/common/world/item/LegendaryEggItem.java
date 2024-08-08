package generations.gg.generations.core.generationscore.common.world.item;

import generations.gg.generations.core.generationscore.common.config.SpeciesKey;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.config.SpeciesKey;
import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil;
import generations.gg.generations.core.generationscore.common.world.item.legends.DistanceTraveledImplItem;
import generations.gg.generations.core.generationscore.common.config.SpeciesKey;
import generations.gg.generations.core.generationscore.common.util.GenerationsUtils;
import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil;
import generations.gg.generations.core.generationscore.common.world.item.legends.DistanceTraveledImplItem;
import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil;
import generations.gg.generations.core.generationscore.common.world.item.legends.DistanceTraveledImplItem;
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

    private final SpeciesKey speciesKey;

    public LegendaryEggItem(Properties properties, SpeciesKey speciesKey, double maxDistance) {
        super(properties, maxDistance);
        this.speciesKey = speciesKey;
    }

    @Override
    protected void onCompletion(ServerLevel level, Player player, InteractionHand usedHand) {
        PokemonUtil.spawn(GenerationsUtils.parseProperties(speciesKey.species().getPath()), level, player.position());
    }

    @Override
    public boolean checkPlayerState(Player player) {
        return !GenerationsCore.CONFIG.caught.capped(player, speciesKey);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, @NotNull TooltipFlag isAdvanced) {
        LangTooltip.super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
    }
}
