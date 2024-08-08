package generations.gg.generations.core.generationscore.common.world.container.slots;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class PredicateSlotItemHandler extends Slot {
    private final Predicate<ItemStack> predicate;

    public PredicateSlotItemHandler(Container itemHandler, int index, int xPosition, int yPosition, Predicate<ItemStack> predicate) {
        super(itemHandler, index, xPosition, yPosition);
        this.predicate = predicate;
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return predicate.test(stack);
    }
}
