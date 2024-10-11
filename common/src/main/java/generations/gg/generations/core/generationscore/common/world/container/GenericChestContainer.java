package generations.gg.generations.core.generationscore.common.world.container;

import generations.gg.generations.core.generationscore.common.world.container.slots.LockedSlot;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GenericChestContainer extends AbstractContainerMenu {
    private final GenericContainer container;
    private final int guiWidth;
    private final int guiHeight;
    private final int playerInventoryX;
    private final Inventory playerInventory;
    private final int lockedSlot;

    public GenericChestContainer(int containerId, Inventory playerInventory, FriendlyByteBuf buf) {
        this(GenerationsContainers.GENERIC.get(), containerId, playerInventory, buf);
    }

    public GenericChestContainer(MenuType<?> type, int containerId, Inventory playerInventory, FriendlyByteBuf buf) {
        this(type, containerId, playerInventory, new GenericContainer.SimpleGenericContainer(buf.readVarInt(), buf.readVarInt()), buf.readVarInt());
    }

    public GenericChestContainer(int containerId, Inventory playerInventory, GenericContainer container) {
        this(GenerationsContainers.GENERIC.get(), containerId, playerInventory, container, -1);
    }

    public GenericChestContainer(MenuType<?> type, int containerId, Inventory playerInventory, GenericContainer container, int lockedSlot) {
        super(type, containerId);

        this.container = container;
        this.playerInventory = playerInventory;
        this.lockedSlot = lockedSlot;

        this.guiWidth = 14 + getInventoryWidth() * 18;
        this.guiHeight = 110 + (getInventoryHeight() * 18);
        container.startOpen(playerInventory.player);

        populate(container, 8, 16, 0, getInventoryHeight(), getInventoryWidth());

        this.playerInventoryX = guiWidth/2 - 80;

        populate(playerInventory, playerInventoryX, guiHeight - 82, 1, 3, 9);
        populate(playerInventory, playerInventoryX, guiHeight - 24, 0, 1, 9);
    }

    private void populate(Container container, int x, int y, int startingRow, int rows, int columns) {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                var slot = column + (startingRow + row) * columns;
                var xSlot = x + column * 18;
                var ySlot = y + row * 18;

                this.addSlot(container, slot, xSlot, ySlot);
            }
        }
    }

    public void addSlot(Container container, int slot, int x, int y) {
        this.addSlot(getSlot(container, slot, x, y));
    }

    public Slot getSlot(Container container, int slot, int x, int y) {
        return lockedSlot == slot ? new LockedSlot(container, slot, x, y) : new Slot(container, slot, x, y);
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

    public void save(Player player) {}

    public Inventory getPlayerInventory() {
        return playerInventory;
    }

    protected int getLocked() {
        return lockedSlot;
    }
}
