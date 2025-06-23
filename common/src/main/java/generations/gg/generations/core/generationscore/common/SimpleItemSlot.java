package generations.gg.generations.core.generationscore.common;

import earth.terrarium.common_storage_lib.resources.ResourceStack;
import earth.terrarium.common_storage_lib.resources.item.ItemResource;
import earth.terrarium.common_storage_lib.storage.base.StorageSlot;
import earth.terrarium.common_storage_lib.storage.base.UpdateManager;
import earth.terrarium.common_storage_lib.storage.util.ModifiableItemSlot;
import net.minecraft.world.item.ItemStack;

import java.util.function.Predicate;

public class SimpleItemSlot implements StorageSlot<ItemResource>, ModifiableItemSlot, UpdateManager<ResourceStack<ItemResource>> {
    private final Runnable update;
    private final Runnable save;
    private ItemResource resource;
    private long amount;

    public SimpleItemSlot(Runnable update, Runnable save) {
        this.resource = ItemResource.BLANK;
        this.amount = this.getAmount();
        this.update = update;
        this.save = save;
    }

    public SimpleItemSlot(Runnable update) {
        this(update, () -> {
        });
    }

    public SimpleItemSlot(ItemStack stack) {
        this.resource = ItemResource.of(stack);
        this.amount = (long)stack.getCount();
        this.update = () -> {
        };
        this.save = () -> {
        };
    }

    public long getLimit(ItemResource resource) {
        return resource.isBlank() ? 99L : (long)resource.getCachedStack().getMaxStackSize();
    }

    public boolean isResourceValid(ItemResource resource) {
        return true;
    }

    public ItemResource getResource() {
        return this.resource;
    }

    public long getAmount() {
        return this.amount;
    }

    public void set(ItemResource resource, long amount) {
        this.resource = resource;
        this.amount = amount;
        this.update();
    }

    public void set(ResourceStack<ItemResource> data) {
        this.resource = (ItemResource)data.resource();
        this.amount = data.amount();
        this.update();
    }

    public long insert(ItemResource resource, long amount, boolean simulate) {
        if (!this.isResourceValid(resource)) {
            return 0L;
        } else {
            long inserted;
            if (this.resource.isBlank()) {
                inserted = Math.min(amount, this.getLimit(resource));
                if (!simulate) {
                    this.resource = resource;
                    this.amount = inserted;
                    this.save.run();
                }

                return inserted;
            } else if (this.resource.equals(resource)) {
                inserted = Math.min(amount, this.getLimit(resource) - this.amount);
                if (!simulate) {
                    this.amount += inserted;
                    this.save.run();
                }

                return inserted;
            } else {
                return 0L;
            }
        }
    }

    public long extract(ItemResource resource, long amount, boolean simulate) {
        if (this.resource.equals(resource)) {
            long extracted = Math.min(amount, this.amount);
            if (!simulate) {
                this.amount -= extracted;
                if (this.amount == 0L) {
                    this.resource = ItemResource.BLANK;
                    this.save.run();
                }
            }

            return extracted;
        } else {
            return 0L;
        }
    }

    public ResourceStack<ItemResource> createSnapshot() {
        return new ResourceStack(this.resource, this.amount);
    }

    public void readSnapshot(ResourceStack<ItemResource> snapshot) {
        this.resource = (ItemResource)snapshot.resource();
        this.amount = snapshot.amount();
    }

    public void update() {
        this.update.run();
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setResource(ItemResource resource) {
        this.resource = resource;
    }

    public ItemStack toItemStack() {
        return this.resource.toStack((int)this.amount);
    }

    public int getMaxAllowed(ItemResource resource) {
        return resource.getCachedStack().getMaxStackSize();
    }

    public boolean isEmpty() {
        return this.resource.isBlank() || this.amount == 0L;
    }

    public static class Filtered extends SimpleItemSlot {
        private final Predicate<ItemResource> filter;

        public Filtered(Runnable update, Predicate<ItemResource> filter) {
            super(update);
            this.filter = filter;
        }

        public boolean isResourceValid(ItemResource resource) {
            return this.filter.test(resource);
        }
    }
}
