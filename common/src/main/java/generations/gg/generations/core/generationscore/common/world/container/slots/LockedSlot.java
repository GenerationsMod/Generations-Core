package generations.gg.generations.core.generationscore.common.world.container.slots;

import earth.terrarium.common_storage_lib.resources.item.ItemResource;
import earth.terrarium.common_storage_lib.storage.base.CommonStorage;
import earth.terrarium.common_storage_lib.storage.util.MenuStorageSlot;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class LockedSlot extends MenuStorageSlot {
    public LockedSlot(CommonStorage<ItemResource> storage, int slot, int x, int y) {
        super(storage, slot, x, y);
    }

    @Override
    public boolean mayPickup(@NotNull Player player) {
        return false;
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return false;
    }
}
