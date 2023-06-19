package generations.gg.generations.core.generationscore.world.dialogue.nodes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.pokemod.pokemod.PokeMod;
import com.pokemod.pokemod.api.data.Codecs;
import com.pokemod.pokemod.command.arguments.pixelmondata.PixelmonDataParser;
import com.pokemod.pokemod.world.entity.pixelmon.StarterPickEntity;
import com.pokemod.pokemod.world.npc.dialogue.DialogueNodeType;
import com.pokemod.pokemod.world.npc.dialogue.DialogueNodeTypes;
import com.pokemod.pokemod.world.npc.dialogue.DialoguePlayer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class SwapStarterPicksNode extends AbstractNode {
    public static final Codec<SwapStarterPicksNode> CODEC = RecordCodecBuilder.create(instance -> instance.group(Codecs.listCodec(PixelmonDataParser.CODEC).fieldOf("pixelmonSet").forGetter(node -> node.pixelmonSet)).apply(instance, SwapStarterPicksNode::new));
    private final List<PixelmonDataParser> pixelmonSet;

    public SwapStarterPicksNode(List<PixelmonDataParser> pixelmons) {
        this.pixelmonSet = pixelmons;
    }

    @Override
    public void run(ServerPlayer player, DialoguePlayer dialoguePlayer) {
        var starterPickEntities = player.getLevel().getEntitiesOfClass(StarterPickEntity.class, AABB.of(new BoundingBox((int) (player.getX() + -15), (int) (player.getY() + -2), (int) (player.getZ() + -15), (int) (player.getX() + 15), (int) (player.getY() + 2), (int) (player.getZ() + 15))));
        if (pixelmonSet.size() != starterPickEntities.size()) {
            PokeMod.LOGGER.error("Pixelmon Set used for starters does not match amount of Pixelmon Starter Picks in world. Set has a size of {} but we found {} entities", pixelmonSet.size(), starterPickEntities.size());
            if (pixelmonSet.size() > starterPickEntities.size()) return;
        }

        for (int i = 0; i < pixelmonSet.size(); i++) {
            var starterPick = starterPickEntities.get(i);
            var data = pixelmonSet.get(i).parse(player.level);
            starterPick.setTalkedToProfessor(true);
            starterPick.swapPixelmon(data);
        }

        dialoguePlayer.discard();
    }

    @Override
    public Codec<? extends AbstractNode> getCodec() {
        return CODEC;
    }

    @Override
    public DialogueNodeType<?> getType() {
        return DialogueNodeTypes.SWAP_STARTER_PICKS;
    }
}
