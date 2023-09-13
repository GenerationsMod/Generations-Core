package generations.gg.generations.core.generationscore.world.shop;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.List;

public record ShopPreset(List<ShopEntry> entries, int maxAppearingItems) {
    public static Codec<ShopPreset> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.list(ShopEntry.CODEC).fieldOf("entries").forGetter(ShopPreset::entries),
            Codec.INT.fieldOf("maxAppearingItems").forGetter(ShopPreset::maxAppearingItems)
    ).apply(instance, ShopPreset::new));
}