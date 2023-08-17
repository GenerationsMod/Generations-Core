package generations.gg.generations.core.generationscore.util;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class Adapter<T> implements JsonDeserializer<T>, JsonSerializer<T> {
    private String variant;

    public Adapter(String variant) {
        this.variant = variant;
    }

    private Map<String, Class<? extends T>> types = new HashMap<>();

    public int getSize() {
        return types.size();
    }

    public Set<String> getIds() {
        return types.keySet();
    }

    public void registerType(String id, Class<? extends T> type) {
        this.types.put(id.toLowerCase(), type);
    }

    @Override
    public T deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        var json = jsonElement.getAsJsonObject();
        var variant = json.get(this.variant).getAsString().toLowerCase();
        var type1 = this.types.get(variant);
        if(type1 == null) throw new IllegalArgumentException("Cannot resolve type for variant $variant");
        return context.deserialize(json, type1);
    }

    public JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context) {
        var json = context.serialize(src, src.getClass()).getAsJsonObject();
        var variant = getId(src);
        if(variant == null) throw new IllegalArgumentException("Cannot resolve variant for type ${src::class.qualifiedName}");
        json.addProperty(variant, variant);
        return json;
    }

    public String getId(T src) {
        return this.types.entrySet().stream().filter(it -> it.getValue().equals(src.getClass())).map(Map.Entry::getKey).findAny().orElse(null);
    }
}