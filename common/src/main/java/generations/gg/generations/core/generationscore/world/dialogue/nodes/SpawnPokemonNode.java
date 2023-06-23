package generations.gg.generations.core.generationscore.world.dialogue.nodes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import generations.gg.generations.core.generationscore.world.dialogue.DialogueNodeType;
import generations.gg.generations.core.generationscore.world.dialogue.GenerationsDialogueNodeTypes;
import generations.gg.generations.core.generationscore.world.dialogue.DialoguePlayer;
import generations.gg.generations.core.generationscore.world.entity.block.PokemonUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

public class SpawnPokemonNode extends AbstractNode {
    public static Codec<SpawnPokemonNode> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                    Codec.STRING.fieldOf("data").forGetter(SpawnPokemonNode::data),
                    Codec.INT.optionalFieldOf("maxTick", 100).forGetter(SpawnPokemonNode::maxTick),
                    BlockPos.CODEC.fieldOf("pos").forGetter(SpawnPokemonNode::location),
                    Codec.FLOAT.fieldOf("yaw").forGetter(SpawnPokemonNode::yaw))
            .apply(instance, SpawnPokemonNode::new));

    private final String data;
    private final int maxTick;
    private final BlockPos pos;
    private final float yaw;

    public SpawnPokemonNode(String data, int maxTick, BlockPos pos, float yaw) {
        this.data = data;
        this.maxTick = maxTick;
        this.pos = pos;
        this.yaw = yaw;
    }

    private String data() {
        return data;
    }

    private int maxTick() {
        return maxTick;
    }

    private BlockPos location() {
        return pos;
    }

    private float yaw() {
        return yaw;
    }

    @Override
    public void run(ServerPlayer serverPlayer, DialoguePlayer dialoguePlayer) {
        var level = serverPlayer.getLevel();
        PokemonUtil.spawn(data, level, pos, yaw);
    }

    @Override
    public Codec<? extends AbstractNode> getCodec() {
        return CODEC_BY_NAME;
    }

    @Override
    public DialogueNodeType<?> getType() {
        return GenerationsDialogueNodeTypes.SPAWN_PIXELMON.get();
    }

    @Override
    public void encode(@NotNull FriendlyByteBuf friendlyByteBuf) {
        super.encode(friendlyByteBuf);
        friendlyByteBuf.writeUtf(data);
        friendlyByteBuf.writeInt(maxTick);
        friendlyByteBuf.writeBlockPos(pos);
        friendlyByteBuf.writeFloat(yaw);
    }

    public static SpawnPokemonNode decode(FriendlyByteBuf buf) {
        return new SpawnPokemonNode(buf.readUtf(), buf.readInt(), buf.readBlockPos(), buf.readFloat());
    }
}
