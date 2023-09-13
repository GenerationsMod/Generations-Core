package generations.gg.generations.core.generationscore.world.shop;

import generations.gg.generations.core.generationscore.util.ShopUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

import java.util.Random;

public class SimpleShopEntry {

    private static final Random rnd = new Random();

    private final ItemStack item;
    private final String description;
    private final int buyPrice;
    private final int sellPrice;
    private final int order;

    public SimpleShopEntry(ShopEntry shopEntry) {
        this.item = shopEntry.getItem();
        this.item.setCount(shopEntry.getAmount());
        this.description = shopEntry.getDescription();
        this.buyPrice = applyVariation(shopEntry.getBuyPrice(), shopEntry.getPriceVariation());
        this.sellPrice = applyVariation(shopEntry.getSellPrice(), shopEntry.getPriceVariation());
        this.order = shopEntry.getOrder();
    }

    public SimpleShopEntry(CompoundTag tag) {
        this.item = ItemStack.of(tag.getCompound("item"));
        this.description = tag.getString("description");
        this.buyPrice = tag.getInt("buyPrice");
        this.sellPrice = tag.getInt("sellPrice");
        this.order = tag.getInt("order");
    }

    public CompoundTag serializeToNbt() {
        CompoundTag tag = new CompoundTag();

        CompoundTag itemTag = new CompoundTag();
        item.save(itemTag);
        tag.put("item", itemTag);
        tag.putString("description", description);
        tag.putInt("buyPrice", buyPrice);
        tag.putInt("sellPrice", sellPrice);
        tag.putInt("order", order);

        return tag;
    }

    private int applyVariation(int price, double variation) {
        if (variation == 0)
            return price;

        int maxVariation = (int) (price * variation);
        return rnd.nextInt(price - maxVariation, price + maxVariation + 1);
    }

    public ItemStack getItem() {
        return item;
    }

    public String getDescription() {
        return description;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public int getOrder() {
        return order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleShopEntry that = (SimpleShopEntry) o;
        return order == that.order && ShopUtils.matches(item, that.item) && description.equals(that.description);
    }
}