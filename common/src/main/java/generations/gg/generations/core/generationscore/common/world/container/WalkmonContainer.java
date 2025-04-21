package generations.gg.generations.core.generationscore.common.world.container;

import generations.gg.generations.core.generationscore.common.world.container.slots.PredicateSlotItemHandler;
import generations.gg.generations.core.generationscore.common.world.item.WalkmonItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;

public class WalkmonContainer extends GenericChestContainer {

    public WalkmonContainer(int containerId, Inventory playerInventory, FriendlyByteBuf buf) {
        super(GenerationsContainers.WALKMON.get(), containerId, playerInventory, buf);
    }

    public WalkmonContainer(int containerId, Inventory playerInventory, GenericContainer.SimpleGenericContainer container, int lockedSlot) {
        super(GenerationsContainers.WALKMON.get(), containerId, playerInventory, container, lockedSlot);
    }

    @Override
    public Slot getSlot(Container container, int slot, int x, int y) {
        if(container instanceof Inventory) return super.getSlot(container, slot, x, y);
        return new PredicateSlotItemHandler(container, slot, x, y, itemStack -> itemStack.has(DataComponents.JUKEBOX_PLAYABLE));
    }

    @Override
    public void save(Player player) {
        var stack = playerInventory.getItem(this.getLocked());
        if(stack.getItem() instanceof WalkmonItem walkmon) {
            var holder = walkmon.load(stack);

            holder.save(stack);
        }
    }


}
