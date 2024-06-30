package generations.gg.generations.core.generationscore.world.item;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.config.LegendKeys;
import generations.gg.generations.core.generationscore.world.entity.block.PokemonUtil;
import generations.gg.generations.core.generationscore.world.item.legends.EnchantableItem;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class MeteoriteItem extends EnchantableItem implements LangTooltip {
    public MeteoriteItem(Properties arg) {
        super(arg);
    }

    @Override
    public int neededEnchantmentLevel(Player player) {
        var caught = GenerationsCore.CONFIG.caught;
        if(!caught.capped(player, LegendKeys.DEOXYS)) return super.neededEnchantmentLevel(player);
        else return 0;
    }
    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {
        if (!level.isClientSide()) {
            ItemStack stack = player.getItemInHand(usedHand);

            if (EnchantableItem.isEnchanted(stack)) {
                PokemonUtil.spawn(LegendKeys.DEOXYS.createProperties(70), level, player.getOnPos());
                stack.shrink(1);

                return InteractionResultHolder.success(stack);
            }
        }

        return super.use(level, player, usedHand);
    }
}
