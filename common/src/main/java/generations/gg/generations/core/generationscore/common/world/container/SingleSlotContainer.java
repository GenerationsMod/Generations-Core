package generations.gg.generations.core.generationscore.common.world.container;

import earth.terrarium.common_storage_lib.item.impl.SimpleItemStorage;
import earth.terrarium.common_storage_lib.item.impl.vanilla.WrappedVanillaContainer;
import earth.terrarium.common_storage_lib.resources.item.ItemResource;
import earth.terrarium.common_storage_lib.storage.base.CommonStorage;
import earth.terrarium.common_storage_lib.storage.util.MenuStorageSlot;
import generations.gg.generations.core.generationscore.common.world.container.slots.LockedSlot;
import generations.gg.generations.core.generationscore.common.world.container.slots.PredicateSlotItemHandler;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public abstract class SingleSlotContainer extends AbstractContainerMenu {
    protected final SimpleItemStorage handler;

    protected SingleSlotContainer(MenuType<? extends SingleSlotContainer> type, int id) {
        this(type, id, new SimpleItemStorage(1));
    }

    protected SingleSlotContainer(MenuType<? extends SingleSlotContainer> type, int id, SimpleItemStorage handler) {
        super(type, id);
        this.handler = handler;

        this.addSlot(new PredicateSlotItemHandler(handler, 0, 80,35, this::isStackValidForSingleSlot));
    }

    //Needs to be applied after constructor
    public void applyPlayerInventory(Inventory playerInventory) {
        var storage = new WrappedVanillaContainer(playerInventory);

        for(int y = 0; y < 3; y++) {
            for(int x = 0; x < 9; x++) {
                this.addSlot(getSlot(storage, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
            }
        }

        for (int x = 0; x < 9; x++) {
            this.addSlot(getSlot(storage, x, 8 + x * 18, 142));
        }
    }

    protected boolean isStackValidForSingleSlot(ItemStack stack) {
        return true;
    }

    protected Slot getSlot(CommonStorage<ItemResource> inventory, int i, int x, int y) {
        if(isPlayerSlotLocked(i)) {
            return new LockedSlot(inventory, i, x, y);
        } else {
            return new MenuStorageSlot(inventory, i, x, y);
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
