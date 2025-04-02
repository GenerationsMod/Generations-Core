package generations.gg.generations.core.generationscore.common.world.item;

import com.cobblemon.mod.common.api.pokemon.PokemonSpecies;
import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.config.SpeciesKey;
import generations.gg.generations.core.generationscore.common.util.GenerationsUtils;
import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil;
import generations.gg.generations.core.generationscore.common.world.item.legends.DistanceTraveledImplItem;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
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

    public LegendaryEggItem(Properties properties, SpeciesKey speciesKey) {
        super(properties, 10_000);
        this.speciesKey = speciesKey;
    }

//    @Override
//    public double getMaxDistance() {
//        var species = PokemonSpecies.INSTANCE.getByIdentifier(speciesKey.species());
//        return species.getEggCycles() * GenerationsCore.CONFIG.breeding.blocksPerEggCcyle;
//    }

    @Override
    protected void onCompletion(ServerLevel level, Player player, InteractionHand usedHand) {
        PokemonUtil.spawn(GenerationsUtils.parseProperties(speciesKey.species().getPath()), level, player.position());
    }

    @Override
    public boolean checkPlayerState(ServerPlayer player) {
        return GenerationsCore.CONFIG.caught.capped(player, speciesKey);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, @NotNull TooltipFlag isAdvanced) {
        var distance = remainingNeededDistance(stack);

        if(distance > 0) tooltipComponents.add(Component.nullToEmpty("Distance needed to hatch: %.2f".formatted(distance)));
        else {
            var species = PokemonSpecies.INSTANCE.getByIdentifier(speciesKey.species());
            tooltipComponents.add(Component.nullToEmpty("Ready to Hatch. Right Click to hatch " + (species != null ? species.getName() : "Redacted") + "!"));
        }
    }
}
