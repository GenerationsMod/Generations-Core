package generations.gg.generations.core.generationscore.world.shop;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import generations.gg.generations.core.generationscore.util.Time;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class Shop {
    public static Codec<Shop> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("maxEntries").forGetter(Shop::getMaxEntries),
            Codec.STRING.fieldOf("refreshType").forGetter(Shop::getRefreshTypeName),
            Codec.INT.fieldOf("refreshTime").forGetter(Shop::getRefreshTimeAsInt),
            Codec.list(ResourceLocation.CODEC).optionalFieldOf("presets").forGetter(shop -> Optional.ofNullable(shop.getPresetKeys())),
            Codec.list(ShopEntry.CODEC).fieldOf("entries").forGetter(Shop::getEntries)
    ).apply(instance, (maxEntries, refreshType, refreshTime, presets, entries) ->
            new Shop(maxEntries, refreshType, refreshTime, presets.orElse(Collections.emptyList()), entries)));

    private int maxEntries;
    private ShopRefreshType refreshType;
    private Time refreshTime;
    private List<ResourceLocation> presetKeys;
    private List<ShopEntry> entries;

    public Shop(int maxEntries, String refreshType, int refreshTime, List<ResourceLocation> presetKeys, List<ShopEntry> entries) {
        this(maxEntries, ShopRefreshType.get(refreshType), Time.fromInt(refreshTime), presetKeys, entries);
    }

    public Shop(int maxEntries, ShopRefreshType refreshType, Time refreshTime, List<ResourceLocation> presetKeys, List<ShopEntry> entries) {
        this.maxEntries = maxEntries;
        this.refreshType = refreshType;
        this.refreshTime = refreshTime;
        this.presetKeys = presetKeys;
        this.entries = entries;
    }

    public Shop(FriendlyByteBuf buffer) {
        this(buffer.readInt(), buffer.readEnum(ShopRefreshType.class), Time.decode(buffer), buffer.readList(FriendlyByteBuf::readResourceLocation), buffer.readList(ShopEntry::decode));
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(maxEntries);
        buffer.writeEnum(refreshType);
        refreshTime.encode(buffer);
        buffer.writeCollection(getPresetKeys(), FriendlyByteBuf::writeResourceLocation);
        buffer.writeCollection(entries, (buf, shopEntry) -> shopEntry.encode(buf));
    }

    public int getMaxEntries() {
        return maxEntries;
    }

    public void setMaxEntries(int maxEntries) {
        this.maxEntries = maxEntries;
    }

    public ShopRefreshType getRefreshType() {
        return refreshType;
    }

    public String getRefreshTypeName() {
        return refreshType.name().toLowerCase(Locale.ENGLISH);
    }

    public void setRefreshType(ShopRefreshType refreshType) {
        this.refreshType = refreshType;
    }

    public Time getRefreshTime() {
        return refreshTime;
    }

    public int getRefreshTimeAsInt() {
        return refreshTime.toInt();
    }

    public void setRefreshTime(Time refreshTime) {
        this.refreshTime = refreshTime;
    }

    public List<ResourceLocation> getPresetKeys() {
        return presetKeys;
    }

    public List<ShopPreset> getPresets() {
        return presetKeys.stream().map(PokeModRegistries.Dialogue.SHOP_PRESETS::get).toList();
    }

    public void setPresetKeys(List<ResourceLocation> presetKeys) {
        this.presetKeys = presetKeys;
    }

    public List<ShopEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<ShopEntry> entries) {
        this.entries = entries;
    }
}