package generations.gg.generations.core.generationscore.world.item;

import com.cobblemon.mod.common.battles.actor.PlayerBattleActor;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

import java.util.function.BiPredicate;

public class MeltanBox extends PostBattleUpdatingItemImpl {
    public MeltanBox(Properties settings, String speciesId, String lang, TriPredicate<PlayerBattleActor, ItemStack, BattleData> predicate) {
        super(settings, speciesId, lang, predicate);
    }
}
