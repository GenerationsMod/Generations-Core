package com.pokemod.pokemod.world.item;

import com.pixelmongenerations.mimikyu.Battle;
import com.pokemod.pokemod.PokeMod;
import com.pokemod.pokemod.api.battle.BattleController;
import com.pokemod.pokemod.registries.types.PixelmonData;
import com.pokemod.pokemod.world.entity.pixelmon.PixelmonEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.TriPredicate;
import org.jetbrains.annotations.NotNull;

public class PostBattleUpdatingItemImpl extends Item implements PostBattleUpdatingItem {
    private final ResourceLocation speciesId;
    private final String lang;
    private final TriPredicate<ServerPlayer, ItemStack, Battle<BattleController>> predicate;

    public PostBattleUpdatingItemImpl(Item.Properties settings, String speciesId, String lang, TriPredicate<ServerPlayer, ItemStack, Battle<BattleController>> predicate) {
        super(settings);
        this.speciesId = PokeMod.id(speciesId);
        this.lang = lang;
        this.predicate = predicate;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, @NotNull InteractionHand usedHand) {
        var stack = player.getItemInHand(usedHand);

        if (!level.isClientSide()) {
            int damage = stack.getDamageValue();

            if (damage >= getMaxDamage(stack)) {
                stack.shrink(1);
                level.addFreshEntity(new PixelmonEntity(level, PixelmonData.of(speciesId), player.getOnPos(), (int) player.getYHeadRot()));
                postSpawn(level, player, usedHand);
            } else {
                player.displayClientMessage(Component.translatable(lang, getMaxDamage(stack) - damage), true);
            }

            return InteractionResultHolder.success(stack);
        }

        return InteractionResultHolder.pass(stack);
    }

    protected void postSpawn(Level level, Player player, InteractionHand usedHand) {
    }

    @Override
    public void onBattleFinish(ServerPlayer player, ItemStack stack, Battle<BattleController> battle) {
        if (predicate.test(player, stack, battle)) addDamage(stack, 1);
    }
}
