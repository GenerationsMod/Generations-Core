package generations.gg.generations.core.generationscore.world.level.block.entities.shrines.altar;

import earth.terrarium.botarium.common.item.ItemContainerBlock;
import earth.terrarium.botarium.common.item.SerializableContainer;
import generations.gg.generations.core.generationscore.GenerationsCore;
import generations.gg.generations.core.generationscore.client.model.ModelContextProviders;
import generations.gg.generations.core.generationscore.util.ExtendedsimpleItemContainer;
import generations.gg.generations.core.generationscore.util.GenerationsUtils;
import generations.gg.generations.core.generationscore.world.entity.block.PokemonUtil;
import generations.gg.generations.core.generationscore.world.item.legends.CreationTrioItem;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.item.legends.RedChainItem;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.shrines.InteractShrineBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.entities.shrines.ShrineBlockEntity;
import generations.gg.generations.core.generationscore.world.level.block.shrines.ShrineBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TimeSpaceAltarBlockEntity extends ShrineBlockEntity implements ItemContainerBlock, ModelContextProviders.VariantProvider {

    private TimeSpaceAltarItemStackHandler handler;

    public TimeSpaceAltarBlockEntity(BlockPos pos, BlockState state) {
        super(GenerationsBlockEntities.TIMESPACE_ALTAR.get(), pos, state);
    }


    @Override
    public String getVariant() {
        return handler.hasRedChain() ? "red chain" : "none";
    }

    @Override
    public TimeSpaceAltarItemStackHandler getContainer() {
        return handler == null ? (handler = new TimeSpaceAltarItemStackHandler()) : handler;
    }

    @Nullable
    public CreationTrioItem getOrb() {
        if (handler.getItem(0).getItem() instanceof CreationTrioItem item) return item;
        return null;
    }

    public class TimeSpaceAltarItemStackHandler extends ExtendedsimpleItemContainer {

        public TimeSpaceAltarItemStackHandler() {
            super(TimeSpaceAltarBlockEntity.this, 2);
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            if (slot == 0) {
                return stack.getItem() instanceof CreationTrioItem;
            } else {
                return stack.is(GenerationsItems.RED_CHAIN.get()) && RedChainItem.isEnchanted(stack);
            }
        }

        @Override
        protected int getStackLimit(int slot, @NotNull ItemStack stack) {
            return 1;
        }

        public boolean hasRedChain() {
            return !getItem(1).isEmpty();
        }

        public boolean hasOrb(ServerPlayer player) {
            var orb = getItem(0);

            return !orb.isEmpty() && !GenerationsCore.CONFIG.caught.capped(player, ((CreationTrioItem) orb.getItem()).getSpeciesId());
        }


        public ItemStack extractItem() {
            for (int i = 0; i < 2; i++)
                if (!getItem(i).isEmpty()) return extractItem(i, 1, false);

            return ItemStack.EMPTY;
        }

        public boolean shouldSpawn(ServerPlayer player) {
            return hasRedChain() && hasOrb(player);
        }

        public void dumpAllIntoPlayerInventory(ServerPlayer player) {
            for (ItemStack stack : getItems())
                player.getInventory().placeItemBackInInventory(stack);

            clearContent();
        }
    }
}
