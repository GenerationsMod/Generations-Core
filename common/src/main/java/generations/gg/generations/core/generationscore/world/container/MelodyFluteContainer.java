package generations.gg.generations.core.generationscore.world.container;

import com.pokemod.pokemod.world.item.MelodyFluteItem;
import com.pokemod.pokemod.world.item.PokeModItems;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class MelodyFluteContainer extends SingleSlotContainer {
    private final int slot;

    public MelodyFluteContainer(int id, Inventory playerInventory, ItemStackHandler handler) {
        super(PixelmonContainers.MELODY_FLUTE.get(), id, handler);
        slot = playerInventory.selected;
        applyPlayerInventory(playerInventory);
    }

    public MelodyFluteContainer(int id, Inventory playerInventory, FriendlyByteBuf buf) {
        super(PixelmonContainers.MELODY_FLUTE.get(), id);
        this.slot = buf.readShort();
        applyPlayerInventory(playerInventory);
    }

    @Override
    protected boolean isStackValidForSingleSlot(ItemStack stack) {
        return MelodyFluteItem.isItem(PokeModItems.ICY_WING, stack) ||
                MelodyFluteItem.isItem(PokeModItems.ELEGANT_WING, stack) ||
                MelodyFluteItem.isItem(PokeModItems.STATIC_WING, stack) ||
                MelodyFluteItem.isItem(PokeModItems.BELLIGERENT_WING, stack) ||
                MelodyFluteItem.isItem(PokeModItems.FIERY_WING, stack) ||
                MelodyFluteItem.isItem(PokeModItems.SINISTER_WING, stack) ||
                MelodyFluteItem.isItem(PokeModItems.RAINBOW_WING, stack) ||
                MelodyFluteItem.isItem(PokeModItems.SILVER_WING, stack);
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

    public static class MelodyFluteItemStackHandler extends ItemStackHandler {
        private final ServerPlayer player;
        private final InteractionHand hand;

        private ItemStack previous;
        private boolean changed = false;

        public MelodyFluteItemStackHandler(ServerPlayer player, InteractionHand hand) {
            super(1);
            var itemStack = MelodyFluteItem.getImbuedItem(player.getItemInHand(hand));
            previous = itemStack;

            setStackInSlot(0, itemStack);

            this.player = player;
            this.hand = hand;
        }

        @Override
        protected void onContentsChanged(int slot) {
            changed = !ItemStack.isSame(getStackInSlot(slot), previous);
        }

        public void save() {
            MelodyFluteItem.setImbuedItem(player.getItemInHand(hand), getStackInSlot(0));
        }
    }
}
