package generations.gg.generations.core.generationscore.world.recipe;

import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import generations.gg.generations.core.generationscore.util.GenerationsUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
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

    record ItemResult(Item item) implements RksResult {
        @Override
        public void toJson(JsonObject object) {
            object.addProperty("data", BuiltInRegistries.ITEM.getKey(item).toString());
        }

        public static ItemResult fromJson(JsonObject object) {
            return new ItemResult(BuiltInRegistries.ITEM.get(new ResourceLocation(object.getAsJsonPrimitive("data").getAsString())));
        }

        @Override
        public String type() {
            return "item";
        }
    }
    record PokemonResult(PokemonProperties properties) implements RksResult {
        @Override
        public void toJson(JsonObject object) {
            object.addProperty("data", properties.asString(" "));
        }

        @Override
        public String type() {
            return "pokemon";
        }

        public static PokemonResult fromJson(JsonObject object) {
            var properties = GenerationsUtils.parseProperties(object.getAsJsonPrimitive("data").getAsString());
            return new PokemonResult(properties);
        }
    }
}
