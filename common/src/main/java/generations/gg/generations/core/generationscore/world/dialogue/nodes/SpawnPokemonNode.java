package generations.gg.generations.core.generationscore.world.dialogue.nodes;

import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import com.google.gson.JsonObject;
import generations.gg.generations.core.generationscore.util.GenerationsUtils;
import generations.gg.generations.core.generationscore.world.dialogue.DialoguePlayer;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning.LocationLogic;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning.LocationLogicTypes;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning.YawLogic;
import generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning.YawLogicTypes;
import generations.gg.generations.core.generationscore.world.entity.block.PokemonUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

public class SpawnPokemonNode extends AbstractNode {
    private final PokemonProperties properties;
    private final int maxTick;
    private final LocationLogic pos;
    private final YawLogic yaw;

    public SpawnPokemonNode(PokemonProperties properties, int maxTick, LocationLogic pos, YawLogic yaw) {
        this.properties = properties;
        this.maxTick = maxTick;
        this.pos = pos;
        this.yaw = yaw;
    }

    private PokemonProperties properties() {
        return properties;
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
        PokemonUtil.spawn(properties, level, pos.createSupplier(serverPlayer).get(), yaw.createSupplier(serverPlayer).getAsFloat());
        dialoguePlayer.discard();
    }

    @Override
    public AbstractNodeType<?> type() {
        return AbstractNodeTypes.SPAWN_POKEMON;
    }

    @Override
    public void encode(@NotNull FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeUtf(properties.asString(" "));
        friendlyByteBuf.writeInt(maxTick);
        pos.encode(friendlyByteBuf);
        yaw.encode(friendlyByteBuf);
    }

    public static SpawnPokemonNode decode(FriendlyByteBuf buf) {
        return new SpawnPokemonNode(GenerationsUtils.parseProperties(buf.readUtf()), buf.readInt(), LocationLogicTypes.decode(buf), YawLogicTypes.decode(buf));
    }

    @Override
    public void toJson(JsonObject json) {
        json.addProperty("properties", properties.asString(" "));
        json.addProperty("maxTick", maxTick);
        json.add("pos", LocationLogicTypes.toJson(pos));
        json.add("yaw", YawLogicTypes.toJson(yaw));
    }

    public static SpawnPokemonNode fromJson(JsonObject object) {
        return new SpawnPokemonNode(
                GenerationsUtils.parseProperties(object.getAsJsonPrimitive("properties").getAsString()),
                object.getAsJsonPrimitive("maxTick").getAsInt(),
                LocationLogicTypes.fromJson(object.getAsJsonObject("pos")),
                YawLogicTypes.fromJson(object.getAsJsonObject("yaw"))
        );
    }
}
