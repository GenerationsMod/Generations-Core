package generations.gg.generations.core.generationscore.world.shop;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class ShopEntry {
    public static Codec<ShopEntry> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ItemStack.CODEC.fieldOf("item").forGetter(ShopEntry::getItem),
            Codec.INT.fieldOf("amount").forGetter(ShopEntry::getAmount),
            Codec.STRING.fieldOf("description").forGetter(ShopEntry::getDescription),
            Codec.INT.fieldOf("buyPrice").forGetter(ShopEntry::getBuyPrice),
            Codec.INT.fieldOf("sellPrice").forGetter(ShopEntry::getSellPrice),
            Codec.DOUBLE.optionalFieldOf("priceVariation").forGetter(entry -> Optional.of(entry.priceVariation)),
            Codec.INT.optionalFieldOf("order").forGetter(entry -> Optional.of(entry.order)),
            Codec.INT.fieldOf("weight").forGetter(ShopEntry::getWeight)
    ).apply(instance, (item, amount, description, buyPrice, sellPrice, priceVariation, order, weight) ->
            new ShopEntry(item, amount, description, buyPrice, sellPrice,
                    priceVariation.orElse(0.0), order.orElse(0), weight)));

    private ItemStack item;
    private int amount;
    private String description;
    private int buyPrice;
    private int sellPrice;
    private double priceVariation;
    private int order;
    private int weight;

    public ShopEntry(ItemStack item, int amount, String description, int buyPrice, int sellPrice, double priceVariation, int order, int weight) {
        this.item = item;
        this.amount = amount;
        this.description = description;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.priceVariation = priceVariation;
        this.order = order;
        this.weight = weight;
    }

    public static ShopEntry decode(FriendlyByteBuf buf) {
        return new ShopEntry(buf.readItem(), buf.readInt(), buf.readUtf(), buf.readInt(), buf.readInt(), buf.readDouble(), buf.readInt(), buf.readInt());
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeItem(item);
        buf.writeInt(amount);
        buf.writeUtf(description);
        buf.writeInt(buyPrice);
        buf.writeInt(sellPrice);
        buf.writeDouble(priceVariation);
        buf.writeInt(order);
        buf.writeInt(weight);
        return;
    }

    public ShopEntry(CompoundTag tag) {
        this(ItemStack.of(tag.getCompound("item")), tag.getInt("amount"), tag.getString("description"),
                tag.getInt("buyPrice"), tag.getInt("sellPrice"), tag.getDouble("priceVariation"),
                tag.getInt("order"), tag.getInt("weight"));
    }

    public CompoundTag serializeToNbt() {
        CompoundTag tag = new CompoundTag();

        CompoundTag itemTag = new CompoundTag();
        item.save(itemTag);
        tag.put("item", itemTag);
        tag.putInt("amount", amount);
        tag.putString("description", description);
        tag.putInt("buyPrice", buyPrice);
        tag.putInt("sellPrice", sellPrice);
        tag.putDouble("priceVariation", priceVariation);
        tag.putInt("order", order);
        tag.putInt("weight", weight);

        return tag;
    }

    public ItemStack getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public double getPriceVariation() {
        return priceVariation;
    }

    public String getDescription() {
        return description;
    }

    public int getOrder() {
        return order;
    }

    public int getWeight() {
        return weight;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void setPriceVariation(double priceVariation) {
        this.priceVariation = priceVariation;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public WeightedEntry.Wrapper<ShopEntry> toWeightedEntry() {
        return WeightedEntry.wrap(this, weight);
    }

    public SimpleShopEntry toSimpleEntry() {
        return new SimpleShopEntry(this);
    }
}