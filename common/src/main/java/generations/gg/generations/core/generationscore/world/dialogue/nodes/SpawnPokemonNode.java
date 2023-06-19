package generations.gg.generations.core.generationscore.world.dialogue.nodes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.pokemod.pokemod.command.arguments.pixelmondata.PixelmonDataParser;
import com.pokemod.pokemod.world.entity.pixelmon.PixelmonEntity;
import com.pokemod.pokemod.world.npc.dialogue.DialogueNodeType;
import com.pokemod.pokemod.world.npc.dialogue.DialogueNodeTypes;
import com.pokemod.pokemod.world.npc.dialogue.DialoguePlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

public class SpawnPokemonNode extends AbstractNode {
    public static Codec<SpawnPokemonNode> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                    PixelmonDataParser.CODEC.fieldOf("data").forGetter(SpawnPokemonNode::data),
                    Codec.INT.optionalFieldOf("maxTick", 100).forGetter(SpawnPokemonNode::maxTick),
                    BlockPos.CODEC.fieldOf("pos").forGetter(SpawnPokemonNode::location),
                    Codec.INT.fieldOf("yaw").forGetter(SpawnPokemonNode::yaw))
            .apply(instance, SpawnPokemonNode::new));

    private final PixelmonDataParser data;
    private final int maxTick;
    private final BlockPos pos;
    private final int yaw;

    public SpawnPokemonNode(PixelmonDataParser data, int maxTick, BlockPos pos, int yaw) {
        this.data = data;
        this.maxTick = maxTick;
        this.pos = pos;
        this.yaw = yaw;
    }

    private PixelmonDataParser data() {
        return data;
    }

    private int maxTick() {
        return maxTick;
    }

    private BlockPos location() {
        return pos;
    }

    private int yaw() {
        return yaw;
    }

    @Override
    public void run(ServerPlayer serverPlayer, DialoguePlayer dialoguePlayer) {
        var level = serverPlayer.getLevel();
        level.addFreshEntity(new PixelmonEntity(level, data.parse(level), pos, yaw));
    }

    @Override
    public Codec<? extends AbstractNode> getCodec() {
        return CODEC_BY_NAME;
    }

    @Override
    public DialogueNodeType<?> getType() {
        return DialogueNodeTypes.SPAWN_PIXELMON;
    }
}
