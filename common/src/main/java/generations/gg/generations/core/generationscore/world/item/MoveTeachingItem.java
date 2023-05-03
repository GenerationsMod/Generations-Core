package com.pokemod.pokemod.world.item;

import com.pixelmongenerations.mimikyu.move.MoveInstance;
import com.pokemod.pokemod.registries.PokeModRegistries;
import com.pokemod.pokemod.world.entity.pixelmon.PixelmonEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class MoveTeachingItem extends Item implements PixelmonInteractions.PixelmonInteraction {
    private final ResourceLocation move;

    public MoveTeachingItem(ResourceLocation move, Item.Properties properties) {
        super(properties);
        this.move = move;
    }

    @Override
    public InteractionResult interact(PixelmonEntity pixelmonEntity, Player player, ItemStack itemInHand) {
        var data = pixelmonEntity.getPixelmonData();
        var moveName = Language.getInstance().getOrDefault(move.toLanguageKey("move"));

        if (!PokeModRegistries.Battles.MOVES.registry().containsKey(move)) {
            player.displayClientMessage(Component.translatable("move.doesntexist", moveName, data.getName()), true);
            return InteractionResult.PASS;
        }

        if (data.getPokemonForm().moves().machineMoves().contains(move)) {
            var moveSet = data.moves;

            if (moveSet.contains(move)) {
                player.displayClientMessage(Component.translatable("move.alreadyknows", data.getName(), moveName).withStyle(ChatFormatting.BOLD), true);
                return InteractionResult.PASS;
            }

            var previousMoveData = moveSet.get(0);

            if (previousMoveData != null) {
                var previousMove = Language.getInstance().getOrDefault(PokeModRegistries.Battles.MOVES.getId(previousMoveData.data).toLanguageKey("move"));
                moveSet.set(0, new MoveInstance(PokeModRegistries.Battles.MOVES.registry().get(move)));
                player.displayClientMessage(Component.translatable("move.newmove1").withStyle(ChatFormatting.BOLD), true);
                player.displayClientMessage(Component.translatable("move.newmove2", data.getName(), previousMove).withStyle(ChatFormatting.BOLD), true);
                player.displayClientMessage(Component.translatable("move.newmove3", moveName).withStyle(ChatFormatting.BOLD), true);
            } else {
                moveSet.set(0, new MoveInstance(PokeModRegistries.Battles.MOVES.registry().get(move)));
                player.displayClientMessage(Component.translatable("move.learned", data.getName(), moveName).withStyle(ChatFormatting.BOLD), true);
            }

            return InteractionResult.SUCCESS;
        }

        player.sendSystemMessage(Component.translatable("move.cantlearn", data.getName(), Language.getInstance().getOrDefault(move.toLanguageKey("move"))).withStyle(ChatFormatting.BOLD));

        return InteractionResult.PASS;
    }
}
