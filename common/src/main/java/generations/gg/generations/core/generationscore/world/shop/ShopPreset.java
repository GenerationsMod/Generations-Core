package generations.gg.generations.core.generationscore.world.shop;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.FriendlyByteBuf;

import java.util.List;

public record ShopPreset(List<ShopEntry> entries, int maxAppearingItems) {
    public ShopPreset(FriendlyByteBuf buf) {
        this(buf.readList(ShopEntry::decode), buf.readInt());
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeCollection(entries, (buf1, shopEntry) -> shopEntry.encode(buf1));
        buf.writeInt(maxAppearingItems);
    }

    public static Codec<ShopPreset> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.list(ShopEntry.CODEC).fieldOf("entries").forGetter(ShopPreset::entries),
            Codec.INT.fieldOf("maxAppearingItems").forGetter(ShopPreset::maxAppearingItems)
    ).apply(instance, ShopPreset::new));
}