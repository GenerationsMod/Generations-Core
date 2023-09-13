package generations.gg.generations.core.generationscore.world.dialogue.nodes.spawning;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.gson.JsonObject;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.util.GenerationsUtils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiFunction;
import java.util.function.Function;

public class YawLogicTypes {
    public static final BiMap<ResourceLocation, YawLogicType<?>> YAW_LOGICS = HashBiMap.create();

    public static YawLogicType<BlockYawLogic> BLOCK = register("block", BlockYawLogic::fromJson, BlockYawLogic::decode);
    public static YawLogicType<EntityYawLogic> ENTITY = register("entity", EntityYawLogic::fromJson, EntityYawLogic::decode);


    public static <T extends YawLogic> YawLogicType<T> register(String name, Function<JsonObject, T> codec, Function<FriendlyByteBuf, T> decoder) {
        return register(GenerationsCore.id(name), codec, decoder);
    }
    public static <T extends YawLogic> YawLogicType<T> register(ResourceLocation name, Function<JsonObject, T> codec, Function<FriendlyByteBuf, T> decoder) {
        return (YawLogicType<T>) GenerationsUtils.ensureMapReturn(YAW_LOGICS, name, new YawLogicType<T>(name, codec, decoder));
    }

    public static <T extends YawLogic> JsonObject toJson(T t) {
        var json = new JsonObject();
        json.addProperty("type", YAW_LOGICS.inverse().get(t.type()).toString());
        t.toJson(json);
        return json;
    }

    public static YawLogic fromJson(JsonObject buf) {
        return GenerationsUtils.processIfNotNull(YAW_LOGICS.get(ResourceLocation.tryParse(buf.getAsJsonPrimitive("type").getAsString())), buf, (BiFunction<YawLogicType<? extends YawLogic>, JsonObject, YawLogic>) (locationLogicType, object) -> locationLogicType.codec().apply(object));
    }

    public static <T extends YawLogic> void encode(T t, FriendlyByteBuf buf) {
        buf.writeResourceLocation(YAW_LOGICS.inverse().get(t.type()));
        t.encode(buf);
    }

    public static YawLogic decode(FriendlyByteBuf buf) {
        return GenerationsUtils.processIfNotNull(YAW_LOGICS.get(buf.readResourceLocation()), buf, (BiFunction<YawLogicType<?>, FriendlyByteBuf, YawLogic>) (locationLogicType, buf1) -> locationLogicType.decoder().apply(buf1));
    }

    public static void init() {
    }
}