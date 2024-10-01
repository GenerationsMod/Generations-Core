package generations.gg.generations.core.generationscore.common.world.recipe;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.BiConsumer;
import java.util.function.Function;

public record ItemResult(ItemStack item) implements RksResult<ItemResult> {
    public static final Codec<ItemResult> CODEC = Codec.either(BuiltInRegistries.ITEM.byNameCodec(), ItemStack.CODEC).xmap(a -> a.map(Item::getDefaultInstance, b -> b), a -> a.getCount() > 1 || a.hasTag() ? Either.right(a) : Either.left(a.getItem())).xmap(ItemResult::new, ItemResult::item);

    public static final Function<FriendlyByteBuf, ItemResult> FROM_BUFFER = buffer -> new ItemResult(buffer.readItem());

    public static final BiConsumer<FriendlyByteBuf, ItemResult> TO_BUFFER = (buffer, result) -> buffer.writeItem(result.item());

    @Override
    public ItemStack getStack() {
        return item;
    }

    @Override
    public RksResultType<ItemResult> type() {
        return RksResultType.ITEM.get();
    }

    @Override
    public boolean isPokemon() {
        return false;
    }
}
