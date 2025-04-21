package generations.gg.generations.core.generationscore.common.world.level.block.entities.generic;

import generations.gg.generations.core.generationscore.common.world.level.block.entities.GenerationsBlockEntities;
import generations.gg.generations.core.generationscore.common.world.level.block.generic.GenericChestBlock;
import generations.gg.generations.core.generationscore.common.world.container.GenericChestContainer;
import generations.gg.generations.core.generationscore.common.world.container.GenericContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
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
import net.minecraft.world.level.block.entity.ChestLidController;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@SuppressWarnings("deprecation")
public class GenericChestBlockEntity extends RandomizableContainerBlockEntity implements GenericContainer, LidBlockEntity {
    private static final int EVENT_SET_OPEN_COUNT = 1;
    private int width;
    private int height;
    private String defaultTranslation;
    private NonNullList<ItemStack> items;
    private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {

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
                Container container = ((GenericChestContainer) player.containerMenu).getContainer();
                return container == GenericChestBlockEntity.this;
            }
            return false;
        }
    };

    private final ChestLidController chestLidController = new ChestLidController();

    public GenericChestBlockEntity(BlockPos arg, BlockState arg2) {
        this(arg, arg2, 9, 1, "container.chest");
    }

    public GenericChestBlockEntity(BlockPos arg, BlockState arg2, int width, int height, String defaultTranslation) {
        super(GenerationsBlockEntities.GENERIC_CHEST.get(), arg, arg2);
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
    public void loadAdditional(@NotNull CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);
        this.width = tag.getInt("width");
        this.height = tag.getInt("height");
        this.defaultTranslation = tag.getString("defaultTranslation");
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(tag))
            ContainerHelper.loadAllItems(tag, this.items, provider);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.Provider provider) {
        super.saveAdditional(tag, provider);
        if (!this.trySaveLootTable(tag)) ContainerHelper.saveAllItems(tag, this.items, provider);
        tag.putInt("width", width);
        tag.putInt("height", height);
        tag.putString("defaultTranslation", defaultTranslation);
    }

    public static void lidAnimateTick(Level level, BlockPos pos, BlockState state, GenericChestBlockEntity blockEntity) {
        blockEntity.chestLidController.tickLid();
    }

    void playSound(BlockState state, SoundEvent sound) {
        Vec3i vec3i = state.getValue(GenericChestBlock.FACING).getNormal();
        double d = (double) this.worldPosition.getX() + 0.5 + (double) vec3i.getX() / 2.0;
        double e = (double) this.worldPosition.getY() + 0.5 + (double) vec3i.getY() / 2.0;
        double f = (double) this.worldPosition.getZ() + 0.5 + (double) vec3i.getZ() / 2.0;
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
        if (!this.remove && !player.isSpectator())
            this.openersCounter.incrementOpeners(player, Objects.requireNonNull(this.getLevel()), this.getBlockPos(), this.getBlockState());
    }

    @Override
    public void stopOpen(@NotNull Player player) {
        if (!this.remove && !player.isSpectator())
            this.openersCounter.decrementOpeners(player, Objects.requireNonNull(this.getLevel()), this.getBlockPos(), this.getBlockState());
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
    }

    public void recheckOpen() {
        if (!this.remove)
            this.openersCounter.recheckOpeners(Objects.requireNonNull(this.getLevel()), this.getBlockPos(), this.getBlockState());
    }

    protected void signalOpenCount(Level level, BlockPos pos, BlockState state, int eventId, int eventParam) {
        level.blockEvent(pos, state.getBlock(), 1, eventParam);
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
