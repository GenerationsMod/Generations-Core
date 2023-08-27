package generations.gg.generations.core.generationscore.world.level.block.shrines;

import generations.gg.generations.core.generationscore.world.dialogue.BuiltinDialogues;
import generations.gg.generations.core.generationscore.world.dialogue.DialogueManager;
import generations.gg.generations.core.generationscore.world.dialogue.DialoguePlayer;
import generations.gg.generations.core.generationscore.world.dialogue.Dialogues;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntityModels;
import generations.gg.generations.core.generationscore.world.level.block.entities.generic.GenericShrineBlockEntity;
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

@SuppressWarnings("deprecation")
public class TapuShrineBlock extends ShrineBlock<GenericShrineBlockEntity> {

    public TapuShrineBlock(BlockBehaviour.Properties properties) {
        super(properties, GenerationsBlockEntities.GENERIC_SHRINE, GenerationsBlockEntityModels.TAPU_SHRINE);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level world, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand handIn, @NotNull BlockHitResult hit) {
        if (!world.isClientSide() && !DialogueManager.DIALOGUE_MAP.containsKey((ServerPlayer) player) && player.getItemInHand(handIn).is(GenerationsItems.SPARKLING_STONE.get())) {
            var graph = Dialogues.instance().get(BuiltinDialogues.TAPU_SPAWN);

            new DialoguePlayer(graph, null, (ServerPlayer) player, false);
            player.getItemInHand(handIn).shrink(1);
            return InteractionResult.PASS;
        }

        return InteractionResult.FAIL;
    }
}
