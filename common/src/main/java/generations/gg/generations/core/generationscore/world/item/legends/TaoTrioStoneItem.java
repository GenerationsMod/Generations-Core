package generations.gg.generations.core.generationscore.world.item.legends;

import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import com.cobblemon.mod.common.api.types.ElementalTypes;
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor;
import com.google.common.collect.Streams;
import generations.gg.generations.core.generationscore.util.GenerationsUtils;
import generations.gg.generations.core.generationscore.world.item.ItemWithLangTooltipImpl;
import generations.gg.generations.core.generationscore.world.item.PostBattleUpdatingItem;
import net.minecraft.world.item.ItemStack;

public class TaoTrioStoneItem extends ItemWithLangTooltipImpl implements PostBattleUpdatingItem {
    private final PokemonProperties species;

    public TaoTrioStoneItem(Properties arg, String species) {
        super(arg);
        this.species = GenerationsUtils.parseProperties(species);
    }

    public PokemonProperties getSpecies() {
        return species;
    }

    @Override
    public boolean checkData(PlayerBattleActor player, ItemStack stack, BattleData pixelmonData) {
        return Streams.stream(pixelmonData.pokemon().getTypes()).anyMatch(ElementalTypes.INSTANCE.getDRAGON()::equals);
    }
}
