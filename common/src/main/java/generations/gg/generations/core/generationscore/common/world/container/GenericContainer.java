package generations.gg.generations.core.generationscore.common.world.container;

import dev.architectury.registry.menu.MenuRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface GenericContainer extends Container, MenuProvider {
    int getWidth();

    int getHeight();

    default void openScreen(Player player) {
        openScreen(player, -1);
    }

    default void openScreen(Player player, int lockedSlot) {
        if (!player.isLocalPlayer()) MenuRegistry.openExtendedMenu((ServerPlayer) player, this, byteBuf -> byteBuf.writeVarInt(getWidth()).writeVarInt(getHeight()).writeVarInt(lockedSlot));
    }



    default @Nullable
    @Override
    AbstractContainerMenu createMenu(int i, @NotNull Inventory arg, @NotNull Player arg2) {
        return new GenericChestContainer(i, arg, this);
    }

    class SimpleGenericContainer extends SimpleContainer implements GenericContainer {
        private final int width;
        private final int height;

        public SimpleGenericContainer(int width, int height) {
            super(width * height);

            this.width = width;
            this.height = height;
        }

        @Override
        public int getWidth() {
            return width;
        }

        @Override
        public int getHeight() {
            return height;
        }

        @Override
        public @NotNull Component getDisplayName() {
            return Component.empty();
        }

        @Override
        public void fromTag(@NotNull ListTag containerNbt) {
            for (int i = 0; i < this.getContainerSize(); i++) {
                this.setItem(i, ItemStack.EMPTY);
            }

            for (int i = 0; i < containerNbt.size(); i++) {
                CompoundTag compoundTag = containerNbt.getCompound(i);
                int j = compoundTag.getByte("Slot") & 255;
                if (j < this.getContainerSize()) {
                    this.setItem(j, ItemStack.of(compoundTag));
                }
            }
        }

        @Override
        public @NotNull ListTag createTag() {
            ListTag listTag = new ListTag();

            for (int i = 0; i < this.getContainerSize(); i++) {
                ItemStack itemStack = this.getItem(i);
                if (!itemStack.isEmpty()) {
                    CompoundTag compoundTag = new CompoundTag();
                    compoundTag.putByte("Slot", (byte)i);
                    itemStack.save(compoundTag);
                    listTag.add(compoundTag);
                }
            }

            return listTag;
        }
    }
}
