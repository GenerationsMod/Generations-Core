package generations.gg.generations.core.generationscore.world.container;

import generations.gg.generations.core.generationscore.util.ExtendedsimpleItemContainer;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.item.MelodyFluteItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

public class MelodyFluteContainer extends SingleSlotContainer {
    private final int slot;

    public MelodyFluteContainer(int id, Inventory playerInventory, Container handler) {
        super(GenerationsContainers.MELODY_FLUTE.get(), id, handler);
        slot = playerInventory.selected;
        applyPlayerInventory(playerInventory);
    }

    public MelodyFluteContainer(int id, Inventory playerInventory, FriendlyByteBuf buf) {
        super(GenerationsContainers.MELODY_FLUTE.get(), id);
        this.slot = buf.readShort();
        applyPlayerInventory(playerInventory);
    }

    @Override
    protected boolean isStackValidForSingleSlot(ItemStack stack) {
        return MelodyFluteItem.isItem(GenerationsItems.ICY_WING, stack) ||
                MelodyFluteItem.isItem(GenerationsItems.ELEGANT_WING, stack) ||
                MelodyFluteItem.isItem(GenerationsItems.STATIC_WING, stack) ||
                MelodyFluteItem.isItem(GenerationsItems.BELLIGERENT_WING, stack) ||
                MelodyFluteItem.isItem(GenerationsItems.FIERY_WING, stack) ||
                MelodyFluteItem.isItem(GenerationsItems.SINISTER_WING, stack) ||
                MelodyFluteItem.isItem(GenerationsItems.RAINBOW_WING, stack) ||
                MelodyFluteItem.isItem(GenerationsItems.SILVER_WING, stack);
    }

    protected boolean isPlayerSlotLocked(int slot) {
        return this.slot == slot;
    }

    @Override
    public boolean isValidSlotIndex(int slotIndex) {
        return super.isValidSlotIndex(slotIndex) || slotIndex == slot;
    }

    public void save() {
        if(handler instanceof MelodyFluteItemStackHandler melodyFluteItemStackHandler) melodyFluteItemStackHandler.save();
    }

    public static class MelodyFluteItemStackHandler extends ExtendedsimpleItemContainer {
        private final ServerPlayer player;
        private final InteractionHand hand;

        private ItemStack previous;
        private boolean changed = false;

        public MelodyFluteItemStackHandler(ServerPlayer player, InteractionHand hand) {
            super(null, 1);
            var itemStack = MelodyFluteItem.getImbuedItem(player.getItemInHand(hand));
            previous = itemStack;

            setItem(0, itemStack);

            this.player = player;
            this.hand = hand;
        }

        @Override
        public void setChanged() {
            changed = !ItemStack.isSame(getItem(0), previous);
        }

        public void save() {
            MelodyFluteItem.setImbuedItem(player.getItemInHand(hand), getItem(0));
        }
    }
}
