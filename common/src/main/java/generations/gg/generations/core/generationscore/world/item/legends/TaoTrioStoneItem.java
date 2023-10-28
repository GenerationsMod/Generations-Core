package generations.gg.generations.core.generationscore.world.item.legends;

import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import com.cobblemon.mod.common.api.types.ElementalTypes;
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor;
import com.cobblemon.mod.common.pokemon.Species;
import com.google.common.collect.Streams;
import generations.gg.generations.core.generationscore.config.SpeciesKey;
import generations.gg.generations.core.generationscore.util.GenerationsUtils;
import generations.gg.generations.core.generationscore.world.item.ItemWithLangTooltipImpl;
import generations.gg.generations.core.generationscore.world.item.PostBattleUpdatingItem;
import net.minecraft.world.item.ItemStack;

public class TaoTrioStoneItem extends ElementalPostBattleUpdateItemImpl {
    private final SpeciesKey species;

    public TaoTrioStoneItem(Properties arg, SpeciesKey species) {
        super(arg, ElementalTypes.INSTANCE.getDRAGON());
        this.species = species;
    }

    public SpeciesKey getSpecies() {
        return species;
    }
}
