package generations.gg.generations.core.generationscore.world.level.block.entities;

import com.google.common.annotations.VisibleForTesting;
import generations.gg.generations.core.generationscore.config.LegendKeys;
import generations.gg.generations.core.generationscore.config.SpeciesKey;
import generations.gg.generations.core.generationscore.world.entity.block.PokemonUtil;
import generations.gg.generations.core.generationscore.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.world.level.block.entities.shrines.ShrineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.Clearable;
import net.minecraft.world.Container;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.JukeboxBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.ticks.ContainerSingleItem;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Objects;

public class MeloettaMusicBoxBlockEntity extends ShrineBlockEntity implements Clearable, ContainerSingleItem {
    private static final int SONG_END_PADDING = 20;
    private final NonNullList<ItemStack> items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
    private int ticksSinceLastEvent;
    private long tickCount;
    private long recordStartedTick;
    private boolean isPlaying;

    public MeloettaMusicBoxBlockEntity(BlockPos pos, BlockState state) {
        super(GenerationsBlockEntities.MELOETTA_MUSIC_BOX.get(), pos, state);
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        if (tag.contains("RecordItem", 10)) {
            this.items.set(0, ItemStack.of(tag.getCompound("RecordItem")));
        }
        this.isPlaying = tag.getBoolean("IsPlaying");
        this.recordStartedTick = tag.getLong("RecordStartTick");
        this.tickCount = tag.getLong("TickCount");
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
        if (!this.getFirstItem().isEmpty()) {
            tag.put("RecordItem", this.getFirstItem().save(new CompoundTag()));
        }
        tag.putBoolean("IsPlaying", this.isPlaying);
        tag.putLong("RecordStartTick", this.recordStartedTick);
        tag.putLong("TickCount", this.tickCount);
    }

    public boolean isRecordPlaying() {
        return !this.getFirstItem().isEmpty() && this.isPlaying;
    }

    private void setHasRecordBlockState(@Nullable Entity entity, boolean hasRecord) {
        if (Objects.requireNonNull(this.level).getBlockState(this.getBlockPos()) == this.getBlockState()) {
            this.level.setBlock(this.getBlockPos(), (BlockState)this.getBlockState().setValue(JukeboxBlock.HAS_RECORD, hasRecord), 2);
            this.level.gameEvent(GameEvent.BLOCK_CHANGE, this.getBlockPos(), GameEvent.Context.of(entity, this.getBlockState()));
        }
    }

    @VisibleForTesting
    public void startPlaying() {
        this.recordStartedTick = this.tickCount;
        this.isPlaying = true;
        assert this.level != null;
        this.level.updateNeighborsAt(this.getBlockPos(), this.getBlockState().getBlock());
        this.level.levelEvent(null, 1010, this.getBlockPos(), Item.getId(this.getFirstItem().getItem()));
        this.setChanged();
    }

    private void stopPlaying() {
        this.isPlaying = false;
        Objects.requireNonNull(this.level).gameEvent(GameEvent.JUKEBOX_STOP_PLAY, this.getBlockPos(), GameEvent.Context.of(this.getBlockState()));
        this.level.updateNeighborsAt(this.getBlockPos(), this.getBlockState().getBlock());
        this.level.levelEvent(1011, this.getBlockPos(), 0);
        this.setChanged();
    }

    private void tick(Level level, BlockPos pos, BlockState state) {
        ++this.ticksSinceLastEvent;
        if (this.isRecordPlaying() && this.getFirstItem().getItem() instanceof RecordItem recordItem) {
            if (this.shouldRecordStopPlaying(recordItem)) {
                this.stopPlaying();

                if(getFirstItem().is(GenerationsItems.RELIC_SONG.get())) {
                    PokemonUtil.spawn(LegendKeys.MELOETTA.createProperties(70), level, pos);
                    setFirstItem(new ItemStack(GenerationsItems.INERT_RELIC_SONG.get()));
                }

            } else if (this.shouldSendJukeboxPlayingEvent()) {
                this.ticksSinceLastEvent = 0;
                level.gameEvent(GameEvent.JUKEBOX_PLAY, pos, GameEvent.Context.of(state));
                this.spawnMusicParticles(level, pos);
            }
        }
        ++this.tickCount;
    }

    private boolean shouldRecordStopPlaying(RecordItem record) {
        return this.tickCount >= this.recordStartedTick + (long)record.getLengthInTicks() + 20L;
    }

    private boolean shouldSendJukeboxPlayingEvent() {
        return this.ticksSinceLastEvent >= 20;
    }

    @Override
    public @NotNull ItemStack getItem(int slot) {
        return this.items.get(slot);
    }

    @Override
    public @NotNull ItemStack removeItem(int slot, int amount) {
        ItemStack itemStack = Objects.requireNonNullElse(this.items.get(slot), ItemStack.EMPTY);
        this.items.set(slot, ItemStack.EMPTY);
        if (!itemStack.isEmpty()) {
            this.setHasRecordBlockState(null, false);
            this.stopPlaying();
        }
        return itemStack;
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        if (stack.is(ItemTags.MUSIC_DISCS) && this.level != null) {
            this.items.set(slot, stack);
            this.setHasRecordBlockState(null, true);
            this.startPlaying();
        }
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return Container.stillValidBlockEntity(this, player);
    }

    @Override
    public boolean canPlaceItem(int index, ItemStack stack) {
        return stack.is(ItemTags.MUSIC_DISCS) && this.getItem(index).isEmpty();
    }

    @Override
    public boolean canTakeItem(Container target, int index, @NotNull ItemStack stack) {
        return target.hasAnyMatching(ItemStack::isEmpty);
    }

    private void spawnMusicParticles(Level level, BlockPos pos) {
        if (level instanceof ServerLevel serverLevel) {
            Vec3 vec3 = Vec3.atBottomCenterOf(pos).add(0.0, 1.2f, 0.0);
            float f = (float)level.getRandom().nextInt(4) / 24.0f;
            serverLevel.sendParticles(ParticleTypes.NOTE, vec3.x(), vec3.y(), vec3.z(), 0, f, 0.0, 0.0, 1.0);
        }
    }

    public void popOutRecord() {
        if (this.level == null || this.level.isClientSide) {
            return;
        }
        BlockPos blockPos = this.getBlockPos();
        ItemStack itemStack = this.getFirstItem();
        if (itemStack.isEmpty()) {
            return;
        }
        this.removeFirstItem();
        Vec3 vec3 = Vec3.atLowerCornerWithOffset(blockPos, 0.5, 1.01, 0.5).offsetRandom(this.level.random, 0.7f);
        ItemStack itemStack2 = itemStack.copy();
        ItemEntity itemEntity = new ItemEntity(this.level, vec3.x(), vec3.y(), vec3.z(), itemStack2);
        itemEntity.setDefaultPickUpDelay();
        this.level.addFreshEntity(itemEntity);
    }

    public static void playRecordTick(Level level, BlockPos pos, BlockState state, MeloettaMusicBoxBlockEntity jukebox) {
        jukebox.tick(level, pos, state);
    }

    @VisibleForTesting
    public void setRecordWithoutPlaying(ItemStack stack) {
        this.items.set(0, stack);
        Objects.requireNonNull(this.level).updateNeighborsAt(this.getBlockPos(), this.getBlockState().getBlock());
        this.setChanged();
    }
}
