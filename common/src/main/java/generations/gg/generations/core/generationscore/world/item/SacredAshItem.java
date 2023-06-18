package generations.gg.generations.core.generationscore.world.item;

import com.cobblemon.mod.common.battles.actor.PlayerBattleActor;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SacredAshItem extends Item implements PostBattleUpdatingItem {

    public SacredAshItem(Properties properties) {
        super(properties);
    }

    public static boolean isFullyCharged(ItemStack stack) {
        return stack.getDamageValue() >= stack.getMaxDamage();
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return isFullyCharged(stack);
    }

    @Override
    public boolean isHeld() {
        return true;
    }

    @Override
    public boolean checkData(PlayerBattleActor player, ItemStack stack, BattleData pixelmonData) {
         return pixelmonData.move().equals(("sacred_fire")); //TODO: This will not work as is due to lack of proper event. Need to implment on cobblemon side or rethink.
    }
}
