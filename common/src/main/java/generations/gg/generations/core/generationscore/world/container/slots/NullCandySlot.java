package generations.gg.generations.core.generationscore.world.container.slots;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.DoubleSupplier;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class NullCandySlot extends Slot {
    private static final Container emptyInventory = new SimpleContainer(0);
    private final Container candies;
    private final Supplier<ItemStack> item;
    private final DoubleSupplier supplier;

    public NullCandySlot(Container candies, int x, int y, RegistrySupplier<Item> item, DoubleSupplier supplier) {
        super(emptyInventory, 0, x, y);
        this.candies = candies;
        this.item = () -> new ItemStack(item.get());
        this.supplier = supplier;
    }

    @Override
    public void onQuickCraft(@NotNull ItemStack oldStack, @NotNull ItemStack newStack) {
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return false;
    }

    @Override
    public @NotNull ItemStack getItem() {
        return item.get();
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public void set(@NotNull ItemStack stack) {

    }

    @Override
    public @NotNull ItemStack remove(int amount) {
        if(hasItem()) {
            IntStream.range(0, candies.getContainerSize()).forEach(i -> candies.setItem(i, ItemStack.EMPTY));
            return item.get();
        }

        return ItemStack.EMPTY;
    }
    @Override
    public boolean isActive() {
        return supplier.getAsDouble() == 1.0f;
    }
}
