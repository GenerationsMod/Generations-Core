package generations.gg.generations.core.generationscore.world.item;

import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import com.cobblemon.mod.common.api.types.ElementalTypes;
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor;
import com.google.common.collect.Streams;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.config.LegendKeys;
import generations.gg.generations.core.generationscore.util.GenerationsUtils;
import generations.gg.generations.core.generationscore.world.dialogue.DialogueGraph;
import generations.gg.generations.core.generationscore.world.dialogue.DialogueManager;
import generations.gg.generations.core.generationscore.world.dialogue.DialoguePlayer;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.AbstractNode;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.ChooseNode;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.SpawnPokemonNode;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning.PlayerLocationLogic;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning.PlayerYawLogic;
import generations.gg.generations.core.generationscore.world.entity.block.PokemonUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class EnigmaStoneItem extends ItemWithLangTooltipImpl implements PostBattleUpdatingItem {
    public EnigmaStoneItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        var stack = player.getItemInHand(usedHand);

        if (!level.isClientSide() && !DialogueManager.DIALOGUE_MAP.containsKey((ServerPlayer) player)) {
            var graph = generateGraph(player);
            if(graph == null) return InteractionResultHolder.fail(stack);

            new DialoguePlayer(graph, null, (ServerPlayer) player, false);
            player.getItemInHand(usedHand).shrink(1);
            return InteractionResultHolder.success(stack);
        }

        return InteractionResultHolder.fail(stack);
    }

    public static DialogueGraph generateGraph(Player player) {
        var map = new HashMap<String, AbstractNode>();

        if(GenerationsCore.CONFIG.caught.capped(player, LegendKeys.LATIAS)) {
            map.put("Latias", generateEons("latias"));
        }

        if(GenerationsCore.CONFIG.caught.capped(player, LegendKeys.LATIOS)) {
            map.put("Latios", generateEons("latias"));
        }

        if(map.isEmpty()) return null;
        return new DialogueGraph(new ChooseNode("Choose which Tapu to Spawn:", map));
    }

    private static SpawnPokemonNode generateEons(String name) {
        return new SpawnPokemonNode(GenerationsUtils.parseProperties(name + " level=70"), 20, PlayerLocationLogic.INSTANCE, PlayerYawLogic.INSTANCE);
    }

    @Override
    public boolean checkData(PlayerBattleActor player, ItemStack stack, BattleData pixelmonData) {
        return Streams.stream(pixelmonData.pokemon().getSpecies().getTypes()).anyMatch(type -> type.equals(ElementalTypes.INSTANCE.getPSYCHIC()) || type.equals(ElementalTypes.INSTANCE.getDRAGON()));
    }
}
