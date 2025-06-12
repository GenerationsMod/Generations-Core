package generations.gg.generations.core.generationscore.common.world.item.tools.effects;

import generations.gg.generations.core.generationscore.common.world.item.tools.ToolEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;

import java.util.Map;

public record EnchantmentToolEffect(ResourceKey<Enchantment> enchantment, int level, int durabilityCost) implements ToolEffect {
    @Override
    public boolean use(Level world, Player player, InteractionHand usedHand) {
        if (world.isClientSide()) return false;

        var holder = world.registryAccess().registry(Registries.ENCHANTMENT).get().getHolderOrThrow(enchantment);

        ItemStack itemStack = player.getItemInHand(usedHand);
        var enchantments = new ItemEnchantments.Mutable(EnchantmentHelper.getEnchantmentsForCrafting(itemStack));
        if (enchantments.getLevel(holder) >= level) return false;
        enchantments.set(holder, level);
        EnchantmentHelper.setEnchantments(itemStack, enchantments.toImmutable());
        player.getItemInHand(usedHand).hurtAndBreak(durabilityCost, (ServerLevel) world, (ServerPlayer) player, owner -> {}/*owner.broadcastBreakEvent(usedHand)*/);
        return true;
    }

    @Override
    public boolean useOn(UseOnContext context) {
        return false;
    }
}
