package generations.gg.generations.core.generationscore.common.world.item;

import com.cobblemon.mod.common.battles.actor.PlayerBattleActor;
import com.cobblemon.mod.common.pokemon.Pokemon;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public interface PostBattleUpdatingItem {
    default void afterBattle(PlayerBattleActor player, ItemStack stack, boolean held, List<BattleData> battleParticipants) {
        if(held == isHeld()) {
            int count = Math.toIntExact(battleParticipants.stream().filter(data -> checkData(player, stack, data)).count());
            stack.setDamageValue(Math.toIntExact(Mth.clamp(stack.getDamageValue() + count, 0, stack.getMaxDamage())));
        }
    }

    default void afterBattle(PlayerBattleActor player, ItemStack stack, List<BattleData> battleParticipants) {
        afterBattle(player, stack, false, battleParticipants);
    }

    default boolean isHeld() {
        return false;
    }

    boolean checkData(PlayerBattleActor player, ItemStack stack, BattleData pixelmonData);

    record BattleData(boolean isNpc, Pokemon pokemon, String move) {}
}