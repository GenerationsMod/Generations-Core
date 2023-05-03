package com.pokemod.pokemod.world.item;

import com.pixelmongenerations.mimikyu.Battle;
import com.pokemod.pokemod.PokeMod;
import com.pokemod.pokemod.api.battle.BattleController;
import com.pokemod.pokemod.registries.types.PixelmonData;
import com.pokemod.pokemod.world.entity.pixelmon.PixelmonEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class LakeCrystalItem extends Item implements PostBattleUpdatingItem {

    private final ResourceLocation speciesId;

    public LakeCrystalItem(Item.Properties properties, String speciesId) {
        super(properties);
        this.speciesId = PokeMod.id(speciesId);
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return isEnchanted(stack);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {
        if(!level.isClientSide()) {
            ItemStack stack = player.getItemInHand(usedHand);

            if(!isEnchanted(stack) && stack.getDamageValue() >= getMaxDamage(stack)) {
                level.addFreshEntity(new PixelmonEntity(level, PixelmonData.of(speciesId), player.getOnPos()));
                stack.getOrCreateTag().putBoolean("enchanted", true);
                return InteractionResultHolder.success(stack);
            }
        }

        return super.use(level, player, usedHand);
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return stack != null && !isEnchanted(stack);
    }

    public static boolean isEnchanted(ItemStack stack) {
        return stack.getOrCreateTag().getBoolean("enchanted");
    }

    @Override
    public void onBattleFinish(ServerPlayer player, ItemStack stack, Battle<BattleController> battle) {
        var participant = battle.controller.getParticipant(player).orElseThrow();
        //TODO: Water
        //return !isEnchanted(stack) && pixelmonData.data().getFirst().getElements().contains(ElementType.PSYCHIC);
    }
}
