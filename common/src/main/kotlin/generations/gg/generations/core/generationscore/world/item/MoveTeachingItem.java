package generations.gg.generations.core.generationscore.world.item;

import com.cobblemon.mod.common.api.moves.BenchedMove;
import com.cobblemon.mod.common.api.moves.Move;
import com.cobblemon.mod.common.api.moves.MoveTemplate;
import com.cobblemon.mod.common.api.moves.Moves;
import com.cobblemon.mod.common.api.pokemon.moves.LearnsetQuery;
import com.cobblemon.mod.common.api.types.ElementalType;
import com.cobblemon.mod.common.api.types.ElementalTypes;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.google.common.collect.Streams;
import dev.architectury.event.EventResult;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.stream.Stream;

public class MoveTeachingItem extends Item implements PixelmonInteractions.PixelmonInteraction {
    public MoveTeachingItem(Properties properties) {
        super(properties);
    }


    public EventResult interact(PokemonEntity pixelmonEntity, Player player, ItemStack itemInHand) {
        var data = pixelmonEntity.getPokemon();
        var template = getMove(itemInHand);

        if (template == null) {
            player.displayClientMessage(Component.translatable("move.doesntexist"), true);
            return EventResult.pass();
        } else if (LearnsetQuery.Companion.getTM_MOVE().canLearn(template, data.getForm().getMoves())) {
            var moveSet = data.getMoveSet();
            if (Stream.concat(Streams.stream(moveSet.iterator()).map(Move::getTemplate), Streams.stream(data.getBenchedMoves()).map(BenchedMove::getMoveTemplate)).anyMatch(it -> it.equals(template))) {
                player.displayClientMessage(Component.translatable("move.alreadyknows", data.getDisplayName(), template.getDisplayName()).withStyle(ChatFormatting.BOLD), true);
                return EventResult.pass();
            }

            data.getBenchedMoves().add(new BenchedMove(template, 0));

            player.displayClientMessage(Component.translatable("move.newmove1").withStyle(ChatFormatting.BOLD), true);
            player.displayClientMessage(Component.translatable("move.newmove3", template.getDisplayName()).withStyle(ChatFormatting.BOLD), true);

            return EventResult.interruptTrue();
        } else {
            player.sendSystemMessage(
                Component.translatable("move.cantlearn", data.getDisplayName(), template.getDisplayName()).withStyle(ChatFormatting.BOLD));
            return EventResult.pass();
        }
    }

    public static ElementalType getType(ItemStack stack) {
        var template = getMove(stack);

        if (template != null) {
            return template.getElementalType();
        } else {
            return ElementalTypes.INSTANCE.getNORMAL();
        }
    }

    public ItemStack createTm(int number, String move) {
        var stack = GenerationsItems.TM.get().getDefaultInstance();
        var tag = stack.getOrCreateTag();
        tag.putInt("number", number);
        tag.putString("move", move);
        return stack;
    }

    public MoveTemplate getMove(ItemStack itemStack) {
        var tag = itemStack.getTag();

        if (tag != null) {
            if(tag.contains("move")) {
                return Moves.INSTANCE.getByName(tag.getString("move"));
            }
        }

        return null;
    }
}


