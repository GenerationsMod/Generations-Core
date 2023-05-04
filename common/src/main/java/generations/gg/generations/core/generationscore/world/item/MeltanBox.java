package generations.gg.generations.core.generationscore.world.item;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

import java.util.function.BiPredicate;

public class MeltanBox extends PostBattleUpdatingItemImpl {

    public MeltanBox(Properties settings, String speciesId, String lang, BiPredicate<ServerPlayer, ItemStack/*, Battle<BattleController>*/> predicate) {
        super(settings, speciesId, lang, predicate);
    }
}
