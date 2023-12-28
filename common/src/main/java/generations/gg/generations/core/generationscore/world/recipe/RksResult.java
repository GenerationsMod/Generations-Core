package generations.gg.generations.core.generationscore.world.recipe;

import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import generations.gg.generations.core.generationscore.util.GenerationsUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public interface RksResult {
    void toJson(JsonObject object);

    String type();

    static JsonObject toJson(RksResult result) {
        var object = new JsonObject();
        object.addProperty("type", result.type());
        result.toJson(object);
        return object;
    }

    static RksResult fromJson(JsonObject object) {
        return switch (object.getAsJsonPrimitive("type").getAsString()) {
            case "item" -> ItemResult.fromJson(object);
            case "pokemon" -> PokemonResult.fromJson(object);
            default -> throw new JsonParseException("Type " + object.getAsJsonPrimitive("type").getAsString() + " isn't valid.");
        };
    }

    record ItemResult(ItemStack item) implements RksResult {
        @Override
        public void toJson(JsonObject object) {
            var json = GenerationsUtils.jsonToNbt(GenerationsUtils.toCompoundTag(item));

            json.getAsJsonObject().asMap().forEach(object::add);
        }

        public static ItemResult fromJson(JsonObject object) {
            return new ItemResult(ItemStack.of((CompoundTag) GenerationsUtils.jsonToNbt(object)));
        }

        @Override
        public String type() {
            return "item";
        }
    }
    record PokemonResult(PokemonProperties properties) implements RksResult {
        @Override
        public void toJson(JsonObject object) {
            object.add("data", properties.saveToJSON());
        }

        @Override
        public String type() {
            return "pokemon";
        }

        public static PokemonResult fromJson(JsonObject object) {
            var properties = new PokemonProperties().loadFromJSON(object.getAsJsonObject("data"));
            return new PokemonResult(properties);
        }
    }
}
