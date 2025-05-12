package generations.gg.generations.core.generationscore.common.world.level.block.entities;

import com.google.common.annotations.VisibleForTesting;
import generations.gg.generations.core.generationscore.common.config.LegendKeys;
import generations.gg.generations.core.generationscore.common.world.item.GenerationsItems;
import generations.gg.generations.core.generationscore.common.world.entity.block.PokemonUtil;
import generations.gg.generations.core.generationscore.common.world.level.block.entities.shrines.ShrineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
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
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.item.JukeboxSongPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.JukeboxBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.JukeboxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.ticks.ContainerSingleItem;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Optional;

public class MeloettaMusicBoxBlockEntity extends ShrineBlockEntity implements Clearable, ContainerSingleItem {
    private static final int SONG_END_PADDING = 20;
    private ItemStack item;
    private final JukeboxSongPlayer jukeboxSongPlayer;

    public MeloettaMusicBoxBlockEntity(BlockPos pos, BlockState state) {
        super(GenerationsBlockEntities.MELOETTA_MUSIC_BOX.get(), pos, state);
        this.item = ItemStack.EMPTY;
        this.jukeboxSongPlayer = new JukeboxSongPlayer(this::onSongChanged, this.getBlockPos());
    }

    public JukeboxSongPlayer getSongPlayer() {
        return this.jukeboxSongPlayer;
    }

    public void onSongChanged() {
        if(getTheItem().is(GenerationsItems.RELIC_SONG.get())) {
            PokemonUtil.spawn(LegendKeys.MELOETTA.createPokemon(70), level, getBlockPos(), getAngle());
            this.setSongItemWithoutPlaying(new ItemStack(GenerationsItems.INERT_RELIC_SONG.get()));
        }

        this.level.updateNeighborsAt(this.getBlockPos(), this.getBlockState().getBlock());
        this.setChanged();
    }

    private void notifyItemChangedInJukebox(boolean hasRecord) {
        if (this.level != null && this.level.getBlockState(this.getBlockPos()) == this.getBlockState()) {
            this.level.setBlock(this.getBlockPos(), (BlockState)this.getBlockState().setValue(JukeboxBlock.HAS_RECORD, hasRecord), 2);
            this.level.gameEvent(GameEvent.BLOCK_CHANGE, this.getBlockPos(), GameEvent.Context.of(this.getBlockState()));
        }
    }

    public void popOutTheItem() {
        if (this.level != null && !this.level.isClientSide) {
            BlockPos blockPos = this.getBlockPos();
            ItemStack itemStack = this.getTheItem();
            if (!itemStack.isEmpty()) {
                this.removeTheItem();
                Vec3 vec3 = Vec3.atLowerCornerWithOffset(blockPos, 0.5, 1.01, 0.5).offsetRandom(this.level.random, 0.7F);
                ItemStack itemStack2 = itemStack.copy();
                ItemEntity itemEntity = new ItemEntity(this.level, vec3.x(), vec3.y(), vec3.z(), itemStack2);
                itemEntity.setDefaultPickUpDelay();
                this.level.addFreshEntity(itemEntity);
            }
        }
    }

    public static void tick(Level level, BlockPos pos, BlockState state, MeloettaMusicBoxBlockEntity jukebox) {
        jukebox.jukeboxSongPlayer.tick(level, state);
    }

    public int getComparatorOutput() {
        return (Integer)JukeboxSong.fromStack(this.level.registryAccess(), this.item).map(Holder::value).map(JukeboxSong::comparatorOutput).orElse(0);
    }

    public void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.contains("RecordItem", 10)) {
            this.item = (ItemStack)ItemStack.parse(registries, tag.getCompound("RecordItem")).orElse(ItemStack.EMPTY);
        } else {
            this.item = ItemStack.EMPTY;
        }

        if (tag.contains("ticks_since_song_started", 4)) {
            JukeboxSong.fromStack(registries, this.item).ifPresent((holder) -> {
                this.jukeboxSongPlayer.setSongWithoutPlaying(holder, tag.getLong("ticks_since_song_started"));
            });
        }

    }

    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if (!this.getTheItem().isEmpty()) {
            tag.put("RecordItem", this.getTheItem().save(registries));
        }

        if (this.jukeboxSongPlayer.getSong() != null) {
            tag.putLong("ticks_since_song_started", this.jukeboxSongPlayer.getTicksSinceSongStarted());
        }

    }

    public ItemStack getTheItem() {
        return this.item;
    }

    public ItemStack splitTheItem(int amount) {
        ItemStack itemStack = this.item;
        this.setTheItem(ItemStack.EMPTY);
        return itemStack;
    }

    public void setTheItem(ItemStack item) {
        this.item = item;
        boolean bl = !this.item.isEmpty();
        Optional<Holder<JukeboxSong>> optional = JukeboxSong.fromStack(this.level.registryAccess(), this.item);
        this.notifyItemChangedInJukebox(bl);
        if (bl && optional.isPresent()) {
            this.jukeboxSongPlayer.play(this.level, (Holder)optional.get());
        } else {
            this.jukeboxSongPlayer.stop(this.level, this.getBlockState());
        }

    }

    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    public BlockEntity getContainerBlockEntity() {
        return this;
    }

    public boolean canPlaceItem(int slot, ItemStack stack) {
        return stack.has(DataComponents.JUKEBOX_PLAYABLE) && this.getItem(slot).isEmpty();
    }

    public boolean canTakeItem(Container target, int slot, ItemStack stack) {
        return target.hasAnyMatching(ItemStack::isEmpty);
    }

    @VisibleForTesting
    public void setSongItemWithoutPlaying(ItemStack stack) {
        this.item = stack;
        JukeboxSong.fromStack(this.level.registryAccess(), stack).ifPresent((holder) -> {
            this.jukeboxSongPlayer.setSongWithoutPlaying(holder, 0L);
        });
        this.level.updateNeighborsAt(this.getBlockPos(), this.getBlockState().getBlock());
        this.setChanged();
    }

    @VisibleForTesting
    public void tryForcePlaySong() {
        JukeboxSong.fromStack(this.level.registryAccess(), this.getTheItem()).ifPresent((holder) -> {
            this.jukeboxSongPlayer.play(this.level, holder);
        });
    }
}
