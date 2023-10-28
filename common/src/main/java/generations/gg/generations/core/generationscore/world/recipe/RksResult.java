package generations.gg.generations.core.generationscore.world.recipe;

import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.util.GenerationsUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Map;
import java.util.function.Consumer;

public interface RksResult {
    void toJson(JsonObject object);

    String type();

    public static JsonObject toJson(RksResult result) {
        var object = new JsonObject();
        object.addProperty("type", result.type());
        result.toJson(object);
        return object;
    }

    public static RksResult fromJson(JsonObject object) {
        return switch (object.getAsJsonPrimitive("type").getAsString()) {
            case "item" -> ItemResult.fromJson(object);
            case "pokemon" -> PokemonResult.fromJson(object);
            default -> throw new JsonParseException("Type " + object.getAsJsonPrimitive("type").getAsString() + " isn't valid.");
        };
    }

    public record ItemResult(ItemStack item) implements RksResult {
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
    public record PokemonResult(PokemonProperties properties) implements RksResult {
        @Override
        public void toJson(JsonObject object) {
            object.addProperty("data", properties.asString(" "));
        }

        @Override
        public String type() {
            return "pokemon";
        }

        public static PokemonResult fromJson(JsonObject object) {
            return new PokemonResult(GenerationsUtils.parseProperties(object.getAsJsonPrimitive("data").getAsString()));
        }
    }
}
