package generations.gg.generations.core.generationscore.world.container;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface GenericContainer extends Container, MenuProvider {
    int getWidth();

    int getHeight();

    default void openScreen(Player player) {
        if (!player.isLocalPlayer()) NetworkHooks.openScreen((ServerPlayer) player, this, byteBuf -> byteBuf.writeVarInt(getWidth()).writeVarInt(getHeight()));
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
    }
}
