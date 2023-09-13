package generations.gg.generations.core.generationscore.world.dialogue.nodes;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class JsonUtils {
    public static <K, V> JsonElement toJson(Map<K, V> map, Function<K, String> keyFunction, Function<V, JsonElement> valueFunction) {
        var jsonObject = new JsonObject();

        map.forEach((k,v) -> jsonObject.add(keyFunction.apply(k), valueFunction.apply(v)));
        return jsonObject;
    }

    public static <K, V> Map<K, V> fromJson(JsonObject next, Function<String, K> keyFunction, Function<JsonElement, V> value) {
        var map = new HashMap<K, V>();
        next.asMap().forEach((k,v) -> map.put(keyFunction.apply(k), value.apply(v)));
        return map;
    }

    public static <T> JsonArray toJsonList(List<T> text, BiConsumer<JsonArray, T> consumer) {
        var array = new JsonArray();
        text.forEach(a -> consumer.accept(array, a));
        return array;
    }

    public static <T> void toNullable(String field, JsonObject json, T t, Function<T, JsonObject> consumer) {
        if(t != null) json.add(field, consumer.apply(t));
    }

    public static <T> T fromNullable(String field, JsonObject json, Function<JsonObject, T> function) {
        if(json.has(field)) return function.apply(json.get(field).getAsJsonObject());
        else return null;
    }

    public static <T> List<T> fromJsonList(String field, JsonObject jsonObject, Function<JsonElement, T> function) {
        var list = new ArrayList<T>();
        jsonObject.getAsJsonArray(field).forEach(jsonElement -> list.add(function.apply(jsonElement)));
        return list;
    }
}
