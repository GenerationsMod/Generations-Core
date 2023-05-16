package generations.gg.generations.core.generationscore.world.container;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GenericChestContainer extends AbstractContainerMenu {
    private final GenericContainer container;
    private final int guiWidth;
    private final int guiHeight;
    private final int playerInventoryX;

    public GenericChestContainer(int containerId, Inventory playerInventory, FriendlyByteBuf buf) {
        this(containerId, playerInventory, new GenericContainer.SimpleGenericContainer(buf.readVarInt(), buf.readVarInt()));
    }

    public GenericChestContainer(int containerId, Inventory playerInventory, GenericContainer container) {
        super(GenerationsContainers.GENERIC.get(), containerId);

        this.container = container;
        container.startOpen(playerInventory.player);

        this.guiWidth = 14 + getInventoryWidth() * 18;
        this.guiHeight = 110 + (getInventoryHeight() * 18);

        populate(container, 8, 16, 0, getInventoryHeight(), getInventoryWidth());

        this.playerInventoryX = guiWidth/2 - 80;

        populate(playerInventory, playerInventoryX, guiHeight - 82, 1, 3, 9);
        populate(playerInventory, playerInventoryX, guiHeight - 24, 0, 1, 9);
    }

    private void populate(Container container, int x, int y, int startingRow, int rows, int columns) {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                this.addSlot(new Slot(container, column + (startingRow + row) * columns, x + column * 18, y + row * 18));
            }
        }
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;

        Slot slot = this.slots.get(index);

        if(slot.hasItem()) {
            ItemStack itemStack1 = slot.getItem();
            itemStack = itemStack1.copy();

            if(index < container.getContainerSize() ? !this.moveItemStackTo(itemStack1, container.getContainerSize(), this.slots.size(), true) : !this.moveItemStackTo(itemStack1, 0, container.getContainerSize(), false)) {
                return ItemStack.EMPTY;
            }

            if(itemStack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemStack;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return this.container.stillValid(player);
    }

    public void removed(@NotNull Player player) {
        super.removed(player);
        this.container.stopOpen(player);
    }

    public int getInventoryWidth() {
        return container.getWidth();
    }

    public int getInventoryHeight() {
        return container.getHeight();
    }

    public int getGuiWidth() {
        return guiWidth;
    }

    public int getGuiHeight() {
        return guiHeight;
    }

    public int getPlayerInventoryX() {
        return playerInventoryX;
    }

    public Container getContainer() {
        return container;
    }
}
