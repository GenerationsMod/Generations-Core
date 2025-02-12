package generations.gg.generations.core.generationscore.common.api.player;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.berry.Flavor;
import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import generations.gg.generations.core.generationscore.common.api.events.CurryEvents;
import generations.gg.generations.core.generationscore.common.util.GenerationsUtils;
import generations.gg.generations.core.generationscore.common.world.item.curry.CurryData;
import generations.gg.generations.core.generationscore.common.world.item.curry.CurryTasteRating;
import generations.gg.generations.core.generationscore.common.world.item.curry.CurryType;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.locale.Language;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.IntStream;

public class CurryDex extends PlayerDataExtension {
    public static String KEY = "curry_dex";

    private static final Comparator<CurryDexEntry> comparator = Comparator.<CurryDexEntry>comparingInt(a -> a.type.ordinal()).thenComparingInt(a -> a.flavor.ordinal());
    List<CurryDexEntry> entries;

    public CurryDex() {
        this(new ArrayList<>());
    }

    public CurryDex(List<CurryDexEntry> entries) {
        this.entries = entries;
        sort();
    }

    public static CurryDex of(ServerPlayer player) {
        return (CurryDex) Cobblemon.playerData.get(player).getExtraData().computeIfAbsent(KEY, key -> new CurryDex());
    }

    public static void add(ServerPlayer player, CurryData data) {
        CurryDex dex = of(player);

        if (data != null) {
            CurryDexEntry dexEntry = new CurryDexEntry();

            dexEntry.setInstant(Instant.now());
            dexEntry.type = data.getCurryType();
            dexEntry.flavor = data.getFlavor();
            dexEntry.rating = data.getRating();
            dexEntry.biome = player.level().getBiome(player.getOnPos()).unwrapKey().get().location();
            dexEntry.pokemonName = "n/a"; /*PixelmonParty.of(player).stream().filter(Objects::nonNull).map(PixelmonData::displayName).findFirst().orElse("n/a") TODO: Enable*/
            dexEntry.pos = player.getOnPos();

            CurryEvents.AddEntry event = new CurryEvents.AddEntry(player, data, dexEntry);

            if (!CurryEvents.ADD_ENTRY.invoker().act(event).isTrue()) dex.add(event.getEntry());
        }
    }

    public void add(CurryDexEntry entry) {
        if (entries.stream().noneMatch(a -> comparator.compare(entry, a) == 0) || entry.type == CurryType.Gigantamax && entries.stream().noneMatch(a -> a.type == CurryType.Gigantamax)) {
            entries.add(entry);
            sort();
        }
    }

    private void sort() {
        entries.sort(comparator);
    }

    private void clear() {
        entries.clear();
    }

    public CurryDexEntry getEntry(int id) {
        return entries.get(id);
    }

    public List<CurryDexEntry> getEntries() {
        return entries;
    }

    public void setToRead() {
        entries.forEach(entry -> entry.newEntry = false);
    }


    public CurryTasteRating getCurrentTaste() {
        int size = entries.size();
        if(size >= 151) return CurryTasteRating.Charizard;
        else if (size >= 100) return CurryTasteRating.Copperajah;
        else if (size >= 50) return CurryTasteRating.Milcery;
        else if (size >= 10) return CurryTasteRating.Wobbuffet;
        else return CurryTasteRating.Koffing;
    }

    public void toByteBuf(FriendlyByteBuf buf) {
        buf.writeCollection(entries, CurryDexEntry::toByteBuf);
    }

    @Environment(EnvType.CLIENT)
    public static CurryDex fromByteBuf(FriendlyByteBuf buf) {
        Player player = Minecraft.getInstance().player; //TODO: Need to figure out a better way to handle client side pixelmon player. Currently explodes with held item stuff if the uuid list is used..
        return new CurryDex(buf.readList(CurryDexEntry::fromByteBuf));
    }

    public static class CurryDexEntry {
        public long instant; //TODO: Convert back to an Instant when cobblemon converts to using solely codecs in the future.
        public String pokemonName;
        public ResourceLocation biome;
        public BlockPos pos;

        public boolean newEntry = true;

        public CurryType type;
        @Nullable public Flavor flavor;
        public CurryTasteRating rating;

        public static CurryDexEntry fromNbt(CompoundTag entry) {
            CurryDexEntry curryDexEntry = new CurryDexEntry();
            curryDexEntry.instant = entry.getLong("instant");
            curryDexEntry.pokemonName = entry.getString("pokeName");
            curryDexEntry.biome = new ResourceLocation(entry.getString("biome"));
            curryDexEntry.pos = BlockPos.of(entry.getLong("pos"));

            curryDexEntry.rating = GenerationsUtils.getByName(entry.getString("rating"), CurryTasteRating.class).orElse(CurryTasteRating.Unknown);
            curryDexEntry.type = GenerationsUtils.getByName(entry.getString("type"), CurryType.class).orElse(CurryType.None);
            curryDexEntry.flavor = GenerationsUtils.getByName(entry.getString("flavor"), Flavor.class).orElse(null);

            curryDexEntry.newEntry = entry.getBoolean("newEntry");

            return curryDexEntry;
        }

        public CompoundTag toNbt() {
            CompoundTag compound = new CompoundTag();
            compound.putLong("instant", instant);
            compound.putString("pokeName", pokemonName);
            compound.putString("biome", biome.toString());
            compound.putLong("pos", pos.asLong());

            compound.putString("rating", rating.getSerializedName());
            compound.putString("type", type.getSerializedName());
            if(flavor != null) compound.putString("flavor", flavor.name());

            compound.putBoolean("newEntry", newEntry);

            return compound;
        }


        public long getInstant() {
            return instant;
        }

        public void setInstant(Instant instant) {
            this.instant = instant.toEpochMilli();
        }

        @Override
        public String toString() {
            String name = Language.getInstance().getOrDefault("item.curry.name");
            if(type != CurryType.None) name = type.getLocalizedName() + " " + name;
            if(flavor != null && type != CurryType.Gigantamax) name = GenerationsUtils.getFlavorLocalizedName(flavor) + " " + name;
            return name;
        }

        public String getDescription() {
            return Language.getInstance().getOrDefault("gui.currydex.description."
                    + (flavor != null ? flavor.name().toLowerCase(Locale.ENGLISH) + "_" : "") +
                    (type != CurryType.None ? type.name().toLowerCase(Locale.ENGLISH) + "_" : "") +
                    "curry");
        }

        public static void toByteBuf(FriendlyByteBuf byteBuf, CurryDexEntry entry) {
            byteBuf.writeVarLong(entry.getInstant())
                    .writeUtf(entry.pokemonName)
                    .writeResourceLocation(entry.biome)
                    .writeVarLong(entry.pos.asLong())
                    .writeBoolean(entry.newEntry)
                    .writeByte(entry.type.ordinal());

                    byteBuf.writeNullable(entry.flavor, FriendlyByteBuf::writeEnum);
                    byteBuf.writeByte(entry.rating.ordinal());
        }

        public static CurryDexEntry fromByteBuf(FriendlyByteBuf byteBuf) {
            CurryDexEntry entry = new CurryDexEntry();

            entry.instant = byteBuf.readVarLong();
            entry.pokemonName = byteBuf.readUtf();
            entry.biome = byteBuf.readResourceLocation();
            entry.pos = BlockPos.of(byteBuf.readVarLong());

            entry.newEntry = byteBuf.readBoolean();

            entry.type = CurryType.getCurryTypeFromIndex(byteBuf.readByte());
            entry.flavor = byteBuf.readNullable(friendlyByteBuf -> friendlyByteBuf.readEnum(Flavor.class));
            entry.rating = CurryTasteRating.fromId(byteBuf.readByte());
            return entry;
        }

        public JsonObject toJson() {
            var json = new JsonObject();
            json.addProperty("instant", getInstant());
            json.addProperty("pokemonName", pokemonName);
            json.addProperty("biome", biome.toString());
            json.add("pos", toBlockPosFromJson(pos));
            json.addProperty("newEntry", newEntry);
            json.addProperty("type", type.getSerializedName());
            if(flavor != null) json.addProperty("flavor", flavor.name().toLowerCase());
            json.addProperty("entry", rating.getSerializedName());
            return json;
        }

        public static CurryDexEntry fromJson(JsonObject byteBuf) {
            CurryDexEntry entry = new CurryDexEntry();

            entry.instant = byteBuf.getAsJsonPrimitive("instnace").getAsLong();
            entry.pokemonName = byteBuf.getAsJsonPrimitive("pokemonName").getAsString();
            entry.biome = new ResourceLocation(byteBuf.getAsJsonPrimitive("biome").getAsString());
            entry.pos = fromJsonToBlockPos(byteBuf.getAsJsonArray("pos"));

            entry.newEntry = byteBuf.getAsJsonPrimitive("newEntry").getAsBoolean();

            entry.type = CurryType.get(byteBuf.getAsJsonPrimitive("type").getAsString());
            entry.flavor = byteBuf.has("flavor") ? Flavor.valueOf(byteBuf.getAsJsonPrimitive("flavor").getAsString().toUpperCase()) : null;
            entry.rating = CurryTasteRating.get(byteBuf.getAsJsonPrimitive("rating").getAsString());
            return entry;
        }

        private static BlockPos fromJsonToBlockPos(JsonArray element) {
            var array = element.asList().stream().map(JsonElement::getAsJsonPrimitive).mapToInt(JsonPrimitive::getAsInt).toArray();
            return new BlockPos(array[0], array[1], array[2]);
        }

        private static JsonArray toBlockPosFromJson(BlockPos pos) {
            return IntStream.of(pos.getX(), pos.getY(), pos.getZ()).mapToObj(JsonPrimitive::new).collect(JsonArryaCollector.toJsonArray());
        }
    }

    @NotNull
    @Override
    public String name() {
        return KEY;
    }

    @NotNull
    @Override
    public JsonObject serialize() {
        var json = super.serialize();
        json.add("entries", entries.stream().map(CurryDexEntry::toJson).collect(JsonArryaCollector.toJsonArray()));
        return json;
    }

    @NotNull
    @Override
    public PlayerDataExtension deserialize(@NotNull JsonObject jsonObject) {
        return new CurryDex(jsonObject.getAsJsonArray("entries").asList().stream().map(JsonElement::getAsJsonObject).map(CurryDexEntry::fromJson).toList());
    }

    public static class JsonArryaCollector<T extends JsonElement> implements Collector<T, JsonArray, JsonArray> {
        public static <T extends JsonElement> JsonArryaCollector<T> toJsonArray() {
            return new JsonArryaCollector<>();
        }

        @Override
        public Supplier<JsonArray> supplier() {
            return JsonArray::new;
        }

        @Override
        public BiConsumer<JsonArray, T> accumulator() {
            return JsonArray::add;
        }

        @Override
        public BinaryOperator<JsonArray> combiner() {
            return (a, b) -> {
                b.addAll(a);
                return a;
            };
        }

        @Override
        public Function<JsonArray, JsonArray> finisher() {
            return Function.identity();
        }

        @Override
        public Set<Collector.Characteristics> characteristics() {
            return ImmutableSet.of();
        }
    }
}
