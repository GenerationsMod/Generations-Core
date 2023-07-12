package generations.gg.generations.core.generationscore.world.dialogue.nodes;

import generations.gg.generations.core.generationscore.world.dialogue.DialoguePlayer;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning.LocationLogic;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning.YawLogic;
import generations.gg.generations.core.generationscore.world.entity.block.PokemonUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

public class SpawnPokemonNode extends AbstractNode {
    private final String data;
    private final int maxTick;
    private final LocationLogic pos;
    private final YawLogic yaw;

    public SpawnPokemonNode(String data, int maxTick, LocationLogic pos, YawLogic yaw) {
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

    private LocationLogic location() {
        return pos;
    }

    private YawLogic yaw() {
        return yaw;
    }

    @Override
    public void run(ServerPlayer serverPlayer, DialoguePlayer dialoguePlayer) {
        var level = serverPlayer.level();
        PokemonUtil.spawn(data, level, pos.createSupplier(serverPlayer).get(), yaw.createSupplier(serverPlayer).getAsFloat());
    }

    @Override
    public void encode(@NotNull FriendlyByteBuf friendlyByteBuf) {
        super.encode(friendlyByteBuf);
        friendlyByteBuf.writeUtf(data);
        friendlyByteBuf.writeInt(maxTick);
//        friendlyByteBuf.writeBlockPos(pos);
//        friendlyByteBuf.writeFloat(yaw);
    }

    public static SpawnPokemonNode decode(FriendlyByteBuf buf) {
        return new SpawnPokemonNode(buf.readUtf(), buf.readInt(), null, null);
    }
}
