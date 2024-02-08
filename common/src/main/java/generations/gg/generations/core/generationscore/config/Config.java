package generations.gg.generations.core.generationscore.config;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Multiset;
import com.google.gson.*;
import net.minecraft.world.entity.player.Player;

import java.lang.reflect.Type;
import java.time.Duration;
import java.util.Set;
import java.util.stream.Collectors;

public class Config {
    public VanillaTabsToAdd addItemsToVanillaTabs = new VanillaTabsToAdd();
    public Duration lootTime = Duration.ofHours(1);

    public Caught caught = new Caught();
    public EnigmaFragment enigmaFragment = new EnigmaFragment();

    public Blocks blocks = new Blocks();
    public Special special = new Special();

    public Config() {}

    public static class Caught {
        public Set<String> trackedAspects = Set.of("alola", "galarian");
        public Multiset<SpeciesKey> limits = ImmutableMultiset.<SpeciesKey>builder()
//                .setCount(ARTICUNO, 2)
//                .setCount(GALARIAN_ARTICUNO, 2)
//                .setCount(ZAPDOS, 2)
//                .setCount(GALARIAN_ZAPDOS, 2)
//                .setCount(MOLTRES, 2)
//                .setCount(GALARIAN_MOLTRES, 2)
//                .setCount(MEWTWO, 2)
//                .setCount(SUICUNE, 2)
//                .setCount(ENTEI, 2)
//                .setCount(RAIKOU, 2)
//                .setCount(LUGIA, 2)
//                .setCount(HO_OH, 2)
//                .setCount(CELEBI, 2)
//                .setCount(REGIROCK, 2)
//                .setCount(REGICE, 2)
//                .setCount(REGIROCK, 2)
//                .setCount(REGISTEEL, 2)
//                .setCount(REGIELEKI, 2)
//                .setCount(REGIDRAGO, 2)
//                .setCount(LATIAS, 2)
//                .setCount(LATIOS, 2)
//                .setCount(KYORGRE, 2)
//                .setCount(GROUDON, 2)
//                .setCount(RAYQUAZA, 2)
//                .setCount(DEOXYS, 8)
//                .setCount(UXIE, 2)
//                .setCount(AZELF, 2)
//                .setCount(MESPRIT, 2)
//                .setCount(DIALGA, 2)
//                .setCount(PALKIA, 2)
//                .setCount(GIRATINA, 2)
//                .setCount(HEATRAN, 2)
//                .setCount(REGIGIGAS, 2)
//                .setCount(CRESSELIA, 1)
//                .setCount(DARKRAI, 1)
//                .setCount(MANAPHY, 1)
//                .setCount(MANAPHY, 1)
//                .setCount(TORNADUS, 1)
//                .setCount(LATIAS, 1)
//                .setCount(LATIAS, 1)
                .build();

        public boolean capped(Player player, SpeciesKey speciesKey) {
            if(!limits.contains(speciesKey)) return true;

            var limit = limits.count(speciesKey);

            var count = generations.gg.generations.core.generationscore.api.player.Caught.get(player).get(speciesKey);

            return limit > 0 && limit >= count;
        }

        public static class Adapter implements JsonDeserializer<Caught>, JsonSerializer<Caught> {
            @Override
            public Caught deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                var trackedAspects = jsonElement.getAsJsonObject().getAsJsonArray("trackedAspects").asList().stream().map(JsonElement::getAsString).collect(Collectors.toSet());
                var multiset = HashMultiset.<SpeciesKey>create();
                jsonElement.getAsJsonObject().getAsJsonObject("limits").entrySet().forEach(entry -> multiset.setCount(SpeciesKey.fromString(entry.getKey()), entry.getValue().getAsInt()));

                var caughted = new Caught();
                caughted.trackedAspects = trackedAspects;
                caughted.limits = multiset;

                return caughted;
            }

            @Override
            public JsonElement serialize(Caught caught, Type type, JsonSerializationContext jsonSerializationContext) {
                var json = new JsonObject();
                var trackedAspects = new JsonArray();
                caught.trackedAspects.forEach(trackedAspects::add);
                json.add("trackedAspects", trackedAspects);
                var limits = new JsonObject();
                caught.limits.elementSet().forEach(key -> limits.addProperty(key.toString(), caught.limits.count(key)));
                json.add("limits", limits);
                return json;
            }
        }
    }

    public static class VanillaTabsToAdd {
        public boolean coloredBlocks = true;
        public boolean combat = true;
        public boolean toolsAndUtilities = true;
        public boolean functionalBlocks = true;
        public boolean naturalBlocks = true;
        public boolean buildingBlocks = true;
    }

    public static class EnigmaFragment {
        public boolean enabled = true;
        public int limit = 32;
    }

    public static class Blocks {
        public int elevatorRange = 15;
    }

    public static class Special {
        public boolean darkCrystalShadowPokemon = true;
    }
}
