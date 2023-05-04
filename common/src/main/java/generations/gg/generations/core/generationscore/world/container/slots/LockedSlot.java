package generations.gg.generations.core.generationscore.world.container.slots;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class LockedSlot extends Slot {
    public LockedSlot(Inventory inventory, int i, int x, int y) {
        super(inventory, i, x, y);
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
