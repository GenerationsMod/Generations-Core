package com.pokemod.pokemod.world.item.tools.effects;

import com.pokemod.pokemod.world.item.tools.ToolEffect;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Objects;

public record BoneMealToolEffect(int durabilityCost) implements ToolEffect {
    @Override
    public boolean use(Level world, Player player, InteractionHand usedHand) {
        return false;
    }

    @Override
    public boolean useOn(UseOnContext context) {
        BlockPos blockPos = context.getClickedPos();
        BlockPos blockPosRelative = blockPos.relative(context.getClickedFace());
        if (!BoneMealItem.applyBonemeal(new ItemStack(Items.BONE_MEAL), context.getLevel(), blockPos, Objects.requireNonNull(context.getPlayer()))) {
            BlockState blockstate = context.getLevel().getBlockState(blockPos);
            if (!blockstate.isFaceSturdy(context.getLevel(), blockPos, context.getClickedFace())) return false;
            if (!BoneMealItem.growWaterPlant(context.getItemInHand().copy(), context.getLevel(), blockPosRelative, context.getClickedFace())) return false;
        }
        if (!context.getLevel().isClientSide()) {
            context.getLevel().levelEvent(1505, blockPosRelative, 0);
        }
        context.getItemInHand().hurtAndBreak(durabilityCost, context.getPlayer(), (owner) -> owner.broadcastBreakEvent(context.getHand()));
        return true;
    }
}
