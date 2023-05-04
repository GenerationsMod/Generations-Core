package generations.gg.generations.core.generationscore.world.level.block.entities;

import com.pokemod.pokemod.client.model.ModelContextProviders;
import com.pokemod.pokemod.registries.types.PixelmonData;
import com.pokemod.pokemod.world.entity.pixelmon.PixelmonEntity;
import com.pokemod.pokemod.world.item.PokeModItems;
import com.pokemod.pokemod.world.item.CreationTrioItem;
import com.pokemod.pokemod.world.item.RedChainItem;
import generations.gg.generations.core.generationscore.client.model.ModelContextProviders;
import generations.gg.generations.core.generationscore.world.item.CreationTrioItem;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.item.RedChainItem;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TimeSpaceAltarBlockEntity extends InteractShrineBlockEntity implements ModelContextProviders.VariantProvider {

    private final TimeSpaceAltarItemStackHandler handler = new TimeSpaceAltarItemStackHandler();

    public TimeSpaceAltarBlockEntity(BlockPos pos, BlockState state) {
        super(PokeModBlockEntities.TIMESPACE_ALTAR.get(), pos, state);
    }

    @Override
    protected void readNbt(CompoundTag nbt) {
        handler.deserializeNBT(nbt.getCompound("inventory"));
    }

    @Override
    protected void writeNbt(CompoundTag nbt) {
        nbt.put("inventory", handler.serializeNBT());
    }

    public boolean activate(ServerPlayer player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (stack.getItem() instanceof RedChainItem && !handler.hasRedChain()) {
            ItemStack chain = handler.insertItem(1, stack, false);

            if (ItemStack.isSame(stack, chain)) return false;
            player.setItemInHand(hand, chain);

            trySpawn(player);

            return true;
        } else if (stack.getItem() instanceof CreationTrioItem && !handler.hasOrb()) {
            ItemStack chain = handler.insertItem(0, stack, false);

            if (ItemStack.isSame(stack, chain)) return false;
            player.setItemInHand(hand, chain);

            trySpawn(player);
            return true;
        } else if (stack.isEmpty()) {
            ItemStack chain = handler.extractItem();

            player.getInventory().placeItemBackInInventory(chain);
            return true;
        } else {
            return false;
        }

    }

    public void trySpawn(ServerPlayer player) {
        if (handler.shouldSpawn()) {
            var id = ((CreationTrioItem) handler.getStackInSlot(0).getItem()).getSpeciesId();
            toggleActive();
//            level.addFreshEntity(new PixelmonEntity(level, PixelmonData.of(id), getBlockPos())); TODO: Spawn pokemon
            RedChainItem.incrementUsage(handler.getStackInSlot(1));
            if (RedChainItem.getUses(handler.getStackInSlot(1)) >= RedChainItem.MAX_USES)
                handler.setStackInSlot(1, ItemStack.EMPTY);
            handler.setStackInSlot(0, ItemStack.EMPTY);
            handler.dumpAllIntoPlayerInventory(player);
            sync();
            toggleActive();
        }
    }

    @Override
    public String getVariant() {
        return handler.hasRedChain() ? "red chain" : "none";
    }

    @Nullable
    public CreationTrioItem getOrb() {
        if (handler.getStackInSlot(0).getItem() instanceof CreationTrioItem item) return item;
        return null;
    }

    class TimeSpaceAltarItemStackHandler extends ItemStackHandler {

        public TimeSpaceAltarItemStackHandler() {
            super(2);
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
            return !getStackInSlot(1).isEmpty();
        }

        public boolean hasOrb() {
            return !getStackInSlot(0).isEmpty();
        }


        public ItemStack extractItem() {
            for (int i = 0; i < 2; i++) {
                ItemStack stack = getStackInSlot(i);

                if (!stack.isEmpty()) return extractItem(i, 1, false);
            }

            return ItemStack.EMPTY;
        }

        public boolean shouldSpawn() {
            return hasRedChain() && hasOrb();
        }

        public void dumpAllIntoPlayerInventory(ServerPlayer player) {
            for (ItemStack stack : stacks) {
                player.getInventory().placeItemBackInInventory(stack);
            }

            stacks.clear();
        }

        @Override
        protected void onContentsChanged(int slot) {
            sync();
        }
    }
}
