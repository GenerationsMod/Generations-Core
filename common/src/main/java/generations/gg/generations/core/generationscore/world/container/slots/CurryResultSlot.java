package generations.gg.generations.core.generationscore.world.container.slots;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CurryResultSlot extends Slot {
    private final Player player;
    private int removeCount;

    public CurryResultSlot(Player player, Container handler, int slotIndex, int xPosition, int yPosition) {
        super(handler, slotIndex, xPosition, yPosition);
        this.player = player;
    }

    /**
     * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
     */
    public boolean mayPlace(@NotNull ItemStack stack) {
        return false;
    }

    @Override
    public @NotNull ItemStack remove(int amount) {
        if (this.hasItem()) {
            this.removeCount += Math.min(amount, this.getItem().getCount());
        }
        return super.remove(amount);
    }

    public void onTake(@NotNull Player player, @NotNull ItemStack stack) {
        this.checkTakeAchievements(player, stack);
        super.onTake(player, stack);
    }

    protected void checkTakeAchievements(Player player, ItemStack stack) {
        stack.onCraftedBy(player.level(), player, this.removeCount);
        //TODO: Curries made stat?
        this.removeCount = 0;
    }
}
