package generations.gg.generations.core.generationscore.world.shop;

import generations.gg.generations.core.generationscore.util.GenerationsUtils;
import generations.gg.generations.core.generationscore.util.Time;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.time.LocalTime;
import java.util.Arrays;

public class Offers {

    private final ResourceLocation key;
    private final ShopRefreshType refreshType;
    private Time lastRefreshed;
    private Time nextRefresh;
    private final Time shopRefreshTime;
    private boolean refreshed;
    private SimpleShopEntry[] entries;

    private Offers(ResourceLocation key, @Nullable Level level, @Nullable Time lastRefreshed, @Nullable SimpleShopEntry[] entries) {
        this.key = key;
        var shop = Shops.instance().get(key);
        this.refreshType = shop.getRefreshType();
        this.shopRefreshTime = shop.getRefreshTime();
        if (refreshType == ShopRefreshType.CUSTOM_INGAME || refreshType == ShopRefreshType.PER_MC_DAY) {
            if (level == null) {
                throw new RuntimeException("Level is null!");
            }
            this.lastRefreshed = lastRefreshed == null ? GenerationsUtils.getInGameDayTime(level) : lastRefreshed;
        } else {
            this.lastRefreshed = lastRefreshed == null ? Time.of(LocalTime.now()) : lastRefreshed;
        }
        this.nextRefresh = this.lastRefreshed.add(shopRefreshTime);
        this.entries = entries;
        if (entries == null) {
            refreshEntries();
        }
    }

    public static Offers of(ResourceLocation key, @Nullable Level level) {
        return new Offers(key, level, null, null);
    }

    public static Offers of(CompoundTag tag, @Nullable Level level) {
        return new Offers(new ResourceLocation(tag.getString("key")), level, Time.fromInt(tag.getInt("lastRefreshed")),
                tag.getList("entries", 10).stream()
                        .map(t -> new SimpleShopEntry((CompoundTag) t))
                        .toArray(SimpleShopEntry[]::new));
    }

    public CompoundTag serializeToNbt() {
        CompoundTag tag = new CompoundTag();

        tag.putString("key", key.toString());
        tag.putInt("lastRefreshed", lastRefreshed.toInt());
        ListTag entries = new ListTag();
        entries.addAll(Arrays.stream(this.entries)
                .map(SimpleShopEntry::serializeToNbt)
                .toList());
        tag.put("entries", entries);

        return tag;
    }

    private void refreshEntries() {
        this.refreshed = true;
        this.entries = ShopGenerator.refresh(Shops.instance().get(key));
    }

    public ResourceLocation getKey() {
        return key;
    }

    public SimpleShopEntry[] getEntries() {
        if (this.entries == null) {
            refreshEntries();
        }
        return this.entries;
    }

    public void tick(Level level) {
        if (refreshType == ShopRefreshType.NEVER) {
            return;
        }

        switch (refreshType) {
            case PER_MC_DAY -> {
                Time inGameTime = GenerationsUtils.getInGameDayTime(level);
                tryRefresh(shopRefreshTime.isBefore(inGameTime), inGameTime);
            }
            case PER_DAY -> {
                Time time = Time.now();
                tryRefresh(shopRefreshTime.isBefore(time), time);
            }
            case CUSTOM_INGAME -> {
                Time inGameTime = GenerationsUtils.getInGameDayTime(level);
                tryRefresh(nextRefresh.isBefore(inGameTime), inGameTime);
            }
            case CUSTOM_IRL -> {
                Time time = Time.now();
                tryRefresh(nextRefresh.isBefore(time), time);
            }
        }
    }

    private void tryRefresh(boolean condition, Time time) {
        if (condition) {
            if (!refreshed) {
                this.refreshEntries();
                this.lastRefreshed = time;
                this.nextRefresh = lastRefreshed.add(shopRefreshTime);
            }
        } else {
            this.refreshed = false;
        }
    }

}