package generations.gg.generations.core.generationscore.world.container;

import generations.gg.generations.core.generationscore.util.ExtendedsimpleItemContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class TrashCanContainer extends SingleSlotContainer {

    public TrashCanContainer(int id, Inventory arg) {
        super(PixelmonContainers.TRASHCAN.get(), id, new ExtendedsimpleItemContainer(null, 1) {

            @Override
            public void setItem(int slot, @NotNull ItemStack stack) {
            }

            @Override
            public ItemStack getItem(int i) {
                return ItemStack.EMPTY;
            }

            @Override
            public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
                return ItemStack.EMPTY;
            }

            @Override
            public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
                return ItemStack.EMPTY;
            }
        });

        applyPlayerInventory(arg);
    }
}
