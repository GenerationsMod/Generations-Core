package generations.gg.generations.core.generationscore.common.world.container;

import earth.terrarium.common_storage_lib.item.impl.SimpleItemStorage;
import earth.terrarium.common_storage_lib.resources.item.ItemResource;
import generations.gg.generations.core.generationscore.common.world.container.slots.ItemStorageSlot;
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.common.world.item.curry.CurryIngredient;
import generations.gg.generations.core.generationscore.common.world.container.slots.CurryResultSlot;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

public class CookingPotContainer extends AbstractContainerMenu {
    public CookingPotContainer(int id, Inventory playerInventory) {
        this(id, playerInventory, new SimpleItemStorage(14), new SimpleContainerData(4));
    }

    public CookingPotContainer(int id, Inventory playerInventory, SimpleItemStorage storage, ContainerData data) {
        super(GenerationsContainers.COOKING_POT.get(), id);

        addSlot(new ItemStorageSlot(storage, 0, 26, 8 + 11));
        addSlot(new ItemStorageSlot(storage, 1, 44, 8 + 11));
        addSlot(new ItemStorageSlot(storage, 2, 62, 8 + 11));
        addSlot(new ItemStorageSlot(storage, 3, 80, 26 + 11));
        addSlot(new ItemStorageSlot(storage, 4, 80, 44 + 11));
        addSlot(new ItemStorageSlot(storage, 5, 62, 62 + 11));
        addSlot(new ItemStorageSlot(storage, 6, 44, 62 + 11));
        addSlot(new ItemStorageSlot(storage, 7, 26, 62 + 11));
        addSlot(new ItemStorageSlot(storage, 8, 8, 44 + 11));
        addSlot(new ItemStorageSlot(storage, 9, 8, 26 + 11));
        addSlot(new ItemStorageSlot(storage, 10, 35, 35 + 11));
        addSlot(new ItemStorageSlot(storage, 11, 53, 35 + 11));
        addSlot(new ItemStorageSlot(storage, 12, 108, 57 + 11));
        addSlot(new CurryResultSlot(playerInventory.player, storage, 13, 142, 35 + 11));

        bindPlayerInventory(playerInventory);
        addDataSlots(data);
        sendAllDataToRemote();
    }

    private void bindPlayerInventory(Inventory player) {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                addSlot(new Slot(player, x + y * 9 + 9, 8 + x * 18, 84 + y * 18 + 11 + 9));
            }
        }

        for (int x = 0; x < 9; x++) {
            addSlot(new Slot(player, x, 8 + x * 18, 142 + 11 + 9));
        }

    }

    public static boolean isBowl(Object obj) {
        return getItem(obj) == Items.BOWL;
    }

    @Override
    public boolean stillValid(@NotNull Player playerIn) {
        return true; //TODO: WOrry about this later...
    }


    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player playerIn, int index) {
        Slot slot = this.slots.get(index);
        if (!slot.hasItem()) return ItemStack.EMPTY;

        ItemStack slotStack = slot.getItem();
        ItemStack originalStack = slotStack.copy(); // Copy to retain original data

        // Store NBT for debugging before we move it
        var originalData = slotStack.getComponents();


        boolean moved = false;

        // If the slot is the crafting output (slot 13), ensure crafting logic is applied before moving
        if (index == 13) {
//            System.out.println("  - Crafting Slot Detected! Running `onTake()` before moving.");
            slot.onTake(playerIn, slotStack);
        }

        if (index < 14) {
            this.moveItemStackTo(slotStack, 14, 49, false);
        } else if (index < 50) {
            if (isBerryOrMaxMushrooms(originalStack)) {
                this.moveItemStackTo(slotStack, 0, 10, false);
            } else if (isBowl(originalStack)) {
                this.moveItemStackTo(slotStack, 10, 11, false);
            } else if (isCurryIngredientOrMaxHoney(originalStack)) {
                this.moveItemStackTo(slotStack, 11, 12, false);
            } else if (isLog(originalStack)) {
                this.moveItemStackTo(slotStack, 12, 13, false);
            }
        }

        if (!ItemStack.matches(slotStack, originalStack)) {
            slotStack.applyComponents(originalData);
        }

        if (slotStack.getCount() == 0) {
            slot.safeInsert(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }

        if (slotStack.getCount() == originalStack.getCount()) {
            return ItemStack.EMPTY;
        }

        if (index == 13) {
            slot.onTake(playerIn, originalStack);
        }

        return originalStack;
    }

    public static boolean isLog(Object stack) {
        var item = getItem(stack);
        return item.arch$holder().is(ItemTags.LOGS_THAT_BURN);
    }

    private static Item getItem(Object obj) {
        if(obj instanceof ItemResource resource) return resource.getItem();
        else if(obj instanceof ItemStack itemStack) return itemStack.getItem();
        else return null;
    }

    public static boolean isBerryOrMaxMushrooms(Object stack) {
        var item = getItem(stack);
        return item instanceof com.cobblemon.mod.common.item.berry.BerryItem || item == GenerationsItems.MAX_MUSHROOMS.get();
    }

    public static boolean isCurryIngredientOrMaxHoney(Object stack) {
        var item = getItem(stack);
        return item instanceof CurryIngredient || item == GenerationsItems.MAX_HONEY.get();
    }

    public boolean isCooking() {
        return false;
    }

    public int getCookTime() {
        return 0;
    }
}
