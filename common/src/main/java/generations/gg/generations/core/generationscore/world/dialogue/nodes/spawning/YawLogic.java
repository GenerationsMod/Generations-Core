package generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning;

import com.google.gson.JsonObject;
import dev.architectury.utils.value.FloatSupplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public interface YawLogic {
    FloatSupplier createSupplier(Player player);

    YawLogicType<?> type();

    void encode(@NotNull FriendlyByteBuf buffer);

    void toJson(JsonObject json);
}