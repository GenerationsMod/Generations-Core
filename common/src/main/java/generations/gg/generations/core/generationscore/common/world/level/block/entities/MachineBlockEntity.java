package generations.gg.generations.core.generationscore.common.world.level.block.entities;

import earth.terrarium.common_storage_lib.item.util.ItemProvider;
import earth.terrarium.common_storage_lib.resources.item.ItemResource;
import earth.terrarium.common_storage_lib.storage.base.CommonStorage;
import generations.gg.generations.core.generationscore.common.util.ExtendedsimpleItemContainer;
import generations.gg.generations.core.generationscore.common.world.container.GenerationsContainers;
import generations.gg.generations.core.generationscore.common.world.container.MachineBlockContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.stream.IntStream;

public class MachineBlockEntity extends SimpleBlockEntity implements ItemProvider.BlockEntity, MenuProvider {
    private final MachineBlockItemStackHandler candies = new MachineBlockItemStackHandler();

    private int bakeTime = 0;

    public MachineBlockEntity(BlockPos pos, BlockState state) {
        super(GenerationsBlockEntities.MACHINE_BLOCK.get(), pos, state);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.translatable("machine_block");
    }

    @Override
    public CommonStorage<ItemResource> getItems(@Nullable Direction direction) {
        return candies;
    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag nbt, @NotNull HolderLookup.Provider provider) {
        bakeTime = nbt.getInt("bakeTime");
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag nbt, @NotNull HolderLookup.Provider provider) {
        super.saveAdditional(nbt, provider);
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
        return new MachineBlockContainer(new GenerationsContainers.CreationContext<>(i, arg, this));
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

    public class MachineBlockItemStackHandler extends ExtendedsimpleItemContainer {
        private boolean locked;
        public MachineBlockItemStackHandler() {
            super(MachineBlockEntity.this, 18);
        }

        @Override
        public long insert(int index, ItemResource resource, long amount, boolean simulate) {
            if(locked) return 0;
            return super.insert(index, resource, amount, simulate);
        }

        @Override
        public long extract(int index, ItemResource resource, long amount, boolean simulate) {
            if(locked) return 0;
            return super.extract(index, resource, amount, simulate);
        }


        private boolean isFull() {
            return IntStream.range(0, this.size())
                    .mapToObj(candies::get)
                    .allMatch(count -> count.isEmpty());
        }
    }
}
