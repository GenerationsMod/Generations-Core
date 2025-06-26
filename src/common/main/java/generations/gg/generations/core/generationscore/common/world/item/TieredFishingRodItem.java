package generations.gg.generations.core.generationscore.common.world.item;

import generations.gg.generations.core.generationscore.common.world.entity.TieredFishingHookEntity;
import generations.gg.generations.core.generationscore.common.world.item.legends.RubyRodItem;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;

public class TieredFishingRodItem extends FishingRodItem {
    private final TieredFishingHookEntity.Teir tier;

    public TieredFishingRodItem(Properties properties, TieredFishingHookEntity.Teir tier) {
        super(properties);
        this.tier = tier;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand usedHand) {
        ItemStack itemstack = player.getItemInHand(usedHand);
        if (player.fishing != null) {
            if (!level.isClientSide) {
                int i = player.fishing.retrieve(itemstack);
                itemstack.hurtAndBreak(i, (ServerLevel) level, (ServerPlayer) player, arg2 -> {} /*arg2.broadcastBreakEvent(usedHand)*/);

                //Because of guys cheesing this with mending and /repair from some plugins.
                if(this.tier == TieredFishingHookEntity.Teir.RUBY) {
                    if(RubyRodItem.isFinished(itemstack)) {
                        itemstack.setDamageValue(0);
                        itemstack.shrink(1);

                        player.sendSystemMessage(Component.translatable("item.generations_core.ruby_rod.fate_defied"));
                    }
                }
            }
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.FISHING_BOBBER_RETRIEVE, SoundSource.NEUTRAL, 1.0f, 0.4f / (level.getRandom().nextFloat() * 0.4f + 0.8f));
            player.gameEvent(GameEvent.ITEM_INTERACT_FINISH);
        } else {
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.FISHING_BOBBER_THROW, SoundSource.NEUTRAL, 0.5f, 0.4f / (level.getRandom().nextFloat() * 0.4f + 0.8f));
            if (level instanceof ServerLevel serverLevel) {
                int k = (int) EnchantmentHelper.getFishingTimeReduction(serverLevel, itemstack, player);
                int j = EnchantmentHelper.getFishingLuckBonus(serverLevel, itemstack, player);
                level.addFreshEntity(new TieredFishingHookEntity(player, level, j, k, tier));
            }
            player.awardStat(Stats.ITEM_USED.get(this));
            player.gameEvent(GameEvent.ITEM_INTERACT_START);
        }
        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }

    public TieredFishingHookEntity.Teir getTier() {
        return tier;
    }
}
