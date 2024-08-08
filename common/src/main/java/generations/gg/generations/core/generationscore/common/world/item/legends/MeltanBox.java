package generations.gg.generations.core.generationscore.common.world.item.legends;

import com.cobblemon.mod.common.api.storage.party.PlayerPartyStore;
import com.cobblemon.mod.common.api.types.ElementalTypes;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.cobblemon.mod.common.pokemon.Species;
import com.google.common.collect.Streams;
import generations.gg.generations.core.generationscore.common.config.LegendKeys;
import generations.gg.generations.core.generationscore.common.config.LegendKeys;
import generations.gg.generations.core.generationscore.common.config.LegendKeys;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import static generations.gg.generations.core.generationscore.common.world.item.GenerationsItems.MELTAN_BOX_CHARGED;

public class MeltanBox extends PostBattleUpdatingWithItem {
    public MeltanBox(Properties settings) {
        super(settings, LegendKeys.MELMETAL, "pixelmon.meltanbox.amountfull", (player, stack, battle) -> Streams.stream(new PlayerPartyStore(player.getUuid(), player.getUuid()).iterator()).map(Pokemon::getSpecies).map(Species::getResourceIdentifier).map(ResourceLocation::toString).anyMatch(a -> a.equals("cobblemon:meltan")) && Streams.stream(battle.pokemon().getTypes()).anyMatch(type -> type.equals(ElementalTypes.INSTANCE.getSTEEL())));
    }

    @Override
    protected void postSpawn(Level level, Player player, InteractionHand usedHand) {
        player.setItemInHand(usedHand, new ItemStack(MELTAN_BOX_CHARGED.get()));
    }
}
