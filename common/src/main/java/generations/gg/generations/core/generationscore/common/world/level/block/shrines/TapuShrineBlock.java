package generations.gg.generations.core.generationscore.common.world.level.block.shrines;

import generations.gg.generations.core.generationscore.common.GenerationsCore;
import generations.gg.generations.core.generationscore.common.config.LegendKeys;
import generations.gg.generations.core.generationscore.common.util.GenerationsUtils;
import generations.gg.generations.core.generationscore.common.world.dialogue.DialogueGraph;
import generations.gg.generations.core.generationscore.common.world.dialogue.DialogueManager;
import generations.gg.generations.core.generationscore.common.world.dialogue.DialoguePlayer;
import generations.gg.generations.core.generationscore.common.world.dialogue.nodes.AbstractNode;
import generations.gg.generations.core.generationscore.common.world.dialogue.nodes.ChooseNode;
import generations.gg.generations.core.generationscore.common.world.dialogue.nodes.SpawnPokemonNode;
import generations.gg.generations.core.generationscore.common.world.dialogue.nodes.spawning.BlockLocationLogic;
import generations.gg.generations.core.generationscore.common.world.dialogue.nodes.spawning.BlockYawLogic;
import generations.gg.generations.core.generationscore.common.world.dialogue.nodes.spawning.LocationLogic;
import generations.gg.generations.core.generationscore.common.world.dialogue.nodes.spawning.YawLogic;
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsShrines;
import generations.gg.generations.core.generationscore.common.world.level.block.GenerationsVoxelShapes;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.generic.GenericShrineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import static net.minecraft.world.phys.shapes.BooleanOp.OR;

@SuppressWarnings("deprecation")
public class TapuShrineBlock extends ShrineBlock<GenericShrineBlockEntity> {
    private static final GenerationsVoxelShapes.DirectionalShapes SHAPE = GenerationsVoxelShapes.generateDirectionVoxelShape(
            Shapes.join(Shapes.box(0.1875, 0, 0.1875, 0.8125, 0.109375, 0.8125),
                Shapes.box(0.21875, 0, 0.21875, 0.78125, 1, 0.78125), OR));

    private static final LocationLogic TAPU_LOCATION = BlockLocationLogic.of(GenerationsShrines.TAPU_SHRINE.getKey());
    private static final YawLogic TAPU_YAW = BlockYawLogic.of(GenerationsShrines.TAPU_SHRINE.getKey());

    public TapuShrineBlock(BlockBehaviour.Properties properties) {
        super(properties, GenerationsBlockEntities.GENERIC_SHRINE, GenerationsBlockEntityModels.TAPU_SHRINE);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level world, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand handIn, @NotNull BlockHitResult hit) {
        if (!world.isClientSide() && !DialogueManager.DIALOGUE_MAP.containsKey((ServerPlayer) player) && player.getItemInHand(handIn).is(GenerationsItems.SPARKLING_STONE.get())) {
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

        if(!GenerationsCore.CONFIG.caught.capped(player, LegendKeys.TAPU_BULU)) {
            map.put("Tapu Bulu", generateTapu("tapubulu"));
        }

        if(!GenerationsCore.CONFIG.caught.capped(player, LegendKeys.TAPU_FINI)) {
            map.put("Tapu Fini", generateTapu("tapufini"));
        }

        if(!GenerationsCore.CONFIG.caught.capped(player, LegendKeys.TAPU_LELE)) {
            map.put("Tapu Lele", generateTapu("tapulele"));
        }

        if(!GenerationsCore.CONFIG.caught.capped(player, LegendKeys.TAPU_KOKO)) {
            map.put("Tapu Koko", generateTapu("tapukoko"));
        }

        if(map.isEmpty()) return null;
        return new DialogueGraph(new ChooseNode("Choose which Tapu to Spawn:", map));
    }

    private static SpawnPokemonNode generateTapu(String name) {
        return new SpawnPokemonNode(GenerationsUtils.parseProperties(name + " level=70"), 20, TAPU_LOCATION, TAPU_YAW);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE.getShape(state);
    }
}
