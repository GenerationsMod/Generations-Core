package com.pokemod.pokemod.world.item;

import com.pokemod.pokemod.registries.PokeModRegistries;
import com.pokemod.pokemod.world.entity.pixelmon.PixelmonEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PixelmonInteractions {
    private static final List<PixelmonInteraction> customInteractions = new ArrayList<>();
    public static void registerCustomInteraction(PixelmonInteraction customInteraction) {
        customInteractions.add(customInteraction);
    }

    public static void registerDefaultNonItemInteractions() {
        registerCustomInteraction((pixelmonEntity, player, itemInHand) -> {
            var data = pixelmonEntity.getPixelmonData();
//            var holder = PokeModRegistries.Pixelmon.SPECIES.getHolderOrThrow(data.getSpecies());

            /*if(itemInHand.getItem() == Items.SADDLE && PokeModRegistries.PIXELMON.registry().getTag(PokemonTags.SADDLEABLE).stream().anyMatch(a -> a.contains(holder))) {
                data.setSkin("saddle");

                return InteractionResult.CONSUME;
            } else {
                return InteractionResult.PASS;
            }*/
            return InteractionResult.PASS;
        });
    }

    public static InteractionResult triggerCustomInteraction(PixelmonEntity pixelmonEntity, Player player, ItemStack itemInHand) {
        return customInteractions.stream()
                .map(a -> a.interact(pixelmonEntity, player, itemInHand))
                .filter(InteractionResult::consumesAction)
                .findFirst()
                .orElse(InteractionResult.PASS);
    }

    public interface PixelmonInteraction {
        InteractionResult interact(PixelmonEntity pixelmonEntity, Player player, ItemStack itemInHand);

        default boolean isConsumed() {
            return true;
        }
    }
}
