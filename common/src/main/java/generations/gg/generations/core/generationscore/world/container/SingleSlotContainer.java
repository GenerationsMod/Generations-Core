package generations.gg.generations.core.generationscore.world.container;

import generations.gg.generations.core.generationscore.world.container.slots.LockedSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public abstract class SingleSlotContainer extends AbstractContainerMenu {
    protected final ItemStackHandler handler;

    protected SingleSlotContainer(MenuType<? extends SingleSlotContainer> type, int id) {
        this(type, id, new ItemStackHandler(1));
    }

    protected SingleSlotContainer(MenuType<? extends SingleSlotContainer> type, int id, ItemStackHandler handler) {
        super(type, id);
        this.handler = handler;

        this.addSlot(new PredicateSlotItemHandler(handler, 0, 80,35, this::isStackValidForSingleSlot));
    }

    //Needs to be applied after constructor
    public void applyPlayerInventory(Inventory playerInventory) {
        for(int y = 0; y < 3; y++) {
            for(int x = 0; x < 9; x++) {
                this.addSlot(getSlot(playerInventory, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
            }
        }

        for (int x = 0; x < 9; x++) {
            this.addSlot(getSlot(playerInventory, x, 8 + x * 18, 142));
        }
    }

    protected boolean isStackValidForSingleSlot(ItemStack stack) {
        return true;
    }

    protected Slot getSlot(Inventory inventory, int i, int x, int y) {
        if(isPlayerSlotLocked(i)) {
            return new LockedSlot(inventory, i, x, y);
        } else {
            return new Slot(inventory, i, x, y);
        }
    }

    protected boolean isPlayerSlotLocked(int slot) {
        return false;
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;

        Slot slot = this.slots.get(index);

        if (slot.hasItem()) {
            ItemStack itemStack2 = slot.getItem();
            itemStack = itemStack2.copy();

            if (index < 1 ? !this.moveItemStackTo(itemStack2, 1, this.slots.size(), true) : !this.moveItemStackTo(itemStack2, 0, 1, false)) {
                return ItemStack.EMPTY;
            }
            if (itemStack2.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return itemStack;
    }


    @Override
    public boolean stillValid(@NotNull Player player) {
        return true;
    }
}
