package generations.gg.generations.core.generationscore.common.util;

import earth.terrarium.botarium.common.item.SimpleItemContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

public class ExtendedsimpleItemContainer extends SimpleItemContainer {
    private final boolean hasEntity;

    public ExtendedsimpleItemContainer(BlockEntity entity, int size, Predicate<Player> canPlayerAccess) {
        super(entity, size, canPlayerAccess);

        hasEntity = entity != null;
    }

    public ExtendedsimpleItemContainer(BlockEntity entity, int size) {
        super(entity, size);
        hasEntity = entity != null;
    }

    @NotNull
    public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
        if (stack.isEmpty())
            return ItemStack.EMPTY;

        if (!isItemValid(slot, stack))
            return stack;

        validateSlotIndex(slot);

        ItemStack existing = this.getItem(slot);

        int limit = getStackLimit(slot, stack);

        if (!existing.isEmpty()) {
            if (!canItemStacksStack(stack, existing))
                return stack;

            limit -= existing.getCount();
        }

        if (limit <= 0)
            return stack;

        boolean reachedLimit = stack.getCount() > limit;

        if (!simulate) {
            if (existing.isEmpty()) {
                this.setItem(slot, reachedLimit ? copyStackWithSize(stack, limit) : stack);
            } else {
                existing.grow(reachedLimit ? limit : stack.getCount());
            }
        }

        return reachedLimit ? copyStackWithSize(stack, stack.getCount()- limit) : ItemStack.EMPTY;
    }

    @NotNull
    public static ItemStack copyStackWithSize(@NotNull ItemStack itemStack, int size) {
        if (size == 0)
            return ItemStack.EMPTY;
        ItemStack copy = itemStack.copy();
        copy.setCount(size);
        return copy;
    }

    protected void validateSlotIndex(int slot) {
        if (slot < 0 || slot >= getContainerSize())
            throw new RuntimeException("Slot " + slot + " not in valid range - [0," + getContainerSize() + ")");
    }

    public boolean isItemValid(int slot, @NotNull ItemStack stack)
    {
        return true;
    }

    public int getSlotLimit(int slot)
    {
        return 64;
    }

    protected int getStackLimit(int slot, @NotNull ItemStack stack) {
        return Math.min(getSlotLimit(slot), stack.getMaxStackSize());
    }

    @Override
    public ItemStack getItem(int i) {
        validateSlotIndex(i);
        return super.getItem(i);
    }

    public static boolean canItemStacksStack(@NotNull ItemStack a, @NotNull ItemStack b) {
        if (a.isEmpty() || !ItemStack.isSameItem(a, b) || a.hasTag() != b.hasTag())
            return false;

        if (!a.hasTag()) return true;
        assert a.getTag() != null;
        return (a.getTag().equals(b.getTag()));
    }

    @NotNull
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        if (amount == 0)
            return ItemStack.EMPTY;

        validateSlotIndex(slot);

        ItemStack existing = this.getItem(slot);

        if (existing.isEmpty())
            return ItemStack.EMPTY;

        int toExtract = Math.min(amount, existing.getMaxStackSize());

        if (existing.getCount() <= toExtract) {
            if (!simulate) {
                this.setItem(slot, ItemStack.EMPTY);
                setChanged();
                return existing;
            }
            else {
                return existing.copy();
            }
        } else {
            if (!simulate) {
                this.setItem(slot, copyStackWithSize(existing, existing.getCount() - toExtract));
                setChanged();
            }

            return copyStackWithSize(existing, toExtract);
        }
    }

    @Override
    public void setChanged() {
        if(hasEntity) super.setChanged();

    }
}
