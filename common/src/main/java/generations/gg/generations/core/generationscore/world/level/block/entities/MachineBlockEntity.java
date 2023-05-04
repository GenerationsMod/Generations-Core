package generations.gg.generations.core.generationscore.world.level.block.entities;

import com.pokemod.pokemod.world.container.MachineBlockContainer;
import com.pokemod.pokemod.world.container.PixelmonContainers;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.stream.IntStream;

public class MachineBlockEntity extends SimpleBlockEntity implements MenuProvider {
    private final MachineBlockItemStackHandler candies = new MachineBlockItemStackHandler();

    private int bakeTime = 0;

    public MachineBlockEntity(BlockPos pos, BlockState state) {
        super(PokeModBlockEntities.MACHINE_BLOCK.get(), pos, state);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("machine_block");
    }

    protected void readNbt(CompoundTag nbt) {
        candies.deserializeNBT(nbt.getCompound("candies"));
        bakeTime = nbt.getInt("bakeTime");
    }
    protected void writeNbt(CompoundTag nbt) {
        nbt.put("candies", candies.serializeNBT());
        nbt.putInt("bakeTime", bakeTime);
    }

    public void tick() {
        candies.locked = candies.isFull();

        if(candies.locked) {
            if(bakeTime < 100) {
                bakeTime++;
            }
        } else {
            bakeTime = 0;
        }

        sync();
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, @NotNull Inventory arg, @NotNull Player arg2) {
        return new MachineBlockContainer(new PixelmonContainers.CreationContext<>(i, arg, this));
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state1, MachineBlockEntity blockEntity) {
        blockEntity.tick();
    }

    public MachineBlockItemStackHandler getCandies() {
        return candies;
    }

    public int getBakeTime() {
        return bakeTime;
    }

    private class MachineBlockItemStackHandler extends ItemStackHandler{
        private boolean locked;
        public MachineBlockItemStackHandler() {
            super(18);
        }

        @Override
        public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
            if(locked) return stack;
            return super.insertItem(slot, stack, simulate);
        }

        @Override
        public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
            if(locked) return ItemStack.EMPTY;
            return super.extractItem(slot, amount, simulate);
        }

        private boolean isFull() {
            return IntStream.range(0, this.getSlots())
                    .mapToObj(candies::getStackInSlot)
                    .mapToInt(ItemStack::getCount)
                    .allMatch(count -> count > 0 && count >= candies.getSlotLimit(-1));
        }
    }
}
