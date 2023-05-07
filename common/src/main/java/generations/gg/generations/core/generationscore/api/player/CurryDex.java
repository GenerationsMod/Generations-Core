package generations.gg.generations.core.generationscore.api.player;

import com.google.common.collect.ImmutableSet;
import generations.gg.generations.core.generationscore.api.data.curry.Flavor;
import generations.gg.generations.core.generationscore.api.events.CurryEvents;
import generations.gg.generations.core.generationscore.util.GenerationsUtils;
import generations.gg.generations.core.generationscore.world.item.curry.CurryData;
import generations.gg.generations.core.generationscore.world.item.curry.CurryTasteRating;
import generations.gg.generations.core.generationscore.world.item.curry.CurryType;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

import java.time.Instant;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CurryDex {
    private static final Map<Player, CurryDex> LOCAL_PARTY_CACHE = new Object2ObjectArrayMap<>();
    private static final Map<Player, CurryDex> SERVER_PARTY_CACHE = new Object2ObjectArrayMap<>();

    private static final Comparator<CurryDexEntry> comparator = Comparator.<CurryDexEntry>comparingInt(a -> a.type.ordinal()).thenComparingInt(a -> a.flavor.ordinal());
    List<CurryDexEntry> entries = new ArrayList<>();
    private Entity player;

    public CurryDex(Player player) {
        this.player = player;
        if (player != null) {
            generateStorage();
        }
     }

    public CurryDex(Player player, List<CurryDexEntry> entries) {
        this.entries = entries;
        sort();
    }

    public static void onJoin(ServerPlayer player) {
        CurryDex.of(player).sync();
    }

    public static CurryDex of(Player player) {
        if (player.isLocalPlayer()) {
            return LOCAL_PARTY_CACHE.computeIfAbsent(player, CurryDex::new);
        } else {
            return SERVER_PARTY_CACHE.computeIfAbsent(player, CurryDex::new);
        }
    }

    public boolean hasStorage() {
        return this.player != null && true/*this.player.getPersistentData().contains("partyData") TODO: implment getPersistentData or find replacement*/;
    }

    public void generateStorage() {
        if (!hasStorage()) {
            ListTag party = new ListTag();
//            this.player.getPersistentData().put("partyData", party);
        }

        cacheStorage();
    }

    private void cacheStorage() {
        ListTag list = new ListTag(); //this.player.getPersistentData().getList("curryDex", Tag.TAG_COMPOUND);
        entries = list.stream().filter(CompoundTag.class::isInstance).map(CompoundTag.class::cast).map(CurryDexEntry::fromNbt).sorted(comparator).collect(Collectors.toList());
    }

    public static void add(ServerPlayer player, CurryData data) {
        CurryDex dex = of(player);

        if (data != null) {
            CurryDexEntry dexEntry = new CurryDexEntry();

            dexEntry.setInstant(Instant.now());
            dexEntry.type = data.getCurryType();
            dexEntry.flavor = data.getFlavor();
            dexEntry.rating = data.getRating();
            dexEntry.biome = player.level.getBiome(player.getOnPos()).unwrapKey().get().location();
            dexEntry.pokemonName = "n/a"; /*PixelmonParty.of(player).stream().filter(Objects::nonNull).map(PixelmonData::displayName).findFirst().orElse("n/a") TODO: Enable*/;
            dexEntry.pos = player.getOnPos();

            CurryEvents.AddEntry event = new CurryEvents.AddEntry(player, data, dexEntry);

            if (!CurryEvents.ADD_ENTRY.invoker().act(event).isTrue()) dex.add(event.getEntry());
        }
    }

    public void add(CurryDexEntry entry) {
        if (entries.stream().noneMatch(a -> comparator.compare(entry, a) == 0) || entry.type == CurryType.Gigantamax && entries.stream().noneMatch(a -> a.type == CurryType.Gigantamax)) {
            entries.add(entry);
            sort();
//            player.getPersistentData().put("curryDex", entries.stream().map(CurryDexEntry::toNbt).collect(NbtListCollector.toNbtList()));
        }
    }

    private void sort() {
        entries.sort(comparator);
    }

    private void clear() {
        entries.clear();
    }

    public void sendToPlayer(ServerPlayer e) {
        //Pixelmon.NETWORK.sendTo(new CurryDexPacket(this), e);
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

    /**
     * Used to sync player data from the server back to the client.
     */
    public static void replace(Player player, CurryDex curryDex) {
        if (player.isLocalPlayer()) {
            LOCAL_PARTY_CACHE.remove(player);
            LOCAL_PARTY_CACHE.put(player, curryDex);
        } else {
            SERVER_PARTY_CACHE.remove(player);
            SERVER_PARTY_CACHE.put(player, curryDex);
            curryDex.sync();
        }
    }

    public void sync() {
        if (this.player instanceof ServerPlayer serverPlayer) {
//            PokeModNetworking.sendPacket(new S2CSyncCurryDexPacket(this), serverPlayer); TODO: Networking
        } else {
            throw new RuntimeException("Tried to sync a party from the client???");
        }
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
        CurryDex curryDex = new CurryDex(player, buf.readList(CurryDexEntry::fromByteBuf));
        return curryDex;
    }

    public static class CurryDexEntry {
        public Instant instant;
        public String pokemonName;
        public ResourceLocation biome;
        public BlockPos pos;

        public boolean newEntry = true;

        public CurryType type;
        public Flavor flavor;
        public CurryTasteRating rating;

        public static CurryDexEntry fromNbt(CompoundTag entry) {
            CurryDexEntry curryDexEntry = new CurryDexEntry();
            curryDexEntry.instant = Instant.ofEpochMilli(entry.getLong("instant"));
            curryDexEntry.pokemonName = entry.getString("pokeName");
            curryDexEntry.biome = new ResourceLocation(entry.getString("biome"));
            curryDexEntry.pos = BlockPos.of(entry.getLong("pos"));

            curryDexEntry.rating = GenerationsUtils.getByName(entry.getString("rating"), CurryTasteRating.class).orElse(CurryTasteRating.Unknown);
            curryDexEntry.type = GenerationsUtils.getByName(entry.getString("type"), CurryType.class).orElse(CurryType.None);
            curryDexEntry.flavor = GenerationsUtils.getByName(entry.getString("flavor"), Flavor.class).orElse(Flavor.NONE);

            curryDexEntry.newEntry = entry.getBoolean("newEntry");

            return curryDexEntry;
        }

        public CompoundTag toNbt() {
            CompoundTag compound = new CompoundTag();
            compound.putLong("instant", instant.toEpochMilli());
            compound.putString("pokeName", pokemonName);
            compound.putString("biome", biome.toString());
            compound.putLong("pos", pos.asLong());

            compound.putString("rating", rating.getSerializedName());
            compound.putString("type", type.getSerializedName());
            compound.putString("flavor", flavor.getSerializedName());

            compound.putBoolean("newEntry", newEntry);

            return compound;
        }

        public Instant getInstant() {
            return instant;
        }

        public void setInstant(Instant instant) {
            this.instant = instant;
        }

        @Override
        public String toString() {
            String name = I18n.get("item.curry.name");
            if(type != CurryType.None) name = type.getLocalizedName() + " " + name;
            if(flavor != Flavor.NONE && type != CurryType.Gigantamax) name = flavor.getLocalizedName() + " " + name;
            return name;
        }

        public String getDescription() {
            return I18n.get("gui.currydex.description."
                    + (flavor != Flavor.NONE ? flavor.name().toLowerCase(Locale.ENGLISH) + "_" : "") +
                    (type != CurryType.None ? type.name().toLowerCase(Locale.ENGLISH) + "_" : "") +
                    "curry");
        }

        public static void toByteBuf(FriendlyByteBuf byteBuf, CurryDexEntry entry) {
            byteBuf.writeVarLong(entry.getInstant().toEpochMilli())
                    .writeUtf(entry.pokemonName)
                    .writeResourceLocation(entry.biome)
                    .writeVarLong(entry.pos.asLong())
                    .writeBoolean(entry.newEntry)
                    .writeByte(entry.type.ordinal())
                    .writeByte(entry.flavor.ordinal())
                    .writeByte(entry.rating.ordinal());
        }

        public static CurryDexEntry fromByteBuf(FriendlyByteBuf byteBuf) {
            CurryDexEntry entry = new CurryDexEntry();

            entry.instant = Instant.ofEpochMilli(byteBuf.readVarInt());
            entry.pokemonName = byteBuf.readUtf();
            entry.biome = byteBuf.readResourceLocation();
            entry.pos = BlockPos.of(byteBuf.readVarLong());

            entry.newEntry = byteBuf.readBoolean();

            entry.type = CurryType.getCurryTypeFromIndex(byteBuf.readByte());
            entry.flavor = Flavor.getFlavorFromIndex(byteBuf.readByte());
            entry.rating = CurryTasteRating.fromId(byteBuf.readByte());
            return entry;
        }
    }

    public static class NbtListCollector<T extends Tag> implements Collector<T, ListTag, ListTag> {
        public static <T extends Tag> NbtListCollector<T> toNbtList() {
            return new NbtListCollector<>();
        }

        @Override
        public Supplier<ListTag> supplier() {
            return ListTag::new;
        }

        @Override
        public BiConsumer<ListTag, T> accumulator() {
            return ListTag::add;
        }

        @Override
        public BinaryOperator<ListTag> combiner() {
            return (a, b) -> {
                b.addAll(a);
                return a;
            };
        }

        @Override
        public Function<ListTag, ListTag> finisher() {
            return Function.identity();
        }

        @Override
        public Set<Collector.Characteristics> characteristics() {
            return ImmutableSet.of();
        }
    }
}
