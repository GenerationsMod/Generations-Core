package generations.gg.generations.core.generationscore.world.shop;

import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedRandomList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ShopGenerator {

    public static SimpleShopEntry[] refresh(Shop shop) {
        var presets = shop.getPresets();
        int maxEntries = shop.getMaxEntries();

        var generatedEntries = new SimpleShopEntry[maxEntries];
        int entryIndex = 0;

        for (var preset : presets) {
            int maxAppearingEntries = preset.maxAppearingItems() > maxEntries ? maxEntries : preset.maxAppearingItems();
            var presetEntries = generateEntries(preset.entries(), maxAppearingEntries);
            System.arraycopy(presetEntries, 0, generatedEntries, entryIndex, presetEntries.length);
            maxEntries -= maxAppearingEntries;
            entryIndex += maxAppearingEntries;
        }

        if (maxEntries > 0) {
            var entries = generateEntries(shop.getEntries(), maxEntries);
            System.arraycopy(entries, 0, generatedEntries, entryIndex, entries.length);
        }

        List<SimpleShopEntry> out = new ArrayList<>();
        for (SimpleShopEntry entry : generatedEntries) {
            if (!out.contains(entry)) {
                out.add(entry);
            }
        }

        return out.toArray(SimpleShopEntry[]::new);
    }

    private static SimpleShopEntry[] generateEntries(List<ShopEntry> entries, int maxEntries) {
        var rnd = RandomSource.create();
        var weightedEntries = WeightedRandomList.create(entries.stream()
                .map(ShopEntry::toWeightedEntry).toList());
        var out = new SimpleShopEntry[maxEntries];
        for (int i = 0; i < maxEntries; i++) {
            var entry = weightedEntries.getRandom(rnd);
            if (entry.isEmpty()) {
                throw new RuntimeException("Unable to generate entries");
            }
            var simpleEntry = entry.get().getData().toSimpleEntry();
            out[i] = simpleEntry;
        }
        return Arrays.stream(out).filter(Objects::nonNull).toArray(SimpleShopEntry[]::new);
    }
}