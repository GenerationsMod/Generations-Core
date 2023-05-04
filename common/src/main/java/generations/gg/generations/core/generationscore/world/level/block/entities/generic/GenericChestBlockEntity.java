package generations.gg.generations.core.generationscore.world.level.block.entities.generic;

import com.pokemod.pokemod.world.level.block.generic.GenericChestBlock;
import com.pokemod.pokemod.world.container.GenericChestContainer;
import com.pokemod.pokemod.world.container.GenericContainer;
import com.pokemod.pokemod.world.level.block.entities.PokeModBlockEntities;
import generations.gg.generations.core.generationscore.world.container.GenericChestContainer;
import generations.gg.generations.core.generationscore.world.container.GenericContainer;
import generations.gg.generations.core.generationscore.world.level.block.generic.GenericChestBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.ChestLidController;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@SuppressWarnings("deprecation")
public class GenericChestBlockEntity extends RandomizableContainerBlockEntity implements GenericContainer, LidBlockEntity {
    private static final int EVENT_SET_OPEN_COUNT = 1;
    private int width;
    private int height;
    private String defaultTranslation;
    private NonNullList<ItemStack> items;
    private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter(){

        @Override
        protected void onOpen(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state) {
            GenericChestBlockEntity.this.playSound(state, SoundEvents.CHEST_OPEN);
        }

        @Override
        protected void onClose(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state) {
            GenericChestBlockEntity.this.playSound(state, SoundEvents.CHEST_CLOSE);
        }

        @Override
        protected void openerCountChanged(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state, int count, int openCount) {
            GenericChestBlockEntity.this.signalOpenCount(level, pos, state, count, openCount);
        }

        @Override
        protected boolean isOwnContainer(Player player) {
            if (player.containerMenu instanceof GenericChestContainer) {
                Container container = ((GenericChestContainer)player.containerMenu).getContainer();
                return container == GenericChestBlockEntity.this;
            }
            return false;
        }
    };

    private final ChestLidController chestLidController = new ChestLidController();
    private LazyOptional<IItemHandlerModifiable> chestHandler;

    public GenericChestBlockEntity(BlockPos arg, BlockState arg2) {
        this(arg, arg2, 9, 1, "container.chest");
    }

    public GenericChestBlockEntity(BlockPos arg, BlockState arg2, int width, int height, String defaultTranslation) {
        super(PokeModBlockEntities.GENERIC_CHEST.get(), arg, arg2);
        this.width = width;
        this.height = height;
        this.defaultTranslation = defaultTranslation;
        items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
    }

    @Override
    public int getContainerSize() {
        return getWidth() * getHeight();
    }

    @Override
    protected @NotNull Component getDefaultName() {
        return Component.translatable(defaultTranslation);
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        this.width = tag.getInt("width");
        this.height = tag.getInt("height");
        this.defaultTranslation = tag.getString("defaultTranslation");
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(tag)) {
            ContainerHelper.loadAllItems(tag, this.items);
        }
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
        if (!this.trySaveLootTable(tag)) {
            ContainerHelper.saveAllItems(tag, this.items);
        }
        tag.putInt("width", width);
        tag.putInt("height", height);
        tag.putString("defaultTranslation", defaultTranslation);
    }

    public static void lidAnimateTick(Level level, BlockPos pos, BlockState state, GenericChestBlockEntity blockEntity) {
        blockEntity.chestLidController.tickLid();
    }

    void playSound(BlockState state, SoundEvent sound) {
        Vec3i vec3i = state.getValue(GenericChestBlock.FACING).getNormal();
        double d = (double)this.worldPosition.getX() + 0.5 + (double)vec3i.getX() / 2.0;
        double e = (double)this.worldPosition.getY() + 0.5 + (double)vec3i.getY() / 2.0;
        double f = (double)this.worldPosition.getZ() + 0.5 + (double)vec3i.getZ() / 2.0;
        assert this.level != null;
        this.level.playSound(null, d, e, f, sound, SoundSource.BLOCKS, 0.5f, this.level.random.nextFloat() * 0.1f + 0.9f);
    }

    @Override
    public boolean triggerEvent(int id, int type) {
        if (id == 1) {
            this.chestLidController.shouldBeOpen(type > 0);
            return true;
        }
        return super.triggerEvent(id, type);
    }


    @Override
    public void startOpen(@NotNull Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.incrementOpeners(player, Objects.requireNonNull(this.getLevel()), this.getBlockPos(), this.getBlockState());
        }
    }

    @Override
    public void stopOpen(@NotNull Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.decrementOpeners(player, Objects.requireNonNull(this.getLevel()), this.getBlockPos(), this.getBlockState());
        }
    }

    @Override
    protected @NotNull NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(@NotNull NonNullList<ItemStack> itemStacks) {
        this.items = itemStacks;
    }

    @Override
    public float getOpenNess(float partialTicks) {
        return chestLidController.getOpenness(partialTicks);
    }

    @Override
    protected @NotNull AbstractContainerMenu createMenu(int containerId, @NotNull Inventory inventory) {
        return new GenericChestContainer(containerId, inventory, this);
    }

    @Override
    public void setBlockState(@NotNull BlockState blockState) {
        super.setBlockState(blockState);
        if (this.chestHandler != null) {
            LazyOptional<IItemHandlerModifiable> oldHandler = this.chestHandler;
            this.chestHandler = null;
            oldHandler.invalidate();
        }
    }

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, Direction side) {
        if (!this.remove && cap == ForgeCapabilities.ITEM_HANDLER) {
            if (this.chestHandler == null) {
                this.chestHandler = LazyOptional.of(this::createHandler);
            }
            return this.chestHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    private IItemHandlerModifiable createHandler() {
        return new InvWrapper(this);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        if (this.chestHandler != null) {
            this.chestHandler.invalidate();
            this.chestHandler = null;
        }
    }


    public void recheckOpen() {
        if (!this.remove) {
            this.openersCounter.recheckOpeners(Objects.requireNonNull(this.getLevel()), this.getBlockPos(), this.getBlockState());
        }
    }

    protected void signalOpenCount(Level level, BlockPos pos, BlockState state, int eventId, int eventParam) {
        Block block = state.getBlock();
        level.blockEvent(pos, block, 1, eventParam);
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }
}
