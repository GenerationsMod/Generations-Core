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

public class LocationLogicTypes {
    public static final BiMap<ResourceLocation, LocationLogicType<? extends LocationLogic>> LOCATION_LOGICS = HashBiMap.create();

    public static LocationLogicType<BlockLocationLogic> BLOCK = register("block", BlockLocationLogic::fromJson, BlockLocationLogic::decode);
    public static LocationLogicType<EntityLocationLogic> ENTITY = register("entity", EntityLocationLogic::fromJson, EntityLocationLogic::decode);


    public static <T extends LocationLogic> LocationLogicType<T> register(String name, Function<JsonObject, T> codec, Function<FriendlyByteBuf, T> decoder) {
        return register(GenerationsCore.id(name), codec, decoder);
    }
    public static <T extends LocationLogic> LocationLogicType<T> register(ResourceLocation name, Function<JsonObject, T> codec, Function<FriendlyByteBuf, T> decoder) {
        return (LocationLogicType<T>) GenerationsUtils.ensureMapReturn(LOCATION_LOGICS, name, new LocationLogicType<T>(name, codec, decoder));
    }

    public static <T extends LocationLogic> JsonObject toJson(T t) {
        var json = new JsonObject();
        json.addProperty("type", LOCATION_LOGICS.inverse().get(t.type()).toString());
        t.toJson(json);
        return json;
    }

    public static LocationLogic fromJson(JsonObject buf) {
        return GenerationsUtils.processIfNotNull(LOCATION_LOGICS.get(ResourceLocation.tryParse(buf.getAsJsonPrimitive("type").getAsString())), buf, (BiFunction<LocationLogicType<? extends LocationLogic>, JsonObject, LocationLogic>) (locationLogicType, object) -> locationLogicType.codec().apply(object));
    }

    public static <T extends LocationLogic> void encode(T t, FriendlyByteBuf buf) {
        buf.writeResourceLocation(LOCATION_LOGICS.inverse().get(t.type()));
        t.encode(buf);
    }

    public static LocationLogic decode(FriendlyByteBuf buf) {
        return GenerationsUtils.processIfNotNull(LOCATION_LOGICS.get(buf.readResourceLocation()), buf, (BiFunction<LocationLogicType<?>, FriendlyByteBuf, LocationLogic>) (locationLogicType, buf1) -> locationLogicType.decoder().apply(buf1));
    }

    public static void init() {

    }
}