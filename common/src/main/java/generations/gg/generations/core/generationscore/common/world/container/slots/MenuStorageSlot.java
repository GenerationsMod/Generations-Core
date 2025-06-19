//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package generations.gg.generations.core.generationscore.common.world.container.slots;

import earth.terrarium.common_storage_lib.resources.item.ItemResource;
import earth.terrarium.common_storage_lib.storage.base.CommonStorage;
import earth.terrarium.common_storage_lib.storage.base.StorageSlot;
import earth.terrarium.common_storage_lib.storage.base.UpdateManager;
import earth.terrarium.common_storage_lib.storage.util.ModifiableItemSlot;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class MenuStorageSlot extends Slot {
    public static final Container EMPTY = new SimpleContainer(0);
    private final StorageSlot<ItemResource> storageSlot;
    private final ModifiableItemSlot modifiable;

    private ItemStack cached = null;

    public MenuStorageSlot(CommonStorage<ItemResource> storage, int slotIndex, int x, int y) {
        super(EMPTY, slotIndex, x, y);
        this.storageSlot = storage.get(slotIndex);
        StorageSlot var6 = this.storageSlot;
        if (var6 instanceof ModifiableItemSlot slot) {
            this.modifiable = slot;
            cached = modifiable.toItemStack();
        } else {
            throw new UnsupportedOperationException("Cannot create MenuStorageSlot from non-modifiable slot");
        }
    }

    public boolean mayPlace(ItemStack stack) {
        return !stack.isEmpty() && this.storageSlot.isResourceValid(ItemResource.of(stack));
    }

    public @NotNull ItemStack getItem() {
        return cached;
    }

    public void set(ItemStack stack) {
        this.modifiable.set(stack);
        cached = stack;
    }

    public void onQuickCraft(ItemStack oldStackIn, ItemStack newStackIn) {
    }

    public int getMaxStackSize() {
        return (int)this.storageSlot.getLimit((ItemResource)this.storageSlot.getResource());
    }

    public int getMaxStackSize(ItemStack stack) {
        return this.modifiable.getMaxAllowed(ItemResource.of(stack));
    }

    public boolean mayPickup(Player playerIn) {
        return !this.modifiable.isEmpty();
    }

    public @NotNull ItemStack remove(int amount) {
        ItemResource resource = (ItemResource)this.storageSlot.getResource();
        long extract = this.storageSlot.extract(resource, (long)amount, false);
        var itemStack = extract > 0L ? resource.toStack((int)extract) : ItemStack.EMPTY;

        cached = modifiable.toItemStack();

        return itemStack;

    }

    public void setChanged() {
        super.setChanged();
        UpdateManager.batch(new Object[]{this.storageSlot});
    }
}
