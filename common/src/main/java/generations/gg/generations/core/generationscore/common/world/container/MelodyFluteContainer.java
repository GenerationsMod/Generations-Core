package generations.gg.generations.core.generationscore.common.world.container;

import earth.terrarium.common_storage_lib.context.impl.PlayerContext;
import earth.terrarium.common_storage_lib.item.impl.SimpleItemStorage;
import generations.gg.generations.core.generationscore.common.GenerationsStorage;
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.common.world.item.MelodyFluteItem;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class MelodyFluteContainer extends SingleSlotContainer {
    private final int slot;

    public MelodyFluteContainer(int id, Inventory playerInventory, Player player) {
        super(GenerationsContainers.MELODY_FLUTE.get(), id, new SimpleItemStorage(PlayerContext.ofSlot(player, player.getInventory().selected), GenerationsStorage.INSTANCE.getIMBUED(), 1));
        slot = playerInventory.selected;
        applyPlayerInventory(playerInventory);
    }

    public MelodyFluteContainer(int id, Inventory playerInventory) {
        super(GenerationsContainers.MELODY_FLUTE.get(), id);
        this.slot = -1;
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
}
