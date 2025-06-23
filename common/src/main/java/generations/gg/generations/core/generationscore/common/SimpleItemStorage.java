package generations.gg.generations.core.generationscore.common;

import earth.terrarium.common_storage_lib.context.ItemContext;
import earth.terrarium.common_storage_lib.data.DataManager;
import earth.terrarium.common_storage_lib.item.util.ItemStorageData;
import earth.terrarium.common_storage_lib.resources.ResourceStack;
import earth.terrarium.common_storage_lib.resources.item.ItemResource;
import earth.terrarium.common_storage_lib.storage.base.CommonStorage;
import earth.terrarium.common_storage_lib.storage.base.UpdateManager;
import earth.terrarium.common_storage_lib.storage.util.TransferUtil;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Predicate;

public class SimpleItemStorage implements CommonStorage<ItemResource>, UpdateManager<ItemStorageData> {
    protected final NonNullList<SimpleItemSlot> slots;
    private final Runnable onUpdate;
    private final Runnable save;

    public SimpleItemStorage(int size) {
        this.slots = NonNullList.withSize(size, new SimpleItemSlot(this::update));
        this.onUpdate = () -> {
        };
        this.save = () -> {
        };
    }

    public SimpleItemStorage(ItemContext context, DataComponentType<ItemStorageData> componentType, int size) {
        this.slots = NonNullList.withSize(size, new SimpleItemSlot(this::update));
        Objects.requireNonNull(context);
        this.onUpdate = context::updateAll;
        this.save = () -> {
            context.set(componentType, ItemStorageData.of(this));
        };
        if (context.getResource().has(componentType)) {
            this.readSnapshot(context.getResource().get(componentType));
        }

    }

    public SimpleItemStorage(Object entityOrBlockEntity, DataManager<ItemStorageData> dataManager, int size) {
        this.slots = NonNullList.withSize(size, new SimpleItemSlot(this::update));
        this.onUpdate = () -> {
            dataManager.set(entityOrBlockEntity, ItemStorageData.of(this));
        };
        this.save = () -> {
        };
        this.readSnapshot(dataManager.get(entityOrBlockEntity));
    }

    public SimpleItemStorage filter(int slot, Predicate<ItemResource> predicate) {
        this.slots.set(slot, new SimpleItemSlot.Filtered(this::update, predicate));
        return this;
    }

    public int size() {
        return this.slots.size();
    }

    public @NotNull SimpleItemSlot get(int index) {
        return this.slots.get(index);
    }

    public ItemStorageData createSnapshot() {
        return ItemStorageData.of(this);
    }

    public void readSnapshot(ItemStorageData snapshot) {
        for(int i = 0; i < this.slots.size() && i < snapshot.stacks().size(); ++i) {
            ((SimpleItemSlot)this.slots.get(i)).readSnapshot((ResourceStack)snapshot.stacks().get(i));
        }

    }

    public void update() {
        this.onUpdate.run();
    }

    public long insert(ItemResource resource, long amount, boolean simulate) {
        return TransferUtil.insertSlots(this, resource, amount, simulate);
    }

    public long extract(ItemResource resource, long amount, boolean simulate) {
        return TransferUtil.extractSlots(this, resource, amount, simulate);
    }
}
