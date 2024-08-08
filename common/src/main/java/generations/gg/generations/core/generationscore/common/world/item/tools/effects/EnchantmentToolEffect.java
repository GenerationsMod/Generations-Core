package generations.gg.generations.core.generationscore.common.world.item.tools.effects;

import generations.gg.generations.core.generationscore.common.world.item.tools.ToolEffect;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

import java.util.Map;

public record EnchantmentToolEffect(Enchantment enchantment, int level, int durabilityCost) implements ToolEffect {
    @Override
    public boolean use(Level world, Player player, InteractionHand usedHand) {
        ItemStack itemStack = player.getItemInHand(usedHand);
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(itemStack);
        if (enchantments.getOrDefault(enchantment, 0) >= level) return false;
        enchantments.put(enchantment, level);
        EnchantmentHelper.setEnchantments(enchantments, itemStack);
        player.getItemInHand(usedHand).hurtAndBreak(durabilityCost, player, owner -> owner.broadcastBreakEvent(usedHand));
        return true;
    }

    @Override
    public boolean useOn(UseOnContext context) {
        return false;
    }
}
