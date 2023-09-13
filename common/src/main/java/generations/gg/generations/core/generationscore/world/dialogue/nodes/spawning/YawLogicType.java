package generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Function;

public record YawLogicType<T extends YawLogic>(ResourceLocation id, Function<JsonObject, T> codec, Function<FriendlyByteBuf, T> decoder) {
}