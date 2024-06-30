package generations.gg.generations.core.generationscore.world.level.block.shrines;

import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.config.LegendKeys;
import generations.gg.generations.core.generationscore.util.GenerationsUtils;
import generations.gg.generations.core.generationscore.world.dialogue.DialogueGraph;
import generations.gg.generations.core.generationscore.world.dialogue.DialogueManager;
import generations.gg.generations.core.generationscore.world.dialogue.DialoguePlayer;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.AbstractNode;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.ChooseNode;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.SpawnPokemonNode;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning.BlockLocationLogic;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning.BlockYawLogic;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning.LocationLogic;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning.YawLogic;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.level.block.GenerationsShrines;
import generations.gg.generations.core.generationscore.world.level.block.entities.shrines.AbundantShrineBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.function.Consumer;

public class AbundantShrineBlock extends ShrineBlock<AbundantShrineBlockEntity> {
    private static final LocationLogic THERIAN_LOCATION = BlockLocationLogic.of(GenerationsShrines.TAPU_SHRINE.getKey());
    private static final YawLogic THERIAN_YAW = BlockYawLogic.of(GenerationsShrines.TAPU_SHRINE.getKey());

    private static final Consumer<ServerPlayer> playerConsumer = p -> GenerationsUtils.giveItem(p, GenerationsItems.REVEAL_GLASS.get().getDefaultInstance());

    public AbundantShrineBlock(BlockBehaviour.Properties properties) {
        super(properties, GenerationsBlockEntities.ABUNDANT_SHRINE, GenerationsBlockEntityModels.ABUNDANT_SHRINE);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level world, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand handIn, @NotNull BlockHitResult hit) {
        if (!world.isClientSide() && !DialogueManager.DIALOGUE_MAP.containsKey((ServerPlayer) player) && player.getItemInHand(handIn).is(GenerationsItems.REVEAL_GLASS.get())) {
            var graph = generateGraph(player);
            if(graph == null) return InteractionResult.FAIL;

            new DialoguePlayer(graph, null, (ServerPlayer) player, false);
            player.getItemInHand(handIn).shrink(1);
            return InteractionResult.PASS;
        }

        return InteractionResult.FAIL;
    }

    public static DialogueGraph generateGraph(Player player) {
        var map = new HashMap<String, AbstractNode>();

        if(!GenerationsCore.CONFIG.caught.capped(player, LegendKeys.TORNADUS)) {
            map.put("Tornadus", generateTapu("tornadus"));
        }

        if(!GenerationsCore.CONFIG.caught.capped(player, LegendKeys.THUNDURUS)) {
            map.put("Thunderus", generateTapu("thundurus"));
        }

        if(!GenerationsCore.CONFIG.caught.capped(player, LegendKeys.LANDORUS)) {
            map.put("Landorus", generateTapu("landorus"));
        }

        if(!GenerationsCore.CONFIG.caught.capped(player, LegendKeys.ENAMORUS)) {
            map.put("Enamorus", generateTapu("enamorus"));
        }

        if(map.isEmpty()) return null;
        var node = new ChooseNode("Choose which Therian to Spawn:", map);
        node.consumer = playerConsumer;
        return new DialogueGraph(node);
    }

    private static SpawnPokemonNode generateTapu(String name) {
        return new SpawnPokemonNode(GenerationsUtils.parseProperties(name + " level=70"), 20, THERIAN_LOCATION, THERIAN_YAW);
    }
}
