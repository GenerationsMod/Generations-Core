package generations.gg.generations.core.generationscore.common.world.item;

import com.cobblemon.mod.common.api.events.CobblemonEvents;
import generations.gg.generations.core.generationscore.common.world.container.GenericContainer;
import generations.gg.generations.core.generationscore.common.world.container.WalkmonContainer;
import generations.gg.generations.core.generationscore.common.world.container.GenericContainer;
import generations.gg.generations.core.generationscore.common.world.container.WalkmonContainer;
import generations.gg.generations.core.generationscore.common.world.container.GenericContainer;
import generations.gg.generations.core.generationscore.common.world.container.WalkmonContainer;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class WalkmonItem extends Item {
    private final int row;
    private final String defaultTranslation;

    public WalkmonItem(Properties properties, int row, String type) {
        super(properties);
        this.defaultTranslation = "container." + type;
        this.row = row;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {
        if (!level.isClientSide() && usedHand == InteractionHand.MAIN_HAND) {
            getDiscs(player.getItemInHand(usedHand)).openScreen(player, player.getInventory().selected);
        }

        return super.use(level, player, usedHand);
    }

    public GenericContainer.SimpleGenericContainer getDiscs(ItemStack stack) {
        var container = new DiscHolder();
        container.fromTag(stack.getOrCreateTag().getList("inventory", Tag.TAG_COMPOUND));
        return container;
    }

    public void saveDiscs(GenericContainer.SimpleGenericContainer container, ItemStack stack) {
        stack.getOrCreateTag().put("inventory", container.createTag());
    }

    public class DiscHolder extends GenericContainer.SimpleGenericContainer {
        public DiscHolder() {
            super(9, WalkmonItem.this.row);
        }

        @Override
        public @NotNull Component getDisplayName() {
            return Component.translatable(WalkmonItem.this.defaultTranslation);
        }

        @Override
        public @Nullable AbstractContainerMenu createMenu(int i, @NotNull Inventory arg, @NotNull Player arg2) {
            return new WalkmonContainer(i, arg, this, arg2.getInventory().selected);
        }

        public Optional<ItemStack> getDisc() {
            return Optional.empty();
        }
    }
}
