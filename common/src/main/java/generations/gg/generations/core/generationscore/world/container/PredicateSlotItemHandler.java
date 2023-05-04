package generations.gg.generations.core.generationscore.world.container;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

class PredicateSlotItemHandler extends SlotItemHandler {
    private final Predicate<ItemStack> predicate;

    public PredicateSlotItemHandler(IItemHandler itemHandler, int index, int xPosition, int yPosition, Predicate<ItemStack> predicate) {
        super(itemHandler, index, xPosition, yPosition);
        this.predicate = predicate;
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return predicate.test(stack);
    }
}
