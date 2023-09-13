package generations.gg.generations.core.generationscore.world.level.block.entities.shrines.altar;

import earth.terrarium.botarium.common.item.ItemContainerBlock;
import earth.terrarium.botarium.common.item.SerializableContainer;
import generations.gg.generations.core.generationscore.client.model.ModelContextProviders;
import generations.gg.generations.core.generationscore.util.ExtendedsimpleItemContainer;
import generations.gg.generations.core.generationscore.util.GenerationsUtils;
import generations.gg.generations.core.generationscore.world.entity.block.PokemonUtil;
import generations.gg.generations.core.generationscore.world.item.CreationTrioItem;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.item.RedChainItem;
import generations.gg.generations.core.generationscore.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.world.level.block.entities.shrines.InteractShrineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TimeSpaceAltarBlockEntity extends InteractShrineBlockEntity implements ItemContainerBlock, ModelContextProviders.VariantProvider {

    private final TimeSpaceAltarItemStackHandler handler = new TimeSpaceAltarItemStackHandler();

    public TimeSpaceAltarBlockEntity(BlockPos pos, BlockState state) {
        super(GenerationsBlockEntities.TIMESPACE_ALTAR.get(), pos, state);
    }

    public boolean activate(ServerPlayer player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (stack.getItem() instanceof RedChainItem && !handler.hasRedChain()) {
            ItemStack chain = handler.insertItem(1, stack, false);

            if (ItemStack.isSameItem(stack, chain)) return false;
            player.setItemInHand(hand, chain);

            trySpawn(player);

            return true;
        } else if (stack.getItem() instanceof CreationTrioItem && !handler.hasOrb()) {
            ItemStack chain = handler.insertItem(0, stack, false);

            if (ItemStack.isSameItem(stack, chain)) return false;
            player.setItemInHand(hand, chain);

            trySpawn(player);
            return true;
        } else if (stack.isEmpty()) {
            player.getInventory().placeItemBackInInventory(handler.extractItem());
            return true;
        } else return false;
    }

    public void trySpawn(ServerPlayer player) {
        if (handler.shouldSpawn()) {
            var id = ((CreationTrioItem) handler.getItem(0).getItem()).getSpeciesId();
            toggleActive();
            PokemonUtil.spawn(GenerationsUtils.parseProperties(id), level, getBlockPos());
            RedChainItem.incrementUsage(handler.getItem(1));
            if (RedChainItem.getUses(handler.getItem(1)) >= RedChainItem.MAX_USES)
                handler.setItem(1, ItemStack.EMPTY);
            handler.setItem(0, ItemStack.EMPTY);
            handler.dumpAllIntoPlayerInventory(player);
            sync();
            toggleActive();
        }
    }

    @Override
    public String getVariant() {
        return handler.hasRedChain() ? "red chain" : "none";
    }

    @Override
    public SerializableContainer getContainer() {
        return handler;
    }

    @Nullable
    public CreationTrioItem getOrb() {
        if (handler.getItem(0).getItem() instanceof CreationTrioItem item) return item;
        return null;
    }

    class TimeSpaceAltarItemStackHandler extends ExtendedsimpleItemContainer {

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

        public boolean hasOrb() {
            return !getItem(0).isEmpty();
        }


        public ItemStack extractItem() {
            for (int i = 0; i < 2; i++)
                if (!getItem(i).isEmpty()) return extractItem(i, 1, false);

            return ItemStack.EMPTY;
        }

        public boolean shouldSpawn() {
            return hasRedChain() && hasOrb();
        }

        public void dumpAllIntoPlayerInventory(ServerPlayer player) {
            for (ItemStack stack : getItems())
                player.getInventory().placeItemBackInInventory(stack);

            clearContent();
        }
    }
}
